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
public class TipoDestinacaoIT {
    private static final int ID_TIPO_DESTINACAO = 1;

    private TipoDestinacao tipoDestinacao;

    @Before
    public void setUp(){
        tipoDestinacao = new TipoDestinacao();
    }

    @Test
    public void getId() throws Exception {
        tipoDestinacao.setId(ID_TIPO_DESTINACAO);
        assertEquals(Integer.valueOf(ID_TIPO_DESTINACAO), tipoDestinacao.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoDestinacao.setDescricao("teste");
        assertEquals("teste", tipoDestinacao.getDescricao());
    }
}
