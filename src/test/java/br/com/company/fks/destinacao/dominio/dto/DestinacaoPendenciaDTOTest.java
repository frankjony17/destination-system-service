package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DestinacaoPendenciaDTOTest {

    private DestinacaoPendenciaDTO dto;


    @Test
    public void testaInstancia() throws Exception {
        Date now = new Date();
        dto = new DestinacaoPendenciaDTO(1L, 2L, "descricao", "destinacao", now);
        assertNotNull(dto);
        assertEquals(dto.getDestinacaoPendenciaID().getDestinacao().getId(), new Long("1"));
        assertEquals(dto.getDestinacaoPendenciaID().getPendencia().getId(), new Long("2"));
        assertEquals(dto.getDestinacaoPendenciaID().getPendencia().getDescricao(), "descricao");
        assertEquals(dto.getDestinacaoPendenciaID().getPendencia().getModulo(), "destinacao");
        assertEquals(dto.getDataGerada(), now);
    }

    @Test
    public void setDataGerada(){
        DestinacaoPendenciaDTO destinacaoPendenciaDTO = new DestinacaoPendenciaDTO();
        Date date = new Date();
        destinacaoPendenciaDTO.setDataGerada(date);
        assertEquals(date, destinacaoPendenciaDTO.getDataGerada());
    }

    @Test
    public void setDataGeradaNull(){
        DestinacaoPendenciaDTO destinacaoPendenciaDTO = new DestinacaoPendenciaDTO();
        destinacaoPendenciaDTO.setDataGerada(null);
        assertEquals(null, destinacaoPendenciaDTO.getDataGerada());
    }
}