package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by rogerio on 17/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class StatusDestinacaoEnumIT {

    @Test
    public void getCodigo() throws Exception {
        Integer codigoEsperado = 1;
        assertEquals(codigoEsperado, StatusDestinacaoEnum.ATIVO.getCodigo());
    }

    @Test
    public void getDescricao() throws Exception {
        String descricaoEsperada = "Ativo";
        assertEquals(descricaoEsperada, StatusDestinacaoEnum.ATIVO.getDescricao());
    }

    @Test
    public void getByCodigo() {
        StatusDestinacaoEnum statusDestinacaoEnum = StatusDestinacaoEnum.getByCodigo(4);
        assertEquals(StatusDestinacaoEnum.RASCUNHO, statusDestinacaoEnum);
    }

    @Test
    public void getByCodigoSemResultado() {
        StatusDestinacaoEnum statusDestinacaoEnum = StatusDestinacaoEnum.getByCodigo(8);
        assertNull(statusDestinacaoEnum);
    }

}
