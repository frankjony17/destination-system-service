package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class DadosPessoaJuridicaDTOTest {

    private DadosPessoaJuridicaDTO dadosPessoaJuridicaDTO;
    private Date date;

    @Before
    public void setUp(){
        dadosPessoaJuridicaDTO = new DadosPessoaJuridicaDTO();
        date = new Date();
    }

    @Test
    public void setDataSituacaoCadastral(){
        dadosPessoaJuridicaDTO.setDataSituacaoCadastral(date);
        assertEquals(date, dadosPessoaJuridicaDTO.getDataSituacaoCadastral());
    }

    @Test
    public void setDataSituacaoCadastralNull(){
        dadosPessoaJuridicaDTO.setDataSituacaoCadastral(null);
        assertNull(dadosPessoaJuridicaDTO.getDataSituacaoCadastral());
    }

    @Test
    public void setDataAbertura(){
        dadosPessoaJuridicaDTO.setDataAbertura(date);
        assertEquals(date, dadosPessoaJuridicaDTO.getDataAbertura());
    }

    @Test
    public void setDataAberturaNull(){
        dadosPessoaJuridicaDTO.setDataAbertura(null);
        assertNull(dadosPessoaJuridicaDTO.getDataAbertura());
    }

    @Test
    public void setDataOpcaoSimples(){
        dadosPessoaJuridicaDTO.setDataOpcaoSimples(date);
        assertEquals(date, dadosPessoaJuridicaDTO.getDataOpcaoSimples());
    }

    @Test
    public void setDataOpcaoSimplesNull(){
        dadosPessoaJuridicaDTO.setDataOpcaoSimples(null);
        assertNull(dadosPessoaJuridicaDTO.getDataOpcaoSimples());
    }

    @Test
    public void setDataExclusaoSimples(){
        dadosPessoaJuridicaDTO.setDataExclusaoSimples(date);
        assertEquals(date, dadosPessoaJuridicaDTO.getDataExclusaoSimples());
    }

    @Test
    public void setDataExclusaoSimplesNull(){
        dadosPessoaJuridicaDTO.setDataExclusaoSimples(null);
        assertNull(dadosPessoaJuridicaDTO.getDataExclusaoSimples());
    }

    @Test
    public void setDataCriacao(){
        dadosPessoaJuridicaDTO.setDataCriacao(date);
        assertEquals(date, dadosPessoaJuridicaDTO.getDataCriacao());
    }

    @Test
    public void setDataCriacaoNull(){
        dadosPessoaJuridicaDTO.setDataCriacao(null);
        assertNull(dadosPessoaJuridicaDTO.getDataCriacao());
    }
}
