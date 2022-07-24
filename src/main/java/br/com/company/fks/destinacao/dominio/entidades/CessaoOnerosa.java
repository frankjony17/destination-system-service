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

@Getter
@Setter
@Entity
@Table(name = "tb_cessao_onerosa", schema = "destinacao")
@DiscriminatorValue("CESSAO_ONEROSA")
@PrimaryKeyJoinColumn(name="id_cessao_onerosa")
@Audited
@AuditTable(value = "tb_cessao_onerosa_aud", schema = "aud_destinacao")
public class CessaoOnerosa extends Destinacao implements Serializable {

    @Column(name = "ds_tipo_cessao")
    private String tipoCessao;

    @Column(name = "ic_cumprimento_encargo")
    private Boolean envolvePagamento;


}
