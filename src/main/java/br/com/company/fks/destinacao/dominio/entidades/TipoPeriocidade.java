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
 * Created by Basis Tecnologia on 20/10/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_tipo_periocidade", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_tipo_periocidade")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_tipo_periocidade"))})
public class TipoPeriocidade extends Dominio implements Serializable{

    /**
     * Construtor
     */
    public TipoPeriocidade() {
    }

    /**
     * Construtor
     */
    public TipoPeriocidade(int id) {
        this();
        setId(id);
    }

}
