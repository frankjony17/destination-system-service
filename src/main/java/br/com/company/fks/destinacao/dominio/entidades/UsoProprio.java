package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by basis on 30/06/17.
 */
@Getter
@Setter
@Entity
@Table(name ="tb_uso_proprio", schema = "destinacao")
@DiscriminatorValue("USO_PROPRIO")
@PrimaryKeyJoinColumn(name = "id_uso_proprio")
@Audited
@AuditTable(value = "tb_uso_proprio_aud", schema = "aud_destinacao")
public class UsoProprio extends Destinacao implements Serializable {

    @Column(name="ic_homologado")
    private Boolean homologado;

    @Column(name="co_responsavel_cadastro")
    private Long idResponsavelCadastro;

    @Column(name="ds_observacao")
    private String observacao;
}
