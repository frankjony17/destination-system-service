package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.RequerimentoDestinacaoDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.AtendimentoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Basis Tecnologia on 06/10/2016.
 */

/**
 * Classe que conterá a comunicação rest de Atendimento
 */
@RestController
@RequestMapping(value = "atendimento", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AtendimentoController {

    private static final Logger LOGGER = Logger.getLogger(RequerimentoDestinacaoDTO.class);

    @Autowired
    private AtendimentoService atendimentoService;

    /**
     *
     * @param numeroAtendimento recebe o número de atendimento que funciona como id do requerimento
     * @return o requerimento atrelado ao numero de atendimento
     */
    @RequestMapping(value = "buscar-requerimento", method = RequestMethod.GET)
    public ResponseEntity<Resposta<RequerimentoDestinacaoDTO>> findRequerimentoByNumeroAtendimento(@RequestParam(value = "numeroAtendimento") String numeroAtendimento) {
        Resposta<RequerimentoDestinacaoDTO> resposta;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(
                    atendimentoService.findRequerimentoByNumeroAtendimento(numeroAtendimento)).build();
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }

        return new ResponseEntity<>(resposta, HttpStatus.OK);

    }

    /**
     *
     * @param numeroProcesso recebe o número do processo gerado no SEI
     * @return o requerimento que tem o respectivo numero de processo atrelado
     */
    @RequestMapping(value = "buscar-requerimento-numero-processo", method = RequestMethod.GET)
    public ResponseEntity<Resposta<RequerimentoDestinacaoDTO>> findRequerimentoByNumeroProcesso(@RequestParam(value = "numeroProcesso") String numeroProcesso) {
        Resposta<RequerimentoDestinacaoDTO> resposta = null;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(
                    atendimentoService.findRequerimentoByNumeroProcesso(numeroProcesso)).build();
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param idRequerimento id do requerimento que será analisado
     * @param idProcedimento id o procedimento que foi feito
     * @return flag com o status do resultado da verificação
     */
    @RequestMapping(value = "/verificarNumeroSEI/{idRequerimento}/{idProcedimento}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resposta<Boolean>> verificarNumeroSEI(
            @PathVariable("idRequerimento") Long idRequerimento,
            @PathVariable("idProcedimento") String idProcedimento) {
        Resposta<Boolean> resposta = null;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(
                    atendimentoService.verificarNumeroProcedimentoSei(idRequerimento, idProcedimento)).build();
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
