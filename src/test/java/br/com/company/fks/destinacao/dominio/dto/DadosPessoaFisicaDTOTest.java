package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class DadosPessoaFisicaDTOTest {

    private DadosPessoaFisicaDTO dadosPessoaFisicaDTO;
    private Date date;

    @Before
    public void setUp(){
        dadosPessoaFisicaDTO = new DadosPessoaFisicaDTO();
        date = new Date();
    }

    @Test
    public void setDataNascimento(){
        dadosPessoaFisicaDTO.setDataNascimento(date);
        assertEquals(date, dadosPessoaFisicaDTO.getDataNascimento());
    }

    @Test
    public void setDataNascimentoNull(){
        dadosPessoaFisicaDTO.setDataNascimento(null);
        assertNull(dadosPessoaFisicaDTO.getDataNascimento());
    }

    @Test
    public void setDataCriacao(){
        dadosPessoaFisicaDTO.setDataCriacao(date);
        assertEquals(date, dadosPessoaFisicaDTO.getDataCriacao());
    }

    @Test
    public void setDataCriacaoNull(){
        dadosPessoaFisicaDTO.setDataCriacao(null);
        assertNull(dadosPessoaFisicaDTO.getDataCriacao());
    }
}
