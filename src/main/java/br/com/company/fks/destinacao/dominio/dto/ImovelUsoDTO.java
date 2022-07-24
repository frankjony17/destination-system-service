package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class ImovelUsoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String usoPrincipal;
    @Getter
    @Setter
    private String descricaoUso;
    @Getter
    @Setter
    private Date dataVigencia;
    @Getter
    @Setter
    private Long numeroFamilias;

    /**
     * Insere a data da vigencia
     * @param dataVigencia
     */
    public void setDataVigencia(final Date dataVigencia) {
        if (dataVigencia == null) {
            this.dataVigencia = null;
        } else {
            this.dataVigencia = new Date(dataVigencia.getTime());
        }
    }

    /**
     * Retorna a data da vigencia.
     * @return
     */
    public Date getDataVigencia() {
        if (this.dataVigencia == null) {
            return null;
        }
        return new Date(this.dataVigencia.getTime());
    }

}
