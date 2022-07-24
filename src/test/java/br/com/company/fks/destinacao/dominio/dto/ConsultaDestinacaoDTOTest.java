package br.com.company.fks.destinacao.dominio.dto;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ConsultaDestinacaoDTOTest {

    private Date date;
    private ConsultaDestinacaoDTO consultaDestinacaoDTO;


    @Before
    public void setUp(){
        consultaDestinacaoDTO = new ConsultaDestinacaoDTO();
        date = new Date();
    }

    @Test
    public void getDataInicioFundamentoNula(){
        consultaDestinacaoDTO.setDataInicioFundamento(null);
        assertNull(consultaDestinacaoDTO.getDataInicioFundamento());
    }

    @Test
    public void getDataInicioFundamentoNaoNula(){
        consultaDestinacaoDTO.setDataInicioFundamento(date);
        assertEquals(date, consultaDestinacaoDTO.getDataInicioFundamento());
    }

    @Test
    public void getDataFimFundamentoNula(){
        consultaDestinacaoDTO.setDataFimFundamento(null);
        assertNull(consultaDestinacaoDTO.getDataFimFundamento());
    }

    @Test
    public void getDataFimFundamentoNaoNula(){
        consultaDestinacaoDTO.setDataFimFundamento(date);
        assertEquals(date, consultaDestinacaoDTO.getDataFimFundamento());
    }
}
