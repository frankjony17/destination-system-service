package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.RequerimentoDestinacaoDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.AtendimentoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


/**
 * Created by Basis on 01/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AtendimentoControllerTest {

    private static final String NUMERO_ATENDIMENTO = "DF00002/2016";
    private static final String NUMERO_SEI = "123";

    @InjectMocks
    private AtendimentoController atendimentoController;

    @Mock
    private AtendimentoService atendimentoService;


    @Before
    public void setUp() throws Exception {
        when(atendimentoService.findRequerimentoByNumeroAtendimento(anyString())).thenReturn(criarRequerimentoDestinacao());
        when(atendimentoService.findRequerimentoByNumeroProcesso(anyString())).thenReturn(criarRequerimentoDestinacao());
        when(atendimentoService.verificarNumeroProcedimentoSei(anyLong(),anyString())).thenReturn(Boolean.TRUE);
        when(atendimentoService.verificarNumeroProcedimentoSei(anyLong(),anyString())).thenReturn(Boolean.TRUE);
    }

    @Test
    public void findRequerimentoByNumeroAtendimento() throws Exception {
        ResponseEntity <Resposta<RequerimentoDestinacaoDTO>> responseEntity = atendimentoController.findRequerimentoByNumeroAtendimento(NUMERO_ATENDIMENTO);
        Resposta<RequerimentoDestinacaoDTO> resposta = responseEntity.getBody();
        assertNotNull(resposta);
        assertEquals(NUMERO_ATENDIMENTO, resposta.getResultado().getNumeroAtendimento());
    }

    @Test
    public void findRequerimentoByNumeroAtendimentoGerandoExcecao() throws Exception {
        when(atendimentoService.findRequerimentoByNumeroAtendimento(anyString())).thenThrow(IntegracaoException.class);
        ResponseEntity <Resposta<RequerimentoDestinacaoDTO>> responseEntity = atendimentoController.findRequerimentoByNumeroAtendimento(NUMERO_ATENDIMENTO);
        Resposta<RequerimentoDestinacaoDTO> resposta = responseEntity.getBody();
        assertNotNull(resposta.getErros());
        assertNotNull(resposta.getMensagens());
    }

    @Test
    public void findRequerimentoByNumeroProcesso() throws Exception {
        ResponseEntity<Resposta<RequerimentoDestinacaoDTO>> respostaResponseEntity = atendimentoController.findRequerimentoByNumeroProcesso(NUMERO_SEI);
        Resposta<RequerimentoDestinacaoDTO> resposta = respostaResponseEntity.getBody();
        assertNotNull(resposta);
        assertEquals(NUMERO_SEI,resposta.getResultado().getNumeroProcedimento());
    }

    @Test
    public void findRequerimentoByNumeroProcessoGerandoExcecao() throws Exception {
        when(atendimentoService.findRequerimentoByNumeroProcesso(anyString())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<RequerimentoDestinacaoDTO>> respostaResponseEntity = atendimentoController.findRequerimentoByNumeroProcesso(NUMERO_SEI);
        Resposta<RequerimentoDestinacaoDTO> resposta = respostaResponseEntity.getBody();
        assertNotNull(resposta.getErros());
        assertNotNull(resposta.getMensagens());
    }

    @Test
    public void verificarNumeroSEI() throws Exception {
        ResponseEntity<Resposta<Boolean>> respostaResponseEntity = atendimentoController.verificarNumeroSEI(1L,"");
        Resposta<Boolean> resposta = respostaResponseEntity.getBody();
        assertNotNull(resposta);
        assertEquals(Boolean.TRUE,resposta.getResultado().booleanValue());
    }

    @Test
    public void verificarNumeroSEIGerandoExcecao() throws Exception {
        when(atendimentoService.verificarNumeroProcedimentoSei(anyLong(),anyString())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<Boolean>> respostaResponseEntity = atendimentoController.verificarNumeroSEI(1L,"");
        Resposta<Boolean> resposta = respostaResponseEntity.getBody();
        assertNotNull(resposta.getErros());
        assertNotNull(resposta.getMensagens());
    }


    private RequerimentoDestinacaoDTO criarRequerimentoDestinacao (){
        RequerimentoDestinacaoDTO requerimentoDestinacaoDTO = new RequerimentoDestinacaoDTO();
        requerimentoDestinacaoDTO.setNumeroAtendimento(NUMERO_ATENDIMENTO);
        requerimentoDestinacaoDTO.setNumeroProcedimento(NUMERO_SEI);
        return requerimentoDestinacaoDTO;
    }

}