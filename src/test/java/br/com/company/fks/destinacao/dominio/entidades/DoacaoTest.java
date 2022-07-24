package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class DoacaoTest {

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

   /* @Test
    public void getCertidaoCartorial() throws Exception {
        CertidaoCartorial certidaoCartorial = new CertidaoCartorial();
        doacao.setCertidaoCartorial(certidaoCartorial);
        assertEquals(certidaoCartorial, doacao.getCertidaoCartorial());
    }*/

}