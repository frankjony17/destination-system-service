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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created by diego on 01/03/17.
 */
@Getter @Setter
@Entity
@Table(name = "tb_parcela", schema = "destinacao")
@Audited
@AuditTable(value = "tb_parcela_aud", schema = "aud_destinacao")
public class Parcela implements Serializable {

    private static final String SCHEMA = "destinacao";

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_parcela_seq", name = "tb_parcela_seq")
    @GeneratedValue(generator = "tb_parcela_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_parcela")
    private Long id;

    @Column(name = "ds_sequencial")
    private String sequencial;

    @OneToMany(mappedBy = "parcela", cascade = CascadeType.ALL)
    private List<Benfeitoria> benfeitorias;

    @Transient
    private List<Benfeitoria> listaBenfeitorias;

    @Column(name = "nu_area_terreno")
    private BigDecimal areaTerreno;

    @Column(name = "nu_area_diponivel")
    private BigDecimal areaDisponivel;

    @Column(name = "ic_ativa")
    private Boolean ativa;

    @Column(name = "ds_memorial_descritivo")
    private String memorialDescritivo;

    @ManyToOne
    @JoinColumn(name = "co_imovel")
    @JsonIgnore
    private Imovel imovel;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_destinacao_imovel_parcela", schema = Constants.SCHEMA, joinColumns = {
            @JoinColumn(name = "id_parcela")}, inverseJoinColumns = {
            @JoinColumn(name = "id_destinacao_imovel")})
    private List<DestinacaoImovel> destinacaoImoveis;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_parcela_arquivo", schema = SCHEMA, joinColumns = {
            @JoinColumn(name = "id_parcela")}, inverseJoinColumns = {
            @JoinColumn(name = "id_arquivo")})
    private List<Arquivo> arquivos;

    @Transient
    private Set<Long> idDestinacaoImoveis;

    @Transient
    private Boolean utilizada;

    @Transient
    private Boolean parcelacomPendenciaDestinacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parcela)) {
            return false;
        }

        Parcela parcela = (Parcela) o;

        if (!id.equals(parcela.id)) {
            return false;
        }
        return sequencial.equals(parcela.sequencial);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = Constants.TREZE * result + sequencial.hashCode();
        return result;
    }

    public String getDescParcela() {
        StringBuilder result = new StringBuilder();

        result.append(this.getImovel().getRip());
        result.append("/");
        if (this.getDestinacaoImoveis().isEmpty()) {
            result.append("0000");
        } else {
            result.append(this.getDestinacaoImoveis().get(0).getCodigoUtilizacao());
        }
        result.append(this.getSequencial());
        return result.toString();
    }

    public String getUtilizacao() {
        if (this.getDestinacaoImoveis() == null || this.getDestinacaoImoveis().isEmpty()) {
            return "Sem utilização/Vago";
        } else {
            String descricao = "";

            for(DestinacaoImovel destinacaoImovel : this.getDestinacaoImoveis()){
                if(destinacaoImovel.getUltimaDestinacao()&& destinacaoImovel.getDestinacao().getUtilizacao() != null) {
                    descricao = destinacaoImovel.getDestinacao().getUtilizacao().getTipoUtilizacao().getDescricao();
                }
            }
            return descricao;
        }
    }

}
