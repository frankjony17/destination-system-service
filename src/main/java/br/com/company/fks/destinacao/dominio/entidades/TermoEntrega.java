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
 * Created by basis on 17/01/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_termo_entrega", schema = "destinacao")
@DiscriminatorValue("TERMO_ENTREGA")
@PrimaryKeyJoinColumn(name="id_termo_entrega")
@Audited
@AuditTable(value = "tb_termo_entrega_aud", schema = "aud_destinacao")
public class TermoEntrega extends Destinacao implements Serializable{

    private static final long serialVersionUID = 1L;



}
