package br.com.company.fks.destinacao.service.validadores;

import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.service.validadores.impl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SpringContext.class})
public class ValidadorRipStrategyFactoryTest {

    @InjectMocks
    private ValidadorRipStrategyFactory validadorRipStrategyFactory;

    @Mock
    private ValidadorRipGenerico validadorRipGenerico;

    @Mock
    private ValidadorTermoEntrega validadorTermoEntrega;

    @Mock
    private ValidadorCessaoGratuita validadorCessaoGratuita;

    @Mock
    private ValidadorRipCuem validadorRipCuem;

    @Mock
    private ValidadorRipTermoEntrega validadorRipTermoEntrega;

    @Mock
    private ValidadorRipCategoriaCessao validadorRipCategoriaCessao;

    @Mock
    private ValidadorRipUsoProprio validadorRipUsoProprio;

    @Mock
    private ValidadorRipPermissaoUsoImovelFuncional validadorRipPermissaoUsoImovelFuncional;

    @Before
    public void setUp(){ mockStatic(SpringContext.class); }

    @Test
    public void createBeanDoacao(){
        ValidadorRip validadorRip = criarBean(ValidadorRipGenerico.class, validadorRipGenerico, TipoDestinacaoEnum.DOACAO);
        Boolean validadorDoacaoCriado = validadorRip instanceof ValidadorRipGenerico;
        assertTrue(validadorDoacaoCriado);
    }

    @Test
    public void createBeanVenda(){
        ValidadorRip validadorRip = criarBean(ValidadorRipGenerico.class,validadorRipGenerico, TipoDestinacaoEnum.VENDA);
        Boolean validadorVendaCriado = validadorRip instanceof ValidadorRipGenerico;
        assertTrue(validadorVendaCriado);
    }

    @Test
    public void createBeanCDRU(){
        ValidadorRip validadorRip = criarBean(ValidadorRipGenerico.class, validadorRipGenerico, TipoDestinacaoEnum.CDRU);
        Boolean validadorCdruCriado = validadorRip instanceof ValidadorRipGenerico;
        assertTrue(validadorCdruCriado);
    }

    @Test
    public void createBeanCuem(){
        ValidadorRip validadorRip = criarBean(ValidadorRipCuem.class, validadorRipCuem, TipoDestinacaoEnum.CUEM);
        Boolean validadorCuemCriado = validadorRip instanceof ValidadorRipCuem;
        assertTrue(validadorCuemCriado);
    }

    @Test
    public void createBeanTermoEntrega(){
        ValidadorRip validadorRip = criarBean(ValidadorRipTermoEntrega.class, validadorRipTermoEntrega, TipoDestinacaoEnum.TERMO_ENTREGA);
        Boolean validadorTermoEntregaCriado = validadorRip instanceof ValidadorRipTermoEntrega;
        assertTrue(validadorTermoEntregaCriado);
    }

    @Test
    public void createBeanCessaoGratuita(){
        ValidadorRip validadorRip = criarBean(ValidadorRipCategoriaCessao.class, validadorRipCategoriaCessao, TipoDestinacaoEnum.CESSAO_GRATUITA);
        Boolean validadorCessaoGratuitaCriado = validadorRip instanceof ValidadorRipCategoriaCessao;
        assertTrue(validadorCessaoGratuitaCriado);
    }

    @Test
    public void createBeanLocacao(){
        ValidadorRip validadorRip = criarBean(ValidadorRipGenerico.class, validadorRipGenerico, TipoDestinacaoEnum.LOCACAO);
        Boolean validadorLocacaoCriado = validadorRip instanceof ValidadorRipGenerico;
        assertTrue(validadorLocacaoCriado);
    }

    @Test
    public void createBeanUsoProprio(){
        ValidadorRip validadorRip = criarBean(ValidadorRipUsoProprio.class, validadorRipUsoProprio, TipoDestinacaoEnum.USO_PROPRIO);
        Boolean validadorUSoProprioCriado = validadorRip instanceof ValidadorRipUsoProprio;
        assertTrue(validadorUSoProprioCriado);
    }

    @Test
    public void createBeanPermissaoUsoImovelFuncional(){
        ValidadorRip validadorRip = criarBean(ValidadorRipPermissaoUsoImovelFuncional.class, validadorRipPermissaoUsoImovelFuncional, TipoDestinacaoEnum.PERMISSAO_USO_IMOVEL_FUNCIONAL);
        Boolean validadorPermissaoCriado = validadorRip instanceof ValidadorRipPermissaoUsoImovelFuncional;
        assertTrue(validadorPermissaoCriado);
    }

    @Test
    public void createBeanTransferencia(){
        ValidadorRip validadorRip = criarBean(ValidadorRipGenerico.class, validadorRipGenerico, TipoDestinacaoEnum.TRANSFERENCIA);
        Boolean validadorTransferenciaCriado = validadorRip instanceof ValidadorRipGenerico;
        assertTrue(validadorTransferenciaCriado);
    }

    @Test
    public void createBeanCessaoOnerosa(){
        ValidadorRip validadorRip = criarBean(ValidadorRipCategoriaCessao.class, validadorRipCategoriaCessao, TipoDestinacaoEnum.CESSAO_ONEROSA);
        Boolean validadorCessaoOnerosa = validadorRip instanceof ValidadorRipCategoriaCessao;
        assertTrue(validadorCessaoOnerosa);
    }

    private ValidadorRip criarBean(Class clazz, ValidadorRip destinacaoService, TipoDestinacaoEnum tipoDestinacaoEnum) {
        PowerMockito.mockStatic(SpringContext.class);
        PowerMockito.when(SpringContext.getBean(any())).thenReturn(destinacaoService);
        return validadorRipStrategyFactory.createBean(tipoDestinacaoEnum);
    }

}
