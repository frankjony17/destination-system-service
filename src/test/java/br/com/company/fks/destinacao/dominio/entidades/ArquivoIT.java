package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ArquivoIT {
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
    public void getBinario(){
    }
}