package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 09/11/2016.
 */

public class TipoModalidadeDTO implements Serializable {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String descricao;
}
