package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.enums.AdotarEnderecoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class EnderecoCorrespondenciaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private AdotarEnderecoEnum adotarEnderecoEnum;
    @Getter
    @Setter
    private Endereco endereco;

}
