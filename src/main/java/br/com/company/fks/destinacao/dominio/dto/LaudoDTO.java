package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


public class LaudoDTO implements Serializable {
    @Getter
    @Setter
    private ImovelDTO imovel;
    @Getter
    @Setter
    private BigDecimal valorLaudo;
    @Getter
    @Setter
    private String assinaturaLaudo;
    @Getter
    @Setter
    private String arquivosLaudo;
    @Getter
    @Setter
    private BigDecimal valorVenda;
}

