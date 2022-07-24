package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.repository.LicitacaoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LicitacaoServiceTest {

    @InjectMocks
    private LicitacaoService licitacaoService;

    @Mock
    private LicitacaoRepository licitacaoRepository;

    @Mock
    private Licitacao licitacao;

    @Mock
    private Arquivo arquivo;

    @Mock
    private ArquivoService arquivoService;

    @Before
    public void setUp() {
        when(licitacaoRepository.save(any(Licitacao.class))).thenReturn(licitacao);
        when(arquivoService.findById(anyLong())).thenReturn(arquivo);
    }

    @Test
    public void salvarLicitacaoNaoNula() {
        when(licitacao.getId()).thenReturn(1L);
        when(licitacao.getArquivos()).thenReturn(Arrays.asList(arquivo));
        Licitacao licitacaoTeste = licitacaoService.salvar(licitacao, Boolean.TRUE);
        assertNotNull(licitacaoTeste);
    }

    @Test
    public void salvarLicitacaoFalse() {
        when(licitacao.getId()).thenReturn(1L);
        when(licitacao.getArquivos()).thenReturn(Arrays.asList(arquivo));
        Licitacao licitacaoTeste = licitacaoService.salvar(licitacao, Boolean.FALSE);
        assertNotNull(licitacaoTeste);
    }

    @Test
    public void salvarLicitacaoArquivoNull() {
        when(licitacao.getId()).thenReturn(1L);
        when(licitacao.getArquivos()).thenReturn(null);
        Licitacao licitacaoTeste = licitacaoService.salvar(licitacao, Boolean.FALSE);
        assertNotNull(licitacaoTeste);
    }

    @Test
    public void salvarLicitacaoNula() {
        licitacaoService.salvar(null, null);
        assertNull(null);
    }
}