package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 07/11/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_cdru", schema = "destinacao")
@DiscriminatorValue("CDRU")
@PrimaryKeyJoinColumn(name="id_cdru")
@Audited
@AuditTable(value = "tb_cdru_aud", schema = "aud_destinacao")
public class Cdru extends Destinacao implements Serializable{

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_tipo_concessao")
    private TipoConcessao tipoConcessao;

}
