package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class CuemTest {

    private Cuem cuem;

    @Before
    public void setUp(){
        cuem = new Cuem();
    }

    @Test
    public void getTipoModalidade() throws Exception {
        TipoModalidade tipoModalidade = new TipoModalidade();
        cuem.setTipoModalidade(tipoModalidade);
        assertEquals(tipoModalidade, cuem.getTipoModalidade());
    }

    @Test
    public void getContrato() throws Exception {
        Contrato contrato = new Contrato();
        cuem.setContrato(contrato);
        assertEquals(contrato, cuem.getContrato());
    }

}