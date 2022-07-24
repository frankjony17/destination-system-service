package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by diego on 02/12/16.
 */
public class ItemVerificacaoCheckListTest {

    private static final long ID = 1L;
    private static final String DESCRICAO = "teste";
    private ItemVerificacaoCheckList itemVerificacaoCheckList;

    @Before
    public void setUp() {
        itemVerificacaoCheckList = new ItemVerificacaoCheckList();
    }

    @Test
    public void getId() {
        itemVerificacaoCheckList.setId(ID);
        assertNotNull(itemVerificacaoCheckList.getId());
        assertEquals(ID, itemVerificacaoCheckList.getId().longValue());
    }

    @Test
    public void getDescricao() {
        itemVerificacaoCheckList.setDescricao(DESCRICAO);
        assertNotNull(itemVerificacaoCheckList.getDescricao());
        assertEquals(DESCRICAO, itemVerificacaoCheckList.getDescricao());
    }

    @Test
    public void getFundamentoLegal() {
        itemVerificacaoCheckList.setCodFundamentoLegal(1L);
        assertNotNull(itemVerificacaoCheckList.getCodFundamentoLegal());
    }

}
