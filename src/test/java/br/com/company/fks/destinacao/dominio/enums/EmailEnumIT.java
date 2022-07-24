package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 17/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class EmailEnumIT {
    @Test
    public void setAssunto(){
        EmailEnum.CANCELAMENTO.setAssunto("teste");
        String assunto = EmailEnum.CANCELAMENTO.getAssunto();
        assertEquals(assunto, EmailEnum.CANCELAMENTO.getAssunto());
    }
}
