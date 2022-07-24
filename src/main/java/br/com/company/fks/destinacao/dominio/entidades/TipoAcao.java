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
@Table(name = "tb_tipo_acao", schema = "destinacao")
public class TipoAcao implements Serializable {

    @Id
    @Column(name = "id_tipo_acao")
    private Long id;

    @Column(name = "ds_tipo_acao")
    private String descricao;

}

