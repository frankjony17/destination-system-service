package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DestinacaoPendenciaDTOIT {

    private DestinacaoPendenciaDTO dto;

    @Ignore
    @Test
    public void testaInstancia() throws Exception {
        Date now = new Date();
        dto = new DestinacaoPendenciaDTO(1L, 2L, "descricaoo", "destinacao", now);
        assertNotNull(dto);
        assertEquals(dto.getDestinacaoPendenciaID().getDestinacao().getId(), new Long("1"));
        assertEquals(dto.getDestinacaoPendenciaID().getPendencia().getId(), new Long("2"));
        assertEquals(dto.getDestinacaoPendenciaID().getPendencia().getDescricao(), "descricaoo");
        assertEquals(dto.getDestinacaoPendenciaID().getPendencia().getUrl(), "url");
        assertEquals(dto.getDestinacaoPendenciaID().getPendencia().getModulo(), "destinacao");
        assertEquals(dto.getDataGerada(), now);

    }

}