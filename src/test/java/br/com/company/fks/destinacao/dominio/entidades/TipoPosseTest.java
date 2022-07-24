package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class TipoPosseTest {

    private TipoPosse tipoPosse;

    @Before
    public void setUp(){
        tipoPosse = new TipoPosse();
    }

    @Test
    public void getId() throws Exception {
        tipoPosse.setId(1);
        assertEquals(Integer.valueOf(1), tipoPosse.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoPosse.setDescricao("teste");
        assertEquals("teste", tipoPosse.getDescricao());
    }

    @Test
    public void tipoPosseConstrutor(){
        tipoPosse = new TipoPosse(1);
        assertEquals(Integer.valueOf(1), tipoPosse.getId());
    }
}