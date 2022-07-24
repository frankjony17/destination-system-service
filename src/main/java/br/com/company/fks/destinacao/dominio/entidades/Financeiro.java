package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_financeiro", schema = "destinacao")
@Audited
@AuditTable(value = "tb_financeiro_aud", schema = "aud_destinacao")
public class Financeiro implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_financeiro_seq", name = "tb_financeiro_seq")
    @GeneratedValue(generator = "tb_financeiro_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_financeiro")
    private Long id;

    @Column(name = "nu_fcc")
    private String nuFCC;

    @Column(name="ic_fonte_recursos")
    private Boolean fonteRecursos;

    @Column(name = "nu_valor")
    private BigDecimal valor;

    @NotAudited
    @ManyToOne
    @JoinColumn(name ="co_instituicao_financeira")
    private InstituicaoFinanceira instituicaoFinanceira;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_tipo_periocidade")
    private TipoPeriocidade tipoPeriocidade;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_inicio_cobranca")
    private Date dataInicioCobranca;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_tipo_indice_reajuste_anual")
    private TipoReajuste tipoIndiceReajusteAnual;

    @Column(name = "nu_qtd_meses_carencia")
    private Long carenciaMeses;

    @Column(name = "ds_mes_ano_reajuste")
    private String mesAnoReajuste;

    @Column(name = "ds_dia_vencimento_mensal")
    private String diaVencimento;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_tipo_juros_mensal")
    private TipoJuro tipoJurosMensal;

    @Column(name = "vl_juros_mensal")
    private Double jurosMensal;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_tipo_indice_reajuste_mensal")
    private TipoReajuste tipoIndiceJurosMensal;

    @Column(name = "vl_multa_inadimplencia")
    private Double multaInadimplacia;

    @Column(name = "nu_qtd_parcelas")
    private Integer numeroParcelas;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_tipo_pagamento")
    private TipoPagamento tipoPagamento;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_tipo_moeda")
    private TipoMoeda tipoMoeda;

    @Column(name = "vl_entrada")
    private BigDecimal valorEntrada;

    @Column(name = "vl_financidado")
    private BigDecimal valorFinancidado;

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataInicioCobranca
     */
    public void setDataInicioCobranca(final Date dataInicioCobranca){
        if(dataInicioCobranca == null){
            this.dataInicioCobranca = null;
        }
        else{
            this.dataInicioCobranca = new Date(dataInicioCobranca.getTime());
        }
    }

    /**
     * Get da dataInicioCobrancao sobrescrito
     * @return dataInicioCobranca
     */
    public Date getDataInicioCobranca(){
        if(this.dataInicioCobranca == null){
            return null;
        }
        return new Date(this.dataInicioCobranca.getTime());
    }
}
