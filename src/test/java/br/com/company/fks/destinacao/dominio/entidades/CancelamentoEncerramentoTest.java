package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class CancelamentoEncerramentoTest {

    private CancelamentoEncerramento cancelamentoEncerramento;

    @Before
    public void setUp(){ cancelamentoEncerramento = new CancelamentoEncerramento(); }

    @Test
    public void getDataCancelamentoEncerramento() throws Exception{
        Date date = new Date();
        cancelamentoEncerramento.setDataCancelamentoEncerramento(date);
        assertEquals(date, cancelamentoEncerramento.getDataCancelamentoEncerramento());
    }

    @Test
    public void getDataCancelamentoEncerramentoNull(){
        cancelamentoEncerramento.setDataCancelamentoEncerramento(null);
        assertEquals(null, cancelamentoEncerramento.getDataCancelamentoEncerramento());
    }
}
