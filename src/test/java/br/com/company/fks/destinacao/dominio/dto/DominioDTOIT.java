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

public class DominioDTOIT {
    private static final int ID_DOMINIO = 1;
    private static final String DESCRICAO = "teste";

    @Test
    public void testaInstancia() {
        DominioDTO dominioDTO = new DominioDTO(ID_DOMINIO, DESCRICAO);
        assertEquals(ID_DOMINIO, dominioDTO.getId().intValue());
        assertEquals(DESCRICAO, dominioDTO.getDescricao());
    }
}
