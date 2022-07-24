package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.utils.ArquivoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by samuel on 12/05/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
@PrepareForTest({ArquivoUtils.class})
public class ArquivoDTOIT {

    private ArquivoDTO arquivoDTO;
    private Date date;

    @Before
    public void setUp() {
        arquivoDTO = new ArquivoDTO();
        date = new Date();
    }

    @Test
    public void testaInstancia() {
        ArquivoDTO arquivoDTO = new ArquivoDTO(1L, "teste");
        assertNotNull(arquivoDTO);
        assertEquals(1L, arquivoDTO.getId().longValue());
        assertEquals("teste", arquivoDTO.getNome());
    }


    @Test
    public void getDataNula() {
        arquivoDTO.setData(null);
        assertNull(arquivoDTO.getData());
    }

    @Test
    public void getDataNaoNula() {
        arquivoDTO.setData(date);
        assertEquals(date, arquivoDTO.getData());
    }

    @Test
    public void setBytesDoArquivo() {
        byte[] bytes = "test.cfg".getBytes();
        mockStatic(ArquivoUtils.class);
        when(ArquivoUtils.lerArquivoBytes(anyString())).thenReturn(bytes);
        arquivoDTO.setDiretorioArquivo("/tmp/");
        arquivoDTO.setNome("test.cfg");
        arquivoDTO.setBytes();
        assertEquals(arquivoDTO.getImagem(), bytes);
    }

}
