package br.com.company.fks.destinacao.service;

/**
 * Created by diego on 10/01/17.
 */

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by diego on 30/12/16.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class RequerimentoServiceIT extends BaseIntegrationTestCofig {

    @Autowired
    private RequerimentoService requerimentoService;

    private MockServerUtils mockServerUtils;

    @Test(expected = IntegracaoException.class)
    @SneakyThrows
    public void buscarRequerimentoNull() {

        String url = urlIntegracaoUtils.getUrlGetRequerimentoByID(1L);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getRequerimentoJson(), MediaType.APPLICATION_JSON_UTF8);

        requerimentoService.buscarRequerimento(1L);
    }

    @Test
    @SneakyThrows
    public void alterarStatusRequerimento(){

        String url = urlIntegracaoUtils.getUrlGetAlterarStatus(StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA.name(), 1L);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.PUT,"",MediaType.APPLICATION_JSON_UTF8);

        requerimentoService.alterarStatusRequerimento(1L, StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA);
    }

    @Test(expected = IntegracaoException.class)
    @SneakyThrows
    public void buscarPendenciaRequerimento(){

        String url = urlIntegracaoUtils.getUrlGetPendenciasRequerimento(1L, 1, 1);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET,getRequerimentoJson(),MediaType.APPLICATION_JSON_UTF8);

        requerimentoService.buscarPendenciaRequerimento(1L, 1,1);
    }

    @SneakyThrows
    private String getRequerimentoJson() {
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        LinkedHashMap<String, Object> objeto = new LinkedHashMap<>();
        objeto.put("id", "1");
        resposta.put("resposta", null);
        return toJson(resposta);
    }
}