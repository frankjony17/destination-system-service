package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.ArquivoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ArquivoUtils.class)

public class ArquivoTest {
    private Arquivo arquivo;
    @Before
    public void setUp(){
        arquivo = new Arquivo();
    }



    @Test
    public void getId(){
        arquivo.setId(1l);
        assertEquals(Long.valueOf(1), arquivo.getId());
    }

    @Test
    public void getNome(){
        arquivo.setNome("teste");
        assertEquals("teste", arquivo.getNome());
    }

    @Test
    public void getExtensao(){
        arquivo.setExtensao("teste");
        assertEquals("teste", arquivo.getExtensao());
    }

    @Test
    public void getContentType(){
        arquivo.setContentType("teste");
        assertEquals("teste", arquivo.getContentType());
    }

    @Test
    public void getTamanho(){
        arquivo.setTamanho(1l);
        assertEquals(Long.valueOf(1), arquivo.getTamanho());
    }

    @Test
    public void getData(){
        Date data = new Date();
        arquivo.setData(data);
        assertEquals(data, arquivo.getData());
    }

    @Test
    public void getDataNull(){
        arquivo.setData(null);
        assertEquals(null, arquivo.getData());
    }

    @Test
    public void setBytes(){
        byte[] mockimagem = "".getBytes();
        PowerMockito.mockStatic(ArquivoUtils.class);
        when(ArquivoUtils.lerArquivoBytes(anyString())).thenReturn(mockimagem);
        arquivo.setDiretorioArquivo("diretorioArquivo");
        arquivo.setNome("nome");
        arquivo.setBytes();
        assertEquals(mockimagem,arquivo.getImagem());
    }
}