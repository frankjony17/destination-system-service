package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 04/01/17.
 */
public class EmailEnumTest {

    @Test
    public void getAssunto() {
        EmailEnum.CANCELAMENTO.setAssunto("teste");
        String assunto = EmailEnum.CANCELAMENTO.getAssunto();
        assertEquals(assunto, EmailEnum.CANCELAMENTO.getAssunto());
    }

    @Test
    public void getPath() {
        String path = EmailEnum.CANCELAMENTO.getPath();
        assertEquals(path, EmailEnum.CANCELAMENTO.getPath());
    }

    @Test
    public void getParams() {
        String [] params = EmailEnum.CANCELAMENTO.getParams();
        assertArrayEquals(params, EmailEnum.CANCELAMENTO.getParams());
    }

}
