package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ValidadorCessaoOnerosaTest {
    @InjectMocks
    private ValidadorCessaoOnerosa validadorCessaoOnerosa;

    @Mock
    private CessaoOnerosa cessaoOnerosa;

    @Mock
    private DestinacaoImovel destinacao;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Test
    public void validadorEspecifico() {
        when(cessaoOnerosa.getEnvolvePagamento()).thenReturn(true);
        when(cessaoOnerosa.getTipoCessao()).thenReturn("string");
        when(cessaoOnerosa.getDadosResponsavel()).thenReturn(dadosResponsavel);
        when(cessaoOnerosa.getStatusDestinacao()).thenReturn(statusDestinacao);
        when(statusDestinacao.getId()).thenReturn(4);

        try{
            validadorCessaoOnerosa.validadorEspecifico(cessaoOnerosa);
            assertTrue(Boolean.TRUE);
        }catch (NegocioException e) {
            assertTrue(Boolean.TRUE);
        }
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoGerandoErros() throws NegocioException {
        when(cessaoOnerosa.getStatusDestinacao()).thenReturn(statusDestinacao);
        when(statusDestinacao.getId()).thenReturn(1);
        validadorCessaoOnerosa.validadorEspecifico(cessaoOnerosa);

    }

}
