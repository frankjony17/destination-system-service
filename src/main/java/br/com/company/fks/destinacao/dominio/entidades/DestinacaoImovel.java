package br.com.company.fks.destinacao.dominio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;


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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */
@Getter @Setter
@Entity
@Table(name = "tb_destinacao_imovel", schema = "destinacao")
@Audited
@AuditTable(value = "tb_destinacao_imovel_aud", schema = "aud_destinacao")
public class DestinacaoImovel implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_destinacao_imovel_seq", name = "tb_destinacao_imovel_seq")
    @GeneratedValue(generator = "tb_destinacao_imovel_seq", strategy = GenerationType.AUTO)
    @Column(name="id_destinacao_imovel")
    private Long id;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "co_destinacao")
    @JsonIgnore
    private Destinacao destinacao;

    @Audited
    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "co_imovel")
    private Imovel imovel;

    @Column(name = "nu_codigo_utilizacao")
    private String codigoUtilizacao;

    @Audited
    @OneToMany(mappedBy = "destinacaoImovel")
    private List<BenfeitoriaDestinada> benfeitoriasDestinadas;

    @Audited
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_doc_imovel_destinado", schema = "destinacao", joinColumns = {
            @JoinColumn(name = "id_destinacao_imovel")}, inverseJoinColumns = {
            @JoinColumn(name = "id_arquivo")})
    private List<Arquivo> documentos;

    @Column(name = "nu_area_terreno_destinada")
    private BigDecimal areaTerrenoDestinada;

    @Column(name = "nu_fracao_ideal")
    private BigDecimal fracaoIdeal;

    @Column(name = "ds_memorial_desc_area_construida")
    private String memorialDescAreaConstruida;

    @ManyToMany(mappedBy = "destinacaoImoveis")
    private List<Parcela> parcelas;

    @Transient
    private Parcela parcela;

    @NotAudited
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_foto_video_imovel_destinado", schema = "destinacao", joinColumns = {
            @JoinColumn(name = "id_destinacao_imovel")}, inverseJoinColumns = {
            @JoinColumn(name = "id_arquivo")})
    private List<Arquivo> fotoVideo;

    @Column(name = "ic_ultima_destinacao")
    private Boolean ultimaDestinacao;

}
