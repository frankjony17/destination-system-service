package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.repository.EnderecoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 17/11/2016.
 */


@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private Endereco endereco;

    @Before
    public void setUp(){
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
    }

    @Test
    public void salvar() throws Exception {
        Endereco enderecoTeste = enderecoService.salvar(null);
        assertNull(enderecoTeste);
    }

    @Test
    public void atualizar(){
        Endereco retorno = enderecoService.atualizar(endereco, endereco);
        assertNotNull(retorno);
    }

}