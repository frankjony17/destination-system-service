package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis on 3/29/2017.
 */


public class SubTipoDocumentoDTO extends DominioDTO implements Serializable {
    @Getter
    @Setter
    private DominioDTO tipoDocumento;

}