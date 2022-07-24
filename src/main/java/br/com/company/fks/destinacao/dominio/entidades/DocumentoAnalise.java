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
 * Created by basis on 19/12/16.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_documento_analise", schema = "destinacao")
public class DocumentoAnalise implements Serializable{

    @Id
    @Column(name = "id_documento_analise")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_documento_analise_seq", name = "tb_documento_analise_seq")
    @GeneratedValue(generator = "tb_documento_analise_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_documento")
    private Long idDocumento;

    @Column(name = "ic_resposta")
    private Boolean resposta;

    @Column(name = "ds_observacao")
    private String observacao;

    @Column(name = "ds_tipo_documento")
    private String tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "co_analise_tecnica")
    private AnaliseTecnica analiseTecnica;
}
