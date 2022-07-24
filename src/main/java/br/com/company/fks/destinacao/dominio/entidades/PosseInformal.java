package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Basis Tecnologia on 25/10/2016.
 */

@Getter
@Setter
@Entity
@DiscriminatorValue("POSSE_INFORMAL")
@PrimaryKeyJoinColumn(name="id_posse_informal")
@Table(name = "tb_posse_informal", schema = "destinacao")
@Audited
@AuditTable(value = "tb_posse_informal_aud", schema = "aud_destinacao")
public class PosseInformal extends Destinacao implements Serializable{

    @NotAudited
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "co_imovel")
    private Imovel imovel;

    @Transient
    private List<Destinacao> destinacoes;

    @OneToMany(mappedBy = "posseInformal")
    private List<Ocupante> ocupantes;

    @Column(name="ds_nome_entidade")
    private String nomeEntidade;

    @Column(name="ds_cnpj")
    private String cnpj;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PosseInformal)) {
            return false;
        }

        PosseInformal that = (PosseInformal) o;

        return getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
