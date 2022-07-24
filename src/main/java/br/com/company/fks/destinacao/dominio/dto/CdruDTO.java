package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * javadoc
 */

public class CdruDTO extends DestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private TipoConcessaoDTO tipoConcessao;

}
