package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 25/05/17.
 */
public class BenfeitoriaDTOTest {

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
