package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 04/01/17.
 */
public class DespachoEnumTest {

    @Test
    public void getCodigo() {
        Long codigo = DespachoEnum.ALTERAR_AVALIACAO.getCodigo();
        assertEquals(codigo, DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
    }

}
