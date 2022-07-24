package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(PowerMockRunner.class)
public class OcupanteTest {

    private Ocupante ocupante;
    private Ocupante ocupante2;

    @Test
    public void instanciaIguais(){
        ocupante = criarOcupante(1L);
        assertTrue(ocupante.equals(ocupante));
    }

    @Test
    public void instanciaDiferentes(){
        ocupante = criarOcupante(1L);
        assertFalse(ocupante.equals(new Parcela()));
    }

    @Test
    public void idDiferente(){
        ocupante = criarOcupanteComCpfCnpj(1L, "111.111.111-11");
        ocupante2 = criarOcupanteComCpfCnpj(2L, "111.111.111-11");
        assertFalse(ocupante.equals(ocupante2));
    }

    @Test
    public void idIgual(){
        ocupante = criarOcupanteComCpfCnpj(1L, "111.111.111-11");
        ocupante2 = criarOcupanteComCpfCnpj(1L, "111.111.111-11");
        Assert.assertTrue(ocupante.equals(ocupante2));
    }

    @Test
    public void hashcode(){
        ocupante = criarOcupanteComCpfCnpj(1L, "111.111.111-11");
        ocupante2 = criarOcupanteComCpfCnpj(1L, "111.111.111-11");
        assertEquals(ocupante.hashCode(), ocupante2.hashCode());
    }

    private Ocupante criarOcupante(Long id){
        Ocupante ocupante = new Ocupante();
        ocupante.setId(1L);
        return ocupante;
    }

    private Ocupante criarOcupanteComCpfCnpj(Long id, String cpfCnpj){
        Ocupante ocupante = new Ocupante();
        ocupante.setId(id);
        ocupante.setCpfCnpj(cpfCnpj);
        return ocupante;
    }
}
