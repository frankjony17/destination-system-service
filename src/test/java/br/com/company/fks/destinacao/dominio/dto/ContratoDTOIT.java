package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 25/05/17.
 */
@IntegrationTest("server.port:0")
public class ContratoDTOIT {

    private ContratoDTO contratoDTO;
    private Date data;

    @Before
    public void setUp() {
        contratoDTO = new ContratoDTO();
        data = new Date();
    }

    @Test
    public void getDataInicioNula() {
        contratoDTO.setDataInicio(null);
        assertNull(contratoDTO.getDataInicio());
    }

    @Test
    public void getDataInicioNaoNula() {
        contratoDTO.setDataInicio(data);
        assertEquals(data, contratoDTO.getDataInicio());
    }

    @Test
    public void getDataFinalNula() {
        contratoDTO.setDataFinal(null);
        assertNull(contratoDTO.getDataFinal());
    }

    @Test
    public void getDataFinaNaolNula() {
        contratoDTO.setDataFinal(data);
        assertEquals(data, contratoDTO.getDataFinal());
    }
}
