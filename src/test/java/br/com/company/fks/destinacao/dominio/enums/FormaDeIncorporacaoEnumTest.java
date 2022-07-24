package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FormaDeIncorporacaoEnumTest {

    @Test
    public void getByCodigo() {
        FormaDeIncorporacaoEnum formaDeIncorporacaoEnum = FormaDeIncorporacaoEnum.getByCodigo(1L);
        assertEquals(FormaDeIncorporacaoEnum.ORIGINALMENTE_DA_UNIAO, formaDeIncorporacaoEnum);
    }
}
