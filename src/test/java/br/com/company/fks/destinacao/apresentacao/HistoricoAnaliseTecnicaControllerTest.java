package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.HistoricoAnaliseTecnicaDTO;
import br.com.company.fks.destinacao.repository.HistoricoAnaliseTecnicaRepository;
import br.com.company.fks.destinacao.service.HistoricoAnaliseTecnicaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 02/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class HistoricoAnaliseTecnicaControllerTest {

    @InjectMocks
    private HistoricoAnaliseTecnicaController historicoAnaliseTecnicaController;

    @Mock
    private HistoricoAnaliseTecnicaService historicoAnaliseTecnicaService;

    @Mock
    private Page<HistoricoAnaliseTecnicaDTO> historicosDtos;

    @Mock
    private HistoricoAnaliseTecnicaDTO historicoAnaliseTecnicaDTO;

    @Mock
    private HistoricoAnaliseTecnicaRepository historicoAnaliseTecnicaRepository;

    @Test
    public void getHistorico(){
        when(historicoAnaliseTecnicaService.findByAnaliseTecnicaId(anyLong(), anyInt(), anyInt())).thenReturn(historicosDtos);

        ResponseEntity<Resposta<Page<HistoricoAnaliseTecnicaDTO>>> respostaResponseEntity = historicoAnaliseTecnicaController.getHistorico(1l, 1,1);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

}