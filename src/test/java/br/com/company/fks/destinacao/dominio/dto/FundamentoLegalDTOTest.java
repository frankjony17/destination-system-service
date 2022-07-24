package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 04/01/17.
 */
public class FundamentoLegalDTOTest {

    private static final long ID_FUNDAMENTO_LEGAL = 1L;
    private static final String DESCRICAO = "teste";

    @Test
    public void testaInstancia() {
        FundamentoLegalDTO fundamentoLegal = new FundamentoLegalDTO(ID_FUNDAMENTO_LEGAL, DESCRICAO);
        assertEquals(ID_FUNDAMENTO_LEGAL, fundamentoLegal.getId().longValue());
        assertEquals(DESCRICAO, fundamentoLegal.getDescricao());
    }
}
