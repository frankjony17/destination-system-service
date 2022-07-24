package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.DescricaoParentescoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by tawan-souza on 28/11/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_residente", schema = "destinacao")
@Audited
@AuditTable(value = "tb_residente_aud", schema = "aud_destinacao")
public class Residente implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_residente_seq", name = "tb_residente_seq")
    @GeneratedValue(generator = "tb_residente_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_residente")
    private Long id;

    @Column(name = "nu_sequencial")
    private Integer sequencial;

    @Column(name = "ds_cpf")
    private String cpf;

    @Column(name = "ds_nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_descricao")
    private DescricaoParentescoEnum descricao;

    @Column(name = "ds_descricao_outro_residente")
    private String descricaoOutro;

    @Column(name = "nu_renda")
    private Double renda;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_responsavel")
    private Responsavel responsavel;

}
