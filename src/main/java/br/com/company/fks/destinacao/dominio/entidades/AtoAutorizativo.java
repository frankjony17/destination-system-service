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
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_ato_autorizativo", schema = "destinacao")
@Audited
@AuditTable(value = "tb_ato_autorizativo_aud", schema = "aud_destinacao")
public class AtoAutorizativo implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_ato_autorizativo_seq", name = "tb_ato_autorizativo_seq")
    @GeneratedValue(generator = "tb_ato_autorizativo_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_ato_autorizativo")
    private Long id;

    @Column(name = "nu_ato")
    private Long numeroAto;

    @NotAudited
    @JoinColumn(name = "co_tp_ato_autorizativo")
    @OneToOne
    private TipoAtoAutorizativo tipoAtoAutorizativo;

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
     * Get da dataPublicacao sobrescrito
     * @return dataPublicacao
     */
    public Date getDataPublicacao(){
        if(this.dataPublicacao == null){
            return null;
        }
        return new Date(this.dataPublicacao.getTime());
    }

}
