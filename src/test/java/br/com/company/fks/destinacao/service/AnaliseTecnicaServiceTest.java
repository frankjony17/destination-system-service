package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaRepository;
import br.com.company.fks.destinacao.service.analiseTecnica.StatusAnaliseDespachoChefia;
import br.com.company.fks.destinacao.service.analiseTecnica.StatusAnaliseDespachoSecretario;
import br.com.company.fks.destinacao.service.analiseTecnica.StatusAnaliseTecnicaStrategy;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.EnviadorEmail;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by diego on 02/01/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StatusAnaliseTecnicaStrategy.class})
public class AnaliseTecnicaServiceTest {

    @InjectMocks
    private AnaliseTecnicaService analiseTecnicaService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private AnaliseTecnicaRepository analiseTecnicaRepository;

    @Mock
    private ItemVerificacaoService itemVerificacaoService;

    @Mock
    private ItemVerificacaoEspecificaService itemVerificacaoEspecificaService;

    @Mock
    private DocumentoAnaliseService documentoAnaliseService;

    @Mock
    private HistoricoAnaliseTecnicaService historicoAnaliseTecnicaService;

    @Mock
    private AnaliseTecnicaDespachoTecnicoService analiseTecnicaDespachoTecnicoService;

    @Mock
    private AnaliseTecnicaDespachoChefiaService analiseTecnicaDespachoChefiaService;

    @Mock
    private AnaliseTecnicaDespachoSuperintendenteService analiseTecnicaDespachoSuperintendenteService;

    @Mock
    private AnaliseTecnicaDespachoSecretarioService analiseTecnicaDespachoSecretarioService;

    @Mock
    private RequerimentoService requerimentoService;

    @Mock
    private EnviadorEmail enviadorEmail;

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Mock
    private AnaliseTecnica analiseTecnica;

    @Mock
    private AnaliseTecnicaDTO analiseTecnicaDTO;

    @Mock
    private DominioService dominioService;

    @Mock
    private StatusAnaliseTecnica statusAnaliseTecnicaAtual;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private DespachoService despachoService;

    @Mock
    private Publicacao publicacao;

    @Mock
    private PublicacaoService publicacaoService;

    @Mock
    private DominioDTO dominioDTO;

    @Mock
    private StatusAnaliseDespachoChefia statusAnaliseDespachoChefia;

    @Mock
    private StatusAnaliseDespachoSecretario statusAnaliseDespachoSecretario;

    @Mock
    private StatusAnaliseTecnica statusAnaliseTecnica;

    @Mock
    private DocumentoComplementar documentoComplementar;

    @Mock
    private DocumentoComplementarEspecifico documentoComplementarEspecifico;

    @Before
    public void setUp() throws IntegracaoException {
        mockStatic(StatusAnaliseTecnicaStrategy.class);
        when(requerimentoService.getDadosAtendimento(anyLong())).thenReturn(criarMapaDadosRequerimento());
        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        when(usuarioLogadoDTO.getId()).thenReturn(1L);
        when(usuarioLogadoDTO.getNome()).thenReturn("Teste");
        when(entityConverter.converterStrict(any(AnaliseTecnicaDTO.class), eq(AnaliseTecnica.class))).thenReturn(analiseTecnica);
        when(entityConverter.converterStrict(any(AnaliseTecnica.class), eq(AnaliseTecnicaDTO.class))).thenReturn(analiseTecnicaDTO);
        when(analiseTecnicaRepository.findOne(anyLong())).thenReturn(analiseTecnica);
        when(analiseTecnicaRepository.save(any(AnaliseTecnica.class))).thenReturn(analiseTecnica);
        when(analiseTecnica.getDocumentosComplementares()).thenReturn(asList(documentoComplementar));
        when(analiseTecnica.getDocumentosComplementaresEspecificos()).thenReturn(asList(documentoComplementarEspecifico));
    }

    private Map<String, String> criarMapaDadosRequerimento() {
        Map<String, String> mapaDadosRequerimento = new HashMap<>();
        mapaDadosRequerimento.put("numeroAtendimento", "00000");
        mapaDadosRequerimento.put("nomeResponsavel", "teste");
        mapaDadosRequerimento.put("email", "diego.alves@basis.com.br");
        return mapaDadosRequerimento;
    }

    private Set<String> criarPermissao(String... permissao) {
        Set<String> permissoes = new HashSet<>();
        for (String p : permissao) {
            permissoes.add(p);
        }
        return permissoes;
    }

