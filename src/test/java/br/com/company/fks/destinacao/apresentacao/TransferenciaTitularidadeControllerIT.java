package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.dominio.entidades.*;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 23/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class TransferenciaTitularidadeControllerIT extends BaseIntegrationTestCofig {

    private String URL_BASE = "/transferencia/";

    @Test
    @SneakyThrows
    public void findAllTipoTransferencia() {
        mockMvc.perform(get(URL_BASE + "/tipo-transferencia")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void findAllBaseLegal() {
        mockMvc.perform(get(URL_BASE + "/base-legal")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void findAllTipoDestinatario() {
        mockMvc.perform(get(URL_BASE + "/tipo-destinatario")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void salvarTransferencia() {
        TransferenciaTitularidade transferenciaTitularidade = new TransferenciaTitularidade();
        transferenciaTitularidade.setAtosComplementares(asList(criarArquivo()));
        transferenciaTitularidade.setDestinatario(criarDestinatario());
        transferenciaTitularidade.setTipoTransferencia(criarTipoTransferencia());
        String requestJson = toJson(transferenciaTitularidade);
        mockMvc.perform(post(URL_BASE)
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(1));
    }

    private Arquivo criarArquivo() {
        Arquivo arquivo = new Arquivo();
        arquivo.setId(1L);
        return arquivo;
    }

    private BaseLegal criarBaseLegal() {
        BaseLegal baseLegal = new BaseLegal();
        baseLegal.setId(1);
        return baseLegal;
    }

    private Destinatario criarDestinatario() {
        Destinatario destinatario = new Destinatario();
        destinatario.setCnpj("00000000191");
        destinatario.setNomeEmpresarial("Teste");
        return destinatario;
    }

    private TipoTransferencia criarTipoTransferencia() {
        TipoTransferencia tipoTransferencia = new TipoTransferencia();
        tipoTransferencia.setId(1);
        return tipoTransferencia;
    }

}
