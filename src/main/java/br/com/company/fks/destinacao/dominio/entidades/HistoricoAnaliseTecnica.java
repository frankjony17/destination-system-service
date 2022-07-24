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
import java.util.Date;

/**
 * Created by raphael on 29/11/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_historico_analise_tecnica", schema = "destinacao")
@Audited
@AuditTable(value = "tb_historico_analise_tecnica_aud", schema = "aud_destinacao")
public class HistoricoAnaliseTecnica implements Serializable {

    @Id
    @Column(name = "id_historico_analise_tecnica")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_historico_analise_tecnica_seq", name = "tb_historico_analise_tecnica_seq")
    @GeneratedValue(generator = "tb_historico_analise_tecnica_seq", strategy = GenerationType.AUTO)
    private Long id;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_analise_tecnica")
    private AnaliseTecnica analiseTecnica;

    @Column(name = "dt_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_status_anaise_tecnica_anterior")
    private StatusAnaliseTecnica statusAnaliseTecnicaAnterior;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "co_status_analise_tecnica_atual")
    private StatusAnaliseTecnica statusAnaliseTecnicaAtual;

    @Column(name = "ds_justificativa")
    private String justificativa;

    @Column(name = "co_usuario")
    private Long idUsuario;

    @Column(name = "ds_nome_usuario")
    private String nomeUsuario;

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataAlteracao
     */
    public void setDataAlteracao(final Date dataAlteracao){
        if(dataAlteracao == null){
            this.dataAlteracao = null;
        }
        else{
            this.dataAlteracao = new Date(dataAlteracao.getTime());
        }
    }

    /**
     * Get da dataAlteracao sobrescrito
     * @return dataAlteracao
     */
    public Date getDataAlteracao(){
        if(this.dataAlteracao == null){
            return null;
        }
        return new Date(this.dataAlteracao.getTime());
    }
}
