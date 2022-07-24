package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by diego on 17/01/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_status_destinacao", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_status_destinacao")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_status_destinacao"))})
public class StatusDestinacao extends Dominio implements Serializable{
}
