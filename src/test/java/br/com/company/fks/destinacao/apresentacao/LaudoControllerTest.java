package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.LaudoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LaudoControllerTest {

    @InjectMocks
    private LaudoController laudoController;

    @Test
    public void buscarLaudo(){
        ResponseEntity<Resposta<List<LaudoDTO>>> respostaResponseEntity = laudoController.buscarLaudo("rips");
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }
}
