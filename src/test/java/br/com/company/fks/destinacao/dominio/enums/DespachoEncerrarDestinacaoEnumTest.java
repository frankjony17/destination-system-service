package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DespachoEncerrarDestinacaoEnumTest {

    @Test
    public void getDescricao(){
        String descricao = DespachoEncerrarDestinacaoEnum.DE_ACORDO_COM_O_ENCERRAMENTO.getDescricao();
        assertEquals(descricao, DespachoEncerrarDestinacaoEnum.DE_ACORDO_COM_O_ENCERRAMENTO.getDescricao());
    }

    @Test
    public void getCodigo(){
        Integer codigo = DespachoEncerrarDestinacaoEnum.RETORNAR_PARA_COMPLEMENTACAO.getCodigo();
        assertEquals(codigo, DespachoEncerrarDestinacaoEnum.RETORNAR_PARA_COMPLEMENTACAO.getCodigo());
    }
}
