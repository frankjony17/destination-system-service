package br.com.company.fks.destinacao.apresentacao.builder;

import br.com.company.fks.destinacao.dominio.dto.DominioDTO;

/**
 * Created by diego on 17/08/17.
 */
public class DominioDTOBuilder {

    private final DominioDTO dominioDTO;

    private DominioDTOBuilder(){
        this.dominioDTO = new DominioDTO();
    }

    public static DominioDTOBuilder getBuilder(){
        return new DominioDTOBuilder();
    }

    public DominioDTO build(){
        return this.dominioDTO;
    }

    public DominioDTOBuilder setId(Integer id) {
        this.dominioDTO.setId(id);
        return this;
    }

    public DominioDTOBuilder setDescricao(String descricao) {
        this.dominioDTO.setDescricao(descricao);
        return this;
    }

}
