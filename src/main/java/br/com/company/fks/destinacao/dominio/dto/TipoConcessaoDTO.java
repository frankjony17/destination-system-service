package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Classe TipoConcessaoDTO que implementa Serializable
 */

public class TipoConcessaoDTO implements Serializable{
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
}
