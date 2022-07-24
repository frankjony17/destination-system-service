package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.EmailEnum;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Classe responsável por enviar emails
 * Created by diego on 09/12/16.
 */
@EnableAsync
@Component
public class EnviadorEmail {

    private static final String GESTAO_PRAIA = "Gestão Praia";
    @Autowired
    private JavaMailSender javaMailSender;

    @Value(value = "${mail.remetente}")
    private String remetente;

    @Autowired
    private EmailUtils emailUtils;

    /**
     * Envia email com assunto para um destinatário
     * @param emailEnum
     * @param assunto
     * @param destinatario
     * @param params
     * @throws MessagingException
     */
    @Async
    public void enviar(EmailEnum emailEnum, String assunto, String destinatario, String... params) throws MessagingException {
        enviar(emailEnum, assunto, new ArrayList<>(asList(destinatario)), params);
    }

    /**
     * Envia email com assunto para vários destinatários
     * @param emailEnum
     * @param assunto
     * @param destinatarios
     * @param params
     * @throws MessagingException
     */
    @Async
    public void enviar(EmailEnum emailEnum, String assunto, List<String> destinatarios, String... params) throws MessagingException {

        String mensagem = emailUtils.getEmail(emailEnum, params);

        enviar(assunto, mensagem,
                Collections.emptyList(),
                destinatarios.toArray(new String[destinatarios.size()]));
    }

    /**
     * Enviar email com assunto para vários destinatários e vários anexos
     * @param emailEnum
     * @param assunto
     * @param destinatarios
     * @param anexos
     * @param params
     * @throws MessagingException
     */
    @Async
    public void enviar(EmailEnum emailEnum, String assunto, List<String> destinatarios, List<File> anexos, String... params) throws MessagingException {

        String mensagem = emailUtils.getEmail(emailEnum, params);

        enviar(assunto, mensagem,
                anexos,
                destinatarios.toArray(new String[destinatarios.size()]));
    }

    private void enviar(String assunto, String messagem, List<File> anexos, String... destinatarios) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        mimeMessageHelper.setFrom(remetente);
        mimeMessageHelper.setSubject(assunto);
        mimeMessageHelper.setTo(destinatarios);
        mimeMessageHelper.setText(messagem, true);

        adicionarAnexo(anexos, mimeMessageHelper);

        javaMailSender.send(message);
    }

    private void adicionarAnexo(List<File> anexos, MimeMessageHelper mimeMessageHelper) throws MessagingException {
        Optional<List<File>> optional = Optional.ofNullable(anexos);
        Boolean existeArquivo = optional.map(item -> item.size() > Constants.ZERO).orElse(false);
        if (existeArquivo) {
            for (File file : anexos) {
                mimeMessageHelper.addAttachment(file.getName(), file);
            }
        }
    }

    /**
     * Enviar status da análise técnicas para email
     * @param statusAnaliseTecnica
     * @param email
     * @param numeroAtendimento
     * @param nomeRequerente
     * @throws MessagingException
     */
    @Async
    public void enviar(StatusAnaliseTecnica statusAnaliseTecnica, String email, String numeroAtendimento, String nomeRequerente) throws MessagingException {

        Integer idAnaliseTecnica = statusAnaliseTecnica.getId();
        String horaAtual = DataUtil.getHoraAtualFormatada();
        String dataAtual = DataUtil.getDataAtualFormatada();

        enviar(numeroAtendimento, nomeRequerente, idAnaliseTecnica, horaAtual, dataAtual, email);
    }

    private void enviar(String numeroAtendimento, String nomeRequerente, Integer idAnaliseTecnica, String horaAtual, String dataAtual, String email) throws MessagingException {
        if (idAnaliseTecnica.equals(StatusAnaliseTecnicaEnum.CANCELADO.getCodigo())) {
            enviar(EmailEnum.CANCELAMENTO, GESTAO_PRAIA, email, GESTAO_PRAIA, numeroAtendimento, nomeRequerente);
        } else if (idAnaliseTecnica.equals(StatusAnaliseTecnicaEnum.AGUARDANDO_REQUERENTE.getCodigo())) {
            enviar(EmailEnum.ANALISE_AGUARDANDO_REQUERENTE, GESTAO_PRAIA, email, GESTAO_PRAIA, numeroAtendimento, nomeRequerente);
        } else if (idAnaliseTecnica.equals(StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA.getCodigo())) {
            enviar(EmailEnum.ANALISE_ENVIADA_SUPERINTENDENTE, GESTAO_PRAIA, email, "Tecnico", dataAtual, horaAtual);
        } else if (idAnaliseTecnica.equals(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA.getCodigo())) {
            enviar(EmailEnum.ANALISE_ENVIADA_SUPERINTENDENTE, GESTAO_PRAIA, email, "Chefia", dataAtual, horaAtual);
        } else if (idAnaliseTecnica.equals(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo())) {
            enviar(EmailEnum.ANALISE_ENVIADA_SUPERINTENDENTE, GESTAO_PRAIA, email, "Secretario", dataAtual, horaAtual);
        } else {
            enviar(EmailEnum.ANALISE_ENVIADA_SUPERINTENDENTE, GESTAO_PRAIA, email, "Superintendente", dataAtual, horaAtual);
        }
    }

}
