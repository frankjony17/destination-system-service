package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ContratoIT {

    Contrato contrato;

    @Before
    public void setUp(){
        contrato = new Contrato();
    }

    @Test
    public void getId(){
        contrato.setId(1l);
        assertEquals(Long.valueOf(1), contrato.getId());
    }

    @Test
    public void getDataInicio(){
        Date dataInicio = new Date();
        contrato.setDataInicio(dataInicio);
        assertEquals(dataInicio, contrato.getDataInicio());
    }

    @Test
    public void getDataInicioNull(){
        contrato.setDataInicio(null);
        assertEquals(null, contrato.getDataInicio());
    }

    @Test
    public void getNumero(){
        contrato.setNumero("teste");
        assertEquals("teste", contrato.getNumero());
    }

    @Test
    public void getArquivo(){
        Arquivo arquivo = new Arquivo();
        contrato.setArquivo(arquivo);
        assertEquals(arquivo, contrato.getArquivo());
    }

    @Test
    public void getDataFinal(){
        Date dataFinal = new Date();
        contrato.setDataFinal(dataFinal);
        assertEquals(dataFinal, contrato.getDataFinal());
    }

    @Test
    public void getDataFinalNull(){
        contrato.setDataFinal(null);
        assertEquals(null, contrato.getDataFinal());
    }
}
