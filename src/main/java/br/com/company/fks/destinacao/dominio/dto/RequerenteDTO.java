package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 07/10/2016.
 */

public class RequerenteDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String natureza;
    @Getter
    @Setter
    private EnderecoDTO endereco;
    @Getter
    @Setter
    private String cpfCnpj;
    @Getter
    @Setter
    private RepresentanteLegalDTO representanteLegal;

}