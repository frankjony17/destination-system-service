package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 25/05/17.
 */
public class EncargoDTOTest {

    private EncargoDTO encargoDTO;
    private Date data;

    @Before
    public void setUp() {
        encargoDTO = new EncargoDTO();
        data = new Date();
    }

    @Test
    public void getDataCumprimentoNula() {
        encargoDTO.setDataCumprimento(null);
        assertNull(encargoDTO.getDataCumprimento());
    }

    @Test
    public void getDataCumprimentoNaoNula() {
        encargoDTO.setDataCumprimento(data);
        assertEquals(data, encargoDTO.getDataCumprimento());
    }

}
