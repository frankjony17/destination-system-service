package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by diego on 01/03/17.
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("SEM_UTILIZACAO")
@PrimaryKeyJoinColumn(name="id_sem_utilizacao")
@Table(name = "tb_sem_utilizacao", schema = "destinacao")
@Audited
@AuditTable(value = "tb_sem_utilizacao_aud", schema = "aud_destinacao")
public class SemUtilizacao extends Destinacao implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SemUtilizacao)) {
            return false;
        }

        SemUtilizacao semUtilizacao = (SemUtilizacao) o;

        return getId().equals(semUtilizacao.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
