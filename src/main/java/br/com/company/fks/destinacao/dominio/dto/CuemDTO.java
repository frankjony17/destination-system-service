package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 09/11/2016.
 */

public class CuemDTO extends DestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private  TipoModalidadeDTO tipoModalidade;

}
