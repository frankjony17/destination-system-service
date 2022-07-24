package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.UtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.repository.DestinacaoImovelRepository;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 24/11/16.
 */
@RunWith(PowerMockRunner.class)
public class DestinacaoImovelServiceTest {

    private static final String RIP = "0000122";

    @InjectMocks
    private DestinacaoImovelService destinacaoImovelService;

    @Mock
    private DestinacaoImovelRepository destinacaoImovelRepository;

    @Mock
    private DestinacaoImovel primeiraDestinacaoImovel;

    @Mock
    private DestinacaoImovel segundaDestinacaoImovel;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private DestinacaoImovel novaDestinacaoImovel;

    @Mock
    private ImovelService imovelService;

    @Mock
    private Imovel imovel;

    @Mock
    private Destinacao destinacao;

    @Mock
    private Endereco endereco;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private BenfeitoriaDestinadaService benfeitoriaDestinadaService;

    @Mock
    private GeradorCodigoUtilizacaoImovel geradorCodigoUtilizacaoImovel;

    @Mock
    private List<Arquivo> arquivos;

    @Mock
    private List<BenfeitoriaDestinada> benfeitoriaDestinadaList;

    @Mock
    private BenfeitoriaDestinada benfeitoriaDestinada;

    @Mock
    private DestinacaoImovelDTO destinacaoImovelDTO;


    @Before
    public void setUp() {
        when(destinacaoImovelRepository.save(any(DestinacaoImovel.class))).thenReturn(destinacaoImovel);
        when(imovelService.salvar(any(Imovel.class), any(DestinacaoImovel.class))).thenReturn(imovel);
        when(imovel.getRip()).thenReturn(RIP);
        when(primeiraDestinacaoImovel.getImovel()).thenReturn(imovel);
        when(segundaDestinacaoImovel.getImovel()).thenReturn(imovel);
        when(arquivoService.findListaArquivoById(anyList())).thenReturn(new ArrayList<>());
//        when(benfeitoriaDestinadaService.salvar(anyList(), any(DestinacaoImovel.class), Boolean.FALSE)).thenReturn(new ArrayList<>());
        when(destinacaoImovelRepository.getUltimaUtilizacaoImovel(anyLong())).thenReturn("00000000-0000");
    }


    @Test
    public void salvarGerandoCodigoUtilizacao() {
        DestinacaoImovel destinacaoImovelSalva = destinacaoImovelService.salvar(primeiraDestinacaoImovel, Boolean.FALSE);
        assertNotNull(destinacaoImovelSalva);
    }


    @Test
    public void salvarSemGerarCodigoUtilizacao() {
        when(primeiraDestinacaoImovel.getCodigoUtilizacao()).thenReturn(RIP + "00001");
        DestinacaoImovel destinacaoImovelSalva = destinacaoImovelService.salvar(primeiraDestinacaoImovel, Boolean.FALSE);
        assertNotNull(destinacaoImovelSalva);
    }

    @Test
    public void salvarListaDestinacaoImoveis() {
          Parcela parcela = new Parcela();
          List<Parcela> parcelaList = new ArrayList<>();
          parcelaList.add(parcela);
          parcela.setId(1L);
          when(destinacaoImovel.getBenfeitoriasDestinadas()).thenReturn(benfeitoriaDestinadaList);
          when(benfeitoriaDestinadaService.salvar(benfeitoriaDestinadaList, destinacaoImovel, Boolean.TRUE)).thenReturn(benfeitoriaDestinadaList);
          when(destinacaoImovelRepository.save(destinacaoImovel)).thenReturn(destinacaoImovel);
          when(imovelService.salvar(imovel, destinacaoImovel)).thenReturn(imovel);
          when(imovel.getParcelas()).thenReturn(parcelaList);
          when(destinacaoImovel.getImovel()).thenReturn(imovel);
          when(destinacaoImovel.getParcelas()).thenReturn(parcelaList);
        List<DestinacaoImovel> destinacaoImoveisSalva = destinacaoImovelService.salvar(asList(primeiraDestinacaoImovel));
        assertNotNull(destinacaoImoveisSalva);
        assertFalse(destinacaoImoveisSalva.isEmpty());
    }

    @Test
    public void salvarListaDestinacaoImovelGerandoCodigoUtilizacao() {
        List<DestinacaoImovel> destinacaoImoveis = destinacaoImovelService.salvar(asList(primeiraDestinacaoImovel, segundaDestinacaoImovel), destinacao, Boolean.FALSE);
        assertTrue(!destinacaoImoveis.isEmpty());
    }

    @Test
    public void salvarListaDestinacaoImovelComUmaDestinacaoImovelCodigoUtilizacao() {
        when(primeiraDestinacaoImovel.getCodigoUtilizacao()).thenReturn(RIP + "00001");
        List<DestinacaoImovel> destinacaoImoveis = destinacaoImovelService.salvar(asList(primeiraDestinacaoImovel, segundaDestinacaoImovel), destinacao, Boolean.TRUE);
        assertTrue(!destinacaoImoveis.isEmpty());
    }

    @Test
    public void verificarImovelExteriorRetornandoTrue() {
        when(imovel.getEndereco()).thenReturn(endereco);
        when(endereco.getCidadeExterior()).thenReturn("Londres");
        Boolean imovelExterior = destinacaoImovelService.verificarImovelExterior(asList(primeiraDestinacaoImovel));
        assertTrue(imovelExterior);
    }

