package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 04/01/17.
 */
public class PerfilEnumTest {

    @Test
    public void buscarPorNome() {
        PerfilEnum perfilEnumEsperado = PerfilEnum.TECNICO;
        PerfilEnum perfilEnum = PerfilEnum.buscarPorNome("DESTINACAO.TECNICO");
        assertEquals(perfilEnumEsperado, perfilEnum);
    }

    @Test
    public void buscarPorNomeSemResultado() {
        PerfilEnum perfilEnum = PerfilEnum.buscarPorNome("DESTINACAO.TECNICOs");
        assertNull(perfilEnum);
    }

    @Test
    public void getNome() {
        String nomeEsperado = PerfilEnum.TECNICO.getNome();
        assertEquals(nomeEsperado, PerfilEnum.TECNICO.getNome());
    }

}
