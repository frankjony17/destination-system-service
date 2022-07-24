package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class VendaIT {
    private Venda venda;

    @Before
    public void setUp(){
        venda = new Venda();
    }

    @Test
    public void getContrato() throws Exception {
        Contrato contrato = new Contrato();
        venda.setContrato(contrato);
        assertEquals(contrato, venda.getContrato());
    }

   /* @Test
    public void getCertidaoCartorial() throws Exception {
        CertidaoCartorial certidaoCartorial = new CertidaoCartorial();
        venda.setCertidaoCartorial(certidaoCartorial);
        assertEquals(certidaoCartorial, venda.getCertidaoCartorial());
    }*/
}
