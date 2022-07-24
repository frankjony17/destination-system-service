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
public class TipoPagamentoIT {

    private TipoPagamento tipoPagamento;

    @Before
    public void setUp(){
        tipoPagamento = new TipoPagamento();
    }

    @Test
    public void getId() throws Exception {
        tipoPagamento.setId(1);
        assertEquals(Integer.valueOf(1), tipoPagamento.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoPagamento.setDescricao("teste");
        assertEquals("teste", tipoPagamento.getDescricao());
    }

    @Test
    public void TipoPagamentoConstrutor(){
        tipoPagamento = new TipoPagamento(1);
        assertEquals(Integer.valueOf(1), tipoPagamento.getId());
    }
}
