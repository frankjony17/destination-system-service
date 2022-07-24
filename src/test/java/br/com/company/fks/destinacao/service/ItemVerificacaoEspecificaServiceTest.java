package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoEspecificoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.ItemVerificacaoEspecifica;
import br.com.company.fks.destinacao.repository.ItemVerificacaoEspecificaRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by diego on 30/12/16.
 */
@RunWith(PowerMockRunner.class)
public class
ItemVerificacaoEspecificaServiceTest {

    @InjectMocks
    private ItemVerificacaoEspecificaService itemVerificacaoEspecificaService;

    @Mock
    private ItemVerificacaoEspecificaRepository itemVerificacaoEspecificaRepository;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private ItemVerificacaoEspecificoDTO itemVerificacaoEspecificoDTO;

    @Mock
    private ItemVerificacaoEspecifica itemVerificacaoEspecifica;

    @Mock
    private AnaliseTecnica analiseTecnica;

    @Before
    public void setUp() {
        when(entityConverter.converterStrict(any(ItemVerificacaoEspecificoDTO.class), eq(ItemVerificacaoEspecifica.class))).thenReturn(itemVerificacaoEspecifica);
        when(itemVerificacaoEspecificaRepository.save(any(ItemVerificacaoEspecifica.class))).thenReturn(itemVerificacaoEspecifica);
        when(itemVerificacaoEspecifica.getId()).thenReturn(1L);
        when(entityConverter.converterStrict(any(ItemVerificacaoEspecifica.class), eq(ItemVerificacaoEspecificoDTO.class))).thenReturn(itemVerificacaoEspecificoDTO);
        when(itemVerificacaoEspecificaRepository.buscarPorIdAnalise(anyLong())).thenReturn(asList(itemVerificacaoEspecifica));
        when(itemVerificacaoEspecificaRepository.buscarIdsItemVerificacaoEspecificaIdCheckList(anyLong())).thenReturn(asList(new Long(1)));
    }

    @Test
    public void salvar() {
        ItemVerificacaoEspecifica itemVerificacaoEspecificaSalvo =
                itemVerificacaoEspecificaService.salvar(itemVerificacaoEspecificoDTO, analiseTecnica);
        assertNotNull(itemVerificacaoEspecificaSalvo);
        assertEquals(1L, itemVerificacaoEspecificaSalvo.getId().longValue());
    }

    @Test
    public void salvarLista() {
        List<ItemVerificacaoEspecifica> itens =
                itemVerificacaoEspecificaService.salvar(asList(itemVerificacaoEspecificoDTO), analiseTecnica);
        assertNotNull(itens);
    }

    @Test
    public void buscarPorIdAnalise() {
        List<ItemVerificacaoEspecificoDTO> itens = itemVerificacaoEspecificaService.buscarPorIdAnalise(1L);
        assertNotNull(itens);
        assertFalse(itens.isEmpty());
    }

    @Test
    public void isUsadoAnaliseTecnica() {
        Boolean usado = itemVerificacaoEspecificaService.isUsadoAnaliseTecnica(1L);
        assertTrue(usado);
    }


}
