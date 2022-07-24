package br.com.company.fks.destinacao.apresentacao;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haillanderson on 12/09/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class MotivoCancelamentoControllerIT extends BaseIntegrationTestCofig{

    private String URL_BASE = "/motivoCancelamento/";

    @SneakyThrows
    @Test
    public void buscarTodosMotivosCancelamento(){

        mockMvc.perform(get(URL_BASE + "buscarTodosMotivosCancelamento")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
