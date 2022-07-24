package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class MotivoCancelamentoSuspensaoImovelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private Long ordem;

    public MotivoCancelamentoSuspensaoImovelDTO() {
    }

    public MotivoCancelamentoSuspensaoImovelDTO(Long id, String descricao, Long ordem) {
        this.id = id;
        this.descricao = descricao;
        this.ordem = ordem;
    }
}
