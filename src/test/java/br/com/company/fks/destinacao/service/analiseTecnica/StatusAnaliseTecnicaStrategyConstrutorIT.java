package br.com.company.fks.destinacao.service.analiseTecnica;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 02/01/17.
 */

@RunWith(PowerMockRunner.class)
@IntegrationTest("server.port:0")
public class StatusAnaliseTecnicaStrategyConstrutorIT {

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<StatusAnaliseTecnicaStrategy> constructor = StatusAnaliseTecnicaStrategy.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
