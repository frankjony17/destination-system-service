package br.com.company.fks.destinacao.service;

/**
 * Created by diego on 10/01/17.
 */

import br.com.company.fks.destinacao.dominio.dto.RequerimentoConsultaDTO;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by diego on 30/12/16.
 */
@RunWith(PowerMockRunner.class)
public class RequerimentoServiceTest {

    @InjectMocks
    private RequerimentoService requerimentoService;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private LinkedHashMap linkedHashMap;

    @Mock
    private RequerimentoConsultaDTO requerimentoConsultaDTO;

    @Mock
    private RequestUtils requestUtils;

    @Before
    public void setUp() {

        when(requestUtils.doGet(anyString(), eq(Object.class))).thenReturn(responseEntity);
        when(requerimentoConsultaDTO.getIdServico()).thenReturn(1L);


           /* Type targetType = new TypeToken<ImovelDTO>() {}.getType();
            when(entityConverter.converterStrict(Matchers.<LinkedHashMap<String, Object>>any(), eq(targetType))).thenReturn(imovelDTO);*/
    }

    @Test
    public void buscarRequerimento() throws IntegracaoException {
        when(urlIntegracaoUtils.getUrlGetRequerimentoByID(anyLong())).thenReturn("www.google.com.br");
        when(responseEntity.getBody()).thenReturn(criarResposta());
        when(linkedHashMap.get("resposta")).thenReturn(criarResposta());

        Object objeto = requerimentoService.buscarRequerimento(1L);
        assertNotNull(objeto);
    }

    @Test(expected = IntegracaoException.class)
    public void buscarRequerimentoErro() throws IntegracaoException {
        when(urlIntegracaoUtils.getUrlGetRequerimentoByID(anyLong())).thenReturn("www.google.com.br/");
        when(responseEntity.getBody()).thenReturn(new LinkedHashMap<>());
        when(linkedHashMap.get("resposta")).thenReturn(null);

        Object objeto = requerimentoService.buscarRequerimento(1L);
        assertNotNull(objeto);
    }

    private LinkedHashMap criarResposta() {
        LinkedHashMap body = new LinkedHashMap();
        List resposta = new ArrayList();
        LinkedHashMap<String, Object> requerente = new LinkedHashMap<>();
        LinkedHashMap<String, Object> dadosRequerente = new LinkedHashMap<>();
        dadosRequerente.put("nome", "teste");
        dadosRequerente.put("email", "teste@teste.com");
        requerente.put("requerente", dadosRequerente);
        LinkedHashMap<String, Object> atendimento = new LinkedHashMap<>();
        atendimento.put("numero", "0000000");
        requerente.put("atendimento", atendimento);
        requerente.put("nomeResponsavel", "teste");

        resposta.add(requerente);
        body.put("resposta", resposta);
        return body;
    }

