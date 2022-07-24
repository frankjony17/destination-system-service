package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by diego on 25/05/17.
 */
@IntegrationTest("server.port:0")
public class BenfeitoriaDTOIT {

    private BenfeitoriaDTO benfeitoriaDTO;
    private Date data;

    @Before
    public void setUp() {
        benfeitoriaDTO = new BenfeitoriaDTO();
        data = new Date();
    }

    @Test
    public void getDataCancelamentoNula() {
        benfeitoriaDTO.setDataCancelamento(null);
        assertNull(benfeitoriaDTO.getDataCancelamento());
    }

    @Test
    public void getDataCancelamentoNaoNula() {
        benfeitoriaDTO.setDataCancelamento(data);
        assertEquals(data, benfeitoriaDTO.getDataCancelamento());
    }

}
