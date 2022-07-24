package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class TipoLicitacaoTest {

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