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
 * Created by gustavodias on 13/01/17.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_base_legal", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_base_legal")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_base_legal"))})
public class BaseLegal extends Dominio implements Serializable{

    /**
     * Construtor
     */
    public BaseLegal(){
    }

    /**
     * Construtor
     */
    public BaseLegal(int id){
        this();
        setId(id);
    }


}
