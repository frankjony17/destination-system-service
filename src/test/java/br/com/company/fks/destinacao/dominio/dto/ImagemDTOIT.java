package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by samuel on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")

public class ImagemDTOIT {
    @Test
    public void testaConstrutor() {
        ImagemDTO imagemDTO = new ImagemDTO(1L);
        Long idEsparado = 1L;
        assertEquals(idEsparado, imagemDTO.getId());
    }
}
