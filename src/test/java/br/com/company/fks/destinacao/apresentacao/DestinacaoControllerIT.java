package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.builder.AtoAutorizativoDTOBuilder;
import br.com.company.fks.destinacao.apresentacao.builder.BenfeitoriaDestinadaDTOBuilder;
import br.com.company.fks.destinacao.apresentacao.builder.DominioDTOBuilder;
import br.com.company.fks.destinacao.apresentacao.builder.EnderecoDTOBuilder;
import br.com.company.fks.destinacao.apresentacao.builder.ImovelDTOBuilder;
import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.dto.BenfeitoriaDTO;
import br.com.company.fks.destinacao.dominio.dto.BenfeitoriaDestinadaDTO;
import br.com.company.fks.destinacao.dominio.dto.CdruDTO;
import br.com.company.fks.destinacao.dominio.dto.CessaoGratuitaDTO;
import br.com.company.fks.destinacao.dominio.dto.ContratoDTO;
import br.com.company.fks.destinacao.dominio.dto.CuemDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.DoacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.DocumentoArquivoDTO;
import br.com.company.fks.destinacao.dominio.dto.DocumentoDTO;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.dto.EncargoDTO;
import br.com.company.fks.destinacao.dominio.dto.EnderecoDTO;
import br.com.company.fks.destinacao.dominio.dto.FamiliaBeneficiadaDTO;
import br.com.company.fks.destinacao.dominio.dto.FinanceiroDTO;
import br.com.company.fks.destinacao.dominio.dto.FotoDTO;
import br.com.company.fks.destinacao.dominio.dto.ImagemDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.LicitacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.OcupanteDTO;
import br.com.company.fks.destinacao.dominio.dto.ParcelaDTO;
import br.com.company.fks.destinacao.dominio.dto.PendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.PermissaoUsoImovelFuncionalDTO;
import br.com.company.fks.destinacao.dominio.dto.PosseInformalDTO;
import br.com.company.fks.destinacao.dominio.dto.ResponsavelDTO;
import br.com.company.fks.destinacao.dominio.dto.SubTipoDocumentoDTO;
import br.com.company.fks.destinacao.dominio.dto.TermoEntregaDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoConcessaoDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoInstrumentoDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoModalidadeDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoPosseDTO;
import br.com.company.fks.destinacao.dominio.dto.UnidadeAutonomaDTO;
import br.com.company.fks.destinacao.dominio.dto.UsoProprioDTO;
import br.com.company.fks.destinacao.dominio.dto.UtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.VendaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.TipoJuro;
import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import br.com.company.fks.destinacao.dominio.entidades.TipoPagamento;
import br.com.company.fks.destinacao.dominio.entidades.TipoReajuste;
import br.com.company.fks.destinacao.dominio.enums.StatusDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoImovelEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoOperacaoEnum;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by diego on 05/01/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DestinacaoControllerIT extends BaseIntegrationTestCofig {

    private String URL_BASE = "/destinacao/";

    private static final Long USO_PROPRIO_PENDENTE = 4L;

    private MockServerUtils mockServerUtils;

    @Autowired
    private Environment environment;

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarDoacaoTodosCamposPreenchidos() {
        DoacaoDTO doacaoDTO = new DoacaoDTO();
        doacaoDTO.setNumeroAtendimento("DF00001/2017");
        doacaoDTO.setNumeroProcesso("00000001");
        doacaoDTO.setExisteEncargo(Boolean.TRUE);

        doacaoDTO.setContrato(criarContratoDTO());
        doacaoDTO.setTipoInstrumento(criarTipoInstrumento());
        doacaoDTO.setEncargos(criarListaEncargos());
        doacaoDTO.setExisteEncargo(true);
        doacaoDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.DOACAO);
        doacaoDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        doacaoDTO.setDocumentos(criaDocumentoDTO());
        doacaoDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        doacaoDTO.setUtilizacao(criarUtilizacaoDTO());
        doacaoDTO.setCodFundamentoLegal(1L);
        doacaoDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        doacaoDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(doacaoDTO);
        mockMvc.perform(post(URL_BASE + "/doacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarDoacaoTodosCamposPreenchidosComStatus() {
        DoacaoDTO doacaoDTO = new DoacaoDTO();
        doacaoDTO.setNumeroAtendimento("DF00001/2017");
        doacaoDTO.setNumeroProcesso("00000001");
        doacaoDTO.setExisteEncargo(Boolean.TRUE);
        doacaoDTO.setContrato(criarContratoDTO());
        doacaoDTO.setTipoInstrumento(criarTipoInstrumento());
        doacaoDTO.setEncargos(criarListaEncargos());
        doacaoDTO.setExisteEncargo(true);
        doacaoDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.DOACAO);
        doacaoDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        doacaoDTO.setDocumentos(criaDocumentoDTO());
        doacaoDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        doacaoDTO.setUtilizacao(criarUtilizacaoDTO());
        doacaoDTO.setCodFundamentoLegal(1L);
        doacaoDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        doacaoDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(4).build());

        String requestJson = toJson(doacaoDTO);
        mockMvc.perform(post(URL_BASE + "/doacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarDoacaoPorId() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000029");
        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder()
                .setLonLat(100.0, 100.0)
                .setImagem("base64").build();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "29/DOACAO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(29));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarDoacaoFaltandoCampos() {
        DoacaoDTO doacaoDTO = new DoacaoDTO();
        doacaoDTO.setNumeroAtendimento("DF00001/2017");
        doacaoDTO.setNumeroProcesso("00000001");
        doacaoDTO.setExisteEncargo(Boolean.TRUE);
        doacaoDTO.setContrato(criarContratoDTO());
        doacaoDTO.setTipoInstrumento(criarTipoInstrumento());
        doacaoDTO.setEncargos(Collections.emptyList());
        doacaoDTO.setExisteEncargo(Boolean.TRUE);
        doacaoDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.DOACAO);
        doacaoDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        doacaoDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        doacaoDTO.setUtilizacao(criarUtilizacaoDTO());
        doacaoDTO.setCodFundamentoLegal(1L);
        doacaoDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        doacaoDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(doacaoDTO);
        mockMvc.perform(post(URL_BASE + "/doacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarDoacaoFaltandoEncargos() {
        DoacaoDTO doacaoDTO = new DoacaoDTO();
        doacaoDTO.setNumeroAtendimento("DF00001/2017");
        doacaoDTO.setNumeroProcesso("00000001");
        doacaoDTO.setExisteEncargo(Boolean.TRUE);
        doacaoDTO.setContrato(criarContratoDTO());
        doacaoDTO.setTipoInstrumento(criarTipoInstrumento());
        doacaoDTO.setEncargos(null);
        doacaoDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.DOACAO);
        doacaoDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        doacaoDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        doacaoDTO.setUtilizacao(criarUtilizacaoDTO());
        doacaoDTO.setCodFundamentoLegal(1L);
        doacaoDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        doacaoDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(doacaoDTO);
        mockMvc.perform(post(URL_BASE + "/doacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarDoacaoSemEncargos() {
        DoacaoDTO doacaoDTO = new DoacaoDTO();
        doacaoDTO.setNumeroAtendimento("DF00001/2017");
        doacaoDTO.setNumeroProcesso("00000001");
        doacaoDTO.setExisteEncargo(Boolean.TRUE);
        doacaoDTO.setContrato(criarContratoDTO());
        doacaoDTO.setTipoInstrumento(criarTipoInstrumento());
        doacaoDTO.setEncargos(criarListaEncargos());
        doacaoDTO.setExisteEncargo(false);
        doacaoDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.DOACAO);
        doacaoDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        doacaoDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        doacaoDTO.setUtilizacao(criarUtilizacaoDTO());
        doacaoDTO.setCodFundamentoLegal(1L);
        doacaoDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        doacaoDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(doacaoDTO);
        mockMvc.perform(post(URL_BASE + "/doacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarDoacaoLancandoErro() {
        DoacaoDTO doacaoDTO = new DoacaoDTO();
        doacaoDTO.setNumeroAtendimento("DF00001/2017");
        doacaoDTO.setNumeroProcesso("00000001");
        doacaoDTO.setExisteEncargo(Boolean.TRUE);
        doacaoDTO.setContrato(criarContratoDTO());
        doacaoDTO.setTipoInstrumento(criarTipoInstrumento());
        doacaoDTO.setEncargos(criarListaEncargos());
        doacaoDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.DOACAO);
        doacaoDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        doacaoDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        doacaoDTO.setUtilizacao(criarUtilizacaoDTO());
        doacaoDTO.setCodFundamentoLegal(1L);
        doacaoDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        doacaoDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(doacaoDTO);
        mockMvc.perform(post(URL_BASE + "/doacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarVendaTodosCamposPreenchidos() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        vendaDTO.setDocumentos(criaDocumentoDTO());
        vendaDTO.setLicitacao(criarLicitacaoDTO());
        vendaDTO.setFinanceiro(criarFinanceiroDTOVenda());
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarVendaTodosCamposPreenchidosSemArquivos() {
        LicitacaoDTO licitacaoDTO = criarLicitacaoDTO();
        licitacaoDTO.setArquivos(null);
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        vendaDTO.setDocumentos(criaDocumentoDTO());
        vendaDTO.setLicitacao(licitacaoDTO);
        vendaDTO.setFinanceiro(criarFinanceiroDTOVenda());
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarVendaFaltandoCampos() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        LicitacaoDTO licitacaoDTO = criarLicitacaoDTO();
        licitacaoDTO.setNumeroProcesso(null);
        vendaDTO.setLicitacao(licitacaoDTO);
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());

        FinanceiroDTO financeiroDTO = criarFinanceiroDTOVenda();
        financeiroDTO.setTipoPagamento(null);

        vendaDTO.setFinanceiro(financeiroDTO);

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarVendaFaltandoValorFinanceiroLicitacao() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        LicitacaoDTO licitacaoDTO = new LicitacaoDTO();
        licitacaoDTO.setNumeroProcesso("000");
        vendaDTO.setLicitacao(licitacaoDTO);
        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        FinanceiroDTO financeiroDTO = criarFinanceiroDTOVenda();
        financeiroDTO.setTipoPagamento(criarTipoPagamento(2));
        financeiroDTO.setValorEntrada(null);
        financeiroDTO.setValorFinancidado(null);
        financeiroDTO.setValor(null);
        financeiroDTO.setMesAnoReajuste("102020");
        financeiroDTO.setTipoJurosMensal(criarTipoJuro(1));
        financeiroDTO.setDataInicioCobranca(criarDataCobranca(1, 1));
        financeiroDTO.setTipoMoeda(null);

        vendaDTO.setFinanceiro(financeiroDTO);

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarVendaFaltandoValorFinanceiroTipoPagamentoParceladoImovelExterior() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(true));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        LicitacaoDTO licitacaoDTO = new LicitacaoDTO();
        licitacaoDTO.setNumeroProcesso("000");
        vendaDTO.setLicitacao(licitacaoDTO);
        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        FinanceiroDTO financeiroDTO = criarFinanceiroDTOVenda();
        financeiroDTO.setTipoPagamento(criarTipoPagamento(1));
        financeiroDTO.setValorEntrada(BigDecimal.TEN);
        financeiroDTO.setValorFinancidado(BigDecimal.TEN);
        financeiroDTO.setValor(new BigDecimal(5));
        financeiroDTO.setMesAnoReajuste("022017");
        financeiroDTO.setTipoJurosMensal(criarTipoJuro(2));
        financeiroDTO.setDataInicioCobranca(criarDataCobranca(1, 1));
        financeiroDTO.setTipoMoeda(null);
        financeiroDTO.setTipoIndiceJurosMensal(null);

        vendaDTO.setFinanceiro(financeiroDTO);

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarVendaFaltandoValorTotalImovel() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        LicitacaoDTO licitacaoDTO = new LicitacaoDTO();
        licitacaoDTO.setNumeroProcesso("000");
        vendaDTO.setLicitacao(licitacaoDTO);
        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        FinanceiroDTO financeiroDTO = criarFinanceiroDTOVenda();
        financeiroDTO.setTipoPagamento(criarTipoPagamento(2));
        financeiroDTO.setValorEntrada(BigDecimal.TEN);
        financeiroDTO.setValorFinancidado(null);
        financeiroDTO.setValor(new BigDecimal(30.0));
        financeiroDTO.setMesAnoReajuste("022017");
        financeiroDTO.setTipoJurosMensal(criarTipoJuro(2));
        financeiroDTO.setDataInicioCobranca(criarDataCobranca(1, 1));
        financeiroDTO.setTipoMoeda(criarTipoMoeda(null));
        financeiroDTO.setTipoIndiceJurosMensal(criarTipoReajuste(1));

        vendaDTO.setFinanceiro(financeiroDTO);

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarVendaFaltandoTipoPagamentoIdNulo() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        LicitacaoDTO licitacaoDTO = new LicitacaoDTO();
        licitacaoDTO.setNumeroProcesso("000");
        vendaDTO.setLicitacao(licitacaoDTO);
        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        FinanceiroDTO financeiroDTO = criarFinanceiroDTOVenda();
        financeiroDTO.setTipoPagamento(criarTipoPagamento(null));
        financeiroDTO.setValorEntrada(null);
        financeiroDTO.setValorFinancidado(BigDecimal.TEN);
        financeiroDTO.setValor(null);
        financeiroDTO.setMesAnoReajuste("022017");
        financeiroDTO.setTipoJurosMensal(criarTipoJuro(2));
        financeiroDTO.setDataInicioCobranca(criarDataCobranca(10, 10));
        financeiroDTO.setTipoMoeda(criarTipoMoeda(1));
        financeiroDTO.setTipoIndiceJurosMensal(null);
        financeiroDTO.setJurosMensal(null);

        vendaDTO.setFinanceiro(financeiroDTO);

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarVendaFaltandoTipoMoedaIdTipoReajusteMensalIdNulo() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        LicitacaoDTO licitacaoDTO = new LicitacaoDTO();
        licitacaoDTO.setNumeroProcesso("000");
        vendaDTO.setLicitacao(licitacaoDTO);
        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        FinanceiroDTO financeiroDTO = criarFinanceiroDTOVenda();
        financeiroDTO.setTipoPagamento(criarTipoPagamento(2));
        financeiroDTO.setValorEntrada(BigDecimal.TEN);
        financeiroDTO.setValorFinancidado(BigDecimal.TEN);
        financeiroDTO.setValor(new BigDecimal(30.0));
        financeiroDTO.setMesAnoReajuste("022017");
        financeiroDTO.setTipoJurosMensal(criarTipoJuro(2));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 1);
        financeiroDTO.setDataInicioCobranca(criarDataCobranca(1, 1));
        financeiroDTO.setTipoMoeda(criarTipoMoeda(null));
        financeiroDTO.setTipoIndiceJurosMensal(criarTipoReajuste(null));
        financeiroDTO.setJurosMensal(Double.valueOf(1.0));

        vendaDTO.setFinanceiro(financeiroDTO);

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarVendaTodosGerandoErro() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNumeroAtendimento("DF00001/2017");
        vendaDTO.setNumeroProcesso("00000001");
        vendaDTO.setContrato(criarContratoDTO());
        vendaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.VENDA);
        vendaDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        vendaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        vendaDTO.setUtilizacao(criarUtilizacaoDTO());
        vendaDTO.setCodFundamentoLegal(1L);
        vendaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        // vendaDTO.setLicitacao(criarLicitacaoDTO());
        vendaDTO.setFinanceiro(criarFinanceiroDTOVenda());
        vendaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(vendaDTO);
        mockMvc.perform(post(URL_BASE + "/venda")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarPosseInformalCamposPreenchidos() {
        PosseInformalDTO posseInformalDTO = new PosseInformalDTO();
        posseInformalDTO.setNumeroAtendimento("DF00001/2017");
        posseInformalDTO.setNumeroProcesso("00000001");
        posseInformalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        posseInformalDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        posseInformalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        posseInformalDTO.setUtilizacao(criarUtilizacaoDTO());
        posseInformalDTO.setCodFundamentoLegal(1L);
        posseInformalDTO.setImovel(criarImovelDTO(false));
        posseInformalDTO.setTipoPosse(criarTipoPosseDTO(1));
        posseInformalDTO.setOcupantes(criarListaOcupanteDTO());
        posseInformalDTO.setFotos(Arrays.asList(criarFotoDTO()));
        posseInformalDTO.setDocumentosArquivo(Arrays.asList(criarDocumentoArquivoDTO()));
        posseInformalDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(posseInformalDTO);
        mockMvc.perform(post(URL_BASE + "/posse-informal")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarPosseInformalCamposPreenchidosSemOutrosDestinacao() {
        PosseInformalDTO posseInformalDTO = new PosseInformalDTO();
        posseInformalDTO.setNumeroAtendimento("DF00001/2017");
        posseInformalDTO.setNumeroProcesso("00000001");
        posseInformalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        posseInformalDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        posseInformalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        posseInformalDTO.setUtilizacao(criarUtilizacaoDTO());
        posseInformalDTO.setCodFundamentoLegal(1L);
        posseInformalDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        posseInformalDTO.setDocumentos(criaDocumentoDTO());
        ImovelDTO imovelDTO = criarImovelDTO(false);
        imovelDTO.setRip("00000005");
        imovelDTO.setId(6L);
        posseInformalDTO.setImovel(imovelDTO);
        posseInformalDTO.setTipoPosse(criarTipoPosseDTO(1));
        posseInformalDTO.setOcupantes(criarListaOcupanteDTO());
        posseInformalDTO.setNomeEntidade(null);
        posseInformalDTO.setFotos(Arrays.asList(criarFotoDTO()));
        posseInformalDTO.setDocumentosArquivo(Arrays.asList(criarDocumentoArquivoDTO()));
        posseInformalDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(posseInformalDTO);
        mockMvc.perform(post(URL_BASE + "/posse-informal")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarPosseInformalCamposPreenchidosSemFotoInteressado() {
        PosseInformalDTO posseInformalDTO = new PosseInformalDTO();
        posseInformalDTO.setNumeroAtendimento("DF00001/2017");
        posseInformalDTO.setNumeroProcesso("00000001");
        posseInformalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        posseInformalDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        posseInformalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        posseInformalDTO.setUtilizacao(criarUtilizacaoDTO());
        posseInformalDTO.setCodFundamentoLegal(1L);
        posseInformalDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        posseInformalDTO.setImovel(criarImovelDTO(false));
        posseInformalDTO.setTipoPosse(criarTipoPosseDTO(3));
        posseInformalDTO.setOcupantes(criarListaOcupanteDTO());
        posseInformalDTO.setNomeEntidade("teste");
        posseInformalDTO.setFotos(Arrays.asList(criarFotoDTO()));
        posseInformalDTO.setDocumentosArquivo(Arrays.asList(criarDocumentoArquivoDTO()));
        posseInformalDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(posseInformalDTO);
        mockMvc.perform(post(URL_BASE + "/posse-informal")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarPosseInformalTipoPosseNulo() {
        PosseInformalDTO posseInformalDTO = new PosseInformalDTO();
        posseInformalDTO.setNumeroAtendimento("DF00001/2017");
        posseInformalDTO.setNumeroProcesso("00000001");
        posseInformalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        posseInformalDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        posseInformalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        posseInformalDTO.setUtilizacao(criarUtilizacaoDTO());
        posseInformalDTO.setCodFundamentoLegal(1L);
        posseInformalDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        posseInformalDTO.setDocumentos(criaDocumentoDTO());
        ImovelDTO imovelDTO = criarImovelDTO(false);
        imovelDTO.setRip("00000005");
        imovelDTO.setId(6L);
        posseInformalDTO.setImovel(imovelDTO);
        posseInformalDTO.setTipoPosse(null);
        posseInformalDTO.setOcupantes(criarListaOcupanteDTO());
        posseInformalDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(posseInformalDTO);
        mockMvc.perform(post(URL_BASE + "/posse-informal")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarPosseInformalNomeEntidadeNulo() {
        PosseInformalDTO posseInformalDTO = new PosseInformalDTO();
        posseInformalDTO.setNumeroAtendimento("DF00001/2017");
        posseInformalDTO.setNumeroProcesso("00000001");
        posseInformalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        posseInformalDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        posseInformalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        posseInformalDTO.setUtilizacao(criarUtilizacaoDTO());
        posseInformalDTO.setCodFundamentoLegal(1L);
        posseInformalDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        posseInformalDTO.setDocumentos(criaDocumentoDTO());
        ImovelDTO imovelDTO = criarImovelDTO(false);
        imovelDTO.setRip("00000005");
        imovelDTO.setId(6L);
        posseInformalDTO.setImovel(imovelDTO);
        posseInformalDTO.setTipoPosse(criarTipoPosseDTO(null));
        posseInformalDTO.setOcupantes(criarListaOcupanteDTO());
        posseInformalDTO.setNomeEntidade(null);
        posseInformalDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(posseInformalDTO);
        mockMvc.perform(post(URL_BASE + "/posse-informal")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarPosseInformalGerandoErro() {
        PosseInformalDTO posseInformalDTO = new PosseInformalDTO();
        posseInformalDTO.setNumeroAtendimento("DF00001/2017");
        posseInformalDTO.setNumeroProcesso("00000001");
        posseInformalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        posseInformalDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        posseInformalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        posseInformalDTO.setUtilizacao(criarUtilizacaoDTO());
        posseInformalDTO.setCodFundamentoLegal(1L);
        posseInformalDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        posseInformalDTO.setImovel(criarImovelDTO(false));
        posseInformalDTO.setTipoPosse(criarTipoPosseDTO(1));
        posseInformalDTO.setFotos(Arrays.asList(criarFotoDTO()));
        posseInformalDTO.setDocumentosArquivo(Arrays.asList(criarDocumentoArquivoDTO()));
        posseInformalDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(posseInformalDTO);
        mockMvc.perform(post(URL_BASE + "/posse-informal")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarPosseInformalFaltandoCampos() {
        PosseInformalDTO posseInformalDTO = new PosseInformalDTO();
        posseInformalDTO.setNumeroAtendimento("DF00001/2017");
        posseInformalDTO.setNumeroProcesso("00000001");
        posseInformalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        posseInformalDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        posseInformalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        posseInformalDTO.setUtilizacao(criarUtilizacaoDTO());
        posseInformalDTO.setCodFundamentoLegal(1L);
        posseInformalDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        posseInformalDTO.setImovel(criarImovelDTO(false));
        posseInformalDTO.setFotos(Arrays.asList(criarFotoDTO()));
        posseInformalDTO.setDocumentosArquivo(Arrays.asList(criarDocumentoArquivoDTO()));
        posseInformalDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(posseInformalDTO);
        mockMvc.perform(post(URL_BASE + "/posse-informal")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros[0]").value("O campo Tipo Posse não pode ser vazio"));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarPosseInformalSemRip() {
        PosseInformalDTO posseInformalDTO = new PosseInformalDTO();
        posseInformalDTO.setNumeroAtendimento("DF00001/2017");
        posseInformalDTO.setNumeroProcesso("00000001");
        posseInformalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        posseInformalDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        posseInformalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        posseInformalDTO.setUtilizacao(criarUtilizacaoDTO());
        posseInformalDTO.setCodFundamentoLegal(1L);
        posseInformalDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        posseInformalDTO.setImovel(criarImovelDTOTeste());
        posseInformalDTO.setFotos(Arrays.asList(criarFotoDTO()));
        posseInformalDTO.setDocumentosArquivo(Arrays.asList(criarDocumentoArquivoDTO()));
        posseInformalDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(posseInformalDTO);
        mockMvc.perform(post(URL_BASE + "/posse-informal")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros[0]").value("O campo Tipo Posse não pode ser vazio"));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCuemCamposPreenchidos() {

        CuemDTO cuemDTO = new CuemDTO();
        cuemDTO.setNumeroAtendimento("DF00001/2017");
        cuemDTO.setNumeroProcesso("00000001");
        cuemDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CUEM);
        cuemDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        cuemDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cuemDTO.setUtilizacao(criarUtilizacaoDTO());
        cuemDTO.setCodFundamentoLegal(1L);
        cuemDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cuemDTO.setDocumentos(criaDocumentoDTO());
        cuemDTO.setTipoModalidade(criarTipoModalidadeDTO());
        cuemDTO.setContrato(criarContratoDTO());
        cuemDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cuemDTO);
        mockMvc.perform(post(URL_BASE + "/cuem")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));

    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarCuemFaltandoCampos() {

        CuemDTO cuemDTO = new CuemDTO();
        cuemDTO.setNumeroAtendimento("DF00001/2017");
        cuemDTO.setNumeroProcesso("00000001");
        cuemDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CUEM);
        cuemDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        cuemDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cuemDTO.setUtilizacao(criarUtilizacaoDTO());
        cuemDTO.setCodFundamentoLegal(1L);
        cuemDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cuemDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cuemDTO.setTipoModalidade(criarTipoModalidadeDTO());
        cuemDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cuemDTO);
        mockMvc.perform(post(URL_BASE + "/cuem")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros.[0]").value("O campo Data ínicio da Vigência não pode ser vazio"));

    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarCuemPorId() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000031");
        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder()
                .setLonLat(100.0, 100.0)
                .setImagem("base64").build();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "31/CUEM")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(31));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarCuemGerandoErros() {

        CuemDTO cuemDTO = new CuemDTO();
        cuemDTO.setNumeroAtendimento("DF00001/2017");
        cuemDTO.setNumeroProcesso("00000001");
        cuemDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CUEM);
        cuemDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        cuemDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cuemDTO.setUtilizacao(criarUtilizacaoDTO());
        cuemDTO.setCodFundamentoLegal(1L);
        cuemDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cuemDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cuemDTO.setTipoModalidade(criarTipoModalidadeDTO());
        cuemDTO.setContrato(criarContratoDTO());
        cuemDTO.getContrato().setId(2L);
        cuemDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cuemDTO);
        mockMvc.perform(post(URL_BASE + "/cuem")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isInternalServerError());

    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCdruCamposPreenchidos() {

        CdruDTO cdruDTO = new CdruDTO();
        cdruDTO.setNumeroAtendimento("DF00001/2017");
        cdruDTO.setNumeroProcesso("00000001");
        cdruDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CDRU);
        cdruDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        cdruDTO.setCodFundamentoLegal(1L);
        cdruDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cdruDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cdruDTO.setDocumentos(criaDocumentoDTO());
        cdruDTO.setContrato(criarContratoDTO());
        cdruDTO.setEncargos(criarListaEncargos());
        cdruDTO.setFinanceiro(criarFinanceiroDTOVenda());
        cdruDTO.setLicitacao(criarLicitacaoDTO());
        cdruDTO.setTipoConcessao(criarTipoConcessaoDTO());
        cdruDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cdruDTO);
        mockMvc.perform(post(URL_BASE + "/cdru")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));

    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarCdruFaltandoCampos() {

        CdruDTO cdruDTO = new CdruDTO();
        cdruDTO.setNumeroAtendimento("DF00001/2017");
        cdruDTO.setNumeroProcesso("00000001");
        cdruDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CDRU);
        cdruDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        cdruDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cdruDTO.setCodFundamentoLegal(1L);
        cdruDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cdruDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cdruDTO.setContrato(criarContratoDTO());
        cdruDTO.setEncargos(criarListaEncargos());
        cdruDTO.setFinanceiro(criarFinanceiroDTOVenda());
        cdruDTO.setLicitacao(criarLicitacaoDTO());
        TipoConcessaoDTO tipoConcessaoDTO = new TipoConcessaoDTO();
        tipoConcessaoDTO.setDescricao("teset");
        cdruDTO.setTipoConcessao(tipoConcessaoDTO);
        cdruDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cdruDTO);
        mockMvc.perform(post(URL_BASE + "/cdru")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros.[0]").value("Tipo Concessão Vazio"));

    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarCdruGerandoErros() {

        CdruDTO cdruDTO = new CdruDTO();
        cdruDTO.setNumeroAtendimento("DF00001/2017");
        cdruDTO.setNumeroProcesso("00000001");
        cdruDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CDRU);
        cdruDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        cdruDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cdruDTO.setCodFundamentoLegal(1L);
        cdruDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cdruDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        cdruDTO.setContrato(criarContratoDTO());
        cdruDTO.setEncargos(criarListaEncargos());
        cdruDTO.setFinanceiro(criarFinanceiroDTOVenda());
        cdruDTO.setLicitacao(criarLicitacaoDTO());
        cdruDTO.setTipoConcessao(criarTipoConcessaoDTO());
        cdruDTO.getContrato().getArquivo().setId(2L);
        cdruDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cdruDTO);
        mockMvc.perform(post(URL_BASE + "/cdru")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarCdruDestinacaoImovel() {

        CdruDTO cdruDTO = new CdruDTO();
        cdruDTO.setNumeroAtendimento("DF00001/2017");
        cdruDTO.setNumeroProcesso("00000001");
        cdruDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CDRU);
        cdruDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        cdruDTO.setCodFundamentoLegal(1L);
        cdruDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cdruDTO.setContrato(criarContratoDTO());
        cdruDTO.setEncargos(criarListaEncargos());
        cdruDTO.setFinanceiro(criarFinanceiroDTOVenda());
        cdruDTO.setLicitacao(criarLicitacaoDTO());
        cdruDTO.setTipoConcessao(criarTipoConcessaoDTO());
        cdruDTO.getContrato().getArquivo().setId(2L);
        cdruDTO.setDestinacaoImoveis(criarDestinacaoImovel());
        cdruDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());
        String requestJson = toJson(cdruDTO);
        mockMvc.perform(post(URL_BASE + "/cdru")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarCdruPorId() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000032");
        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder()
                .setLonLat(100.0, 100.0)
                .setImagem("base64").build();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "32/CDRU")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(32));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarTermoEntrega() {
        TermoEntregaDTO termoEntregaDTO = new TermoEntregaDTO();
        termoEntregaDTO.setNumeroAtendimento("DF00001/2017");
        termoEntregaDTO.setNumeroProcesso("00000001");
        termoEntregaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTOParcela());
        termoEntregaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        termoEntregaDTO.setDocumentos(criaDocumentoDTO());
        termoEntregaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(4).build());
        termoEntregaDTO.setCodFundamentoLegal(1L);
        termoEntregaDTO.setContrato(criarContratoDTO());
        termoEntregaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.TERMO_ENTREGA);
        termoEntregaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(termoEntregaDTO);
        mockMvc.perform(post(URL_BASE + "/termo-entrega")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarTermoEntregaAtoAutorizativoNull() {
        TermoEntregaDTO termoEntregaDTO = new TermoEntregaDTO();
        termoEntregaDTO.setNumeroAtendimento("DF00001/2017");
        termoEntregaDTO.setNumeroProcesso("00000001");
        termoEntregaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTOParcela());
        termoEntregaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        termoEntregaDTO.setCodFundamentoLegal(1L);
        termoEntregaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(4).build());
        termoEntregaDTO.setContrato(criarContratoDTO());
        termoEntregaDTO.setAtoAutorizativo(null);
        termoEntregaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.TERMO_ENTREGA);

        String requestJson = toJson(termoEntregaDTO);
        mockMvc.perform(post(URL_BASE + "/termo-entrega")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Test
    @SneakyThrows
    public void recuperarTiposDestincacao() {
        mockMvc.perform(get(URL_BASE + "buscar-tipo-destinacao")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCessaoGratuita() {
        CessaoGratuitaDTO cessaoGratuitaDTO = new CessaoGratuitaDTO();
        cessaoGratuitaDTO.setNumeroAtendimento("DF00001/2017");
        cessaoGratuitaDTO.setNumeroProcesso("00000001");
        cessaoGratuitaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTOParcela());
        cessaoGratuitaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cessaoGratuitaDTO.setDocumentos(criaDocumentoDTO());
        cessaoGratuitaDTO.setCodFundamentoLegal(1L);
        cessaoGratuitaDTO.setContrato(criarContratoDTO());
        cessaoGratuitaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_GRATUITA);
        cessaoGratuitaDTO.setUtilizacao(criarUtilizacaoDTO());
        cessaoGratuitaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cessaoGratuitaDTO);
        mockMvc.perform(post(URL_BASE + "/cessaoGratuita")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarCessaoGratuitaPorId() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000030");
        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder()
                .setLonLat(100.0, 100.0)
                .setImagem("base64").build();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "30/CESSAO_GRATUITA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(30));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCessaoGratuitaFamiliaBeneficiadaEmpty() {
        List<ResponsavelDTO> listaResponsaveis = new ArrayList<>();
        ResponsavelDTO responsavel = criarListaResponsaveisPessoaFisica().get(0);
        responsavel.setFamiliasBeneficiadas(Collections.EMPTY_LIST);
        listaResponsaveis.add(responsavel);
        CessaoGratuitaDTO cessaoGratuitaDTO = new CessaoGratuitaDTO();
        cessaoGratuitaDTO.setNumeroAtendimento("DF00001/2017");
        cessaoGratuitaDTO.setNumeroProcesso("00000001");
        cessaoGratuitaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTOParcela());
        cessaoGratuitaDTO.getDadosResponsavel().setResponsaveis(listaResponsaveis);
        cessaoGratuitaDTO.setDocumentos(criaDocumentoDTO());
        cessaoGratuitaDTO.setCodFundamentoLegal(1L);
        cessaoGratuitaDTO.setContrato(criarContratoDTO());
        cessaoGratuitaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_GRATUITA);
        cessaoGratuitaDTO.setUtilizacao(criarUtilizacaoDTO());
        cessaoGratuitaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cessaoGratuitaDTO);
        mockMvc.perform(post(URL_BASE + "/cessaoGratuita")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCessaoGratuitaComFamiliaBeneficiada() {
        List<ResponsavelDTO> listaResponsaveis = new ArrayList<>();
        List<FamiliaBeneficiadaDTO> listaFamiliasBeneficiadas = new ArrayList<>();
        ResponsavelDTO responsavel = criarListaResponsaveisPessoaFisica().get(0);
        FamiliaBeneficiadaDTO familiaBeneficiada = criarListaFamiliaBeneficiadaDTO().get(0);
        familiaBeneficiada.setId(null);
        listaFamiliasBeneficiadas.add(familiaBeneficiada);
        responsavel.setFamiliasBeneficiadas(listaFamiliasBeneficiadas);
        listaResponsaveis.add(responsavel);
        CessaoGratuitaDTO cessaoGratuitaDTO = new CessaoGratuitaDTO();
        cessaoGratuitaDTO.setNumeroAtendimento("DF00001/2017");
        cessaoGratuitaDTO.setNumeroProcesso("00000001");
        cessaoGratuitaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTOParcela());
        cessaoGratuitaDTO.getDadosResponsavel().setResponsaveis(listaResponsaveis);
        cessaoGratuitaDTO.setDocumentos(criaDocumentoDTO());
        cessaoGratuitaDTO.setCodFundamentoLegal(1L);
        cessaoGratuitaDTO.setContrato(criarContratoDTO());
        cessaoGratuitaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_GRATUITA);
        cessaoGratuitaDTO.setUtilizacao(criarUtilizacaoDTO());
        cessaoGratuitaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cessaoGratuitaDTO);
        mockMvc.perform(post(URL_BASE + "/cessaoGratuita")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCessaoGratuitaComDuasDestinacoesImovel() {
        DestinacaoImovelDTO destinacao1 = criarListaDestinacaoImovelDTOParcela().get(0);
        DestinacaoImovelDTO destinacao2 = criarListaDestinacaoImovelDTOParcela().get(0);
        List<DestinacaoImovelDTO> listaDestinacoesImovel = new ArrayList<>();
        listaDestinacoesImovel.add(destinacao1);
        listaDestinacoesImovel.add(destinacao2);
        CessaoGratuitaDTO cessaoGratuitaDTO = new CessaoGratuitaDTO();
        cessaoGratuitaDTO.setNumeroAtendimento("DF00001/2017");
        cessaoGratuitaDTO.setNumeroProcesso("00000001");
        cessaoGratuitaDTO.setDestinacaoImoveis(listaDestinacoesImovel);
        cessaoGratuitaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cessaoGratuitaDTO.setDocumentos(criaDocumentoDTO());
        cessaoGratuitaDTO.setCodFundamentoLegal(1L);
        cessaoGratuitaDTO.setContrato(criarContratoDTO());
        cessaoGratuitaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_GRATUITA);
        cessaoGratuitaDTO.setUtilizacao(criarUtilizacaoDTO());
        cessaoGratuitaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cessaoGratuitaDTO);
        mockMvc.perform(post(URL_BASE + "/cessaoGratuita")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCessaoGratuitaComParcelaEFracaoIdeal() {
        List<DestinacaoImovelDTO> destinacaoImovelDTOs = criarListaDestinacaoImovelDTOParcela();
        destinacaoImovelDTOs.get(0).setParcelas(criarParcelas());
        destinacaoImovelDTOs.get(0).setFracaoIdeal(BigDecimal.valueOf(100));
        destinacaoImovelDTOs.get(0).setId(3L);
        CessaoGratuitaDTO cessaoGratuitaDTO = new CessaoGratuitaDTO();
        cessaoGratuitaDTO.setNumeroAtendimento("DF00001/2017");
        cessaoGratuitaDTO.setNumeroProcesso("00000001");
        cessaoGratuitaDTO.setDestinacaoImoveis(destinacaoImovelDTOs);
        cessaoGratuitaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cessaoGratuitaDTO.setDocumentos(criaDocumentoDTO());
        cessaoGratuitaDTO.setCodFundamentoLegal(1L);
        cessaoGratuitaDTO.setContrato(criarContratoDTO());
        cessaoGratuitaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_GRATUITA);
        cessaoGratuitaDTO.setUtilizacao(criarUtilizacaoDTO());
        cessaoGratuitaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cessaoGratuitaDTO);
        mockMvc.perform(post(URL_BASE + "/cessaoGratuita")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(64));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCessaoGratuitaComParcelaSemFracaoIdeal() {
        List<DestinacaoImovelDTO> destinacaoImovelDTOs = criarListaDestinacaoImovelDTOParcela();
        destinacaoImovelDTOs.get(0).setFracaoIdeal(null);
        destinacaoImovelDTOs.get(0).setParcelas(criarParcelas());
        CessaoGratuitaDTO cessaoGratuitaDTO = new CessaoGratuitaDTO();
        cessaoGratuitaDTO.setNumeroAtendimento("DF00001/2017");
        cessaoGratuitaDTO.setNumeroProcesso("00000001");
        cessaoGratuitaDTO.setDestinacaoImoveis(destinacaoImovelDTOs);
        cessaoGratuitaDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        cessaoGratuitaDTO.setDocumentos(criaDocumentoDTO());
        cessaoGratuitaDTO.setCodFundamentoLegal(1L);
        cessaoGratuitaDTO.setContrato(criarContratoDTO());
        cessaoGratuitaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_GRATUITA);
        cessaoGratuitaDTO.setUtilizacao(criarUtilizacaoDTO());
        cessaoGratuitaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cessaoGratuitaDTO);
        mockMvc.perform(post(URL_BASE + "/cessaoGratuita")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensagens[0]").value("Destinação salva com sucesso"));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCessaoGratuitaResponsavelNull() {
        CessaoGratuitaDTO cessaoGratuitaDTO = new CessaoGratuitaDTO();
        cessaoGratuitaDTO.setNumeroAtendimento("DF00001/2017");
        cessaoGratuitaDTO.setNumeroProcesso("00000001");
        cessaoGratuitaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTOParcela());
        cessaoGratuitaDTO.getDadosResponsavel().setResponsaveis(null);
        cessaoGratuitaDTO.setDocumentos(criaDocumentoDTO());
        cessaoGratuitaDTO.setCodFundamentoLegal(null);
        cessaoGratuitaDTO.setContrato(criarContratoDTO());
        cessaoGratuitaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_GRATUITA);
        cessaoGratuitaDTO.setUtilizacao(criarUtilizacaoDTO());
        cessaoGratuitaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cessaoGratuitaDTO);
        mockMvc.perform(post(URL_BASE + "/cessaoGratuita")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void salvarCessaoGratuitaResponsavelVazio() {
        CessaoGratuitaDTO cessaoGratuitaDTO = new CessaoGratuitaDTO();
        cessaoGratuitaDTO.setNumeroAtendimento("DF00001/2017");
        cessaoGratuitaDTO.setNumeroProcesso("00000001");
        cessaoGratuitaDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTOParcela());
        cessaoGratuitaDTO.getDadosResponsavel().setResponsaveis(Collections.emptyList());
        cessaoGratuitaDTO.setDocumentos(criaDocumentoDTO());
        cessaoGratuitaDTO.setCodFundamentoLegal(null);
        cessaoGratuitaDTO.setContrato(criarContratoDTO());
        cessaoGratuitaDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_GRATUITA);
        cessaoGratuitaDTO.setUtilizacao(criarUtilizacaoDTO());
        cessaoGratuitaDTO.setStatusDestinacao(DominioDTOBuilder.getBuilder().setId(1).build());

        String requestJson = toJson(cessaoGratuitaDTO);
        mockMvc.perform(post(URL_BASE + "/cessaoGratuita")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @SneakyThrows
    public void salvarSemUtilizacaoBenfeitorias() {
        ImovelDTO imovelDTO = criarImovelDTO(false);
        imovelDTO.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.BENFEITORIA);
        imovelDTO.setIndicadorOperacao(TipoOperacaoEnum.CADASTRO);
        imovelDTO.setBenfeitorias(criarListaBenfeitoria());
        imovelDTO.setIdCadastroImovel(25L);
        imovelDTO.setId(null);
        String requestJson = toJson(imovelDTO);
        mockMvc.perform(post(URL_BASE + "/sem-utilizacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @SneakyThrows
    public void salvarSemUtilizacaoBenfeitoriasGerandoErro() {
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setIndicadorOperacao(TipoOperacaoEnum.CADASTRO);
        String requestJson = toJson(imovelDTO);
        mockMvc.perform(post(URL_BASE + "/sem-utilizacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarSemUtilizacaoPorId() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000005");
        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder()
                .setLonLat(100.0, 100.0)
                .setImagem("base64").build();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "6/SEM_UTILIZACAO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(6));
    }

    @Test
    @SneakyThrows
    public void salvarSemUtilizacaoAtualizar() {
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setAreaConstruida(new BigDecimal(100));
        imovelDTO.setAreaTerreno(100.0);
        imovelDTO.setImagens(new ArrayList<>());
        imovelDTO.setMemorialDescritivo("teste");
        imovelDTO.setIdCadastroImovel(20L);
        imovelDTO.setRip("00000020");
        imovelDTO.setIndicadorOperacao(TipoOperacaoEnum.EDICAO);

        imovelDTO.setEndereco(criarEnderecoImovelDestSemUtilizacao());
        imovelDTO.setBenfeitorias(asList(criarBenfeitoriaImovelDestSemUtilizacao(20L, BigDecimal.valueOf(1)),
                criarBenfeitoriaImovelDestSemUtilizacao(21L, BigDecimal.valueOf(1)),
                criarBenfeitoriaImovelDestSemUtilizacao(10L, BigDecimal.valueOf(100)),
                criarBenfeitoriaImovelDestSemUtilizacao(22L, BigDecimal.valueOf(10))));

        String requestJson = toJson(imovelDTO);
        mockMvc.perform(post(URL_BASE + "/sem-utilizacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    public void salvarSemUtilizacaoAtualizarSemBenfeitorias() {
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setAreaConstruida(new BigDecimal(51));
        imovelDTO.setAreaTerreno(51.0);
        imovelDTO.setImagens(new ArrayList<>());
        imovelDTO.setMemorialDescritivo("teste");
        imovelDTO.setIdCadastroImovel(5L);
        imovelDTO.setRip("00000005");
        imovelDTO.setIndicadorOperacao(TipoOperacaoEnum.EDICAO);

        imovelDTO.setEndereco(criarEnderecoImovelDestSemUtilizacao());

        String requestJson = toJson(imovelDTO);
        mockMvc.perform(post(URL_BASE + "/sem-utilizacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    public void consultarDestinacaoComResultado() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000020");
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setLatitude(5002.0);
        imovelDTO.setLongitude(8500.0);

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8)
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8)
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultar?rip=00000020&offset=0&limit=10")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty());
    }

    @Test
    @SneakyThrows
    public void consultarDestinacaoSemResultado() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000021");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(url), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultar?rip=00000021&offset=0&limit=10")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarDestinacaoGerandoErro() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000020");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(url), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultar?rip=00000020&offset=0&limit=1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Test
    @SneakyThrows
    public void consultarDestinacaoComImagens() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000020");
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setImagens(criaListaImagens());

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8)
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8)
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultar?rip=00000020&offset=0&limit=10")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void consultarDestinacaoComContrato() {
        String URL_ID23 = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000011");
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setImagens(criaListaImagens());

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(URL_ID23, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultar?uf=RJ&offset=0&limit=1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @SneakyThrows
    public void consultarDestinacaoImovelSemImagem() {
        String URL_VALOR_23 = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000023");
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setImagens(Collections.emptyList());

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(URL_VALOR_23, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8)
                .mockServe(URL_VALOR_23, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8)
                .mockServe(URL_VALOR_23, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "/consultar?rip=00000023&offset=0&limit=10")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void salvarSemUtilizacaoComUnidadeAutonoma() {
        String requestJson = toJson(criarImovelDTOComUnidadeAutonoma());

        mockMvc.perform(post(URL_BASE + "/sem-utilizacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    public void salvarSemUtilizacaoComUnidadeAutonomaAtualizar() {
        ImovelDTO imovelDTO = criarImovelDTOComUnidadeAutonoma();
        imovelDTO.setIndicadorOperacao(TipoOperacaoEnum.EDICAO);
        imovelDTO.setId(4L);
        imovelDTO.setRip("00000008");
        imovelDTO.setIdCadastroImovel(20L);

        String requestJson = toJson(imovelDTO);

        mockMvc.perform(post(URL_BASE + "/sem-utilizacao")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void cancelarSemUtilizacao() {
        mockMvc.perform(delete(URL_BASE + "/cancelar/00000010")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    public void cancelarSemUtilizacaoImovelDestinado() {
        mockMvc.perform(delete(URL_BASE + "/cancelar/00000009")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensagens").value("Imovel ja esta destinado"));
    }

    @Test
    @SneakyThrows
    public void cancelarSemUtilizacaoGerandoErro() {
        mockMvc.perform(delete(URL_BASE + "/cancelar/000000010")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @SneakyThrows
    public void buscaPendenciasDeDestinacao() {
        mockMvc.perform(get(URL_BASE + "/pendencias")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void consultarCidades() {
        mockMvc.perform(get(URL_BASE + "consultarCidades/Alemanha/000000120000P0000")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void consultarCidadesSemresultado() {
        mockMvc.perform(get(URL_BASE + "consultarCidades/Alemanha/000000120000P1234")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty());
    }

    @Ignore
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @SneakyThrows
    public void salvarUsoProprioCamposPreenchidos() {
        UsoProprioDTO usoProprioDTO = new UsoProprioDTO();
        usoProprioDTO.setNumeroAtendimento("DF00001/2017");
        usoProprioDTO.setNumeroProcesso("00000001");
        usoProprioDTO.setContrato(criarContratoDTO());
        usoProprioDTO.setEncargos(criarListaEncargos());
        usoProprioDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.USO_PROPRIO);
        usoProprioDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        usoProprioDTO.setDocumentos(criaDocumentoDTO());
        usoProprioDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        usoProprioDTO.setUtilizacao(criarUtilizacaoDTO());
        usoProprioDTO.setCodFundamentoLegal(1L);
        usoProprioDTO.setFotos(criarFotos(1L));
        usoProprioDTO.setHomologado(false);

        String requestJson = toJson(usoProprioDTO);
        mockMvc.perform(post(URL_BASE + "usoProprio")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(63));
    }

    @Ignore
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @SneakyThrows
    public void SalvarPermissaoUsoFuncionalCamposPreenchido(){
        PermissaoUsoImovelFuncionalDTO permissaoUsoImovelFuncionalDTO = new PermissaoUsoImovelFuncionalDTO();
        permissaoUsoImovelFuncionalDTO.setNumeroAtendimento("DF00001/2017");
        permissaoUsoImovelFuncionalDTO.setNumeroProcesso("00000001");
        permissaoUsoImovelFuncionalDTO.setContrato(criarContratoDTO());
        permissaoUsoImovelFuncionalDTO.setEncargos(criarListaEncargos());
        permissaoUsoImovelFuncionalDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.PERMISSAO_USO_IMOVEL_FUNCIONAL);
        permissaoUsoImovelFuncionalDTO.setCodFundamentoLegal(1L);
        permissaoUsoImovelFuncionalDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        permissaoUsoImovelFuncionalDTO.setFotos(criarFotos());
        permissaoUsoImovelFuncionalDTO.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        permissaoUsoImovelFuncionalDTO.setUtilizacao(criarUtilizacaoDTO());

        String requestJson = toJson(permissaoUsoImovelFuncionalDTO);
        mockMvc.perform(post(URL_BASE + "permissaoUsoImovelFuncional")
               .contentType(contentType)
               .content(requestJson))
               .andDo(print())
                .andExpect(status().isInternalServerError());



    }

    @Ignore
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @SneakyThrows
    public void salvarUsoProprioCamposComErro() {
        UsoProprioDTO usoProprioDTO = new UsoProprioDTO();
        usoProprioDTO.setNumeroAtendimento("DF00001/2017");
        usoProprioDTO.setNumeroProcesso("00000001");
        usoProprioDTO.setContrato(criarContratoDTO());
        usoProprioDTO.setEncargos(criarListaEncargos());
        usoProprioDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.USO_PROPRIO);
        usoProprioDTO.setTipoDestinacao(criarTipoDestinacaoDTO());
        usoProprioDTO.setDocumentos(criaDocumentoDTO());
        usoProprioDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        usoProprioDTO.setUtilizacao(criarUtilizacaoDTO());
        usoProprioDTO.setCodFundamentoLegal(1L);
        usoProprioDTO.setHomologado(false);

        String requestJson = toJson(usoProprioDTO);
        mockMvc.perform(post(URL_BASE + "/usoProprio")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarUsoProprioPorId() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000007");
        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder().setLonLat(1.0, 1.0).build();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "62/USO_PROPRIO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void consultarUsoPropioPorIdComErro() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000023");
        ImovelDTO imovelDTO = new ImovelDTO();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockMvc.perform(get(URL_BASE + "1/USO_PROPRIO")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void homologarUsoProprioComTodosCamposPreenchidos() {
        UsoProprioDTO usoProprioDTO = this.criarUsoProprioDTO();
        String requestJson = toJson(usoProprioDTO);
        mockMvc.perform(post(URL_BASE + "/homologarUsoProprio")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(62))
                .andExpect(jsonPath("$.resultado.pendencias[*].id").value(Matchers.not(USO_PROPRIO_PENDENTE)));
    }

    @Ignore
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @SneakyThrows
    public void recusarUsoProprioComTodosCamposPreenchidos() {
        UsoProprioDTO usoProprioDTO = this.criarUsoProprioDTO();
        String requestJson = toJson(usoProprioDTO);
        mockMvc.perform(post(URL_BASE + "/recusarUsoProprio")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(62));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarTermoEntregaPorId() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000027");
        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder()
                .setLonLat(100.0, 100.0)
                .setImagem("base64").build();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        String pathfile = getClass().getResource("/arquivos/teste.jpeg").getFile().replace("%20", " ");
        String newPathFile = pathfile.replace("teste.jpeg", "");
        atualizaPathArquivoH2(newPathFile, "teste.jpeg");

        mockMvc.perform(get(URL_BASE + "27/TERMO_ENTREGA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(27));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarTermoEntregaPorIdSemFotoVideo() {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("00000028");
        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder()
                .setLonLat(100.0, 100.0)
                .setImagem("base64").build();

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(get(URL_BASE + "28/TERMO_ENTREGA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado.id").value(28));
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarTermoEntregaPorIdErroIntegracaoIncorporacao() {

        mockMvc.perform(get(URL_BASE + "27/TERMO_ENTREGA")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isInternalServerError());

    }

    @SneakyThrows
    private void atualizaPathArquivoH2(String path, String name) {
        Class.forName(environment.getProperty("spring.datasource.driverClassName"));
        String url = environment.getProperty("spring.datasource.url");
        String user = environment.getProperty("spring.datasource.username");
        String pwd = environment.getProperty("spring.datasource.password");
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String query = "UPDATE destinacao.tb_arquivo as a SET a.dir_arquivo = '" + path + "', a.ds_nome = '" + name + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);

        statement.close();
        connection.close();
    }

    private List<ImagemDTO> criaListaImagens() {
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setId(1L);
        imagemDTO.setImagem("teste");
        imagemDTO.setNomeImagem("teste.jpg");
        return Arrays.asList(imagemDTO);
    }

    private PendenciaDTO criarPendenciaDTO() {
        Date date = new Date(2017 / 07 / 17);
        PendenciaDTO pendenciaDTO = new PendenciaDTO();
        pendenciaDTO.setId(1L);
        pendenciaDTO.setDataGerada(date);
        pendenciaDTO.setDescricao("teste");
        return pendenciaDTO;
    }

    private BenfeitoriaDTO criarBenfeitoriaImovelDestSemUtilizacao(Long idCadImovel, BigDecimal areaConstruida) {
        BenfeitoriaDTO benfeitoriaDTO = new BenfeitoriaDTO();
        benfeitoriaDTO.setIdBenfeitoriaCadImovel(idCadImovel);
        benfeitoriaDTO.setCodigo("E1");
        benfeitoriaDTO.setAtiva(true);
        benfeitoriaDTO.setAreaConstruida(areaConstruida);
        benfeitoriaDTO.setNome("teste");
        benfeitoriaDTO.setEspecializacao("tst");
        return benfeitoriaDTO;
    }

    private EnderecoDTO criarEnderecoImovelDestSemUtilizacao() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setTipoLogradouro("Rua");
        enderecoDTO.setPais("Brasil");
        enderecoDTO.setUf("MG");
        enderecoDTO.setCep("38703750");
        enderecoDTO.setMunicipio("Patos de Minas");
        enderecoDTO.setBairro("Residencial Monjolo");
        return enderecoDTO;
    }

    private ArquivoDTO criarArquivoDTO() {
        ArquivoDTO arquivoDTO = new ArquivoDTO();
        arquivoDTO.setId(1L);
        arquivoDTO.setNome("teste.pdf");
        arquivoDTO.setContentType("application/pdf");
        //    arquivoDTO.setBinario("teste".getBytes());
        return arquivoDTO;
    }

    private Arquivo criarArquivo() {
        Arquivo arquivo = new Arquivo();
        arquivo.setId(1L);
        arquivo.setNome("teste.pdf");
        arquivo.setContentType("application/pdf");
        //    arquivo.setBinario("teste".getBytes());
        return arquivo;
    }

    private ArquivoDTO criarArquivoFotoDTO() {
        ArquivoDTO arquivo = new ArquivoDTO();
        arquivo.setId(1L);
        arquivo.setNome("teste.png");
        arquivo.setContentType("image/png");
        return arquivo;
    }

    private ContratoDTO criarContratoDTO() {
        ContratoDTO contratoDTO = new ContratoDTO();
        contratoDTO.setArquivo(criarArquivoDTO());
        contratoDTO.setDataInicio(new Date());
        contratoDTO.setNumero("1");
        return contratoDTO;

    }

    private List<DocumentoDTO> criaDocumentoDTO() {
        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setTipoDocumento(criarTipoDocumento());
        documentoDTO.setSubTipoDocumento(criarSubTipoDocumento());
        documentoDTO.setDataFinalVigencia(new Date());
        documentoDTO.setDataInicialVigencia(new Date());
        documentoDTO.setArquivo(criarArquivoDTO());
        documentoDTO.setFolha("2");
        documentoDTO.setPagina("3");
        documentoDTO.setLivro("5");
        return Arrays.asList(documentoDTO);
    }

    private TipoInstrumentoDTO criarTipoInstrumento() {
        TipoInstrumentoDTO tipoInstrumentoDTO = new TipoInstrumentoDTO();
        tipoInstrumentoDTO.setDescricao("Doação");
        tipoInstrumentoDTO.setId(2L);
        return tipoInstrumentoDTO;
    }



    private List<EncargoDTO> criarListaEncargos() {
        EncargoDTO encargoDTO = new EncargoDTO();
        encargoDTO.setNome("Teste");
        encargoDTO.setCumprimentoEncargo(Boolean.TRUE);
        return asList(encargoDTO);
    }

    private TipoDestinacaoDTO criarTipoDestinacaoDTO() {
        TipoDestinacaoDTO tipoDestinacaoDTO = new TipoDestinacaoDTO();
        tipoDestinacaoDTO.setDescricao("Doação");
        tipoDestinacaoDTO.setId(1L);
        return tipoDestinacaoDTO;
    }

    private List<DestinacaoImovelDTO> criarListaDestinacaoImovelDTO(Boolean imovelExterior) {
        DestinacaoImovelDTO destinacaoImovelDTO = new DestinacaoImovelDTO();
        destinacaoImovelDTO.setImovel(criarImovelDTO(imovelExterior));
        return asList(destinacaoImovelDTO);
    }

    private List<DestinacaoImovelDTO> criarListaDestinacaoImovelDTOParcela() {
        DestinacaoImovelDTO destinacaoImovelDTO = new DestinacaoImovelDTO();
        destinacaoImovelDTO.setImovel(criarImovelDTOParcela());
        destinacaoImovelDTO.setDocumentos(asList(criarArquivoDTO()));
        return asList(destinacaoImovelDTO);
    }

    private FamiliaBeneficiadaDTO criarFamiliaBeneficiada() {
        FamiliaBeneficiadaDTO familiaBeneficiada = new FamiliaBeneficiadaDTO();
        familiaBeneficiada.setAreaUtilizada(1d);
        familiaBeneficiada.setCpfConjuge("03247831169");
        familiaBeneficiada.setCpfResponsavel("03247831169");
        familiaBeneficiada.setId(1l);
        familiaBeneficiada.setAreaUtilizada(1d);
        familiaBeneficiada.setNomeConjuge("Teste");
        familiaBeneficiada.setSequencial(1);
        familiaBeneficiada.setCpfResponsavel("03247831169");
        familiaBeneficiada.setNomeResponsavel("teste");
        return familiaBeneficiada;
    }

    private List<FamiliaBeneficiadaDTO> criarListaFamiliaBeneficiadaDTO() {
        FamiliaBeneficiadaDTO familiaBeneficiada = criarFamiliaBeneficiada();
        return asList(familiaBeneficiada);
    }

    private ImovelDTO criarImovelDTO(Boolean imovelExterior) {
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setAreaConstruida(new BigDecimal(100));
        imovelDTO.setImagens(new ArrayList<>());
        imovelDTO.setLatitude(1.00);
        imovelDTO.setLongitude(1.00);
        imovelDTO.setMemorialDescritivo("teste");
        imovelDTO.setAreaTerreno(3.00);
        imovelDTO.setNumeroProcesso("1234");
        imovelDTO.setIdCadastroImovel(19L);
        imovelDTO.setId(3L);
        imovelDTO.setRip("0000007");
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setTipoLogradouro("Rua");
        enderecoDTO.setPais("Brasil");
        enderecoDTO.setUf("DF");

        if (imovelExterior) {
            enderecoDTO.setCidadeExterior("teste");
        }

        imovelDTO.setEndereco(enderecoDTO);
        imovelDTO.setParcelas(criarParcelas());
        return imovelDTO;
    }

    private List<ParcelaDTO> criarParcelas() {
        List<ParcelaDTO> parcelaDTOs = new ArrayList<>();
        Set<Long> idsDestinacaoImoveis = new HashSet<>();
        idsDestinacaoImoveis.add(3L);
        ParcelaDTO parcelaDTO = new ParcelaDTO();
        Imovel imovel = new Imovel();
        imovel.setId(3L);
        parcelaDTO.setId(1L);
        parcelaDTO.setIdDestinacaoImoveis(idsDestinacaoImoveis);
        parcelaDTOs.add(parcelaDTO);
        return parcelaDTOs;
    }

    private ImovelDTO criarImovelDTOTeste() {
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setAreaConstruida(new BigDecimal(100));
        imovelDTO.setImagens(new ArrayList<>());
        imovelDTO.setLatitude(1.00);
        imovelDTO.setLongitude(1.00);
        imovelDTO.setMemorialDescritivo("teste");
        imovelDTO.setAreaTerreno(3.00);
        imovelDTO.setNumeroProcesso("1234");
        imovelDTO.setIdCadastroImovel(19L);
        imovelDTO.setId(1L);
        imovelDTO.setRip("000009");
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setTipoLogradouro("Rua");
        enderecoDTO.setPais("Brasil");
        enderecoDTO.setUf("DF");
        imovelDTO.setEndereco(enderecoDTO);
        return imovelDTO;
    }

    private ImovelDTO criarImovelDTOComUnidadeAutonoma() {
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setAreaConstruida(new BigDecimal(100));
        imovelDTO.setImagens(new ArrayList<>());
        imovelDTO.setLatitude(1.00);
        imovelDTO.setLongitude(1.00);
        imovelDTO.setMemorialDescritivo("teste");
        imovelDTO.setAreaTerreno(3.00);
        imovelDTO.setNumeroProcesso("1234");
        imovelDTO.setIdCadastroImovel(19L);
        imovelDTO.setRip("000007");
        imovelDTO.setUnidadeAutonoma(criarUnidadeAutonoma());
        imovelDTO.getUnidadeAutonoma().setId(null);
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setTipoLogradouro("Rua");
        enderecoDTO.setPais("Brasil");
        enderecoDTO.setUf("DF");
        imovelDTO.setIndicadorOperacao(TipoOperacaoEnum.CADASTRO);
        imovelDTO.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.UNIDADE);
        imovelDTO.setEndereco(enderecoDTO);
        imovelDTO.setCoditoTipoProprietario(1L);
        imovelDTO.setCodigoSituacaoIncorporacao(1L);
        imovelDTO.setCodigoNaturezaImovel(1L);
        imovelDTO.setIdCadastroImovel(26L);
        imovelDTO.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.UNIDADE);

        return imovelDTO;
    }

    private UnidadeAutonomaDTO criarUnidadeAutonoma() {
        UnidadeAutonomaDTO unidadeAutonoma = new UnidadeAutonomaDTO();
        unidadeAutonoma.setId(1l);
        unidadeAutonoma.setArea(BigDecimal.TEN);
        unidadeAutonoma.setAreaDisponivel(BigDecimal.TEN);
        unidadeAutonoma.setIdUnidadeAutonomaCadImovel(33L);
        return unidadeAutonoma;
    }

    private List<BenfeitoriaDestinadaDTO> criarListaBenfeitoriasDestinada() {
        BenfeitoriaDestinadaDTO benfeitoriaDestinada = new BenfeitoriaDestinadaDTO();
        benfeitoriaDestinada.setId(1l);
        benfeitoriaDestinada.setAreaUtilizar(BigDecimal.valueOf(1));
        benfeitoriaDestinada.setIdBenfeitoria(1l);

        return asList(benfeitoriaDestinada);
    }

    private List<DestinacaoImovelDTO> criarDestinacaoImovel() {
        DestinacaoImovelDTO destinacaoImovel = new DestinacaoImovelDTO();
        destinacaoImovel.setId(1L);
        destinacaoImovel.setAreaTerrenoDestinada(BigDecimal.valueOf(1));
        destinacaoImovel.setBenfeitoriasDestinadas(criarListaBenfeitoriasDestinada());
        destinacaoImovel.setCodigoUtilizacao("1");
        destinacaoImovel.setFracaoIdeal(BigDecimal.TEN);
        destinacaoImovel.setImovel(criarImovelDTO(false));
        destinacaoImovel.setMemorialDescAreaConstruida("teste");
        destinacaoImovel.setDocumentos(asList(criarArquivoDTO()));

        return asList(destinacaoImovel);
    }

    private List<DestinacaoImovelDTO> criarDestinacaoImovelId62() {
        DestinacaoImovelDTO destinacaoImovel = new DestinacaoImovelDTO();
        destinacaoImovel.setId(62L);
        destinacaoImovel.setAreaTerrenoDestinada(BigDecimal.valueOf(1));

        List<BenfeitoriaDestinadaDTO> listaBenfeitoriaDestinadaDTO = BenfeitoriaDestinadaDTOBuilder.getBuilder().setId(4L)
                .setAreaUtilizar(BigDecimal.TEN).setIdBenfeitoria(13L).buildList();
        destinacaoImovel.setBenfeitoriasDestinadas(listaBenfeitoriaDestinadaDTO);

        destinacaoImovel.setCodigoUtilizacao("1");
        destinacaoImovel.setFracaoIdeal(BigDecimal.TEN);

        EnderecoDTO enderecoDTO = EnderecoDTOBuilder.getBuilder()
                .setId(12L)
                .setCep("32321321")
                .setTipoLogradouro("Rua")
                .setLogradouro("PRINCIPAL")
                .setBairro("Bairro Central")
                .setMunicipio("Natal")
                .setUf("RN")
                .setPais("Brasil")
                .setCep("12123123").build();

        ImovelDTO imovelDTO = ImovelDTOBuilder.getBuilder()
                .setId(3L)
                .setAreaConstruida(BigDecimal.TEN)
                .setPlantas(Arrays.asList(this.criarArquivoDTO()))
                .setBenfeitoriasDestinadas(this.criarListaBenfeitoriasDestinadaDTO())
                .setAreaTerrenoDestinada(BigDecimal.TEN)
                .setEndereco(enderecoDTO)
                .setRip("00000007")
                .build();

        destinacaoImovel.setImovel(imovelDTO);

        destinacaoImovel.setMemorialDescAreaConstruida("teste");
        destinacaoImovel.setDocumentos(asList(criarArquivoDTO()));

        return asList(destinacaoImovel);
    }

    private DestinacaoDTO criarDestinacaoDTO() {
        DestinacaoDTO destinacao = new DestinacaoDTO();
        destinacao.setId(1l);
        destinacao.getDadosResponsavel().setResponsaveis(criarListaResponsaveisPessoaFisica());
        destinacao.setEncargos(criarListaEncargos());
        destinacao.setFinanceiro(criarFinanceiroDTOVenda());
        destinacao.setCodFundamentoLegal(1L);
        destinacao.setLicitacao(criarLicitacaoDTO());
        destinacao.setNumeroAtendimento("DF0001/2016");
        destinacao.setUtilizacao(criarUtilizacaoDTO());

        return destinacao;
    }

    private ImovelDTO criarImovelDTOParcela() {
        ImovelDTO imovelDTO = criarImovelDTO(false);
        imovelDTO.setAreaConstruida(new BigDecimal(100));
        imovelDTO.setAreaTerrenoDestinada(new BigDecimal(20));
        imovelDTO.setFracaoIdeal(true);
        imovelDTO.setBenfeitoriasDestinadas(criarListaBenfeitoriasDestinadaDTO());
        imovelDTO.setPlantas(asList(criarArquivoDTO()));
        return imovelDTO;
    }

    private List<BenfeitoriaDestinadaDTO> criarListaBenfeitoriasDestinadaDTO() {
        BenfeitoriaDestinadaDTO benfeitoriaDestinadaDTO = new BenfeitoriaDestinadaDTO();
        benfeitoriaDestinadaDTO.setAreaUtilizar(new BigDecimal(30));
        benfeitoriaDestinadaDTO.setIdBenfeitoria(1L);
        return asList(benfeitoriaDestinadaDTO);
    }

    private UtilizacaoDTO criarUtilizacaoDTO() {
        UtilizacaoDTO utilizacaoDTO = new UtilizacaoDTO();
        utilizacaoDTO.setAreaServidor(1.0);
        utilizacaoDTO.setFinalidade("teste");
        utilizacaoDTO.setNumeroFamiliasBeneficiadas(1);
        utilizacaoDTO.setNumeroServidores(1);
        return utilizacaoDTO;
    }

    private List<ResponsavelDTO> criarListaResponsaveisPessoaFisica() {
        ResponsavelDTO responsavelDTO = new ResponsavelDTO();
        responsavelDTO.setCpfCnpj("00000000191");
        return asList(responsavelDTO);
    }

    private LicitacaoDTO criarLicitacaoDTO() {
        LicitacaoDTO licitacaoDTO = new LicitacaoDTO();
        licitacaoDTO.setTipoLicitacao(criarTipoLicitacao());
        licitacaoDTO.setNumeroProcesso("000001");
        licitacaoDTO.setArquivos(Arrays.asList(criarArquivo()));
        return licitacaoDTO;
    }

    private DominioDTO criarTipoLicitacao() {
        DominioDTO dominioDTO = new DominioDTO();
        dominioDTO.setId(1);
        dominioDTO.setDescricao("Dispensa");
        return dominioDTO;
    }

    private DominioDTO criarTipoDocumento() {
        DominioDTO dominioDTO = new DominioDTO();
        dominioDTO.setId(1);
        dominioDTO.setDescricao("Extrato");
        return dominioDTO;
    }

    private SubTipoDocumentoDTO criarSubTipoDocumento() {
        SubTipoDocumentoDTO subTipoDocumentoDTO = new SubTipoDocumentoDTO();
        subTipoDocumentoDTO.setId(1);
        subTipoDocumentoDTO.setTipoDocumento(criarTipoDocumento());
        subTipoDocumentoDTO.setDescricao("Licitação");
        return subTipoDocumentoDTO;
    }

    private FinanceiroDTO criarFinanceiroDTOVenda() {
        FinanceiroDTO financeiroDTO = new FinanceiroDTO();
        financeiroDTO.setTipoPagamento(criarTipoPagamento(1));
        financeiroDTO.setValor(new BigDecimal(100000));
        return financeiroDTO;
    }

    private TipoPagamento criarTipoPagamento(Integer id) {
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setId(id);
        tipoPagamento.setDescricao("A vista");
        return tipoPagamento;
    }

    private TipoPosseDTO criarTipoPosseDTO(Integer id) {
        TipoPosseDTO tipoPosseDTO = new TipoPosseDTO();
        tipoPosseDTO.setId(id);
        tipoPosseDTO.setDescricao("Individual");
        return tipoPosseDTO;
    }

    private List<OcupanteDTO> criarListaOcupanteDTO() {
        OcupanteDTO ocupanteDTO = new OcupanteDTO();
        ocupanteDTO.setAreaUtilizada(new BigDecimal(1));
        ocupanteDTO.setCpfCnpj("00000000191");
        ocupanteDTO.setNomeRazaoSocial("teste");
        return asList(ocupanteDTO);
    }

    private TipoModalidadeDTO criarTipoModalidadeDTO() {
        TipoModalidadeDTO tipoModalidadeDTO = new TipoModalidadeDTO();
        tipoModalidadeDTO.setId(1);
        tipoModalidadeDTO.setDescricao("Individual");
        return tipoModalidadeDTO;
    }

    private TipoConcessaoDTO criarTipoConcessaoDTO() {
        TipoConcessaoDTO tipoConcessaoDTO = new TipoConcessaoDTO();
        tipoConcessaoDTO.setId(1L);
        tipoConcessaoDTO.setDescricao("Gratuito");
        return tipoConcessaoDTO;
    }

    private List<BenfeitoriaDTO> criarListaBenfeitoria() {
        BenfeitoriaDTO benfeitoriaDTO = new BenfeitoriaDTO();
        benfeitoriaDTO.setAreaDisponivel(BigDecimal.TEN);
        benfeitoriaDTO.setIdBenfeitoriaCadImovel(1L);
        benfeitoriaDTO.setAtiva(Boolean.TRUE);
        benfeitoriaDTO.setNome("teste");
        benfeitoriaDTO.setEspecializacao("abc");
        return asList(benfeitoriaDTO);
    }

    private TipoJuro criarTipoJuro(Integer id) {
        TipoJuro tipoJuro = new TipoJuro();
        tipoJuro.setId(id);
        return tipoJuro;
    }

    private TipoMoeda criarTipoMoeda(Integer id) {
        TipoMoeda tipoMoeda = new TipoMoeda();
        tipoMoeda.setId(id);
        return tipoMoeda;
    }

    private TipoReajuste criarTipoReajuste(Integer id) {
        TipoReajuste tipoReajuste = new TipoReajuste();
        tipoReajuste.setId(id);
        return tipoReajuste;
    }

    private Date criarDataCobranca(int dia, int mes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes);
        return calendar.getTime();
    }

    private FotoDTO criarFotoDTO() {
        FotoDTO foto = new FotoDTO();
        foto.setId(1L);
        foto.setArquivo(criarArquivoFotoDTO());
        return foto;
    }

    private DocumentoArquivoDTO criarDocumentoArquivoDTO() {
        DocumentoArquivoDTO documentoArquivoDTO = new DocumentoArquivoDTO();
        documentoArquivoDTO.setId(1L);
        documentoArquivoDTO.setArquivo(criarArquivoDTO());
        return documentoArquivoDTO;
    }

    private List<FotoDTO> criarFotos(Long... ids) {
        List<FotoDTO> fotos = new ArrayList<>();
        for (Long id : ids) {
            FotoDTO foto = new FotoDTO();
            foto.setId(id);
            foto.setArquivo(this.criarArquivoDTO());
            fotos.add(foto);
        }
        return fotos;
    }

    private List<DocumentoArquivoDTO> criarDocumentoArquivoDTO(Long... ids) {
        List<DocumentoArquivoDTO> documentos = new ArrayList<>();
        for (Long id : ids) {
            DocumentoArquivoDTO documentoArquivoDTO = new DocumentoArquivoDTO();
            documentoArquivoDTO.setId(id);
            documentos.add(documentoArquivoDTO);
        }
        return documentos;
    }

    private List<DocumentoDTO> criarListaDocumentoDTO() {
        List<DocumentoDTO> documentoDTOs = new ArrayList<>();
        documentoDTOs.add(this.criarDocumentoDTO());
        return documentoDTOs;
    }

    private DocumentoDTO criarDocumentoDTO() {
        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setArquivo(this.criarArquivoDTO());
        documentoDTO.setDataFinalVigencia(this.criarDataMesSeguite().getTime());
        documentoDTO.setDataInicialVigencia(this.criarDataHoje().getTime());
        documentoDTO.setDataPublicacao(this.criarDataHoje().getTime());
        documentoDTO.setDispensado(true);
        documentoDTO.setEspecificar("");
        documentoDTO.setFolha("");
        documentoDTO.setId(1L);
        documentoDTO.setJustificativa("");
        documentoDTO.setLivro("");
        documentoDTO.setNumeroTermo("");
        documentoDTO.setPagina("");
        documentoDTO.setSecao("");
        documentoDTO.setSubTipoDocumento(this.criarSubTipoDocumento());
        documentoDTO.setTipoDocumento(this.criarTipoDocumento());

        return documentoDTO;
    }

    private UsoProprioDTO criarUsoProprioDTO() {
        UsoProprioDTO usoProprioDTO = new UsoProprioDTO();

        usoProprioDTO.setId(62L);
        usoProprioDTO.setDestinacaoImoveis(criarListaDestinacaoImovelDTO(false));
        usoProprioDTO.setDocumentos(this.criarListaDocumentoDTO());
        usoProprioDTO.setFotos(this.criarFotos());
        usoProprioDTO.getDadosResponsavel().setResponsaveis(this.criarListaResponsaveisPessoaFisica());
        usoProprioDTO.setUtilizacao(this.criarUtilizacaoDTO());
        usoProprioDTO.setStatusDestinacao(
                this.criarDominioDTO(StatusDestinacaoEnum.ATIVO.getCodigo(), StatusDestinacaoEnum.ATIVO.getDescricao()));
        usoProprioDTO.setHomologado(true);
        usoProprioDTO.setIdResponsavelCadastro(1L);
        usoProprioDTO.setObservacao("");
        usoProprioDTO.setCodFundamentoLegal(1L);
        usoProprioDTO.setContrato(this.criarContratoDTO());
        usoProprioDTO.setEncargos(this.criarListaEncargos());
        usoProprioDTO.setFinanceiro(this.criarFinanceiroDTOVenda());
        usoProprioDTO.setLicitacao(this.criarLicitacaoDTO());
        usoProprioDTO.setNumeroAtendimento("");
        usoProprioDTO.setTipoDestinacao(this.criarTipoDestinacaoDTO());
        usoProprioDTO.setTipoDestinacaoEnum(TipoDestinacaoEnum.USO_PROPRIO);
        usoProprioDTO.setNumeroProcesso("");
        usoProprioDTO.setDestinacaoImoveis(this.criarDestinacaoImovelId62());

//        usoProprioDTO.setPendencias(
//                
//                PendenciaDTOBuilder.getBuilder()
//                        .setId(4L).setDataGerada(new Date(2017 / 07 / 17)).setDescricao("Pendencia teste")
//                        //.setDestinacaoPendenciaId(new DestinacaoPendenciaID())
//                        .buildList());

        return usoProprioDTO;
    }

    private DominioDTO criarDominioDTO(Integer id, String descricao) {
        return new DominioDTO(id, descricao);
    }

    private Calendar criarDataHoje() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR);
        return calendar;
    }

    private Calendar criarDataMesSeguite() {
        Calendar calendar = this.criarDataHoje();
        calendar.add(Calendar.MONTH, 1);
        return calendar;
    }
}
