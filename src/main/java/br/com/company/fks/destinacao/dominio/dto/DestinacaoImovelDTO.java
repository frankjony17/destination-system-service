package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class DestinacaoImovelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private ImovelDTO imovel;
    @Getter
    @Setter
    private String codigoUtilizacao;
    @Getter
    @Setter
    private List<BenfeitoriaDestinadaDTO> benfeitoriasDestinadas;
    @Getter
    @Setter
    private List<ArquivoDTO> documentos;
    @Getter
    @Setter
    private List<ArquivoDTO> fotoVideo;
    @Getter
    @Setter
    private BigDecimal areaTerrenoDestinada;
    @Getter
    @Setter
    private BigDecimal fracaoIdeal;
    @Getter
    @Setter
    private String memorialDescAreaConstruida;
    @Getter
    @Setter
    private ParcelaDTO parcela;
    @Getter
    @Setter
    private String codigoParcela;
    @Getter
    @Setter
    private String rip;
    @Getter
    @Setter
    private List<ParcelaDTO> parcelas;

    public DestinacaoImovelDTO(String rip, String codigoUtilizacao){
        this.rip = rip;
        this.codigoUtilizacao = codigoUtilizacao;
    }

    public DestinacaoImovelDTO(){

    }

}
