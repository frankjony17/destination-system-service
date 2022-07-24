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

public class FundamentoLegalDTOIT {

    private static final long ID_FUNDAMENTO_LEGAL = 1L;
    private static final String DESCRICAO = "teste";

    @Test
    public void testaInstancia() {
        FundamentoLegalDTO fundamentoLegal = new FundamentoLegalDTO(ID_FUNDAMENTO_LEGAL, DESCRICAO);
        assertEquals(ID_FUNDAMENTO_LEGAL, fundamentoLegal.getId().longValue());
        assertEquals(DESCRICAO, fundamentoLegal.getDescricao());
    }
}
