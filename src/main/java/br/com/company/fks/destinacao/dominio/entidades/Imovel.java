package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.TipoImovelEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoOperacaoEnum;
import br.com.company.fks.destinacao.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */
@Getter @Setter
@Entity
@Table(name = "tb_imovel", schema = "destinacao")
@Audited
@AuditTable(value = "tb_imovel_aud", schema = "aud_destinacao")
public class Imovel implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_imovel_seq", name = "tb_imovel_seq")
    @GeneratedValue(generator = "tb_imovel_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_imovel")
    private Long id;

    @Column(name = "nu_rip")
    private String rip;

    @Column(name = "nu_area_terreno_antigo")
    private BigDecimal areaTerrenoAntigo;

    @Column(name = "nu_area_terreno")
    private BigDecimal areaTerreno;

    @Column(name = "nu_area_construida_antigo")
    private BigDecimal areaConstruidaAntigo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_endereco")
    private Endereco endereco;

    @Column(name = "id_cadastro_imovel")
    private Long idCadastroImovel;

    @OneToMany(mappedBy = "imovel", fetch = FetchType.LAZY)
    private List<Benfeitoria> benfeitorias;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "co_unidade_autonoma")
    private UnidadeAutonoma unidadeAutonoma;

    @Transient
    private TipoOperacaoEnum indicadorOperacao;

    @Column(name = "ds_indicador_unidade_benfeitoria")
    @Enumerated(EnumType.STRING)
    private TipoImovelEnum indicadorUnidadeBenfeitoria;

    @OneToMany(mappedBy = "imovel")
    @JsonManagedReference
    private List<Parcela> parcelas;

    @OneToMany(mappedBy = "imovel")
    private List<DestinacaoImovel> destinacoes;

    @Column(name = "co_tipo_imovel")
    private Long codigoTipoImovel;

    @Column(name = "co_situacao_incorporacao")
    private Long codigoSituacaoIncorporacao;

    @Column(name = "co_natureza_imovel")
    private Long codigoNaturezaImovel;

    @Column(name = "co_tipo_proprietario")
    private Long coditoTipoProprietario;

    @Column(name = "co_forma_incorporacao")
    private Long codigoFormaIncorporacao;

    @Column(name = "co_tipo_aquisicao")
    private Long codigoTipoAquisicao;

    @Column(name = "co_classificao_imovel")
    private Long codigoClassificacaoImovel;

    @Column(name = "co_entidade_extinta")
    private Long codigoEntidadeExtinta;

    @Column(name = "ds_proprietario")
    private String proprietario;

    @Column(name = "ic_ativo")
    private Boolean ativo;

    @Transient
    private String ultimaParcelaCriada;

    @Transient
    private Boolean possuiPendencia;

    @Transient
    private Boolean parcelacomPendenciaDestinacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Imovel)) {
            return false;
        }

        Imovel imovel = (Imovel) o;

        if (!id.equals(imovel.id)) {
            return false;
        }
        if (!rip.equals(imovel.rip)) {
            return false;
        }
        return idCadastroImovel.equals(imovel.idCadastroImovel);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = Constants.TREZE * result + rip.hashCode();
        result = Constants.TREZE * result + idCadastroImovel.hashCode();
        return result;
    }
}
