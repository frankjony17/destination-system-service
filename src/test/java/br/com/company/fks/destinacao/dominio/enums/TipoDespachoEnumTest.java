package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 04/01/17.
 */
public class TipoDespachoEnumTest {

    @Test
    public void testaEnumIgual() {
        TipoDespachoEnum tipoDespachoEnumEsperado = TipoDespachoEnum.CHEFIA;
        assertEquals(tipoDespachoEnumEsperado, TipoDespachoEnum.valueOf("CHEFIA"));
    }

}
