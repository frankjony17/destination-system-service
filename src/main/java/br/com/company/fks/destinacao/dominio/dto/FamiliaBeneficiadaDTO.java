package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by diego on 10/11/16.
 */

public class FamiliaBeneficiadaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Integer sequencial;
    @Getter
    @Setter
    private String nomeResponsavel;
    @Getter
    @Setter
    private String cpfResponsavel;
    @Getter
    @Setter
    private String nomeConjuge;
    @Getter
    @Setter
    private String cpfConjuge;
    @Getter
    @Setter
    private Double areaUtilizada;

}
