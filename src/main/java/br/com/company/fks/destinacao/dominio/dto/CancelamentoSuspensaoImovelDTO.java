package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class CancelamentoSuspensaoImovelDTO implements Serializable {

    @Getter
    @Setter
    private MotivoCancelamentoSuspensaoImovelDTO motivo;
    @Getter
    @Setter
    private String numeroProcesso;
    @Getter
    @Setter
    private String numeroFolha;
    @Getter
    @Setter
    private List<RipSuspensaoImovelDTO> rips;
    @Getter
    @Setter
    private Date dataFimVigencia;
    @Getter
    @Setter
    private String observacao;
    @Getter
    @Setter
    private Long idSuspensao;


    /**
     *
     * @param dataFimVigencia
     */
    public void setDataFimVigencia(final Date dataFimVigencia){
        if (dataFimVigencia == null){
            this.dataFimVigencia = null;
        }else{
            this.dataFimVigencia = new Date(dataFimVigencia.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataFimVigencia() {
        if (this.dataFimVigencia == null) {
            return null;
        }
        return new Date(this.dataFimVigencia.getTime());
    }
}

