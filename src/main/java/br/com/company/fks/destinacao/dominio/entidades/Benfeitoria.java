package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by diego on 31/01/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_benfeitoria", schema = "destinacao")
@Audited
@AuditTable(value = "tb_benfeitoria_aud", schema = "aud_destinacao")
public class Benfeitoria implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_benfeitoria_seq", name = "tb_benfeitoria_seq")
    @GeneratedValue(generator = "tb_benfeitoria_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_benfeitoria")
    private Long id;

    @Column(name = "id_benfeitoria_cad_imovel")
    private Long idBenfeitoriaCadImovel;

    @Column(name = "ds_codigo_identificador")
    private String codigo;

    @Column(name = "ic_ativa")
    private Boolean ativa;

    @Column(name = "nu_area_construida")
    private BigDecimal areaConstruida;

    @Audited
    @ManyToOne
    @JoinColumn(name = "co_parcela")
    @JsonIgnore
    private Parcela parcela;

    @Audited
    @ManyToOne
    @JoinColumn(name = "co_imovel")
    @JsonIgnore
    private Imovel imovel;

    @Column(name = "nu_area_disponivel")
    private BigDecimal areaDisponivel;

    @Column(name = "ds_nome_benfeitoria")
    private String nome;

    @Column(name = "ds_especializacao")
    private String especializacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Benfeitoria)) {
            return false;
        }

        Benfeitoria that = (Benfeitoria) o;

        if (!id.equals(that.id)) {
            return false;
        }
        if (!idBenfeitoriaCadImovel.equals(that.idBenfeitoriaCadImovel)) {
            return false;
        }
        return areaConstruida.equals(that.areaConstruida);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = Constants.TREZE * result + idBenfeitoriaCadImovel.hashCode();
        result = Constants.TREZE * result + areaConstruida.hashCode();
        return result;
    }
}
