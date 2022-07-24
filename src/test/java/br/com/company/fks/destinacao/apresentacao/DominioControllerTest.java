package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.dominio.enums.UFEnum;
import br.com.company.fks.destinacao.service.DominioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by guilherme on 02/03/17.
 */

@RunWith(PowerMockRunner.class)
public class DominioControllerTest {

    @InjectMocks
    private DominioController dominioController;

    @Mock
    private DominioService dominioService;

    @Mock
    private DominioDTO dominioDTO;

    @Mock
    private List<DominioDTO> listaDTOS;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void buscarTodosTipoPagamentos() throws Exception {
        when(dominioService.buscarTodos(eq(TipoPagamento.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tiposPagamentos = dominioController.buscarTodosTipoPagamentos();
        assertNotNull(tiposPagamentos);
    }

    @Test
    public void buscarTodosTipoPeriocidades() throws Exception {
        when(dominioService.buscarTodos(eq(TipoPeriocidade.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tiposPeriocidade = dominioController.buscarTodosTipoPeriocidades();
        assertNotNull(tiposPeriocidade);
    }

    @Test
    public void buscarTodosTipoMoeda() throws Exception {
        when(dominioService.buscarTodos(eq(TipoMoeda.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tiposMoeda = dominioController.buscarTodosTipoMoeda();
        assertNotNull(tiposMoeda);
    }

    @Test
    public void buscarTodosTipoReajuste() throws Exception {
        when(dominioService.buscarTodos(eq(TipoReajuste.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosTipoReajuste();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodosTipoJuros() throws Exception {
        when(dominioService.buscarTodos(eq(TipoJuro.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosTipoJuros();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodosTipoLicitacao() throws Exception {
        when(dominioService.buscarTodos(eq(TipoLicitacao.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosTipoLicitacao();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodosTipoPosse() throws Exception {
        when(dominioService.buscarTodos(eq(TipoPosse.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosTipoPosse();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodosClassificacaoImovel() throws Exception {
        when(dominioService.buscarTodos(eq(ClassificacaoImovel.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosClassificacaoImovel();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodosTipoModalidade() throws Exception {
        when(dominioService.buscarTodos(eq(TipoModalidade.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosTipoModalidade();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodosTiposConcessao() throws Exception {
        when(dominioService.buscarTodos(eq(TipoConcessao.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosTiposConcessao();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodosTiposInstrumento() throws Exception {
        when(dominioService.buscarTodos(eq(TipoInstrumento.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosTiposInstrumento();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodosTiposDestinacao() throws Exception {
        when(dominioService.buscarTodos(eq(TipoDestinacao.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<DominioDTO>> tipos = dominioController.buscarTodosTiposDestinacao();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTodasUfs() throws Exception {
        ResponseEntity<Resposta<UFEnum>> tipos = dominioController.buscarTodasUfs();
        assertNotNull(tipos);
    }

    @Test
    public void buscarTipoDocumento(){
        when(dominioService.buscarTodos(TipoDocumento.class)).thenReturn(listaDTOS);
        ResponseEntity<Resposta<DominioDTO>> retorno = dominioController.buscarTipoDocumento();
        assertNotNull(retorno);
    }

    @Test
    public void buscarTodosTiposAtosAdministrativos(){
        when(dominioService.buscarTodos(TipoDocumento.class)).thenReturn(listaDTOS);
        ResponseEntity<Resposta<DominioDTO>> retorno = dominioController.buscaTodosTiposAtosAdministrativos();
        assertNotNull(retorno);
    }

    @Test
    public void buscaTodosTiposAfetacao() throws Exception {
        when(dominioService.buscarTodos(TipoDocumento.class)).thenReturn(listaDTOS);
        ResponseEntity<Resposta<DominioDTO>> retorno = dominioController.buscaTodosTiposAfetacao();
        assertNotNull(retorno);
    }

    @Test
    public void buscaTodosTiposAcao() throws Exception {
        when(dominioService.buscarTodos(TipoDocumento.class)).thenReturn(listaDTOS);
        ResponseEntity<Resposta<DominioDTO>> retorno = dominioController.buscaTodosTiposAcao();
        assertNotNull(retorno);
    }

    @Test
    public void buscaTodosTipoAto(){
        when(dominioService.buscarTodos(TipoDocumento.class)).thenReturn(listaDTOS);
        ResponseEntity<Resposta<DominioDTO>> retorno = dominioController.buscaTodosTiposAto();
        assertNotNull(retorno);
    }
}