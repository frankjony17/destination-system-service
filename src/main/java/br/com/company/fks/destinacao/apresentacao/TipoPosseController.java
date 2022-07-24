package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.TipoPosseDTO;
import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import br.com.company.fks.destinacao.service.TipoPosseService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by haillanderson on 07/07/17.
 */

@RestController
@RequestMapping(value = "/tipoPosse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TipoPosseController {

    @Autowired
    private TipoPosseService tipoPosseService;

    @Autowired
    private EntityConverter entityConverter;

    @RequestMapping(value = "/buscarTodos", method = {RequestMethod.GET})
    public ResponseEntity<Resposta<List<TipoPosseDTO>>> buscarTodos(){
        List<TipoPosse> listaTipoPosse = tipoPosseService.findAll();
        Resposta<List<TipoPosseDTO>> resposta = RespostaBuilder.getBuilder().setResultado(converter(listaTipoPosse)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    private List<TipoPosseDTO> converter(List<TipoPosse> listaTipoPosse){
        Type targetListType = new TypeToken<List<TipoPosseDTO>>() {}.getType();
        return entityConverter.converterListaStrictLazyLoading(listaTipoPosse, targetListType);
    }
}
