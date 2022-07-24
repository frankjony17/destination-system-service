package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.AtoAutorizativo;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class DestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String numeroAtendimento;
    @Getter
    @Setter
    private String numeroProcesso;
    @Getter
    @Setter
    private Date dataInicioFundamento;
    @Getter
    @Setter
    private Date dataFinalFundamento;
    @Getter
    @Setter
    private UtilizacaoDTO utilizacao;
    @Getter
    @Setter
    private Long codFundamentoLegal;
    @Getter
    @Setter
    private Set<DestinacaoPendenciaDTO> pendencias;
    @Getter
    @Setter
    private List<EncargoDTO> encargos;
    @Getter
    @Setter
    private List<ParcelaDTO> parcelasSelecionadas;
    @Getter
    @Setter
    private List<DestinacaoImovelDTO> destinacaoImoveis;
    @Getter
    @Setter
    private TipoDestinacaoEnum tipoDestinacaoEnum;
    @Getter
    @Setter
    private List<DocumentoDTO> documentos;
    @Getter
    @Setter
    private DadosResponsavelDTO dadosResponsavel;
    @Getter
    @Setter
    private LicitacaoDTO licitacao;
    @Getter
    @Setter
    private FinanceiroDTO financeiro;
    @Getter
    @Setter
    private TipoDestinacaoDTO tipoDestinacao;
    @Getter
    @Setter
    private DominioDTO statusDestinacao;
    @Getter
    @Setter
    private ContratoDTO contrato;
    @Getter
    @Setter
    private List<FotoDTO> fotos;
    @Getter
    @Setter
    private AtoAutorizativo atoAutorizativo;
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
    private Date dataDestinacaoHistorico;


    public void setDataInicioFundamento(final Date dataInicioFundamento){
        if(dataInicioFundamento == null){
            this.dataInicioFundamento = null;
        }
        else{
            this.dataInicioFundamento = new Date(dataInicioFundamento.getTime());
        }
    }

    public Date getDataInicioFundamento(){
        if(this.dataInicioFundamento == null){
            return null;
        }
        return new Date(this.dataInicioFundamento.getTime());
    }

    public void setDataFinalFundamento(final Date dataFinalFundamento){
        if(dataFinalFundamento == null){
            this.dataFinalFundamento = null;
        }
        else{
            this.dataFinalFundamento = new Date(dataFinalFundamento.getTime());
        }
    }

    public Date getDataFinalFundamento(){
        if(this.dataFinalFundamento == null){
            return null;
        }
        return new Date(this.dataFinalFundamento.getTime());
    }

    public void setDataDestinacaoHistorico(final Date dataDestinacaoHistorico){
        if(dataDestinacaoHistorico == null){
            this.dataDestinacaoHistorico = null;
        }
        else{
            this.dataDestinacaoHistorico = new Date(dataDestinacaoHistorico.getTime());
        }
    }

    public Date getDataDestinacaoHistorico(){
        if(this.dataDestinacaoHistorico == null){
            return null;
        }
        return new Date(this.dataDestinacaoHistorico.getTime());
    }
}
