package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.service.ItemVerificacaoEspecificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by diego on 27/12/16.
 */

/**
 * Classe que conterá a comunicação rest para recuperar os itens específicos que foram marcados para serem revisados na analise tecnica
 */
@RestController
@RequestMapping(value = "/item-verificacao-especifico", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ItemVerificacaoEspecificoController {

    @Autowired
    private ItemVerificacaoEspecificaService itemVerificacaoEspecificaService;

    /**
     *
     * @param id da analise tecnica
     * @return uma flag para indicar se essa analise tecnica existe
     */
    @RequestMapping(value = "verificar-uso-analise-tecnica/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<Boolean>> verificarUsoAnaliseTecnica(@PathVariable("id") Long id) {
        Boolean usadoDestinacao = itemVerificacaoEspecificaService.isUsadoAnaliseTecnica(id);
        Resposta<Boolean> resposta = RespostaBuilder.getBuilder().setResultado(usadoDestinacao).build();
        return new ResponseEntity<Resposta<Boolean>>(resposta, HttpStatus.OK);
    }

}
