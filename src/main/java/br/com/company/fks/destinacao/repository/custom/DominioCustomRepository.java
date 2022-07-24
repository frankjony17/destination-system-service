package br.com.company.fks.destinacao.repository.custom;

import br.com.company.fks.destinacao.dominio.entidades.Dominio;

import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
public interface DominioCustomRepository {

    /**
     * Método que retorna uma lista com todos os domínios
     * @param clazz
     * @return
     */
    List<Dominio> buscarTodos(Class clazz);

    /**
     * Método que busca um domínio por id
     * @param id
     * @param classe
     * @return Domínio encontrado pelo id
     */
    Dominio buscarPorId(Integer id, Class classe);

}
