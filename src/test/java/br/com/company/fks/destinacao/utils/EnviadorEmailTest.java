package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.EmailEnum;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import javax.mail.internet.MimeMessage;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 17/05/17.
 */
@RunWith(MockitoJUnitRunner.class)
@IntegrationTest("server.port:0")
public class EnviadorEmailTest{

    private static final String REMETENTE = "diego.alves@basis.com.br";

    @InjectMocks
    private EnviadorEmail enviadorEmail;

    @Mock
    private EmailUtils emailUtils;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage mimeMessage;

    @Before
    public void setup(){
        ReflectionTestUtils.setField(enviadorEmail, "remetente", "teste");
        when(emailUtils.getEmail(any(EmailEnum.class), anyVararg())).thenReturn("teste");
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    @SneakyThrows
    public void enviarCancelado() {
        enviarEmail(10);
    }

    @Test
    @SneakyThrows
    public void enviarAguardandoRequerente() {
        enviarEmail(9);
    }

    @Test
    @SneakyThrows
    public void enviarAguardandoAnaliseTecnica() {
        enviarEmail(1);
    }

    @Test
    @SneakyThrows
    public void enviarAguardandoChefia() {
        enviarEmail(4);
    }

    @Test
    @SneakyThrows
    public void enviarAguardandoSecretario() {
        enviarEmail(6);
    }

    @Test
    @SneakyThrows
    public void enviarAguardandoSuperintendente() {
        enviarEmail(5);
    }

    @Test
    @SneakyThrows
    public void enviarSemDestinatarioCorrespondente() {
        enviarEmail(20);
    }

    @Test
    @SneakyThrows
    public void enviar() {
        enviadorEmail.enviar(EmailEnum.CANCELAMENTO, "Teste", asList(REMETENTE), criarAnexo(), "teste", "teste2", "teste3");
    }

    @Test
    @SneakyThrows
    public void enviarAnexosEmpty() {
        enviadorEmail.enviar(EmailEnum.CANCELAMENTO, "Teste", asList(REMETENTE), Collections.emptyList(), "teste", "teste2", "teste3");
    }

    @SneakyThrows
    private void enviarEmail(Integer id) {
        StatusAnaliseTecnica statusAnaliseTecnica = new StatusAnaliseTecnica();
        statusAnaliseTecnica.setId(id);
        enviadorEmail.enviar(statusAnaliseTecnica, REMETENTE, "123", "Teste");
    }

    private List<File> criarAnexo() {
        String pathfile = getClass().getResource("/arquivos/teste.jpeg").getFile().replace("%20", " ");
        File file = new File(pathfile);
        return asList(file);
    }


}
