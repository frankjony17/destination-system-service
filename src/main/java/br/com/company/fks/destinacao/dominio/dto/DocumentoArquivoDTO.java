package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by haillanderson on 11/07/17.
 */


public class DocumentoArquivoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long idArquivo;
    @Getter
    @Setter
    private ArquivoDTO arquivo;
}
