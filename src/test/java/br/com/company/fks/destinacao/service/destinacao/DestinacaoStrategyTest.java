package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.entidades.Venda;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorDoacao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by diego on 23/11/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpringContext.class})
public class DestinacaoStrategyTest {

    @InjectMocks
    private DestinacaoStrategy destinacaoStrategy;

    @Mock
    private DoacaoService doacaoService;

    @Mock
    private VendaService vendaService;

    @Mock
    private PosseInformalService posseInformalService;

    @Mock
    private CuemService cuemService;

    @Mock
    private CdruService cdruService;

    @Mock
    private TermoEntregaService termoEntregaService;

    @Mock
    private CessaoGratuitaService cessaoGratuitaService;

    @Before
    public void setUp() {
        mockStatic(SpringContext.class);
    }

    @Test
    public void createBeanDoacaoService() {
        DestinacaoService destinacaoService = criarBean(DoacaoService.class, doacaoService, TipoDestinacaoEnum.DOACAO);
        Boolean doacaoServiceCriado = destinacaoService instanceof DoacaoService;
        assertTrue(doacaoServiceCriado);
    }

    @Test
    public void createBeanVendaService() {
        DestinacaoService destinacaoService = criarBean(VendaService.class, vendaService, TipoDestinacaoEnum.VENDA);
        Boolean vendaServiceCriado = destinacaoService instanceof VendaService;
        assertTrue(vendaServiceCriado);
    }

    @Test
    public void createBeanPosseInformalService() {
        DestinacaoService destinacaoService = criarBean(PosseInformalService.class, posseInformalService, TipoDestinacaoEnum.POSSE_INFORMAL);
        Boolean posseInformalServiceCriado = destinacaoService instanceof PosseInformalService;
        assertTrue(posseInformalServiceCriado);
    }

    @Test
    public void createBeanCuemService() {
        DestinacaoService destinacaoService = criarBean(CuemService.class, cuemService, TipoDestinacaoEnum.CUEM);
        Boolean cuemServiceCriado = destinacaoService instanceof CuemService;
        assertTrue(cuemServiceCriado);
    }

    @Test
    public void createBeanCdruService() {
        DestinacaoService destinacaoService = criarBean(CdruService.class, cdruService, TipoDestinacaoEnum.CDRU);
        Boolean cdruServiceCriado = destinacaoService instanceof CdruService;
        assertTrue(cdruServiceCriado);
    }

    @Test
    public void createBeanTermoEntregaService() {
        DestinacaoService destinacaoService = criarBean(TermoEntregaService.class, termoEntregaService, TipoDestinacaoEnum.TERMO_ENTREGA);
        Boolean termoEntregaCriado = destinacaoService instanceof TermoEntregaService;
        assertTrue(termoEntregaCriado);
    }

    @Test
    public void createBeanCessaoGratuitaService() {
        DestinacaoService destinacaoService = criarBean(CessaoGratuitaService.class, cessaoGratuitaService, TipoDestinacaoEnum.CESSAO_GRATUITA);
        Boolean cessaoGratuitaCriado = destinacaoService instanceof CessaoGratuitaService;
        assertTrue(cessaoGratuitaCriado);
    }

    private DestinacaoService criarBean(Class clazz, DestinacaoService destinacaoService, TipoDestinacaoEnum tipoDestinacaoEnum) {
        when(SpringContext.getBean(clazz)).thenReturn(destinacaoService);
        return destinacaoStrategy.createBean(tipoDestinacaoEnum);
    }
}
