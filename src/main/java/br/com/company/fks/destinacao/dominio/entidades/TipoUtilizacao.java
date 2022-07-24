package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by haillanderson on 07/04/17.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_tipo_utilizacao", schema = "destinacao")
public class TipoUtilizacao implements Serializable{

    @Id
    @Column(name = "id_tipo_utilizacao")
    private Integer id;

    @Column(name = "num_tipo_utilizacao")
    private Long numeroTipoUtilizacao;

    @Column(name = "ds_tipo_utilizacao")
    private String descricao;

    @Column(name = "ic_ativo")
    private Boolean ativo;
}
