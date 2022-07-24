package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by diego on 13/06/17.
 */

public class FiltroDestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private Integer offset;
    @Getter
    @Setter
    private Integer limit;
    @Getter
    @Setter
    private String rip;
    @Getter
    @Setter
    private String codigoUtilizacao;
    @Getter
    @Setter
    private String codigoParcela;
    @Getter
    @Setter
    private Long idTipoUtilizacao;
    @Getter
    @Setter
    private Long idSubTipoUtilizacao;
    @Getter
    @Setter
    private List<Integer> tiposDestinacao;
    @Getter
    @Setter
    private String pais;
    @Getter
    @Setter
    private String cep;
    @Getter
    @Setter
    private String uf;
    @Getter
    @Setter
    private String municipio;
    @Getter
    @Setter
    private String cidadeExterior;
    @Getter
    @Setter
    private String nomeResponsavel;
    @Getter
    @Setter
    private String cpfCnpjResponsavel;
    @Getter
    @Setter
    private String codigoContrato;
    @Getter
    @Setter
    private BigDecimal fracaoIdealInicial;
    @Getter
    @Setter
    private BigDecimal fracaoIdealFinal;
    @Getter
    @Setter
    private BigDecimal areaConstruidaInicial;
    @Getter
    @Setter
    private BigDecimal areaConstruidaFinal;
    @Getter
    @Setter
    private Long classificacao;
    @Getter
    @Setter
    private FundamentoLegalDTO fundamentoLegal;

}
