package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 17/10/2016.
 */

public class NaturezaJuridicaDTO implements Serializable {
    @Getter
    @Setter
    private TipoNaturezaJuridicaDTO tipoNaturezaJuridica;
    @Getter
    @Setter
    private String codigo;
    @Getter
    @Setter
    private String representanteEntidade;
    @Getter
    @Setter
    private String descricao;

}
