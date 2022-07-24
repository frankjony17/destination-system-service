package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name="tb_instituicao_financeira", schema = "destinacao")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "id_instituicao_financeira")),
    @AttributeOverride(name="descricao", column =@Column(name = "ds_instituicao_financeira"))})
public class InstituicaoFinanceira extends Dominio implements Serializable {


}
