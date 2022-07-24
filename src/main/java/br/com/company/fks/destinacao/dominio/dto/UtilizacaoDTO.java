package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.SubTipoUtilizacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class UtilizacaoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String finalidade;
    @Getter
    @Setter
    private Integer numeroFamiliasBeneficiadas;
    @Getter
    @Setter
    private Integer numeroServidores;
    @Getter
    @Setter
    private Double areaServidor;
    @Getter
    @Setter
    private String codigoUtilizacao;
    @Getter
    @Setter
    private String responsavelDestinatario;
    @Getter
    @Setter
    private String instrumento;
    @Getter
    @Setter
    private TipoUtilizacao tipoUtilizacao;
    @Getter
    @Setter
    private SubTipoUtilizacao subTipoUtilizacao;
    @Getter
    @Setter
    private String especificacao;
    @Getter
    @Setter
    private String anotacoes;
    @Getter
    @Setter
    private Date dataUtilizacao;
    @Getter
    @Setter
    private String numeroProcesso;
    @Getter
    @Setter
    private Boolean processoPosse;
    @Getter
    @Setter
    private Date dataEfetivacaoUtilizacao;


    /**
     * Método Construtor padrão da Classe
     */
    public UtilizacaoDTO(){

    }

    public UtilizacaoDTO (String rip, String codigoUtilizacao, String sequencial, String descricao, String razaoSocial, String cpfCnpj){
        this.codigoUtilizacao = rip + "/" + codigoUtilizacao+sequencial;
        this.instrumento = descricao;
        this.responsavelDestinatario = cpfCnpj+"-" + razaoSocial;
    }

    public void setDataEfetivacaoUtilizacao(final Date dataEfetivacaoUtilizacao) {
        if(dataEfetivacaoUtilizacao == null) {
            this.dataEfetivacaoUtilizacao = null;
        }
        else {
            this.dataEfetivacaoUtilizacao = new Date(dataEfetivacaoUtilizacao.getTime());
        }
    }

    public Date getDataEfetivacaoUtilizacao() {
        if(this.dataEfetivacaoUtilizacao == null) {
            return null;
        }

        else {
            return new Date(this.dataEfetivacaoUtilizacao.getTime());
        }
    }

    public void setDataUtilizacao(final Date dataUtilizacao) {
        if(dataUtilizacao == null) {
            this.dataUtilizacao = null;
        }
        else {
            this.dataUtilizacao = new Date(dataUtilizacao.getTime());
        }
    }

    public Date getDataUtilizacao() {
        if(this.dataUtilizacao == null) {
            return null;
        }

        else {
            return new Date(this.dataUtilizacao.getTime());
        }
    }
}
