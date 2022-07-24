package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResponsavelDTOTest {

    private ResponsavelDTO responsavelDTO;
    private Date data;

    @Before
    public void setUp(){
        responsavelDTO = new ResponsavelDTO();
        data = new Date();
    }

    @Test
    public void setDataObitoNula(){
        responsavelDTO.setDataObito(null);
        assertNull(responsavelDTO.getDataObito());
    }

    @Test
    public void setDataObito(){
        responsavelDTO.setDataObito(data);
        assertEquals(data, responsavelDTO.getDataObito());
    }
}
