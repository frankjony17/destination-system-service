package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by rogerio on 16/05/17.
 */
public class DestinacaoPendenciaIDTeste {
    private DestinacaoPendenciaID destinacaoPendenciaID;
    private DestinacaoPendenciaID destinacaoPendenciaID1;

    @Before
    public void setUp() {
        destinacaoPendenciaID = new DestinacaoPendenciaID();
        destinacaoPendenciaID1 = new DestinacaoPendenciaID();
    }

    @Test
    public void equalsInstanciaIguais(){
        destinacaoPendenciaID = criarDestinacaoId(1L, 1L);
        assertTrue(destinacaoPendenciaID.equals(destinacaoPendenciaID));
    }

    @Test
    public void equalsInstanciaNaoIgual() {
        destinacaoPendenciaID = criarDestinacaoId(1L, 1L);
        assertFalse(destinacaoPendenciaID.equals(new Arquivo()));
    }

    @Test
    public void equalsNaoIgual() {
        destinacaoPendenciaID = criarDestinacaoId(1L, 1L);
        destinacaoPendenciaID1 = criarDestinacaoId(1L, 2L);
        assertFalse(destinacaoPendenciaID.equals(destinacaoPendenciaID1));
    }

    @Test
    public void equalsPendenciaNaoIgual() {
        destinacaoPendenciaID = criarDestinacaoId(1L, 1L);
        destinacaoPendenciaID1 = criarDestinacaoId(1L, 2L);
        destinacaoPendenciaID1.setDestinacao(destinacaoPendenciaID.getDestinacao());
        assertFalse(destinacaoPendenciaID.equals(destinacaoPendenciaID1));
    }

    private DestinacaoPendenciaID criarDestinacaoId(Long idDestinacao, Long idPendencia) {
        DestinacaoPendenciaID destinacaoPendenciaID = new DestinacaoPendenciaID();
        Destinacao destinacao = new Destinacao();
        destinacao.setId(idDestinacao);
        Pendencia pendencia = new Pendencia();
        pendencia.setId(idPendencia);
        pendencia.setModulo("Modulo");
        destinacaoPendenciaID.setDestinacao(destinacao);
        destinacaoPendenciaID.setPendencia(pendencia);
        return destinacaoPendenciaID;
    }

    @Test
    public void destinacaoPendenciaID() {
        DestinacaoPendenciaID destinacaoPendenciaID = new DestinacaoPendenciaID(new Destinacao(), new Pendencia());
        assertNotNull(destinacaoPendenciaID);
    }
}
