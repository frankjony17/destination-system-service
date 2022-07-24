package br.com.company.fks.destinacao.service.destinacao;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.*;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoPendencia;
import br.com.company.fks.destinacao.dominio.entidades.Dominio;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.dominio.entidades.Pendencia;
import br.com.company.fks.destinacao.dominio.entidades.SemUtilizacao;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.UnidadeAutonoma;
import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoOperacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.SemUtilizacaoRepository;
import br.com.company.fks.destinacao.service.BenfeitoriaService;
import br.com.company.fks.destinacao.service.ContratoService;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import br.com.company.fks.destinacao.service.DominioService;
import br.com.company.fks.destinacao.service.ImovelService;
import br.com.company.fks.destinacao.service.ParcelaService;
import br.com.company.fks.destinacao.service.UsuarioService;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.service.validadores.ValidadorStrategy;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorSemUtilizacao;
import br.com.company.fks.destinacao.service.UtilizacaoService;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.apache.commons.lang.NotImplementedException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.*;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 06/02/17.
 */
@RunWith(PowerMockRunner.class)
public class SemUtilizacaoServiceTest {


    private static final String RIP = "0000122";

    @InjectMocks
    private SemUtilizacaoService semUtilizacaoService;

    @Mock
    private DestinacaoImovelService destinacaoImovelService;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private DominioService dominioService;

    @Mock
    private SemUtilizacaoRepository semUtilizacaoRepository;

    @Mock
    private SemUtilizacao semUtilizacao;

    @Mock
    private Imovel imovel;

    @Mock
    private ImovelService imovelService;

    @Mock
    private Benfeitoria benfeitoria;

    @Mock
    private BenfeitoriaService benfeitoriaService;

    @Mock
    private UnidadeAutonoma  unidadeAutonoma;

    @Mock
    private Pendencia pendencia;

    @Mock
    private DestinacaoPendencia destinacaoPendencia;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private TipoDestinacao tipoDestinacao;

    @Mock
    private ValidadorDestinacao validadorDestinacao;

    @Mock
    private ValidadorSemUtilizacao validadorSemUtilizacao;

    @Mock
    private ValidadorStrategy validadorStrategy;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private Dominio dominio;

    @Mock
    private DestinacaoRepository destinacaoRepository;

    @Mock
    private Destinacao destinacao;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Mock
    private ParcelaService parcelaService;

    @Mock
    private FiltroDestinacaoDTO filtroDestinacaoDTO;

    @Mock
    private Page<Object> page;

    @Mock
    private ImovelDTO imovelDTO;

    @Mock
    private ImagemDTO imagemDTO;

    @Mock
    private ContratoService contratoService;

    @Mock
    private Contrato contrato;

    @Mock
    private UtilizacaoService utilizacaoService;

    @Mock
    private Utilizacao utilizacao;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Mock
    private EnderecoDTO enderecoDTO;

    @Mock
    private Parcela parcela;

    @Mock
    private ParcelaDTO parcelaDTO;

    @Mock
    private PendenciaDTO pendenciaDTO;

