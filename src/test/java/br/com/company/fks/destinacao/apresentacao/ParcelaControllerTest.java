package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ParcelaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.ParcelaService;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by diego.mendes on 22/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParcelaControllerTest {

    @InjectMocks
    private ParcelaController parcelaController;

    @Mock
    private ParcelaService parcelaService;

    @Mock
    private ParcelaDTO parcelaDTO;

    @Mock
    private Parcela parcela;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private ResponseEntity<Resposta<String>> responseEntityString;

    @Mock
    private List<ParcelaDTO> parcelaDTOS;

    @Mock
    private Resposta<List<ParcelaDTO>> listRespostaParcelaDTO;

    @Mock
    private ResponseEntity<Resposta<List<ParcelaDTO>>> respostaResponseEntityParcelaDTO;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void salvar(){
        when(parcelaService.criarNovasParcelas(asList(parcelaDTO))).thenReturn(asList(parcela));
        when(mensagemNegocio.get(anyString())).thenReturn(anyString());
        responseEntityString =  parcelaController.salvar(asList(parcelaDTO));
        assertTrue(responseEntityString.getStatusCode() == HttpStatus.CREATED);
    }

    @Test
    public void excluirParcela() throws Exception{
        when(parcelaDTOS.get(Constants.ZERO)).thenReturn(parcelaDTO);
        when(parcelaDTOS.get(Constants.UM)).thenReturn(parcelaDTO);
        when(entityConverter.converterStrict(any(ParcelaDTO.class), eq(Parcela.class))).thenReturn(parcela);
        when(entityConverter.converterStrict(any(ParcelaDTO.class), eq(Parcela.class))).thenReturn(parcela);
        doNothing().when(parcelaService).excluirParcela(any(Parcela.class),any(Parcela.class),anyString());
        responseEntityString = parcelaController.excluirParcela(parcelaDTOS);
        assertEquals(responseEntityString.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void buscarParcelasSemUtilizacao(){
        when(parcelaService.buscarParcelasCanceladas(anyString())).thenReturn(parcelaDTOS);
        respostaResponseEntityParcelaDTO = parcelaController.buscarParcelasSemUtilizacao(anyString(),anyString());
        assertEquals(respostaResponseEntityParcelaDTO.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void buscarParcelas(){
        when(parcelaService.buscarParcelasPorIdImovel("rip")).thenThrow(NegocioException.class);
        respostaResponseEntityParcelaDTO = parcelaController.buscarParcelas(anyString());
        assertEquals(respostaResponseEntityParcelaDTO.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void buscarParcelasCanceladas(){
        when(parcelaService.buscarParcelasCanceladas(anyString())).thenReturn(parcelaDTOS);
        respostaResponseEntityParcelaDTO = parcelaController.buscarParcelasInativas(anyString());
        assertEquals(respostaResponseEntityParcelaDTO.getStatusCode(),HttpStatus.OK);
    }

    @SneakyThrows
    @Test
    public void editar(){
        when(parcelaService.editar(parcelaDTO)).thenReturn(parcelaDTO);
        parcelaController.editar(parcelaDTO);

    }

    @Test
    @SneakyThrows
    public void salvarListaParcelas(){
        when(parcelaService.salvarListaParcelas(parcelaDTOS)).thenReturn(parcelaDTOS);
        parcelaController.salvarListaParcelas(parcelaDTOS);
    }

    @Test
    @SneakyThrows
    public void salvarListaParcelasException(){
        when(parcelaService.salvarListaParcelas(parcelaDTOS)).thenThrow(NegocioException.class);
        parcelaController.salvarListaParcelas(parcelaDTOS);
    }

    @SneakyThrows
    @Test
    public void editarException(){
        when(parcelaService.editar(parcelaDTO)).thenThrow(NegocioException.class);
        parcelaController.editar(parcelaDTO);

    }
}