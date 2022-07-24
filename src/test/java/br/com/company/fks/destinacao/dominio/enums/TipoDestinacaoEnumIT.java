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
public class TipoDestinacaoEnumIT {
    @Test
    public void descricao() {
        String descricao = TipoDestinacaoEnum.CDRU.descricao();
        assertEquals(descricao, TipoDestinacaoEnum.CDRU.descricao());
    }

    @Test
    public void codigo() {
        Integer codigo = TipoDestinacaoEnum.CDRU.codigo();
        assertEquals(codigo, TipoDestinacaoEnum.CDRU.codigo());
    }

    @Test
    public void getPorCodigo() {
        TipoDestinacaoEnum tipoDestinacaoEnumEsperada = TipoDestinacaoEnum.CDRU;
        TipoDestinacaoEnum tipoDestinacaoEnum = TipoDestinacaoEnum.getPorCodigo(TipoDestinacaoEnum.CDRU.getCodigo());
        assertEquals(tipoDestinacaoEnumEsperada, tipoDestinacaoEnum);
    }

    @Test
    public void getPorCodigoSemResultado() {
        TipoDestinacaoEnum tipoDestinacaoEnum = TipoDestinacaoEnum.getPorCodigo(20);
        assertNull(tipoDestinacaoEnum);
    }

    @Test
    public void getDescricao() {
        String descricao = TipoDestinacaoEnum.CDRU.getDescricao();
        assertEquals(descricao, TipoDestinacaoEnum.CDRU.getDescricao());
    }

    @Test
    public void getCodigo() {
        Integer codigo = TipoDestinacaoEnum.CDRU.getCodigo();
        assertEquals(codigo, TipoDestinacaoEnum.CDRU.getCodigo());
    }

}
