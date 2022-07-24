package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.BenfeitoriaDestinada;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.BenfeitoriaRepository;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.persistence.Id;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 03/02/17.
 */
@RunWith(PowerMockRunner.class)
public class BenfeitoriaServiceTest {

    @InjectMocks
    private BenfeitoriaService benfeitoriaService;

    @Mock
    private BenfeitoriaDestinada benfeitoriaDestinada;

    @Mock
    private Benfeitoria benfeitoria;

    @Mock
    private Imovel imovel;

    @Mock
    private BenfeitoriaRepository benfeitoriaRepository;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private Parcela parcela;

    @Mock
    private Map<Long, Boolean> pendenciasGeradas;

    @Mock
    private ParcelaService parcelaService;

    @Mock
    private BigDecimal bigDecimal;

    @Mock
    private BenfeitoriaDestinadaService benfeitoriaDestinadaService;

    @Before
    public void setUp() {
        when(benfeitoriaDestinada.getIdBenfeitoria()).thenReturn(1L);
        when(benfeitoriaDestinada.getAreaUtilizar()).thenReturn(new BigDecimal(50));
        BigDecimal area = new BigDecimal(100);
        when(benfeitoria.getAreaDisponivel()).thenReturn(area);
        when(benfeitoria.getAreaConstruida()).thenReturn(area);
        when(benfeitoria.getIdBenfeitoriaCadImovel()).thenReturn(1L);
        when(benfeitoriaRepository.save(any(Benfeitoria.class))).thenReturn(benfeitoria);
        when(parcela.getAreaTerreno()).thenReturn(new BigDecimal(100));
        when(parcela.getAreaDisponivel()).thenReturn(new BigDecimal(100));
        when(parcela.getSequencial()).thenReturn("P0");
        when(parcelaService.isParcelaDestinada(any(Parcela.class))).thenReturn(true);
        when(imovel.getBenfeitorias()).thenReturn(Arrays.asList());
        when(imovel.getAreaTerreno()).thenReturn(new BigDecimal(100));
    }

    @Test
    public void salvarLista(){
        when(benfeitoria.getAreaDisponivel()).thenReturn(new BigDecimal(100));
        List<Benfeitoria> benfeitoriasSalvas = benfeitoriaService.salvar(asList(benfeitoria), parcela, imovel);
        assertNotNull(benfeitoriasSalvas);
    }

    @Test
    public void salvarListaNull() {
        when(benfeitoria.getAreaDisponivel()).thenReturn(null);
        List<Benfeitoria> benfeitoriasSalvas = benfeitoriaService.salvar(asList(benfeitoria), parcela, imovel);
        assertNotNull(benfeitoriasSalvas);
    }

    @Test
    public void salvarBenfeitoria(){
        benfeitoriaService.salvar(benfeitoria);
    }

    @Test
    public void atualizarAreaDisponivel(){
        when(benfeitoriaRepository.findOne(anyLong())).thenReturn(benfeitoria);
        when(benfeitoriaRepository.save(any(Benfeitoria.class))).thenReturn(benfeitoria);
        List<BenfeitoriaDestinada> benfeitoriaDestinadas = new ArrayList<>();
        benfeitoriaDestinadas.add(benfeitoriaDestinada);
        benfeitoriaService.atualizarAreaDisponivel(benfeitoriaDestinadas);

    }

    @Test
    public void atualizarAreaDisponivelZero(){
        when(benfeitoriaRepository.save(any(Benfeitoria.class))).thenReturn(benfeitoria);
        List<Benfeitoria> benfeitorias = new ArrayList<>();
        benfeitorias.add(benfeitoria);
        benfeitoriaService.atualizarAreaDisponivelZero(benfeitorias);
    }

    @Test
    @SneakyThrows
    public void atualizar() {
        List<Benfeitoria> benfeitorias =
                benfeitoriaService.atualizar(parcela, criarListaBenfeitorias(), pendenciasGeradas, criarListaBenfeitoriasSalvas(), imovel);
        assertNotNull(benfeitorias);
        assertEquals(3, benfeitorias.size());
    }

    private List<Benfeitoria> criarListaBenfeitorias() {
        return asList(criarBenfeitoria(null, 1L, BigDecimal.TEN), criarBenfeitoria(null, 2L, BigDecimal.TEN), criarBenfeitoria(null, 3L, BigDecimal.TEN));
    }

    private Benfeitoria criarBenfeitoria(Long id, Long idCadImovel, BigDecimal area) {
        Benfeitoria benfeitoria = new Benfeitoria();
        benfeitoria.setAtiva(true);
        benfeitoria.setIdBenfeitoriaCadImovel(idCadImovel);
        benfeitoria.setAreaConstruida(area);
        benfeitoria.setEspecializacao("teste");
        benfeitoria.setCodigo("E1");
        benfeitoria.setNome("teste");
        benfeitoria.setId(id);
        return benfeitoria;
    }

    private List<Benfeitoria> criarListaBenfeitoriasSalvas() {
        Benfeitoria benfeitoriaComParcela = criarBenfeitoria(5L, 5L, BigDecimal.TEN);
        benfeitoriaComParcela.setParcela(parcela);
        return asList(criarBenfeitoria(1L, 1L, BigDecimal.ONE),
                      criarBenfeitoria(2L, 2L, BigDecimal.TEN),
                      criarBenfeitoria(4L, 4L, BigDecimal.TEN),
                      benfeitoriaComParcela);
    }

