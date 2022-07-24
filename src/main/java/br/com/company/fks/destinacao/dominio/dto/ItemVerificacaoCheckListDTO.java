package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by diego on 02/12/16.
 */

public class ItemVerificacaoCheckListDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private Long codFundamentoLegal;

    public ItemVerificacaoCheckListDTO() {
    }

    public ItemVerificacaoCheckListDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
