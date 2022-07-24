package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

@RunWith(PowerMockRunner.class)
public class ValidadorRipCategoriaCessaoTest {

    @InjectMocks
    private ValidadorRipCategoriaCessao validadorRipCategoriaCessao;

    @Mock
    private Imovel imovel;

    @Before
    public void setUp() throws NegocioException {
        validadorRipCategoriaCessao = new ValidadorRipCategoriaCessao();
    }

    @Test
    public void validadorEspecifico() throws NegocioException{
        validadorRipCategoriaCessao.validadorEspecifico(imovel, 0L);
    }
}
