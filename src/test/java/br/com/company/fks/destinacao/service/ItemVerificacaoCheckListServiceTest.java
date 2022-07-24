package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoCheckListDTO;
import br.com.company.fks.destinacao.repository.ItemVerificacaoCheckListRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 02/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemVerificacaoCheckListServiceTest {

    @InjectMocks
    private ItemVerificacaoCheckListService itemVerificacaoCheckListService;

    @Mock
    private ItemVerificacaoCheckListRepository itemVerificacaoCheckListRepository;

    @Mock
    private ItemVerificacaoCheckListDTO itemVerificacaoCheckListDTO;

    @Before
    public void setUp() {
        when(itemVerificacaoCheckListRepository.findByIdFundamentoLegal(anyLong()))
                .thenReturn(asList(itemVerificacaoCheckListDTO));
    }

    @Test
    public void findByIdFundamentoLegal() {
        List<ItemVerificacaoCheckListDTO> itemVerificacaoCheckListDTOs = itemVerificacaoCheckListService.findByIdFundamentoLegal(1L);
        assertTrue(!itemVerificacaoCheckListDTOs.isEmpty());
        assertNotNull(itemVerificacaoCheckListDTOs);
    }
}
