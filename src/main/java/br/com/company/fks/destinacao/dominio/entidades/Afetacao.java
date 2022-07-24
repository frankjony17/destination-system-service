package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.Constants;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_afetacao", schema = "destinacao")
@PrimaryKeyJoinColumn(name = "id_afetacao")
@AuditTable(value = "tb_afetacao_aud", schema = "aud_destinacao")
@Audited
public class Afetacao implements Serializable {

    private final static String id_afetacao = "id_afetacao";

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_afetacao_seq", name = "tb_afetacao_seq")
    @GeneratedValue(generator = "tb_afetacao_seq", strategy = GenerationType.AUTO)
    @Column(name= id_afetacao)
    private Long id;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_tipo_afetacao")
    private TipoAfetacao tipo;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_tipo_acao")
    private TipoAcao tipoDeAcao;

    @Column(name = "ds_especificar")
    private String especificar;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_prazo_reserva")
    private Date prazoDaReserva;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_tipo_ato")
    private TipoAto tipoDeAto;

    @Column(name = "nu_numero_ato")
    private Integer numeroAto;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_data_ato")
    private Date dataDoAto;

    @Column(name = "ic_publicado")
    private Boolean publicado;

    @Column(name = "ds_pagina")
    private String pagina;

    @Column(name = "ds_secao")
    private String secao;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_publicacao")
    private Date dataPublicacao;

    @Column(name = "ic_imovel")
    private Boolean isImovel;

    @NotAudited
    @ManyToMany
    @JoinTable(name = "tb_afetacao_utilizacao", schema = Constants.SCHEMA, joinColumns = {
            @JoinColumn(name = id_afetacao)}, inverseJoinColumns = {
            @JoinColumn(name = "id_tipo_utilizacao")})
    private List<TipoUtilizacao> tipoDeUso;

    @NotAudited
    @ManyToMany
    @JoinTable(name = "tb_afetacao_sub_utilizacao", schema = Constants.SCHEMA, joinColumns = {
            @JoinColumn(name = id_afetacao)}, inverseJoinColumns = {
            @JoinColumn(name = "id_sub_tipo_utilizacao")})
    private List<SubTipoUtilizacao> especificacao;

    @NotAudited
    @ManyToMany
    @JoinTable(name = "tb_afetacao_arquivo", schema = Constants.SCHEMA, joinColumns = {
            @JoinColumn(name = id_afetacao)}, inverseJoinColumns = {
            @JoinColumn(name = "id_arquivo")})
    private List<Arquivo> documentos;

    @NotAudited
    @ManyToMany
    @JoinTable(name = "tb_afetacao_imovel", schema = Constants.SCHEMA, joinColumns = {
            @JoinColumn(name = id_afetacao)}, inverseJoinColumns = {
            @JoinColumn(name = "id_imovel")})
    private List<Imovel> imoveis;




    public void setPrazoDaReserva(final Date prazoDaReserva){
        if(prazoDaReserva == null){
            this.prazoDaReserva = null;
        }
        else{
            this.prazoDaReserva = new Date(prazoDaReserva.getTime());
        }
    }

    public Date getPrazoDaReserva(){
        if(this.prazoDaReserva == null){
            return null;
        }
        return new Date(this.prazoDaReserva.getTime());
    }

    public Date getDataDoAto(){
        if(this.dataDoAto == null){
            return null;
        }
        return new Date(this.dataDoAto.getTime());
    }

    public void setDataDoAto(final Date dataDoAto){
        if(dataDoAto == null){
            this.dataDoAto = null;
        }
        else{
            this.dataDoAto = new Date(dataDoAto.getTime());
        }
    }
    public Date getDataPublicacao(){
        if(this.dataPublicacao == null){
            return null;
        }
        return new Date(this.dataPublicacao.getTime());
    }

    public void setDataPublicacao(final Date dataPublicacao){
        if(dataPublicacao == null){
            this.dataPublicacao = null;
        }
        else{
            this.dataPublicacao = new Date(dataPublicacao.getTime());
        }
    }





}
