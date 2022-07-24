package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class SuspensaoImovelDTOTest {

    private SuspensaoImovelDTO suspensaoImovelDTO;
    private Date data;

    @Before
    public void setUp(){
        suspensaoImovelDTO = new SuspensaoImovelDTO();
        data = new Date();
    }

    @Test
    public void setVigencia(){
        suspensaoImovelDTO.setVigencia(data);
        assertEquals(data, suspensaoImovelDTO.getVigencia());
    }

    @Test
    public void setVigenciaNull(){
        suspensaoImovelDTO.setVigencia(null);
        assertNull(suspensaoImovelDTO.getVigencia());
    }
}
