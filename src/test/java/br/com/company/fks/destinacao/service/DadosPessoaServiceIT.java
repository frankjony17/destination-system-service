package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.builder.RespostaDadosPessoaFisica;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaConsultaDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaFisicaDTO;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DadosPessoaServiceIT extends BaseIntegrationTestCofig{

    private static final String URL_BASE = "/buscar/";

    @Autowired
    private  DadosPessoaService dadosPessoaService;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private RespostaDadosPessoaFisica respostaDadosPessoaFisica;

    private MockServerUtils mockServerUtils;

    private ResponseEntity responseEntity;



    @Ignore
    @Test
    @SneakyThrows
    public void testeBuscarPessoaFisica(){
        dadosPessoaService.buscarPessoaFisica(new DadosPessoaConsultaDTO(), new FundamentoLegalDTO());
        respostaDadosPessoaFisica = new RespostaDadosPessoaFisica();
        respostaDadosPessoaFisica.getResultado();

        mockMvc.perform(get(URL_BASE + "/buscar-pessoa-fisica")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.id").value("1"));




    }


}
