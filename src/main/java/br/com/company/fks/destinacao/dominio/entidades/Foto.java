package br.com.company.fks.destinacao.dominio.entidades;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by basis on 05/07/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_foto", schema = "destinacao")
@Audited
@AuditTable(value = "tb_foto_aud", schema = "aud_destinacao")
public class Foto implements Serializable {

    @Id
    @Column(name = "id_foto")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_foto_seq", name = "tb_foto_seq")
    @GeneratedValue(generator = "tb_foto_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Audited
    @OneToOne
    @JoinColumn(name = "co_arquivo")
    private Arquivo arquivo;



}
