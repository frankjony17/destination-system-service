package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class DestinacaoDTOTest {

    private DestinacaoDTO destinacaoDTO;

    @Before
    public void setUp(){
        destinacaoDTO = new DestinacaoDTO();
    }
    @Test
    public void getDataInicioFundamento() {
        Date date = new Date();
        destinacaoDTO.setDataInicioFundamento(date);
        assertEquals(date, destinacaoDTO.getDataInicioFundamento());
    }

    @Test
    public void getDataInicioFundamentoNull() {
        destinacaoDTO.setDataInicioFundamento(null);
        assertEquals(null, destinacaoDTO.getDataInicioFundamento());
    }

    @Test
    public void getDataFinalFundamento() {
        Date date = new Date();
        destinacaoDTO.setDataFinalFundamento(date);
        assertEquals(date, destinacaoDTO.getDataFinalFundamento());
    }

    @Test
    public void getDataFinalFundamentoNull() {
        destinacaoDTO.setDataFinalFundamento(null);
        assertEquals(null, destinacaoDTO.getDataFinalFundamento());
    }

    @Test
    public void getDataDestinacaoHistorico(){
        Date date = new Date();
        destinacaoDTO.setDataDestinacaoHistorico(date);
        assertEquals(date, destinacaoDTO.getDataDestinacaoHistorico());
    }

    @Test
    public void getDataDestinacaoHistoricoNull(){
        destinacaoDTO.setDataDestinacaoHistorico(null);
        assertEquals(null, destinacaoDTO.getDataDestinacaoHistorico());
    }
}
