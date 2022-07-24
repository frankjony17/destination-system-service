package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.CascadeType;
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

/**
 * Created by haillanderson on 31/08/2017.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_cancelamento", schema = "destinacao")
@PrimaryKeyJoinColumn(name="id_cancelamento")
@Audited
@AuditTable(value = "tb_cancelamento_aud", schema = "aud_destinacao")
public class Cancelamento implements Serializable{
    
    @Id
    @Column(name = "id_cancelamento")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_cancelamento_seq", name = "tb_cancelamento_seq")
    @GeneratedValue(generator = "tb_cancelamento_seq", strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column(name="dt_cancelamento")
    private Date dataCancelamento;
    
    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_motivo_cancelamento")
    private MotivoCancelamento motivoCancelamento;
    
    @Column(name = "ds_observacao")
    private String observacao;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_cancelamento_arquivo", schema = Constants.SCHEMA, joinColumns = {
            @JoinColumn(name = "id_cancelamento")}, inverseJoinColumns = {
            @JoinColumn(name = "id_arquivo")})
    private List<Arquivo> arquivos;


    /**
     * Informa a data do cancelamento
     * @param dataCancelamento
     */
    public void setDataCancelamento(final Date dataCancelamento){
        if (dataCancelamento == null){
            this.dataCancelamento= null;
        }else{
            this.dataCancelamento = new Date(dataCancelamento.getTime());
        }
    }

    /**
     *
     * @return dataCancelamento
     */
    public Date getDataCancelamento() {
        if (this.dataCancelamento == null) {
            return null;
        }
        return new Date(this.dataCancelamento.getTime());
    }
}
