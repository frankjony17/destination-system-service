package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by raphael on 29/11/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_despacho", schema = "destinacao")
public class Despacho implements Serializable {

    @Id
    @Column(name = "id_despacho")
    private Long id;

    @Column(name = "ds_descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_despacho")
    private TipoDespachoEnum tipoDespacho;

    @Transient
    private String justificativa;

}
