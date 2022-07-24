package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by diego on 25/05/17.
 */
@IntegrationTest("server.port:0")
public class ImovelUsoDTOIT {

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
