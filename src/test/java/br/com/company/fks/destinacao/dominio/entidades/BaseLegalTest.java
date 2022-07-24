package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by diego on 23/01/17.
 */
public class BaseLegalTest {

    @Test
    public void testaInstancia() {
        BaseLegal baseLegal = new BaseLegal(1);
        assertNotNull(baseLegal);
        assertEquals(1, baseLegal.getId().intValue());
    }

}