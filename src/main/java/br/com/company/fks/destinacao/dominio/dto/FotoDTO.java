package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by basis on 06/07/17.
 */

public class FotoDTO implements Serializable {
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
