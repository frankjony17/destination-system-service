package br.com.company.fks.destinacao.dominio.enums;

import java.util.Set;

/**
 * Created by diego on 22/12/16.
 */
public enum PermissaoAnaliseEnum {

    EXEC_ANALISE_TECNICO("DESTINACAO_EXEC_ANALISE_TEC_TECNICO"),
    EXEC_ANALISE_CHEFIA("DESTINACAO_EXEC_ANALISE_TEC_CHEFIA"),
    EXEC_ANALISE_SUPERINTENDENTE("DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE"),
    EXEC_ANALISE_SECRETARIO("DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO"),
    /*Permissão inserida para aumentar cobertura dos testes*/
    PERMISSAO_TESTE("TESTE");

    private String descricao;

    PermissaoAnaliseEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static PermissaoAnaliseEnum getPermissaoAnaliseDescricao(Set<String> permissoes) {
        PermissaoAnaliseEnum permissaoAnalise = null;
        for (String key : permissoes) {
            permissaoAnalise = getPermissaoAnaliseDescricao(key);
            if (permissaoAnalise != null) {
                break;
            }
        }
        return permissaoAnalise;
    }

    private static PermissaoAnaliseEnum getPermissaoAnaliseDescricao(String permissao) {
        PermissaoAnaliseEnum permissaoAnalise = null;
        for (PermissaoAnaliseEnum permissaoAnaliseEnum : PermissaoAnaliseEnum.values()) {
            if (permissaoAnaliseEnum.getDescricao().equals(permissao)) {
                permissaoAnalise = permissaoAnaliseEnum;
                break;
            }
        }
        return permissaoAnalise;
    }
}
