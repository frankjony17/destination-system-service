package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 21/08/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class TipoAtoAutorizativoEnumIT {

    @Test
    public void getDestcricao() {
        assertEquals("Portaria", TipoAtoAutorizativoEnum.PORTARIA.getDescricao());
    }

    @Test
    public void getCodigo() {
        assertEquals(new Integer(1), TipoAtoAutorizativoEnum.PORTARIA.getCodigo());
    }

}
