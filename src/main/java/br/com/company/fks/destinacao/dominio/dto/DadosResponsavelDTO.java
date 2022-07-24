package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoRepresentacaoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


public class DadosResponsavelDTO implements Serializable{
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private TipoPosseOcupacaoEnum tipoPosseOcupacao;
    @Getter
    @Setter
    private TipoRepresentacaoEnum tipoRepresentacao;
    @Getter
    @Setter
    private Integer qtdResponsaveis;
    @Getter
    @Setter
    private String cnpj;
    @Getter
    @Setter
    private String codigoUg;
    @Getter
    @Setter
    private String nomeEntiade;
    @Getter
    @Setter
    private EnderecoCorrespondenciaDTO enderecoCorrespondencia;
    @Getter
    @Setter
    private List<ResponsavelDTO> responsaveis;
    @Getter
    @Setter
    private IntervenienteDTO interveniente;
}
