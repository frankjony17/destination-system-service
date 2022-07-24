package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created by diego on 02/03/17.
 */

public class ParcelaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String sequencial;
    @Getter
    @Setter
    private List<BenfeitoriaDTO> benfeitorias;
    @Getter
    @Setter
    private List<BenfeitoriaDTO> listaBenfeitorias;
    @Getter
    @Setter
    private BigDecimal areaTerreno;
    @Getter
    @Setter
    private BigDecimal areaDisponivel;
    @Getter
    @Setter
    private Boolean ativa;
    @Getter
    @Setter
    private String memorialDescritivo;
    @Getter
    @Setter
    private List<ArquivoDTO> arquivos;
    @Getter
    @Setter
    private List<ArquivoDTO> arquivosExcluir;
    @Getter
    @Setter
    private Long idImovel;
    @Getter
    @Setter
    private String rip;
    @Getter
    @Setter
    private Set<Long> idDestinacaoImoveis;
    @Getter
    @Setter
    private String utilizacao;
    @Getter
    @Setter
    private Long idParcelaInativar;
    @Getter
    @Setter
    private Boolean utilizada;
    @Getter
    @Setter
    private String descParcela;
    @Getter
    @Setter
    private Boolean parcelacomPendenciaDestinacao;



}
