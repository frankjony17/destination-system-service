package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 04/01/17.
 */
public class PermissaoAnaliseEnumTest {

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
