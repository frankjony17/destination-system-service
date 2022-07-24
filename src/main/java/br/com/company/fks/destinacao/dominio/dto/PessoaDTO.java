package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by haillanderson on 18/07/17.
 */


public class PessoaDTO implements Serializable {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String numeroDocumento;
}
