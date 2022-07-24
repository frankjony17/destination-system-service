package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.dto.BenfeitoriaDTO;
import br.com.company.fks.destinacao.dominio.dto.ParcelaDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ParcelaRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anySet;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 02/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParcelaServiceTest {

    @InjectMocks
    private ParcelaService parcelaService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private ParcelaRepository parcelaRepository;

    @Mock
    private Parcela parcela;

    @Mock
    private Imovel imovel;

    @Mock
    private ImovelService imovelService;

    @Mock
    private Benfeitoria benfeitoriaUm;

    @Mock
    private Benfeitoria benfeitoriaDois;

    @Mock
    private BenfeitoriaService benfeitoriaService;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private DestinacaoImovelService destinacaoImovelService;

    @Mock
    private List<Parcela> parcelas;

    @Mock
    private List<ArquivoDTO> arquivoDTOS;

    @Mock
    private ParcelaDTO parcelaDTONova;

    @Mock
    private ParcelaDTO parcelaDTORemanescente;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private List<DestinacaoImovel> destinacaoImoveis;

    @Mock
    private Arquivo arquivo;

    @Mock
    private Parcela parcelaExcluida;

    @Mock
    private Parcela parcelaAtribuida;

    @Mock
    private ArquivoDTO arquivoDTO;

    @Mock
    private ParcelaDTO parcelaDTOs;

    @Mock
    private Benfeitoria benfeitoria;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private Destinacao destinacao;

    @Mock
    private BenfeitoriaDTO benfeitoriaDTO;

    @Before
    public void setUp() {
        when(imovelService.findById(anyLong())).thenReturn(imovel);
        when(destinacaoImovelService.findByIds(anySet())).thenReturn(asList(destinacaoImovel));
        when(parcelaRepository.findOne(anyLong())).thenReturn(parcela);
        when(parcelaRepository.save(any(Parcela.class))).thenReturn(parcela);
        when(imovelService.buscarImoveisPorRip(anyString())).thenReturn(Arrays.asList(imovel));
        when(entityConverter.converterStrict(any(ParcelaDTO.class), eq(Parcela.class))).thenReturn(parcela);
        when(imovel.getRip()).thenReturn("00000007");
        when(parcela.getArquivos()).thenReturn(asList(arquivo));
        when(parcela.getAreaTerreno()).thenReturn(BigDecimal.TEN);
        when(parcela.getId()).thenReturn(1L);
        Type type = new TypeToken<List<ParcelaDTO>>() {}.getType();
        when(entityConverter.converterListaStrictLazyLoading(anyList(), Matchers.<Type>any())).thenReturn(asList(parcelaDTONova));
    }

    private Parcela criarParcela() {
        Set<Long> idsDestinacaoImoveis = new HashSet<>();
        idsDestinacaoImoveis.add(3L);
        Parcela parcela = new Parcela();
        parcela.setId(1L);
        parcela.setAreaTerreno(BigDecimal.TEN);
        parcela.setSequencial("P0");
        parcela.setAreaTerreno(BigDecimal.TEN);
        parcela.setAreaDisponivel(BigDecimal.TEN);
        parcela.setImovel(imovel);
        List<Benfeitoria> benfeitorias = new ArrayList<>();
        benfeitorias.add(benfeitoriaUm);
        List<Arquivo> arquivos = new ArrayList<>();
        parcela.setBenfeitorias(benfeitorias);
        parcela.setArquivos(arquivos);
        parcela.setIdDestinacaoImoveis(idsDestinacaoImoveis);
        parcela.setDestinacaoImoveis(destinacaoImoveis);
        return parcela;
    }

    @Test
    public void criarNovasParcelas() {
        when(parcelaDTONova.getSequencial()).thenReturn("P1");
        when(parcelaDTONova.getAreaTerreno()).thenReturn(BigDecimal.TEN);
        when(parcelaDTONova.getIdParcelaInativar()).thenReturn(1L);
        when(parcelaDTONova.getIdImovel()).thenReturn(1L);
        Set<Long> idsDestinacaoImoveis = new HashSet<>();
        idsDestinacaoImoveis.add(1L);
        when(parcelaDTONova.getIdDestinacaoImoveis()).thenReturn(idsDestinacaoImoveis);

        when(parcelaDTORemanescente.getSequencial()).thenReturn("P2");
        when(parcelaDTORemanescente.getAreaTerreno()).thenReturn(BigDecimal.TEN);
        when(parcelaDTORemanescente.getIdParcelaInativar()).thenReturn(1L);
        when(parcelaDTORemanescente.getIdImovel()).thenReturn(1L);
        when(parcelaDTONova.getIdDestinacaoImoveis()).thenReturn(idsDestinacaoImoveis);

        List<Parcela> parcelas = parcelaService.criarNovasParcelas(asList(parcelaDTONova, parcelaDTORemanescente));
        assertNotNull(parcelas);

    }

    @Test
    public void inativar() {
        Parcela parcela = new Parcela();
        List<Parcela> parcelas = asList(parcela);
        parcelaService.inativar(parcelas);
        assertFalse(parcelas.get(0).getAtiva());
    }

    @Test
    public void salvarParcelaInicial() {
        Parcela parcelaCriada = parcelaService.salvarParcelaInicial(imovel, destinacaoImovel);
        assertNotNull(parcelaCriada);
    }

    @Test
    public void atualizarAreaDisponivel() {
        Parcela parcelaAtualizada = parcelaService.atualizarAreaDisponivel(1L, BigDecimal.TEN);
        assertNotNull(parcelaAtualizada);
    }

    @Test
    public void excluirParcelaComDuasParcelasAtivas() {
        when(parcelaRepository.buscarNumeroParcelas(anyString())).thenReturn(2);
        when(imovelService.buscarPorRip(anyString())).thenReturn(imovel);
        when(parcelaRepository.findByIdDestinacaoImovelParcelaInicial(anySet())).thenReturn(parcela);
        when(parcelaExcluida.getId()).thenReturn(1L);
        when(parcelaAtribuida.getId()).thenReturn(2L);
        parcelaService.excluirParcela(parcelaExcluida, parcelaAtribuida, "00000007");
    }

    @Test
    public void excluirParcelaDifenteDuasAtivas() {
        when(parcelaRepository.buscarNumeroParcelas(anyString())).thenReturn(3);
        when(imovelService.buscarPorRip(anyString())).thenReturn(imovel);
        when(imovelService.buscarImoveisPorRip(anyString())).thenReturn(asList(imovel));
        when(parcelaRepository.findByIdDestinacaoImovelParcelaInicial(anySet())).thenReturn(parcela);
        when(parcelaExcluida.getId()).thenReturn(1L);
        when(parcelaExcluida.getAreaTerreno()).thenReturn(BigDecimal.TEN);
        when(parcelaExcluida.getBenfeitorias()).thenReturn(asList(benfeitoriaUm));
        when(parcelaExcluida.getImovel()).thenReturn(imovel);
        when(parcelaAtribuida.getId()).thenReturn(2L);
        when(parcelaAtribuida.getAreaTerreno()).thenReturn(BigDecimal.TEN);
        when(parcelaAtribuida.getBenfeitorias()).thenReturn(asList(benfeitoriaDois));
        when(parcela.getSequencial()).thenReturn("P2");
        when(imovel.getParcelas()).thenReturn(asList(parcela));
        parcelaService.excluirParcela(parcelaExcluida, parcelaAtribuida, "00000007");
    }

    @Test
    public void atualizar() {
        when(destinacaoImovel.getFracaoIdeal()).thenReturn(BigDecimal.ONE);
        List<Parcela> parcelasAtualizadas = parcelaService.atualizar(asList(parcela), destinacaoImovel);
        assertFalse(parcelasAtualizadas.isEmpty());
    }

    @Test
    public void atualizarParcelaNula() {
        when(destinacaoImovel.getFracaoIdeal()).thenReturn(BigDecimal.ONE);
        List<Parcela> parcelasAtualizadas = parcelaService.atualizar(null, destinacaoImovel);
        assertTrue(parcelasAtualizadas.isEmpty());
    }

    @Test
    public void getUltimaParcela() {
        Parcela primeiraParcela = new Parcela();
        primeiraParcela.setId(1L);
        primeiraParcela.setSequencial("P1");
        Parcela segundaParcela = new Parcela();
        segundaParcela.setId(2L);
        segundaParcela.setSequencial("P2");
        String sequencial = parcelaService.getUltimaParcela(asList(primeiraParcela, segundaParcela));
        assertEquals("P2", sequencial);
    }

    @Test
    public void buscarParcelasSemUso() {
        when(parcelaRepository.buscarParcelasSemUso(anyString(), anyString())).thenReturn(asList(parcela));
        List<ParcelaDTO> parcelas = parcelaService.buscarParcelasSemUso("00000007", "00000");
        assertFalse(parcelas.isEmpty());
    }

    @Test
    public void buscarParcelasCanceladas() {
        when(parcelaRepository.buscarParcelasCanceladas(anyString())).thenReturn(asList(parcela));
        List<ParcelaDTO> parcelas = parcelaService.buscarParcelasCanceladas("00000007");
        assertFalse(parcelas.isEmpty());
    }

    @Test
    public void buscarIdsParcelasUtilizadasPorImovelId() {
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        when(parcelaRepository.buscarIdsParcelasUtilizadasPorImovelId(anyLong())).thenReturn(ids);
        Set<Long> idsParcelasUtilizadas = parcelaService.buscarIdsParcelasUtilizadasPorImovelId(1L);
        assertFalse(idsParcelasUtilizadas.isEmpty());
    }

    @Test
    public void buscarIdsParcelasUtilizadasPorImovelIdRetornoVazio() {
        when(parcelaRepository.buscarIdsParcelasUtilizadasPorImovelId(anyLong())).thenReturn(null);
        Set<Long> idsParcelasUtilizadas = parcelaService.buscarIdsParcelasUtilizadasPorImovelId(1L);
        assertTrue(idsParcelasUtilizadas.isEmpty());
    }

    @Test
    public void isUtilizada() {
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        when(parcela.getId()).thenReturn(1L);
        Boolean utilizada = parcelaService.isUtilizada(parcela, ids);
        assertTrue(utilizada);
    }

    @Test
    public void isParcelaDestinada() {
        when(parcelaRepository.contarQuantidadeParcelaDestinacao(anyLong())).thenReturn(2);
        when(parcela.getId()).thenReturn(1L);
        Boolean destinada = parcelaService.isParcelaDestinada(parcela);
        assertTrue(destinada);
    }

    @Test
    public void isParcelaDestinadaParcelaFalse() {
        when(parcelaRepository.contarQuantidadeParcelaDestinacao(anyLong())).thenReturn(2);
        when(parcela.getId()).thenReturn(1L);
        Boolean destinada = parcelaService.isParcelaDestinada(null);
        assertFalse(destinada);
    }

    @Test
    @SneakyThrows
    public void editar() {
        when(parcelaDTONova.getId()).thenReturn(1L);
        when(arquivoDTO.getId()).thenReturn(1L);
        when(parcelaDTONova.getArquivos()).thenReturn(asList(arquivoDTO));
        when(entityConverter.converterStrict(any(Parcela.class), eq(ParcelaDTO.class))).thenReturn(parcelaDTORemanescente);
        ParcelaDTO parcelaEditada = parcelaService.editar(parcelaDTONova);
        assertNotNull(parcelaEditada);
    }

    @Test
    @SneakyThrows
    public void editarArquivoNulo() {
        when(parcelaDTONova.getId()).thenReturn(1L);
        when(arquivoDTO.getId()).thenReturn(1L);
        when(parcelaDTONova.getArquivos()).thenReturn(null);
        when(entityConverter.converterStrict(any(Parcela.class), eq(ParcelaDTO.class))).thenReturn(parcelaDTORemanescente);
        ParcelaDTO parcelaEditada = parcelaService.editar(parcelaDTONova);
        assertNotNull(parcelaEditada);
    }

    @Test
    public void extrairParcelasAtivas() {
        Parcela parcelaAtiva = new Parcela();
        parcelaAtiva.setAtiva(true);
        Parcela parcelaInativa = new Parcela();
        parcelaInativa.setAtiva(false);
        List<Parcela> parcelasAtivas = parcelaService.extrairParcelasAtivas(asList(parcelaAtiva, parcelaInativa));
        assertEquals(1, parcelasAtivas.size());
    }

    @Test
    public void buscarParcelasPorIdImovel(){
        when(parcelaRepository.buscarParcelasPorId(anyString())).thenReturn(asList(parcela));
        List<ParcelaDTO> parcelas = parcelaService.buscarParcelasPorIdImovel("Testes");
        assertFalse(parcelas.isEmpty());
    }

    @Test
    public void buscarParcelaPorIdImovelSequencial(){
        when(parcelaRepository.buscarParcelaPorIdImovelSequencial(anyLong(),anyString())).thenReturn(parcela);
        Parcela buscarParcela = parcelaService.buscarParcelaPorIdImovelSequencial(1L, "Sequencial");
        assertNotNull(buscarParcela);
    }

    @Test
    public void salvarListaParcelasFalse() throws NegocioException {
        List<ParcelaDTO> parcelas = new ArrayList<>();
        parcelas.add(parcelaDTOs);
        parcelaDTOs.setId(1L);
        when(parcelaRepository.findOne(anyLong())).thenReturn(parcela);
        when(parcelaDTOs.getId()).thenReturn(1L);
        when(parcela.getAtiva()).thenReturn(false);
        List<BenfeitoriaDTO> benfeitoriaDTOList = new ArrayList<>();
        benfeitoriaDTOList.add(benfeitoriaDTO);
        benfeitoria.setId(1L);
        when(parcelaDTOs.getBenfeitorias()).thenReturn(benfeitoriaDTOList);
        List<Benfeitoria> benfeitoriaList = new ArrayList<>();
        benfeitoriaList.add(benfeitoria);
        benfeitoria.setId(1L);
        when(parcela.getBenfeitorias()).thenReturn(benfeitoriaList);
        when(benfeitoria.getAtiva()).thenReturn(true);
        parcelaService.salvarListaParcelas(parcelas);
    }

    @Test
    public void salvarListaParcelasNull() throws NegocioException {
        List<ParcelaDTO> parcelas = new ArrayList<>();
        parcelas.add(parcelaDTOs);
        parcelaDTOs.setId(1L);
        when(parcelaRepository.findOne(anyLong())).thenReturn(parcela);
        when(parcelaDTOs.getId()).thenReturn(1L);
        when(parcela.getAtiva()).thenReturn(false);
        List<BenfeitoriaDTO> benfeitoriaDTOList = new ArrayList<>();
        benfeitoriaDTOList.add(benfeitoriaDTO);
        benfeitoria.setId(1L);
        when(parcelaDTOs.getBenfeitorias()).thenReturn(benfeitoriaDTOList);
        List<Benfeitoria> benfeitoriaList = new ArrayList<>();
        when(parcela.getBenfeitorias()).thenReturn(benfeitoriaList);
        when(benfeitoria.getAtiva()).thenReturn(true);
        parcelaService.salvarListaParcelas(parcelas);
    }

    @Test
    public void salvarListaParcelas() throws NegocioException {
        List<ParcelaDTO> parcelas = new ArrayList<>();
        parcelas.add(parcelaDTOs);
        parcelaDTOs.setId(1L);
        when(parcelaRepository.findOne(anyLong())).thenReturn(parcela);
        when(parcelaDTOs.getId()).thenReturn(1L);
        when(parcela.getAtiva()).thenReturn(true);
        List<Benfeitoria> benfeitoriaList = new ArrayList<>();
        benfeitoriaList.add(benfeitoria);
        benfeitoria.setId(1L);
        when(parcela.getBenfeitorias()).thenReturn(benfeitoriaList);
        when(benfeitoria.getParcela()).thenReturn(parcela);
        when(benfeitoria.getParcela()).thenReturn(parcela);
        List<DestinacaoImovel> destinacaoImovelList = new ArrayList<>();
        destinacaoImovelList.add(destinacaoImovel);
        destinacaoImovel.setId(1L);
        when(parcela.getDestinacaoImoveis()).thenReturn(destinacaoImovelList);
        when(destinacaoImovel.getUltimaDestinacao()).thenReturn(true);
        when(destinacaoImovel.getCodigoUtilizacao()).thenReturn("1");
        when(parcelaDTOs.getAreaTerreno()).thenReturn(new BigDecimal(100));
        destinacaoPendenciaService.gerarPendencia(destinacao, 1L);
        when(destinacaoImovel.getDestinacao()).thenReturn(destinacao);
        when(parcela.getParcelacomPendenciaDestinacao()).thenReturn(true);
        parcelaService.salvarListaParcelas(parcelas);
    }

    @Test
    public void buscarParcelaPorId(){
        when(parcelaRepository.buscarParcelaPorId(anyLong())).thenReturn(parcela);
        Parcela buscarParcela = parcelaService.buscarParcelaPorId(1L);
        assertNotNull(buscarParcela);
    }

    @Test
    public void salvarParcela(){
        when(parcelaRepository.save(parcela)).thenReturn(parcela);
        Parcela buscarParcela = parcelaService.salvarParcela(parcela);
        assertNotNull(buscarParcela);
    }
}