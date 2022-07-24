package br.com.company.fks.destinacao.service.strategy;

import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.EncerramentoDestinacao;
import br.com.company.fks.destinacao.repository.EncerramentoDestinacaoRepository;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ConfirmarEncerramentoRetornarComplementacaoStrategyTest {

    @InjectMocks
    private ConfirmarEncerramentoRetornarComplementacaoStrategy confirmarEncerramentoRetornarComplementacaoStrategy;

    @Mock
    private EncerramentoDestinacao encerramentoDestinacao;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private Destinacao destinacao;

    @Mock
    private EncerramentoDestinacaoRepository encerramentoDestinacaoRepository;

    @Before
    public void setUp(){
        when(encerramentoDestinacaoRepository.save(any(EncerramentoDestinacao.class))).thenReturn(encerramentoDestinacao);
    }

    @Test
    public void confirmarEncerramento(){
        confirmarEncerramentoRetornarComplementacaoStrategy.confirmarEncerramento();
    }
}
