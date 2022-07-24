package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.enums.DescricaoParentescoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class ResidenteDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Integer sequencial;
    @Getter
    @Setter
    private String cpf;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private DescricaoParentescoEnum descricao;
    @Getter
    @Setter
    private String descricaoOutro;
    @Getter
    @Setter
    private Double renda;

}
