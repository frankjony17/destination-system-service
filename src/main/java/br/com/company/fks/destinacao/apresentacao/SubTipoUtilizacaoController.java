package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.SubTipoDocumentoDTO;
import br.com.company.fks.destinacao.dominio.dto.SubTipoUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.SubTipoUtilizacao;
import br.com.company.fks.destinacao.service.SubTipoUtilizacaoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haillanderson on 07/04/17.
 */

@RestController
@RequestMapping(value = "/subTipoUtilizacao", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SubTipoUtilizacaoController {

    @Autowired
    private SubTipoUtilizacaoService subTipoUtilizacaoService;

    @Autowired
    private EntityConverter entityConverter;

    @RequestMapping(value = "/buscarTodosSubTiposUtilizacaoAtivos", method = {RequestMethod.GET})
    public ResponseEntity<Resposta<List<SubTipoUtilizacaoDTO>>> buscarTodosSubTiposUtilizacaoAtivos(){
        List<SubTipoUtilizacao> listaSubTipoUtilizacao = subTipoUtilizacaoService.buscarTodosSubTiposUtilizacaoAtivos();
        Resposta<List<SubTipoUtilizacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(converter(listaSubTipoUtilizacao)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscarTodosSubTiposUtilizacaoAtivosIntegrador/{tipoUtilizacaoDTO}", method = {RequestMethod.GET})
    public ResponseEntity<Resposta<List<SubTipoUtilizacaoDTO>>> buscarTodosSubTiposUtilizacaoAtivosIntegrador(@PathVariable("tipoUtilizacaoDTO") Integer tipoUtilizacaoDTO){
        List<SubTipoUtilizacao> listaSubTipoUtilizacao = subTipoUtilizacaoService.buscarFiltrando(tipoUtilizacaoDTO);
        Resposta<List<SubTipoUtilizacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(converter(listaSubTipoUtilizacao)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscarTodosSubTiposUtilizacaoAtivosAfetacao", method = {RequestMethod.POST})
    public ResponseEntity<Resposta<List<SubTipoUtilizacaoDTO>>> buscarTodosSubTiposUtilizacaoAtivosAfetacao(@RequestBody List<TipoUtilizacaoDTO> tipoUtilizacaoDTO){
        List<SubTipoUtilizacao> listaSubTipoUtilizacao = new ArrayList<>();
        tipoUtilizacaoDTO.forEach(tipoUtilizacaoDTO1 -> {
             List<SubTipoUtilizacao> list =  subTipoUtilizacaoService.buscarFiltrando(tipoUtilizacaoDTO1.getId().intValue());
             listaSubTipoUtilizacao.addAll(list);
        });

        List<SubTipoUtilizacao> list = listaSubTipoUtilizacao.stream().filter(o -> !o.getDescricao().equals("Outro (especificar)")).collect(Collectors.toList());
        Resposta<List<SubTipoUtilizacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(converter(list)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    private List<SubTipoDocumentoDTO> converter(List<SubTipoUtilizacao> subTipoUtilizacao){
        Type targetListType = new TypeToken<List<SubTipoUtilizacaoDTO>>() {}.getType();
        return entityConverter.converterListaStrictLazyLoading(subTipoUtilizacao, targetListType);
    }
}
