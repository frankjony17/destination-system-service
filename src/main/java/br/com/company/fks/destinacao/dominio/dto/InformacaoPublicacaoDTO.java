package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by haillanderson on 26/05/17.
 */


public class InformacaoPublicacaoDTO implements Serializable{
    @Getter
    @Setter
    private Integer numeroPagina;
    @Getter
    @Setter
    private Integer numeroSecao;
    @Getter
    @Setter
    private Date dataPublicacao;

    /**
     * Set da dataPublicacao sobrescrito
     * @param date
     */
    public void setDataPublicacao(final Date date){
        if (date == null) {
            this.dataPublicacao = null;
        } else {
            this.dataPublicacao = new Date(date.getTime());
        }
    }

    /**
     * Get a data publicada sobrescrito
     * @return
     */
    public Date getDataPublicacao() {
        if (this.dataPublicacao == null) {
            return null;
        }
        return new Date(this.dataPublicacao.getTime());
    }

}
