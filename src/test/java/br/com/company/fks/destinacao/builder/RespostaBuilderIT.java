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
public class RespostaBuilderIT {

    private Resposta<TipoMoeda> resposta;

    @Mock
    private TipoMoeda tipoMoedaEsperada;

    @Mock
    private TipoMoeda tipoMoeda;

    @Before
    public void setUp() {
        when(tipoMoedaEsperada.getId()).thenReturn(1);
        when(tipoMoedaEsperada.getDescricao()).thenReturn("Real");
        when(tipoMoeda.getDescricao()).thenReturn("Real");
        when(tipoMoeda.getId()).thenReturn(1);


        resposta = RespostaBuilder.getBuilder()
                .setResultado(tipoMoeda)
                .setErro("Erro 1")
                .setErros(asList("Erro 2"))
                .setMensagen("Msg 1")
                .setMensagens(asList("Msg 2"))
                .setTotalElementos(1L)
                .setTotalPaginas(1)
                .build();

    }

    @Test
    public void getErros() {
        List<String> errosResposta = resposta.getErros();
        assertFalse(errosResposta.isEmpty());
        assertArrayEquals(asList("Erro 1", "Erro 2").toArray(), errosResposta.toArray());
    }

    @Test
    public void getMensagens() {
        List<String> mensagensResposta = resposta.getMensagens();
        assertFalse(mensagensResposta.isEmpty());
        assertArrayEquals(asList("Msg 1", "Msg 2").toArray(), mensagensResposta.toArray());
    }

    @Test
    public void getTotalElementos() {
        assertEquals(new Long(1), resposta.getTotalElementos());
    }

    @Test
    public void getPagina() {
        assertEquals(new Integer(1), resposta.getTotalPaginas());
    }

}
