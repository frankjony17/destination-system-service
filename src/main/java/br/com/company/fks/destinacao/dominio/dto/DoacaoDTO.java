package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class DoacaoDTO extends DestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private Boolean existeEncargo;
    @Getter
    @Setter
    private TipoInstrumentoDTO tipoInstrumento;

}
