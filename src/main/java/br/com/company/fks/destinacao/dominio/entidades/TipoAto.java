package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by rogerio on 09/06/18.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_tipo_ato", schema = "destinacao")

public class TipoAto implements Serializable {

    @Id
    @Column(name = "id_tipo_ato")
    private Long id;

    @Column(name = "ds_tipo_ato")
    private String descricao;

}

