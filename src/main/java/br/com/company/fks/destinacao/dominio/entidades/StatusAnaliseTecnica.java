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
 * Created by diego on 06/12/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_status_analise_tecnica", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_status_analise_tecnica")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_descricao"))})
public class StatusAnaliseTecnica extends Dominio implements Serializable {
}
