package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.SubTipoUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.SubTipoUtilizacao;
import br.com.company.fks.destinacao.service.SubTipoUtilizacaoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 18/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class SubTipoUtilizacaoControllerTest {

    @InjectMocks
    private SubTipoUtilizacaoController subTipoUtilizacaoController;

    @Mock
    private SubTipoUtilizacaoService subTipoUtilizacaoService;

    @Mock
    private List<SubTipoUtilizacao> listaSubTipoUtilizacao;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private SubTipoUtilizacao subTipoUtilizacao;

    @Mock
    private TipoUtilizacaoDTO tipoUtilizacaoDTO;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private List<TipoUtilizacaoDTO> tipoUtilizacaoDTOList;

    @Mock
    private Stream<SubTipoUtilizacao> subTipoUtilizacaoStream;

    @Test
    @SneakyThrows
    public void buscarTodosSubTiposUtilizacao(){
        when(subTipoUtilizacaoService.buscarTodosSubTiposUtilizacaoAtivos()).thenReturn(listaSubTipoUtilizacao);
        when(entityConverter.converterListaStrictLazyLoading(listaSubTipoUtilizacao, Type.class)).thenReturn(listaSubTipoUtilizacao);
        ResponseEntity<Resposta<List<SubTipoUtilizacaoDTO>>> resposta;
        resposta = subTipoUtilizacaoController.buscarTodosSubTiposUtilizacaoAtivos();
        assertNotNull(resposta);
        assertTrue(resposta.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarTodosSubTiposUtilizacaoAtivosIntegrador(){
        when(subTipoUtilizacaoService.buscarTodosSubTiposUtilizacaoAtivos()).thenReturn(listaSubTipoUtilizacao);
        when(entityConverter.converterListaStrictLazyLoading(listaSubTipoUtilizacao, Type.class)).thenReturn(listaSubTipoUtilizacao);
        ResponseEntity<Resposta<List<SubTipoUtilizacaoDTO>>> resposta;
        resposta = subTipoUtilizacaoController.buscarTodosSubTiposUtilizacaoAtivosIntegrador(12);
        assertNotNull(resposta);
        assertTrue(resposta.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarTodosSubTiposUtilizacaoAtivosAfetacao(){
        List<SubTipoUtilizacao> listaSubTipoUtilizacao2 = new ArrayList<>();
        List<TipoUtilizacaoDTO> tipoUtilizacaoDTOList = new ArrayList<>();
        tipoUtilizacaoDTOList.add(tipoUtilizacaoDTO);
        listaSubTipoUtilizacao2.add(subTipoUtilizacao);
        when(tipoUtilizacaoDTO.getId()).thenReturn(1L);
        when(subTipoUtilizacaoService.buscarFiltrando(anyInt())).thenReturn(listaSubTipoUtilizacao2);
        when(subTipoUtilizacaoStream.collect(Collectors.toList())).thenReturn(listaSubTipoUtilizacao2);
        when(subTipoUtilizacao.getDescricao()).thenReturn("descri");
        when(subTipoUtilizacaoStream.filter(any(Predicate.class))).thenReturn(subTipoUtilizacaoStream);
        when(subTipoUtilizacaoStream.collect(Collectors.toList())).thenReturn(listaSubTipoUtilizacao2);
        subTipoUtilizacaoController.buscarTodosSubTiposUtilizacaoAtivosAfetacao(tipoUtilizacaoDTOList);
    }
}