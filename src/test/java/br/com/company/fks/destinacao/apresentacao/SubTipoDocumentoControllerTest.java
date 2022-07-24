package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.SubTipoDocumentoDTO;
import br.com.company.fks.destinacao.dominio.entidades.SubTipoDocumento;
import br.com.company.fks.destinacao.service.SubTipoDocumentoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Type;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 06/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SubTipoDocumentoControllerTest {

    @InjectMocks
    private SubTipoDocumentoController subTipoDocumentoController;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private SubTipoDocumentoService subTipoDocumentoService;

    @Mock
    private List<SubTipoDocumento> listaSubTipo;

    @Mock
    private List<SubTipoDocumentoDTO> resposta;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    @SneakyThrows
    public void buscarTipoDocumento(){
        when(entityConverter.converterListaStrictLazyLoading(listaSubTipo, Type.class)).thenReturn(resposta);
        when(subTipoDocumentoService.buscarSubTipoDocumento(anyInt())).thenReturn(listaSubTipo);
        listaSubTipo = subTipoDocumentoService.buscarSubTipoDocumento(null);
        subTipoDocumentoController.buscarTipoDocumento(null);


    }
    @Test
    @SneakyThrows
    public void buscarTipoDocumentoCondicional(){
        when(entityConverter.converterListaStrictLazyLoading(listaSubTipo, Type.class)).thenReturn(resposta);
        when(subTipoDocumentoService.buscarSubTipoDocumento(anyInt())).thenReturn(Collections.emptyList());
        listaSubTipo = subTipoDocumentoService.buscarSubTipoDocumento(anyInt());
        subTipoDocumentoController.buscarTipoDocumento(anyInt());


    }

}