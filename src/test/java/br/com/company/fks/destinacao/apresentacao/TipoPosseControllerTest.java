package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.TipoPosseDTO;
import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import br.com.company.fks.destinacao.service.TipoPosseService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TipoPosseControllerTest {

    @InjectMocks
    private TipoPosseController tipoPosseController;

    @Mock
    private TipoPosseService tipoPosseService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private List<TipoPosse> listaTipoPosse;

    @Test
    public void buscarTodos (){
        when(tipoPosseService.findAll()).thenReturn(listaTipoPosse);
        when(entityConverter.converterListaStrictLazyLoading(listaTipoPosse, Type.class)).thenReturn(listaTipoPosse);
        ResponseEntity<Resposta<List<TipoPosseDTO>>> resposta;
        resposta = tipoPosseController.buscarTodos();
        assertNotNull(resposta);
        assertTrue(resposta.getStatusCode() == HttpStatus.OK);
    }
}
