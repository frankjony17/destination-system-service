package br.com.company.fks.destinacao.dominio.enums;

import lombok.Getter;

/**
 * Created by diego on 17/01/17.
 */
@Getter
public enum StatusDestinacaoEnum {

    ATIVO(1, "Ativo"),
    CANCELADO(2, "Cancelado"),
    PENDENTE(3, "Pendente"),
    RASCUNHO(4, "Rascunho");

    private Integer codigo;
    private String descricao;

    StatusDestinacaoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Recupera o status da destinação pelo código.
     * @param codigo
     * @return
     */
    public static StatusDestinacaoEnum getByCodigo(Integer codigo) {
        StatusDestinacaoEnum statusDestinacaoEnum = null;
        for (StatusDestinacaoEnum value : values()) {
            if (value.getCodigo().equals(codigo)) {
                statusDestinacaoEnum = value;
                break;
            }
        }
        return statusDestinacaoEnum;
    }

}
