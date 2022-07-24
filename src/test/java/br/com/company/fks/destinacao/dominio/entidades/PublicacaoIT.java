package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class PublicacaoIT {
    private Publicacao publicacao;

    @Before
    public void setUp(){
        publicacao = new Publicacao();
    }

    @Test
    public void getId(){
        publicacao.setId(1l);
        assertEquals(Long.valueOf(1), publicacao.getId());
    }

    @Test
    public void getNumeroPagina(){
        publicacao.setNumeroPagina(1);
        assertEquals(Integer.valueOf(1), publicacao.getNumeroPagina());
    }

    @Test
    public void getNumeroSecao(){
        publicacao.setNumeroSecao(1);
        assertEquals(Integer.valueOf(1), publicacao.getNumeroSecao());
    }

    @Test
    public void getDataPublicacao(){
        Date data = new Date();
        publicacao.setDataPublicacao(data);
        assertEquals(data, publicacao.getDataPublicacao());
    }

    @Test
    public void getDataPublicacaoNull(){
        publicacao.setDataPublicacao(null);
        assertEquals(null, publicacao.getDataPublicacao());
    }
}
