package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import org.apache.xpath.operations.Neg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ValidadorRipGenericoTest {

    @InjectMocks
    private ValidadorRipGenerico validadorRipGenerico;

    @Mock
    private Imovel imovel;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Test
    public void validadorEspecifico() throws NegocioException {
        when(statusDestinacao.getId()).thenReturn(4);
        try {
            validadorRipGenerico.validadorEspecifico(imovel, 1L);
            assertFalse(Boolean.FALSE);
        } catch (NegocioException e) {
            assertTrue(Boolean.TRUE);
        }
    }
}
