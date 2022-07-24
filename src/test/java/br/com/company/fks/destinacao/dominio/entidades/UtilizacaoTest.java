package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class UtilizacaoTest {

    private Utilizacao utilizacao;

    @Before
    public void setUp(){
        utilizacao = new Utilizacao();
    }


    @Test
    public void getId() throws Exception {
        utilizacao.setId(1l);
        assertEquals(Long.valueOf(1), utilizacao.getId());
    }

    @Test
    public void getFinalidade() throws Exception {
        utilizacao.setFinalidade("teste");
        assertEquals("teste", utilizacao.getFinalidade());
    }

    @Test
    public void getNumeroFamiliasBeneficiadas() throws Exception {
        utilizacao.setNumeroFamiliasBeneficiadas(1);
        assertEquals(Integer.valueOf(1), utilizacao.getNumeroFamiliasBeneficiadas());
    }

    @Test
    public void getNumeroServidores() throws Exception {
        utilizacao.setNumeroServidores(1);
        assertEquals(Integer.valueOf(1), utilizacao.getNumeroServidores());
    }

    @Test
    public void getAreaServidor() throws Exception {
        utilizacao.setAreaServidor(Double.valueOf(1));
        assertEquals(Double.valueOf(1), utilizacao.getAreaServidor());
    }

    @Test
    public void getDataUtilizacao(){
        Date date = new Date();
        utilizacao.setDataUtilizacao(date);
        assertEquals(date, utilizacao.getDataUtilizacao());
    }

    @Test
    public void getDataUtilizacaoNull(){
        utilizacao.setDataUtilizacao(null);
        assertEquals(null, utilizacao.getDataUtilizacao());
    }

    @Test
    public void DataEfetivacaoUtilizacao(){
        Date date = new Date();
        utilizacao.setDataEfetivacaoUtilizacao(date);
        assertEquals(date, utilizacao.getDataEfetivacaoUtilizacao());
    }

    @Test
    public void DataEfetivacaoUtilizacaoNull(){
        utilizacao.setDataEfetivacaoUtilizacao(null);
        assertEquals(null, utilizacao.getDataEfetivacaoUtilizacao());
    }
}