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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 04/10/2016.
 */
@Getter @Setter
@Entity
@Table(name = "tb_utilizacao", schema = "destinacao")
@Audited
@AuditTable(value = "tb_utilizacao_aud", schema = "aud_destinacao")
public class Utilizacao implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_utilizacao_seq", name = "tb_utilizacao_seq")
    @GeneratedValue(generator = "tb_utilizacao_seq", strategy = GenerationType.AUTO)
    @Column(name="id_utilizacao")
    private Long id;

    @Column(name="ds_finalidade")
    private String finalidade;

    @Column(name="nu_familias_beneficiadas")
    private Integer numeroFamiliasBeneficiadas;

    @Column(name="nu_servidores")
    private Integer numeroServidores;

    @Column(name = "nu_area_servidor")
    private Double areaServidor;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_tipo_utilizacao")
    private TipoUtilizacao tipoUtilizacao;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_sub_tipo_utilizacao")
    private SubTipoUtilizacao subTipoUtilizacao;

    @Column(name = "ds_especificacao_utilizacao")
    private String especificacao;

    @Column(name = "dt_utilizacao")
    private Date dataUtilizacao;

    @Column(name = "ic_processo_posse")
    private Boolean processoPosse;

    @Column(name = "num_processo")
    private String numeroProcesso;

    @Column(name = "ds_anotacoes")
    private String anotacoes;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_efetivacao_utilizacao")
    private Date dataEfetivacaoUtilizacao;


    public void setDataUtilizacao(final Date dataUtilizacao){
        if(dataUtilizacao == null){
            this.dataUtilizacao = null;
        }
        else{
            this.dataUtilizacao = new Date(dataUtilizacao.getTime());
        }
    }

    public Date getDataUtilizacao(){
        if(this.dataUtilizacao == null){
            return null;
        }
        return new Date(this.dataUtilizacao.getTime());
    }

    public void setDataEfetivacaoUtilizacao(final Date dataEfetivacaoUtilizacao){
        if(dataEfetivacaoUtilizacao == null){
            this.dataEfetivacaoUtilizacao = null;
        }
        else{
            this.dataEfetivacaoUtilizacao = new Date(dataEfetivacaoUtilizacao.getTime());
        }
    }

    public Date getDataEfetivacaoUtilizacao(){
        if(this.dataEfetivacaoUtilizacao == null){
            return null;
        }
        return new Date(this.dataEfetivacaoUtilizacao.getTime());
    }


}
