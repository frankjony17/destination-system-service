package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by diego on 23/01/17.
 */
public class TipoTransferenciaTest {

    @Test
    public void testaInstancia() {
        TipoTransferencia tipoTransferencia = new TipoTransferencia(1);
        assertNotNull(tipoTransferencia);
        assertEquals(1, tipoTransferencia.getId().intValue());
    }

}