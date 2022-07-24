package br.com.company.fks.destinacao.builder;

import br.com.company.fks.destinacao.dominio.dto.DadosPessoaFisicaDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class RespostaDadosPessoaFisicaTest {
    @Mock
    private DadosPessoaFisicaDTO dadosPessoaFisica;

    @Before
    public void setUp() {
        when(dadosPessoaFisica.getId()).thenReturn(123L);
        when(dadosPessoaFisica.getCpf()).thenReturn("0123456789");
    }

    @Test
    public void getResultadoObjeto() {
        RespostaDadosPessoaFisica resposta = new RespostaDadosPessoaFisica();
        resposta.setResultado(dadosPessoaFisica);
        DadosPessoaFisicaDTO resultado = resposta.getResultado();
        assertEquals(resultado.getCpf(), dadosPessoaFisica.getCpf());
        assertEquals(resultado.getId(), dadosPessoaFisica.getId());
    }

    @Test
    public void getErros() {
        RespostaDadosPessoaFisica resposta = new RespostaDadosPessoaFisica();
        resposta.setErros(asList("Erro 1", "Erro 2"));
        List<String> errosResposta = resposta.getErros();
        assertFalse(errosResposta.isEmpty());
        assertArrayEquals(asList("Erro 1", "Erro 2").toArray(), errosResposta.toArray());
    }

    @Test
    public void getMensagens() {
        RespostaDadosPessoaFisica resposta = new RespostaDadosPessoaFisica();
        resposta.setMensagens(asList("Msg 1", "Msg 2"));
        List<String> mensagensResposta = resposta.getMensagens();
        assertFalse(mensagensResposta.isEmpty());
        assertArrayEquals(asList("Msg 1", "Msg 2").toArray(), mensagensResposta.toArray());
    }

    @Test
    public void getTotalElementos() {
        RespostaDadosPessoaFisica resposta = new RespostaDadosPessoaFisica();
        resposta.setTotalElementos(1L);
        assertEquals(new Long(1), resposta.getTotalElementos());
    }

    @Test
    public void getTotalPaginas() {
        RespostaDadosPessoaFisica resposta = new RespostaDadosPessoaFisica();
        resposta.setTotalPaginas(1);
        assertEquals(new Integer(1), resposta.getTotalPaginas());
    }

}