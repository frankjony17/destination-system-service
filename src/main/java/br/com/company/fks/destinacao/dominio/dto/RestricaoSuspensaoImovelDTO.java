package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Classe de imovel suspensao restricao
 */

public class RestricaoSuspensaoImovelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private String permissao;
    @Getter
    @Setter
    private Long ordem;
    @Getter
    @Setter
    private String codigoSistemico;

    public RestricaoSuspensaoImovelDTO() {
    }

    public RestricaoSuspensaoImovelDTO(Long id, String descricao, String permissao, Long ordem, String codigoSistemico) {
        this.id = id;
        this.descricao = descricao;
        this.permissao = permissao;
        this.ordem = ordem;
        this.codigoSistemico = codigoSistemico;
    }
}

