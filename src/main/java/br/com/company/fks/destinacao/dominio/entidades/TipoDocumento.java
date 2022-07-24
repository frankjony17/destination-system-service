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
 * Created by sdias on 3/28/2017.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_tipo_documento", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_tipo_documento")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_tipo_documento"))})
public class TipoDocumento extends Dominio implements Serializable{
}
