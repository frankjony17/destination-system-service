package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 19/06/17.
 */
@RunWith(PowerMockRunner.class)
public class CastObjectUtilTest {

    @Test
    public void cast() {
        Long longEsperado = CastObjectUtil.cast(new Long(1), Long.class);
        assertEquals(new Long(1), longEsperado);
    }

    @Test(expected = RuntimeException.class)
    public void castGerandoErro() {
        CastObjectUtil.cast(new Long(1), BigDecimal.class);
    }

    @Test
    public void castNulo(){ assertEquals(null, CastObjectUtil.cast(null, Long.class)); }

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testeInstanciaClasse() {
        Constructor<CastObjectUtil> constructor = CastObjectUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}