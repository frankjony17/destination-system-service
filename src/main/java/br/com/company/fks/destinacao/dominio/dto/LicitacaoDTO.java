package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */

public class LicitacaoDTO extends InformacaoPublicacaoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private DominioDTO tipoLicitacao;
    @Getter
    @Setter
    private String numeroProcesso;
    @Getter
    @Setter
    private List<Arquivo> arquivos;
}
