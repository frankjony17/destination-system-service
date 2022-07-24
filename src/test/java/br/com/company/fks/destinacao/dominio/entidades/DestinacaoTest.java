package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.dto.DestinacaoDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class DestinacaoTest {

    private Destinacao destinacao;

    @Before
    public void setUp() {
        destinacao = new Destinacao();
    }

    @Test
    public void getDataInicioFundamento() {
        Date date = new Date();
        destinacao.setDataInicioFundamento(date);
        assertEquals(date, destinacao.getDataInicioFundamento());
    }

    @Test
    public void getDataInicioFundamentoNull() {
        destinacao.setDataInicioFundamento(null);
        assertEquals(null, destinacao.getDataInicioFundamento());
    }

    @Test
    public void getDataFinalFundamento() {
        Date date = new Date();
        destinacao.setDataFinalFundamento(date);
        assertEquals(date, destinacao.getDataFinalFundamento());
    }

    @Test
    public void getDataFinalFundamentoNull() {
        destinacao.setDataFinalFundamento(null);
        assertEquals(null, destinacao.getDataFinalFundamento());
    }

    @Test
    public void getDataDestinacaoHistorico(){
        Date date = new Date();
        destinacao.setDataDestinacaoHistorico(date);
        assertEquals(date, destinacao.getDataDestinacaoHistorico());
    }

    @Test
    public void getDataDestinacaoHistoricoNull(){
        destinacao.setDataDestinacaoHistorico(null);
        assertEquals(null, destinacao.getDataDestinacaoHistorico());
    }
}
