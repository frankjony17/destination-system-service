package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 17/10/2016.
 */

public class TipoNaturezaJuridicaDTO implements Serializable {
    @Getter
    @Setter
    private Integer codigo;
    @Getter
    @Setter
    private String descricao;
}
