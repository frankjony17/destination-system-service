package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.apache.xpath.operations.Neg;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 23/01/17.
 */
@RunWith(PowerMockRunner.class)
public class ValidadorCessaoGratuitaTest {

   @InjectMocks
    private ValidadorCessaoGratuita validadorCessaoGratuita;

   @Mock
    private CessaoGratuita cessaoGratuita;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private StatusDestinacao statusDestinacao;
    @Test
    public void validadorEspecifico(){
        when(cessaoGratuita.getCodFundamentoLegal()).thenReturn(1L);
        when(cessaoGratuita.getDadosResponsavel()).thenReturn(dadosResponsavel);
        when(statusDestinacao.getId()).thenReturn(4);
        try {
            validadorCessaoGratuita.validadorEspecifico(cessaoGratuita);
            assertTrue(Boolean.TRUE);
        } catch (NegocioException e) {
            assertTrue(Boolean.FALSE);
        }
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoGerandoErros() throws NegocioException {
        when(cessaoGratuita.getCodFundamentoLegal()).thenReturn(null);
        validadorCessaoGratuita.validadorEspecifico(cessaoGratuita);
    }

}