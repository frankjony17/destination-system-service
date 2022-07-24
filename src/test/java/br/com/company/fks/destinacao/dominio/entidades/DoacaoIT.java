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
public class DoacaoIT {
    private Doacao doacao;

    @Before
    public void setUp(){
        doacao = new Doacao();
    }

    @Test
    public void getExisteEncargo() throws Exception {
        doacao.setExisteEncargo(true);
        assertEquals(true, doacao.getExisteEncargo());
    }

    @Test
    public void getContrato() throws Exception {
        Contrato contrato = new Contrato();
        doacao.setContrato(contrato);
        assertEquals(contrato, doacao.getContrato());
    }

    @Test
    public void getTipoInstrumento() throws Exception {
        TipoInstrumento tipoInstrumento = new TipoInstrumento();
        doacao.setTipoInstrumento(tipoInstrumento);
        assertEquals(tipoInstrumento, doacao.getTipoInstrumento());
    }

    /*@Test
    public void getCertidaoCartorial() throws Exception {
        CertidaoCartorial certidaoCartorial = new CertidaoCartorial();
        doacao.setCertidaoCartorial(certidaoCartorial);
        assertEquals(certidaoCartorial, doacao.getCertidaoCartorial());
    }*/
}
