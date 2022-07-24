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

public class ParcelaIT {

    private Parcela parcela;
    private Parcela parcela2;

    @Test
    public void equalsInstanciaIguais(){
        parcela = criaParcela(1L);
        assertTrue(parcela.equals(parcela));
    }

    @Test
    public void equalsInstanciaNaoIguais(){
        parcela = criaParcela(1L);
        assertFalse(parcela.equals(new Benfeitoria()));
    }

    @Test
    public void equalsSequencialIgual(){
        parcela = criarParcela(1L, "Exemplo");
        parcela2 = criarParcela(1L, "Exemplo");
        assertTrue(parcela.equals(parcela2));
    }

    @Test
    public void equalsIdDiferentes(){
        parcela = criaParcela(1L);
        parcela2 = criaParcela(2L);
        assertFalse(parcela.equals(parcela2));
    }

    @Test
    public void hashcode() {
        parcela = criarParcela(1L, "exemplo");
        parcela2 = criarParcela(1L, "exemplo");
        assertEquals(parcela.hashCode(), parcela2.hashCode());
    }

    public Parcela criarParcela(Long id, String ex){
        Parcela parcela = new Parcela();
        parcela.setId(id);
        parcela.setSequencial(ex);
        return parcela;
    }

    public Parcela criaParcela(Long id){
        Parcela parcela = new Parcela();
        parcela.setId(id);
        return parcela;
    }

}
