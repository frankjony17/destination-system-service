package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 10/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class RequerimentoControllerIT extends BaseIntegrationTestCofig {

    private static final String URL_BASE = "/requerimento/";

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;
    private MockServerUtils mockServerUtils;


    @Test
    @SneakyThrows
    public void buscarRequerimento() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoByID(1L);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getRequerimentoArrayJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.id").value("1"));
    }

    @Test
    @SneakyThrows
    public void buscarRequerimentoLancaErros() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoByID(1L);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getRequerimentoVazioJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void consultaRequerimentoEAnaliseTecnica() {

        String url = urlIntegracaoUtils.getBuscarRequerimentoAnaliseTecnica() + "?idServico=1&nuAtendimento&nomeRequerente&cpfCnpj&uf&situacao&nomeResponsavel&dataSolicitacaoInicio&dataSolicitacaoFinal&dataEnvioAnaliseInicio&dataEnvioAnaliseFinal&offset=0&limit=1";
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getRequerimentoJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "consultar/analise-tecnica")
                .param("idServico", "1")
                .param("page", "1")
                .param("limit", "1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.id").value("1"));
    }

    @Test
    @SneakyThrows
    public void buscarAnaliseTecnicaRequerimento() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoAnaliseTecnicaByID(1L);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getRequerimentoArrayJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "requerimento-analise-tecnica/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado[0].id").value("1"));
    }

    @Test
    @SneakyThrows
    public void buscarAnaliseTecnicaRequerimentoLancaErros() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoAnaliseTecnicaByID(1L);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "requerimento-analise-tecnica/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarPendenciaRequerimento() {

        String url = urlIntegracaoUtils.getUrlGetPendenciasRequerimento(1L, 1, 5);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getRequerimentoArrayJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "requerimento-pendencia/1")
                .param("idRequerimento", "1")
                .param("offset", "1")
                .param("limit", "5")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());

    }

    @Test
    @SneakyThrows
    public void buscarPendenciaRequerimentoLancaErros() {

        String url = urlIntegracaoUtils.getUrlGetPendenciasRequerimento(1L, 1, 5);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getRequerimentoVazioJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "requerimento-pendencia/1")
                .param("idRequerimento", "1")
                .param("offset", "1")
                .param("limit", "5")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void listarTitulosServico() {

        String url = urlIntegracaoUtils.getUrlListaTituloServico();
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getListaServicosJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "listar-titulo-servicos")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado[0].id").value("45"))
                .andExpect(jsonPath("$.resultado[0].titulo").value("Requerimento de Adesão à Gestão das Praias"))
                .andExpect(jsonPath("$.resultado[0].subtitulo").value("Adesão de Praias"));
    }

    @Test
    @SneakyThrows
    public void listarTitulosServicoLancaErros() {

        String url = urlIntegracaoUtils.getUrlListaTituloServico();
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resposta", null);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "listar-titulo-servicos")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosStatusAnaliseTecnica() {
        mockMvc.perform(get(URL_BASE + "buscar-tipo-status-analise-tecnica")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void alterarStatusRequerimento() {

        String url = urlIntegracaoUtils.getUrlGetAlterarStatus("DEFERIDO", 286L);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.PUT, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(put(URL_BASE + "alterar-status/DEFERIDO/286")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    private String getRequerimentoArrayJson() {
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        LinkedHashMap<String, Object> objeto = new LinkedHashMap<>();
        objeto.put("id", "1");
        List lista = new ArrayList();
        lista.add(objeto);
        resposta.put("resposta", lista);
        return toJson(resposta);
    }

    @SneakyThrows
    private String getRequerimentoJson() {
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        LinkedHashMap<String, Object> objeto = new LinkedHashMap<>();
        objeto.put("id", "1");
        resposta.put("resposta", objeto);
        return toJson(resposta);
    }

    @SneakyThrows
    private String getRequerimentoVazioJson() {
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        LinkedHashMap<String, Object> objeto = new LinkedHashMap<>();
        objeto.put("id", "1");
        List lista = new ArrayList();
        resposta.put("resposta", lista);
        return toJson(resposta);
    }

    @SneakyThrows
    private String getListaServicosJson() {
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        LinkedHashMap<String, Object> objeto = new LinkedHashMap<>();
        objeto.put("id", "45");
        objeto.put("titulo", "Requerimento de Adesão à Gestão das Praias");
        objeto.put("subtitulo", "Adesão de Praias");
        List lista = new ArrayList();
        lista.add(objeto);
        resposta.put("resposta", lista);
        return toJson(resposta);
    }

}
