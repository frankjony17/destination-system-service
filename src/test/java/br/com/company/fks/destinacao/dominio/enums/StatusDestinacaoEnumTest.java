package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 23/01/17.
 */
public class StatusDestinacaoEnumTest {

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