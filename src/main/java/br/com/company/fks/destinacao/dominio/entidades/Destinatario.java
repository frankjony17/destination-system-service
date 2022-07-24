package br.com.company.fks.destinacao.dominio.entidades;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gustavodias on 13/01/17.
 */

@Entity
@Getter
@Setter
@Table(name = "tb_destinatario", schema = "destinacao")
@Audited
@AuditTable(value = "tb_destinatario_aud", schema = "aud_destinacao")
public class Destinatario implements Serializable{

    @Id
    @Column(name = "id_destinatario")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_destinatario_seq", name = "tb_destinatario_seq")
    @GeneratedValue(generator = "tb_destinatario_seq", strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "ds_cnpj")
    private String cnpj;

    @Column(name = "ds_nome_empresarial")
    private String nomeEmpresarial;

    @Column(name = "ds_autarquia_fundacao")
    private String autarquiaFundacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_endereco_correspondencia")
    private EnderecoCorrespondencia enderecoCorrespondencia;

    @OneToMany(mappedBy = "destinatario")
    private List<Telefone> telefones;

}
