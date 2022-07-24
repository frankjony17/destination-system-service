package br.com.company.fks.destinacao.dominio.entidades;

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
public class BaseLegalIT {
    @Test
    public void testaInstancia() {
        BaseLegal baseLegal = new BaseLegal(1);
        assertNotNull(baseLegal);
        assertEquals(1, baseLegal.getId().intValue());
    }
}
