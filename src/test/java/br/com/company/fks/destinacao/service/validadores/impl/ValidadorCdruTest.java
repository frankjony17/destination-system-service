package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Cdru;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.TipoConcessao;
import br.com.company.fks.destinacao.dominio.entidades.TipoLicitacao;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 22/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidadorCdruTest {

    private static final int ID_TIPO_LICITACAO = 1;

    @InjectMocks
    private ValidadorCdru validadorCdru;

    @Mock
    private Cdru cdru;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private TipoConcessao tipoConcessao;

    @Mock
    private Licitacao licitacao;

    @Mock
    private TipoLicitacao tipoLicitacao;

    @Mock
    private DadosResponsavel responsavel;

    @Before
    public void setUp () {
        when(tipoConcessao.getId()).thenReturn(1);
        when(cdru.getTipoConcessao()).thenReturn(tipoConcessao);
        when(cdru.getLicitacao()).thenReturn(licitacao);
        when(cdru.getCodFundamentoLegal()).thenReturn(1L);
        when(cdru.getDadosResponsavel()).thenReturn(responsavel);

        when(tipoLicitacao.getId()).thenReturn(ID_TIPO_LICITACAO);
        when(licitacao.getTipoLicitacao()).thenReturn(tipoLicitacao);
        when(licitacao.getNumeroProcesso()).thenReturn("212");

    }

    @Test
    public void validadorEspecificoSucesso() {
        try {
            validadorCdru.validadorEspecifico(cdru);
            assertTrue(Boolean.TRUE);
        } catch (NegocioException e) {
            fail();
        }
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoTipoConcessaoNulo() throws NegocioException {
        when(cdru.getTipoConcessao()).thenReturn(null);
        validadorCdru.validadorEspecifico(cdru);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoIdTipoConcessaoNulo() throws NegocioException {
        when(tipoConcessao.getId()).thenReturn(null);
        when(cdru.getTipoConcessao()).thenReturn(tipoConcessao);
        validadorCdru.validadorEspecifico(cdru);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoTipoLicitacaoNulo() throws NegocioException {
        when(licitacao.getTipoLicitacao()).thenReturn(null);
        validadorCdru.validadorEspecifico(cdru);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoIdTipoLicitacaoNulo() throws NegocioException {
        when(tipoLicitacao.getId()).thenReturn(null);
        when(licitacao.getTipoLicitacao()).thenReturn(tipoLicitacao);
        validadorCdru.validadorEspecifico(cdru);
    }


    @Test(expected = NegocioException.class)
    public void validadorEspecificoNumeroProcessoNulo() throws NegocioException {
        when(licitacao.getNumeroProcesso()).thenReturn(null);
        validadorCdru.validadorEspecifico(cdru);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoFundamentoLegalNulo() throws NegocioException {
        when(cdru.getCodFundamentoLegal()).thenReturn(null);
        validadorCdru.validadorEspecifico(cdru);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    public void validadorEspecificoResponsavelNulo() throws NegocioException {
        when(cdru.getDadosResponsavel()).thenReturn(null);
        validadorCdru.validadorEspecifico(cdru);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    public void validadorEspecificoResponsavel() throws NegocioException {
        when(cdru.getDadosResponsavel()).thenReturn(responsavel);
        validadorCdru.validadorEspecifico(cdru);
    }

}
