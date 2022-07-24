package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by samuel on 12/05/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")

public class ItemVerificacaoCheckListDTOIT {

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
