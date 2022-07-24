package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ImovelUsoDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.LinkedHashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by diego on 23/11/16.
 */
@RunWith(PowerMockRunner.class)
public class UsoImovelServiceTest {

    @InjectMocks
    private UsoImovelService usoImovelService;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private ImovelUsoDTO imovelUsoDTO;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private RequestUtils requestUtils;

    @Before
    public void setUp() {
        mockStatic(SecurityContextHolder.class);
        mockStatic(SecurityContext.class);
        when(urlIntegracaoUtils.getUsosImoveis()).thenReturn("http://www.google.com");
        when(requestUtils.doGet(anyString(), eq(Object.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(criarListaUsoImoveis());
    }

    private LinkedHashMap<String, Object> criarListaUsoImoveis() {
        LinkedHashMap<String, Object> usos = new LinkedHashMap<>();
        usos.put("resposta", asList(imovelUsoDTO));
        return usos;
    }

    @Test
    public void getUsoImoveisSucesso() throws IntegracaoException {
        List<ImovelUsoDTO> imovelUsoDTOs = usoImovelService.getUsoImoveis();
        assertNotNull(imovelUsoDTOs);
        assertTrue(!imovelUsoDTOs.isEmpty());
    }

    @Test(expected = IntegracaoException.class)
    public void getUsoImoveisErroIntegracao() throws IntegracaoException {
        when(responseEntity.getBody()).thenReturn(new LinkedHashMap<>());
        List<ImovelUsoDTO> imovelUsoDTOs = usoImovelService.getUsoImoveis();
        assertNotNull(imovelUsoDTOs);
        assertTrue(!imovelUsoDTOs.isEmpty());
    }
}
