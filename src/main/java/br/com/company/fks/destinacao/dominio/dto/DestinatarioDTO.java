package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


public class DestinatarioDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String cnpj;
    @Getter
    @Setter
    private String nomeEmpresarial;
    @Getter
    @Setter
    private String autarquiaFundacao;
    @Getter
    @Setter
    private EnderecoCorrespondenciaDTO enderecoCorrespondencia;
    @Getter
    @Setter
    private List<TelefoneDTO> telefones;
}
