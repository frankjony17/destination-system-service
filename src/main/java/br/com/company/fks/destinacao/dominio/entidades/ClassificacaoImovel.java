package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jonatas on 03/05/18.
 */

@Setter
@Getter
@Entity
@Table(name = "tb_classificao_imovel", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_classificacao_imovel")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_classificao_imovel"))})
public class ClassificacaoImovel extends Dominio implements Serializable {

}




