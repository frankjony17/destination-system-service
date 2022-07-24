package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by diego on 29/11/16.
 */

public class DocumentoComplentarEspecificoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private AnaliseTecnicaDTO analiseTecnica;
    @Getter
    @Setter
    private ArquivoDTO arquivo;

}
