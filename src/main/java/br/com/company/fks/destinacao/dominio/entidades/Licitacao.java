package br.com.company.fks.destinacao.dominio.entidades;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_licitacao", schema = "destinacao")
public class Licitacao implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_licitacao_seq", name = "tb_licitacao_seq")
    @GeneratedValue(generator = "tb_licitacao_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_licitacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "co_tipo_licitacao")
    private TipoLicitacao tipoLicitacao;

    @Column(name = "nu_processo")
    private String numeroProcesso;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_licitacao_arquivo", schema = "destinacao", joinColumns = {
            @JoinColumn(name = "id_licitacao")}, inverseJoinColumns = {
            @JoinColumn(name = "id_arquivo")})
    private List<Arquivo> arquivos;

}
