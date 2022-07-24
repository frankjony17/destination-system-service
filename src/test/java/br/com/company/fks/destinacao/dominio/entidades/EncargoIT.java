package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class EncargoIT {
    private Encargo encargo;

    @Before
    public void setUp(){
        encargo = new Encargo();
    }

    @Test
    public void getId() throws Exception {
        encargo.setId(1l);
        assertEquals(Long.valueOf(1), encargo.getId());
    }

    @Test
    public void getNome() throws Exception {
        encargo.setNome("teste");
        assertEquals("teste", encargo.getNome());
    }

    @Test
    public void getDataCumprimento() throws Exception {
        Date data = new Date();
        encargo.setDataCumprimento(data);
        assertEquals(data, encargo.getDataCumprimento());
    }

    @Test
    public void getDataCumprimentoNull(){
        encargo.setDataCumprimento(null);
        assertEquals(null, encargo.getDataCumprimento());
    }

    @Test
    public void getCumprimentoEncargo() throws Exception {
        encargo.setCumprimentoEncargo(true);
        assertTrue( encargo.getCumprimentoEncargo());
    }
}
