package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class TelefoneDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String ddd;
    @Getter
    @Setter
    private String numero;
    @Getter
    @Setter
    private TipoTelefoneDTO tipoTelefone;
    @Getter
    @Setter
    private Boolean isPrincipal;
}
