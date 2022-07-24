package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class CessaoOnerosaDTO extends DestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private String tipoCessao;
    @Getter
    @Setter
    private Boolean envolvePagamento;

}
