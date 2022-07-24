package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class ConsultaImovelDTO implements Serializable {
    @Getter
    @Setter
    private String rip;
    @Getter
    @Setter
    private String sequencialParcela;
    @Getter
    @Setter
    private String tipoDestinacao;
    @Getter
    @Setter
    private FundamentoLegalDTO fundamentoLegal;
    @Getter
    @Setter
    private Long idModalidade;



}
