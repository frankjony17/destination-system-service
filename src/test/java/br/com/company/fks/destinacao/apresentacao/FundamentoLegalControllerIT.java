package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 05/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class FundamentoLegalControllerIT extends BaseIntegrationTestCofig {

    private String URL_BASE = "/fundamentoLegal/";

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;
    private MockServerUtils mockServerUtils;

    @Test
    @SneakyThrows
    public void buscarTodosFundamentoLegal() {

        String url = urlIntegracaoUtils.getBuscarFundamentoLegalIntegradorByTipoDestinacaoEnum(TipoDestinacaoEnum.DOACAO);

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, jsonFundamentoLegal(), MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(get(URL_BASE + "DOACAO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado[0].id").value(1));
    }

    @Test
    @SneakyThrows
    public void buscarTodosFundamentoLegalGerandoErroIntegracao() {

        String url = urlIntegracaoUtils.getBuscarFundamentoLegalIntegradorByTipoDestinacaoEnum(TipoDestinacaoEnum.DOACAO);

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, jsonFundamentoLegalVazio(), MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(get(URL_BASE + "DOACAO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros").isNotEmpty())
                .andExpect(jsonPath("$.erros[0]").value("Fundamento legal não encontrado"));
    }

    @Test
    @SneakyThrows
    public void verificarUsoDestinacao() {
        mockMvc.perform(get(URL_BASE + "verificar-uso-destinacao/11")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado").isBoolean());
    }

    @Test
    @SneakyThrows
    public void verificarUsoDestinacaoNaoUtilizado() {
        mockMvc.perform(get(URL_BASE + "verificar-uso-destinacao/12")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado").isBoolean());
    }

    private String jsonFundamentoLegal() throws JsonProcessingException {
        Map<String, Object> fundamentoLegal = new HashedMap();
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        fundamentoLegalDTO.setId(1L);
        fundamentoLegalDTO.setDescricao("Teste 1");
        fundamentoLegal.put("resultado", Arrays.asList(fundamentoLegalDTO));
        return toJson(fundamentoLegal);
    }

    private String jsonFundamentoLegalVazio() throws JsonProcessingException {
        Map<String, Object> fundamentoLegal = new HashedMap();
        fundamentoLegal.put("resultado", Collections.emptyList());
        return toJson(fundamentoLegal);
    }

}
