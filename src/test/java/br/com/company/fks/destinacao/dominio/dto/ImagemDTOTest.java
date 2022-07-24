package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 06/04/17.
 */
public class ImagemDTOTest {

    @Test
    public void testaConstrutor() {
        ImagemDTO imagemDTO = new ImagemDTO(1L);
        Long idEsparado = 1L;
        assertEquals(idEsparado, imagemDTO.getId());
    }

}