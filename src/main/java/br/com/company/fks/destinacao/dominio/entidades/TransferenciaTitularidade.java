package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gustavodias on 13/01/17.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_transferencia", schema = "destinacao")
@DiscriminatorValue("TRANSFERENCIA")
@PrimaryKeyJoinColumn(name="id_transferencia")
@Audited
@AuditTable(value = "tb_transferencia_aud", schema = "aud_destinacao")
public class TransferenciaTitularidade extends Destinacao implements Serializable {

    @ManyToMany
    @JoinTable(name = "tb_transferencia_ato_complementar", schema = "destinacao", joinColumns = {
            @JoinColumn(name = "id_transferencia")}, inverseJoinColumns = {
            @JoinColumn(name = "id_arquivo")})
    private List<Arquivo> atosComplementares;

    @NotAudited
    @ManyToOne
    @JoinColumn(name="co_tipo_transferencia")
    private TipoTransferencia tipoTransferencia;


    @OneToOne
    @JoinColumn(name = "co_destinatario")
    private Destinatario destinatario;

    @ManyToOne
    @JoinColumn(name = "co_tipo_destinatario")
    private TipoDestinatario tipoDestinatario;




}
