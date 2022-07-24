package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.utils.DataUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 09/11/2016.
 */


public class RequerimentoConsultaDTO implements Serializable {
    @Getter
    @Setter
    private Long idServico;
    @Getter
    @Setter
    private String nuAtendimento;
    @Getter
    @Setter
    private String nomeRequerente;
    @Getter
    @Setter
    private String cpfCnpj;
    @Getter
    @Setter
    private String situacao;
    @Getter
    @Setter
    private String nomeResponsavel;
    @Getter
    @Setter
    private String uf;
    @Getter
    @Setter
    @DateTimeFormat
    private Date dataSolicitacaoInicio;
    @Getter
    @Setter
    @DateTimeFormat
    private Date dataSolicitacaoFinal;
    @Getter
    @Setter
    @DateTimeFormat
    private Date dataEnvioAnaliseInicio;
    @Getter
    @Setter
    @DateTimeFormat
    private Date dataEnvioAnaliseFinal;
    @Getter
    @Setter
    private Integer page ;
    @Getter
    @Setter
    private Integer limit ;

    /**
     * Formata a dataSolicitacaoInicio
     * @param dataSolicitacaoInicio
     * @throws ParseException
     */
    public void setDataSolicitacaoInicio(String dataSolicitacaoInicio) throws ParseException {
        if (dataSolicitacaoInicio == null) {
            this.dataSolicitacaoInicio = null;
        } else {
            this.dataSolicitacaoInicio = new SimpleDateFormat(DataUtil.YYYY_MM_DD).parse(dataSolicitacaoInicio);
        }
    }

    /**
     * Retorna data solicitacao inicio.
     * @return
     */
    public Date getDataSolicitacaoInicio() {
        if (this.dataSolicitacaoInicio == null) {
            return null;
        }
        return new Date(this.dataSolicitacaoInicio.getTime());
    }

    /**
     * Formata dataSolicitacaoFinal
     * @param dataSolicitacaoFinal
     * @throws ParseException
     */
    public void setDataSolicitacaoFinal(String dataSolicitacaoFinal) throws ParseException {
        if (dataSolicitacaoFinal == null) {
            this.dataEnvioAnaliseFinal = null;
        } else {
            this.dataSolicitacaoFinal = new SimpleDateFormat(DataUtil.YYYY_MM_DD).parse(dataSolicitacaoFinal);
        }
    }

    /**
     * Retorna data solicitação final.
     * @return
     */
    public Date getDataSolicitacaoFinal() {
        if (this.dataSolicitacaoFinal == null) {
            return null;
        }
        return new Date(this.dataSolicitacaoFinal.getTime());
    }

    /**
     * Formata dataEnvioAnaliseInicio
     * @param dataEnvioAnaliseInicio
     * @throws ParseException
     */
    public void setDataEnvioAnaliseInicio(String dataEnvioAnaliseInicio) throws ParseException {
        if (dataEnvioAnaliseInicio == null) {
            this.dataEnvioAnaliseInicio = null;
        } else {
            this.dataEnvioAnaliseInicio = new SimpleDateFormat(DataUtil.YYYY_MM_DD).parse(dataEnvioAnaliseInicio);
        }
    }

    /**
     * Retorna data analise inicio.
     * @return
     */
    public Date getDataEnvioAnaliseInicio() {
        if (this.dataEnvioAnaliseInicio == null) {
            return null;
        }
        return new Date(this.dataEnvioAnaliseInicio.getTime());
    }

    /**
     * Formata dataEnvioAnaliseFinal
     * @param dataEnvioAnaliseFinal
     * @throws ParseException
     */
    public void setDataEnvioAnaliseFinal(String dataEnvioAnaliseFinal) throws ParseException {
        if (dataEnvioAnaliseFinal == null) {
            this.dataEnvioAnaliseFinal = null;
        } else {
            this.dataEnvioAnaliseFinal = new SimpleDateFormat(DataUtil.YYYY_MM_DD).parse(dataEnvioAnaliseFinal);
        }
    }

    /**
     * Retorna data analise final.
     * @return
     */
    public Date getDataEnvioAnaliseFinal() {
        if (this.dataEnvioAnaliseFinal == null) {
            return null;
        }
        return new Date(this.dataEnvioAnaliseFinal.getTime());
    }


}
