package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.dto.DocumentoAnaliseDTO;
import br.com.company.fks.destinacao.dominio.dto.DocumentoComplementarDTO;
import br.com.company.fks.destinacao.dominio.dto.DocumentoComplementarEspecificoDTO;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoEspecificoDTO;
import br.com.company.fks.destinacao.dominio.dto.PublicacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Created by diego on 26/01/17.

@IntegrationTest("server.port:0")
public class AnaliseTecnicaControllerIT extends BaseIntegrationTestCofig {

    private String URL_BASE = "/analise-tecnica/";
    private MockServerUtils mockServerUtils;

    @Before
    @SneakyThrows
    public void setUp() {

        String urlGetDadosAtendimento = urlIntegracaoUtils.getUrlGetRequerimentoByID(1L);
        String urlUpdateAnaliseTecnica = urlIntegracaoUtils.getUrlAlterarStatusAnaliseTecnica(1L, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA);

        mockServerUtils = new MockServerUtils()
                                .iniciarRestTemplate(requestUtils.getRestTemplate())
                                .mockServe(urlGetDadosAtendimento, HttpMethod.GET, getRequerimentoJson(), MediaType.APPLICATION_JSON_UTF8)
                                .mockServe(urlUpdateAnaliseTecnica, HttpMethod.PUT, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarIdNotNull() {

        /*SecurityContextHolder.getContext().setAuthentication(
                new AnonymousAuthenticationToken("61914509153", criarUsuarioLogado(), AuthorityUtils.createAuthorityList(getPermissoes())));*/

        AnaliseTecnicaDTO analiseTecnicaDTO = criarAnaliseTecnica(127L);

        String requestJson = toJson(analiseTecnicaDTO);
        mockMvc.perform(post(URL_BASE)
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(128));

    }

    @Test
    @SneakyThrows
    public void salvarIdNull() {

        AnaliseTecnicaDTO analiseTecnicaDTO = criarAnaliseTecnica(null);

        String requestJson = toJson(analiseTecnicaDTO);
        mockMvc.perform(post(URL_BASE)
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(128));

    }


    /*@Ignore
    @Test
    @SneakyThrows
    public void salvarComCodigoStatusPorDespacho() {

        AnaliseTecnicaDTO analiseTecnicaDTO = criarAnaliseTecnica(127l);

        String requestJson = toJson(analiseTecnicaDTO);
        mockMvc.perform(post(URL_BASE)
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(127));

    }*/


    @Test
    @SneakyThrows
    public void salvarGerandoErro() {

        String requestJson = toJson(new AnaliseTecnicaDTO());
        mockMvc.perform(post(URL_BASE)
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());

    }

    @Test
    @SneakyThrows
    public void salvarRascunho() {
        AnaliseTecnicaDTO analiseTecnicaDTO = criarAnaliseTecnica(null);

        String requestJson = toJson(analiseTecnicaDTO);
        mockMvc.perform(post(URL_BASE + "salvarRascunho")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.statusAnaliseTecnica.id").value(2));
    }

    @Test
    @SneakyThrows
    public void salvarRascunhoGerandoErro() {
        String requestJson = toJson(new AnaliseTecnicaDTO());
        mockMvc.perform(post(URL_BASE + "salvarRascunho")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarPorId() {
        mockMvc.perform(get(URL_BASE + "/126")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado.id").value(126));
    }

    @Test
    @SneakyThrows
    public void buscarPorIdGerandoErro() {
        mockMvc.perform(get(URL_BASE + "/122")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarAnalisePorRequerimento() {
        mockMvc.perform(get(URL_BASE + "/requerimento/286")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarAnalisePorRequerimentoGerandoErro() {
        mockMvc.perform(get(URL_BASE + "/requerimento/285")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void registrarPublicacaoDiarioUniao() {

        String urlUpdateAnaliseTecnica = urlIntegracaoUtils.getUrlAlterarStatusAnaliseTecnica(287l, StatusAnaliseTecnicaEnum.PUBLICADO);

        String urlUpdateStatusRequerimento = urlIntegracaoUtils.getUrlGetAlterarStatus("DEFERIDO", 287l);
        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(urlUpdateStatusRequerimento, HttpMethod.PUT, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8)
                .mockServe(urlUpdateAnaliseTecnica, HttpMethod.PUT, StringUtils.EMPTY, MediaType.APPLICATION_JSON_UTF8);

        PublicacaoDTO publicacaoDTO = criarDadosPublicacao();

        AnaliseTecnicaDTO analiseTecnicaDTO = criarDadosAnaliseTecnicaDTO(publicacaoDTO);

        String requestJson = toJson(analiseTecnicaDTO);
        mockMvc.perform(post(URL_BASE + "registrarPublicacaoDiarioUniao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    private AnaliseTecnicaDTO criarDadosAnaliseTecnicaDTO(PublicacaoDTO publicacaoDTO) {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setId(126L);
        analiseTecnicaDTO.setPublicacao(publicacaoDTO);
        return analiseTecnicaDTO;
    }

    private PublicacaoDTO criarDadosPublicacao() {
        PublicacaoDTO publicacaoDTO = new PublicacaoDTO();
        publicacaoDTO.setDataPublicacao(new Date());
        publicacaoDTO.setNumeroPagina(1);
        publicacaoDTO.setNumeroSecao(2);
        return publicacaoDTO;
    }

    private DominioDTO criarStatusAnaliseTecnica(){
        DominioDTO dominioDTO = new DominioDTO();
        dominioDTO.setId(6);
        dominioDTO.setDescricao("teste");
        return dominioDTO;
    }

    private List<DespachoDTO> criarDespachoChefia(){
        DespachoDTO despachoDTO = new DespachoDTO();
        despachoDTO.setId(6l);
        return asList(despachoDTO);
    }

    private List<DespachoDTO> criarDespachoSuperIntendente(){
        DespachoDTO despachoDTO = new DespachoDTO();
        despachoDTO.setId(13l);
        return asList(despachoDTO);
    }


    private AnaliseTecnicaDTO criarAnaliseTecnica(Long id) {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        if (id != null) {
            analiseTecnicaDTO.setId(id);
            analiseTecnicaDTO.setStatusAnaliseTecnica(criarStatusAnaliseTecnica());
            analiseTecnicaDTO.setDespachosChefia(criarDespachoChefia());
            analiseTecnicaDTO.setDespachosSuperintendente(criarDespachoSuperIntendente());
        }
        analiseTecnicaDTO.setDespachosTecnico(criarDespachos(TipoDespachoEnum.DEFAULT));
        analiseTecnicaDTO.setDocumentosAnaliseObrigatorio(criarDocumentoAnaliseDTO("OBRIGATORIO", "teste", 1L, Boolean.TRUE));
        analiseTecnicaDTO.setDocumentosAnaliseComplementar(criarDocumentoAnaliseDTO("COMPLEMENTAR", "teste", 1L, Boolean.TRUE));
        analiseTecnicaDTO.setDocumentoPendente(Boolean.TRUE);
        analiseTecnicaDTO.setObsDocumentoPendente("teste");
        analiseTecnicaDTO.setInformacaoComplementar("teste");
        analiseTecnicaDTO.setCodFundamentoLegal(10L);
        analiseTecnicaDTO.setItensVerificacao(criarListaItensVerificacao());
        analiseTecnicaDTO.setItensVerificacaoEspecifica(criarListaIntesVerificacaoEspecifica());
        analiseTecnicaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        analiseTecnicaDTO.setIdRequerimento(1L);
        analiseTecnicaDTO.setDocumentosComplementaresEspecificos(criarDocumentoComplementarEspecificoDTO());
        analiseTecnicaDTO.setDocumentosComplementares(criarListaDocumentoComplementarDTO());
        return analiseTecnicaDTO;
    }

    private List<DespachoDTO> criarDespachos(TipoDespachoEnum tipoDespachoEnum) {
        DespachoDTO despachoDTO = new DespachoDTO();

        switch (tipoDespachoEnum) {
            case CHEFIA:
                despachoDTO.setId(2L);
                despachoDTO.setTipoDespacho(tipoDespachoEnum);
                break;
            case SUPERINTENDENTE:
                despachoDTO.setId(3L);
                despachoDTO.setTipoDespacho(tipoDespachoEnum);
                break;
            case SECRETARIO:
                despachoDTO.setId(4L);
                despachoDTO.setTipoDespacho(tipoDespachoEnum);
                break;
            default:
                despachoDTO.setId(1L);
                despachoDTO.setTipoDespacho(tipoDespachoEnum);
                break;
        }

        despachoDTO.setJustificativa("teste");
        return asList(despachoDTO);
    }

    private List<DocumentoAnaliseDTO> criarDocumentoAnaliseDTO(String tipoDocumento, String observacao, Long idDocumento, Boolean resposta) {
        DocumentoAnaliseDTO documentoAnaliseDTO = new DocumentoAnaliseDTO();
        documentoAnaliseDTO.setTipoDocumento(tipoDocumento);
        documentoAnaliseDTO.setIdDocumento(idDocumento);
        documentoAnaliseDTO.setObservacao(observacao);
        documentoAnaliseDTO.setResposta(resposta);
        return asList(documentoAnaliseDTO);
    }


    private TipoDestinacaoDTO criarTipoDestinacaoDTO() {
        TipoDestinacaoDTO tipoDestinacaoDTO = new TipoDestinacaoDTO();
        tipoDestinacaoDTO.setId(1L);
        tipoDestinacaoDTO.setDescricao("Doação");
        return tipoDestinacaoDTO;
    }

    private List<ItemVerificacaoDTO> criarListaItensVerificacao() {
        ItemVerificacaoDTO itemVerificacaoDTO = new ItemVerificacaoDTO();
        itemVerificacaoDTO.setIdItem(1L);
        itemVerificacaoDTO.setResposta(Boolean.TRUE);
        itemVerificacaoDTO.setObservacao("teste");
        return asList(itemVerificacaoDTO);
    }

    private List<ItemVerificacaoEspecificoDTO> criarListaIntesVerificacaoEspecifica() {
        ItemVerificacaoEspecificoDTO itemVerificacaoEspecificoDTO = new ItemVerificacaoEspecificoDTO();
        itemVerificacaoEspecificoDTO.setResposta(Boolean.TRUE);
        itemVerificacaoEspecificoDTO.setIdItem(1L);
        itemVerificacaoEspecificoDTO.setObservacao("teste");
        return asList(itemVerificacaoEspecificoDTO);
    }

    private List<DocumentoComplementarEspecificoDTO> criarDocumentoComplementarEspecificoDTO() {
        DocumentoComplementarEspecificoDTO documentoComplementarEspecificoDTO = new DocumentoComplementarEspecificoDTO();
        documentoComplementarEspecificoDTO.setArquivo(criarArquivoDTO());
        return asList(documentoComplementarEspecificoDTO);
    }

    private List<DocumentoComplementarDTO> criarListaDocumentoComplementarDTO() {
        DocumentoComplementarDTO documentoComplementarDTO = new DocumentoComplementarDTO();
        documentoComplementarDTO.setArquivo(criarArquivoDTO());
        return asList(documentoComplementarDTO);
    }

    private ArquivoDTO criarArquivoDTO() {
        ArquivoDTO arquivoDTO = new ArquivoDTO();
        arquivoDTO.setId(1L);
        return arquivoDTO;
    }

    private String getRequerimentoJson() throws JsonProcessingException {
        Map<String, Object> resposta = new HashedMap();
        Map<String, Object> requerimento = new HashedMap();
        Map<String, Object> requerente = new HashedMap();
        Map<String, Object> atendimento = new HashedMap();

        atendimento.put("numero", "123");
        requerente.put("nome", "Teste");
        requerente.put("email", "teste@teste.com");

        requerimento.put("atendimento", atendimento);
        requerimento.put("requerente", requerente);

        List listaObjetos = Arrays.asList(requerimento);
        resposta.put("resposta", listaObjetos);
        return toJson(resposta);
    }

}
