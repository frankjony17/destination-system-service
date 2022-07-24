package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 17/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class UFEnumIT {
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
