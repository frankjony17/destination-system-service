package br.com.company.fks.destinacao.builder;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class RespostaDadosPessoaFisicaHistoricoCampoIT {

    @Test
    public void testaInstancia() {
        assertNotNull(new RespostaDadosPessoaFisicaHistoricoCampo());
    }

}