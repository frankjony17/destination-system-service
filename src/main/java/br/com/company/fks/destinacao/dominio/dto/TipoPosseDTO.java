package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 25/10/2016.
 */

public class TipoPosseDTO implements Serializable {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String descricao;
}
