package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
public class TipoEspecificacaoDTO implements Serializable{
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String descricao;
}
