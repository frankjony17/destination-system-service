package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class VendaTest {

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

  /*  @Test
    public void getCertidaoCartorial() throws Exception {
        CertidaoCartorial certidaoCartorial = new CertidaoCartorial();
        venda.setCertidaoCartorial(certidaoCartorial);
        assertEquals(certidaoCartorial, venda.getCertidaoCartorial());
    }*/

}