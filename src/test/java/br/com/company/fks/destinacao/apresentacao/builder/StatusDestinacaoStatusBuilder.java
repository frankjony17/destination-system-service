/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.fks.destinacao.apresentacao.builder;

import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;

/**
 *
 * @author diego
 */
public class StatusDestinacaoStatusBuilder {
    
    private final StatusDestinacao statusDestinacao;

    private StatusDestinacaoStatusBuilder(){
        this.statusDestinacao = new StatusDestinacao();
    }

    public static StatusDestinacaoStatusBuilder getBuilder(){
        return new StatusDestinacaoStatusBuilder();
    }

    public StatusDestinacao build(){
        return this.statusDestinacao;
    }

    public StatusDestinacaoStatusBuilder setId(Integer id){
        statusDestinacao.setId(id);
        return this;
    }

    public StatusDestinacaoStatusBuilder setTipoLogradouro(String descricao){
        statusDestinacao.setDescricao(descricao);
        return this;
    }
    
}
