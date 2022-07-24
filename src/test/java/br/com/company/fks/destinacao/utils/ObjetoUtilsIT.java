package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 17/05/17.
 */
@RunWith(PowerMockRunner.class)
@IntegrationTest("server.port:0")
public class ObjetoUtilsIT {

    private Benfeitoria novaBenfeitoria;

    private Benfeitoria oldBenfeitoria;

    @Before
    public void setUp() {
        novaBenfeitoria = new Benfeitoria();
        oldBenfeitoria = new Benfeitoria();
        oldBenfeitoria.setAtiva(true);
        novaBenfeitoria.setAtiva(false);
    }

    @Test
    @SneakyThrows
    public void compararObjects() {

        Set<String> fields = new HashSet<>();
        fields.add("ativa");

        Boolean valorEsperado = ObjetoUtils.compararObjetosDiferentes(oldBenfeitoria, novaBenfeitoria, fields);
        assertTrue(valorEsperado);
    }

    @Test
    @SneakyThrows
    public void compararObjectsNaoAlterados() {
        novaBenfeitoria.setAtiva(true);
        Set<String> fields = new HashSet<>();
        fields.add("ativa");
        Boolean valorEsperado = ObjetoUtils.compararObjetosDiferentes(oldBenfeitoria, novaBenfeitoria, fields);
        assertFalse(valorEsperado);
    }

    @Test
    @SneakyThrows
    public void compararObjectsSemCamposComparar() {
        Set<String> fields = new HashSet<>();
        Boolean valorEsperado = ObjetoUtils.compararObjetosDiferentes(oldBenfeitoria, novaBenfeitoria, fields);
        assertFalse(valorEsperado);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void compararObjectsGerandoErros() {

        Set<String> fields = new HashSet<>();
        fields.add("ativas");
        Boolean valorEsperado = ObjetoUtils.compararObjetosDiferentes(oldBenfeitoria, novaBenfeitoria, fields);
        assertFalse(valorEsperado);
    }

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaConstrutorPrivado() {
        Constructor<ObjetoUtils> constructor = ObjetoUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void criarCamposValidar() {
        Set<String> resultado = ObjetoUtils.criarCamposValidar("teste 1", "teste 2");
        Set<String> valorEsperado = camposEsperados();
        assertFalse(resultado.isEmpty());
        resultado.forEach(campo -> assertTrue(valorEsperado.contains(campo)));
    }



    private Set<String> camposEsperados() {
        Set<String> camposEsperados = new HashSet<>();
        camposEsperados.add("teste 1");
        camposEsperados.add("teste 2");
        return camposEsperados;
    }

}
