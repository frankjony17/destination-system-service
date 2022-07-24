package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 07/10/2016.
 */
@Getter @Setter
@Entity
@Table(name = "tb_tipo_destinacao", schema = "destinacao")
public class TipoDestinacao implements Serializable {

    @Id
    @Column(name = "id_tipo_destinacao")
    private Integer id;

    @Column(name = "ds_tipo_destinacao")
    private String descricao;
}
