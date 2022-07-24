package br.com.company.fks.destinacao.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by diego on 19/12/16.
 */
@Getter
@Setter
@Embeddable
public class AnaliseTecnicaDespachoID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_analise_tecnica", referencedColumnName = "id_analise_tecnica")
    private AnaliseTecnica analiseTecnica;

    @ManyToOne
    @JoinColumn(name = "id_despacho", referencedColumnName = "id_despacho")
    private Despacho despacho;

    /**
     * Construtor
     */
    public AnaliseTecnicaDespachoID() {
    }

    /**
     * Construtor
     * @param analiseTecnica
     * @param despacho
     */
    public AnaliseTecnicaDespachoID(AnaliseTecnica analiseTecnica, Despacho despacho) {
        this.despacho = despacho;
        this.analiseTecnica = analiseTecnica;
    }

}
