package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by haillanderson on 07/04/17.
 */


public class SubTipoUtilizacaoDTO implements Serializable{
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private TipoUtilizacao tipoUtilizacao;
}
