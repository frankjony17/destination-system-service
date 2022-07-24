package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class VersaoDestinacaoEnumTest {

    @Test
    public void getDescricao(){
        String descricao = VersaoDestinacaoEnum.VERSAO1.getDescricao();
        assertEquals(descricao, VersaoDestinacaoEnum.VERSAO1.getDescricao());
    }

    @Test
    public void getCodigo(){
        Integer codigo = VersaoDestinacaoEnum.VERSAO2.getCodigo();
        assertEquals(codigo, VersaoDestinacaoEnum.VERSAO2.getCodigo());
    }

    @Test
    public void getByDescricao(){
        VersaoDestinacaoEnum versaoDestinacaoEnum = VersaoDestinacaoEnum.getByDescricao("Versão 1");
        assertNotNull(versaoDestinacaoEnum);
    }

    @Test
    public void getByDescricaoSemResultado(){
        VersaoDestinacaoEnum versaoDestinacaoEnum = VersaoDestinacaoEnum.getByDescricao("descricao");
        assertNull(versaoDestinacaoEnum);
    }
}