    @Before
    public void setUp() {
        when(semUtilizacaoRepository.save(any(SemUtilizacao.class))).thenReturn(semUtilizacao);
        when(semUtilizacaoRepository.buscarDestinacaoRipImovel(anyString())).thenReturn(semUtilizacao);
        when(semUtilizacao.getDestinacaoImoveis()).thenReturn(asList(destinacaoImovel));
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getUnidadeAutonoma()).thenReturn(unidadeAutonoma);
        when(imovel.getRip()).thenReturn(RIP);
        when(validadorStrategy.createBean(TipoDestinacaoEnum.SEM_UTILIZACAO)).thenReturn(validadorDestinacao);
        when(dominioService.findById(anyInt(),any())).thenReturn(statusDestinacao);
        when(filtroDestinacaoDTO.getRip()).thenReturn("1");
        when(filtroDestinacaoDTO.getOffset()).thenReturn(1);
        when(filtroDestinacaoDTO.getLimit()).thenReturn(1);
        when(destinacaoRepository.consultar(anyString(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(),
                anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), any(Pageable.class)))
        .thenReturn(page);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);
        when(imovelDTO.getLatitude()).thenReturn(-5294635.49386766);
        when(imovelDTO.getLongitude()).thenReturn(-1797975.8866145262);
        when(imagemDTO.getImagem()).thenReturn("imagem texto");
        when(imovelDTO.getImagens()).thenReturn(asList(imagemDTO));
        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        when(utilizacao.getId()).thenReturn(1L);

    }

    @Test(expected = NotImplementedException.class)
    public void salvarDadosEspecificos(){
        semUtilizacaoService.salvarDadosEspecificos(semUtilizacao);
    }

    @Test
    public void salvar() {
        SemUtilizacao semUtilizacaoSalva = semUtilizacaoService.salvar(semUtilizacao);
        assertNotNull(semUtilizacaoSalva);
        assertNotNull(semUtilizacaoSalva.getId());
    }

    @SneakyThrows
    @Test
    public void cancelar(){
        semUtilizacaoService.cancelar(RIP);
        doNothing().when(imovelService).inativar(any(Imovel.class));
    }

    @SneakyThrows
    @Test
    public void salvarImovelDestinacao(){
        when(imovel.getIndicadorOperacao()).thenReturn(TipoOperacaoEnum.CADASTRO);
        when(utilizacaoService.selecionaSemUsoVago()).thenReturn(utilizacao);
        semUtilizacaoService.salvar(imovel);
        validadorDestinacao.validadorEspecifico(validadorDestinacao);
        assertNotNull(imovel);
    }

