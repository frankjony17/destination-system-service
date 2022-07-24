package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class RipSuspensaoImovelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String rip;
    @Getter
    @Setter
    private SuspensaoImovelDTO suspensaoImovel;
    @Getter
    @Setter
    private boolean ativo;

    public RipSuspensaoImovelDTO(String rip) {
        this.rip = rip;
    }

    public RipSuspensaoImovelDTO() {

    }
}
