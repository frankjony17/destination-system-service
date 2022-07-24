package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.HistoricoAnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.repository.HistoricoAnaliseTecnicaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 30/12/16.
 */
@RunWith(PowerMockRunner.class)
public class HistoricoAnaliseTecnicaServiceTest {

    @InjectMocks
    private HistoricoAnaliseTecnicaService historicoAnaliseTecnicaService;

    @Mock
    private HistoricoAnaliseTecnicaRepository historicoAnaliseTecnicaRepository;

    @Mock
    private HistoricoAnaliseTecnicaDTO historicoAnaliseTecnicaDTO;

    @Mock
    private StatusAnaliseTecnica statusAnaliseTecnicaAnterior;

    @Mock
    private StatusAnaliseTecnica statusAnaliseTecnicaAtual;

    @Mock
    private AnaliseTecnica analiseTecnica;

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Mock
    private HistoricoAnaliseTecnica historicoAnaliseTecnica;

    @Mock
    private AnaliseTecnicaDespachoTecnico analiseTecnicaDespachoTecnico;

    @Mock
    private AnaliseTecnicaDespachoChefia analiseTecnicaDespachoChefia;

    @Mock
    private AnaliseTecnicaDespachoSuperintendente analiseTecnicaDespachoSuperintendente;

    @Mock
    private AnaliseTecnicaDespachoSecretario analiseTecnicaDespachoSecretario;

    @Before
    public void setUp() {
        when(historicoAnaliseTecnicaRepository.findByAnaliseTecnicaId(anyLong(), any(Pageable.class))).thenReturn(criarListaPageble());
        when(usuarioLogadoDTO.getId()).thenReturn(1L);
        when(usuarioLogadoDTO.getNome()).thenReturn("teste");
        when(historicoAnaliseTecnica.getId()).thenReturn(1L);
        when(historicoAnaliseTecnicaRepository.save(any(HistoricoAnaliseTecnica.class))).thenReturn(historicoAnaliseTecnica);
    }

    private Page<HistoricoAnaliseTecnicaDTO> criarListaPageble() {
        Pageable pageable = new PageRequest(1, 5, Sort.Direction.ASC, "id");
        List<HistoricoAnaliseTecnicaDTO> historicos = asList(historicoAnaliseTecnicaDTO);
        Page<HistoricoAnaliseTecnicaDTO> pages = new PageImpl<HistoricoAnaliseTecnicaDTO>(historicos, pageable, 1L);
        return pages;
    }

    @Test
    public void findByAnaliseTecnicaId() {
        Page<HistoricoAnaliseTecnicaDTO> pages = historicoAnaliseTecnicaService.findByAnaliseTecnicaId(1L, 1, 5);
        assertNotNull(pages);
        assertFalse(pages.getContent().isEmpty());
    }

    @Test
    public void salvarHistoricoAnaliseTecnico() {
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA.getCodigo());
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissoes(PermissaoAnaliseEnum.EXEC_ANALISE_TECNICO.getDescricao()));
        when(analiseTecnicaDespachoTecnico.getJustificativa()).thenReturn("teste");
        when(analiseTecnica.getDespachosAnaliseTecnico()).thenReturn(asList(analiseTecnicaDespachoTecnico));
        HistoricoAnaliseTecnica historicoAnaliseTecnicaSalvo = historicoAnaliseTecnicaService.salvar(statusAnaliseTecnicaAnterior, statusAnaliseTecnicaAtual, analiseTecnica, usuarioLogadoDTO);
        assertNotNull(historicoAnaliseTecnicaSalvo);
        assertEquals(1L, historicoAnaliseTecnicaSalvo.getId().longValue());
    }

    @Test
    public void salvarHistoricoAnaliseChefia() {
        when(statusAnaliseTecnicaAnterior.getId()).thenReturn(StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA.getCodigo());
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo());
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissoes(PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA.getDescricao()));
        when(analiseTecnicaDespachoTecnico.getJustificativa()).thenReturn("teste");
        when(analiseTecnica.getDespachosAnaliseChefia()).thenReturn(asList(analiseTecnicaDespachoChefia));
        HistoricoAnaliseTecnica historicoAnaliseTecnicaSalvo = historicoAnaliseTecnicaService.salvar(statusAnaliseTecnicaAnterior, statusAnaliseTecnicaAtual, analiseTecnica, usuarioLogadoDTO);
        assertNotNull(historicoAnaliseTecnicaSalvo);
        assertEquals(1L, historicoAnaliseTecnicaSalvo.getId().longValue());
    }

    @Test
    public void salvarHistoricoAnaliseSuperintendente() {
        when(statusAnaliseTecnicaAnterior.getId()).thenReturn(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA.getCodigo());
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo());
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissoes(PermissaoAnaliseEnum.EXEC_ANALISE_SUPERINTENDENTE.getDescricao()));
        when(analiseTecnicaDespachoTecnico.getJustificativa()).thenReturn("teste");
        when(analiseTecnica.getDespachosAnaliseSuperintendente()).thenReturn(asList(analiseTecnicaDespachoSuperintendente));
        HistoricoAnaliseTecnica historicoAnaliseTecnicaSalvo = historicoAnaliseTecnicaService.salvar(statusAnaliseTecnicaAnterior, statusAnaliseTecnicaAtual, analiseTecnica, usuarioLogadoDTO);
        assertNotNull(historicoAnaliseTecnicaSalvo);
        assertEquals(1L, historicoAnaliseTecnicaSalvo.getId().longValue());
    }

    @Test
    public void salvarHistoricoAnaliseSecretario() {
        when(statusAnaliseTecnicaAnterior.getId()).thenReturn(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo());
        when(statusAnaliseTecnicaAtual.getId()).thenReturn(StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO.getCodigo());
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissoes(PermissaoAnaliseEnum.EXEC_ANALISE_SECRETARIO.getDescricao()));
        when(analiseTecnicaDespachoTecnico.getJustificativa()).thenReturn("teste");
        when(analiseTecnica.getDespachosAnaliseSecretario()).thenReturn(asList(analiseTecnicaDespachoSecretario));
        HistoricoAnaliseTecnica historicoAnaliseTecnicaSalvo = historicoAnaliseTecnicaService.salvar(statusAnaliseTecnicaAnterior, statusAnaliseTecnicaAtual, analiseTecnica, usuarioLogadoDTO);
        assertNotNull(historicoAnaliseTecnicaSalvo);
        assertEquals(1L, historicoAnaliseTecnicaSalvo.getId().longValue());
    }

    private Set<String> criarPermissoes(String permissao) {
        Set<String> permissoes = new HashSet<>();
        permissoes.add(permissao);
        return permissoes;
    }

}
