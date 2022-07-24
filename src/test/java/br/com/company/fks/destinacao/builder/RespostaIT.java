package br.com.company.fks.destinacao.builder;
import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Created by samuel on 17/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
public class RespostaIT {

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
    public void getErros() {
        Resposta<TipoMoeda> resposta = new Resposta<>();
        resposta.setErros(asList("Erro 1", "Erro 2"));
        List<String> errosResposta = resposta.getErros();
        assertArrayEquals(asList("Erro 1", "Erro 2").toArray(), errosResposta.toArray());
        assertFalse(errosResposta.isEmpty());
    }

    @Test
    public void getMensagens() {
        Resposta<TipoMoeda> resposta = new Resposta<>();
        resposta.setMensagens(asList("Msg 1", "Msg 2"));
        List<String> mensagensResposta = resposta.getMensagens();
        assertArrayEquals(asList("Msg 1", "Msg 2").toArray(), mensagensResposta.toArray());
        assertFalse(mensagensResposta.isEmpty());
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
