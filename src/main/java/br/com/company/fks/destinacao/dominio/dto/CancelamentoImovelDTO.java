package br.com.company.fks.destinacao.dominio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


public class CancelamentoImovelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCancelamento;
    @Getter
    @Setter
    private Long usuarioCadastrador;
    @Getter
    @Setter
    private TipoCancelamentoImovelDTO tipoCancelamentoImovel;
    @Getter
    @Setter
    private String justificativa;
    @Getter
    @Setter
    private ImovelDTO imovel;
    @Getter
    @Setter
    private String numeroProcesso;


    public void setDataCancelamento(final Date dataCancelamento){
        if(dataCancelamento == null){
            this.dataCancelamento= null;
        }
        else{
            this.dataCancelamento = new Date(dataCancelamento.getTime());
        }
    }

    /**
     * Get da dataCumprimento sobrescrito
     * @return dataCumprimento
     */
    public Date getDataCancelamento(){
        if(this.dataCancelamento == null){
            return null;
        }
        return new Date(this.dataCancelamento.getTime());
    }
}
