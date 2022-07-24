package br.com.company.fks.destinacao.service.strategy;

import br.com.company.fks.destinacao.dominio.entidades.CancelamentoEncerramento;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.repository.CancelamentoEncerramentoUtilizacaoRepository;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
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
public class ConfirmarCancelamentoAceitarStrategyTest {

    @InjectMocks
    private ConfirmarCancelamentoAceitarStrategy confirmarCancelamentoAceitarStrategy;

    @Mock
    private CancelamentoEncerramentoUtilizacaoRepository cancelamentoEncerramentoUtilizacaoRepository;

    @Mock
    private Destinacao destinacao;

    @Mock
    private DestinacaoRepository destinacaoRepository;

    @Mock
    private CancelamentoEncerramento cancelamentoEncerramento;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Before
    public void setUp(){
        when(destinacaoRepository.save(any(Destinacao.class))).thenReturn(destinacao);
    }

    @Test
    public void confirmarCancelamento(){
        confirmarCancelamentoAceitarStrategy.confirmarCancelamento();
    }

}
