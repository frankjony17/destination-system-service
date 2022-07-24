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
public class TipoDestinatarioIT {
    @Test
    public void testaInstancia() {
        TipoDestinatario tipoDestinatario = new TipoDestinatario(1);
        assertNotNull(tipoDestinatario);
        assertEquals(1, tipoDestinatario.getId().intValue());
    }
}
