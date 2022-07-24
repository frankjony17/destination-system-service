package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class CancelamentoTest {

    private Cancelamento cancelamento;
    private Date date;

    @Before
    public void setUp(){
        cancelamento = new Cancelamento();
        date = new Date();
    }

    @Test
    public void setDataCancelamento(){
        cancelamento.setDataCancelamento(date);
        assertEquals(date, cancelamento.getDataCancelamento());
    }

    @Test
    public void setDataCancelamentoNull(){
        cancelamento.setDataCancelamento(null);
        assertNull(cancelamento.getDataCancelamento());
    }
}
