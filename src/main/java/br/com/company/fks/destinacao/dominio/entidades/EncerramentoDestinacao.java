package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.DespachoEncerrarDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoEncerrarDestinacaoEnum;
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

@Getter
@Setter
@Entity
@Table(name = "tb_encerramento_destinacao", schema = "destinacao")
@PrimaryKeyJoinColumn(name="id_encerramento_destinacao")
@Audited
@AuditTable(value = "tb_encerramento_destinacao_aud", schema = "aud_destinacao")
public class EncerramentoDestinacao implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_encerramento_destinacao_seq", name = "tb_encerramento_destinacao_seq")
    @GeneratedValue(generator = "tb_encerramento_destinacao_seq", strategy = GenerationType.AUTO)
    @Column(name="id_encerramento_destinacao")
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
    @Column(name="dt_encerramento_destinacao")
    private Date dataEncerramentoDestinacao;

    @Enumerated(EnumType.STRING)
    @Column(name="ds_motivo_encerramento_destinacao")
    private MotivoEncerrarDestinacaoEnum motivo;

    @Column(name = "ds_observacao_motivo")
    private String observacaoMotivo;

    @Audited
    @AuditJoinTable(name = "tb_encerramento_destinacao_arquivo_aud", schema = "aud_destinacao")
    @ManyToMany
    @JoinTable(name = "tb_encerramento_destinacao_arquivo", schema = "destinacao", joinColumns = {
            @JoinColumn(name = "co_encerramento_destinacao")}, inverseJoinColumns = {
            @JoinColumn(name = "co_arquivo")})
    private List<Arquivo> arquivos;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_despacho_encerramento_destinacao")
    private DespachoEncerrarDestinacaoEnum despacho;

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
     * @param dataEncerramentoDestinacao
     */
    public void setDataEncerramentoDestinacao(final Date dataEncerramentoDestinacao){
        if(dataEncerramentoDestinacao == null){
            this.dataEncerramentoDestinacao = null;
        }
        else{
            this.dataEncerramentoDestinacao = new Date(dataEncerramentoDestinacao.getTime());
        }
    }

    /**
     * Get da dataCancelamentoEncerramento sobrescrito
     * @return dataCancelamentoEncerramento
     */

    public Date getDataEncerramentoDestinacao(){
        if(this.dataEncerramentoDestinacao == null){
            return null;
        }
        return new Date(this.dataEncerramentoDestinacao.getTime());
    }


}
