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
public class TipoPosseIT {
    private TipoPosse tipoPosse;

    @Before
    public void setUp(){
        tipoPosse = new TipoPosse();
    }

    @Test
    public void getId() throws Exception {
        tipoPosse.setId(1);
        assertEquals(Integer.valueOf(1), tipoPosse.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoPosse.setDescricao("teste");
        assertEquals("teste", tipoPosse.getDescricao());
    }

    @Test
    public void tipoPosseConstrutor(){
        tipoPosse = new TipoPosse(1);
        assertEquals(Integer.valueOf(1), tipoPosse.getId());
    }
}
