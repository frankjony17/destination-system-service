package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.FundamentoLegalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by diego on 02/12/16.
 */

/**
 * Classe que conterá a comunicação rest de fundamento legal
 */
@RestController
@RequestMapping(value = "/fundamentoLegal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FundamentoLegalController {

    private static final Logger LOGGER = Logger.getLogger(FundamentoLegalDTO.class);

    @Autowired
    private FundamentoLegalService fundamentoLegalService;
    
    /**
     *
     * @param tipoDestinacaoEnum recebe o enum do tipo de destinacao
     * @return
     */
    @RequestMapping(value = "{tipoDestinacao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<FundamentoLegalDTO>>> buscarTodosFundamentoLegal(
                                                @PathVariable("tipoDestinacao")TipoDestinacaoEnum tipoDestinacaoEnum) {

        Resposta<List<FundamentoLegalDTO>> resposta;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            List<FundamentoLegalDTO> fundamentosLegais =
                    fundamentoLegalService.buscarFundamentoLegalByTipoDestinacaoEnumFuncionalidade(tipoDestinacaoEnum);
            resposta = RespostaBuilder.getBuilder()
                    .setResultado(fundamentosLegais)
                    .build();
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            httpStatus = HttpStatus.BAD_REQUEST;
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }

        return new ResponseEntity<>(resposta, httpStatus);
    }

       /**
     * @param id da destinacao
     * @return flag com o status da operação
     */
    @RequestMapping(value = "verificar-uso-destinacao/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<Boolean>> verificarUsoDestinacao(@PathVariable("id") Long id) {
        Boolean usadoDestinacao = fundamentoLegalService.isUsadoDestinacao(id);
        Resposta<Boolean> resposta = RespostaBuilder.getBuilder().setResultado(usadoDestinacao).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
