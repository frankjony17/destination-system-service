package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaConsultaDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaFisicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaHistoricoCampoDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaJuridicaDTO;
import br.com.company.fks.destinacao.service.DadosPessoaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DadosPessoaControllerTest {

    @InjectMocks
    private DadosPessoaController controller;

    @Mock
    private DadosPessoaService service;

    @Mock
    private DadosPessoaFisicaDTO dadosPessoaFisicaDTO;

    @Mock
    private DadosPessoaHistoricoCampoDTO dadosPessoaHistoricoCampoDTO;

    @Mock
    private DadosPessoaJuridicaDTO dadosPessoaJuridicaDTO;

    @Mock
    private DadosPessoaConsultaDTO dadosPessoaConsultaDTO;

    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        when(dadosPessoaFisicaDTO.getCpf()).thenReturn("0123456789");
        //when(service.buscarPessoaFisica(any(DadosPessoaConsultaDTO.class))).thenReturn(dadosPessoaFisicaDTO);
       // when(service.buscarPessoaJuridica(any(DadosPessoaConsultaDTO.class))).thenReturn(dadosPessoaJuridicaDTO);
        when(service.buscarPessoaFisicaHistoricaCampo(anyString(), anyString())).thenReturn(singletonList(dadosPessoaHistoricoCampoDTO));
        when(service.buscarPessoaJuridicaHistoricaCampo(anyString(), anyString())).thenReturn(singletonList(dadosPessoaHistoricoCampoDTO));
    }

    @Test
    public void buscarDadosPessoaFisica() throws Exception {
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        ResponseEntity<Resposta> responseEntity = controller.buscarDadosPessoaFisica(dadosPessoaConsultaDTO, request);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void buscarDadosPessoaFisicaHistoricoCampo() {
        ResponseEntity<Resposta> entity = controller.buscarDadosPessoaFisicaHistoricoCampo("nome", "0123456789");
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
        List body = (List) entity.getBody().getResultado();
        assertFalse(body.isEmpty());
    }

    @Test
    public void buscarDadosPessoaJuridica() throws Exception {
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        ResponseEntity<Resposta> responseEntity = controller.buscarDadosPessoaJuridica(dadosPessoaConsultaDTO, request);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void buscarDadosPessoaJuridicaHistoricoCampo() {
        ResponseEntity<Resposta> entity = controller.buscarDadosPessoaJuridicaHistoricoCampo("nome", "0123456789");
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
        List body = (List) entity.getBody().getResultado();
        assertFalse(body.isEmpty());
    }

}