package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.service.ItemVerificacaoEspecificaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by guilherme on 02/03/17.
 */

@RunWith(PowerMockRunner.class)
public class ItemVerificacaoEspecificoControllerTest {

    @InjectMocks
    private ItemVerificacaoEspecificoController itemVerificacaoEspecificoController;

    @Mock
    private ItemVerificacaoEspecificaService itemVerificacaoCheckListService;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void verificarUsoAnaliseTecnica() throws Exception {
        when(itemVerificacaoCheckListService.isUsadoAnaliseTecnica(anyLong())).thenReturn(true);
        ResponseEntity<Resposta<Boolean>> resposta =
                itemVerificacaoEspecificoController.verificarUsoAnaliseTecnica(1l);
        assertNotNull(resposta);
    }


}