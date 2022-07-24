package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.entidades.Dominio;
import br.com.company.fks.destinacao.dominio.entidades.TipoModalidade;
import br.com.company.fks.destinacao.repository.custom.DominioCustomRepository;

import java.lang.reflect.Type;

import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.TypeToken;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 17/11/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class DominioServiceTest {

    @InjectMocks
    private DominioService dominioService;

    @Mock
    private DominioCustomRepository dominioCustomRepository;

    @Mock
    private Dominio dominio;

    @Mock
    private DominioDTO dominioDTO;

    @Mock
    private EntityConverter entityConverter;

    @Before
    public void setUp(){
        Type targetListType = new TypeToken<List<DominioDTO>>() {}.getType();
        when(entityConverter.converterListaStrictLazyLoading(asList(dominio), targetListType)).thenReturn(asList(dominioDTO));
        when(dominioCustomRepository.buscarPorId(anyInt(), any(Class.class))).thenReturn(dominio);
    }

    @Test
    public void buscarTodos() {
        when(dominioCustomRepository.buscarTodos(any(Class.class))).thenReturn(asList(dominio));
        List<DominioDTO> dominioTeste = dominioService.buscarTodos(TipoModalidade.class);
        Assert.assertTrue(!dominioTeste.isEmpty());
    }

    @Test
    public void buscarTodosNull() {
        when(dominioCustomRepository.buscarTodos(any(Class.class))).thenReturn(asList());
        List<DominioDTO> dominioTeste = dominioService.buscarTodos(TipoModalidade.class);
        Assert.assertTrue(dominioTeste.isEmpty());
    }

    @Test
    public void findById() {
        Dominio dominio = dominioService.findById(1, TipoModalidade.class);
        assertNotNull(dominio);
    }

}