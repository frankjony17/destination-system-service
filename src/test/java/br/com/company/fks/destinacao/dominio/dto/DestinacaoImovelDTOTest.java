package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 06/04/17.
 */
public class DestinacaoImovelDTOTest {

    @Test
    public void testaConstrutor() {
        String rip = "123";
        String codigoUtilizacao = "1234";
        DestinacaoImovelDTO destinacaoImovelDTO = new DestinacaoImovelDTO(rip, codigoUtilizacao);
        assertEquals(rip, destinacaoImovelDTO.getRip());
        assertEquals(codigoUtilizacao, destinacaoImovelDTO.getCodigoUtilizacao());
    }

}