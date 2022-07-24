package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

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
 * Created by raphael on 29/11/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_item_verificacao_especificacao", schema = "destinacao")
public class ItemVerificacaoEspecifica implements Serializable {

    @Id
    @Column(name = "id_item_verificacao_especifica")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_item_verificacao_especifica_seq", name = "tb_item_verificacao_especifica_seq")
    @GeneratedValue(generator = "tb_item_verificacao_especifica_seq", strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "co_item_verif_check_list")
    private ItemVerificacaoCheckList itemVerificacaoCheckList;

    @Column(name = "ic_resposta")
    private Boolean resposta;

    @Column(name = "ds_observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "co_analise_tecnica")
    private AnaliseTecnica analiseTecnica;

}
