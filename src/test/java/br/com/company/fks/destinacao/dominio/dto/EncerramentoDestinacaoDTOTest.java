package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class EncerramentoDestinacaoDTOTest {

    private EncerramentoDestinacaoDTO encerramentoDestinacaoDTO;
    private Date data;

    @Before
    public void setUp(){
        encerramentoDestinacaoDTO = new EncerramentoDestinacaoDTO();
        data = new Date();
    }

    @Test
    public void getDataEncerramentoDestinacaoNula(){
        encerramentoDestinacaoDTO.setDataEncerramentoDestinacao(null);
        assertNull(encerramentoDestinacaoDTO.getDataEncerramentoDestinacao());
    }

    @Test
    public void getDataEncerramentoDestinacaoNaoNula(){
        encerramentoDestinacaoDTO.setDataEncerramentoDestinacao(data);
        assertEquals(data, encerramentoDestinacaoDTO.getDataEncerramentoDestinacao());
    }
}
