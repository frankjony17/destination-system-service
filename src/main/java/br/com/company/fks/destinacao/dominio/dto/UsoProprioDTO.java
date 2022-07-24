package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by basis on 30/06/17.
 */

public class UsoProprioDTO extends DestinacaoDTO implements Serializable{
    @Getter
    @Setter
    private Boolean homologado;
    @Getter
    @Setter
    private Long idResponsavelCadastro;
    @Getter
    @Setter
    private String observacao;
}
