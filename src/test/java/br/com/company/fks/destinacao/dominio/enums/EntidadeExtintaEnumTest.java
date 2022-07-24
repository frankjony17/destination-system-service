package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class EntidadeExtintaEnumTest {

    @Test
    public void getByCodigo(){
        EntidadeExtintaEnum entidadeExtintaEnum = EntidadeExtintaEnum.getByCodigo(1L);
        assertEquals(EntidadeExtintaEnum.MINISTERIO_DA_ADM, entidadeExtintaEnum);
    }

}
