package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 21/08/17.
 */
public class TipoAtoAutorizativoEnumTest {


    @Test
    public void getDestcricao() {
        assertEquals("Portaria", TipoAtoAutorizativoEnum.PORTARIA.getDescricao());
    }

    @Test
    public void getCodigo() {
        assertEquals(new Integer(1), TipoAtoAutorizativoEnum.PORTARIA.getCodigo());
    }

}