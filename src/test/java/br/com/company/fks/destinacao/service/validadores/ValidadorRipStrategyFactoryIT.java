package br.com.company.fks.destinacao.service.validadores;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.service.validadores.impl.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by diego on 26/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpringContext.class})
public class ValidadorRipStrategyFactoryIT extends BaseIntegrationTestCofig {

    @InjectMocks
    private ValidadorRipStrategyFactory validadorRipStrategyFactory;


    @Mock
    private ValidadorRipGenerico validadorRipGenerico;

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
    public void setUp() {
        mockStatic(SpringContext.class);
    }

    @Ignore
    @Test
    public void createBeanDoacao(){
        ValidadorRip validadorRip = criarBean(ValidadorRipGenerico.class, validadorRipGenerico, TipoDestinacaoEnum.DOACAO);
        Boolean validadorDoacao = validadorRip instanceof ValidadorRipGenerico;
        assertTrue(validadorDoacao);
    }

    @Ignore
    @Test
    public void createBeanVenda(){
        ValidadorRip validadorRip = criarBean(ValidadorRipGenerico.class, validadorRipGenerico, TipoDestinacaoEnum.VENDA);
        Boolean validadorVenda = validadorRip instanceof ValidadorRipGenerico;
        assertTrue(validadorVenda);
    }

    @Ignore
    @Test
    public void createBeanCDRU(){
        ValidadorRip validadorRip = criarBean(ValidadorRipGenerico.class, validadorRipGenerico, TipoDestinacaoEnum.CDRU);
        Boolean validadorCDRU = validadorRip instanceof ValidadorRipGenerico;
        assertTrue(validadorCDRU);
    }

    @Ignore
    @Test
    public void createBeanTermoEntrega(){
        ValidadorRip validadorRip = criarBean(ValidadorTermoEntrega.class, validadorRipCuem, TipoDestinacaoEnum.CUEM);
        Boolean validadorTermoEntrega = validadorRip instanceof  ValidadorRipTermoEntrega;
        assertTrue(validadorTermoEntrega);
    }

    @Ignore
    @Test
    public void createBeanCessaoGratuita(){
        ValidadorRip validadorRip = criarBean(ValidadorRipCategoriaCessao.class, validadorRipCategoriaCessao, TipoDestinacaoEnum.CESSAO_GRATUITA);
        Boolean validadorCessaoGratuita = validadorRip instanceof ValidadorRipCategoriaCessao;
        assertTrue(validadorCessaoGratuita);
    }

    @Ignore
    @Test
    public void createBeanPermissaoUsoImovelFuncional() {
       ValidadorRip validadorRip = criarBean(ValidadorRipPermissaoUsoImovelFuncional.class, validadorRipPermissaoUsoImovelFuncional, TipoDestinacaoEnum.PERMISSAO_USO_IMOVEL_FUNCIONAL);
       Boolean validadorPermissaoUsoImovelFuncional = validadorRip instanceof ValidadorRipPermissaoUsoImovelFuncional;
       assertTrue(validadorPermissaoUsoImovelFuncional);
    }
    @Ignore
    @Test
    public void createBeanUsoProprio(){
        ValidadorRip validadorRip = criarBean(ValidadorRipUsoProprio.class, validadorRipUsoProprio, TipoDestinacaoEnum.USO_PROPRIO);
        Boolean validadorRipUsoProprio = validadorRip instanceof ValidadorRipUsoProprio;
        assertTrue(validadorRipUsoProprio);
    }

    private ValidadorRip criarBean(Class clazz, ValidadorRip ripService, TipoDestinacaoEnum tipoDestinacaoEnum){
        when(SpringContext.getBean(clazz)).thenReturn(ripService);
        return validadorRipStrategyFactory.createBean(tipoDestinacaoEnum);
    }
}