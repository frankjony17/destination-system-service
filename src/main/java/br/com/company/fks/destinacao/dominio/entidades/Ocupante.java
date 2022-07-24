package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by haillanderson on 10/07/17.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_ocupante", schema = "destinacao")
@Audited
@AuditTable(value = "tb_ocupante_aud", schema = "aud_destinacao")
public class Ocupante implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_ocupante_seq", name = "tb_ocupante_seq")
    @GeneratedValue(generator = "tb_ocupante_seq", strategy = GenerationType.AUTO)
    @Column(name="id_ocupante")
    private Long id;

    @Column(name="ds_cpf_cnpj")
    private String cpfCnpj;

    @ManyToOne
    @JoinColumn(name="co_posse_informal")
    @JsonIgnore
    private PosseInformal posseInformal;

    @Column(name="ds_nome_razao_social")
    private String nomeRazaoSocial;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_endereco")
    private Endereco endereco;

    @Column(name = "ds_ug")
    private String ug;

    @Column(name = "nu_area_utilizada")
    private BigDecimal areaUtilizada;

    @Column(name = "ds_estado_civil")
    private String estadoCivil;

    @Column(name = "ds_cpf_conjuge")
    private String cpfConjuge;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ocupante)) {
            return false;
        }

        Ocupante that = (Ocupante) o;

        if (!id.equals(that.id)) {
            return false;
        }
        return cpfCnpj.equals(that.cpfCnpj);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = Constants.TREZE * result + cpfCnpj.hashCode();
        return result;
    }
}
