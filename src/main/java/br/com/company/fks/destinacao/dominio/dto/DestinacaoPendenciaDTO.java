package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


public class DestinacaoPendenciaDTO implements Serializable{
    @Getter
    @Setter
    private DestinacaoPendenciaIDDTO destinacaoPendenciaID;
    @Getter
    @Setter
    private Date dataGerada;
    
    public DestinacaoPendenciaDTO(){
        
    }
    
    public DestinacaoPendenciaDTO(Long idDestinacao, Long idPendencia, String descricao, String modulo, Date dataGerada){
        DestinacaoDTO destinacaoDTO = new DestinacaoDTO();
        destinacaoDTO.setId(idDestinacao);

        PendenciaDTO pendenciaDTO = new PendenciaDTO();
        pendenciaDTO.setId(idPendencia);
        pendenciaDTO.setDescricao(descricao);
        pendenciaDTO.setModulo(modulo);

        this.destinacaoPendenciaID = new DestinacaoPendenciaIDDTO();
        this.destinacaoPendenciaID.setDestinacao(destinacaoDTO);
        this.destinacaoPendenciaID.setPendencia(pendenciaDTO);
        
        this.dataGerada = dataGerada;
    }

    /**
     * Informa a data gerada
     * @param dataGerada
     */
    public void setDataGerada(final Date dataGerada){
        if (dataGerada == null){
            this.dataGerada = null;
        }else{
            this.dataGerada = new Date(dataGerada.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataGerada() {
        if (this.dataGerada == null) {
            return null;
        }
        return new Date(this.dataGerada.getTime());
    }

}
