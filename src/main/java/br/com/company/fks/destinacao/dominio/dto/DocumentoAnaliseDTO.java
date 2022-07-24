package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by basis on 19/12/16.
 */

public class DocumentoAnaliseDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long idDocumento;
    @Getter
    @Setter
    private Boolean resposta;
    @Getter
    @Setter
    private String observacao;
    @Getter
    @Setter
    private String tipoDocumento;

}
