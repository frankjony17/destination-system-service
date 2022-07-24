package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by rogerio on 15/05/17.
 */
public class DestinacaoPendenciaTest {

    private DestinacaoPendencia destinacaoPendencia;
    private DestinacaoPendencia destinacaoPendencia1;
    private Destinacao destinacao;
    private Pendencia pendencia;

    @Before
    public void setUp(){
        destinacaoPendencia = new DestinacaoPendencia();
        destinacaoPendencia1 = new DestinacaoPendencia();
        destinacao = new Destinacao();
        destinacao.setId(1L);
        pendencia = new Pendencia();
        pendencia.setId(1L);
    }
    @Test
    public void TestaConstrutor(){
        destinacaoPendencia = new DestinacaoPendencia(destinacao, pendencia);
        assertEquals(destinacaoPendencia.getDestinacaoPendenciaID(),destinacaoPendencia.getDestinacaoPendenciaID() );

    }

    @Test
    public void equalsInstanciaIguais(){
        destinacaoPendencia = criarDestinacaoPendencia(1L,1L, new Date());
        assertTrue(destinacaoPendencia.equals(destinacaoPendencia));
    }

    @Test
    public void equalsInstanciaNaoIguais(){
        destinacaoPendencia = criarDestinacaoPendencia(1L,1L, new Date());
        assertFalse(destinacaoPendencia.equals(new Imovel()));
    }

    @Test
    public void equalsIdNaoIguais(){
        destinacaoPendencia = criarDestinacaoPendencia(1L,1L, new Date());
        destinacaoPendencia1 = criarDestinacaoPendencia(2L, 1L, new Date());
        assertFalse(destinacaoPendencia.equals(destinacaoPendencia1));
    }

    @Test
    public void equalsDataGerada(){
        destinacaoPendencia = criarDestinacaoPendencia(1L, 1L, new Date(2017));
        destinacaoPendencia1 = criarDestinacaoPendencia(1L, 1L, new Date(2016));
        destinacaoPendencia1.setDestinacaoPendenciaID(destinacaoPendencia.getDestinacaoPendenciaID());
        assertFalse(destinacaoPendencia.equals(destinacaoPendencia1));
    }

    @Test
    public void hashcode(){
        destinacaoPendencia = criarDestinacaoPendencia(1L, 1L, new Date(2017));
        destinacaoPendencia1 = criarDestinacaoPendencia(1L, 2L, new Date(2017));
        destinacaoPendencia.setDestinacaoPendenciaID(destinacaoPendencia1.getDestinacaoPendenciaID());
        assertEquals(destinacaoPendencia.hashCode(), destinacaoPendencia1.hashCode());
    }

    @Test
    public void getDataGerada(){
        Date date = new Date();
        destinacaoPendencia.setDataGerada(date);
        assertEquals(date, destinacaoPendencia.getDataGerada());
    }

    @Test
    public void getDataGeradaNull(){
        destinacaoPendencia.setDataGerada(null);
        assertEquals(null, destinacaoPendencia.getDataGerada());
    }

    private DestinacaoPendencia criarDestinacaoPendencia(Long idDestinacao, Long idPendencia ,Date dataGerada){
        DestinacaoPendencia destinacaoPendencia = new DestinacaoPendencia();
        Destinacao destinacao = new Destinacao();
        destinacao.setId(idDestinacao);
        Pendencia pendencia = new Pendencia();
        pendencia.setId(idPendencia);
        pendencia.setModulo("Modulo");
        DestinacaoPendenciaID destinacaoPendenciaID = new DestinacaoPendenciaID(destinacao, pendencia);
        destinacaoPendencia.setDestinacaoPendenciaID(destinacaoPendenciaID);
        destinacaoPendencia.setDataGerada(dataGerada);

        return destinacaoPendencia;
    }
}
