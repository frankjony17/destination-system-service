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
public class TipoPericiocidadeIT {
    private TipoPeriocidade tipoPeriocidade;

    @Before
    public void setUp(){
        tipoPeriocidade = new TipoPeriocidade();
    }

    @Test
    public void getId() throws Exception {
        tipoPeriocidade.setId(1);
        assertEquals(Integer.valueOf(1), tipoPeriocidade.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoPeriocidade.setDescricao("teste");
        assertEquals("teste", tipoPeriocidade.getDescricao());
    }

    @Test
    public void TipoPeriocidadeConstrutor(){
        tipoPeriocidade = new TipoPeriocidade(1);
        assertEquals(Integer.valueOf(1), tipoPeriocidade.getId());
    }
}
