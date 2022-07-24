package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Publicacao;
import br.com.company.fks.destinacao.repository.PublicacaoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 29/12/16.
 */
@RunWith(PowerMockRunner.class)
public class PublicacaoServiceTest {

    @InjectMocks
    private PublicacaoService publicacaoService;

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Mock
    private Publicacao publicacao;

    @Before
    public void setUp() {
        when(publicacao.getId()).thenReturn(1L);
        when(publicacaoRepository.save(any(Publicacao.class))).thenReturn(publicacao);
    }

    @Test
    public void salvar() {
        Publicacao publicacaoSalva = publicacaoService.salvar(publicacao);
        assertNotNull(publicacaoSalva);
    }

}
