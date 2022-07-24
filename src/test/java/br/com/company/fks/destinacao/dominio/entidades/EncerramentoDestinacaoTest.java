package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class EncerramentoDestinacaoTest {

    private EncerramentoDestinacao encerramentoDestinacao;

    @Before
    public void setUp(){ encerramentoDestinacao = new EncerramentoDestinacao(); }

    @Test
    public void getDataEncerramentoDestinacao() throws  Exception{
        Date date = new Date();
        encerramentoDestinacao.setDataEncerramentoDestinacao(date);
        assertEquals(date, encerramentoDestinacao.getDataEncerramentoDestinacao());
    }

    @Test
    public void getDataEncerramentoDestinacaoNull(){
        encerramentoDestinacao.setDataEncerramentoDestinacao(null);
        assertEquals(null, encerramentoDestinacao.getDataEncerramentoDestinacao());
    }
}
