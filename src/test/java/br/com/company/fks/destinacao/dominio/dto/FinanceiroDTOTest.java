package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 25/05/17.
 */
public class FinanceiroDTOTest {

    private FinanceiroDTO financeiroDTO;
    private Date data;

    @Before
    public void setUp() {
        financeiroDTO = new FinanceiroDTO();
        data = new Date();
    }

    @Test
    public void getDataInicioCobrancaNula() {
        financeiroDTO.setDataInicioCobranca(null);
        assertNull(financeiroDTO.getDataInicioCobranca());
    }

    @Test
    public void getDataInicioCobrancaNaoNula() {
        financeiroDTO.setDataInicioCobranca(data);
        assertEquals(data, financeiroDTO.getDataInicioCobranca());
    }

}
