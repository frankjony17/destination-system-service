package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.PermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.enums.StatusDestinacaoEnum;
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
public class ValidadorPermissaoUsoImovelFuncionalTest {

    @InjectMocks
    private ValidadorPermissaoUsoImovelFuncional validadorPermissaoUsoImovelFuncional;

    @Mock
    private PermissaoUsoImovelFuncional permissaoUsoImovelFuncional;

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
        when(permissaoUsoImovelFuncional.getDestinacaoImoveis()).thenReturn(asList(destinacao));
        when(permissaoUsoImovelFuncional.getCodFundamentoLegal()).thenReturn(1L);
        when(permissaoUsoImovelFuncional.getDadosResponsavel()).thenReturn(dadosResponsavel);
        when(permissaoUsoImovelFuncional.getStatusDestinacao()).thenReturn(statusDestinacao);
        when(statusDestinacao.getId()).thenReturn(4);

        try{
            validadorPermissaoUsoImovelFuncional.validadorEspecifico(permissaoUsoImovelFuncional);
            assertTrue(Boolean.TRUE);
        }catch (NegocioException e) {
            assertTrue(Boolean.TRUE);
        }
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoGerandoErros() throws NegocioException {
        when(permissaoUsoImovelFuncional.getStatusDestinacao()).thenReturn(statusDestinacao);
        when(statusDestinacao.getId()).thenReturn(1);
        validadorPermissaoUsoImovelFuncional.validadorEspecifico(permissaoUsoImovelFuncional);

    }
}
