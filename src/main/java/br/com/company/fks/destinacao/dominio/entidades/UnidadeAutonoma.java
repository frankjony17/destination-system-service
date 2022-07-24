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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by diego on 31/01/17.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_unidade_autonoma", schema = "destinacao")
@Audited
@AuditTable(value = "tb_unidade_autonoma_aud", schema = "aud_destinacao")
public class UnidadeAutonoma implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_unidade_autonoma_seq", name = "tb_unidade_autonoma_seq")
    @GeneratedValue(generator = "tb_unidade_autonoma_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_unidade_autonoma")
    private Long id;

    @Column(name = "id_unidade_autonoma_cad_imovel")
    private Long idUnidadeAutonomaCadImovel;

    @Column(name = "nu_area")
    private BigDecimal area;

    @Column(name = "nu_area_disponivel")
    private BigDecimal areaDisponivel;

    @Column(name = "ds_especializacao")
    private String especializacao;

}
