package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.InstituicaoFinanceira;
import br.com.company.fks.destinacao.dominio.entidades.TipoJuro;
import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import br.com.company.fks.destinacao.dominio.entidades.TipoPagamento;
import br.com.company.fks.destinacao.dominio.entidades.TipoPeriocidade;
import br.com.company.fks.destinacao.dominio.entidades.TipoReajuste;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */

public class FinanceiroDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nuFCC;
    @Getter
    @Setter
    private BigDecimal valor;
    @Getter
    @Setter
    private TipoPeriocidade tipoPeriocidade;
    @Getter
    @Setter
    private InstituicaoFinanceira instituicaoFinanceira;
    @Getter
    @Setter
    private Boolean fonteRecursos;
    @Getter
    @Setter
    private Date dataInicioCobranca;
    @Getter
    @Setter
    private TipoReajuste tipoIndiceReajusteAnual;
    @Getter
    @Setter
    private Long carenciaMeses;
    @Getter
    @Setter
    private String mesAnoReajuste;
    @Getter
    @Setter
    private String diaVencimento;
    @Getter
    @Setter
    private TipoJuro tipoJurosMensal;
    @Getter
    @Setter
    private Double jurosMensal;
    @Getter
    @Setter
    private TipoReajuste tipoIndiceJurosMensal;
    @Getter
    @Setter
    private Double multaInadimplacia;
    @Getter
    @Setter
    private Integer numeroParcelas;
    @Getter
    @Setter
    private TipoPagamento tipoPagamento;
    @Getter
    @Setter
    private TipoMoeda tipoMoeda;
    @Getter
    @Setter
    private BigDecimal valorEntrada;
    @Getter
    @Setter
    private BigDecimal valorFinancidado;

    /**
     * Informa data da cobrança
     * @param data
     */
    public void setDataInicioCobranca(final Date data) {
        if (data == null) {
            this.dataInicioCobranca = null;
        } else {
            this.dataInicioCobranca = new Date(data.getTime());
        }
    }

    /**
     * Retorna a data de inicio de cobrança
     * @return
     */
    public Date getDataInicioCobranca() {
        if (this.dataInicioCobranca != null) {
            return new Date(this.dataInicioCobranca.getTime());
        }
        return null;
    }

}
