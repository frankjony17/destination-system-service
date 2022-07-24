package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertFalse;

/**
 * Created by rogerio on 16/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DestinacaoPendenciaIDIT {
    private DestinacaoPendenciaID destinacaoPendenciaID;
    private DestinacaoPendenciaID destinacaoPendenciaID1;

    @Before
    public void setUp() {
        destinacaoPendenciaID = new DestinacaoPendenciaID();
        destinacaoPendenciaID1 = new DestinacaoPendenciaID();
    }

    @Test
    public void equalsInstanciaNaoIgual(){
        destinacaoPendenciaID = criarDestinacaoId(1L, 1L);
        assertFalse(destinacaoPendenciaID.equals(new Arquivo()));
    }

    @Test
    public void equalsPendenciaNaoIgual(){
        destinacaoPendenciaID = criarDestinacaoId(1L, 1L);
        destinacaoPendenciaID1 = criarDestinacaoId(1L, 2L);
        destinacaoPendenciaID1.setDestinacao(destinacaoPendenciaID.getDestinacao());
        assertFalse(destinacaoPendenciaID.equals(destinacaoPendenciaID1));
    }

    private DestinacaoPendenciaID criarDestinacaoId(Long idDestinacao, Long idPendencia){
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
}
