package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by diego on 18/01/17.
 */

public class BenfeitoriaDestinadaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long idBenfeitoria;
    @Getter
    @Setter
    private BigDecimal areaUtilizar;

}
