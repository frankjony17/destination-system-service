package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class AfetacaoDTOTest {

    private AfetacaoDTO afetacaoDTO;
    private Date date;

    @Before
    public void setUp(){
        afetacaoDTO = new AfetacaoDTO();
        date = new Date();
    }

    @Test
    public void setPrazoDaReserva(){
        afetacaoDTO.setPrazoDaReserva(date);
        assertEquals(date, afetacaoDTO.getPrazoDaReserva());
    }

    @Test
    public void setPrazoDaReservaNull(){
        afetacaoDTO.setPrazoDaReserva(null);
        assertNull(afetacaoDTO.getPrazoDaReserva());
    }

    @Test
    public void setDataDoAto(){
        afetacaoDTO.setDataDoAto(date);
        assertEquals(date, afetacaoDTO.getDataDoAto());
    }

    @Test
    public void setDataDoAtoNull(){
        afetacaoDTO.setDataDoAto(null);
        assertNull(afetacaoDTO.getDataDoAto());
    }

    @Test
    public void setDataPublicacao(){
        afetacaoDTO.setDataPublicacao(date);
        assertEquals(date, afetacaoDTO.getDataPublicacao());
    }

    @Test
    public void setDataPublicacaoNull(){
        afetacaoDTO.setDataPublicacao(null);
        assertNull(afetacaoDTO.getDataPublicacao());
    }
}
