package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by diego on 23/01/17.
 */
public class AnaliseTecnicaDespachoIDTest {

    @Test
    public void testaInstancia() {
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID(new AnaliseTecnica(), new Despacho());
        assertNotNull(analiseTecnicaDespachoID);
    }

}