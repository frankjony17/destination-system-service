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
 * Created by Basis Tecnologia on 20/10/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_venda", schema = "destinacao")
@DiscriminatorValue("VENDA")
@PrimaryKeyJoinColumn(name="id_venda")
@Audited
@AuditTable(value = "tb_venda_aud", schema = "aud_destinacao")
public class Venda extends Destinacao implements Serializable{

    private static final long serialVersionUID = 1L;

}
