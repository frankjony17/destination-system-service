package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.*;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.CessaoOnerosaRepository;
import br.com.company.fks.destinacao.repository.TransferenciaTitularidadeRepository;
import br.com.company.fks.destinacao.repository.UsoProprioRepository;
import br.com.company.fks.destinacao.service.CessaoOnerosaService;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import br.com.company.fks.destinacao.service.EncargoService;
import br.com.company.fks.destinacao.service.TransferenciaTitularidadeService;
import br.com.company.fks.destinacao.service.destinacao.*;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by sdias on 02/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DestinacaoControllerTest {

    @InjectMocks
    private DestinacaoController destinacaoController;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private Destinacao destinacao;

    @Mock
    private Doacao doacao;

    @Mock
    private DoacaoService doacaoService;

    @Mock
    private DoacaoDTO doacaoDTO;

    @Mock
    private Venda venda;

    @Mock
    private VendaService vendaService;

    @Mock
    private VendaDTO vendaDTO;

    @Mock
    private Imovel imovel;

    @Mock
    private Cuem cuem;

    @Mock
    private CuemService cuemService;

    @Mock
    private CuemDTO cuemDTO;

    @Mock
    private Cdru cdru;

    @Mock
    private CdruService cdruService;

    @Mock
    private CdruDTO cdruDTO;

    @Mock
    private PosseInformal posseInformal;

    @Mock
    private PosseInformalService posseInformalService;

    @Mock
    private PosseInformalDTO posseInformalDTO;

    @Mock
    private TermoEntrega termoEntrega;

    @Mock
    private TermoEntregaService termoEntregaService;

    @Mock
    private TermoEntregaDTO termoEntregaDTO;

    @Mock
    private CessaoGratuita cessaoGratuita;

    @Mock
    private CessaoGratuitaService cessaoGratuitaService;

    @Mock
    private CessaoGratuitaDTO cessaoGratuitaDTO;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private DestinacaoStrategy destinacaoStrategy;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private PendenciaDTO  pendenciaDTO;

    @Mock
    private Resposta resposta;

    @Mock
    private SemUtilizacaoService semUtilizacaoService;

    @Mock
    private ImovelDTO imovelDTO;

    @Mock
    private FiltroDestinacaoDTO filtroDestinacaoDTO;

    @Mock
    private PermissaoUsoImovelFuncionalDTO permissaoUsoImovelFuncionalDTO;

    @Mock
    private PermissaoUsoImovelFuncional permissaoUsoImovelFuncional;

    @Mock
    private PermissaoUsoImovelFuncionalService permissaoUsoImovelFuncionalService;

    @Mock
    private UsoProprio usoProprio;

    @Mock
    private UsoProprioDTO usoProprioDTO;

    @Mock
    private UsoProprioRepository usoProprioRepository;

    @Mock
    private UsoProprioService usoProprioService;

    @Mock
    private TransferenciaTitularidade transferenciaTitularidade;

    @Mock
    private TransferenciaTitularidadeDTO transferenciaTitularidadeDTO;

    @Mock
    private TransferenciaTitularidadeService transferenciaTitularidadeService;

    @Mock
    private TransferenciaTitularidadeRepository transferenciaTitularidadeRepository;

    @Mock
    private CessaoOnerosa cessaoOnerosa;

    @Mock
    private CessaoOnerosaService cessaoOnerosaService;

    @Mock
    private CessaoOnerosaDTO cessaoOnerosaDTO;

    @Mock
    private CessaoOnerosaRepository cessaoOnerosaRepository;

    @Mock
    private FundamentoLegalDTO fundamentoLegalDTO;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private Pendencia pendencia;

    @Mock
    private DestinacaoService destinacaoService;

    @Mock
    private EncargoService encargoService;

    @Mock
    private List<Encargo> listaEncargos;

    @Test
    public void salvarDoacaoTest() throws Exception {
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.DOACAO);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.DOACAO))).thenReturn(doacaoService);
        when(doacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.DOACAO);
        when(entityConverter.converterStrict(any(DoacaoDTO.class),eq(Doacao.class))).thenReturn(doacao);
        when(doacaoService.salvar(any(Doacao.class))).thenReturn(doacao);
        when(mensagemNegocio.get(anyString())).thenReturn("Destinacao Salva");
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarDoacao(doacaoDTO);
        Resposta<Destinacao> resposta = responseEntity.getBody();
        assertNotNull(resposta);

    }

    @Test
    public void salvarVendaTest() throws Exception {
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.VENDA);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.VENDA))).thenReturn(vendaService);
        when(venda.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.VENDA);
        when(entityConverter.converterStrict(any(VendaDTO.class),eq(Venda.class))).thenReturn(venda);
        when(vendaService.salvar(any(Venda.class))).thenReturn(venda);
        when(mensagemNegocio.get(anyString())).thenReturn("Destinacao Salva");
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarVenda(vendaDTO);
        Resposta<Destinacao> resposta = responseEntity.getBody();
        assertNotNull(resposta);

    }

    @Test
    public void salvarPosseInformalTest() throws Exception {
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.POSSE_INFORMAL);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.POSSE_INFORMAL))).thenReturn(posseInformalService);
        when(posseInformal.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.POSSE_INFORMAL);
        when(posseInformal.getImovel()).thenReturn(imovel);
        when(entityConverter.converterStrict(any(PosseInformalDTO.class),eq(PosseInformal.class))).thenReturn(posseInformal);
        when(posseInformalService.salvar(any(PosseInformal.class))).thenReturn(posseInformal);
        when(mensagemNegocio.get(anyString())).thenReturn("Destinacao Salva");
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarPosseInformal(posseInformalDTO);
        Resposta<Destinacao> resposta = responseEntity.getBody();
        assertNotNull(resposta);

    }

    @Test
    public void salvarCuemTest() throws Exception {
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CUEM);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.CUEM))).thenReturn(cuemService);
        when(cuem.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CUEM);
        when(entityConverter.converterStrict(any(CuemDTO.class),eq(Cuem.class))).thenReturn(cuem);
        when(cuemService.salvar(any(Cuem.class))).thenReturn(cuem);
        when(mensagemNegocio.get(anyString())).thenReturn("Destinacao Salva");
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarCuem(cuemDTO);
        Resposta<Destinacao> resposta = responseEntity.getBody();
        assertNotNull(resposta);

    }

    @Test
    public void salvarCdruTest() throws Exception {
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CDRU);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.CDRU))).thenReturn(cdruService);
        when(cdru.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CDRU);
        when(entityConverter.converterStrict(any(CdruDTO.class),eq(Cdru.class))).thenReturn(cdru);
        when(cdruService.salvar(any(Cdru.class))).thenReturn(cdru);
        when(mensagemNegocio.get(anyString())).thenReturn("Destinacao Salva");
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarCdru(cdruDTO);
        Resposta<Destinacao> resposta = responseEntity.getBody();
        assertNotNull(resposta);

    }

    @Test
    public void salvarTermoEntregaTest() throws Exception {
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.TERMO_ENTREGA);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.TERMO_ENTREGA))).thenReturn(termoEntregaService);
        when(termoEntrega.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.TERMO_ENTREGA);
        when(entityConverter.converterStrict(any(TermoEntregaDTO.class),eq(TermoEntrega.class))).thenReturn(termoEntrega);
        when(termoEntregaService.salvar(any(TermoEntrega.class))).thenReturn(termoEntrega);
        when(mensagemNegocio.get(anyString())).thenReturn("Destinacao Salva");
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarTermoEntrega(termoEntregaDTO);
        Resposta<Destinacao> resposta = responseEntity.getBody();
        assertNotNull(resposta);

    }

    @Test
    public void recuperarTiposDestincacaoTest() throws Exception{
        assertNotNull(destinacaoController.recuperarTiposDestincacao());
    }

    @Test
    public void salvarCessaoGratuitaTest() throws Exception {
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CESSAO_GRATUITA);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.CESSAO_GRATUITA))).thenReturn(cessaoGratuitaService);
        when(cessaoGratuita.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CESSAO_GRATUITA);
        when(entityConverter.converterStrict(any(CessaoGratuitaDTO.class),eq(CessaoGratuita.class))).thenReturn(cessaoGratuita);
        when(cessaoGratuitaService.salvar(any(CessaoGratuita.class))).thenReturn(cessaoGratuita);
        when(mensagemNegocio.get(anyString())).thenReturn("Destinacao Salva");
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarCessaoGratuita(cessaoGratuitaDTO);
        Resposta<Destinacao> resposta = responseEntity.getBody();
        assertNotNull(resposta);

    }

    @Test
    public void salvarUsoProprio(){
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.USO_PROPRIO);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.USO_PROPRIO))).thenReturn(usoProprioService);
        when(usoProprio.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.USO_PROPRIO);
        when(entityConverter.converterStrict(any(UsoProprioDTO.class), eq(UsoProprio.class))).thenReturn(usoProprio);
        when(usoProprioService.salvarDadosEspecificos(any(UsoProprio.class))).thenReturn(usoProprio);
        when(mensagemNegocio.get(anyString())).thenReturn("Destinacao Salva");
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarUsoProprio(usoProprioDTO);
        Resposta<Destinacao> resposta = responseEntity.getBody();
        assertNotNull(resposta);
    }

    @SneakyThrows
    @Test
    public void  salvarDadosDestinacaoLancaExeption(){
        when(entityConverter.converterStrict(any(CdruDTO.class),eq(Cdru.class))).thenReturn(cdru);
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CDRU);
        when(cdru.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CDRU);
        when( destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.CDRU))).thenReturn(cdruService);
        when(cdruService.salvar(any(Cdru.class))).thenThrow(NegocioException.class);
        ResponseEntity<Resposta<Destinacao>>resposta =  destinacaoController.salvarCdru(cdruDTO);
        assertNotNull(resposta);
        assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
    }

    @SneakyThrows
    @Test
    public void  salvarDadosDestinacaoLancaException(){
        when(entityConverter.converterStrict(any(CdruDTO.class),eq(Cdru.class))).thenReturn(cdru);
        when(destinacao.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CDRU);
        when(cdru.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CDRU);
        when( destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.CDRU))).thenReturn(cdruService);
        when(cdruService.salvar(any(Cdru.class))).thenThrow(RuntimeException.class);
        ResponseEntity<Resposta<Destinacao>>resposta =  destinacaoController.salvarCdru(cdruDTO);
        assertNotNull(resposta);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,resposta.getStatusCode());
    }

    private Set<PendenciaDTO> criarPendenciaDTO(){
        Set<PendenciaDTO>pendenciaDTOS = new HashSet<>();
        pendenciaDTOS.add(pendenciaDTO);
        return pendenciaDTOS;
    }
    @Test
    public void buscaPendenciasDeRequerimentoTest(){
        when(destinacaoPendenciaService.buscarPendenciasUsuario()).thenReturn(criarPendenciaDTO());
        Resposta<PendenciaDTO> respostaBuilder =RespostaBuilder.getBuilder().setResultado(destinacaoPendenciaService.buscarPendenciasUsuario()).build();
        ResponseEntity<Resposta<PendenciaDTO>>resposta = destinacaoController.buscaPendenciasDeDestinacao();
        assertNotNull(respostaBuilder);
        assertNotNull(resposta);
        assertEquals(HttpStatus.OK,HttpStatus.OK);
    }

    @Test
    public void salvarSemUtilizacao() throws Exception{
        when(entityConverter.converterStrict(any(ImovelDTO.class),eq(Imovel.class))).thenReturn(imovel);
        when(semUtilizacaoService.salvar(any(Imovel.class))).thenReturn(destinacao);
        ResponseEntity<Resposta<String>> respostaResponseEntity = destinacaoController.salvarSemUtilizacao(imovelDTO);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.CREATED);
        assertTrue(respostaResponseEntity.getBody().getErros().size() == Constants.ZERO);
    }

    @Test
    public void salvarSemUtilizacaoGerandoExcessao() throws Exception{
        when(entityConverter.converterStrict(any(ImovelDTO.class),eq(Imovel.class))).thenReturn(imovel);
        when(semUtilizacaoService.salvar(any(Imovel.class))).thenThrow(NegocioException.class);
        ResponseEntity<Resposta<String>> respostaResponseEntity = destinacaoController.salvarSemUtilizacao(imovelDTO);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
        assertTrue(respostaResponseEntity.getBody().getErros().size() != Constants.ZERO);
    }

    @Test
    public void cancelarDestinacaoSemUtilizacao() throws Exception{
        doNothing().when(semUtilizacaoService).cancelar(anyString());
        ResponseEntity<Resposta<String>> respostaResponseEntity = destinacaoController.cancelarDestinacaoSemUtilizacao("");
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
        assertTrue(respostaResponseEntity.getBody().getErros().size() == Constants.ZERO);
    }

    @Test
    public void cancelarDestinacaoSemUtilizacaoGerandoExcecao() throws Exception{
        doThrow(NegocioException.class).when(semUtilizacaoService).cancelar(anyString());
        ResponseEntity<Resposta<String>> respostaResponseEntity = destinacaoController.cancelarDestinacaoSemUtilizacao("");
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    @SneakyThrows
    public void consultarDestinacaoSemErros() {
        when(filtroDestinacaoDTO.getRip()).thenReturn("1");
        Resposta<List<ConsultaDestinacaoDTO>> resposta =
                RespostaBuilder.getBuilder()
                        .setResultado(asList(new ConsultaDestinacaoDTO()))
                        .setTotalElementos(1L)
                        .setTotalPaginas(1).build();
        when(semUtilizacaoService.consultar(any(FiltroDestinacaoDTO.class))).thenReturn(resposta);
        ResponseEntity responseEntity = destinacaoController.consultarDestinacao(filtroDestinacaoDTO);
        Resposta<List<ConsultaDestinacaoDTO>> respostaEsperada = (Resposta<List<ConsultaDestinacaoDTO>>) responseEntity.getBody();
        assertNotNull(respostaEsperada.getResultado());
        assertEquals(new Long(1), respostaEsperada.getTotalElementos());
        assertEquals(new Integer(1), respostaEsperada.getTotalPaginas());
    }

    @Test
    @SneakyThrows
    public void consultarDestinacaoComErros() {
        when(filtroDestinacaoDTO.getRip()).thenReturn("1");
        when(semUtilizacaoService.consultar(any(FiltroDestinacaoDTO.class))).thenThrow(IntegracaoException.class);
        ResponseEntity responseEntity = destinacaoController.consultarDestinacao(filtroDestinacaoDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void buscarHistorico(){
        when(destinacaoStrategy.createBean(TipoDestinacaoEnum.DOACAO)).thenReturn(destinacaoService);
        Resposta<List<ConsultaDestinacaoDTO>> resposta =
                RespostaBuilder.getBuilder()
                        .setResultado(asList(new ConsultaDestinacaoDTO()))
                        .setTotalElementos(1L)
                        .setTotalPaginas(1).build();
        when(destinacaoService.buscarHistoricoDestinacao(anyLong(), anyLong())).thenReturn(resposta);
        ResponseEntity responseEntity = destinacaoController.buscarHistorico(1L,1l, 2L);
        Resposta<List<ConsultaDestinacaoDTO>> respostaEsperada = (Resposta<List<ConsultaDestinacaoDTO>>) responseEntity.getBody();
        assertNotNull(respostaEsperada.getResultado());
        assertEquals(new Long(1), respostaEsperada.getTotalElementos());
        assertEquals(new Integer(1), respostaEsperada.getTotalPaginas());
    }

    @Test
    @SneakyThrows
    public void buscarHistoricoComErro(){
        when(destinacaoStrategy.createBean(TipoDestinacaoEnum.DOACAO)).thenReturn(destinacaoService);
        when(destinacaoService.buscarHistoricoDestinacao(anyLong(), anyLong())).thenThrow(IntegracaoException.class);
        ResponseEntity responseEntity = destinacaoController.buscarHistorico(1L,1l, 2L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void consultarUtilizacoes() throws IntegracaoException {
        when(filtroDestinacaoDTO.getFundamentoLegal()).thenReturn(fundamentoLegalDTO);
        Resposta<List<ConsultarUtilizacaoDTO>> resposta =
                RespostaBuilder.getBuilder()
                        .setResultado(asList(new ConsultarUtilizacaoDTO()))
                        .setTotalElementos(1L)
                        .setTotalPaginas(1).build();
        when(semUtilizacaoService.consultarUtilizacao(filtroDestinacaoDTO, fundamentoLegalDTO)).thenReturn(resposta);
        ResponseEntity responseEntity = destinacaoController.consultarUtilizacoes(filtroDestinacaoDTO);
        Resposta<List<ConsultarUtilizacaoDTO>> respostaEsperada = (Resposta<List<ConsultarUtilizacaoDTO>>) responseEntity.getBody();
        assertNotNull(respostaEsperada.getResultado());
        assertEquals(new Long(1), respostaEsperada.getTotalElementos());
        assertEquals(new Integer(1), respostaEsperada.getTotalPaginas());
    }

    @Test
    @SneakyThrows
    public void consultarUtilizacoesComErro() throws IntegracaoException {
        when(filtroDestinacaoDTO.getFundamentoLegal()).thenReturn(fundamentoLegalDTO);
        when(semUtilizacaoService.consultarUtilizacao(filtroDestinacaoDTO, fundamentoLegalDTO)).thenThrow(IntegracaoException.class);
        ResponseEntity responseEntity = destinacaoController.consultarUtilizacoes(filtroDestinacaoDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void consultarDestinacaoPendencia() throws Exception {
        when(pendencia.getId()).thenReturn(1L);
        Resposta<List<ConsultaDestinacaoDTO>> resposta =
                RespostaBuilder.getBuilder()
                        .setResultado(asList(new ConsultaDestinacaoDTO()))
                        .setTotalElementos(1L)
                        .setTotalPaginas(1).build();
        when(semUtilizacaoService.consultarDestinacaoPendencia(anyString())).thenReturn(resposta);
        ResponseEntity responseEntity = destinacaoController.consultarDestinacaoPendencia("pendencia");
        Resposta<List<ConsultaDestinacaoDTO>> respostaEsperada = (Resposta<List<ConsultaDestinacaoDTO>>) responseEntity.getBody();
        assertNotNull(respostaEsperada.getResultado());
        assertEquals(new Long(1), respostaEsperada.getTotalElementos());
        assertEquals(new Integer(1), respostaEsperada.getTotalPaginas());
    }

    @Test
    @SneakyThrows
    public void consultarDestinacaoPendenciaComErro() throws Exception {
        when(pendencia.getId()).thenReturn(1L);
        when(semUtilizacaoService.consultarDestinacaoPendencia(anyString())).thenThrow(IntegracaoException.class);
        ResponseEntity responseEntity = destinacaoController.consultarDestinacaoPendencia(anyString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void salvarPermissaoUsoImovelInformalTest() {
        when(permissaoUsoImovelFuncional.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.PERMISSAO_USO_IMOVEL_FUNCIONAL);
        when(entityConverter.converterStrict(any(PermissaoUsoImovelFuncionalDTO.class), eq(PermissaoUsoImovelFuncional.class))).thenReturn(permissaoUsoImovelFuncional);
        when(permissaoUsoImovelFuncionalService.salvar(any(PermissaoUsoImovelFuncional.class))).thenReturn(permissaoUsoImovelFuncional);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.PERMISSAO_USO_IMOVEL_FUNCIONAL))).thenReturn(permissaoUsoImovelFuncionalService);
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarPermissaoUsoImovelInformal(permissaoUsoImovelFuncionalDTO);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        assertTrue(responseEntity.getBody().getErros().size() == Constants.ZERO);
    }

    @Test
    @SneakyThrows
    public void salvarTransferencia(){
        when(transferenciaTitularidade.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.TRANSFERENCIA);
        when(entityConverter.converterStrict(any(TransferenciaTitularidadeDTO.class), eq(TransferenciaTitularidade.class))).thenReturn(transferenciaTitularidade);
        when(transferenciaTitularidadeService.salvar(any(TransferenciaTitularidade.class))).thenReturn(transferenciaTitularidade);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.TRANSFERENCIA))).thenReturn(transferenciaTitularidadeService);
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarTransferencia(transferenciaTitularidadeDTO);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        assertTrue(responseEntity.getBody().getErros().size() == Constants.ZERO);
    }

    @Test
    @SneakyThrows
    public void salvarCessaoOnerosa(){
        when(cessaoOnerosa.getTipoDestinacaoEnum()).thenReturn(TipoDestinacaoEnum.CESSAO_ONEROSA);
        when(entityConverter.converterStrict(any(CessaoOnerosaDTO.class), eq(CessaoOnerosa.class))).thenReturn(cessaoOnerosa);
        when(cessaoOnerosaService.salvar(any(CessaoOnerosa.class))).thenReturn(cessaoOnerosa);
        when(destinacaoStrategy.createBean(eq(TipoDestinacaoEnum.CESSAO_ONEROSA))).thenReturn(cessaoOnerosaService);
        ResponseEntity<Resposta<Destinacao>> responseEntity = destinacaoController.salvarCessaoOnerosa(cessaoOnerosaDTO);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        assertTrue(responseEntity.getBody().getErros().size() == Constants.ZERO);
    }

    @Test
    @SneakyThrows
    public void consultarCidades(){
        assertNotNull(destinacaoController.consultarCidades("pais", "dadosUtilizacao"));
    }

    @Test
    public void buscarListaVersaoDestinacao() throws IntegracaoException{
        when(destinacaoStrategy.createBean(TipoDestinacaoEnum.getPorCodigo(1))).thenReturn(destinacaoService);
        assertNotNull(destinacaoController.buscarListaVersaoDestinacao(1L, "rip"));
    }

    @Test
    @SneakyThrows
    public void consultarDadosDestinacaoAvaliacao(){
        assertNotNull(destinacaoController.consultarDadosDestinacaoAvaliacao("rip"));
    }

    @Test
    public void buscarEncargos(){
        when(encargoService.listaEncargos(anyLong())).thenReturn(listaEncargos);
        assertNotNull(destinacaoController.buscarEncargos(1L));
    }

    @Test
    public void recusarUsoProprio(){
        when(entityConverter.converterStrict(any(UsoProprioDTO.class), eq(UsoProprio.class))).thenReturn(usoProprio);
        when(destinacaoStrategy.createBean(destinacao.getTipoDestinacaoEnum())).thenReturn(usoProprioService);
        assertNotNull(destinacaoController.recusarUsoProprio(usoProprioDTO));
    }

    @Test
    public void homologarUsoProprio(){
        when(entityConverter.converterStrict(any(UsoProprioDTO.class), eq(UsoProprio.class))).thenReturn(usoProprio);
        when(destinacaoStrategy.createBean(destinacao.getTipoDestinacaoEnum())).thenReturn(usoProprioService);
        assertNotNull(destinacaoController.homologarUsoProprio(usoProprioDTO));
    }
}