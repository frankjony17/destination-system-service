package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 04/01/17.
 */
public class ItemVerificacaoCheckListDTOTest {

    private static final long ID_ITEM_VERF_CHECKLIST = 1L;
    private static final String DESCRICAO = "teste";

    @Test
    public void testaInstancia() {
        ItemVerificacaoCheckListDTO itemVerificacaoCheckListDTO =
                new ItemVerificacaoCheckListDTO(ID_ITEM_VERF_CHECKLIST, DESCRICAO);
        assertEquals(ID_ITEM_VERF_CHECKLIST, itemVerificacaoCheckListDTO.getId().longValue());
        assertEquals(DESCRICAO, itemVerificacaoCheckListDTO.getDescricao());
    }
}
