package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.dto.PessoaDTO;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Created by diego on 25/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ResponsavelControllerIT extends BaseIntegrationTestCofig {

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    private MockServerUtils mockServerUtils;

    private String URL_BASE = "/responsavel/";

    @Test
    @SneakyThrows
    @Ignore
    public void getDadosPessoaFisica() {
        mockMvc.perform(get(URL_BASE + "buscar/00000000191")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void getDadosPessoaFisicaCPFInvalido() {
        mockMvc.perform(get(URL_BASE + "buscar/00000000192")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void getDadosPessoaJuridica() {
        mockMvc.perform(get(URL_BASE + "buscar/74939822000179")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void getDadosPessoaJuridicaBlackList() {
        mockMvc.perform(get(URL_BASE + "buscar/00000000000000")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarResponsavelImovel(){
        String url = urlIntegracaoUtils.getUrlBuscarResponsavelImovel(1L);

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "buscarResponsavelImovel/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    public void buscarResponsavelImovelGerandoIntegrationException(){
        String url = urlIntegracaoUtils.getUrlBuscarResponsavelImovel(1L);

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServeServerError(url, HttpMethod.GET);

        mockMvc.perform(get(URL_BASE + "buscarResponsavelImovel/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @SneakyThrows
    private String getImovelJson() {
        LinkedHashMap<String, Object> responsavel = new LinkedHashMap<>();
        responsavel.put("id", 1L);
        responsavel.put("uniaoParcialmente", true);
        responsavel.put("tipoProprietarioParcial", criarTipoProprietarioReal());
        responsavel.put("tipoProprietarioReal", criarTipoProprietarioReal());
        responsavel.put("orgaoEntidade", "teste");
        responsavel.put("codigoOrgaoEntidade", 1L);
        responsavel.put("unidadeUsuario", "unidade de teste");
        responsavel.put("codigoUg", "teste");
        responsavel.put("pessoas", asList(criarPessoaDTO()));
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resultado", responsavel);
        return toJson(resposta);
    }

    private DominioDTO criarTipoProprietarioReal(){
        DominioDTO tipoProprietarioReal = new DominioDTO();
        tipoProprietarioReal.setId(1);
        tipoProprietarioReal.setDescricao("Proprietário Real Teste");
        return tipoProprietarioReal;
    }

    private PessoaDTO criarPessoaDTO(){
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId("00000000191");
        pessoaDTO.setNome("Nome Pessoa Teste");
        pessoaDTO.setNumeroDocumento("teste");
        return pessoaDTO;
    }

}
