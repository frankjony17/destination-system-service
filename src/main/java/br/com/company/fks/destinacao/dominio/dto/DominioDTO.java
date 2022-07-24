package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */

public class DominioDTO implements Serializable {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String descricao;

    public DominioDTO(){}

    public DominioDTO (Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}
