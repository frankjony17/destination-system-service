package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CancelamentoEncerramentoDTOTest {

    private CancelamentoEncerramentoDTO cancelamentoEncerramentoDTO;
    private Date data;

    @Before
    public void setUp(){
        cancelamentoEncerramentoDTO = new CancelamentoEncerramentoDTO();
        data = new Date();
    }

    @Test
    public void getDataCancelamentoEncerramentoNula(){
        cancelamentoEncerramentoDTO.setDataCancelamentoEncerramento(null);
        assertNull(cancelamentoEncerramentoDTO.getDataCancelamentoEncerramento());
    }

    @Test
    public void getDataCancelamentoEncerramentoNaoNula(){
        cancelamentoEncerramentoDTO.setDataCancelamentoEncerramento(data);
        assertEquals(data, cancelamentoEncerramentoDTO.getDataCancelamentoEncerramento());
    }
}
