package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by haillanderson on 07/04/17.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_sub_tipo_utilizacao", schema = "destinacao")
public class SubTipoUtilizacao implements Serializable{

    @Id
    @Column(name = "id_sub_tipo_utilizacao")
    private Integer id;

    @Column(name = "ds_sub_tipo_utilizacao")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "co_tipo_utilizacao")
    private TipoUtilizacao tipoUtilizacao;

    @Transient
    private Boolean usoPrivado;

    @Column(name = "ic_ativo")
    private Boolean ativo;


}
