package br.com.company.fks.destinacao.apresentacao;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haillanderson on 17/04/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class SubTipoUtilizacaoControllerIT extends BaseIntegrationTestCofig{

    private static final String URL_BASE = "/subTipoUtilizacao/";

    @Test
    @SneakyThrows
    public void buscarTodosSubTiposUtilizacaoAtivos(){
        mockMvc.perform(get(URL_BASE + "buscarTodosSubTiposUtilizacaoAtivos")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

}