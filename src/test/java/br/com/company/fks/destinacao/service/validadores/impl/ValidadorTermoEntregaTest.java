package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TermoEntrega;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 23/01/17.
 */
@RunWith(PowerMockRunner.class)
public class ValidadorTermoEntregaTest {

    @InjectMocks
    private ValidadorTermoEntrega validadorTermoEntrega;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private TermoEntrega termoEntrega;

    @Mock
    private Contrato contrato;

    @Mock
    private StatusDestinacao statusDestinacao;


    @Test
    public void validadorEspecifico() {
        when(contrato.getDataInicio()).thenReturn(new Date());
        when(termoEntrega.getContrato()).thenReturn(contrato);
        when(termoEntrega.getCodFundamentoLegal()).thenReturn(1L);
        when(termoEntrega.getStatusDestinacao()).thenReturn(statusDestinacao);
        when(statusDestinacao.getId()).thenReturn(4);
        try {
            validadorTermoEntrega.validadorEspecifico(termoEntrega);
            assertTrue(Boolean.TRUE);
        } catch (NegocioException e) {
            assertTrue(Boolean.FALSE);
        }
    }


    @Test(expected = NegocioException.class)
    public void validadorEspecificoGerandoErros() throws NegocioException {
        when(termoEntrega.getStatusDestinacao()).thenReturn(statusDestinacao);
        when(statusDestinacao.getId()).thenReturn(1);
        validadorTermoEntrega.validadorEspecifico(termoEntrega);
    }

}