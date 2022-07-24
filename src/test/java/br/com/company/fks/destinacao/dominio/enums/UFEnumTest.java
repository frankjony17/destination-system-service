package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 04/01/17.
 */
public class UFEnumTest {

    @Test
    public void getPorCodigo() {
        UFEnum ufEnumEsperado = UFEnum.DF;
        UFEnum ufEnum = UFEnum.getPorCodigo("DF");
        assertEquals(ufEnumEsperado, ufEnum);
    }

    @Test
    public void getPorCodigoSemRetorno() {
        UFEnum ufEnum = UFEnum.getPorCodigo("Df");
        assertEquals(null, ufEnum);
    }

    @Test
    public void getDescricao() {
        String descricao = UFEnum.DF.getDescricao();
        assertEquals(descricao, UFEnum.DF.getDescricao());
    }

    @Test
    public void getSigla() {
        String sigla = UFEnum.DF.getSigla();
        assertEquals(sigla, UFEnum.DF.getSigla());
    }

}
