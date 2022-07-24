package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by gustavodias on 13/01/17.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_tipo_destinatario", schema = "destinacao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_tipo_destinatario")),
        @AttributeOverride(name = "descricao", column = @Column(name = "ds_tipo_destinatario"))})
@Audited
@AuditTable(value = "tb_tipo_destinatario_aud", schema = "aud_destinacao")
public class TipoDestinatario extends Dominio implements Serializable{


    /**
     * Construtor
     */
    public TipoDestinatario(){
    }

    /**
     * Construtor
     */
    public TipoDestinatario(int id) {
        this();
        setId(id);
    }


}
