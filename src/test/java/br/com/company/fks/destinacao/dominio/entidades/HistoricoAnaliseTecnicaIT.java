package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class HistoricoAnaliseTecnicaIT {
    private HistoricoAnaliseTecnica historicoAnaliseTecnica;

    @Before
    public void setUp(){
        historicoAnaliseTecnica = new HistoricoAnaliseTecnica();
    }

    @Test
    public void getDataAlteracao(){
        Date date = new Date();
        historicoAnaliseTecnica.setDataAlteracao(date);
        assertEquals(date, historicoAnaliseTecnica.getDataAlteracao());
    }

    @Test
    public void getDataAlteracaoNull(){
        historicoAnaliseTecnica.setDataAlteracao(null);
        assertEquals(null, historicoAnaliseTecnica.getDataAlteracao());
    }
}
