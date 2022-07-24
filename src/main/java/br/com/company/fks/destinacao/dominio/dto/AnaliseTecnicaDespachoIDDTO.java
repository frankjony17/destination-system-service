package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by diego on 19/12/16.
 */

public class AnaliseTecnicaDespachoIDDTO implements Serializable {

    @Getter
    @Setter
    private AnaliseTecnicaDTO analiseTecnica;
    @Getter
    @Setter
    private DespachoDTO despacho;

}
