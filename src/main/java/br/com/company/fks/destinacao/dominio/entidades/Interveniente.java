package br.com.company.fks.destinacao.dominio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Getter
@Setter
@Entity
@Table(name = "tb_interveniente", schema = "destinacao")
@Audited
@AuditTable(value = "tb_interveniente_aud", schema = "aud_destinacao")
public class Interveniente implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_interveniente_seq", name = "tb_interveniente_seq")
    @GeneratedValue(generator = "tb_interveniente_seq", strategy = GenerationType.AUTO)
    @Column(name="id_interveniente")
    private Long id;

    @Column(name="ds_cpf_cnpj")
    private String cpfCnpj;

    @Column(name="ds_nome")
    private String nome;

    @Column(name = "ds_codigo_ug")
    private String codigoUg;

    @Column(name = "ds_email")
    private String email;

    @OneToMany(mappedBy = "interveniente")
    private List<Telefone> telefones;

    @OneToOne
    @JoinColumn(name = "co_endereco_correspondencia")
    private EnderecoCorrespondencia enderecoCorrespondencia;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_dados_responsavel")
    private DadosResponsavel dadosResponsavel;

    @Column(name = "ic_possui")
    private Boolean isPossui = Boolean.FALSE;

}
