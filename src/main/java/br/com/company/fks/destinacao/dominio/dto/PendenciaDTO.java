package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class PendenciaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private String modulo;
    @Getter
    @Setter
    private Date dataGerada;
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private Long quantidade;
    @Getter
    @Setter
    private Long idDestinacao;

    public PendenciaDTO() {}

    public PendenciaDTO(Long id, Long idDestinacao, String descricao, String modulo, Date dataGerada) {
        this.id = id;
        this.idDestinacao = idDestinacao;
        this.descricao = descricao;
        this.modulo = modulo;
        this.dataGerada = new Date(dataGerada.getTime());
    }

    public PendenciaDTO(Long idDestinacao, String descricao, Date dataGerada) {
        this.idDestinacao = idDestinacao;
        this.descricao = descricao;
        this.dataGerada = new Date(dataGerada.getTime());
    }

    /**
     * Informa data da pendencia gerada.
     * @param dataGerada
     */
    public void setDataGerada(final Date dataGerada) {
        if (dataGerada == null) {
            this.dataGerada = null;
        } else {
            this.dataGerada = new Date(dataGerada.getTime());
        }
    }

    /**
     * Retorna data da pendencia gerada.
     * @return Date
     */
    public Date getDataGerada() {
        if (this.dataGerada == null) {
            return null;
        }
        return new Date(this.dataGerada.getTime());
    }



}
