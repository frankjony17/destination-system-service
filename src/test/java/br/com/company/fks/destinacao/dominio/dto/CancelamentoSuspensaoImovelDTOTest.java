package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class CancelamentoSuspensaoImovelDTOTest {

    private CancelamentoSuspensaoImovelDTO cancelamentoSuspensaoImovelDTO;
    private Date date;

    @Before
    public void setUp(){
        cancelamentoSuspensaoImovelDTO = new CancelamentoSuspensaoImovelDTO();
        date = new Date();
    }

    @Test
    public void setDataFimVigencia(){
        cancelamentoSuspensaoImovelDTO.setDataFimVigencia(date);
        assertEquals(date, cancelamentoSuspensaoImovelDTO.getDataFimVigencia());
    }

    @Test
    public void setDataFimVigenciaNull(){
        cancelamentoSuspensaoImovelDTO.setDataFimVigencia(null);
        assertNull(cancelamentoSuspensaoImovelDTO.getDataFimVigencia());
    }
}
