package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.DocumentoArquivo;
import br.com.company.fks.destinacao.dominio.entidades.Foto;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorPosseInformal;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 21/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidadorPosseInformalTest {

    private static final String ERRO_AO_SALVAR = "Erro ao salvar";

    private static final int ID_TIPO_POSSE_ENTIDADE_COLETIVA = 3;
    private static final String NOME_ENTIDADE = "teste";

    @InjectMocks
    private ValidadorPosseInformal validadorPosseInformal;

    @Mock
    private PosseInformal posseInformal;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private TipoPosse tipoPosse;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Before
    public void setUp() {
        when(mensagemNegocio.get(anyString())).thenReturn(ERRO_AO_SALVAR);
        when(posseInformal.getDadosResponsavel()).thenReturn(dadosResponsavel);
        when(dadosResponsavel.getTipoPosseOcupacao()).thenReturn(TipoPosseOcupacaoEnum.COLETIVO);

    }

    @Test
    public void validadorEspecificoValidandoComSucesso() {
        when(tipoPosse.getId()).thenReturn(ID_TIPO_POSSE_ENTIDADE_COLETIVA);
        when(posseInformal.getNomeEntidade()).thenReturn(NOME_ENTIDADE);
        when(posseInformal.getDocumentosArquivo()).thenReturn(criarDocumentoArquivos());
        when(posseInformal.getFotos()).thenReturn(criarFotos());
        try {
            validadorPosseInformal.validadorEspecifico(posseInformal);
            assertTrue(Boolean.TRUE);
        } catch (NegocioException e) {
            fail();
        }

    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoValidandoComErroNomeEntidadeVazio() throws NegocioException {
        when(tipoPosse.getId()).thenReturn(ID_TIPO_POSSE_ENTIDADE_COLETIVA);
        validadorPosseInformal.validadorEspecifico(posseInformal);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoValidandoComErroTipoPosseVazio() throws NegocioException {
        when(dadosResponsavel.getTipoPosseOcupacao()).thenReturn(null);
        validadorPosseInformal.validadorEspecifico(posseInformal);
    }

    @Test
    @Ignore
    public void validarCamposEntidadeVaziosIdTipoPosseInvalido() throws NegocioException {
        when(tipoPosse.getId()).thenReturn(4);
        when(posseInformal.getDocumentosArquivo()).thenReturn(criarDocumentoArquivos());
        when(posseInformal.getFotos()).thenReturn(criarFotos());
        try {
            validadorPosseInformal.validadorEspecifico(posseInformal);
            assertTrue(Boolean.TRUE);
        } catch (NegocioException e) {
            fail();
        }
    }

    private List<DocumentoArquivo> criarDocumentoArquivos() {
        DocumentoArquivo documentoArquivo = new DocumentoArquivo();
        documentoArquivo.setId(1L);
        return asList(documentoArquivo);
    }

    private List<Foto> criarFotos() {
        Foto foto = new Foto();
        foto.setId(1L);
        return asList(foto);
    }
}
