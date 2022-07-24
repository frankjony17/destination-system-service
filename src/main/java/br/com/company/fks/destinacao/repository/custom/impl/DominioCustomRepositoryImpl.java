package br.com.company.fks.destinacao.repository.custom.impl;

import br.com.company.fks.destinacao.dominio.entidades.Dominio;
import br.com.company.fks.destinacao.repository.custom.DominioCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public class DominioCustomRepositoryImpl implements DominioCustomRepository {
    @Autowired
    private JpaContext jpaContext;

    @Override
    public List<Dominio> buscarTodos(Class classe) {
        String jpql = "from " + classe.getName() + " t order by t.descricao";
        EntityManager em = jpaContext.getEntityManagerByManagedType(classe);
        TypedQuery typedQuery = em.createQuery(jpql, classe);
        return typedQuery.getResultList();
    }

    @Override
    public Dominio buscarPorId(Integer id, Class classe) {
        EntityManager em = jpaContext.getEntityManagerByManagedType(classe);
        return (Dominio) em.find(classe, id);
    }
}
