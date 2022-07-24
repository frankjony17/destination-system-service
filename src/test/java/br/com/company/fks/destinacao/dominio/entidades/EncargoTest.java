package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class EncargoTest {

    private Encargo encargo;

    @Before
    public void setUp(){
        encargo = new Encargo();
    }

    @Test
    public void getId() throws Exception {
        encargo.setId(1l);
        assertEquals(Long.valueOf(1), encargo.getId());
    }

    @Test
    public void getNome() throws Exception {
        encargo.setNome("teste");
        assertEquals("teste", encargo.getNome());
    }

    @Test
    public void getDataCumprimento() throws Exception {
        Date data = new Date();
        encargo.setDataCumprimento(data);
        assertEquals(data, encargo.getDataCumprimento());
    }

    @Test
    public void getDataCumprimentoNull(){
        encargo.setDataCumprimento(null);
        assertEquals(null, encargo.getDataCumprimento());
    }

    @Test
    public void getPrazoIndeterminado() throws Exception {
        encargo.setCumprimentoEncargo(true);
        assertTrue( encargo.getCumprimentoEncargo());
    }

}