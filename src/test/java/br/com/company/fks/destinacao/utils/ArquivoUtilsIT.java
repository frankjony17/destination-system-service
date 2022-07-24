package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by diego on 17/05/17.
 */
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
@PrepareForTest({Files.class, Paths.class, Magic.class})
public class ArquivoUtilsIT {

    @Before
    public void setUp() {
        mockStatic(Files.class);
        mockStatic(Magic.class);
    }

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testeInstanciaClasse() {
        Constructor<ArquivoUtils> constructor = ArquivoUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void lerArquivoGerandoException() {
        String arquivo = ArquivoUtils.lerArquivo("teste/teste.jpg");
        assertNull(arquivo);
    }

    @Test
    public void salvarErro() throws IOException {
        byte [] bytes = new byte[1024];
        //when(Files.write(any(Paths.class), bytes)).thenThrow(IOException.class);
        Boolean arquivoSalvo = ArquivoUtils.salvar(bytes, "/abcd/", System.currentTimeMillis() + ".jpeg");
        assertEquals(Boolean.FALSE, arquivoSalvo);
    }

    @Test(expected = MagicParseException.class)
    @SneakyThrows
    public void getContentTypeGerandoMagicParseException() {
        when(Magic.getMagicMatch(any(byte[].class))).thenThrow(MagicParseException.class);
        byte [] arquivo = getByteArrayfile();
        ArquivoUtils.getContentType(arquivo);
    }

    @Test(expected = MagicException.class)
    @SneakyThrows
    public void getContentTypeGerandoMagicMagicException() {
        when(Magic.getMagicMatch(any(byte[].class))).thenThrow(MagicException.class);
        byte [] arquivo = getByteArrayfile();
        ArquivoUtils.getContentType(arquivo);
    }

    @Test(expected = MagicMatchNotFoundException.class)
    @SneakyThrows
    public void getContentTypeGerandoMagicMatchNotFoundException() {
        when(Magic.getMagicMatch(any(byte[].class))).thenThrow(MagicMatchNotFoundException.class);
        byte [] arquivo = getByteArrayfile();
        ArquivoUtils.getContentType(arquivo);
    }

    private byte[] getByteArrayfile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/arquivos/teste.jpeg");
        return IOUtils.toByteArray(inputStream);
    }

}
