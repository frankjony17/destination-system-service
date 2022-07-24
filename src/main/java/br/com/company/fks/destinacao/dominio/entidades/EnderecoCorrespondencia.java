package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.AdotarEnderecoEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_endereco_correspondencia", schema = "destinacao")
@Audited
@AuditTable(value = "tb_endereco_correspondencia_aud", schema = "aud_destinacao")
public class EnderecoCorrespondencia implements Serializable {


    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_endereco_correspondencia_seq", name = "tb_endereco_correspondencia_seq")
    @GeneratedValue(generator = "tb_endereco_correspondencia_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_endereco_correspondencia")
    private Long id;

    @Column(name = "ds_adotar_endereco")
    @Enumerated(EnumType.STRING)
    private AdotarEnderecoEnum adotarEnderecoEnum;

    @JoinColumn(name = "co_endereco")
    @OneToOne
    private Endereco endereco;

}
