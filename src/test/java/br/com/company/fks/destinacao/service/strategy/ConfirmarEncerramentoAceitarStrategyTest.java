package br.com.company.fks.destinacao.service.strategy;

import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.EncerramentoDestinacao;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.EncerramentoDestinacaoRepository;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ConfirmarEncerramentoAceitarStrategyTest {

    @InjectMocks
    private ConfirmarEncerramentoAceitarStrategy confirmarEncerramentoAceitarStrategy;

    @Mock
    private DestinacaoRepository destinacaoRepository;

    @Mock
    private EncerramentoDestinacaoRepository encerramentoDestinacaoRepository;

    @Mock
    private Destinacao destinacao;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private EncerramentoDestinacao encerramentoDestinacao;

    @Before
    public void setUp(){
        when(encerramentoDestinacaoRepository.save(any(EncerramentoDestinacao.class))).thenReturn(encerramentoDestinacao);
    }

    @Test
    public void confirmarCancelamento(){
        confirmarEncerramentoAceitarStrategy.confirmarEncerramento();
    }
}
