package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.CancelamentoEncerramentoDTO;
import br.com.company.fks.destinacao.dominio.entidades.CancelamentoEncerramento;
import br.com.company.fks.destinacao.service.CancelamentoEncerramentoUtilizacaoService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class CancelamentoEncerramentoUtilizacaoControllerTest {

    @Mock
    private CancelamentoEncerramentoUtilizacaoService service;

    @InjectMocks
    private CancelamentoEncerramentoUtilizacaoController controller;

    @Mock
    private CancelamentoEncerramento cancelamentoEncerramento;

    @Before
    public void setUp() throws Exception {
        when(cancelamentoEncerramento.getDataCancelamentoEncerramento()).thenReturn(new Date());
    }

    @Test
    public void buscarMotivosCancelarEncerrar() throws Exception {
        ResponseEntity<Resposta> entity = controller.buscarMotivosCancelarEncerrar();
        statusOkBodyNaoNulo(entity);
        List resultado = (List) entity.getBody().getResultado();
        assertFalse(resultado.isEmpty());
    }

    @Test
    public void buscarDespachosCancelarEncerrar() throws Exception {
        ResponseEntity<Resposta> entity = controller.buscarDespachosCancelarEncerrar();
        statusOkBodyNaoNulo(entity);
        List resultado = (List) entity.getBody().getResultado();
        assertFalse(resultado.isEmpty());
    }

    @Test
    public void submeterSuperIntendente() throws Exception {
        ResponseEntity entity = controller.submeterSuperIntendente(1L, new CancelamentoEncerramentoDTO());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void buscarPorIdDestinacao() throws Exception {
        ResponseEntity<Resposta> responseEntity = controller.buscarPorIdDestinacao(1L);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void confirmarCancelamento() throws Exception {
        ResponseEntity entity =controller.confirmarCancelamento(1L, new CancelamentoEncerramentoDTO());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    private void statusOkBodyNaoNulo(ResponseEntity<Resposta> entity) {
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue(entity.getBody() != null);
    }

    @Ignore
    @Test
    public void getDataCancelamentoEncerramento() throws  Exception {
        Date data = new Date();
        cancelamentoEncerramento.setDataCancelamentoEncerramento(data);
        assertEquals(data, cancelamentoEncerramento.getDataCancelamentoEncerramento());
    }



}