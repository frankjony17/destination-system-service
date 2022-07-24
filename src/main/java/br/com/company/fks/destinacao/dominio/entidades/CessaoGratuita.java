package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 07/11/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_cessao_gratuita", schema = "destinacao")
@DiscriminatorValue("CESSAO_GRATUITA")
@PrimaryKeyJoinColumn(name="id_cessao_gratuita")
@Audited
@AuditTable(value = "tb_cessao_gratuita_aud", schema = "aud_destinacao")
public class CessaoGratuita extends Destinacao implements Serializable{

}
