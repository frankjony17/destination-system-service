package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertTrue;

/**
 * Created by haillanderson on 21/08/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class TipoAtoAutorizativoIT {

    @Test
    public void testaConstrutor(){
        TipoAtoAutorizativo tipoAtoAutorizativo = new TipoAtoAutorizativo(1);
        assertTrue(1 == tipoAtoAutorizativo.getId());
    }
}
