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
public class DespachoControllerIT extends BaseIntegrationTestCofig {

    private String URL_BASE = "/despacho/";

    @Test
    @SneakyThrows
    public void buscarDespachosDefault() {
        mockMvc.perform(get(URL_BASE)
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado[0].id").value(1))
                .andExpect(jsonPath("$.resultado[0].descricao").value("Atende aos requisitos"));
    }

    @Test
    @SneakyThrows
    public void buscarPorTipo() {
        mockMvc.perform(get(URL_BASE + "/DEFAULT")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado[0].id").value(1))
                .andExpect(jsonPath("$.resultado[0].descricao").value("Atende aos requisitos"));
    }

}
