package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.TipoUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import br.com.company.fks.destinacao.service.TipoUtilizacaoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Ignore;
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
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 18/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TipoUtilizacaoControllerTest {

    @InjectMocks
    private TipoUtilizacaoController tipoUtilizacaoController;

    @Mock
    private TipoUtilizacaoService tipoUtilizacaoService;

    @Mock
    private List<TipoUtilizacao> listaTipoUtilizacao;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private TipoUtilizacao tipoUtilizacao;

    @Mock
    private Stream<TipoUtilizacao> tipoUtilizacaoStream;


    @Test
    @SneakyThrows
    public void buscarTodosTiposUtilizacao() {
        when(tipoUtilizacaoService.buscarTodosTiposUtilizacaoAtivos()).thenReturn(listaTipoUtilizacao);
        when(entityConverter.converterListaStrictLazyLoading(listaTipoUtilizacao, Type.class)).thenReturn(listaTipoUtilizacao);
        ResponseEntity<Resposta<List<TipoUtilizacaoDTO>>> resposta;
        resposta = tipoUtilizacaoController.buscarTodosTiposUtilizacaoAtivos();
        assertNotNull(resposta);
        assertTrue(resposta.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarTodosTiposUtilizacaoAtivosAfetacao(){
        List<TipoUtilizacao> tipoUtilizacaoList = new ArrayList<>();
        tipoUtilizacaoList.add(tipoUtilizacao);
        tipoUtilizacao.setId(1);
        when(tipoUtilizacaoService.buscarTodosTiposUtilizacaoAtivos()).thenReturn(tipoUtilizacaoList);
        when(entityConverter.converterListaStrictLazyLoading(tipoUtilizacaoList, Type.class)).thenReturn(tipoUtilizacaoList);
        when(tipoUtilizacaoStream.collect(Collectors.toList())).thenReturn(tipoUtilizacaoList);
        when(tipoUtilizacao.getDescricao()).thenReturn("descricao");
        when(tipoUtilizacaoStream.filter(any(Predicate.class))).thenReturn(tipoUtilizacaoStream);
        when(tipoUtilizacaoStream.collect(Collectors.toList())).thenReturn(tipoUtilizacaoList);
        tipoUtilizacaoController.buscarTodosTiposUtilizacaoAtivosAfetacao();
    }

}