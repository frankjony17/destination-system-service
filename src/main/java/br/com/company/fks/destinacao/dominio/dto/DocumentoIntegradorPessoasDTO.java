package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


public class DocumentoIntegradorPessoasDTO implements Serializable{
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private List<FormatoArquivoIntegradorDTO> formatoArquivos;
    @Getter
    @Setter
    private Boolean situacao;
    @Getter
    @Setter
    private Boolean pessoaFisica;
    @Getter
    @Setter
    private Boolean pessoaJuridica;
    @Getter
    @Setter
    private Boolean imovel;

}
