package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import br.com.company.fks.destinacao.repository.TipoUtilizacaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 19/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TipoUtilizacaoServiceTest {

    @InjectMocks
    private TipoUtilizacaoService tipoUtilizacaoService;

    @Mock
    private TipoUtilizacaoRepository tipoUtilizacaoRepository;

    @Mock
    private List<TipoUtilizacao> tipoUtilizacaoList;

    @Test
    public void buscarTodosTiposUtilizacao(){
        when(tipoUtilizacaoRepository.findAll()).thenReturn(tipoUtilizacaoList);
        List<TipoUtilizacao> retorno = tipoUtilizacaoService.buscarTodosTiposUtilizacaoAtivos();
        assertNotNull(retorno);
    }

}