package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
public class HistoricoAnaliseTecnicaTest {
    private HistoricoAnaliseTecnica historicoAnaliseTecnica;

    @Before
    public void setUp(){
        historicoAnaliseTecnica = new HistoricoAnaliseTecnica();
    }
    
    @Test
    public void getDataAlteracao(){
        Date date = new Date();
        historicoAnaliseTecnica.setDataAlteracao(date);
        assertEquals(date, historicoAnaliseTecnica.getDataAlteracao());
    }

    @Test
    public void getDataAlteracaoNull(){
        historicoAnaliseTecnica.setDataAlteracao(null);
        assertEquals(null, historicoAnaliseTecnica.getDataAlteracao());
    }
}
