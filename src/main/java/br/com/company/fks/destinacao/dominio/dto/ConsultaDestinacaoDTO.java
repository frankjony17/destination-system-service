package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by diego on 14/06/17.
 */

public class ConsultaDestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String rip;
    @Getter
    @Setter
    private String codigoUtilizacao;
    @Getter
    @Setter
    private String sequencialParcela;
    @Getter
    @Setter
    private String codigoContrato;
    @Getter
    @Setter
    private DominioDTO tipoDestinacao;
    @Getter
    @Setter
    private DominioDTO tipoUtilizacao;
    @Getter
    @Setter
    private DominioDTO subTipoUtilizacao;
    @Getter
    @Setter
    private BigDecimal fracaoIdeal;
    @Getter
    @Setter
    private BigDecimal areaConstruida;
    @Getter
    @Setter
    private String nomeResponsavel;
    @Getter
    @Setter
    private String cpfCnpj;
    @Getter
    @Setter
    private EnderecoDTO endereco;
    @Getter
    @Setter
    private Double latitude;
    @Getter
    @Setter
    private Double longitude;
    @Getter
    @Setter
    private BigDecimal areaTerreno;
    @Getter
    @Setter
    private String dataInicioContrato;
    @Getter
    @Setter
    private String dataFimContrato;
    @Getter
    @Setter
    private String imagem;
    @Getter
    @Setter
    private DominioDTO statusDestinacao;
    @Getter
    @Setter
    private Long codigoClassificacaoImovel;
    @Getter
    @Setter
    private Boolean ativa;
    @Getter
    @Setter
    private Long versaoDestinacao;
    @Getter
    @Setter
    private Boolean ativarCopia;
    @Getter
    @Setter
    private String dataDestinacaoHistorico;
    @Getter
    @Setter
    private Date dataInicioFundamento;
    @Getter
    @Setter
    private Date dataFimFundamento;


    /**
     *
     * @param dataInicioFundamento
     */
    public void setDataInicioFundamento(final Date dataInicioFundamento){
        if (dataInicioFundamento == null){
            this.dataInicioFundamento = null;
        }else{
            this.dataInicioFundamento = new Date(dataInicioFundamento.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataInicioFundamento() {
        if (this.dataInicioFundamento == null) {
            return null;
        }
        return new Date(this.dataInicioFundamento.getTime());
    }


    /**
     *
     * @param dataFimFundamento
     */
    public void setDataFimFundamento(final Date dataFimFundamento){
        if (dataFimFundamento == null){
            this.dataFimFundamento = null;
        }else{
            this.dataFimFundamento = new Date(dataFimFundamento.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataFimFundamento() {
        if (this.dataFimFundamento == null) {
            return null;
        }
        return new Date(this.dataFimFundamento.getTime());
    }

}
