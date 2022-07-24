package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */

public class DocumentoTest {
    private Documento documento;

    @Before
    public void setUp(){
        documento = new Documento();
    }

    @Test
    public void getDataPublicacao(){
        Date date = new Date();
        documento.setDataPublicacao(date);
        assertEquals(date, documento.getDataPublicacao());
    }

    @Test
    public void getDataPublicacaoNull(){
        documento.setDataPublicacao(null);
        assertEquals(null, documento.getDataPublicacao());
    }

    @Test
    public void getDataInicialVigencia(){
        Date date = new Date();
        documento.setDataInicialVigencia(date);
        assertEquals(date, documento.getDataInicialVigencia());
    }

    @Test
    public void getDataInicialVigenciaNull(){
        documento.setDataInicialVigencia(null);
        assertEquals(null, documento.getDataInicialVigencia());
    }

    @Test
    public void getDataFinalVigencia(){
        Date date = new Date();
        documento.setDataFinalVigencia(date);
        assertEquals(date, documento.getDataFinalVigencia());
    }

    @Test
    public void getDataFinalVigenciaNull(){
        documento.setDataFinalVigencia(null);
        assertEquals(null, documento.getDataFinalVigencia());
    }
}
