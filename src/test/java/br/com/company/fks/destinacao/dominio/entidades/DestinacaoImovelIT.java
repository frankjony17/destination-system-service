package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DestinacaoImovelIT {
    private DestinacaoImovel destinacaoImovel;

    @Before
    public void setUp(){
        destinacaoImovel = new DestinacaoImovel();
    }

    @Test
    public void getId() throws Exception {
        destinacaoImovel.setId(1l);
        assertEquals(Long.valueOf(1), destinacaoImovel.getId());
    }

    @Test
    public void getDestinacao() throws Exception {
        Destinacao destinacao = new Destinacao();
        destinacaoImovel.setDestinacao(destinacao);
        assertEquals(destinacao, destinacaoImovel.getDestinacao());
    }

    @Test
    public void getImovel() throws Exception {
        Imovel imovel = new Imovel();
        destinacaoImovel.setImovel(imovel);
        assertEquals(imovel, destinacaoImovel.getImovel());
    }

    @Test
    public void getCodigoUtilizacao() throws Exception {
        destinacaoImovel.setCodigoUtilizacao("01");
        assertEquals("01", destinacaoImovel.getCodigoUtilizacao());
    }
}
