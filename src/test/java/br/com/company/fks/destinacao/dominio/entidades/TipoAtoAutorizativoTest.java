package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by haillanderson on 21/08/17.
 */
public class TipoAtoAutorizativoTest {

    @Test
    public void testaConstrutor(){
        TipoAtoAutorizativo tipoAtoAutorizativo = new TipoAtoAutorizativo(1);
        assertTrue(1 == tipoAtoAutorizativo.getId());
    }
}
