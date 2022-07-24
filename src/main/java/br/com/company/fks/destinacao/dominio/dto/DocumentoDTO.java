package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sdias on 3/29/2017.
 */


public class DocumentoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private DominioDTO tipoDocumento;
    @Getter
    @Setter
    private SubTipoDocumentoDTO subTipoDocumento;
    @Getter
    @Setter
    private Boolean dispensado;
    @Getter
    @Setter
    private String justificativa;
    @Getter
    @Setter
    private String especificar;
    @Getter
    @Setter
    private String pagina;
    @Getter
    @Setter
    private String secao;
    @Getter
    @Setter
    private ArquivoDTO arquivo;
    @Getter
    @Setter
    private Date dataPublicacao;
    @Getter
    @Setter
    private Date dataInicialVigencia;
    @Getter
    @Setter
    private Date dataFinalVigencia;
    @Getter
    @Setter
    private String numeroTermo;
    @Getter
    @Setter
    private String livro;
    @Getter
    @Setter
    private String folha;

    /**
     * Informa data da pulbicação.
     * @param dataPublicacao
     */
    public void setDataPublicacao(final Date dataPublicacao) {
        if (dataPublicacao == null) {
            this.dataPublicacao = null;
        } else {
            this.dataPublicacao = new Date(dataPublicacao.getTime());
        }
    }

    /**
     * Retorna data da publicação.
     * @return Date
     */
    public Date getDataPublicacao() {
        if (this.dataPublicacao == null) {
            return null;
        }
        return new Date(this.dataPublicacao.getTime());
    }

    /**
     * Informa data inicial da vigencia.
     * @param datInicial
     */
    public void setDataInicialVigencia(final Date datInicial) {
        if (datInicial == null) {
            this.dataInicialVigencia = null;
        } else {
            this.dataInicialVigencia = new Date(datInicial.getTime());
        }
    }

    /**
     * Retorna a data inicial da vigencia
     * @return Date
     */
    public Date getDataInicialVigencia() {
        if (this.dataInicialVigencia == null) {
            return null;
        }
        return new Date(this.dataInicialVigencia.getTime());
    }

    /**
     * Informa a data final da vigencia.
     * @param datafinal
     */
    public void setDataFinalVigencia(final Date datafinal) {
        if (datafinal == null) {
            this.dataFinalVigencia = null;
        } else {
            this.dataFinalVigencia = new Date(datafinal.getTime());
        }
    }

    /**
     * Retorna data inicial da vigencia.
     * @return Date
     */
    public Date getDataFinalVigencia() {
        if (this.dataFinalVigencia == null) {
            return null;
        }
        return new Date(this.dataFinalVigencia.getTime());
    }

}
