package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/*
 * create by haillanderson on 31/08/2017
 */
@Getter
@Setter
@Entity
@Table(name = "tb_motivo_cancelamento", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_motivo_cancelamento")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_motivo_cancelamento"))})
public class MotivoCancelamento extends Dominio implements Serializable {}
