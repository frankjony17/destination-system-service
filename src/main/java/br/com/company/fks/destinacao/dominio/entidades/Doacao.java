package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 26/07/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_doacao", schema = "destinacao")
@DiscriminatorValue("DOACAO")
@PrimaryKeyJoinColumn(name="id_doacao")
@Audited
@AuditTable(value = "tb_doacao_aud", schema = "aud_destinacao")
public class Doacao extends Destinacao implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name="ic_existe_encargo")
    private Boolean existeEncargo;

    @NotAudited
    @OneToOne
    @JoinColumn(name="co_tipo_instrumento")
    private TipoInstrumento tipoInstrumento;

}
