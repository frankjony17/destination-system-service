package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.dto.LocalizacaoEctDTO;
import br.com.company.fks.destinacao.dominio.dto.MunicipioDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.LocalidadeService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 02/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class LocalidadeControllerTest {

    @InjectMocks
    private LocalidadeController localidadeController;

    @Mock
    private LocalidadeService localidadeService;

    @Mock
    private LocalizacaoEctDTO localizacaoEctDTO;

    @Mock
    private MunicipioDTO municipioDTO;

    @Test
    @SneakyThrows
    public void listarEnderecoPorCep(){
        when(localidadeService.findEnderecoByCep(anyString())).thenReturn(localizacaoEctDTO);
        ResponseEntity<Resposta<LocalizacaoEctDTO>> respostaResponseEntity = localidadeController.listarEnderecoPorCep("84975648");
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void listarEnderecoPorCepDisparandoException(){
        when(localidadeService.findEnderecoByCep(anyString())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<LocalizacaoEctDTO>> respostaResponseEntity = localidadeController.listarEnderecoPorCep("84975648");
        assertNotNull(respostaResponseEntity);
    }

    @Test
    @SneakyThrows
    public void buscarMunicipioPorUf() {
        when(localidadeService.findMunicipioByUF(anyString())).thenReturn(Arrays.asList(municipioDTO));
        ResponseEntity<Resposta<List<MunicipioDTO>>> respostaResponseEntity = localidadeController.buscarMunicipioPorUf("DF");
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarMunicipioPorUfDiparandoexception(){
        when(localidadeService.findMunicipioByUF(anyString())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<List<MunicipioDTO>>> respostaResponseEntity = localidadeController.buscarMunicipioPorUf("DF");
        assertNotNull(respostaResponseEntity);
    }

    @Test
    public void buscarPaises(){
        ResponseEntity<Resposta<List<DominioDTO>>> respostaResponseEntity = localidadeController.buscarPaises();
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

}