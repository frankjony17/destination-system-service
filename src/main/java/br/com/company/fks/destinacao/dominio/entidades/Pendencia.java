package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Basis Tecnologia on 04/10/2016.
 */
@Getter @Setter
@Entity
@Table(name = "tb_pendencia", schema = "destinacao")
public class Pendencia  implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_pendencia")
    private Long id;

    @Column(name="ds_pendencia")
    private String descricao;

    @Column(name = "ds_modulo")
    private String modulo;

    @ManyToMany
    @JoinTable(name = "tb_permissao_pendencia", schema = Constants.SCHEMA, joinColumns = {
            @JoinColumn(name = "id_pendencia")}, inverseJoinColumns = {
            @JoinColumn(name = "id_permissao")})
    private Set<Permissao> permissoes;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pendencia)) {
            return false;
        }

        Pendencia pendencia = (Pendencia) o;

        if (!id.equals(pendencia.id)) {
            return false;
        }
        return modulo.equals(pendencia.modulo);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = Constants.TREZE * result + modulo.hashCode();
        return result;
    }
}