    @Test
    public void consultaRequerimentoEAnaliseTecnica() {
        when(urlIntegracaoUtils.getBuscarRequerimentoAnaliseTecnica()).thenReturn("http://www.google.com");
        when(requestUtils.getForEntity(any(URI.class), eq(Object.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(linkedHashMap);
        when(linkedHashMap.get("resposta")).thenReturn(new Object());
        Object object = requerimentoService.consultaRequerimentoEAnaliseTecnica(requerimentoConsultaDTO);
        assertNotNull(object);
    }

    @Test
    public void buscarAnaliseTecnicaRequerimento() throws IntegracaoException {
        when(urlIntegracaoUtils.getUrlGetRequerimentoAnaliseTecnicaByID(anyLong())).thenReturn("www.google.com");
        when(responseEntity.getBody()).thenReturn(linkedHashMap);
        when(linkedHashMap.get("resposta")).thenReturn(new ArrayList<>());
        Object objeto = requerimentoService.buscarAnaliseTecnicaRequerimento(1L);
        assertNotNull(objeto);
    }

    @Test(expected = IntegracaoException.class)
    public void buscarAnaliseTecnicaRequerimentoErro() throws IntegracaoException {
        LinkedHashMap<String, Object> resposta = null;
        when(urlIntegracaoUtils.getUrlGetRequerimentoAnaliseTecnicaByID(anyLong())).thenReturn("url teste");
        when(responseEntity.getBody()).thenReturn(resposta);
        requerimentoService.buscarAnaliseTecnicaRequerimento(1L);
    }

    @Test
    public void alterarStatusRequerimentoRecebendoString() {
        when(urlIntegracaoUtils.getUrlGetAlterarStatus(anyString(), anyLong())).thenReturn("www.google.com");
        requerimentoService.alterarStatusRequerimento(1L, "teste");
    }

    @Test
    public void alterarStatusRequerimentoRecementoEnum() {
        when(urlIntegracaoUtils.getUrlGetAlterarStatus(anyString(), anyLong())).thenReturn("www.google.com");
        requerimentoService.alterarStatusRequerimento(1L, StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA);
    }

    @Test
    public void alterarStatusAnaliseTecnica() {
        when(urlIntegracaoUtils.getUrlAlterarStatusAnaliseTecnica(anyLong(), any(StatusAnaliseTecnicaEnum.class))).thenReturn("www.google.com");
        requerimentoService.alterarStatusAnaliseTecnica(1L, StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA);
    }

    @Test
    public void getDadosAtendimento() throws IntegracaoException {
        when(urlIntegracaoUtils.getUrlGetRequerimentoByID(anyLong())).thenReturn("www.google.com.br");
        when(responseEntity.getBody()).thenReturn(criarResposta());
        when(linkedHashMap.get("atendimento")).thenReturn(new Object());
        Map<String, String> mapa = requerimentoService.getDadosAtendimento(1L);
        assertEquals("0000000", mapa.get("numeroAtendimento"));
        assertEquals("teste@teste.com", mapa.get("email"));
        assertEquals("teste", mapa.get("nomeResponsavel"));
    }

    @Test
    public void buscarPendenciaRequerimento() throws IntegracaoException {
        when(urlIntegracaoUtils.getUrlGetPendenciasRequerimento(anyLong(), anyInt(), anyInt())).thenReturn("http://www.google.com");
        when(requestUtils.doGet(anyString(), any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(criarPendencia());
        Object respota = requerimentoService.buscarPendenciaRequerimento(1L, 1, 5);
        assertNotNull(respota);
    }

    @Test(expected = IntegracaoException.class)
    public void buscarPendenciaRequerimentoGerandoErro() throws IntegracaoException {
        when(urlIntegracaoUtils.getUrlGetPendenciasRequerimento(anyLong(), anyInt(), anyInt())).thenReturn("url teste");
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resposta", null);
        when(responseEntity.getBody()).thenReturn(resposta);
        requerimentoService.buscarPendenciaRequerimento(1L, 1, 5);
    }

    private LinkedHashMap criarPendencia() {
        LinkedHashMap body = new LinkedHashMap();
        List resposta = new ArrayList();
        LinkedHashMap<String, Object> requerimento = new LinkedHashMap<>();
        LinkedHashMap<String, Object> pendencia = new LinkedHashMap<>();

        requerimento.put("idRequerimento", 1L);
        pendencia.put("requerimento", requerimento);

        resposta.add(pendencia);
        body.put("resposta", resposta);
        return body;
    }

    @Test
    public void listarTitulosServico() throws IntegracaoException {
        when(urlIntegracaoUtils.getUrlListaTituloServico()).thenReturn("www.google.com");
        when(requestUtils.doGet(anyString(), any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(criarTitulos());
        Object resposta = requerimentoService.listarTitulosServico();
        assertNotNull(resposta);
    }

    private LinkedHashMap criarTitulos() {
        LinkedHashMap body = new LinkedHashMap();
        List resposta = new ArrayList();
        LinkedHashMap<String, Object> titulos = new LinkedHashMap<>();
        titulos.put("id", "1");
        titulos.put("titulo", "Requerimento de Adesão à Gestão das Praias");
        resposta.add(titulos);
        body.put("resposta", resposta);
        return body;
    }

    @Test(expected = IntegracaoException.class)
    public void listarTitulosServicoGerandoErro() throws IntegracaoException {
        when(urlIntegracaoUtils.getUrlListaTituloServico()).thenReturn("url teste");
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resposta", null);
        when(responseEntity.getBody()).thenReturn(resposta);
        requerimentoService.listarTitulosServico();
    }

}
