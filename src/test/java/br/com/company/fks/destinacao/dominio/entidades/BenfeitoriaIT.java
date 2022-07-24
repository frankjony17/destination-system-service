package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class BenfeitoriaIT {

    private Benfeitoria primeiraBenfeitoria;
    private Benfeitoria segundaBenfeitoria;

    @Test
    public void equalsIdDiferente() {
        primeiraBenfeitoria = criarBenfeitoria(1L, 1L, BigDecimal.TEN);
        segundaBenfeitoria = criarBenfeitoria(2L, 1L, BigDecimal.TEN);
        assertFalse(primeiraBenfeitoria.equals(segundaBenfeitoria));
    }

    @Test
    public void equalsIdCadImovelDiferente() {
        primeiraBenfeitoria = criarBenfeitoria(1L, 1L, BigDecimal.TEN);
        segundaBenfeitoria = criarBenfeitoria(1L, 2L, BigDecimal.TEN);
        assertFalse(primeiraBenfeitoria.equals(segundaBenfeitoria));
    }

    @Test
    public void equalsAreaDiferente() {
        primeiraBenfeitoria = criarBenfeitoria(1L, 1L, BigDecimal.TEN);
        segundaBenfeitoria = criarBenfeitoria(1L, 1L, BigDecimal.ONE);
        assertFalse(primeiraBenfeitoria.equals(segundaBenfeitoria));
    }

    @Test
    public void equalsMesmaInstanciaIguais() {
        primeiraBenfeitoria = criarBenfeitoria(1L, 1L, BigDecimal.TEN);
        assertTrue(primeiraBenfeitoria.equals(primeiraBenfeitoria));
    }

    @Test
    public void equalsMesmaNaoInstanciaBenfeitoria() {
        primeiraBenfeitoria = criarBenfeitoria(1L, 1L, BigDecimal.TEN);
        assertFalse(primeiraBenfeitoria.equals(new Arquivo()));
    }

    @Test
    public void hashcode() {
        primeiraBenfeitoria = criarBenfeitoria(1L, 1L, BigDecimal.TEN);
        segundaBenfeitoria = criarBenfeitoria(1L, 1L, BigDecimal.TEN);
        assertEquals(primeiraBenfeitoria.hashCode(), segundaBenfeitoria.hashCode());
    }

    private Benfeitoria criarBenfeitoria(Long id, Long idCadImovel, BigDecimal areaConstruida) {
        Benfeitoria benfeitoria = new Benfeitoria();
        benfeitoria.setId(id);
        benfeitoria.setIdBenfeitoriaCadImovel(idCadImovel);
        benfeitoria.setAreaConstruida(areaConstruida);
        return benfeitoria;
    }

}
