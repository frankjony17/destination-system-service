package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by diego on 18/01/17.
 */

public class EdificacaoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private DominioDTO tipoEspecializacaoEdificacao;

}
