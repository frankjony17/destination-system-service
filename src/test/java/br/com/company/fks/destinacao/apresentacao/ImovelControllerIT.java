package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.NestedServletException;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 06/01/17.
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ImovelControllerIT extends BaseIntegrationTestCofig {

    private static final String URL_BASE = "/imovel/";

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;
    private MockServerUtils mockServerUtils;

    @Ignore
    @Test
    @SneakyThrows
    public void consultarImovelRip() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000010");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000010/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.rip").value("00000010"));
    }

    @Test
    @SneakyThrows
    @Ignore
    public void consultarImovelRipSemJurisdicao() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000002");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000002/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty())
                .andExpect(jsonPath("$.erros").value("O RIP informado não está sob jurisdição da sua unidade de lotação."));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarImovelRipSemParcelaAtiva() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000004");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000004/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarImovelRipCessaoGratuitaSemBenfeitoria() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000007");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000007/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isEmpty())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.id").value(3))
                .andExpect(jsonPath("$.resultado.rip").value("00000007"));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarImovelRipDestinacaoAtiva() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000009");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000009/CUEM")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty())
                .andExpect(jsonPath("$.erros").value("RIP INVÁLIDO"));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarImovelRipCessaoGratuitaValida() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000010");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000010/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.rip").value("00000010"));
    }
    @Test
    @SneakyThrows
    @Ignore
    public void consultarImovelRipCessaoGratuitaInvalida() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000003");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000003/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty())
                .andExpect(jsonPath("$.erros").value("RIP Inválido"));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarImovelRipGerandoErro() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000008");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000008/POSSE_INFORMAL")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void consultarImovelRipDoacaoIncorporado() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000005");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000005/DOACAO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty())
                .andExpect(jsonPath("$.erros").value("RIP Inválido"));
    }

    @Test
    @SneakyThrows
    @Ignore
    public void consultarImovelRipLocacaoCavidadeOuEspelhoDagua() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000005");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRip/00000005/LOCACAO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty())
                .andExpect(jsonPath("$.erros").value("RIP Inválido"));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarNumeroDestinacao() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000009");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000009/POSSE_INFORMAL")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.rip").value("00000009"))
                .andExpect(jsonPath("$.resultado.destinacoes").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarNumeroDestinacaoSemDestinacao() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000005");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000005/POSSE_INFORMAL")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.rip").value("00000005"))
                .andExpect(jsonPath("$.resultado.destinacoes").isEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarDestinacaoPorUF(){
        mockMvc.perform(get(URL_BASE + "/consultarDestinacao")
                .param("uf","MG")
                .param("offset","1")
                .param("limit","1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarDestinacaoPorMunicipioUF(){
        mockMvc.perform(get(URL_BASE + "/consultarDestinacao")
                .param("municipio","Patos de Minas")
                .param("uf","MG")
                .param("offset","1")
                .param("limit","1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void buscarDadosRipUtilizacaoParcelaAtiva(){
        mockMvc.perform(get(URL_BASE + "/buscarDadosRipUtilizacao/00000009")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void buscarDadosRipUtilizacao(){
        mockMvc.perform(get(URL_BASE + "/buscarDadosRipUtilizacao/00000010")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void consultarNumeroDestinacaoGerandoErros() {

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000008");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000008/POSSE_INFORMAL")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty())
                .andExpect(jsonPath("$.erros[0]").value("RIP invalido"));
    }

    @Test
    @SneakyThrows
    public void buscarValorAvaliacao() {

        mockMvc.perform(get(URL_BASE + "/buscarValorAvaliacao/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void verificarImovelDestinado() {
        mockMvc.perform(get(URL_BASE + "/existe-destinacao-imovel/00000123")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado").value(false));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarPorRipCUEMValido(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000010");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipCUEM/00000010/0000/P0/CUEM/2")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void consultarPorRipCUEMInvalido(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000007");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(get(URL_BASE + "/consultarPorRipCUEM/00000007/0000/P0/CUEM/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void buscarRipCodigoUtilizacaoParcelaTipoDestinacaoSemDestinacao(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000010");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000010/0000/P3/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.resultado").isEmpty())
                .andExpect(jsonPath("$.erros").value("Código de Utilização Inválido"));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void buscarRipCodigoUtilizacaoParcelaTipoDestinacao(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000010");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000010/0000/P0/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void buscarRipCodigoUtilizacaoParcelaTipoDestinacaoErroIntegracao(){

        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000010");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServeServerError(url, HttpMethod.GET);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000010/0000/P0/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void validarRipUsoProprioProprietarioUniao(){
        usuarioLogadoDTO.setOrganizacao("UNIAO");
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000007");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000007/0000/P0/USO_PROPRIO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void validarRipUsoProprioProprietarioUniaoDisparandoException(){
        usuarioLogadoDTO.setOrganizacao("teste");
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000007");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000007/0000/P0/USO_PROPRIO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void validarRipUsoProprioProprietarioTerceiros(){
        usuarioLogadoDTO.setCpf("00000000191");
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000010");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000010/0000/P0/USO_PROPRIO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void consultarDadosPosseInformal(){
        usuarioLogadoDTO.setCpf("00000000191");
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000011");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarDadosPosseInformal/00000011/0000/P0")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Ignore
    @Test(expected = NestedServletException.class)
    @SneakyThrows
    public void consultarDadosPosseInformalImovelDTONull(){
        usuarioLogadoDTO.setCpf("00000000191");
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000001");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarDadosPosseInformal/00000001/0000/P0")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.resultado").isEmpty())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void validarRipUsoProprioProprietarioTerceirosDisparandoException(){
        usuarioLogadoDTO.setCpf("TESTE");
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000010");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000010/0000/P0/USO_PROPRIO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarRipUsoProprioProprietarioEntidade(){
        usuarioLogadoDTO.setIdOrganizacao(64L);
        usuarioLogadoDTO.setOrganizacao("SENADO");
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000011");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000011/0000/P0/USO_PROPRIO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void validarRipUsoProprioProprietarioEntidadeDisparandoException(){
        usuarioLogadoDTO.setIdOrganizacao(64L);
        usuarioLogadoDTO.setOrganizacao("TESTE");
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000011");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000011/0000/P0/USO_PROPRIO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void buscarDadosPosseInformalValido(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000011");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarDadosPosseInformal/00000011/0000/P0")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void buscarDadosPosseInformalComErro(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000011");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarDadosPosseInformal/00000011/0000/P0")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @SneakyThrows
    @Ignore
    public void validarRipTermoEntregaProprietarioDiferenteUniao(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000011");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000011/0000/P0/TERMO_ENTREGA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarRipTermoEntregaValido(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000007");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000007/0000/P0/TERMO_ENTREGA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    public void validarRipTermoEntregaSemAreaDisponivel(){
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000070");
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getImovelJson(), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultarPorRipEDestinacao/00000070/0000/P0/TERMO_ENTREGA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @SneakyThrows
    private String getImovelJson() {
        LinkedHashMap<String, Object> imovel = new LinkedHashMap<>();
        imovel.put("id", 1L);
        imovel.put("rip", "00000010");
        imovel.put("idCadastroImovel", "30");
        imovel.put("benfeitorias", asList(criarBenfeitorias(1L, BigDecimal.valueOf(3242.34)), criarBenfeitorias(2L, BigDecimal.valueOf(234.23))));
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resultado", imovel);
        return toJson(resposta);
    }

    /*@SneakyThrows
    private LinkedHashMap<String, Object> getImovel(String rip, int areaBenfeitoria, String proprietario){
        LinkedHashMap<String, Object> imovel = new LinkedHashMap<>();
        imovel.put("id", 1L);
        imovel.put("rip", rip);
        imovel.put("idCadastroImovel", "30");
        imovel.put("benfeitorias", asList(criarBenfeitorias(1L, BigDecimal.valueOf(areaBenfeitoria)), criarBenfeitorias(2L, BigDecimal.valueOf(areaBenfeitoria + 100))));
        imovel.put("proprietario", proprietario);
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resultado", imovel);
        return resposta;
    }*/

    private LinkedHashMap<String, Object> criarBenfeitorias(Long id, BigDecimal areaConstruida) {
        LinkedHashMap<String, Object> benfeitoria = new LinkedHashMap<>();
        benfeitoria.put("id", id);
        benfeitoria.put("areaConstruida", areaConstruida);
        return benfeitoria;
    }
}
