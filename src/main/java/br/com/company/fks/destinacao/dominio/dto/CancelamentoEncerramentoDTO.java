package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.enums.DespachoCancelarEncerrarEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoCancelarEncerrarEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tawan-souza on 19/12/17.
 */

@NoArgsConstructor
public class CancelamentoEncerramentoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nomeResponsavelTecnico;
    @Getter
    @Setter
    private String cpfResponsavelTecnico;
    @Getter
    @Setter
    private String nomeSuperintendente;
    @Getter
    @Setter
    private String cpfSuperIntendente;
    @Getter
    @Setter
    private Date dataCancelamentoEncerramento;
    @Getter
    @Setter
    private MotivoCancelarEncerrarEnum motivo;
    @Getter
    @Setter
    private String observacaoMotivo;
    @Getter
    @Setter
    private List<ArquivoDTO> arquivos;
    @Getter
    @Setter
    private DespachoCancelarEncerrarEnum despacho;
    @Getter
    @Setter
    private String observacaoDespacho;
    @Getter
    @Setter
    private Boolean isAtivo;

    public void setDataCancelamentoEncerramento(final Date data){
        if( data == null ){
            this.dataCancelamentoEncerramento = null;

        }else {
            this.dataCancelamentoEncerramento = new Date(data.getTime());
        }
    }

    public Date getDataCancelamentoEncerramento(){
        if (this.dataCancelamentoEncerramento == null){
            return null;
        }
        return  new Date(this.dataCancelamentoEncerramento.getTime());
    }
}
