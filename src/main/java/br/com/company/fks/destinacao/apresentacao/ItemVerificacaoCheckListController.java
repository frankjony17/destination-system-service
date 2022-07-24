package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoCheckListDTO;
import br.com.company.fks.destinacao.service.ItemVerificacaoCheckListService;
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
 * Classe que conterá a comunicação rest para recuperar os itens que foram marcados para serem revisados na analise tecnica
 */
@RestController
@RequestMapping(value = "itemVerificacaoCheckList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ItemVerificacaoCheckListController {

    @Autowired
    private ItemVerificacaoCheckListService itemVerificacaoCheckListService;

    /**
     *
     * @param idFuncamentoLegal recebe o id do fundamento legal
     * @return retorna a lista de checklist atrelados a aquele fundamento legal
     */
    @RequestMapping(value = "{idFuncamentoLegal}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ItemVerificacaoCheckListDTO>>> listarItemsPorFundamento(@PathVariable("idFuncamentoLegal") Long idFuncamentoLegal) {
        List<ItemVerificacaoCheckListDTO> itens =
                itemVerificacaoCheckListService.findByIdFundamentoLegal(idFuncamentoLegal);
        Resposta<List<ItemVerificacaoCheckListDTO>> resposta = RespostaBuilder.getBuilder()
                                                                              .setResultado(itens)
                                                                              .build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
