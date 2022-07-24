package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoCheckListDTO;
import br.com.company.fks.destinacao.service.ItemVerificacaoCheckListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by guilherme on 02/03/17.
 */

@RunWith(PowerMockRunner.class)
public class ItemVerificacaoCheckListControllerTest {

    @InjectMocks
    private ItemVerificacaoCheckListController itemVerificacaoCheckListController;

    @Mock
    private ItemVerificacaoCheckListService itemVerificacaoCheckListService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void listarItemsPorFundamento() throws Exception {
        when(itemVerificacaoCheckListService.findByIdFundamentoLegal(anyLong())).thenReturn(criarListaCheckList());
        ResponseEntity<Resposta<List<ItemVerificacaoCheckListDTO>>> resposta =
                itemVerificacaoCheckListController.listarItemsPorFundamento(1l);

        assertNotNull(resposta);
    }


    private List<ItemVerificacaoCheckListDTO> criarListaCheckList(){
        ItemVerificacaoCheckListDTO item = new ItemVerificacaoCheckListDTO();

        item.setId(1l);
        item.setDescricao("Teste");
        item.setCodFundamentoLegal(1L);

        return asList(item);
    }


}