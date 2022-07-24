package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 07/10/2016.
 */

public class EnderecoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String cep;
    @Getter
    @Setter
    private String tipoLogradouro;
    @Getter
    @Setter
    private String logradouro;
    @Getter
    @Setter
    private String numero;
    @Getter
    @Setter
    private String complemento;
    @Getter
    @Setter
    private String municipio;
    @Getter
    @Setter
    private String bairro;
    @Getter
    @Setter
    private String uf;
    @Getter
    @Setter
    private String pais;
    @Getter
    @Setter
    private String cidadeExterior;
    @Getter
    @Setter
    private String codigoPostal;

}
