package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoRepresentacaoEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tawan-souza on 27/11/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_dados_responsavel", schema = "destinacao")
@Audited
@AuditTable(value = "tb_dados_responsavel_aud", schema = "aud_destinacao")
public class DadosResponsavel implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_dados_responsavel_seq", name = "tb_dados_responsavel_seq")
    @GeneratedValue(generator = "tb_dados_responsavel_seq", strategy = GenerationType.AUTO)
    @Column(name="id_dados_responsavel")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="ds_tipo_posse_ocupacao")
    private TipoPosseOcupacaoEnum tipoPosseOcupacao;

    @Enumerated(EnumType.STRING)
    @Column(name="ds_tipo_representacao")
    private TipoRepresentacaoEnum tipoRepresentacao;

    @Column(name = "nu_qtd_responsaveis")
    private Integer qtdResponsaveis;

    @Column(name = "ds_cnpj")
    private String cnpj;

    @Column(name = "ds_codigo_ug")
    private String codigoUg;

    @Column(name = "ds_nome_entidade")
    private String nomeEntiade;

    @OneToOne
    @JoinColumn(name = "co_endereco_correspondencia")
    private EnderecoCorrespondencia enderecoCorrespondencia;

    @OneToMany(mappedBy = "dadosResponsavel")
    private List<Responsavel> responsaveis;

    @OneToOne
    @JoinColumn(name = "co_interveniente")
    private Interveniente interveniente;

}
