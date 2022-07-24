package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.destinacao.DoacaoService;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Basis Tecnologia on 17/11/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class FundamentoLegalServiceTest {

    @InjectMocks
    private FundamentoLegalService fundamentoLegalService;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private Map<String, Object> map;

    @Mock
    private List<FundamentoLegalDTO> fundamentoLegalDTOs;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private DoacaoService doacaoService;

    @Before
    public void setup(){
        when(urlIntegracaoUtils.getBuscarFundamentoLegalIntegradorByTipoDestinacaoEnum(any(TipoDestinacaoEnum.class))).thenReturn("");
        when(responseEntity.getBody()).thenReturn(map);
        when(requestUtils.doGet(anyString(), any())).thenReturn(responseEntity);
    }

    @Test
    @SneakyThrows
    public void isUsadoDestinacao (){
        when(doacaoService.isUsadoDestinacao(anyLong())).thenReturn(Boolean.TRUE);
        Boolean retorno = fundamentoLegalService.isUsadoDestinacao(1L);
        assertTrue(retorno);
    }

    @Test
    @SneakyThrows
    public void buscarFundamentoLegalByTipoDestinacaoEnumFuncionalidade (){
        when(map.get(anyString())).thenReturn(fundamentoLegalDTOs);
        List<FundamentoLegalDTO> retorno = fundamentoLegalService.buscarFundamentoLegalByTipoDestinacaoEnumFuncionalidade(TipoDestinacaoEnum.CDRU);
        assertNotNull(retorno);
    }

    @Test(expected = IntegracaoException.class)
    @SneakyThrows
    public void buscarFundamentoLegalByTipoDestinacaoEnumFuncionalidadeListaVazia (){
        when(map.get(anyString())).thenReturn(Collections.emptyList());
        when(mensagemNegocio.get(anyString())).thenReturn("");
        List<FundamentoLegalDTO> retorno = fundamentoLegalService.buscarFundamentoLegalByTipoDestinacaoEnumFuncionalidade(TipoDestinacaoEnum.CDRU);
        assertTrue(retorno.isEmpty());
    }

}