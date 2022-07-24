package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by diego on 19/12/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_analise_tecnica_despacho_chefia", schema = "destinacao")
public class AnaliseTecnicaDespachoChefia implements Serializable{

    @EmbeddedId
    private AnaliseTecnicaDespachoID analiseTecnicaDespachoID;

    @Column(name = "ds_justificativa")
    private String justificativa;

}
