package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 04/01/17.
 */
public class DominioDTOTest {

    private static final int ID_DOMINIO = 1;
    private static final String DESCRICAO = "teste";

    @Test
    public void testaInstancia() {
        DominioDTO dominioDTO = new DominioDTO(ID_DOMINIO, DESCRICAO);
        assertEquals(ID_DOMINIO, dominioDTO.getId().intValue());
        assertEquals(DESCRICAO, dominioDTO.getDescricao());
    }
}
