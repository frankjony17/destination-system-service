package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.MotivoCancelamentoDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoPosseDTO;
import br.com.company.fks.destinacao.dominio.entidades.MotivoCancelamento;
import br.com.company.fks.destinacao.service.EncerramentoDestinacaoService;
import br.com.company.fks.destinacao.service.MotivoCancelamentoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 13/09/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MotivoCancelamentoControllerTest {

    @InjectMocks
    private MotivoCancelamentoController motivoCancelamentoController;

    @Mock
    private MotivoCancelamentoService motivoCancelamentoService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private MotivoCancelamentoDTO motivoCancelamentoDTO;

    @Mock
    private MotivoCancelamento motivoCancelamento;

    @Mock
    private List<MotivoCancelamento> listaMotivoCancelamento;

    @Test
    @SneakyThrows
    public void buscarTodosMotivosCancelamento(){
        when(motivoCancelamentoService.findAll()).thenReturn(listaMotivoCancelamento);
        when(entityConverter.converterListaStrictLazyLoading(listaMotivoCancelamento, Type.class)).thenReturn(listaMotivoCancelamento);
        ResponseEntity<Resposta<List<MotivoCancelamentoDTO>>> resposta;
        resposta = motivoCancelamentoController.buscarTodosMotivosCancelamento();
        Assert.assertNotNull(resposta);
        Assert.assertTrue(resposta.getStatusCode() == HttpStatus.OK);

    }
}
