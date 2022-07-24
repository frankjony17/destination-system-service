package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.repository.UtilizacaoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 17/11/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UtilizacaoServiceTest {

    @InjectMocks
    private UtilizacaoService utilizacaoService;

    @Mock
    private UtilizacaoRepository utilizacaoRepository;

    @Mock
    private Utilizacao utilizacao;

    @Before
    public void setUp(){
        when(utilizacaoRepository.save(any(Utilizacao.class))).thenReturn(utilizacao);
    }

    @Test
    public void salvar() throws Exception {
        Utilizacao utilizacaoTeste = utilizacaoService.salvar(utilizacao, Boolean.TRUE);
        Assert.assertNotNull(utilizacaoTeste);
    }

    @Test
    public void salvar2() throws Exception {
        Utilizacao utilizacaoTeste = utilizacaoService.salvar(utilizacao, Boolean.FALSE);
        Assert.assertNotNull(utilizacaoTeste);
    }

    @Test
    public void salvarUtilizacaoNull() throws Exception {
        Utilizacao utilizacaoNull = null;
        Utilizacao utilizacaoTeste = utilizacaoService.salvar(utilizacaoNull, Boolean.FALSE);
        Assert.assertNull(utilizacaoTeste);
    }

    @Test
    public void buscarEspecificacoes(){
        when(utilizacaoRepository.buscarEspecificacoes(anyInt())).thenReturn(Collections.emptyList());
        List<String> retorno = utilizacaoService.buscarEspecificacoes(1);
        assertNotNull(retorno);
    }

    @Test
    public void selecionaSemUsoVago(){ utilizacaoService.selecionaSemUsoVago(); }
}