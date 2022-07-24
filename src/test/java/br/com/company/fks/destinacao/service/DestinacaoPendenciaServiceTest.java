package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoPendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.PendenciaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoPendencia;
import br.com.company.fks.destinacao.dominio.entidades.Pendencia;
import br.com.company.fks.destinacao.repository.DestinacaoPendenciaRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 20/03/2017.
 */
@RunWith(PowerMockRunner.class)
public class DestinacaoPendenciaServiceTest {

    @InjectMocks
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private DestinacaoPendenciaRepository destinacaoPendenciaRepository;

    @Mock
    private DestinacaoPendencia destinacaoPendencia;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Mock
    private PendenciaService pendenciaService;

    @Mock
    private Pendencia pendencia;

    @Mock
    private PendenciaDTO pendenciaDTO;

    @Mock
    private DestinacaoPendenciaDTO destinacaoPendenciaDTO;

    @Mock
    private Destinacao destinacao;

    @Mock
    private EntityConverter entityConverter;

    @Before
    public void setUp() throws Exception {
        when(destinacaoPendenciaRepository.save(any(DestinacaoPendencia.class))).thenReturn(destinacaoPendencia);
        when(destinacaoPendenciaRepository.buscarPendenciasUsuarioLogado(anySet(),anySet())).thenReturn(criarPendenciasDTO());
        when(pendenciaService.findById(anyLong())).thenReturn(pendencia);

    }

    @Test
    public void gerarPendenciaCerticaoCartorialTrue() throws Exception {
        when(destinacaoPendenciaService.buscarDestinacaoPendenciaPorId(anyLong(),anyLong())).thenReturn(destinacaoPendenciaDTO);
        destinacaoPendenciaService.gerarPendencia(destinacao, 1L);

    }


    @Test
    public void gerarPendencia() throws Exception {
        destinacaoPendenciaService.gerarPendencia(destinacao,1L);
        assertNotNull(destinacaoPendencia);
    }

    @Test
    public void buscarPendenciasUsuarioTest() {
        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(criarPermissao());
        when(pendenciaService.getMapPendencias()).thenReturn(criarMapaPendencias());
        when(pendenciaDTO.getDescricao()).thenReturn("Dist");
        when(pendenciaDTO.getDataGerada()).thenReturn(new Date());
        when(pendenciaDTO.getIdDestinacao()).thenReturn(1L);
        when(pendenciaDTO.getId()).thenReturn(1L);
        Set<PendenciaDTO> pendencias = destinacaoPendenciaService.buscarPendenciasUsuario();
        assertNotNull(pendencias);
    }

    private Map<String, Pendencia> criarMapaPendencias() {
        Map<String, Pendencia> mapaPendencias = new HashMap<>();
        Pendencia pendencia = new Pendencia();
        pendencia.setModulo("Destinacao");
        pendencia.setDescricao("teste");
        mapaPendencias.put("Dist", pendencia);
        return mapaPendencias;
    }

    private Set<PendenciaDTO> criarPendenciasDTO(String... permissao) {
        Set<PendenciaDTO> pendenciaDTOS= new HashSet<>();
        pendenciaDTOS.add(pendenciaDTO);
        return pendenciaDTOS;
    }

    private Set<String> criarPermissao(String... permissao) {
        Set<String> permissoes = new HashSet<>();
        for (String p : permissao) {
            permissoes.add(p);
        }
        return permissoes;
    }

    @Test
    public void removerPendencia(){
        destinacaoPendenciaService.removerPendencia(1L,2L);
    }

    @Test
    public void removerPendenciaNulo(){
        when(destinacaoPendenciaService.buscarDestinacaoPendenciaPorId(anyLong(),anyLong())).thenReturn(destinacaoPendenciaDTO);
        when(entityConverter.converterStrict(destinacaoPendenciaDTO,DestinacaoPendencia.class)).thenReturn(destinacaoPendencia);
        destinacaoPendenciaService.removerPendencia(1L,2L);
    }

    @Test
    public void buscarPendenciasPorIdDestinacao(){ destinacaoPendenciaService.buscarPendenciasPorIdDestinacao(anyLong());}
}