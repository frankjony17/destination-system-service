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
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 22/07/2016.
 */
@Getter @Setter
@Entity
@Table(name = "tb_encargo", schema = "destinacao")
@Audited
@AuditTable(value = "tb_encargo_aud", schema = "aud_destinacao")
public class Encargo implements Serializable{

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_encargo_seq", name = "tb_encargo_seq")
    @GeneratedValue(generator = "tb_encargo_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_encargo")
    private Long id;

    @Column(name = "ds_nome")
    private String nome;

    @Column(name = "dt_cumprimento")
    private Date dataCumprimento;

    @Column(name = "ic_cumprimento_encargo")
    private Boolean cumprimentoEncargo;

    @Column(name = "ic_utilizar_data_contrato")
    private Boolean utilizarData;


    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataCumprimento
     */
    public void setDataCumprimento(final Date dataCumprimento){
        if(dataCumprimento == null){
            this.dataCumprimento = null;
        }
        else{
            this.dataCumprimento = new Date(dataCumprimento.getTime());
        }
    }

    /**
     * Get da dataCumprimento sobrescrito
     * @return dataCumprimento
     */
    public Date getDataCumprimento(){
        if(this.dataCumprimento == null){
            return null;
        }
        return new Date(this.dataCumprimento.getTime());
    }

}
