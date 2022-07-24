package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.utils.ArquivoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by diego on 23/01/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ArquivoUtils.class})
public class ArquivoDTOTest {

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
