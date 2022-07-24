package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by basis on 16/01/17.
 */

public class LocalizacaoEctDTO implements Serializable {
    @Getter
    @Setter
    private String cep;
    @Getter
    @Setter
    private String uf;
    @Getter
    @Setter
    private String municipio;
    @Getter
    @Setter
    private String tipoLogradouro;
    @Getter
    @Setter
    private String logradouro;
    @Getter
    @Setter
    private String bairro;
}
