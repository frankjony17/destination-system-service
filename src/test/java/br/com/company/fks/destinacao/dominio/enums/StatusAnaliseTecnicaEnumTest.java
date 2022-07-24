package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 04/01/17.
 */
public class StatusAnaliseTecnicaEnumTest {

    @Test
    public void getPorCodigo() {
        StatusAnaliseTecnicaEnum statusAnaliseTecnicaEnumEsperado = StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA;
        StatusAnaliseTecnicaEnum statusAnaliseTecnicaEnum =
                StatusAnaliseTecnicaEnum.getPorCodigo(StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA.getCodigo());
        assertEquals(statusAnaliseTecnicaEnumEsperado, statusAnaliseTecnicaEnum);
    }

    @Test
    public void getPorCodigoSemResultado() {
        StatusAnaliseTecnicaEnum statusAnaliseTecnicaEnum =
                StatusAnaliseTecnicaEnum.getPorCodigo(20);
        assertNull(statusAnaliseTecnicaEnum);
    }

    @Test
    public void getDescricao() {
        String descricao = StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA.getDescricao();
        assertEquals(descricao, StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA.getDescricao());
    }

    @Test
    public void getCodigo() {
        Integer codigoEsperado = StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO.getCodigo();
        assertEquals(codigoEsperado, StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO.getCodigo());
    }

}


