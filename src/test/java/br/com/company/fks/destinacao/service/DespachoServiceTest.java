package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.dominio.enums.PerfilEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import br.com.company.fks.destinacao.repository.DespachoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 29/12/16.
 */
@RunWith(PowerMockRunner.class)
public class DespachoServiceTest {

    @InjectMocks
    private DespachoService despachoService;

    @Mock
    private DespachoRepository despachoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp() {
        when(despachoRepository.buscarPorTipoDespacho(eq(TipoDespachoEnum.DEFAULT))).thenReturn(criaListaDespachos());
    }

    private List<Despacho> criaListaDespachos() {
        List<Despacho> despachos = new ArrayList<>();
        Despacho despacho1 = new Despacho();
        despacho1.setId(2L);
        despacho1.setDescricao("Teste 2");
        despacho1.setTipoDespacho(TipoDespachoEnum.DEFAULT);

        Despacho despacho2 = new Despacho();
        despacho2.setId(1L);
        despacho2.setDescricao("Teste 1");
        despacho2.setTipoDespacho(TipoDespachoEnum.DEFAULT);

        return asList(despacho1, despacho2);

    }

    @Test
    public void buscarPorTipoDespachoPerfilChefia() {
        when(usuarioService.getPerfil()).thenReturn(PerfilEnum.CHEFIA);
        List<Despacho> despachos = despachoService.buscarPorTipoDespacho(TipoDespachoEnum.DEFAULT);
        assertNotNull(despachos);
        assertFalse(despachos.isEmpty());
    }

    @Test
    public void buscarPorTipoDespachoPerfilDiferenteChefia() {
        when(usuarioService.getPerfil()).thenReturn(PerfilEnum.TECNICO);
        List<Despacho> despachos = despachoService.buscarPorTipoDespacho(TipoDespachoEnum.DEFAULT);
        assertNotNull(despachos);
        assertFalse(despachos.isEmpty());
    }

    @Test
    public void filtrarDespachos() {
        List<DespachoDTO> despachosFiltrados = despachoService.filtrarDespachos(TipoDespachoEnum.DEFAULT, criarListaDespachosDTO());
        assertEquals(1, despachosFiltrados.size());
        assertNotNull(despachosFiltrados);
    }

    @Test
    public void sort() {
        List<DespachoDTO> despachos = criarListaDespachosDTO();
        despachoService.sort(despachos);
        DespachoDTO despacho = despachos.get(0);
        assertEquals(3L, despacho.getId().longValue());
    }

    private List<DespachoDTO> criarListaDespachosDTO() {
        DespachoDTO despachoDTO1 = new DespachoDTO();
        despachoDTO1.setId(1L);
        despachoDTO1.setDescricao("Teste 1");
        despachoDTO1.setTipoDespacho(TipoDespachoEnum.DEFAULT);

        DespachoDTO despachoDTO2 = new DespachoDTO();
        despachoDTO2.setId(2L);
        despachoDTO2.setDescricao("Teste 2");
        despachoDTO2.setTipoDespacho(TipoDespachoEnum.CHEFIA);

        DespachoDTO despachoDTO3 = new DespachoDTO();
        despachoDTO3.setId(3L);
        despachoDTO3.setDescricao("Teste 3");
        despachoDTO3.setTipoDespacho(TipoDespachoEnum.CHEFIA);

        return asList(despachoDTO1, despachoDTO2, despachoDTO3);
    }
}
