package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.enums.DespachoEncerrarDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoEncerrarDestinacaoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
public class EncerramentoDestinacaoDTO implements Serializable {
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
    private Date dataEncerramentoDestinacao;
    @Getter
    @Setter
    private MotivoEncerrarDestinacaoEnum motivo;
    @Getter
    @Setter
    private String observacaoMotivo;
    @Getter
    @Setter
    private List<ArquivoDTO> arquivos;
    @Getter
    @Setter
    private DespachoEncerrarDestinacaoEnum despacho;
    @Getter
    @Setter
    private String observacaoDespacho;
    @Getter
    @Setter
    private Boolean isAtivo;

    public void setDataEncerramentoDestinacao(final Date data){
        if( data == null ){
            this.dataEncerramentoDestinacao = null;

        }else {
            this.dataEncerramentoDestinacao = new Date(data.getTime());
        }
    }

    public Date getDataEncerramentoDestinacao(){
        if (this.dataEncerramentoDestinacao == null){
            return null;
        }
        return  new Date(this.dataEncerramentoDestinacao.getTime());
    }
}
