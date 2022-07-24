package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 17/05/17.
 */
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
public class DataUtilIT {

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<DataUtil> constructor = DataUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    @SneakyThrows
    public void converterLocalDate() {
        LocalDate dataAtual = LocalDate.now();
        Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dataAtual.toString());
        LocalDate dataEsperada = DataUtil.converterLocalDate(data);
        assertEquals(dataAtual, dataEsperada);
    }

    @Test
    @SneakyThrows
    public void converterLocalDateRetornadoNulo() {
        LocalDate dataEsperada = DataUtil.converterLocalDate(null);
        assertNull(dataEsperada);
    }

}
