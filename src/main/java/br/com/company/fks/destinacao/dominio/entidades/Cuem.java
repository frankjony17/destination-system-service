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
@Table(name = "tb_cuem", schema = "destinacao")
@DiscriminatorValue("CUEM")
@PrimaryKeyJoinColumn(name="id_cuem")
@Audited
@AuditTable(value = "tb_cuem_aud", schema = "aud_destinacao")
public class Cuem extends Destinacao implements Serializable{

    @NotAudited
    @OneToOne
    @JoinColumn(name = "co_tipo_modalidade")
    private TipoModalidade tipoModalidade;

}
