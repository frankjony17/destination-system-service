package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.SemUtilizacao;
import br.com.company.fks.destinacao.dominio.entidades.UnidadeAutonoma;
import br.com.company.fks.destinacao.dominio.enums.TipoImovelEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 08/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidadorSemUtilizacaoTest {

    @InjectMocks
    private ValidadorSemUtilizacao validadorSemUtilizacao;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private SemUtilizacao semUtilizacao;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private Imovel imovel;

    @Mock
    private Benfeitoria benfeitoria;

    @Mock
    private UnidadeAutonoma unidadeAutonoma;

    @Mock
    private Endereco endereco;

    @Before
    public void setUp() {
        mocarEndereco();
        mocarImovel();
        mocarUnidadeAutonoma();
        mocarBenfeitoria();
        when(destinacaoImovel.getCodigoUtilizacao()).thenReturn("0000");
        when(semUtilizacao.getDestinacaoImoveis()).thenReturn(asList(destinacaoImovel));
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
    }

    private void mocarBenfeitoria() {
        when(benfeitoria.getAreaConstruida()).thenReturn(BigDecimal.TEN);
        when(benfeitoria.getAtiva()).thenReturn(true);
        when(benfeitoria.getCodigo()).thenReturn("E1");
        when(benfeitoria.getIdBenfeitoriaCadImovel()).thenReturn(1L);
    }

    private void mocarUnidadeAutonoma() {
        when(unidadeAutonoma.getArea()).thenReturn(BigDecimal.TEN);
        when(unidadeAutonoma.getIdUnidadeAutonomaCadImovel()).thenReturn(1L);
    }

    private void mocarEndereco() {
        when(endereco.getUf()).thenReturn("DF");
        when(endereco.getBairro()).thenReturn("Asa Sul");
        when(endereco.getCep()).thenReturn("0000000");
        when(endereco.getLogradouro()).thenReturn("Rua 1");
        when(endereco.getMunicipio()).thenReturn("Brasilia");
        when(endereco.getNumero()).thenReturn("1");
    }

    private void mocarImovel() {
        when(imovel.getEndereco()).thenReturn(endereco);
        when(imovel.getRip()).thenReturn("00000007");
        when(imovel.getCodigoNaturezaImovel()).thenReturn(1L);
        when(imovel.getCodigoSituacaoIncorporacao()).thenReturn(1L);
        when(imovel.getCodigoTipoImovel()).thenReturn(1L);
        when(imovel.getCoditoTipoProprietario()).thenReturn(1L);
        when(imovel.getIdCadastroImovel()).thenReturn(1L);
    }


    @Test
    @SneakyThrows
    public void validadorEspecificoSucesso() {
        when(imovel.getIndicadorUnidadeBenfeitoria()).thenReturn(TipoImovelEnum.BENFEITORIA);
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getBenfeitorias()).thenReturn(asList(benfeitoria));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
        assertTrue(true);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelNulo() {
        when(imovel.getIndicadorUnidadeBenfeitoria()).thenReturn(TipoImovelEnum.BENFEITORIA);
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getEndereco()).thenReturn(null);
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelEnderecoNulo() {
        when(imovel.getIndicadorUnidadeBenfeitoria()).thenReturn(TipoImovelEnum.BENFEITORIA);
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getEndereco()).thenReturn(null);
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoBenfeitoriaInvalidoPossuiUnidadeAutonoma() {
        when(imovel.getIndicadorUnidadeBenfeitoria()).thenReturn(TipoImovelEnum.BENFEITORIA);
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getEndereco()).thenReturn(endereco);
        when(imovel.getUnidadeAutonoma()).thenReturn(unidadeAutonoma);
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoUnidadeAutonomaInvalidaUnidadeAutomomaNula() {
        when(imovel.getIndicadorUnidadeBenfeitoria()).thenReturn(TipoImovelEnum.UNIDADE);
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getEndereco()).thenReturn(endereco);
        when(imovel.getUnidadeAutonoma()).thenReturn(null);
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoUnidadeAutonomaInvalidaUnidadeAutomomaNulaPossouBenfeitoria() {
        when(imovel.getIndicadorUnidadeBenfeitoria()).thenReturn(TipoImovelEnum.UNIDADE);
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getEndereco()).thenReturn(endereco);
        when(imovel.getBenfeitorias()).thenReturn(asList(benfeitoria));
        when(imovel.getUnidadeAutonoma()).thenReturn(null);
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

}