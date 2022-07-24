package br.com.company.fks.destinacao.dominio.entidades;

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

/**
 * Created by diego on 10/11/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_familia_beneficiada", schema = "destinacao")
@Audited
@AuditTable(value = "tb_familia_beneficiada_aud", schema = "aud_destinacao")
public class FamiliaBeneficiada implements Serializable {

    @Id
    @Column(name = "id_familia_beneficiada")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_familia_beneficiada_seq", name = "tb_familia_beneficiada_seq")
    @GeneratedValue(generator = "tb_familia_beneficiada_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nu_sequencial")
    private Integer sequencial;

    @Column(name = "ds_nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "ds_cpf_responsavel")
    private String cpfResponsavel;

    @Column(name = "ds_nome_conjuge")
    private String nomeConjuge;

    @Column(name = "ds_cpf_conjuge")
    private String cpfConjuge;

    @Column(name = "nu_area_utilizada")
    private Double areaUtilizada;

    @ManyToOne
    @JoinColumn(name = "co_responsavel")
    @JsonIgnore
    private Responsavel responsavel;

}
