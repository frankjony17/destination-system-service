package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 21/10/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_endereco", schema = "destinacao")
@Audited
@AuditTable(value = "tb_endereco_aud", schema = "aud_destinacao")
public class Endereco implements Serializable {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_endereco_seq", name = "tb_endereco_seq")
    @GeneratedValue(generator = "tb_endereco_seq", strategy = GenerationType.AUTO)
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "ds_cep")
    private String cep;

    @Column(name = "ds_tipo_logradouro")
    private String tipoLogradouro;

    @Column(name = "ds_logradouro")
    private String logradouro;

    @Column(name = "ds_numero")
    private String numero;

    @Column(name = "ds_complemento")
    private String complemento;

    @Column(name = "ds_municipio")
    private String municipio;

    @Column(name = "ds_bairro")
    private String bairro;

    @Column(name = "ds_uf")
    private String uf;

    @Column(name = "ds_pais")
    private String pais;

    @Column(name = "ds_cidade_exterior")
    private String cidadeExterior;

    @Column(name = "ds_codigo_postal")
    private String codigoPostal;

    @Column(name = "ds_nome_cidade_exterior")
    private String nomeCidadeExterior;

    @Column(name = "ds_endereco_exterior")
    private  String enderecoExterior;

    public void setPais(Object pais){
        if(pais instanceof String){
            this.pais = (String) pais;
        } else {
            this.pais = null;
        }
    }

}
