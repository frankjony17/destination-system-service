package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 25/05/17.
 */
public class AnaliseTecnicaDTOTest {

    private AnaliseTecnicaDTO analiseTecnicaDTO;
    private Date data;

    @Before
    public void setUp() {
        analiseTecnicaDTO = new AnaliseTecnicaDTO();
        data = new Date();
    }

    @Test
    public void getDataEnvioPublicacaoNula() {
        analiseTecnicaDTO.setDataEnvioPublicacao(null);
        assertNull(analiseTecnicaDTO.getDataEnvioPublicacao());
    }

    @Test
    public void getDataEnvioPublicacaoNaoNula() {
        analiseTecnicaDTO.setDataEnvioPublicacao(data);
        assertEquals(data, analiseTecnicaDTO.getDataEnvioPublicacao());
    }

}
