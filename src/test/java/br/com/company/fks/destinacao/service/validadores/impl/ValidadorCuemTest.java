package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorCuem;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 22/11/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Optional.class)
public class ValidadorCuemTest {

    private static final String CNPJ_BENEFICIARIO = "00000000000191";

    private List<String> erros = new ArrayList<>();

    @InjectMocks
    private ValidadorCuem validadorCuem;

    @Mock
    private Cuem cuem;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private Contrato contrato;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private Responsavel responsavel;

    @Mock
    private TipoModalidade tipoModalidade;

    @Mock
    private FamiliaBeneficiada familiaBeneficiada;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Before
    public void setUp() {

        Calendar calendarDataContratro = Calendar.getInstance();
        calendarDataContratro.set(2016, Calendar.NOVEMBER, 16);

        when(contrato.getDataInicio()).thenReturn(calendarDataContratro.getTime());
        when(tipoModalidade.getId()).thenReturn(1);
        when(cuem.getTipoModalidade()).thenReturn(tipoModalidade);
        when(cuem.getContrato()).thenReturn(contrato);
        //when(responsavel.getFamiliasBeneficiadas()).thenReturn(asList(familiaBeneficiada));
        when(responsavel.getCpfCnpj()).thenReturn(CNPJ_BENEFICIARIO);
        when(cuem.getDadosResponsavel()).thenReturn(dadosResponsavel);
        when(cuem.getStatusDestinacao()).thenReturn(statusDestinacao);
        when(statusDestinacao.getId()).thenReturn(1);


    }

    @Test
    public void validadorEspecificoSucesso() {
        try {
            validadorCuem.validadorEspecifico(cuem);
            assertTrue(Boolean.TRUE);
        } catch (NegocioException e) {
            fail();
        }
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoIdTipoModalidadeNulo() throws NegocioException {
        when(tipoModalidade.getId()).thenReturn(null);
        validadorCuem.validadorEspecifico(cuem);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoTipoModalidadeNulo() throws NegocioException {
        when(cuem.getTipoModalidade()).thenReturn(null);
        validadorCuem.validadorEspecifico(cuem);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoContratoNulo() throws NegocioException {
        when(cuem.getContrato()).thenReturn(null);
        validadorCuem.validadorEspecifico(cuem);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoContratoDataInicioNulo() throws NegocioException {
        when(contrato.getDataInicio()).thenReturn(null);
        when(cuem.getContrato()).thenReturn(contrato);
        validadorCuem.validadorEspecifico(cuem);
    }


    @Test
    public void validadorEspecificoModalidadeIndividualMaisUmBeneficiarioNulo() throws NegocioException {
        FamiliaBeneficiada familiaBeneficiada = mock(FamiliaBeneficiada.class);
        when(tipoModalidade.getId()).thenReturn(1);
        when(responsavel.getFamiliasBeneficiadas()).thenReturn(asList(familiaBeneficiada, this.familiaBeneficiada));
        validadorCuem.validadorEspecifico(cuem);
    }

    @Test
    public void validadorEspecificoFamiliasBeneficiadasVazio() throws NegocioException {
        when(cuem.getDadosResponsavel()).thenReturn(null);
        validadorCuem.validadorEspecifico(cuem);
    }

    @Test
    public void validarBeneficiario(){
        validadorCuem.validarBeneficiario(asList(responsavel),tipoModalidade ,erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioListaVazia(){
        validadorCuem.validarBeneficiario(anyList(),tipoModalidade ,erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void verificarExisteMaisUmBeneficiarioTipoIndividual(){
        when(responsavel.getFamiliasBeneficiadas()).thenReturn(asList(familiaBeneficiada, familiaBeneficiada));
        validadorCuem.validarBeneficiario(asList(responsavel),tipoModalidade ,erros);
        assertFalse(erros.isEmpty());
    }
}
