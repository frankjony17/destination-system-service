package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class PendenciaIT {
    private Pendencia pendencia;
    private Pendencia pendencia1;

    @Before
    public void setUp(){
        pendencia = new Pendencia();
        pendencia1 = new Pendencia();
    }

    @Test
    public void getId() throws Exception {
        pendencia.setId(1l);
        assertEquals(Long.valueOf(1), pendencia.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        pendencia.setDescricao("teste");
        assertEquals("teste", pendencia.getDescricao());
    }

    @Test
    public void equalsInstanciaIgual(){
        pendencia = criarPendencia(1L, "Modulo");
        assertTrue(pendencia.equals(pendencia));
    }

    @Test
    public void equalsInstaciaDiferente(){
        pendencia = criarPendencia(1L, "Modulo");
        assertFalse(pendencia.equals(new Imovel()));
    }

    @Test
    public void equalsIdDiferentes(){
        pendencia = criarPendencia(1L, "Modulo");
        pendencia1 = criarPendencia(2L, "Modulo");
        assertFalse(pendencia.equals(pendencia1));
    }

    @Test
    public void equalsModuloDiferente(){
        pendencia = criarPendencia(1L, "Modulo");
        pendencia1 = criarPendencia(1L, "Modulo1");
        assertFalse(pendencia.equals(pendencia1));
    }

    @Test
    public void hashcode(){
        pendencia = criarPendencia(1L, "Modulo");
        pendencia1 = criarPendencia(1L, "Modulo");
        assertEquals(pendencia.hashCode(),pendencia1.hashCode());
    }

    private Pendencia criarPendencia(Long id, String modulo){
        Pendencia pendencia = new Pendencia();
        pendencia.setId(id);
        pendencia.setModulo(modulo);
        return pendencia;
    }
}
