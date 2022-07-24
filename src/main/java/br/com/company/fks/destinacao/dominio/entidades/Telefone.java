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
@Table(name = "tb_telefone", schema = "destinacao")
@Audited
@AuditTable(value = "tb_telefone_aud", schema = "aud_destinacao")
public class Telefone implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_telefone_seq", name = "tb_telefone_seq")
    @GeneratedValue(generator = "tb_telefone_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_telefone")
    private Long id;

    @Column(name = "ds_ddd")
    private String ddd;

    @Column(name = "ds_numero")
    private String numero;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_responsavel")
    private Responsavel responsavel;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_interveniente")
    private Interveniente interveniente;

    @Column(name = "ic_principal")
    private Boolean isPrincipal = Boolean.FALSE;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_destinatario")
    private Destinatario destinatario;
}
