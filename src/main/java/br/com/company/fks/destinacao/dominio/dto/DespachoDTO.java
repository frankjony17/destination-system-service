package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by diego on 29/11/16.
 */

public class DespachoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private TipoDespachoEnum tipoDespacho;
    @Getter
    @Setter
    private String justificativa;

}
