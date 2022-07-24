package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 10/02/17.
 */
@RunWith(PowerMockRunner.class)
public class ConstantsTest {

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaConstrutorPrivado() {
        Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
