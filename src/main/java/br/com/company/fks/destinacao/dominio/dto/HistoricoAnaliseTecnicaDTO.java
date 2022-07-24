package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by diego on 29/11/16.
 */

public class HistoricoAnaliseTecnicaDTO implements Serializable {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private AnaliseTecnica analiseTecnica;
    @Getter
    @Setter
    private Date dataAlteracao;
    @Getter
    @Setter
    private StatusAnaliseTecnica statusAnaliseTecnicaAnterior;
    @Getter
    @Setter
    private StatusAnaliseTecnica statusAnaliseTecnicaAtual;
    @Getter
    @Setter
    private String justificativa;
    @Getter
    @Setter
    private Long idUsuario;
    @Getter
    @Setter
    private String nomeUsuario;

    public HistoricoAnaliseTecnicaDTO () {

    }

    public HistoricoAnaliseTecnicaDTO (Long id,
                                       Date dataAlteracao,
                                       StatusAnaliseTecnica statusAnaliseTecnicaAnterior,
                                       StatusAnaliseTecnica statusAnaliseTecnicaAtual,
                                       String justificativa,
                                       String nomeUsuario) {
        this.id = id;
        this.dataAlteracao = new Date(dataAlteracao.getTime());
        this.statusAnaliseTecnicaAnterior = statusAnaliseTecnicaAnterior;
        this.statusAnaliseTecnicaAtual = statusAnaliseTecnicaAtual;
        this.justificativa = justificativa;
        this.nomeUsuario = nomeUsuario;

    }

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param dataAlteracao
     */
    public void setDataAlteracao(final Date dataAlteracao) {
        if (dataAlteracao == null) {
            this.dataAlteracao = null;
        } else {
            this.dataAlteracao = new Date(dataAlteracao.getTime());
        }
    }

    /**
     * Retorna a data de alteração
     * @return Date
     */
    public Date getDataAlteracao() {
        if (this.dataAlteracao == null) {
            return null;
        }
        return new Date(this.dataAlteracao.getTime());
    }

}