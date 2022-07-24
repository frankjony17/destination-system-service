package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.DoubleHolder;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class FamiliaBeneficiadaTest {

    private FamiliaBeneficiada familiaBeneficiada;

    @Before
    public void setUp(){
        familiaBeneficiada = new FamiliaBeneficiada();
    }


    @Test
    public void getId() throws Exception {
        familiaBeneficiada.setId(1l);
        assertEquals(Long.valueOf(1), familiaBeneficiada.getId());
    }

    @Test
    public void getSequencial() throws Exception {
        familiaBeneficiada.setSequencial(10);
        assertEquals(Integer.valueOf(10), familiaBeneficiada.getSequencial());
    }

    @Test
    public void getNomeResponsavel() throws Exception {
        familiaBeneficiada.setNomeResponsavel("teste");
        assertEquals("teste", familiaBeneficiada.getNomeResponsavel());
    }

    @Test
    public void getCpfResponsavel() throws Exception {
        familiaBeneficiada.setCpfResponsavel("03247831169");
        assertEquals("03247831169", familiaBeneficiada.getCpfResponsavel());
    }

    @Test
    public void getNomeConjuge() throws Exception {
        familiaBeneficiada.setNomeConjuge("teste");
        assertEquals("teste", familiaBeneficiada.getNomeConjuge());
    }

    @Test
    public void getCpfConjuge() throws Exception {
        familiaBeneficiada.setCpfConjuge("03247831169");
        assertEquals("03247831169", familiaBeneficiada.getCpfConjuge());
    }

    @Test
    public void getAreaUtilizada() throws Exception {
        familiaBeneficiada.setAreaUtilizada(Double.valueOf(01));
        assertEquals(Double.valueOf(01), familiaBeneficiada.getAreaUtilizada());
    }

    @Test
    public void getResponsavel() throws Exception {
        Responsavel responsavel = new Responsavel();
        familiaBeneficiada.setResponsavel(responsavel);
        assertEquals(responsavel, familiaBeneficiada.getResponsavel());
    }

}