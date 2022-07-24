package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 17/05/17.
 */
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
public class ConversorUtilIT {

    private static final String IMAGEM = "imagem";

    @Test
    @SneakyThrows
    public void encode() {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("/base64.properties");
        properties.load(inputStream);
        byte [] arquivoDecode = ConversorUtil.decodeBase64ByteArray((String) properties.get(IMAGEM));
        assertNotNull(arquivoDecode);
        assertTrue(arquivoDecode.length > 0);
    }

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<ConversorUtil> constructor = ConversorUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    @SneakyThrows
    public void encodeBase64(){
        byte[ ] byteArray = {
                0,   0,   0,   0, 12,  63,   0,   0, 112,  65,
                0, 25, 127,  71,   0,   0, 12,  59,   0,   0,
                12,  47,  73,  70, 13,   5,  75,   6, 15,  63,
                77,   6, 15,  63,  80,   6, 18,  63,  30,  55,
                10, 121, 25, 25, 127, 25, 25, 127, 127,   1,
                0,   0,   0, 12, 25,   0,   0, 28, 25,   0,
                0, 18, 127 };
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("/base64.properties");
        properties.load(inputStream);
        String arquivoEncode = ConversorUtil.encodeBase64((byteArray));
        assertNotNull(arquivoEncode);
        assertTrue(!arquivoEncode.isEmpty() );
    }
}
