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
public class TipoLicitacaoIT {
    private TipoLicitacao tipoLicitacao;

    @Before
    public void setUp(){
        tipoLicitacao = new TipoLicitacao();
    }

    @Test
    public void getId() throws Exception {
        tipoLicitacao.setId(1);
        assertEquals(Integer.valueOf(1), tipoLicitacao.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoLicitacao.setDescricao("teste");
        assertEquals("teste",tipoLicitacao.getDescricao());
    }

    @Test
    public void TipoLicitacaoContrutor(){
        tipoLicitacao = new TipoLicitacao(1);
        assertEquals(Integer.valueOf(1), tipoLicitacao.getId());
    }
}
