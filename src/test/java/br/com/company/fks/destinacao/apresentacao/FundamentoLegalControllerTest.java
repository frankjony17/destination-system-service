package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.FundamentoLegalService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Created by sdias on 02/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FundamentoLegalControllerTest {

    @InjectMocks
    private FundamentoLegalController fundamentoLegalController;

    @Mock
    private FundamentoLegalService fundamentoLegalService;

    @Mock
    private List<FundamentoLegalDTO> listaFundamentoLegalDTO;

    @Mock
    private FundamentoLegalDTO fundamentoLegalDTO;


    @Test
    public void buscarTodosFundamentoLegal() throws Exception {
        ResponseEntity<Resposta<List<FundamentoLegalDTO>>> responseEntity = fundamentoLegalController.buscarTodosFundamentoLegal(TipoDestinacaoEnum.DOACAO);
        Resposta<List<FundamentoLegalDTO>> resposta = responseEntity.getBody();
        assertNotNull(resposta);
    }

    @Test
    public void buscarTodosFundamentoLegalGerandoExcecao() throws Exception {
        when(fundamentoLegalService.buscarFundamentoLegalByTipoDestinacaoEnumFuncionalidade(any())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<List<FundamentoLegalDTO>>> responseEntity = fundamentoLegalController.buscarTodosFundamentoLegal(TipoDestinacaoEnum.DOACAO);
        Resposta<List<FundamentoLegalDTO>> resposta = responseEntity.getBody();
        assertNotNull(resposta.getErros());
        assertTrue(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void verificarUsoDestinacao() throws Exception{
        when(fundamentoLegalService.isUsadoDestinacao(anyLong())).thenReturn(Boolean.TRUE);
        ResponseEntity<Resposta<Boolean>> respostaResponseEntity = fundamentoLegalController.verificarUsoDestinacao(1L);
        Resposta<Boolean> resposta = respostaResponseEntity.getBody();
        assertNotNull(resposta);
        assertTrue(resposta.getResultado().booleanValue());
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

}