package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.PosseInformalDTO;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Encargo;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Financeiro;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.dominio.entidades.Ocupante;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.dominio.enums.StatusDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.EnderecoRepository;
import br.com.company.fks.destinacao.repository.PosseInformalRepository;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import br.com.company.fks.destinacao.service.DominioService;
import br.com.company.fks.destinacao.service.EncargoService;
import br.com.company.fks.destinacao.service.EnderecoService;
import br.com.company.fks.destinacao.service.FinanceiroService;
import br.com.company.fks.destinacao.service.ImovelService;
import br.com.company.fks.destinacao.service.LicitacaoService;
import br.com.company.fks.destinacao.service.OcupanteService;
import br.com.company.fks.destinacao.service.ParcelaService;
import br.com.company.fks.destinacao.service.ResponsavelService;
import br.com.company.fks.destinacao.service.UtilizacaoService;
import br.com.company.fks.destinacao.service.validadores.ValidadorStrategy;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorPosseInformal;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 17/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PosseInformalServiceTest {

    private static final String NUMERO_RIP = "0000122";
    private static final String ERRO_AO_SALVAR = "Erro ao salvar";

    @InjectMocks
    private PosseInformalService posseInformalService;

    @Mock
    private PosseInformalRepository posseInformalRepository;

    @Mock
    private DestinacaoRepository destinacaoRepository;

    @Mock
    private ImovelService imovelService;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private OcupanteService ocupanteService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private ValidadorStrategy validadorStrategy;

    @Mock
    private ValidadorPosseInformal validadorPosseInformal;

    @Mock
    private UtilizacaoService utilizacaoService;

    @Mock
    private FinanceiroService financeiroService;

    @Mock
    private LicitacaoService licitacaoService;

    @Mock
    private EncargoService encargoService;

    @Mock
    private ResponsavelService responsavelService;

    @Mock
    private DestinacaoImovelService destinacaoImovelService;

    @Mock
    private PosseInformal posseInformal;

    @Mock
    private Imovel imovel;

    @Mock
    private Endereco endereco;

    @Mock
    private Ocupante ocupante;

    @Mock
    private Utilizacao utilizacao;

    @Mock
    private Financeiro financeiro;

    @Mock
    private Licitacao licitacao;

    @Mock
    private Encargo encargo;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private Responsavel responsavel;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private DestinacaoDTO destinacaoDTO;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private DominioService dominioService;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Mock
    private ParcelaService parcelaService;

    @Mock
    private Parcela parcela;

    @Mock
    private PosseInformalDTO posseInformalDTO;

    @Mock
    private List<Destinacao> destinacoes;


    @Mock
    private DestinacaoService destinacaoService;

    @Before
    public void setUp() throws NegocioException {

        when(posseInformalRepository.save(any(PosseInformal.class))).thenReturn(posseInformal);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(enderecoService.salvar(any(Endereco.class))).thenReturn(endereco);
        when(imovel.getEndereco()).thenReturn(endereco);
        when(ocupanteService.salvar(anyListOf(Ocupante.class), any(PosseInformal.class))).thenReturn(asList(ocupante));

        mockDependenciasDestinacao();

        mockPosseInformal();

    }

    private void mockDependenciasDestinacao() throws NegocioException {
        when(encargoService.salvar(anyListOf(Encargo.class))).thenReturn(asList(encargo));
        when(responsavelService.salvar(anyListOf(Responsavel.class), any(DadosResponsavel.class))).thenReturn(asList(responsavel));
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(parcelaService.atualizarAreaDisponivel(anyLong(), eq(BigDecimal.ZERO))).thenReturn(parcela);
    }

    private void mockPosseInformal() {
        when(posseInformal.getUtilizacao()).thenReturn(utilizacao);
        when(posseInformal.getCodFundamentoLegal()).thenReturn(1L);
        when(posseInformal.getFinanceiro()).thenReturn(financeiro);
        when(posseInformal.getLicitacao()).thenReturn(licitacao);
        when(posseInformal.getEncargos()).thenReturn(asList(encargo));
        when(posseInformal.getDadosResponsavel()).thenReturn(dadosResponsavel);
        when(posseInformal.getDestinacaoImoveis()).thenReturn(asList(destinacaoImovel));
        when(posseInformal.getImovel()).thenReturn(imovel);
        when(posseInformal.getImovel().getRip()).thenReturn(NUMERO_RIP);
        when(posseInformal.getImovel().getParcelas()).thenReturn(asList(parcela));
        when(posseInformal.getOcupantes()).thenReturn(asList(ocupante));
        when(posseInformal.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.POSSE_INFORMAL);
        when(parcela.getId()).thenReturn(1L);
        when(parcela.getSequencial()).thenReturn("P0");
    }

    @Test
    public void salvarDadosEspecificosImovelNaoNuloExisteDestinacao() {
        when(imovelService.buscarPorRip(anyString())).thenReturn(imovel);
        when(destinacaoRepository.buscarDestinacaoPorRip(anyString())).thenReturn(asList(posseInformal));
        PosseInformal posseInformalSalvar = posseInformalService.salvarDadosEspecificos(posseInformal);
        assertNotNull(posseInformalSalvar);
        assertEquals(posseInformalSalvar.getId(), posseInformal.getId());
    }

    @Test
    public void salvarDadosEspecificosImovelNuloExisteDestinacao() {
        when(imovelService.buscarPorRip(anyString())).thenReturn(null);
        when(destinacaoRepository.buscarDestinacaoPorRip(anyString())).thenReturn(asList(posseInformal));
        PosseInformal posseInformalSalvar = posseInformalService.salvarDadosEspecificos(posseInformal);
        assertNotNull(posseInformalSalvar);
        assertEquals(posseInformalSalvar.getId(), posseInformal.getId());
    }

    @Test
    public void salvarDadosEspecificosImovelNaoNuloNaoExisteDestinacao() {
        when(imovelService.buscarPorRip(anyString())).thenReturn(imovel);
        when(posseInformal.getId()).thenReturn(1L);
        List<Ocupante> ocupanteList = new ArrayList<>();
        ocupanteList.add(ocupante);
        ocupante.setId(1L);
        when(posseInformal.getOcupantes()).thenReturn(ocupanteList);
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        when(posseInformalRepository.buscarIdsOcupantesPosseInformal(1L)).thenReturn(longList);
        when(posseInformal.getId()).thenReturn(1L);
        PosseInformal posseInformalSalvar = posseInformalService.salvarDadosEspecificos(posseInformal);
        assertNotNull(posseInformalSalvar);
        assertEquals(posseInformalSalvar.getId(), posseInformal.getId());
    }



    @Test
    public void consultarDestinacaoNumeroRip() {
        when(destinacaoRepository.buscarDestinacaoPorRip(anyString())).thenReturn(asList(posseInformal));
        List<Destinacao> destinacoes = posseInformalService.consultarDestinacaoNumeroRip(NUMERO_RIP);
        assertNotNull(destinacoes);
        assertTrue(!destinacoes.isEmpty());
    }

    @Test
    public void consultarNumeroDestinacaoDestinacaoEncontrada () {
        when(destinacaoRepository.buscarDestinacaoPorRip(anyString())).thenReturn(asList(posseInformal));
        when(entityConverter.converterStrict(any(Destinacao.class), eq(DestinacaoDTO.class))).thenReturn(destinacaoDTO);
        List<DestinacaoDTO> destinacoes = posseInformalService.consultarNumeroDestinacao(NUMERO_RIP);
        assertNotNull(destinacoes);
        assertTrue(!destinacoes.isEmpty());
    }

    @Test
    public void consultarNumeroDestinacaoDestinacoesNaoEncontradas () {
        when(destinacaoRepository.buscarDestinacaoPorRip(anyString())).thenReturn(null);
        when(entityConverter.converterStrict(any(Destinacao.class), eq(DestinacaoDTO.class))).thenReturn(destinacaoDTO);
        List<DestinacaoDTO> destinacoes = posseInformalService.consultarNumeroDestinacao(NUMERO_RIP);
        assertNotNull(destinacoes);
        assertTrue(destinacoes.isEmpty());
    }

    @Test
    public void consultarPosseInformalId() throws Exception {
        when(posseInformalRepository.findOne(any(Long.class))).thenReturn(posseInformal);
        when(entityConverter.converterStrict(any(PosseInformal.class), eq(PosseInformalDTO.class))).thenReturn(posseInformalDTO);
        when(posseInformal.getDestinacaoImoveis()).thenReturn(new ArrayList<>());
        Resposta<PosseInformalDTO> posseInformalDTOResposta = posseInformalService.findOne(0L);
        assertEquals(posseInformalDTO,posseInformalDTOResposta.getResultado());
    }

}
