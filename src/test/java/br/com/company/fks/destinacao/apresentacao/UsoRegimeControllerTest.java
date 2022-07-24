package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.ImovelUsoDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.UsoImovelService;
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
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 02/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UsoRegimeControllerTest {

    @InjectMocks
    private UsoRegimeController usoRegimeController;

    @Mock
    private UsoImovelService usoImovelService;

    @Mock
    private List<ImovelUsoDTO> imovelUsoDTOList;

    @Test
    @SneakyThrows
    public void findAll(){
        when(usoImovelService.getUsoImoveis()).thenReturn(imovelUsoDTOList);

        ResponseEntity<Resposta<List<ImovelUsoDTO>>> respostaResponseEntity = usoRegimeController.findAll();
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void findAllDisparandoException(){
        when(usoImovelService.getUsoImoveis()).thenThrow(IntegracaoException.class);

        ResponseEntity<Resposta<List<ImovelUsoDTO>>> respostaResponseEntity = usoRegimeController.findAll();
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

}