package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class TipoReajusteTest {

    private TipoReajuste tipoReajuste;

    @Before
    public void setUp(){
        tipoReajuste = new TipoReajuste();
    }

    @Test
    public void getId() throws Exception {
        tipoReajuste.setId(1);
        assertEquals(Integer.valueOf(1), tipoReajuste.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoReajuste.setDescricao("teste");
        assertEquals("teste", tipoReajuste.getDescricao());
    }

    @Test
    public void tipoReajusteConstrutor(){
        tipoReajuste = new TipoReajuste(1);
        assertEquals(Integer.valueOf(1), tipoReajuste.getId());
    }

}