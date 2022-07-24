package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by rogerio on 17/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class PermissaoAnaliseEnumIT {
    @Test
    public void getDescricao() {
        String descricao = PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA.getDescricao();
        assertEquals(descricao, PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA.getDescricao());
    }

    @Test
    public void getPermissaoAnaliseDescricao() {
        PermissaoAnaliseEnum permissaoAnaliseEnumEsparado = PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA;
        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_CHEFIA");
        PermissaoAnaliseEnum permissaoAnaliseEnum = PermissaoAnaliseEnum.getPermissaoAnaliseDescricao(permissoes);
        assertEquals(permissaoAnaliseEnumEsparado, permissaoAnaliseEnum);
    }

    @Test
    public void getPermissaoAnaliseDescricaoSemResultado() {
        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_CHEFIAs");
        PermissaoAnaliseEnum permissaoAnaliseEnum = PermissaoAnaliseEnum.getPermissaoAnaliseDescricao(permissoes);
        assertNull(permissaoAnaliseEnum);
    }
}
