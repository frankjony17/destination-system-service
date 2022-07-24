package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.dominio.dto.LocalizacaoEctDTO;
import br.com.company.fks.destinacao.dominio.dto.MunicipioDTO;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by diego on 21/02/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class LocalidadeControllerIT extends BaseIntegrationTestCofig {

    private static final String URL_BASE = "/localidade/";

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;
    private MockServerUtils mockServerUtils;

    @Test
    @SneakyThrows
    public void listarEnderecoPorCep() {
        String url = urlIntegracaoUtils.getUrlBuscarEnderecoByCep("38703750");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getLocalidadeJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/buscar-endereco-cep/38703750")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.cep").value("38703750"))
                .andExpect(jsonPath("$.resultado.municipio").value("Brasilia"));
    }

    @Test
    @SneakyThrows
    public void listarEnderecoPorCepGerandoErro() {
        String url = urlIntegracaoUtils.getUrlBuscarEnderecoByCep("38703750");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/buscar-endereco-cep/38703750")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarMunicipioPorUf() {
        String url = urlIntegracaoUtils.getUrlGetBuscarMunicipioPorUf("DF");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getMunicipioJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/buscar-municipo-uf/DF")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarMunicipioPorUfGerandoErro() {
        String url = urlIntegracaoUtils.getUrlGetBuscarMunicipioPorUf("DF");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/buscar-municipo-uf/DF")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarPaises(){
        mockMvc.perform(get(URL_BASE + "/buscar-paises")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @SneakyThrows
    private String getLocalidadeJson() {
        LocalizacaoEctDTO localizacaoEctDTO = new LocalizacaoEctDTO();
        localizacaoEctDTO.setBairro("Teste");
        localizacaoEctDTO.setCep("38703750");
        localizacaoEctDTO.setLogradouro("teste");
        localizacaoEctDTO.setMunicipio("Brasilia");
        localizacaoEctDTO.setTipoLogradouro("Avenida");
        localizacaoEctDTO.setUf("DF");
        return toJson(localizacaoEctDTO);
    }

    @SneakyThrows
    private String getMunicipioJson() {
        MunicipioDTO municipioDTO = new MunicipioDTO();
        municipioDTO.setNome("Brasilia");
        return toJson(Arrays.asList(municipioDTO));
    }
}
