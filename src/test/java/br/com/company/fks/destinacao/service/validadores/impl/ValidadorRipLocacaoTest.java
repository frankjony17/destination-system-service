package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ValidadorRipLocacaoTest {

    @InjectMocks
    private ValidadorRipLocacao validadorRipLocacao;

    @Mock
    private Imovel imovel;

    @Before
    public void setUp() throws Exception{
        validadorRipLocacao = new ValidadorRipLocacao();
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecifico() throws NegocioException {
        validadorRipLocacao.validadorEspecifico(imovel, 1L);
    }
    @Test
    public void validadorEspecificoErro() throws NegocioException {
        when(imovel.getCodigoTipoImovel()).thenReturn(5L);
        validadorRipLocacao.validadorEspecifico(imovel, 1L);
    }
    @Test
    public void validadorEspecificoErroIdTres() throws NegocioException {
        when(imovel.getCodigoTipoImovel()).thenReturn(3L);
        validadorRipLocacao.validadorEspecifico(imovel, 1L);
    }
}
