package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by basis on 16/12/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_publicacao", schema = "destinacao")
@Audited
@AuditTable(value = "tb_publicacao_aud", schema = "aud_destinacao")
public class Publicacao implements Serializable{

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_publicacao_seq", name = "tb_publicacao_seq")
    @GeneratedValue(generator = "tb_publicacao_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_publicacao")
    private Long id;

    @Column(name = "nu_pagina")
    private Integer numeroPagina;

    @Column(name = "nu_secao")
    private Integer numeroSecao;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_publicacao")
    private Date dataPublicacao;

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
     * Get da dataPublicação sobrescrito
     * @return dataPublicacao
     */
    public Date getDataPublicacao(){
        if(this.dataPublicacao == null){
            return null;
        }
        return new Date(this.dataPublicacao.getTime());
    }
}
