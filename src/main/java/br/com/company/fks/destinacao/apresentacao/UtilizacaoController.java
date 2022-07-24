package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.service.UtilizacaoService;
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
 * Created by haillanderson on 17/04/17.
 */

@RestController
@RequestMapping(value = "/utilizacao", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UtilizacaoController {

    @Autowired
    private UtilizacaoService utilizacaoService;

    @RequestMapping(value = "/buscarEspecificacoes/{idTipoUtilizacao}", method = {RequestMethod.GET})
    public ResponseEntity<Resposta<List<String>>> buscarEspecificacoes(@PathVariable("idTipoUtilizacao") Integer idTipoUtilizacao){
        List<String> listaEspecificacoes = utilizacaoService.buscarEspecificacoes(idTipoUtilizacao);
        Resposta<List<String>> resposta = RespostaBuilder.getBuilder().setResultado(listaEspecificacoes).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
