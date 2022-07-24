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
public class TipoJuroIT {
    private TipoJuro tipoJuro;

    @Before
    public void setUp(){
        tipoJuro = new TipoJuro();
    }

    @Test
    public void getId() throws Exception {
        tipoJuro.setId(1);
        assertEquals(Integer.valueOf(1), tipoJuro.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoJuro.setDescricao("teste");
        assertEquals("teste", tipoJuro.getDescricao());
    }

    @Test
    public void tipoJuro(){
        tipoJuro = new TipoJuro(1);
        assertEquals(Integer.valueOf(1), tipoJuro.getId());
    }

}
