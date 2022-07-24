package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by diego on 02/12/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_item_verificacao_check_list", schema = "destinacao")
public class ItemVerificacaoCheckList implements Serializable {

    @Id
    @Column(name = "id_item_verificacao_check_list")
    private Long id;

    @Column(name = "ds_item_verificacao_check_list")
    private String descricao;

    @Column(name = "co_fundamento_legal")
    private Long codFundamentoLegal;

}
