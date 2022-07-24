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

public class DestinacaoImovelDTOIT {
    @Test
    public void testaConstrutor() {
        String rip = "124";
        String codigoUtilizacao = "1234";
        DestinacaoImovelDTO destinacaoImovelDTO = new DestinacaoImovelDTO(rip, codigoUtilizacao);
        assertEquals(rip, destinacaoImovelDTO.getRip());
        assertEquals(codigoUtilizacao, destinacaoImovelDTO.getCodigoUtilizacao());
    }
}
