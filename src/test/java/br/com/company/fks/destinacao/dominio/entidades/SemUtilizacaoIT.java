package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by samuel on 15/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")

public class SemUtilizacaoIT {
    private SemUtilizacao semUtilizacao;
    private SemUtilizacao semUtilizacao2;


    @Test
    public void equalsInstanciaIguais(){
        semUtilizacao = criarSemUtilizacao(1L);
        assertTrue(semUtilizacao.equals(semUtilizacao));
    }

    @Test
    public void equalsInstanciaNaoIguais(){
        semUtilizacao = criarSemUtilizacao(1L);
        assertFalse(semUtilizacao.equals(new Benfeitoria()));
    }

    @Test
    public void equalsIdNaoIgual(){
        semUtilizacao = criarSemUtilizacao(1L);
        semUtilizacao2 = criarSemUtilizacao(2L);
        assertTrue(semUtilizacao.equals(semUtilizacao2));
    }

    public SemUtilizacao criarSemUtilizacao(Long id){
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        semUtilizacao.setId(1l);
        return semUtilizacao;
    }

    @Test
    public void hashcode() {
        semUtilizacao = criarSemUtilizacao(1L);
        semUtilizacao2 = criarSemUtilizacao(1L);
        assertEquals(semUtilizacao.hashCode(), semUtilizacao2.hashCode());
    }


}
