package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import br.com.company.fks.destinacao.service.DespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by raphael on 01/12/16.
 */

/**
 * Classe que conterá a comunicação rest de Despacho
 */
@Controller
@RequestMapping(value = "/despacho")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;

    /**
     *
     * @return uma lista com todos os despachos feitos
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<Despacho>>> buscarDespachosDefault(){
        List<Despacho> despachos = despachoService.buscarPorTipoDespacho(TipoDespachoEnum.DEFAULT);
        Resposta resposta = RespostaBuilder.getBuilder().setResultado(despachos).build();
        return new ResponseEntity<Resposta<List<Despacho>>>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param tipoDespacho recebe o tipo de despacho que sera filtrado
     * @return uma lista com todos os despachos daquele tipo
     */
    @RequestMapping(value = "/{tipoDespacho}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<Despacho>>> buscarPorTipo(@PathVariable("tipoDespacho")TipoDespachoEnum tipoDespacho){
        List<Despacho> despachos = despachoService.buscarPorTipoDespacho(tipoDespacho);
        Resposta resposta = RespostaBuilder.getBuilder().setResultado(despachos).build();
        return new ResponseEntity<Resposta<List<Despacho>>>(resposta, HttpStatus.OK);
    }

}
