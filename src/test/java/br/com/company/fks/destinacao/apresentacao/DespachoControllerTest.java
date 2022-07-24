package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.dominio.enums.PerfilEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import br.com.company.fks.destinacao.service.DespachoService;
import br.com.company.fks.destinacao.service.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by guilherme on 01/03/17.
 */
@RunWith(PowerMockRunner.class)
public class DespachoControllerTest {

    @InjectMocks
    private DespachoController despachoController;

    @Mock
    private DespachoService despachoService;

    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp(){
        when(despachoService.buscarPorTipoDespacho(eq(TipoDespachoEnum.DEFAULT))).thenReturn(criaListaDespachos());
    }

    @Test
    public void buscarDespachosDefault() throws Exception {
        when(usuarioService.getPerfil()).thenReturn(PerfilEnum.CHEFIA);
        ResponseEntity<Resposta<List<Despacho>>> despachos =
                despachoController.buscarDespachosDefault();
        assertNotNull(despachos);
    }

    @Test
    public void buscarPorTipo() throws Exception {
        when(despachoService.buscarPorTipoDespacho(TipoDespachoEnum.CHEFIA)).thenReturn(criaListaDespachos());
        ResponseEntity<Resposta<List<Despacho>>> despachos =
                despachoController.buscarPorTipo(TipoDespachoEnum.CHEFIA);
        assertNotNull(despachos);
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

}