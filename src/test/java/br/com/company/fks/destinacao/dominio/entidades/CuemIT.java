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
public class CuemIT {
    private Cuem cuem;

    @Before
    public void setUp(){
        cuem = new Cuem();
    }

    @Test
    public void getTipoModalidade() throws Exception {
        TipoModalidade tipoModalidade = new TipoModalidade();
        cuem.setTipoModalidade(tipoModalidade);
        assertEquals(tipoModalidade, cuem.getTipoModalidade());
    }

    @Test
    public void getContrato() throws Exception {
        Contrato contrato = new Contrato();
        cuem.setContrato(contrato);
        assertEquals(contrato, cuem.getContrato());
    }
}
