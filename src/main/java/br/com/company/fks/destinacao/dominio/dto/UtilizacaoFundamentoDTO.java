package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
public class UtilizacaoFundamentoDTO implements Serializable{
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private TipoUtilizacaoDTO tipoUsoPermitido;
    @Getter
    @Setter
    private List<TipoEspecificacaoDTO> especificacoes;


}
