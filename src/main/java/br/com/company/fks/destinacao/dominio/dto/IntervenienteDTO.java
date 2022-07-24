package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.EnderecoCorrespondencia;
import br.com.company.fks.destinacao.dominio.entidades.Telefone;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


public class IntervenienteDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String cpfCnpj;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String codigoUg;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private List<Telefone> telefones;
    @Getter
    @Setter
    private EnderecoCorrespondencia enderecoCorrespondencia;
    @Getter
    @Setter
    private DadosResponsavel dadosResponsavel;
    @Getter
    @Setter
    private Boolean isPossui;
}
