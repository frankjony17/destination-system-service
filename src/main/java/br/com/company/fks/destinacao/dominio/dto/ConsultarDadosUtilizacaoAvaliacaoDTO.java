package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tawan-souza on 23/08/17.
 */

public class ConsultarDadosUtilizacaoAvaliacaoDTO implements Serializable {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private ImovelDTO imovel;
    @Getter
    @Setter
    private DestinacaoDTO destinacao;
    @Getter
    @Setter
    private String codigoUtilizacao;
    @Getter
    @Setter
    private BigDecimal areaTerrenoDestinada;
    @Getter
    @Setter
    private BigDecimal fracaoIdeal;
    @Getter
    @Setter
    private String memorialDescAreaConstruida;
    @Getter
    @Setter
    private String codigoParcela;
    @Getter
    @Setter
    private String rip;


}
