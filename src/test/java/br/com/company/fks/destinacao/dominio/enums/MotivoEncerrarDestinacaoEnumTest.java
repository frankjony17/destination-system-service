package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MotivoEncerrarDestinacaoEnumTest {

    @Test
    public void getDescricao(){
        String descricao = MotivoEncerrarDestinacaoEnum.CONCLUSAO_DO_CONTRATO.getDescricao();
        assertEquals(descricao, MotivoEncerrarDestinacaoEnum.CONCLUSAO_DO_CONTRATO.getDescricao());
    }

    @Test
    public void getCodigo(){
        Integer codigo = MotivoEncerrarDestinacaoEnum.DEVOLUCAO_DO_IMOVEL.getCodigo();
        assertEquals(codigo, MotivoEncerrarDestinacaoEnum.DEVOLUCAO_DO_IMOVEL.getCodigo());
    }

    @Test
    public void getIsPosseInformal(){
        Boolean posse = MotivoEncerrarDestinacaoEnum.ENCERRAMENTO_POR_REINTEGRACAO_DE_POSSE.getIsPosseInformal();
        assertEquals(posse, MotivoEncerrarDestinacaoEnum.ENCERRAMENTO_POR_REINTEGRACAO_DE_POSSE.getIsPosseInformal());
    }
}
