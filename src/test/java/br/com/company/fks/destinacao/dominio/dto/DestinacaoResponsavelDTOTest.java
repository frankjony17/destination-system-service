package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class DestinacaoResponsavelDTOTest {

    private DestinacaoResponsavelDTO destinacaoResponsavelDTO;
    private Date date;

    @Before
    public void setUp(){
        destinacaoResponsavelDTO = new DestinacaoResponsavelDTO("instrumento", new Date(), new Date(), "codigoUtilizacao", "rip", "parcela");
        date = new Date();
    }

    @Test(expected = NullPointerException.class)
    public void toDtoNulo() throws Exception {
        List<Object> objects = Collections.singletonList(null);
        DestinacaoResponsavelDTO.toDto(objects);
    }

    @Test
    public void toDtoList() throws Exception {
        Object[] objects = new Object[6];
        objects[0] = "string";
        objects[1] = new Date();
        objects[2] = new Date();
        objects[3] = "string";
        objects[4] = "string";
        objects[5] = "string";
        List<Object> objectsList = Collections.singletonList(objects);
        List<DestinacaoResponsavelDTO> dtos = DestinacaoResponsavelDTO.toDto(objectsList);
        Assert.assertTrue(dtos.size() == 1);
    }

    @Test
    public void setInicioVigenecia(){
        destinacaoResponsavelDTO.setInicioVigencia(date);
        assertEquals(date, destinacaoResponsavelDTO.getInicioVigencia());
    }

    @Test
    public void setInicioVigenciaNull(){
        destinacaoResponsavelDTO.setInicioVigencia(null);
        assertNull(destinacaoResponsavelDTO.getInicioVigencia());
    }

    @Test
    public void setFimVigencia(){
        destinacaoResponsavelDTO.setFimVigencia(date);
        assertEquals(date, destinacaoResponsavelDTO.getFimVigencia());
    }

    @Test
    public void setFimVigenciaNull(){
        destinacaoResponsavelDTO.setFimVigencia(null);
        assertNull(destinacaoResponsavelDTO.getFimVigencia());
    }
}