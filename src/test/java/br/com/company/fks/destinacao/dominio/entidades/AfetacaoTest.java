package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class AfetacaoTest {

    private Afetacao afetacao;

    @Before
    public void setUp(){ afetacao = new Afetacao(); }

    @Test
    public void getPrazoDaReserva(){
        Date date = new Date();
        afetacao.setPrazoDaReserva(date);
        assertEquals(date, afetacao.getPrazoDaReserva());
    }

    @Test
    public void getPrazoDaReservaNull(){
        afetacao.setPrazoDaReserva(null);
        assertEquals(null, afetacao.getPrazoDaReserva());
    }

    @Test
    public void getDataDoAto(){
        Date date = new Date();
        afetacao.setDataDoAto(date);
        assertEquals(date, afetacao.getDataDoAto());
    }

    @Test
    public void getDataDoAtoNull(){
        afetacao.setDataDoAto(null);
        assertEquals(null, afetacao.getDataDoAto());
    }

    @Test
    public void getDataPublicacao(){
        Date date = new Date();
        afetacao.setDataPublicacao(date);
        assertEquals(date, afetacao.getDataPublicacao());
    }

    @Test
    public void getDataPublicacaoNull(){
        afetacao.setDataPublicacao(null);
        assertEquals(null, afetacao.getDataPublicacao());
    }
}
