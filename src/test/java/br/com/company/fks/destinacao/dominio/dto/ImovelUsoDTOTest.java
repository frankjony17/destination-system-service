package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 25/05/17.
 */
public class ImovelUsoDTOTest {

    private ImovelUsoDTO imovelUsoDTO;
    private Date data;

    @Before
    public void setUp() {
        imovelUsoDTO = new ImovelUsoDTO();
        data = new Date();
    }

    @Test
    public void getDataInicioCobrancaNula() {
        imovelUsoDTO.setDataVigencia(null);
        assertNull(imovelUsoDTO.getDataVigencia());
    }

    @Test
    public void getDataInicioCobrancaNaoNula() {
        imovelUsoDTO.setDataVigencia(data);
        assertEquals(data, imovelUsoDTO.getDataVigencia());
    }

}
