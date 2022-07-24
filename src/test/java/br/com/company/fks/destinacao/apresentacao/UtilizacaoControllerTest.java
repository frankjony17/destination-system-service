package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.service.UtilizacaoService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 18/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UtilizacaoControllerTest {

    @InjectMocks
    private UtilizacaoController utilizacaoController;

    @Mock
    private UtilizacaoService utilizacaoService;

    @Mock
    private List<String> listaEpecificacoes;


    @Test
    @SneakyThrows
    public void buscarEspecificacoes(){
        when(utilizacaoService.buscarEspecificacoes(anyInt())).thenReturn(listaEpecificacoes);
        ResponseEntity<Resposta<List<String>>> resposta;
        resposta = utilizacaoController.buscarEspecificacoes(1);
        assertNotNull(resposta);
        assertTrue(resposta.getStatusCode() == HttpStatus.OK);
    }

}