package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class TipoCancelamentoImovelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private Long ordem;
    @Getter
    @Setter
    private String codigoSistemico;

    public TipoCancelamentoImovelDTO() {

    }

    public TipoCancelamentoImovelDTO(Long id, String descricao, Long ordem, String codigoSistemico) {
        this.id = id;
        this.descricao = descricao;
        this.ordem = ordem;
        this.codigoSistemico = codigoSistemico;
    }
}


