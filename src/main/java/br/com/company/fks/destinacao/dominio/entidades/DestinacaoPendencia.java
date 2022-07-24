package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis on 10/03/2017.
 */

@Getter
@Setter
@Entity
@Table(name = "tb_destinacao_pendencia", schema = "destinacao")
public class DestinacaoPendencia implements Serializable {

    @EmbeddedId
    private DestinacaoPendenciaID destinacaoPendenciaID;

    @Column(name = "dt_data_geracao")
    private Date dataGerada;


    public DestinacaoPendencia(){}

    public DestinacaoPendencia(Destinacao destinacao, Pendencia pendencia) {
        this.destinacaoPendenciaID = new DestinacaoPendenciaID(destinacao, pendencia);
        this.dataGerada = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DestinacaoPendencia)) {
            return false;
        }

        DestinacaoPendencia that = (DestinacaoPendencia) o;

        if (!destinacaoPendenciaID.equals(that.destinacaoPendenciaID)) {
            return false;
        }
        return dataGerada.equals(that.dataGerada);

    }

    @Override
    public int hashCode() {
        int result = dataGerada.hashCode();
        if (destinacaoPendenciaID != null) {
            result = Constants.TREZE * result + destinacaoPendenciaID.hashCode();
        }
        return result;
    }

    /**
     * Set da dataGerada sobrescrito
     * @param dataGerada
     */
    public void setDataGerada(final Date dataGerada){
        if(dataGerada == null){
            this.dataGerada = null;
        }
        else{
            this.dataGerada = new Date(dataGerada.getTime());
        }
    }

    /**
     * Get da dataGerada sobrescrito
     * @return dataGerada
     */
    public Date getDataGerada(){
        if(this.dataGerada == null){
            return null;
        }
        return new Date(this.dataGerada.getTime());
    }
}
