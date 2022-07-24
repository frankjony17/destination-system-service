package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class TipoPeriocidadeTest {

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