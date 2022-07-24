package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.DespachoCancelarEncerrarEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoCancelarEncerrarEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tawan-souza on 14/12/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_cancelamento_encerramento", schema = "destinacao")
@PrimaryKeyJoinColumn(name="id_cancelamento_encerramento")
@Audited
@AuditTable(value = "tb_cancelamento_encerramento_aud", schema = "aud_destinacao")
public class CancelamentoEncerramento implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_cancelamento_encerramento_seq", name = "tb_cancelamento_encerramento_seq")
    @GeneratedValue(generator = "tb_cancelamento_encerramento_seq", strategy = GenerationType.AUTO)
    @Column(name="id_cancelamento_encerramento")
    private Long id;

    @Column(name = "ds_nome_responsavel_tecnico")
    private String nomeResponsavelTecnico;

    @Column(name = "ds_cpf_responsavel_tecnico")
    private String cpfResponsavelTecnico;

    @Column(name = "ds_nome_superintendente")
    private String nomeSuperintendente;

    @Column(name = "ds_cpf_superintendente")
    private String cpfSuperIntendente;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_cancelamento_encerramento")
    private Date dataCancelamentoEncerramento;

    @Enumerated(EnumType.STRING)
    @Column(name="ds_motivo_cancelamento_encerramento")
    private MotivoCancelarEncerrarEnum motivo;

    @Column(name = "ds_observacao_motivo")
    private String observacaoMotivo;

    @Audited
    @AuditJoinTable(name = "tb_cancelamento_encerramento_arquivo_aud", schema = "aud_destinacao")
    @ManyToMany
    @JoinTable(name = "tb_cancelamento_encerramento_arquivo", schema = "destinacao", joinColumns = {
            @JoinColumn(name = "co_cancelamento_encerramento")}, inverseJoinColumns = {
            @JoinColumn(name = "co_arquivo")})
    private List<Arquivo> arquivos;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_despacho_cancelamento_encerramento")
    private DespachoCancelarEncerrarEnum despacho;

    @Column(name = "ds_observacao_despacho")
    private String observacaoDespacho;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "co_destinacao")
    private Destinacao destinacao;

    @Column(name = "ic_ativo")
    private Boolean isAtivo;

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataCancelamentoEncerramento
     */
    public void setDataCancelamentoEncerramento(final Date dataCancelamentoEncerramento){
        if(dataCancelamentoEncerramento == null){
            this.dataCancelamentoEncerramento = null;
        }
        else{
            this.dataCancelamentoEncerramento = new Date(dataCancelamentoEncerramento.getTime());
        }
    }

    /**
     * Get da dataCancelamentoEncerramento sobrescrito
     * @return dataCancelamentoEncerramento
     */

    public Date getDataCancelamentoEncerramento(){
        if(this.dataCancelamentoEncerramento == null){
            return null;
        }
        return new Date(this.dataCancelamentoEncerramento.getTime());
    }



}
