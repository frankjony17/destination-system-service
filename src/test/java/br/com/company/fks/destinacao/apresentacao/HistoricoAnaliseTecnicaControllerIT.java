package br.com.company.fks.destinacao.apresentacao;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 05/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class HistoricoAnaliseTecnicaControllerIT extends BaseIntegrationTestCofig {

    private String URL_BASE = "/historicoAnaliseTecnica/";

    @Test
    @SneakyThrows
    public void getHistorico() {
        mockMvc.perform(get(URL_BASE + "125/0/5")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.content[0].id").value(285));
    }

}
