package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_permissao_uso_imovel_funcional", schema = "destinacao")
@DiscriminatorValue("PERMISSAO_USO_IMOVEL_FUNCIONAL")
@PrimaryKeyJoinColumn(name="id_permissao_uso_imovel_funcional")
@Audited
@AuditTable(value = "tb_permissao_uso_imovel_funcional_aud", schema = "aud_destinacao")
public class PermissaoUsoImovelFuncional extends Destinacao implements Serializable {

}
