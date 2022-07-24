package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.TipoUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import br.com.company.fks.destinacao.service.TipoUtilizacaoService;
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
import java.util.stream.Collectors;

/**
 * Created by haillanderson on 07/04/17.
 */

@RestController
@RequestMapping(value = "/tipoUtilizacao", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TipoUtilizacaoController {

    @Autowired
    private TipoUtilizacaoService tipoUtilizacaoService;

    @Autowired
    private EntityConverter entityConverter;

    @RequestMapping(value = "/buscarTodosTiposUtilizacaoAtivos", method = {RequestMethod.GET})
    public ResponseEntity<Resposta<List<TipoUtilizacaoDTO>>> buscarTodosTiposUtilizacaoAtivos(){
        List<TipoUtilizacao> listaTipoUtilizacao = tipoUtilizacaoService.buscarTodosTiposUtilizacaoAtivos();
        Resposta<List<TipoUtilizacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(converter(listaTipoUtilizacao)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscarTodosTiposUtilizacaoAtivosAfetacao", method = {RequestMethod.GET})
    public ResponseEntity<Resposta<List<TipoUtilizacaoDTO>>> buscarTodosTiposUtilizacaoAtivosAfetacao(){
        List<TipoUtilizacao> listaTipoUtilizacao = tipoUtilizacaoService.buscarTodosTiposUtilizacaoAtivos();
        List<TipoUtilizacao> list = listaTipoUtilizacao.stream().filter(o -> !o.getDescricao().equals("Sem uso definido")).collect(Collectors.toList());
        Resposta<List<TipoUtilizacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(converter(list)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }


    private List<TipoUtilizacaoDTO> converter(List<TipoUtilizacao> tipoUtilizacao){
        Type targetListType = new TypeToken<List<TipoUtilizacaoDTO>>() {}.getType();
        return entityConverter.converterListaStrictLazyLoading(tipoUtilizacao, targetListType);
    }
}
