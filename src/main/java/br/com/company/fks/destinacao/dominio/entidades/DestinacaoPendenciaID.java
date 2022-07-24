package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Basis on 10/03/2017.
 */

@Setter
@Getter
@Embeddable
public class DestinacaoPendenciaID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_destinacao", referencedColumnName = "id_destinacao")
    private Destinacao destinacao;

    @ManyToOne
    @JoinColumn(name = "id_pendencia", referencedColumnName = "id_pendencia")
    private Pendencia pendencia;

    /**
     * Construtor
     * @param destinacao;
     * @param pendencia;
     */
    public DestinacaoPendenciaID(Destinacao destinacao, Pendencia pendencia){
        this.destinacao = destinacao;
        this.pendencia = pendencia;
    }

    public DestinacaoPendenciaID() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DestinacaoPendenciaID)) {
            return false;
        }

        DestinacaoPendenciaID that = (DestinacaoPendenciaID) o;

        if (!destinacao.equals(that.destinacao)) {
            return false;
        }
        return pendencia.equals(that.pendencia);

    }

    @Override
    public int hashCode() {
        int result = destinacao.hashCode();
        result = Constants.TREZE * result + pendencia.hashCode();
        return result;
    }
}
