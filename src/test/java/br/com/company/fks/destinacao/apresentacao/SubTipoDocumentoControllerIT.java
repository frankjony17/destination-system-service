package br.com.company.fks.destinacao.apresentacao;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haillanderson on 06/04/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class SubTipoDocumentoControllerIT extends BaseIntegrationTestCofig{

    private static final String URL_BASE = "/documento/";

    @Test
    @SneakyThrows
    public void buscarTipoDocumento(){
        mockMvc.perform(get(URL_BASE + "buscarSubTipoDocumento/" +1)
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void buscarTipoDocumentoVazio(){
        mockMvc.perform(get(URL_BASE + "buscarSubTipoDocumento/" +2)
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }
}