package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.dominio.dto.RequerimentoDestinacaoDTO;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 10/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class AtendimentoControllerIT extends BaseIntegrationTestCofig {

    private static final String URL_BASE = "/atendimento/";

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;
    private MockServerUtils mockServerUtils;

    @Test
    @SneakyThrows
    public void buscarRequerimento() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoByNumeroAtendimento("1");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, requerimentoToJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/buscar-requerimento")
                .param("numeroAtendimento", "1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.idRequerimento").value(1))
                .andExpect(jsonPath("$.resultado.numeroAtendimento").value("DF00001/2017"));
    }

    @Test
    @SneakyThrows
    public void findRequerimentoByNumeroAtendimentoLancaErros() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoByNumeroAtendimento("1");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/buscar-requerimento")
                .param("numeroAtendimento", "1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty())
                .andExpect(jsonPath("$.erros[0]").value("Atendimento não encontrado"));
    }

    @Test
    @SneakyThrows
    public void findRequerimentoByNumeroProcesso() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoByNumeroProcesso("123");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, requerimentoToJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/buscar-requerimento-numero-processo")
                .param("numeroProcesso", "123")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.idRequerimento").value(1))
                .andExpect(jsonPath("$.resultado.numeroProcedimento").value("123"));
    }

    @Test
    @SneakyThrows
    public void findRequerimentoByNumeroProcessoLancaException() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoByNumeroProcesso("123");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/buscar-requerimento-numero-processo")
                .param("numeroProcesso", "123")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty())
                .andExpect(jsonPath("$.erros[0]").value("Processo não encontrado"));
    }

    @Test
    @SneakyThrows
    public void verificarNumeroSEI() {
        String url = urlIntegracaoUtils.getVerificarNumeroProcesso(1L, "1");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(Boolean.TRUE), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/verificarNumeroSEI/1/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado").value(Boolean.TRUE));
    }

    @Test
    @SneakyThrows
    public void verificarNumeroSEILancaErros() {
        String url = urlIntegracaoUtils.getVerificarNumeroProcesso(1L, "1");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(Boolean.FALSE), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/verificarNumeroSEI/1/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty())
                .andExpect(jsonPath("$.erros[0]").value("Processo nÃ£o encontrado"));
    }

    @SneakyThrows
    private String requerimentoToJson() {
        RequerimentoDestinacaoDTO requerimentoDestinacaoDTO = new RequerimentoDestinacaoDTO();
        requerimentoDestinacaoDTO.setNumeroProcedimento("123");
        requerimentoDestinacaoDTO.setNumeroAtendimento("DF00001/2017");
        requerimentoDestinacaoDTO.setIdRequerimento(1L);
        return toJson(requerimentoDestinacaoDTO);
    }
}
