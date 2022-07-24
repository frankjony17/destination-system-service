package br.com.company.fks.destinacao.apresentacao;

import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by samuel on 14/07/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class tipoPosseControllerIT extends BaseIntegrationTestCofig{

    private static final String URL_BASE = "/tipoPosse/";

    @Ignore
    @Test
    @SneakyThrows
    public void buscarTodos(){
        mockMvc.perform(get(URL_BASE + "buscarTodos")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

}
