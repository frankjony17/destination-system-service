package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by diego on 23/01/17.
 */
public class TipoDestinatarioTest {

    @Test
    public void testaInstancia() {
        TipoDestinatario tipoDestinatario = new TipoDestinatario(1);
        assertNotNull(tipoDestinatario);
        assertEquals(1, tipoDestinatario.getId().intValue());
    }

}