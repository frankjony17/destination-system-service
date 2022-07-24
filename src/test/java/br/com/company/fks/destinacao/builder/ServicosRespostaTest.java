package br.com.company.fks.destinacao.builder;

import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 04/01/17.
 */

@RunWith(PowerMockRunner.class)
public class ServicosRespostaTest {

    @Mock
    private TipoMoeda tipoMoeda;

    @Mock
    private TipoMoeda tipoMoedaEsperada;

    @Before
    public void setUp() {
        when(tipoMoedaEsperada.getId()).thenReturn(1);
        when(tipoMoedaEsperada.getDescricao()).thenReturn("Real");
        when(tipoMoeda.getId()).thenReturn(1);
        when(tipoMoeda.getDescricao()).thenReturn("Real");
    }



    @Test
    public void getResultadoObjeto() {
        ServicosResposta servicosResposta = criarServicoResposta(tipoMoeda, null);
        assertFalse(servicosResposta.getResposta().isEmpty());
        TipoMoeda tipoMoedaResposta = (TipoMoeda) servicosResposta.getResposta().get(0);
        assertEquals(tipoMoedaEsperada.getId(), tipoMoedaResposta.getId());
        assertEquals(tipoMoedaEsperada.getDescricao(), tipoMoedaResposta.getDescricao());
    }

    @Test
    public void getResultadoList() {
        ServicosResposta servicosResposta = criarServicoResposta(asList(tipoMoeda), null);
        assertFalse(servicosResposta.getResposta().isEmpty());
        List<Object> listaResposta = servicosResposta.getResposta();
        TipoMoeda tipoMoedaResposta = (TipoMoeda) listaResposta.get(0);
        assertEquals(tipoMoedaEsperada.getId(), tipoMoedaResposta.getId());
        assertEquals(tipoMoedaEsperada.getDescricao(), tipoMoedaResposta.getDescricao());
    }

    @Test
    public void getResultadoArray() {
        TipoMoeda [] tipoMoedaArray = new TipoMoeda[] {tipoMoeda};
        ServicosResposta servicosResposta = criarServicoResposta(tipoMoedaArray, null);
        assertFalse(servicosResposta.getResposta().isEmpty());
    }

    @Test
    public void getResultadoSet() {
        Set<TipoMoeda> tipoMoedas = new HashSet<>(asList(tipoMoeda));
        ServicosResposta servicosResposta = criarServicoResposta(tipoMoedas, null);
        assertFalse(servicosResposta.getResposta().isEmpty());
        List<Object> listaResposta = servicosResposta.getResposta();
        TipoMoeda tipoMoedaResposta = (TipoMoeda) listaResposta.get(0);
        assertEquals(tipoMoedaEsperada.getId(), tipoMoedaResposta.getId());
        assertEquals(tipoMoedaEsperada.getDescricao(), tipoMoedaResposta.getDescricao());
    }

    @Test
    public void getMensagem() {
        ServicosResposta servicosResposta = criarServicoResposta(tipoMoeda, "Msg 1");
        assertFalse(servicosResposta.getMensagem().isEmpty());
        List<Object> listaMensagem = servicosResposta.getMensagem();
        String mensagem = (String) listaMensagem.get(0);
        assertEquals("Msg 1", mensagem);
    }

    @Test
    public void setResposta() {
        ServicosResposta servicosResposta = new ServicosResposta.Builder().build();
        servicosResposta.setResposta(asList(tipoMoeda));
        assertFalse(servicosResposta.getResposta().isEmpty());
        TipoMoeda tipoMoedaResposta = (TipoMoeda) servicosResposta.getResposta().get(0);
        assertEquals(tipoMoedaEsperada.getId(), tipoMoedaResposta.getId());
        assertEquals(tipoMoedaEsperada.getDescricao(), tipoMoedaResposta.getDescricao());
    }

    @Test
    public void setMensagem() {
        ServicosResposta servicosResposta = new ServicosResposta.Builder().build();
        servicosResposta.setMensagem(asList("Msg 1"));
        assertFalse(servicosResposta.getMensagem().isEmpty());
        List<Object> listaMensagem = servicosResposta.getMensagem();
        String mensagem = (String) listaMensagem.get(0);
        assertEquals("Msg 1", mensagem);
    }

    private ServicosResposta criarServicoResposta(Object resultado, Object mensagem) {
        ServicosResposta servicosResposta =
                new ServicosResposta.Builder().resultado(resultado).mensagem(mensagem).build();
        return servicosResposta;
    }

}
