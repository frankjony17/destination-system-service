package br.com.company.fks.destinacao.builder;

import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 04/01/17.
 */
@RunWith(PowerMockRunner.class)
public class RespostaTest {

    @Mock
    private TipoMoeda tipoMoedaEsperada;

    @Mock
    private TipoMoeda tipoMoeda;

    @Before
    public void setUp() {
        when(tipoMoedaEsperada.getId()).thenReturn(1);
        when(tipoMoedaEsperada.getDescricao()).thenReturn("Real");
        when(tipoMoeda.getId()).thenReturn(1);
        when(tipoMoeda.getDescricao()).thenReturn("Real");
    }

    @Test
    public void getResultadoObjeto() {
        Resposta<TipoMoeda> resposta = new Resposta<>();
        resposta.setResultado(tipoMoeda);
        TipoMoeda tipoMoedaResposta = resposta.getResultado();
        assertEquals(tipoMoedaEsperada.getId(), tipoMoedaResposta.getId());
        assertEquals(tipoMoedaEsperada.getDescricao(), tipoMoedaResposta.getDescricao());
    }

    @Test
    public void getResultadoArray() {
        Resposta<List<TipoMoeda>> resposta = new Resposta<>();
        resposta.setResultado(asList(tipoMoeda));
        List<TipoMoeda> listaTipoMoedaResposta = resposta.getResultado();
        assertFalse(listaTipoMoedaResposta.isEmpty());
        TipoMoeda tipoMoedaResposta = listaTipoMoedaResposta.get(0);
        assertEquals(tipoMoedaEsperada.getId(), tipoMoedaResposta.getId());
        assertEquals(tipoMoedaEsperada.getDescricao(), tipoMoedaResposta.getDescricao());
    }

    @Test
    public void getErros() {
        Resposta<TipoMoeda> resposta = new Resposta<>();
        resposta.setErros(asList("Erro 1", "Erro 2"));
        List<String> errosResposta = resposta.getErros();
        assertFalse(errosResposta.isEmpty());
        assertArrayEquals(asList("Erro 1", "Erro 2").toArray(), errosResposta.toArray());
    }

    @Test
    public void getMensagens() {
        Resposta<TipoMoeda> resposta = new Resposta<>();
        resposta.setMensagens(asList("Msg 1", "Msg 2"));
        List<String> mensagensResposta = resposta.getMensagens();
        assertFalse(mensagensResposta.isEmpty());
        assertArrayEquals(asList("Msg 1", "Msg 2").toArray(), mensagensResposta.toArray());
    }

    @Test
    public void getTotalElementos() {
        Resposta<TipoMoeda> resposta = new Resposta<>();
        resposta.setTotalElementos(1L);
        assertEquals(new Long(1), resposta.getTotalElementos());
    }

    @Test
    public void getTotalPaginas() {
        Resposta<TipoMoeda> resposta = new Resposta<>();
        resposta.setTotalPaginas(1);
        assertEquals(new Integer(1), resposta.getTotalPaginas());
    }

}
