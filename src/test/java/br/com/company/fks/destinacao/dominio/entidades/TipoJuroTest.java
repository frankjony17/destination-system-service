package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class TipoJuroTest {

    private TipoJuro tipoJuro;

    @Before
    public void setUp(){
        tipoJuro = new TipoJuro();
    }

    @Test
    public void getId() throws Exception {
        tipoJuro.setId(1);
        assertEquals(Integer.valueOf(1), tipoJuro.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoJuro.setDescricao("teste");
        assertEquals("teste", tipoJuro.getDescricao());
    }

    @Test
    public void tipoJuro(){
        tipoJuro = new TipoJuro(1);
        assertEquals(Integer.valueOf(1), tipoJuro.getId());
    }


}