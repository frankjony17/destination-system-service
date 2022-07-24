package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.dominio.dto.ImovelUsoDTO;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by guilherme on 22/02/17.
 */


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class UsoRegimeIT extends BaseIntegrationTestCofig {

    private static final String URL_BASE = "/usoImovel/";

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;
    private MockServerUtils mockServerUtils;


    @Test
    @SneakyThrows
    public void findAll() {

        String url = urlIntegracaoUtils.getUsosImoveis();
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE)
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void findAllDisparandExcecao(){
        String url = urlIntegracaoUtils.getUsosImoveis();
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getJsonVazio(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE)
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @SneakyThrows
    private String getJson() {
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        ImovelUsoDTO ImovelUsoDTO = new ImovelUsoDTO();
        List usoRegimesDTO = Arrays.asList(ImovelUsoDTO);
        resposta.put("resposta",usoRegimesDTO);
        return toJson(resposta);
    }

    @SneakyThrows
    private String getJsonVazio(){
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resposta", null);
        return toJson(resposta);
    }

}
