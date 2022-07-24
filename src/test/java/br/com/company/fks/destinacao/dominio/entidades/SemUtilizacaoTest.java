package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by samuel on 15/05/17.
 */
public class SemUtilizacaoTest {
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
        assertFalse(semUtilizacao.equals(semUtilizacao2));
    }

    @Test
    public void hashcode() {
        semUtilizacao = criarSemUtilizacao(1L);
        semUtilizacao2 = criarSemUtilizacao(1L);
        assertEquals(semUtilizacao.hashCode(), semUtilizacao2.hashCode());
    }

    private SemUtilizacao criarSemUtilizacao(Long id){
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        semUtilizacao.setId(id);
        return semUtilizacao;
    }
}
