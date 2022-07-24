
package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by raphael on 29/11/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_analise_tecnica", schema = "destinacao")

public class AnaliseTecnica implements Serializable {

    private static final String TABELA = "analiseTecnicaDespachoID.analiseTecnica";

    @Id
    @Column(name = "id_analise_tecnica")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_analise_tecnica_seq", name = "tb_analise_tecnica_seq")
    @GeneratedValue(generator = "tb_analise_tecnica_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ic_documento_pendente")
    private Boolean documentoPendente;

    @Column(name = "ds_obs_documento_pendente")
    private String obsDocumentoPendente;

    @Column(name = "ds_info_complementar")
    private String informacaoComplementar;

    @Column(name = "ds_info_complementar_espeficia")
    private String informacaoComplementarEspecifica;

    @OneToMany(mappedBy = TABELA)
    private List<AnaliseTecnicaDespachoTecnico> despachosAnaliseTecnico;

    @OneToMany(mappedBy = TABELA)
    private List<AnaliseTecnicaDespachoChefia> despachosAnaliseChefia;

    @OneToMany(mappedBy = TABELA)
    private List<AnaliseTecnicaDespachoSuperintendente> despachosAnaliseSuperintendente;

    @OneToMany(mappedBy = TABELA)
    private List<AnaliseTecnicaDespachoSecretario> despachosAnaliseSecretario;

    @ManyToOne
    @JoinColumn(name = "co_status_analise_tecnica")
    private StatusAnaliseTecnica statusAnaliseTecnica;

    @Column(name = "co_fundamento_legal")
    private Long codFundamentoLegal;

    @OneToMany(mappedBy = "analiseTecnica", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DocumentoComplementarEspecifico> documentosComplementaresEspecificos;

    @OneToMany(mappedBy = "analiseTecnica", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DocumentoComplementar> documentosComplementares;

    @Column(name = "co_requerimento")
    private Long idRequerimento;

    @Column(name = "co_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "co_publicacao")
    private Publicacao publicacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_envio_publicacao")
    private Date dataEnvioPublicacao;

    @Transient
    private List<Despacho> despachosTecnico;

    @Transient
    private List<Despacho> despachosChefia;

    @Transient
    private List<Despacho> despachosSuperintendente;

    @Transient
    private List<Despacho> despachosSecretario;

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataEnvioPublicacao
     */
    public void setDataEnvioPublicacao(final Date dataEnvioPublicacao){
        if(dataEnvioPublicacao == null){
            this.dataEnvioPublicacao = null;
        }
        else {
            this.dataEnvioPublicacao = new Date(dataEnvioPublicacao.getTime());
        }
    }

    /**
     * Get da dataEnvioPublicacao
     * @return dataEnvioPublicacao
     */
    public Date getDataEnvioPublicacao(){
        if(this.dataEnvioPublicacao == null){
            return null;
        }
        return new Date(this.dataEnvioPublicacao.getTime());
    }


    /**
     * Recupera os depacho Tecnico
     * @return
     */
    public List<Despacho> getDespachosTecnico() {

        if (this.despachosTecnico == null) {
            this.despachosTecnico = new ArrayList<>();
        }

        this.despachosAnaliseTecnico.forEach(despachoAnalise -> {
            Despacho despacho = despachoAnalise.getAnaliseTecnicaDespachoID().getDespacho();
            despacho.setJustificativa(despachoAnalise.getJustificativa());
            this.despachosTecnico.add(despachoAnalise.getAnaliseTecnicaDespachoID().getDespacho());
        });

        return this.despachosTecnico;
    }

    /**
     * recupera o despacho da chefia
     * @return
     */
    public List<Despacho> getDespachosChefia() {
        if (this.despachosChefia == null) {
            this.despachosChefia = new ArrayList<>();
        }

        this.despachosAnaliseChefia.forEach(despachoAnalise -> {
            Despacho despacho = despachoAnalise.getAnaliseTecnicaDespachoID().getDespacho();
            despacho.setJustificativa(despachoAnalise.getJustificativa());
            this.despachosChefia.add(despachoAnalise.getAnaliseTecnicaDespachoID().getDespacho());
        });

        return this.despachosChefia;
    }

    /**
     * recupera o despacho do superintendente
     * @return
     */
    public List<Despacho> getDespachosSuperintendente() {

        if (this.despachosSuperintendente == null) {

            this.despachosSuperintendente = new ArrayList<>();
        }

        this.despachosAnaliseSuperintendente.forEach(despachoAnalise -> {
            Despacho despacho = despachoAnalise.getAnaliseTecnicaDespachoID().getDespacho();
            despacho.setJustificativa(despachoAnalise.getJustificativa());
            this.despachosSuperintendente.add(despachoAnalise.getAnaliseTecnicaDespachoID().getDespacho());
        });

        return this.despachosSuperintendente;
    }

    /**
     * recupera o despacho do secretario
     * @return
     */
    public List<Despacho> getDespachosSecretario() {
        if (this.despachosSecretario == null) {
            this.despachosSecretario = new ArrayList<>();
        }

        this.despachosAnaliseSecretario.forEach(despachoAnalise -> {
            Despacho despacho = despachoAnalise.getAnaliseTecnicaDespachoID().getDespacho();
            despacho.setJustificativa(despachoAnalise.getJustificativa());
            this.despachosSecretario.add(despachoAnalise.getAnaliseTecnicaDespachoID().getDespacho());
        });

        return this.despachosSecretario;
    }

}
