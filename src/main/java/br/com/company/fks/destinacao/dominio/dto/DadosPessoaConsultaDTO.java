package br.com.company.fks.destinacao.dominio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaConsultaDTO implements Serializable{
    @Getter
    @Setter
    private String cpfConsultor;
    @Getter
    @Setter
    private String cpfCnpjConsultado;
    @Getter
    @Setter
    private String funcionalidadeConsultora;
    @Getter
    @Setter
    private Integer periodoMinimo;
    @Getter
    @Setter
    private String ipConsultor;
    @Getter
    @Setter
    private FundamentoLegalDTO fundamentoLegal;
    @Getter
    @Setter
    private String tipoDestinacao;



}
