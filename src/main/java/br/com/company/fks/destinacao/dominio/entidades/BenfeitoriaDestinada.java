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
import java.math.BigDecimal;

/**
 * Created by diego on 18/01/17.
 */
@Entity
@Getter
@Setter
@Table(name = "tb_benfeitoria_destinada", schema = "destinacao")
@Audited
@AuditTable(value = "tb_benfeitoria_destinada_aud", schema = "aud_destinacao")
public class BenfeitoriaDestinada implements Serializable {

    @Id
    @Column(name = "id_benfeitoria_destinada")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_benfeitoria_destinada_seq", name = "tb_benfeitoria_destinada_seq")
    @GeneratedValue(generator = "tb_benfeitoria_destinada_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "co_benfeitoria")
    private Long idBenfeitoria;

    @Column(name = "nu_area_utilizar")
    private BigDecimal areaUtilizar;

    @Audited
    @ManyToOne
    @JoinColumn(name = "co_destinacao_imovel")
    @JsonIgnore
    private DestinacaoImovel destinacaoImovel;

    @Column(name = "ic_ativa")
    private Boolean ativa;

}
