package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 07/10/2016.
 */

public class RepresentanteLegalDTO implements Serializable {
    @Getter
    @Setter
    private String cpfCnpj;
    @Getter
    @Setter
    private String nome;

}
