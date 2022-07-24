package br.com.company.fks.destinacao.repository.custom.impl;

import br.com.company.fks.destinacao.dominio.entidades.Dominio;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.UnfinishedStubbingException;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.jpa.repository.JpaContext;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class DominioCustomRepositoryImplTest {

    @InjectMocks
    private DominioCustomRepositoryImpl dominioCustomRepository;

    @Mock
    private JpaContext jpaContext;

    @Mock
    private Dominio dominio;

    @Mock
    private List<Dominio> dominioList;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery typedQuery;

    @Test
    public void buscarTodos(){
        when(jpaContext.getEntityManagerByManagedType(any())).thenReturn(entityManager);
        when(entityManager.createQuery(any(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(dominioList);
        dominioCustomRepository.buscarTodos(Dominio.class);
    }

    @Test
    public void buscarPorId(){
        when(jpaContext.getEntityManagerByManagedType(any())).thenReturn(entityManager);
        when(entityManager.find(any(), any())).thenReturn(dominio);
        dominioCustomRepository.buscarPorId(1, Dominio.class);
    }


}