//    @SneakyThrows
//    @Test(expected = NegocioException.class)
//    public void atualizarImovelDestinacaoException(){
//        Set<PendenciaDTO> parcelaDTOs = new HashSet<>();
//        PendenciaDTO pendenciaDTO = new PendenciaDTO();
//        pendenciaDTO.setId(1l);
//        parcelaDTOs.add(pendenciaDTO);
//
//
//
//        when(semUtilizacaoRepository.getQuantidadeDestinacoesImovel(anyString())).thenReturn(1L);
//        when(imovel.getIndicadorOperacao()).thenReturn(TipoOperacaoEnum.EDICAO);
//        when(benfeitoriaService.buscarBenfeitoriasSemParcelaIdImovel(anyLong())).thenReturn(Arrays.asList(benfeitoria));
//        when(imovelService.extrairImovelParcela(anyList())).thenReturn(imovel);
//        when(semUtilizacaoRepository.buscarDestinacaoRipImovel(RIP)).thenReturn(semUtilizacao);
//        when(semUtilizacao.getDestinacaoImoveis()).thenReturn(Arrays.asList(destinacaoImovel));
//        when(destinacaoImovel.getParcelas()).thenReturn(Arrays.asList(parcela));
//        when(destinacaoImovel.getDestinacao()).thenReturn(destinacao);
//        when(destinacao.getId()).thenReturn(1l);
//
//        when(destinacaoPendenciaService.buscarPendenciasPorIdDestinacao(anyLong())).thenReturn(parcelaDTOs);
//        Destinacao destinacaoSalvo = semUtilizacaoService.salvar(imovel);
//
//        assertNotNull(destinacaoSalvo);
//    }

    @SneakyThrows
    @Test
    public void atualizarImovelDestinacao() {
        when(semUtilizacaoRepository.buscarDestinacaoRipImovel(anyString())).thenReturn(semUtilizacao);

        when(semUtilizacaoRepository.getQuantidadeDestinacoesImovel(anyString())).thenReturn(0L);
        when(imovel.getIndicadorOperacao()).thenReturn(TipoOperacaoEnum.EDICAO);
        when(imovelService.atualizar(any(Imovel.class),anyMap())).thenReturn(imovel);
        when(benfeitoriaService.atualizar(any(Parcela.class),anyList(),anyMap(), anyList(), any(Imovel.class))).thenReturn(asList(benfeitoria));
        when(semUtilizacaoRepository.buscarDestinacaoRipImovel(anyString())).thenReturn(semUtilizacao);
        Set<PendenciaDTO> pendenciaDTOSet = new HashSet<>();
        pendenciaDTOSet.add(pendenciaDTO);
        when(destinacaoPendenciaService.buscarPendenciasPorIdDestinacao(anyLong())).thenReturn(pendenciaDTOSet);
        when(destinacaoImovel.getDestinacao()).thenReturn(destinacao);
        when(destinacao.getId()).thenReturn(1L);
        List<Parcela> parcelaList = new ArrayList<>();
        parcelaList.add(parcela);
        when(parcelaService.extrairParcelasAtivas(anyList())).thenReturn(new ArrayList<>());
        when(imovelService.extrairImovelParcela(anyList())).thenReturn(imovel);
        List<Benfeitoria> benfeitoriaList = new ArrayList<>();
        benfeitoriaList.add(benfeitoria);
        benfeitoria.setId(1L);
        when(benfeitoriaService.buscarBenfeitoriasSemParcelaIdImovel(anyLong())).thenReturn(benfeitoriaList);
        when(semUtilizacaoRepository.getQuantidadeDestinacoesImovel(anyString())).thenReturn(1L);
        destinacaoPendenciaService.gerarPendencia(destinacao, 1L);
        semUtilizacaoService.salvar(imovel);
        assertNotNull(imovel);
    }



    @Test
    @SneakyThrows
    public void consultar() {
        List destinacoes = new ArrayList();
        Object [] destinacao = new Object[40];
        destinacao[0] = 1L;
        destinacoes.add(destinacao);
        when(destinacaoRepository.consultar(anyString(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(),
                anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), any(Pageable.class)))
                .thenReturn(page);
        when(page.getContent()).thenReturn(destinacoes);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);
        when(imovelService.getImovelCadastroImoveis(anyString())).thenReturn(imovelDTO);
        Resposta<List<ConsultaDestinacaoDTO>> resposta = semUtilizacaoService.consultar(filtroDestinacaoDTO);
        assertTrue(resposta.getResultado().isEmpty());
    }


    @Test
    @SneakyThrows
    public void consultarSemImagemSemContrato() {
        when(imovelDTO.getImagens()).thenReturn(null);
        List destinacoes = new ArrayList();
        Object [] destinacao = new Object[35];
        destinacao[0] = 1L;
        destinacoes.add(destinacao);
        when(destinacaoRepository.consultar(anyString(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(),
                anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), any(Pageable.class)))
                .thenReturn(page);
        when(imovelService.getImovelCadastroImoveis(anyString())).thenReturn(imovelDTO);
        when(enderecoDTO.getUf()).thenReturn("DF");
        when(page.getContent()).thenReturn(destinacoes);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);
        Resposta<List<ConsultaDestinacaoDTO>> resposta = semUtilizacaoService.consultar(filtroDestinacaoDTO);
        assertTrue(resposta.getResultado().isEmpty());
    }


    @Test
    @SneakyThrows
    public void consultarImagemVaziaSemContrato() {
        when(imovelDTO.getImagens()).thenReturn(Collections.emptyList());
        List destinacoes = new ArrayList();
        Object [] destinacao = new Object[40];
        destinacao[0] = 1L;
        destinacoes.add(destinacao);
        when(destinacaoRepository.consultar(anyString(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(),
                anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), anyObject(), any(Pageable.class)))
                .thenReturn(page);
        when(imovelService.getImovelCadastroImoveis(anyString())).thenReturn(imovelDTO);
        when(page.getContent()).thenReturn(destinacoes);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);
        Resposta<List<ConsultaDestinacaoDTO>> resposta = semUtilizacaoService.consultar(filtroDestinacaoDTO);
        assertTrue(resposta.getResultado().isEmpty());
    }


    @SneakyThrows
    public void consultarSemResultados() {
        when(page.getContent()).thenReturn(Collections.emptyList());
        when(imovelService.getImovelCadastroImoveis(anyString())).thenReturn(imovelDTO);
        when(page.getTotalElements()).thenReturn(0L);
        when(page.getTotalPages()).thenReturn(0);
        Resposta<List<ConsultaDestinacaoDTO>> resposta = semUtilizacaoService.consultar(filtroDestinacaoDTO);
        assertTrue(resposta.getResultado().isEmpty());
    }

    @Test
    public void testStringRipRepository(){
        assertEquals(destinacaoRepository.RIP, "rip", "rip");
    }
}