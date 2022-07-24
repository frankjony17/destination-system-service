package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.BenfeitoriaDestinada;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.repository.BenfeitoriaDestinadaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 23/01/17.
 */
@RunWith(PowerMockRunner.class)
public class BenfeitoriaDestinadaServiceTest {

    private static final int TAMANHO_LISTA_BENFEITORIAS_DESTINADAS = 3;

    @InjectMocks
    private BenfeitoriaDestinadaService benfeitoriaDestinadaService;

    @Mock
    private BenfeitoriaDestinadaRepository benfeitoriaDestinadaRepository;

    @Mock
    private BenfeitoriaDestinada benfeitoriaDestinada;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    private Long idBenfeitoriaDestinadaMaisUmaVez;

    @Mock
    private List<BenfeitoriaDestinada> benfeitoriasDestinadasSalvas;

    @Before
    public void setUp() {
        when(destinacaoImovel.getId()).thenReturn(1L);
        when(benfeitoriaDestinada.getId()).thenReturn(1L);
        when(benfeitoriaDestinadaRepository.save(any(BenfeitoriaDestinada.class))).thenReturn(benfeitoriaDestinada);
        when(benfeitoriaDestinadaRepository.buscarBenfeitoriasIdImovel(anyString())).thenReturn(criarListaBenfeitoriaDestinada(TAMANHO_LISTA_BENFEITORIAS_DESTINADAS));
    }

    private List<BenfeitoriaDestinada> criarListaBenfeitoriaDestinada(int size) {
        List<BenfeitoriaDestinada> benfeitoriasDestinadas = new ArrayList<>();
        Long id = 0L;
        Long idBenfeitoria = 0L;
        for (int i = 0; i < size; i++) {
            benfeitoriasDestinadas.add(criarBenfeitoriaDestinada(++id, ++idBenfeitoria));
        }
        idBenfeitoriaDestinadaMaisUmaVez = idBenfeitoria;
        benfeitoriasDestinadas.add(criarBenfeitoriaDestinada(++id, idBenfeitoria));

        return benfeitoriasDestinadas;
    }

    private BenfeitoriaDestinada criarBenfeitoriaDestinada(Long id, Long idBenfeitoria) {
        BenfeitoriaDestinada benfeitoriaDestinada = new BenfeitoriaDestinada();
        benfeitoriaDestinada.setDestinacaoImovel(destinacaoImovel);
        benfeitoriaDestinada.setIdBenfeitoria(idBenfeitoria);
        benfeitoriaDestinada.setId(id);
        benfeitoriaDestinada.setAreaUtilizar(new BigDecimal(100));
        return benfeitoriaDestinada;
    }

    @Test
    public void salvar() {
        BenfeitoriaDestinada benfeitoriaDestinadaSalva = benfeitoriaDestinadaService.salvar(benfeitoriaDestinada);
        assertNotNull(benfeitoriaDestinadaSalva);
        assertEquals(benfeitoriaDestinadaSalva.getId(), benfeitoriaDestinada.getId());
    }

    @Test
    public void salvarLista() {
         List<BenfeitoriaDestinada> benfeitoriaDestinadaList = new ArrayList<>();
         benfeitoriaDestinadaList.add(benfeitoriaDestinada);
         benfeitoriaDestinada.setAtiva(false);
         when(destinacaoImovel.getBenfeitoriasDestinadas()).thenReturn(benfeitoriaDestinadaList);
         when(benfeitoriaDestinadaRepository.save(benfeitoriaDestinada)).thenReturn(benfeitoriaDestinada);
         List<BenfeitoriaDestinada> listaBenfeitoriaSalva = benfeitoriaDestinadaService.salvar(benfeitoriaDestinadaList, destinacaoImovel, true);
         assertFalse(listaBenfeitoriaSalva.isEmpty());
    }

    @Test
    public void salvarListaFalse() {
         List<BenfeitoriaDestinada> benfeitoriaDestinadaList = new ArrayList<>();
         benfeitoriaDestinadaList.add(benfeitoriaDestinada);
         benfeitoriaDestinada.setAtiva(false);
         when(destinacaoImovel.getBenfeitoriasDestinadas()).thenReturn(benfeitoriaDestinadaList);
         when(benfeitoriaDestinadaRepository.save(benfeitoriaDestinada)).thenReturn(benfeitoriaDestinada);
         List<BenfeitoriaDestinada> listaBenfeitoriaSalva = benfeitoriaDestinadaService.salvar(benfeitoriaDestinadaList, destinacaoImovel, false);
         assertFalse(listaBenfeitoriaSalva.isEmpty());
    }

    @Test
    public void salvarListaVazia() {
        List<BenfeitoriaDestinada> listaBenfeitorias = null;
        List<BenfeitoriaDestinada> listaBenfeitoriaSalva = benfeitoriaDestinadaService.salvar(listaBenfeitorias, destinacaoImovel, Boolean.FALSE);
        assertTrue(listaBenfeitoriaSalva.isEmpty());
    }

    @Test
    public void buscarBenfeitoriasRipImovel() {
        int totalEsperado = TAMANHO_LISTA_BENFEITORIAS_DESTINADAS + 1;
        List<BenfeitoriaDestinada> benfeitoriasDestinadas = benfeitoriaDestinadaService.buscarBenfeitoriasRipImovel("123");
        assertFalse(benfeitoriasDestinadas.isEmpty());
        assertEquals(totalEsperado, benfeitoriasDestinadas.size());
    }

    @Test
    public void buscarBenfeitoriasPorIdRipImovel(){
        when(benfeitoriaDestinadaRepository.buscarBenfeitoriasIdImovelIdBenfeitoria(anyLong(), anyLong())).thenReturn(benfeitoriaDestinada);
        assertNotNull(benfeitoriaDestinadaService.buscarBenfeitoriasPorIdRipImovel(1L,1L));
    }

    @Test
    public void buscarBenfeitoriasPorId(){
        when(benfeitoriaDestinadaRepository.buscarBenfeitoriaPorId(anyLong())).thenReturn(benfeitoriaDestinada);
        assertNotNull(benfeitoriaDestinadaService.buscarBenfeitoriaPorId(1L));
    }

    @Test
    public void buscarBenfeitoriaPorIdNull(){
        assertNull(benfeitoriaDestinadaService.buscarBenfeitoriaPorId(null));
    }

    @Test
    public void buscarMapaBenfeitoriasIdImovel() throws Exception {
        int totalEsperado = TAMANHO_LISTA_BENFEITORIAS_DESTINADAS;
        Map<Long, BigDecimal> mapa = benfeitoriaDestinadaService.buscarMapaBenfeitoriasIdImovel("123");
        assertFalse(mapa.isEmpty());
        assertEquals(totalEsperado, mapa.size());
        assertEquals(new BigDecimal(200), mapa.get(idBenfeitoriaDestinadaMaisUmaVez));
    }

}