package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DestinacaoResponsavelDTOIT {

    @Test(expected = NullPointerException.class)
    public void toDtoNulo() throws Exception {
        List<Object> objects = Collections.singletonList(null);
        DestinacaoResponsavelDTO.toDto(objects);
    }

    @Test
    public void toDtoList() throws Exception {
        Object[] objects = new Object[6];
        objects[0] = "stringg";
        objects[1] = new Date();
        objects[2] = new Date();
        objects[3] = "string";
        objects[4] = "string";
        objects[5] = "string";
        List<Object> objectsList = Collections.singletonList(objects);
        List<DestinacaoResponsavelDTO> dtos = DestinacaoResponsavelDTO.toDto(objectsList);
        Assert.assertTrue(dtos.size() == 1);
    }

}