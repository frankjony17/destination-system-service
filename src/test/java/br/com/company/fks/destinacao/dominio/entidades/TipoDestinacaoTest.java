package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class TipoDestinacaoTest {

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