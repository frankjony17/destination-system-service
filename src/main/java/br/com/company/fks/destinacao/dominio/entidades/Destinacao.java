package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Basis Tecnologia on 26/07/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_destinacao", schema = "destinacao")
@DiscriminatorColumn(name="ds_instrumento", discriminatorType= DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@AuditTable(value = "tb_destinacao_aud", schema = "aud_destinacao")
@Audited
public class Destinacao implements Serializable {

    private static final String TABELA = "destinacaoPendenciaID.destinacao";
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_destinacao_seq", name = "tb_destinacao_seq")
    @GeneratedValue(generator = "tb_destinacao_seq", strategy = GenerationType.AUTO)
    @Column(name="id_destinacao")
    private Long id;

    @Column(name="nu_atendimento")
    private String numeroAtendimento;

    @Column(name="nu_processo")
    private String numeroProcesso;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_final_fundamento")
    private Date dataFinalFundamento;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_inicio_fundamento")
    private Date dataInicioFundamento;

    @ManyToOne
    @JoinColumn(name="co_utilizacao")
    private Utilizacao utilizacao;

    @Column(name="co_fundamento_legal")
    private Long codFundamentoLegal;

    @NotAudited
    @JsonIgnore
    @OneToMany(mappedBy = TABELA, cascade = CascadeType.ALL)
    private Set<DestinacaoPendencia> pendencias;

    @NotAudited
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_destinacao_encargo", schema = Constants.SCHEMA, joinColumns = {
            @JoinColumn(name = "id_destinacao")}, inverseJoinColumns = {
            @JoinColumn(name = "id_encargo")})
    private List<Encargo> encargos;

    @NotAudited
    @OneToMany(mappedBy = Constants.SCHEMA)
    private List<DestinacaoImovel> destinacaoImoveis;

    @OneToOne
    @JoinColumn(name = "co_dados_responsavel")
    private DadosResponsavel dadosResponsavel;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_tipo_destinacao")
    private TipoDestinacao tipoDestinacao;

    @Transient
    private TipoDestinacaoEnum tipoDestinacaoEnum;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "destinacao")
    private List<Documento> documentos;

    @NotAudited
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_licitacao")
    private Licitacao licitacao;

    @NotAudited
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_financeiro")
    private Financeiro financeiro;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_status_destinacao")
    private StatusDestinacao statusDestinacao;


    @OneToOne
    @JoinColumn(name="co_contrato")
    private Contrato contrato;

    @NotAudited
    @OneToMany
    @JoinColumn(name="co_destinacao")
    private List<Foto> fotos;

    @NotAudited
    @OneToMany
    @JoinColumn(name="co_destinacao")
    private List<DocumentoArquivo> documentosArquivo;

    @OneToMany(mappedBy = "destinacao")
    private List<CancelamentoEncerramento> cancelamentosEncerramentos;

    @JoinColumn(name = "co_ato_autorizativo")
    @OneToOne
    private AtoAutorizativo atoAutorizativo;

    @Column(name = "ic_ativa")
    private Boolean ativa;

    @Column(name = "co_versao_destinacao")
    private Long versaoDestinacao;

    @Transient
    private Boolean ativarCopia;

    @Transient
    private String dataHistoricoFormatada;

    @Column(name = "dt_destinacao_historico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDestinacaoHistorico;

    public Date getDataInicioFundamento(){
        if(this.dataInicioFundamento == null){
            return null;
        }
        return new Date(this.dataInicioFundamento.getTime());
    }

    public void setDataInicioFundamento(final Date dataInicioFundamento){
        if(dataInicioFundamento == null){
            this.dataInicioFundamento = null;
        }
        else{
            this.dataInicioFundamento = new Date(dataInicioFundamento.getTime());
        }
    }


    public Date getDataFinalFundamento(){
        if(this.dataFinalFundamento == null){
            return null;
        }
        return new Date(this.dataFinalFundamento.getTime());
    }

    public void setDataFinalFundamento(final Date dataFinalFundamento){
        if(dataFinalFundamento == null){
            this.dataFinalFundamento = null;
        }
        else{
            this.dataFinalFundamento = new Date(dataFinalFundamento.getTime());
        }
    }


    public Date getDataDestinacaoHistorico(){
        if(this.dataDestinacaoHistorico == null){
            return null;
        }
        return new Date(this.dataDestinacaoHistorico.getTime());
    }

    public void setDataDestinacaoHistorico(final Date dataDestinacaoHistorico){
        if(dataDestinacaoHistorico == null){
            this.dataDestinacaoHistorico = null;
        }
        else{
            this.dataDestinacaoHistorico = new Date(dataDestinacaoHistorico.getTime());
        }
    }


}
