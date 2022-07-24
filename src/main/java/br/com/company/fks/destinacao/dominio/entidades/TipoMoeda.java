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
@Table(name = "tb_tipo_moeda", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_tipo_moeda")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_tipo_moeda"))})
public class TipoMoeda extends Dominio implements Serializable{

    /**
     * Construtor
     */
    public TipoMoeda() {
    }

    /**
     * Construtor
     */
    public TipoMoeda(int id) {
        this();
        setId(id);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
