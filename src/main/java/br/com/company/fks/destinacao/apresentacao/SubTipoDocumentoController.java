package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.SubTipoDocumentoDTO;
import br.com.company.fks.destinacao.dominio.entidades.SubTipoDocumento;
import br.com.company.fks.destinacao.service.SubTipoDocumentoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by sdias on 3/30/2017.
 */

@RestController
@RequestMapping(value = "/documento", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SubTipoDocumentoController {

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private SubTipoDocumentoService subTipoDocumentoService;



    @RequestMapping(value = "/buscarSubTipoDocumento/{subTipoDocumento}", method = {RequestMethod.GET})
    public ResponseEntity<Resposta<List<SubTipoDocumentoDTO>>> buscarTipoDocumento (@PathVariable("subTipoDocumento") Integer id ){
        List<SubTipoDocumento> listaSubTipo = subTipoDocumentoService.buscarSubTipoDocumento(id);
        Resposta<List<SubTipoDocumentoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(converter(listaSubTipo)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    private List<SubTipoDocumentoDTO> converter(List<SubTipoDocumento> subTipoDocumentos){

        if (subTipoDocumentos.isEmpty()) {
            return Collections.emptyList();
        }
        Type targetListType = new TypeToken<List<SubTipoDocumentoDTO>>() {}.getType();
        return entityConverter.converterListaStrictLazyLoading(subTipoDocumentos, targetListType);
    }
}
