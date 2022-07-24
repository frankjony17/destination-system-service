package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.LocalizacaoEctDTO;
import br.com.company.fks.destinacao.dominio.dto.MunicipioDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 23/01/17.
 */
@RunWith(PowerMockRunner.class)
public class LocalidadeServiceTest {

    @InjectMocks
    private LocalidadeService localidadeService;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private LocalizacaoEctDTO localizacaoEctDTO;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private MunicipioDTO municipioDTO;

    @Before
    public void setUp() {
        when(urlIntegracaoUtils.getUrlBuscarEnderecoByCep(anyString())).thenReturn("www.google.com");
        when(urlIntegracaoUtils.getUrlGetBuscarMunicipioPorUf(anyString())).thenReturn("www.google.com");
        when(requestUtils.doGet(anyString(), eq(LocalizacaoEctDTO.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(localizacaoEctDTO);
        when(localizacaoEctDTO.getBairro()).thenReturn("Asa Sul");
        when(localizacaoEctDTO.getCep()).thenReturn("0");
        when(localizacaoEctDTO.getLogradouro()).thenReturn("w3");
        when(localizacaoEctDTO.getUf()).thenReturn("DF");
    }

    @Test
    public void findEnderecoByCep() throws IntegracaoException {
        LocalizacaoEctDTO localizacaoEctDTORecuperado = localidadeService.findEnderecoByCep("0");
        assertNotNull(localizacaoEctDTORecuperado);
    }

    @Test(expected = IntegracaoException.class)
    public void findEnderecoByCepErroIntegracao() throws IntegracaoException {
        when(responseEntity.getBody()).thenReturn(null);
        LocalizacaoEctDTO localizacaoEctDTORecuperado = localidadeService.findEnderecoByCep("0");
        assertNotNull(localizacaoEctDTORecuperado);
    }

    @Test
    @SneakyThrows
    public void findMunicipioByUF() {
        when(requestUtils.doGet(anyString(), eq(List.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(Arrays.asList(municipioDTO));
        List<MunicipioDTO> municipioDTOs = localidadeService.findMunicipioByUF("UF");
        assertNotNull(municipioDTOs);
    }

    @Test(expected = IntegracaoException.class)
    @SneakyThrows
    public void findMunicipioByUFErroIntegracao() {
        when(requestUtils.doGet(anyString(), eq(List.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(null);
        List<MunicipioDTO> municipioDTOs = localidadeService.findMunicipioByUF("UF");
    }

}