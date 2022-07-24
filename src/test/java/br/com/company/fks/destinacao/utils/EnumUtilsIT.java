package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.UFEnum;
import br.com.company.fks.destinacao.exceptions.EnumException;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 17/05/17.
 */
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
public class EnumUtilsIT {

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<EnumUtils> constructor = EnumUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void buscarEnumChaveValor() {
        Map<String, String> mapaEnum = EnumUtils.buscarEnumChaveValor(StatusAnaliseTecnicaEnum.class);
        assertEquals("Aguardando Análise Técnica", mapaEnum.get("AGUARDANDO_ANALISE_TECNICA"));
    }

    @Test
    public void buscarEnumCodigoDescricao() {
        List<Map<String, String>> resultado = EnumUtils.buscarEnumCodigoDescricao(TipoDestinacaoEnum.class);
        assertNotNull(resultado);
    }

    @Test(expected = EnumException.class)
    public void buscarEnumCodigoCodigoInexistenteNoEnum() {
        List<Map<String, String>> resultado = EnumUtils.buscarEnumCodigoDescricao(UFEnum.class);
        assertEquals("Doação", resultado.get(1));
    }

}
