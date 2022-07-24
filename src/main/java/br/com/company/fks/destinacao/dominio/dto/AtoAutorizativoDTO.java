package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.enums.TipoAtoAutorizativoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */

public class AtoAutorizativoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long numeroAto;
    @Getter
    @Setter
    private TipoAtoAutorizativoEnum tipoAtoAutorizativoEnum;
    @Getter
    @Setter
    private DominioDTO tipoAtoAutorizativo;
    @Getter
    @Setter
    private Date dataPublicacao;

    /**
     * Informa data da publicação
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
     * Recupera a data da publicacao
     * @return
     */
    public Date getDataPublicacao() {
        if (this.dataPublicacao == null) {
            return null;
        }
        return new Date(this.dataPublicacao.getTime());
    }

}
