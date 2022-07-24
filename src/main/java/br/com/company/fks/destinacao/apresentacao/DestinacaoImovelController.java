package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haillanderson on 21/03/17.
 */

@RestController
@RequestMapping(value = "destinacaoImovel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DestinacaoImovelController {

    @Autowired
    private DestinacaoImovelService destinacaoImovelService;

    @RequestMapping(value = "buscarDadosUtilizacao/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DestinacaoImovelDTO>> buscarDadosUtilizacao(@PathVariable("rip") String rip) {
        DestinacaoImovelDTO destinacaoImovelDTO = destinacaoImovelService.buscarCodigoUtilizacao(rip);
        Resposta <DestinacaoImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(destinacaoImovelDTO).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
