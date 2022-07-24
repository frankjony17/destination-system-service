package br.com.company.fks.destinacao.dominio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sdias on 3/29/2017.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_documento", schema = "destinacao")
@AuditTable(value = "tb_documento_aud", schema = "aud_destinacao")
@Audited
public class Documento implements Serializable {


    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_documento_seq", name = "tb_documento_seq")
    @GeneratedValue(generator = "tb_documento_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_documento")
    private Long id;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_tipo_documento")
    private TipoDocumento tipoDocumento;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_sub_tipo_documento")
    private SubTipoDocumento subTipoDocumento;

    @Column(name = "ic_dispensado")
    private Boolean dispensado;

    @Column(name = "ds_justificativa")
    private String justificativa;

    @Column(name = "ds_especificar")
    private String especificar;

    @Column(name = "ds_pagina")
    private String pagina;

    @Column(name = "ds_secao")
    private String secao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "co_destinacao")
    /*@Transient*/
    private Destinacao destinacao;

    @OneToOne
    @JoinColumn(name = "co_arquivo")
    private Arquivo arquivo;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_publicacao")
    private Date dataPublicacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_inicial_vigencia")
    private Date dataInicialVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_final_vigencia")
    private Date dataFinalVigencia;

    @Column(name = "ds_numero_termo")
    private String numeroTermo;

    @Column(name = "ds_livro")
    private String livro;

    @Column(name = "ds_folhas")
    private String folha;

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataPublicacao
     */
    public void setDataPublicacao(final Date dataPublicacao){
        if(dataPublicacao == null){
            this.dataPublicacao = null;
        }
        else{
            this.dataPublicacao = new Date(dataPublicacao.getTime());
        }
    }

    /**
     * Get da dataPublicacao sobrescrito
     * @return dataPublicacao
     */
    public Date getDataPublicacao(){
        if(this.dataPublicacao == null){
            return null;
        }
        return new Date(this.dataPublicacao.getTime());
    }

    /**
     * Set da dataInicialVigencia sobrescrito
     * @param dataInicialVigencia
     */
    public void setDataInicialVigencia(final Date dataInicialVigencia){
        if(dataInicialVigencia == null){
            this.dataInicialVigencia = null;
        }
        else{
            this.dataInicialVigencia = new Date(dataInicialVigencia.getTime());
        }
    }

    /**
     * Get da dataIniclaVigencia sobrescrito
     * @return dataIniciaVigencia
     */
    public Date getDataInicialVigencia(){
        if(this.dataInicialVigencia == null){
            return null;
        }
        return new Date(this.dataInicialVigencia.getTime());
    }

    /**
     * Set da dataFinalVigencia sobrescrito
     * @param dataFinalVigencia
     */
    public void setDataFinalVigencia(final Date dataFinalVigencia){
        if(dataFinalVigencia == null){
            this.dataFinalVigencia = null;
        }
        else{
            this.dataFinalVigencia = new Date(dataFinalVigencia.getTime());
        }
    }

    /**
     * Get da dataFinalVigencia sobrescrito
     * @return dataFinalVigencia
     */
    public Date getDataFinalVigencia(){
        if(this.dataFinalVigencia == null){
            return null;
        }
        return new Date(this.dataFinalVigencia.getTime());
    }
}
