package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class CancelamentoImovelDTOTest {

    private CancelamentoImovelDTO cancelamentoImovelDTO;
    private Date date;

    @Before
    public void setUp(){
        cancelamentoImovelDTO = new CancelamentoImovelDTO();
        date = new Date();
    }

    @Test
    public void setDataCancelamento(){
        cancelamentoImovelDTO.setDataCancelamento(date);
        assertEquals(date, cancelamentoImovelDTO.getDataCancelamento());
    }

    @Test
    public void setDataCancelamentoNull(){
        cancelamentoImovelDTO.setDataCancelamento(null);
        assertNull(cancelamentoImovelDTO.getDataCancelamento());
    }
}