    @Test
    public void extrairBenfeitorias() {
        List<Benfeitoria> benfeitorias = criarListaBenfeitorias();
        benfeitorias.get(0).setCodigo("C1");
        when(parcela.getBenfeitorias()).thenReturn(benfeitorias);
        when(parcela.getAtiva()).thenReturn(true);
        List<Benfeitoria> benfeitoriasFiltradas = benfeitoriaService.extrairBenfeitorias(asList(parcela));
        assertEquals(2, benfeitoriasFiltradas.size());
    }

    @Test
    public void extrairBenfeitoriasNulas() {
        when(parcela.getBenfeitorias()).thenReturn(null);
        when(parcela.getAtiva()).thenReturn(true);
        List<Benfeitoria> benfeitoriasFiltradas = benfeitoriaService.extrairBenfeitorias(asList(parcela));
        assertTrue(benfeitoriasFiltradas.isEmpty());
    }

    @Test
    public void buscarBenfeitoriasPorImovelIdCadastro() {
        when(benfeitoriaRepository.buscarBenfeitoriasPorImovelIdCadastro(anyLong())).thenReturn(criarListaBenfeitorias());
        List<Benfeitoria> benfeitorias = benfeitoriaService.buscarBenfeitoriasPorImovelIdCadastro(1L);
        assertNotNull(benfeitorias);
        assertEquals(3, benfeitorias.size());
    }

    @Test
    public void extrairBenfeitoriasParcelasSalvas() {
        when(parcela.getBenfeitorias()).thenReturn(criarListaBenfeitorias());
        List<Benfeitoria> benfeitorias = benfeitoriaService.extrairBenfeitoriasParcelasSalvas(asList(parcela));
        assertNotNull(benfeitorias);
        assertEquals(3, benfeitorias.size());
    }

    @Test
    public void buscarBenfeitoriasSemParcelaIdImovel() {
        when(benfeitoriaRepository.buscarBenfeitoriasSemParcelaIdImovel(anyLong())).thenReturn(criarListaBenfeitorias());
        List<Benfeitoria> benfeitorias = benfeitoriaService.buscarBenfeitoriasSemParcelaIdImovel(1L);
        assertNotNull(benfeitorias);
        assertEquals(3, benfeitorias.size());
    }
    @Test
    public void buscarBenfeitoriaPorId (){
        when(benfeitoriaRepository.buscarBenfeitoriaPorId(anyLong())).thenReturn(benfeitoria);
        Benfeitoria benfeitoria = benfeitoriaService.buscarBenfeitoriaPorId(1L);
        assertNotNull(benfeitoria);
    }

    @Test
    public void somAreaConstruida(){
        when(benfeitoriaRepository.sumAreaConstruida(anyString())).thenReturn(bigDecimal);
        BigDecimal bigDecimal = benfeitoriaService.somaAreaConstruida("teste");
        assertNotNull(bigDecimal);
    }

    @Test
    public void inativar(){
        Benfeitoria benfeitoria = new Benfeitoria();
        List<Benfeitoria> benf = asList(benfeitoria);
        benfeitoriaService.inativar(benf);
        assertFalse(benf.get(0).getAtiva());
    }

    @Test
    public void atualizarAreaDisponivelBenfeitoriaNull(){
        List<Benfeitoria> benfeitoriaList = new ArrayList<>();
        benfeitoriaList.add(benfeitoria);
        benfeitoria.setId(1L);
        when(imovel.getBenfeitorias()).thenReturn(benfeitoriaList);
        when(benfeitoriaDestinadaService.buscarBenfeitoriasPorIdRipImovel(anyLong(), anyLong())).thenReturn(null);
        when(imovel.getId()).thenReturn(1L);
        when(benfeitoria.getId()).thenReturn(2L);
        when(benfeitoria.getAreaConstruida()).thenReturn(new BigDecimal(100));
        when(benfeitoriaDestinada.getAreaUtilizar()).thenReturn(new BigDecimal(200));
        benfeitoriaService.atualizarAreaDisponivelBenfeitoria(imovel, Boolean.TRUE);
        assertNotNull(benfeitoriaList);
    }

    @Test
    public void atualizarAreaDisponivelBenfeitoria(){
        List<Benfeitoria> benfeitoriaList = new ArrayList<>();
        benfeitoriaList.add(benfeitoria);
        benfeitoria.setId(1L);
        when(imovel.getBenfeitorias()).thenReturn(benfeitoriaList);
        when(benfeitoriaDestinadaService.buscarBenfeitoriasPorIdRipImovel(anyLong(), anyLong())).thenReturn(benfeitoriaDestinada);
        when(imovel.getId()).thenReturn(1L);
        when(benfeitoria.getId()).thenReturn(2L);
        when(benfeitoria.getAreaConstruida()).thenReturn(new BigDecimal(100));
        when(benfeitoriaDestinada.getAreaUtilizar()).thenReturn(new BigDecimal(200));
        benfeitoriaService.atualizarAreaDisponivelBenfeitoria(imovel, Boolean.TRUE);
        assertNotNull(benfeitoriaList);
    }
}