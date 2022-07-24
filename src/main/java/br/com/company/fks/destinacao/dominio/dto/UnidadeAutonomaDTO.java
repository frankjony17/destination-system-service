package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by diego on 31/01/17.
 */

public class UnidadeAutonomaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long idUnidadeAutonomaCadImovel;
    @Getter
    @Setter
    private BigDecimal area;
    @Getter
    @Setter
    private BigDecimal areaDisponivel;
    @Getter
    @Setter
    private String especializacao;

}
