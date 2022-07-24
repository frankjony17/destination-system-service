package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertNull;

/**
 * Created by rogerio on 17/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class PerfilEnumIT {
    @Test
    public void buscarPorNomeSemResultado() {
        PerfilEnum perfilEnum = PerfilEnum.buscarPorNome("DESTINACAO.TECNICOs");
        assertNull(perfilEnum);
    }
}