    @Test
    public void salvarPermissaoChefiaNovaAnalise() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_CHEFIA"));
        when(analiseTecnicaDTO.getId()).thenReturn(null);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnicaAtual);
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(idStatusAnalise);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }

    @Test
    public void salvarPermissaoChefiaAnaliseExistente() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_CHEFIA"));
        when(analiseTecnicaDTO.getId()).thenReturn(1L);
        when(analiseTecnicaDTO.getStatusAnaliseTecnica()).thenReturn(dominioDTO);
        when(dominioDTO.getId()).thenReturn(5);
        when(StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(eq(PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA))).thenReturn(statusAnaliseDespachoChefia);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnicaAtual);
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(idStatusAnalise);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }

    @Test
    public void salvarPermissaoTecnicoNovaAnalise() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_TECNICO"));
        when(analiseTecnicaDTO.getId()).thenReturn(null);
        when(analiseTecnica.getId()).thenReturn(null);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnicaAtual);
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(idStatusAnalise);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }

    @Test
    public void salvarPermissaoSuperintendenteNovaAnalise() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE"));
        when(analiseTecnicaDTO.getId()).thenReturn(null);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnicaAtual);
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(idStatusAnalise);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }

    @Test
    public void salvarPermissaoSecretarioAguardandoRequerente() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO"));
        when(analiseTecnicaDTO.getId()).thenReturn(1L);
        when(analiseTecnicaDTO.getStatusAnaliseTecnica()).thenReturn(dominioDTO);
        when(dominioDTO.getId()).thenReturn(6);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(9);
        when(StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(eq(PermissaoAnaliseEnum.EXEC_ANALISE_SECRETARIO))).thenReturn(statusAnaliseDespachoSecretario);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }

    @Test
    public void salvarPermissaoSecretarioErroEnvioEmail() throws IntegracaoException {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO"));
        when(analiseTecnicaDTO.getId()).thenReturn(1L);
        when(analiseTecnicaDTO.getStatusAnaliseTecnica()).thenReturn(dominioDTO);
        when(dominioDTO.getId()).thenReturn(6);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(9);
        when(StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(eq(PermissaoAnaliseEnum.EXEC_ANALISE_SECRETARIO))).thenReturn(statusAnaliseDespachoSecretario);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(requerimentoService.getDadosAtendimento(anyLong())).thenThrow(IntegracaoException.class);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }

    @Test
    public void salvarPermissaoSecretarioEnviarPublicacao() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO"));
        when(analiseTecnicaDTO.getId()).thenReturn(1L);
        when(analiseTecnicaDTO.getStatusAnaliseTecnica()).thenReturn(dominioDTO);
        when(dominioDTO.getId()).thenReturn(6);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(9);
        when(StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(eq(PermissaoAnaliseEnum.EXEC_ANALISE_SECRETARIO))).thenReturn(statusAnaliseDespachoSecretario);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnicaAtual);
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(idStatusAnalise);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }
//TESTANDO
    @Test
    public void salvarTecnicoAnaliseJaExitente() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.CANCELADO.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_TECNICO"));
        when(analiseTecnicaDTO.getId()).thenReturn(1L);
        when(analiseTecnicaDTO.getStatusAnaliseTecnica()).thenReturn(dominioDTO);
        when(dominioDTO.getId()).thenReturn(6);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(9);
        when(StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(eq(PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA))).thenReturn(statusAnaliseDespachoChefia);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnicaAtual);
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(idStatusAnalise);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }

    @Test
    public void salvarRascunho() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA.getCodigo();
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao("DESTINACAO_EXEC_ANALISE_TEC_TECNICO"));
        when(analiseTecnicaDTO.getId()).thenReturn(null);
        when(dominioService.findById(idStatusAnalise, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnicaAtual);
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(idStatusAnalise);
        AnaliseTecnicaDTO analiseTecnicaDTOSalvo = analiseTecnicaService.salvarRascunho(analiseTecnicaDTO);
        assertNotNull(analiseTecnicaDTOSalvo);
    }

    @Test
    public void buscarPorId() throws NegocioException {
        AnaliseTecnicaDTO analiseTecnicaDTO = analiseTecnicaService.buscarPorId(1L);
        assertNotNull(analiseTecnicaDTO);
    }

    @Test
    public void buscarPorIdRequerimento() throws NegocioException {
        when(analiseTecnicaRepository.buscarPorIdRequerimento(1L)).thenReturn(analiseTecnica);
        AnaliseTecnicaDTO analiseTecnicaDTO = analiseTecnicaService.buscarPorIdRequerimento(1L);
        assertNotNull(analiseTecnicaDTO);
    }

    @Test(expected = NegocioException.class)
    public void buscarPorIdLancandoErro() throws NegocioException {
        when(analiseTecnicaRepository.findOne(anyLong())).thenReturn(null);
        AnaliseTecnicaDTO analiseTecnicaDTO = analiseTecnicaService.buscarPorId(1L);
        assertNotNull(analiseTecnicaDTO);
    }

    @Test
    public void registrarPublicacaoDiarioUniao() {
        Integer codigoStatusPublicado = StatusAnaliseTecnicaEnum.PUBLICADO.getCodigo();
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(codigoStatusPublicado);
        when(dominioService.findById(codigoStatusPublicado, StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnicaAtual);
        when(publicacaoService.salvar(any(Publicacao.class))).thenReturn(publicacao);
        when(analiseTecnica.getPublicacao()).thenReturn(publicacao);
        when(publicacao.getId()).thenReturn(1L);
        AnaliseTecnica analiseTecnicaPublicada = analiseTecnicaService.registrarPublicacaoDiarioUniao(1L, publicacao);
        assertNotNull(analiseTecnicaPublicada);
        assertNotNull(analiseTecnicaPublicada.getPublicacao());
    }

}
