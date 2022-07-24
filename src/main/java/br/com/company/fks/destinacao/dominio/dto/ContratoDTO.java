package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class ContratoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Date dataInicio;
    @Getter
    @Setter
    private String numero;
    @Getter
    @Setter
    private ArquivoDTO arquivo;
    @Getter
    @Setter
    private Date dataFinal;

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param data
     */
    public void setDataFinal(final Date data) {
        if (data == null) {
            this.dataFinal = null;
        } else {
            this.dataFinal =  new Date(data.getTime());
        }
    }

    /**
     * Retorna a data final do contrato.
     * @return Date
     */
    public Date getDataFinal() {
        if (this.dataFinal == null) {
            return null;
        }
        return new Date(this.dataFinal.getTime());
    }

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param data
     */
    public void setDataInicio(final Date data) {
        if (data == null) {
            this.dataInicio = null;
        } else {
            this.dataInicio =  new Date(data.getTime());
        }
    }

    /**
     * Retorna a data inicio do contrato.
     * @return Date
     */
    public Date getDataInicio() {
        if (this.dataInicio == null) {
            return null;
        }
        return new Date(this.dataInicio.getTime());
    }

}
