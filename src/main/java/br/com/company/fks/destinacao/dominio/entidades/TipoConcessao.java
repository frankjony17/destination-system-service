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
 * Created by Basis Tecnologia on 07/11/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_tipo_concessao", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_tipo_concessao")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_tipo_concessao"))})
public class TipoConcessao extends Dominio implements Serializable{
}
