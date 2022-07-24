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
public class PublicacaoDTOIT {

    private PublicacaoDTO publicacaoDTO;
    private Date data;

    @Before
    public void setUp() {
        publicacaoDTO = new PublicacaoDTO();
        data = new Date();
    }

    @Test
    public void getDataPublicacaoNula() {
        publicacaoDTO.setDataPublicacao(null);
        assertNull(publicacaoDTO.getDataPublicacao());
    }

    @Test
    public void getDataPublicacaoNaoNula() {
        publicacaoDTO.setDataPublicacao(data);
        assertEquals(data, publicacaoDTO.getDataPublicacao());
    }

}
