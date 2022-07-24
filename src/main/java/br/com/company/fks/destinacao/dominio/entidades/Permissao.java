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
 * Created by diego on 14/03/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_permissao", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_permissao")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_permissao"))})
public class Permissao extends Dominio implements Serializable {
}
