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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 04/10/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_contrato", schema = "destinacao")
@Audited
@AuditTable(value = "tb_contrato_aud", schema = "aud_destinacao")
public class Contrato implements Serializable {

    @Id
    @Column(name = "id_contrato")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_contrato_seq", name = "tb_contrato_seq")
    @GeneratedValue(generator = "tb_contrato_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_inicio")
    private Date dataInicio;

    @Column(name = "nu_contrato")
    private String numero;

    @Audited
    @OneToOne
    @JoinColumn(name = "co_arquivo")
    private Arquivo arquivo;

    @Column(name = "dt_final")
    private Date dataFinal;

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataFinal
     */
    public void setDataFinal(final Date dataFinal){
        if(dataFinal == null){
            this.dataFinal = null;
        }
        else{
            this.dataFinal = new Date(dataFinal.getTime());
        }
    }

    /**
     * Get da dataFinal sobrescrito
     * @return dataFinal
     */
    public Date getDataFinal(){
        if(this.dataFinal == null){
            return null;
        }
        return new Date(this.dataFinal.getTime());
    }

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataInicio
     */
    public void setDataInicio(final Date dataInicio){
        if(dataInicio == null){
            this.dataInicio = null;
        }
        else{
            this.dataInicio = new Date(dataInicio.getTime());
        }
    }

    /**
     * Get da dataInicio sobrescrito
     * @return dataInicio
     */
    public Date getDataInicio(){
        if(this.dataInicio == null){
            return null;
        }
        return new Date(this.dataInicio.getTime());
    }

}
