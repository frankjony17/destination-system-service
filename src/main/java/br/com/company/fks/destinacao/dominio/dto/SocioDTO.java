package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by basis on 14/11/17.
 */

public class SocioDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String tipo;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String numero;
    @Getter
    @Setter
    private Integer percentualParticipacao;
    @Getter
    @Setter
    private String codigoPaisOrigem;
    @Getter
    @Setter
    private String nomePaisOrigem;
}