    @Test
    public void verificarImovelExteriorRetornandoFalse() {
        when(imovel.getEndereco()).thenReturn(endereco);
        Boolean imovelExterior = destinacaoImovelService.verificarImovelExterior(asList(primeiraDestinacaoImovel));
        assertTrue(!imovelExterior);
    }

    @Test
    public void getUltimaUtilizadaoImovel(){
        String ultimaUtilizadaoImovel = destinacaoImovelService.getUltimaUtilizadaoImovel(1l);
        assertNotNull(ultimaUtilizadaoImovel);
    }

    @Test
    public void salvarDestinacaoImovel(){
        when(imovelService.findById(anyLong())).thenReturn(imovel);
        when(arquivoService.findListaArquivoById(asList())).thenReturn(arquivos);
        DestinacaoImovel destinacaoImovel = destinacaoImovelService.salvar(primeiraDestinacaoImovel, Boolean.FALSE);
        assertNotNull(destinacaoImovel);

    }

    @Test
    public void marcarUltimaDestiancaoPorImovel() {
        when(destinacaoImovel.getCodigoUtilizacao()).thenReturn("0000");
        when(novaDestinacaoImovel.getCodigoUtilizacao()).thenReturn("0001");
        when(destinacaoImovelRepository.buscarDestinacaoImovelPorId(anyLong())).thenReturn(asList(destinacaoImovel, novaDestinacaoImovel));
        destinacaoImovelService.desmarcarUltimaDestiancaoPorImovel(asList(1L), Boolean.TRUE);
    }

    @Test
    public void marcarUltimaDestiancaoPorImovelFalse() {
        when(destinacaoImovel.getCodigoUtilizacao()).thenReturn("0000");
        when(novaDestinacaoImovel.getCodigoUtilizacao()).thenReturn("0001");
        when(destinacaoImovelRepository.buscarDestinacaoImovelPorId(anyLong())).thenReturn(asList(destinacaoImovel, novaDestinacaoImovel));
        destinacaoImovelService.desmarcarUltimaDestiancaoPorImovel(asList(1L), Boolean.FALSE);
    }

    @Test
    public void desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas() {
        when(destinacaoImovel.getCodigoUtilizacao()).thenReturn("0000");
        when(novaDestinacaoImovel.getCodigoUtilizacao()).thenReturn("0001");
        when(destinacaoImovelRepository.buscarDestinacaoImovelPorId(anyLong())).thenReturn(asList(destinacaoImovel, novaDestinacaoImovel));
        destinacaoImovelService.desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas(asList(1L));
    }

    @Test
    public void buscarCodigoUtilizacao(){
        when(destinacaoImovelRepository.buscarCodigoUtilizacao(anyString())).thenReturn(destinacaoImovelDTO);
        DestinacaoImovelDTO destinacaoImovelDTO = destinacaoImovelService.buscarCodigoUtilizacao("rip");
        assertNotNull(destinacaoImovelDTO);
    }

    @Test
    public void findByIdImovelSequencial(){
        when(destinacaoImovelRepository.findByIdImovelSequencial(anyLong())).thenReturn(destinacaoImovel);
        DestinacaoImovel destinacaoImovel = destinacaoImovelService.findByIdImovelSequencial(1L);
        assertNotNull(destinacaoImovel);
    }

    @Test
    public void buscarTodasUtilizacoesPorRip(){
        when(destinacaoImovelRepository.buscarTodasUtilizacoesPorRip(anyString())).thenReturn(Arrays.asList());
        List<UtilizacaoDTO> utilizacaoDTOS = destinacaoImovelService.buscarTodasUtilizacoesPorRip("rip");
        assertNotNull(utilizacaoDTOS);
    }

    @Test
    public void findByDestinacaoImovelById(){
       when(destinacaoImovelRepository.findByDestinacaoImovelById(anyLong())).thenReturn(Arrays.asList());
       List<DestinacaoImovel> destinacaoImovels = destinacaoImovelService.findByDestinacaoImovelById(1L);
       assertNotNull(destinacaoImovels);
    }

    @Test
    public void findByIds(){
        when(destinacaoImovelRepository.findByIds(anySet())).thenReturn(Arrays.asList());
        List<DestinacaoImovel> destinacaoImovels = destinacaoImovelService.findByIds(anySet());
        assertNotNull(destinacaoImovels);
    }

    @Test
    public void findById(){
        when(destinacaoImovelRepository.findOne(1L)).thenReturn(destinacaoImovel);
        DestinacaoImovel destinacaoImovel = destinacaoImovelService.findById(1L);
        assertNotNull(destinacaoImovel);
    }

    @Test
    public void findByIdImovelSequencialParcelaCodigoUtilizacao() {
        when(destinacaoImovelRepository.findByIdImovelSequencialParcelaCodigoUtilizacao(anyLong(), anyString(), anyString())).thenReturn(destinacaoImovel);
        DestinacaoImovel destinacaoImovel = destinacaoImovelService.findByIdImovelSequencialParcelaCodigoUtilizacao(1L, "sequencial", "codigoUtilizacao");
        assertNotNull(destinacaoImovel);
    }

}
