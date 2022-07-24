package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ItemVerificacaoCheckListIT {
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
