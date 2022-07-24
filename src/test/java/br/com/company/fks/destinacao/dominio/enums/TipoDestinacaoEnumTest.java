package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 04/01/17.
 */
public class TipoDestinacaoEnumTest {

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

    @Test
    public void getPermissaoConsultar() {
        String permissaoConsultar = TipoDestinacaoEnum.CDRU.getPermissaoConsultar();
        assertEquals(permissaoConsultar, TipoDestinacaoEnum.CDRU.getPermissaoConsultar());
    }

    @Test
    public void permissaoConsultar() {
        String permissaoConsultar = TipoDestinacaoEnum.CDRU.permissaoConsultar();
        assertEquals(permissaoConsultar, TipoDestinacaoEnum.CDRU.permissaoConsultar());
    }

    @Test
    public void getPermissaoCadastrarEditar() {
        String permissaoCadastrarEditar = TipoDestinacaoEnum.CDRU.getPermissaoCadastrarEditar();
        assertEquals(permissaoCadastrarEditar, TipoDestinacaoEnum.CDRU.getPermissaoCadastrarEditar());
    }

    @Test
    public void permissaoCadastrarEditar() {
        String permissaoCadastrarEditar = TipoDestinacaoEnum.CDRU.permissaoCadastrarEditar();
        assertEquals(permissaoCadastrarEditar, TipoDestinacaoEnum.CDRU.permissaoCadastrarEditar());
    }

}
