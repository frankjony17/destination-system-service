package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.EstadoCivilEnum;
import br.com.company.fks.destinacao.dominio.enums.OpcoesPadraoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */
@Getter @Setter
@Entity
@Table(name = "tb_responsavel", schema = "destinacao")
@Audited
@AuditTable(value = "tb_responsavel_aud", schema = "aud_destinacao")
public class Responsavel implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_responsavel_seq", name = "tb_responsavel_seq")
    @GeneratedValue(generator = "tb_responsavel_seq", strategy = GenerationType.AUTO)
    @Column(name="id_responsavel")
    private Long id;

    @Column(name="ds_cpf_cnpj")
    private String cpfCnpj;

    @Column(name="ds_nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name="ds_estado_civil")
    private EstadoCivilEnum estadoCivil;

    @Column(name = "ic_obito")
    private Boolean isObito = Boolean.FALSE;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_obito")
    private Date dataObito;

    @Column(name = "ic_falecido_sisobi")
    private Boolean isFalecidoSisobi = Boolean.FALSE;

    @Column(name = "ic_falecido_receita")
    private Boolean isFalecidoReceita = Boolean.FALSE;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "nu_area_fracao")
    private Double areaFracao;

    @Column(name = "ds_observacoes")
    private String observacoes;

    @Column(name = "nu_renda")
    private Double renda;

    @Column(name = "nu_renda_familiar")
    private Double rendaFamiliar;

    @Column(name = "nu_renda_familiar_cad_unico")
    private Double rendaFamiliarCDAUnico;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_possui_imovel_particular")
    private OpcoesPadraoEnum possuiImovelParticular;

    @OneToMany(mappedBy = "responsavel")
    private List<Residente> residentes;

    @OneToOne
    @JoinColumn(name = "co_endereco_correspondencia")
    private EnderecoCorrespondencia enderecoCorrespondencia;

    @OneToMany(mappedBy = "responsavel", cascade=CascadeType.ALL)
    private List<FamiliaBeneficiada> familiasBeneficiadas;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_dados_responsavel")
    private DadosResponsavel dadosResponsavel;

    @OneToMany(mappedBy = "responsavel")
    private List<Telefone> telefones;

    @Column(name = "ic_principal")
    private Boolean isPrincipal = Boolean.FALSE;


    /**
     * Informa a data do cancelamento
     * @param dataObito
     */
    public void setDataObito(final Date dataObito){
        if (dataObito == null){
            this.dataObito= null;
        }else{
            this.dataObito = new Date(dataObito.getTime());
        }
    }

    /**
     *
     * @return dataCancelamento
     */
    public Date getDataObito() {
        if (this.dataObito == null) {
            return null;
        }
        return new Date(this.dataObito.getTime());
    }
}
