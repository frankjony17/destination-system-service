package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Classe RequerimentoDestinacaoDTO que implementa Serializable
 */

public class RequerimentoDestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private String numeroAtendimento;
    @Getter
    @Setter
    private String numeroProcedimento;
    @Getter
    @Setter
    private String nomeRequerimento;
    @Getter
    @Setter
    private String urlArquivo;
    @Getter
    @Setter
    private RequerenteDTO requerente;
    @Getter
    @Setter
    private ImovelUsoDTO imovelUso;
    @Getter
    @Setter
    private Long idRequerimento;

}
