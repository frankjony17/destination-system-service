package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.SubTipoUtilizacao;
import br.com.company.fks.destinacao.repository.SubTipoUtilizacaoRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 19/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class SubTipoUtilizacaoServiceTest {

    @InjectMocks
    private SubTipoUtilizacaoService subTipoUtilizacaoService;

    @Mock
    private SubTipoUtilizacaoRepository subTipoUtilizacaoRepository;

    @Mock
    private List<SubTipoUtilizacao> subTipoUtilizacaoList;

    @Test
    public void buscarTodosSubTiposUtilizacao(){
        when(subTipoUtilizacaoRepository.findAll()).thenReturn(subTipoUtilizacaoList);
        List<SubTipoUtilizacao> retorno = subTipoUtilizacaoService.buscarTodosSubTiposUtilizacaoAtivos();
        assertNotNull(retorno);
    }

    @Test
    public void buscarFiltrando(){
        List<SubTipoUtilizacao> test = subTipoUtilizacaoService.buscarFiltrando(1);
        Assert.notNull(test);
    }

}