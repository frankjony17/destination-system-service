package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class TipoPagamentoTest {

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