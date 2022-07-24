package br.com.company.fks.destinacao.dominio.entidades.auditoria;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guilherme.lima on 27/01/17.
 */
@Entity
@RevisionEntity(AuditingRevisionListener.class)
@Table(name = "tb_revision_aud", schema = "aud_destinacao")
public class Revision implements Serializable {

    @Id
    @GeneratedValue(generator = "aud_destinacao.tb_revision_aud_seq",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "aud_destinacao.tb_revision_aud_seq", sequenceName = "aud_destinacao.tb_revision_aud_seq", schema = "aud_destinacao")
    @RevisionNumber
    private int id;

    @Column(name = "nu_cpf")
    private String cpf;

    @RevisionTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_operacao")
    private Date dataOperacao;

    /**
     * Método construtor da classe
     */
    public Revision() {
        //construtor
    }

    /**
     * Método get para o atributo id
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Método set para o atributo id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método get para o atributo cpf
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Método set para o atributo cpf
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Método get para o atributo dataOperacao
     * @return
     */
    public Date getDataOperacao() {
        if(this.dataOperacao == null){
            return null;
        }
        return new Date(this.dataOperacao.getTime());
    }

    /**
     * Método set para o atributo dataOperacao
     * @param dataOperacao
     */
    public void setDataOperacao(Date dataOperacao) {
        if(dataOperacao == null){
            this.dataOperacao = null;
        }
        else{
            this.dataOperacao = new Date(dataOperacao.getTime());
        }
    }


}
