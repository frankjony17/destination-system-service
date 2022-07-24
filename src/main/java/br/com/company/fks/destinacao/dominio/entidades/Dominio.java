package br.com.company.fks.destinacao.dominio.entidades;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */

import br.com.company.fks.destinacao.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Classe de mapeamento Domínio
 */
@MappedSuperclass
@Getter
@Setter
public abstract class Dominio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dominio)) {
            return false;
        }

        Dominio dominio = (Dominio) o;

        if (!id.equals(dominio.id)) {
            return false;
        }
        return descricao.equals(dominio.descricao);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = Constants.TREZE * result + descricao.hashCode();
        return result;
    }
}
