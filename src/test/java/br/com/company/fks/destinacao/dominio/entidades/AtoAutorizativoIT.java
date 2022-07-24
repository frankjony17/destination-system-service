package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by haillanderson on 21/08/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class AtoAutorizativoIT {

    AtoAutorizativo atoAutorizativo;

    @Before
    public void setup(){
        atoAutorizativo = new AtoAutorizativo();
    }

    @Test
    public void getId(){
        atoAutorizativo.setId(1l);
        assertEquals(Long.valueOf(1), atoAutorizativo.getId());
    }

    @Test
    public void getNumeroAto(){
        atoAutorizativo.setNumeroAto(654654L);
        assertTrue(654654L == atoAutorizativo.getNumeroAto());
    }

    @Test
    public void getTipoAtoAutorizativo(){
        TipoAtoAutorizativo tipoAtoAutorizativo = new TipoAtoAutorizativo();
        atoAutorizativo.setTipoAtoAutorizativo(tipoAtoAutorizativo);
        assertEquals(tipoAtoAutorizativo, atoAutorizativo.getTipoAtoAutorizativo());
    }

    @Test
    public void getDataPublicacao(){
        Date dataPublicacao = new Date();
        atoAutorizativo.setDataPublicacao(dataPublicacao);
        assertEquals(dataPublicacao, atoAutorizativo.getDataPublicacao());
    }

    @Test
    public void getDataPublicacaoNull(){
        atoAutorizativo.setDataPublicacao(null);
        assertEquals(null, atoAutorizativo.getDataPublicacao());
    }
}
