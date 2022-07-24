package br.com.company.fks.destinacao.service.analiseTecnica;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by diego on 30/12/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpringContext.class})
public class StatusAnaliseTecnicaStrategyTest {

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Mock
    private StatusAnaliseDespachoChefia statusAnaliseDespachoChefia;

    @Mock
    private StatusAnaliseDespachoSuperintendente statusAnaliseDespachoSuperintendente;

    @Mock
    private StatusAnaliseDespachoSecretario statusAnaliseDespachoSecretario;

    @Before
    public void setUp() {
        mockStatic(SpringContext.class);
        Set<String> permissoes = new HashSet();
        permissoes.add(PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA.getDescricao());
        when(usuarioLogadoDTO.getPermissoes()).thenReturn(permissoes);
    }

    @Test
    public void buscarStatusAnalisePorDespachoPorPermissao() {
        when(SpringContext.getBean(eq(StatusAnaliseDespachoChefia.class))).thenReturn(statusAnaliseDespachoChefia);
        StatusAnaliseDespacho statusAnaliseDespacho = StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(usuarioLogadoDTO);
        Boolean instanciaStatusAnalalise = statusAnaliseDespacho instanceof  StatusAnaliseDespachoChefia;
        assertTrue(instanciaStatusAnalalise);
    }

    @Test
    public void buscarStatusAnalisePorDespachoPorPermissaoChefia() {
        StatusAnaliseDespacho statusAnaliseDespacho = criarBean(StatusAnaliseDespachoChefia.class, statusAnaliseDespachoChefia, PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA);
        Boolean instanciaStatusAnalalise = statusAnaliseDespacho instanceof  StatusAnaliseDespachoChefia;
        assertTrue(instanciaStatusAnalalise);
    }

    @Test
    public void buscarStatusAnalisePorDespachoPorPermissaoSuperintendente() {
        StatusAnaliseDespacho statusAnaliseDespacho = criarBean(StatusAnaliseDespachoSuperintendente.class, statusAnaliseDespachoSuperintendente, PermissaoAnaliseEnum.EXEC_ANALISE_SUPERINTENDENTE);
        Boolean instanciaStatusAnalalise = statusAnaliseDespacho instanceof  StatusAnaliseDespachoSuperintendente;
        assertTrue(instanciaStatusAnalalise);
    }

    @Test
    public void buscarStatusAnalisePorDespachoPorPermissaoSecretario() {
        StatusAnaliseDespacho statusAnaliseDespacho = criarBean(StatusAnaliseDespachoSecretario.class, statusAnaliseDespachoSecretario, PermissaoAnaliseEnum.EXEC_ANALISE_SECRETARIO);
        Boolean instanciaStatusAnalalise = statusAnaliseDespacho instanceof  StatusAnaliseDespachoSecretario;
        assertTrue(instanciaStatusAnalalise);
    }

    private StatusAnaliseDespacho criarBean(Class clazz, StatusAnaliseDespacho statusAnaliseDespacho, PermissaoAnaliseEnum permissaoAnaliseEnum) {
        when(SpringContext.getBean(clazz)).thenReturn(statusAnaliseDespacho);
        return StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(permissaoAnaliseEnum);
    }

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<StatusAnaliseTecnicaStrategy> constructor = StatusAnaliseTecnicaStrategy.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
