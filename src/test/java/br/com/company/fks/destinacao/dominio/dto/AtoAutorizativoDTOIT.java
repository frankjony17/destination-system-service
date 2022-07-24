package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by diego on 25/05/17.
 */
@IntegrationTest("server.port:0")
public class AtoAutorizativoDTOIT {

    private AtoAutorizativoDTO atoAutorizativoDTO;
    private Date data;

    @Before
    public void setUp() {
        atoAutorizativoDTO = new AtoAutorizativoDTO();
        data = new Date();
    }

    @Test
    public void getDataPublicacaoNula() {
        atoAutorizativoDTO.setDataPublicacao(null);
        assertNull(atoAutorizativoDTO.getDataPublicacao());
    }

    @Test
    public void getDataPublicacaoNaoNula() {
        atoAutorizativoDTO.setDataPublicacao(data);
        assertEquals(data, atoAutorizativoDTO.getDataPublicacao());
    }

}
