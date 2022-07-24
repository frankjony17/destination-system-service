package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ValidadorTransferenciaTest {

    @InjectMocks
    private ValidadorTransferencia validadorTransferencia;

    @Mock
    private TransferenciaTitularidade transferenciaTitularidade;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Test
    public void validadorEspecifico(){
        when(transferenciaTitularidade.getCodFundamentoLegal()).thenReturn(1L);
        when(transferenciaTitularidade.getDadosResponsavel()).thenReturn(dadosResponsavel);
        try{
            validadorTransferencia.validadorEspecifico(transferenciaTitularidade);
            assertTrue(Boolean.TRUE);
        } catch (NegocioException e){
            assertTrue(Boolean.FALSE);
        }
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoGerandoErros() throws NegocioException {
        when(transferenciaTitularidade.getCodFundamentoLegal()).thenReturn(null);
        validadorTransferencia.validadorEspecifico(transferenciaTitularidade);

    }

}
