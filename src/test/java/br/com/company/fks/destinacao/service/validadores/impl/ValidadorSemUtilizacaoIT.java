package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.SemUtilizacao;
import br.com.company.fks.destinacao.dominio.entidades.UnidadeAutonoma;
import br.com.company.fks.destinacao.dominio.enums.TipoImovelEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;

import static java.util.Arrays.asList;

/**
 * Created by diego on 19/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ValidadorSemUtilizacaoIT extends BaseIntegrationTestCofig {

    @Autowired
    private ValidadorSemUtilizacao validadorSemUtilizacao;

    @Test
    @SneakyThrows
    public void validadorEspecificoImovelNull() {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        semUtilizacao.setDestinacaoImoveis(asList(destinacaoImovel));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoUnidadePossouBenfeitoria() {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        Imovel imovel = new Imovel();
        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma();
        imovel.setUnidadeAutonoma(unidadeAutonoma);
        imovel.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.BENFEITORIA);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        semUtilizacao.setDestinacaoImoveis(asList(destinacaoImovel));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoBenfeitoriaPossuiBenfeitoriaUnidadeNula() {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        Imovel imovel = new Imovel();
        Benfeitoria benfeitoria = new Benfeitoria();
        imovel.setBenfeitorias(asList(benfeitoria));
        imovel.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.UNIDADE);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        semUtilizacao.setDestinacaoImoveis(asList(destinacaoImovel));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoBenfeitoriaPossuiBenfeitoriaUnidade() {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        Imovel imovel = new Imovel();
        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma();
        imovel.setUnidadeAutonoma(unidadeAutonoma);
        Benfeitoria benfeitoria = new Benfeitoria();
        imovel.setBenfeitorias(asList(benfeitoria));
        imovel.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.UNIDADE);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        semUtilizacao.setDestinacaoImoveis(asList(destinacaoImovel));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoBenfeitoriaPossuiBenfeitoriaNulaUnidade() {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        Imovel imovel = new Imovel();
        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma();
        imovel.setUnidadeAutonoma(unidadeAutonoma);
        imovel.setBenfeitorias(null);
        imovel.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.UNIDADE);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        semUtilizacao.setDestinacaoImoveis(asList(destinacaoImovel));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoBenfeitoriaPossuiVaziaUnidade() {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        Imovel imovel = new Imovel();
        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma();
        imovel.setUnidadeAutonoma(unidadeAutonoma);
        imovel.setBenfeitorias(Collections.emptyList());
        imovel.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.UNIDADE);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        semUtilizacao.setDestinacaoImoveis(asList(destinacaoImovel));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoBenfeitoriaNaoPossuiBenfeitoriaPossuiUnidade() {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        Imovel imovel = new Imovel();
        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma();
        imovel.setUnidadeAutonoma(unidadeAutonoma);
        imovel.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.UNIDADE);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        semUtilizacao.setDestinacaoImoveis(asList(destinacaoImovel));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoImovelTipoBenfeitoriaNaoPossuiBenfeitoriaUnidade() {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        Imovel imovel = new Imovel();
        imovel.setIndicadorUnidadeBenfeitoria(TipoImovelEnum.UNIDADE);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        semUtilizacao.setDestinacaoImoveis(asList(destinacaoImovel));
        validadorSemUtilizacao.validadorEspecifico(semUtilizacao);
    }

}
