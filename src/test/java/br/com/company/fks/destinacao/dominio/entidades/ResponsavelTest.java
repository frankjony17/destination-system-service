package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class ResponsavelTest {

    @Test
    public void setDataObito(){
        Responsavel responsavel = new Responsavel();
        responsavel.setDataObito(null);
        assertEquals(null, responsavel.getDataObito());
    }

    @Test
    public void getDataObito(){
        Responsavel responsavel = new Responsavel();
        Date date = new Date();
        responsavel.setDataObito(date);
        assertEquals(date, responsavel.getDataObito());
    }
}
