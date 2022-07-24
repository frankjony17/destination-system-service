package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.RequerimentoDestinacaoDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 23/11/16.
 */
@RunWith(PowerMockRunner.class)
public class AtendimentoServiceTest {

    private static final String NUMERO_ATENDIMENTO = "DF00002/2016";
    private static final String NUMERO_SEI = "123";

    @InjectMocks
    private AtendimentoService atendimentoService;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private RequerimentoDestinacaoDTO requerimentoDestinacaoDTO;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private RequestUtils requestUtils;

    @Before
    public void setUp() {
        when(urlIntegracaoUtils.getUrlImovelCadastroImoveis(anyString())).thenReturn("www.google.com.br");
        when(responseEntity.getBody()).thenReturn(requerimentoDestinacaoDTO);
        when(requestUtils.doGet(anyString(), eq(RequerimentoDestinacaoDTO.class))).thenReturn(responseEntity);
        when(urlIntegracaoUtils.getVerificarNumeroProcesso(anyLong(), anyString())).thenReturn("www.google.com.br");
    }

    @Test
    public void findRequerimentoByNumeroAtendimentoSucesso() throws IntegracaoException {
        RequerimentoDestinacaoDTO requerimentoDestinacaoDTO = atendimentoService.findRequerimentoByNumeroAtendimento(NUMERO_ATENDIMENTO);
        assertNotNull(requerimentoDestinacaoDTO);
    }

    @Test(expected = IntegracaoException.class)
    public void findRequerimentoByNumeroAtendimentoErro() throws IntegracaoException {
        when(responseEntity.getBody()).thenReturn(null);
        atendimentoService.findRequerimentoByNumeroAtendimento(NUMERO_ATENDIMENTO);
    }

    @Test
    public void findRequerimentoByNumeroProcessoSucesso() throws IntegracaoException {
        RequerimentoDestinacaoDTO requerimentoDestinacaoDTO = atendimentoService.findRequerimentoByNumeroProcesso(NUMERO_SEI);
        assertNotNull(requerimentoDestinacaoDTO);
    }

    @Test(expected = IntegracaoException.class)
    public void findRequerimentoByNumeroProcessoErro() throws IntegracaoException {
        when(responseEntity.getBody()).thenReturn(null);
        atendimentoService.findRequerimentoByNumeroProcesso(NUMERO_SEI);
    }

    @Test
    public void verificarNumeroProcedimentoSeiSucesso() throws IntegracaoException {
        when(responseEntity.getBody()).thenReturn(Boolean.TRUE);
        when(requestUtils.doGet(anyString(), eq(Boolean.class))).thenReturn(responseEntity);
        Boolean processoOk = atendimentoService.verificarNumeroProcedimentoSei(1L, NUMERO_ATENDIMENTO);
        assertTrue(processoOk);
    }

    @Test(expected = IntegracaoException.class)
    public void verificarNumeroProcedimentoSeiErro() throws IntegracaoException {
        when(responseEntity.getBody()).thenReturn(Boolean.FALSE);
        when(requestUtils.doGet(anyString(), eq(Boolean.class))).thenReturn(responseEntity);
        Boolean processoOk = atendimentoService.verificarNumeroProcedimentoSei(1L, NUMERO_ATENDIMENTO);
        assertTrue(processoOk);
    }
}
