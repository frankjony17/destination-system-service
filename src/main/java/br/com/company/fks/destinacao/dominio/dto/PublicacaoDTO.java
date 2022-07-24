package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by diego on 16/12/16.
 */

public class PublicacaoDTO extends InformacaoPublicacaoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;

}
