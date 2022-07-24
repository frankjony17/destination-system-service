package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.MotivoCancelamentoDTO;
import br.com.company.fks.destinacao.dominio.entidades.MotivoCancelamento;
import br.com.company.fks.destinacao.service.MotivoCancelamentoService;
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
 * Created by haillanderson on 01/09/17.
 */

@RestController
@RequestMapping(value = "/motivoCancelamento", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MotivoCancelamentoController {
    
    @Autowired
    private MotivoCancelamentoService motivoCancelamentoService;
    
    @Autowired
    private EntityConverter entityConverter;
    
    @RequestMapping(value = "/buscarTodosMotivosCancelamento", method = {RequestMethod.GET})
    public ResponseEntity<Resposta<List<MotivoCancelamentoDTO>>> buscarTodosMotivosCancelamento(){
        List<MotivoCancelamento> listaMotivoCancelamento = motivoCancelamentoService.findAll();
        Resposta<List<MotivoCancelamentoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(converter(listaMotivoCancelamento)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }


    private List<MotivoCancelamentoDTO> converter(List<MotivoCancelamento> motivoCancelamento){
        Type targetListType = new TypeToken<List<MotivoCancelamentoDTO>>() {}.getType();
        return entityConverter.converterListaStrictLazyLoading(motivoCancelamento, targetListType);
    }
    
}
