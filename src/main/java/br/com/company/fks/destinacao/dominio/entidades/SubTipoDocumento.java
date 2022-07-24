package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by sdias on 3/28/2017.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_sub_tipo_documento", schema = "destinacao")
public class SubTipoDocumento implements Serializable{

    @Id
    @Column(name = "id_sub_tipo_documento")
    private Long id;

    @Column(name = "ds_sub_tipo_documento")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "co_tipo_documento")
    private TipoDocumento tipoDocumento;
}
