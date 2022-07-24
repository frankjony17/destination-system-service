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
public class CdruIT {

    private Cdru cdru;

    @Before
    public void setUp(){

        cdru = new Cdru();
    }

    @Test
    public void getTipoConcessao() throws Exception {
        TipoConcessao tipoConcessao = new TipoConcessao();
        cdru.setTipoConcessao(tipoConcessao);
        assertEquals(tipoConcessao, cdru.getTipoConcessao());
    }

    @Test
    public void getContrato() throws Exception {
        Contrato contrato = new Contrato();
        cdru.setContrato(contrato);
        assertEquals(contrato, cdru.getContrato());
    }

   /* @Test
    public void getCertidaoCartorial() throws Exception {
        CertidaoCartorial certidaoCartorial = new CertidaoCartorial();
        cdru.setCertidaoCartorial(certidaoCartorial);
        assertEquals(certidaoCartorial, cdru.getCertidaoCartorial());
    }*/
}
