package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by diego on 18/01/17.
 */

public class BenfeitoriaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String codigo;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private BigDecimal areaConstruida;
    @Getter
    @Setter
    private BigDecimal areaDisponivel;
    @Getter
    @Setter
    private DominioDTO estadoConservacao;
    @Getter
    @Setter
    private String observacoes;
    @Getter
    @Setter
    private Boolean ativa;
    @Getter
    @Setter
    private String motivoCancelamento;
    @Getter
    @Setter
    private String descricaoCancelamento;
    @Getter
    @Setter
    private String numeroProcessoCancelamento;
    @Getter
    @Setter
    private Date dataCancelamento;
    @Getter
    @Setter
    private EdificacaoDTO edificacao;
    @Getter
    @Setter
    private BenfeitoriaComplementarDTO benfeitoriaComplementar;
    @Getter
    @Setter
    private Long idBenfeitoriaCadImovel;
    @Getter
    @Setter
    private String especializacao;

    /**
     * Informa a data do cancelamento
     * @param dataCancelamento
     */
    public void setDataCancelamento(final Date dataCancelamento) {
        if (dataCancelamento == null) {
            this.dataCancelamento = null;
        } else {
            this.dataCancelamento = new Date(dataCancelamento.getTime());
        }
    }

    /**
     * Retorna a data do cancelamento.
     * @return Date
     */
    public Date getDataCancelamento() {
        if (this.dataCancelamento == null) {
            return null;
        }
        return new Date(this.dataCancelamento.getTime());
    }

}
