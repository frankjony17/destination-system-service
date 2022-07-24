package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class TipoReajusteIT {
    private TipoReajuste tipoReajuste;

    @Before
    public void setUp(){
        tipoReajuste = new TipoReajuste();
    }

    @Test
    public void getId() throws Exception {
        tipoReajuste.setId(1);
        assertEquals(Integer.valueOf(1), tipoReajuste.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoReajuste.setDescricao("teste");
        assertEquals("teste", tipoReajuste.getDescricao());
    }

    @Test
    public void tipoReajusteConstrutor(){
        tipoReajuste = new TipoReajuste(1);
        assertEquals(Integer.valueOf(1), tipoReajuste.getId());
    }
}
