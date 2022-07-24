package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class EncargoDTO implements Serializable {

    @Getter
    @Setter
    private long idDestinacao;
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private Date dataCumprimento;
    @Getter
    @Setter
    private Boolean cumprimentoEncargo;
    @Getter
    @Setter
    private Boolean utilizarData;

    /**
     * Informa a data do cumprimento.
     * @param data
     */
    public void setDataCumprimento(final Date data) {
        if (data == null) {
            this.dataCumprimento = null;
        } else {
            this.dataCumprimento = new Date(data.getTime());
        }
    }

    /**
     * Retorna da data do cumprimento.
     * @return Date
     */
    public Date getDataCumprimento() {
        if (this.dataCumprimento == null) {
            return null;
        }
        return new Date(this.dataCumprimento.getTime());
    }

}
