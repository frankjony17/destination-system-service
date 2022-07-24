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
public class UtilizacaoIT {
    private Utilizacao utilizacao;

    @Before
    public void setUp(){
        utilizacao = new Utilizacao();
    }


    @Test
    public void getId() throws Exception {
        utilizacao.setId(1l);
        assertEquals(Long.valueOf(1), utilizacao.getId());
    }

    @Test
    public void getFinalidade() throws Exception {
        utilizacao.setFinalidade("teste");
        assertEquals("teste", utilizacao.getFinalidade());
    }

    @Test
    public void getNumeroFamiliasBeneficiadas() throws Exception {
        utilizacao.setNumeroFamiliasBeneficiadas(1);
        assertEquals(Integer.valueOf(1), utilizacao.getNumeroFamiliasBeneficiadas());
    }

    @Test
    public void getNumeroServidores() throws Exception {
        utilizacao.setNumeroServidores(1);
        assertEquals(Integer.valueOf(1), utilizacao.getNumeroServidores());
    }

    @Test
    public void getAreaServidor() throws Exception {
        utilizacao.setAreaServidor(Double.valueOf(1));
        assertEquals(Double.valueOf(1), utilizacao.getAreaServidor());
    }

}
