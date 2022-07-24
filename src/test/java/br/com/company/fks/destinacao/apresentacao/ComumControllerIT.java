package br.com.company.fks.destinacao.apresentacao;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 04/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ComumControllerIT extends BaseIntegrationTestCofig {

    private String URL_BASE = "/comum/";

    @Test
    @SneakyThrows
    public void getUrlAmbiente() {
        mockMvc.perform(get(URL_BASE + "url-menu")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resposta[0].servicos").isNotEmpty())
                .andExpect(jsonPath("$.resposta[0].cadastroImoveis").isNotEmpty())
                .andExpect(jsonPath("$.resposta[0].integrador").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarIdSistema() {
        mockMvc.perform(get(URL_BASE + "buscar-id-sistema")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resposta[0]").isNotEmpty())
                .andExpect(jsonPath("$.resposta[0]").value("2"));
    }

}
