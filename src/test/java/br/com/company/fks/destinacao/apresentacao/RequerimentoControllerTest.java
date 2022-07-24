package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.RequerimentoConsultaDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.RequerimentoService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 02/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RequerimentoControllerTest {

    private ResponseEntity<Resposta> respostaResponseEntity;

    @InjectMocks
    private RequerimentoController requerimentoController;

    @Mock
    private RequerimentoService requerimentoService;

    @Mock
    private Object object;

    @Mock
    private RequerimentoConsultaDTO requerimentoConsultaDTO;

    @Test
    @SneakyThrows
    public void buscarRequerimento(){
        when(requerimentoService.buscarRequerimento(anyLong())).thenReturn(object);

        respostaResponseEntity = requerimentoController.buscarRequerimento(1l);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarRequerimentoDisparandoException(){
        when(requerimentoService.buscarRequerimento(anyLong())).thenThrow(IntegracaoException.class);

        respostaResponseEntity = requerimentoController.buscarRequerimento(1l);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void consultaRequerimentoEAnaliseTecnica(){
        when(requerimentoService.consultaRequerimentoEAnaliseTecnica(requerimentoConsultaDTO)).thenReturn(object);

        respostaResponseEntity = requerimentoController.consultaRequerimentoEAnaliseTecnica(requerimentoConsultaDTO);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);

    }

    @Test
    @SneakyThrows
    public void buscarAnaliseTecnicaRequerimento(){
        when(requerimentoService.buscarAnaliseTecnicaRequerimento(anyLong())).thenReturn(object);

        respostaResponseEntity = requerimentoController.buscarAnaliseTecnicaRequerimento(1l);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarAnaliseTecnicaRequerimentoDisparandoException(){
        when(requerimentoService.buscarAnaliseTecnicaRequerimento(anyLong())).thenThrow(IntegracaoException.class);

        respostaResponseEntity = requerimentoController.buscarAnaliseTecnicaRequerimento(1l);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarPendenciaRequerimento(){
        when(requerimentoService.buscarPendenciaRequerimento(anyLong(), anyInt(), anyInt())).thenReturn(object);

        respostaResponseEntity = requerimentoController.buscarPendenciaRequerimento(1l,1,1);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarPendenciaRequerimentoDisparandoException(){
        when(requerimentoService.buscarPendenciaRequerimento(anyLong(), anyInt(), anyInt())).thenThrow(IntegracaoException.class);

        respostaResponseEntity = requerimentoController.buscarPendenciaRequerimento(1l,1,1);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void alterarStatusRequerimento(){
        respostaResponseEntity = requerimentoController.alterarStatusRequerimento("",1l);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void listarTitulosServico(){
        when(requerimentoService.listarTitulosServico()).thenReturn(object);
        respostaResponseEntity = requerimentoController.listarTitulosServico();
        assertNotNull(requerimentoConsultaDTO);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void listarTitulosServicoDisparandoException(){
        when(requerimentoService.listarTitulosServico()).thenThrow(IntegracaoException.class);
        respostaResponseEntity = requerimentoController.listarTitulosServico();
        assertNotNull(requerimentoConsultaDTO);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void buscarTodosStatusAnaliseTecnica(){
        respostaResponseEntity = requerimentoController.buscarTodosStatusAnaliseTecnica();
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

}