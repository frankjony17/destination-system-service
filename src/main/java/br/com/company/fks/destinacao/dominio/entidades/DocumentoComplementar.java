package br.com.company.fks.destinacao.dominio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tb_doc_complementar", schema = "destinacao")
public class DocumentoComplementar implements Serializable {

    @Id
    @Column(name = "id_doc_complementar")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_doc_complementar_seq", name = "tb_doc_complementar_seq")
    @GeneratedValue(generator = "tb_doc_complementar_seq", strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "co_analise_tecnica")
    @JsonIgnore
    private AnaliseTecnica analiseTecnica;

    @ManyToOne
    @JoinColumn(name = "co_arquivo")
    private Arquivo arquivo;

}
