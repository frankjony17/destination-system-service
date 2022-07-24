package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ImovelUsoDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.UsoImovelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

/**
 * Classe que conterá a comunicação rest para o Uso de Regime do imovel
 */
@RestController
@RequestMapping(value = "usoImovel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsoRegimeController {

    private static final Logger LOGGER = Logger.getLogger(UsoRegimeController.class);

    @Autowired
    private UsoImovelService usoRegimeService;

    /**
     *
     * @return retorno todos os usos de imoveis no banco
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ImovelUsoDTO>>> findAll() {
        Resposta<List<ImovelUsoDTO>> resposta;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(usoRegimeService.getUsoImoveis()).build();
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
