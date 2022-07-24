package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.ItemVerificacao;
import br.com.company.fks.destinacao.repository.ItemVerificacaoRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 30/12/16.
 */
@RunWith(PowerMockRunner.class)
public class ItemVerificacaoServiceTest {

    @InjectMocks
    private ItemVerificacaoService itemVerificacaoService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private ItemVerificacaoRepository itemVerificacaoRepository;

    @Mock
    private ItemVerificacao itemVerificacao;

    @Mock
    private ItemVerificacaoDTO itemVerificacaoDTO;

    @Mock
    private AnaliseTecnica analiseTecnica;

    @Before
    public void setUp() {
        when(entityConverter.converterStrict(any(ItemVerificacaoDTO.class), eq(ItemVerificacao.class))).thenReturn(itemVerificacao);
        when(itemVerificacaoRepository.save(any(ItemVerificacao.class))).thenReturn(itemVerificacao);
        when(itemVerificacao.getId()).thenReturn(1L);
        when(itemVerificacaoRepository.buscarPorIdAnalise(anyLong())).thenReturn(asList(itemVerificacao));
    }

    @Test
    public void salvar() {

        ItemVerificacao itemVerificacaoSalva = itemVerificacaoService.salvar(itemVerificacaoDTO, analiseTecnica);
        assertNotNull(itemVerificacaoSalva);
    }

    @Test
    public void salvarLista() {
        List<ItemVerificacao> itens = itemVerificacaoService.salvar(asList(itemVerificacaoDTO), analiseTecnica);
        assertNotNull(itens);
        assertFalse(itens.isEmpty());
    }

    @Test
    public void buscarPorIdAnalise() {
        List<ItemVerificacaoDTO> itens = itemVerificacaoService.buscarPorIdAnalise(1L);
        assertNotNull(itens);
        assertFalse(itens.isEmpty());
    }


}
