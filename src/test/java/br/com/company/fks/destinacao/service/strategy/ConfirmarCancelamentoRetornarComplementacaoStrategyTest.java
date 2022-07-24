package br.com.company.fks.destinacao.service.strategy;

import br.com.company.fks.destinacao.dominio.entidades.CancelamentoEncerramento;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.repository.CancelamentoEncerramentoUtilizacaoRepository;
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
public class ConfirmarCancelamentoRetornarComplementacaoStrategyTest {

    @InjectMocks
    private ConfirmarCancelamentoRetornarComplementacaoStrategy confirmarCancelamentoRetornarComplementacaoStrategy;

    @Mock
    private CancelamentoEncerramentoUtilizacaoRepository repository;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private Destinacao destinacao;

    @Mock
    private CancelamentoEncerramento cancelamentoEncerramento;

    @Before
    public void setUp(){
        when(repository.save(any(CancelamentoEncerramento.class))).thenReturn(cancelamentoEncerramento);
    }

    @Test
    public void confirmarCancelamento(){
        confirmarCancelamentoRetornarComplementacaoStrategy.confirmarCancelamento();
    }
}
