package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TipoAquisicaoEnumTest {

    @Test
    public void getByCodigo(){
        TipoAquisicaoEnum tipoAquisicaoEnum = TipoAquisicaoEnum.getByCodigo(1L);
        assertEquals(TipoAquisicaoEnum.REVERSAO, tipoAquisicaoEnum);
    }
}
