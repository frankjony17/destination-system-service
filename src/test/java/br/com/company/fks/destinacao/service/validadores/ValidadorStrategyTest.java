package br.com.company.fks.destinacao.service.validadores;

import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.service.validadores.impl.*;
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
 * Created by diego on 22/11/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpringContext.class})
public class ValidadorStrategyTest {

    @InjectMocks
    private ValidadorStrategy validadorStrategy;

    @Mock
    private ValidadorDoacao validadorDoacao;

    @Mock
    private ValidadorVenda validadorVenda;

    @Mock
    private ValidadorPosseInformal validadorPosseInformal;

    @Mock
    private ValidadorCuem validadorCuem;

    @Mock
    private ValidadorCdru validadorCdru;

    @Mock
    private ValidadorTermoEntrega validadorTermoEntrega;

    @Mock
    private ValidadorCessaoGratuita validadorCessaoGratuita;

    @Before
    public void setUp() {
        mockStatic(SpringContext.class);
    }

    @Test
    public void createBeanDoacao() {
        ValidadorDestinacao validadorDestinacao = criarBean(ValidadorDoacao.class, validadorDoacao, TipoDestinacaoEnum.DOACAO);
        Boolean validadorDoacaoCriado = validadorDestinacao instanceof ValidadorDoacao;
        assertTrue(validadorDoacaoCriado);
    }

    @Test
    public void createBeanVenda() {
        ValidadorDestinacao validadorDestinacao = criarBean(ValidadorVenda.class, validadorVenda, TipoDestinacaoEnum.VENDA);
        Boolean validadorVendaCriado = validadorDestinacao instanceof ValidadorVenda;
        assertTrue(validadorVendaCriado);
    }

    @Test
    public void createBeanPosseInformal() {
        ValidadorDestinacao validadorDestinacao = criarBean(ValidadorPosseInformal.class, validadorPosseInformal, TipoDestinacaoEnum.POSSE_INFORMAL);
        Boolean validadorPosseInformalCriado = validadorDestinacao instanceof ValidadorPosseInformal;
        assertTrue(validadorPosseInformalCriado);
    }

    @Test
    public void createBeanCuem() {
        ValidadorDestinacao validadorDestinacao = criarBean(ValidadorCuem.class, validadorCuem, TipoDestinacaoEnum.CUEM);
        Boolean validadorCuemCriado = validadorDestinacao instanceof ValidadorCuem;
        assertTrue(validadorCuemCriado);
    }

    @Test
    public void createBeanCdru() {
        ValidadorDestinacao validadorDestinacao = criarBean(ValidadorCdru.class, validadorCdru, TipoDestinacaoEnum.CDRU);
        Boolean validadorCdruCriado = validadorDestinacao instanceof ValidadorCdru;
        assertTrue(validadorCdruCriado);
    }

    @Test
    public void createBeanTermoEntrega() {
        ValidadorDestinacao validadorDestinacao = criarBean(ValidadorTermoEntrega.class, validadorTermoEntrega, TipoDestinacaoEnum.TERMO_ENTREGA);
        Boolean validadorTermoEntregaCriado = validadorDestinacao instanceof ValidadorTermoEntrega;
        assertTrue(validadorTermoEntregaCriado);
    }

    @Test
    public void createBeanCessaoGratuita() {
        ValidadorDestinacao validadorDestinacao = criarBean(ValidadorCessaoGratuita.class, validadorCessaoGratuita, TipoDestinacaoEnum.CESSAO_GRATUITA);
        Boolean validadorCessaoGratuitaCriado = validadorDestinacao instanceof ValidadorCessaoGratuita;
        assertTrue(validadorCessaoGratuitaCriado);
    }

    private ValidadorDestinacao criarBean(Class clazz, ValidadorDestinacao destinacaoService, TipoDestinacaoEnum tipoDestinacaoEnum) {
        when(SpringContext.getBean(clazz)).thenReturn(destinacaoService);
        return validadorStrategy.createBean(tipoDestinacaoEnum);
    }

}
