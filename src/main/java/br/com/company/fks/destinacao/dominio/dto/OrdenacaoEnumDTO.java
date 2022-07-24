package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by tawan-souza on 08/12/17.
 */

@NoArgsConstructor
public class OrdenacaoEnumDTO implements Serializable{
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private Integer codigo;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private Boolean exibir;

    public OrdenacaoEnumDTO(String nome, Integer codigo, String descricao) {
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public OrdenacaoEnumDTO(String nome, Integer codigo, String descricao, Boolean exibir) {
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.exibir = exibir;
    }
}
