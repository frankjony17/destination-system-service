package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.dto.ContratoDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 25/05/17.
 */
public class ContratoDTOTest {

    private ContratoDTO contratoDTO;
    private Date data;

    @Before
    public void setUp() {
        contratoDTO = new ContratoDTO();
        data = new Date();
    }

    @Test
    public void getDataInicioNula() {
        contratoDTO.setDataInicio(null);
        assertNull(contratoDTO.getDataInicio());
    }

    @Test
    public void getDataInicioNaoNula() {
        contratoDTO.setDataInicio(data);
        assertEquals(data, contratoDTO.getDataInicio());
    }

    @Test
    public void getDataFinalNula() {
        contratoDTO.setDataFinal(null);
        assertNull(contratoDTO.getDataFinal());
    }

    @Test
    public void getDataFinaNaolNula() {
        contratoDTO.setDataFinal(data);
        assertEquals(data, contratoDTO.getDataFinal());
    }

}
