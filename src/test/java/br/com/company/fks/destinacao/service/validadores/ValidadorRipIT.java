package br.com.company.fks.destinacao.service.validadores;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

/**
 * Created by haillanderson on 19/04/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ValidadorRipIT extends BaseIntegrationTestCofig{

    private ValidadorRip validadorRip;

    @Autowired
    private ValidadorRipStrategyFactory validadorRipStrategyFactory;

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarVendaIdTipoImovelCinco(){
        Imovel imovel = criaImovel();
        imovel.setCodigoSituacaoIncorporacao(1L);
        imovel.setCodigoTipoImovel(5L);
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.VENDA);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel,null, fundamentoLegalDTO);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarVendaIdTipoImovelTres(){
        Imovel imovel = criaImovel();
        imovel.setCodigoSituacaoIncorporacao(1L);
        imovel.setCodigoTipoImovel(3L);
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.VENDA);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, null, fundamentoLegalDTO);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarVendaIdTipoImovelDois(){
        Imovel imovel = criaImovel();
        imovel.setCodigoSituacaoIncorporacao(1L);
        imovel.setCodigoTipoImovel(2L);
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.VENDA);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, null, fundamentoLegalDTO);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarVendaSituacaoIncorporacaoDois(){
        Imovel imovel = criaImovel();
        imovel.setCodigoSituacaoIncorporacao(2L);
        imovel.setCodigoTipoImovel(2L);
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.VENDA);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, null,fundamentoLegalDTO);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarVendaSituacaoIncorporacaoDoisIdTipoImovelTres(){
        Imovel imovel = criaImovel();
        imovel.setCodigoSituacaoIncorporacao(2L);
        imovel.setCodigoTipoImovel(3L);
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.VENDA);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, null, fundamentoLegalDTO);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarLocacaoIdTipoImovelTres(){
        Imovel imovel = criaImovel();
        imovel.setCodigoTipoImovel(3L);
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.LOCACAO);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, null, fundamentoLegalDTO);
    }

   /* @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarTeste(){
        validadorRip.validar("teste",criaImovel(),null);
    }*/


    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarCuemNaturezaDois(){
        Imovel imovel = criaImovel();
        imovel.setCodigoNaturezaImovel(2L);
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.CUEM);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, null, fundamentoLegalDTO);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarCuemSituacaoIncorporacaoDois(){
        Imovel imovel = criaImovel();
        imovel.setCodigoNaturezaImovel(1L);
        imovel.setCodigoSituacaoIncorporacao(2L);
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.CUEM);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, null, fundamentoLegalDTO);
    }


    @Ignore
    @Test
    @SneakyThrows
    public void validarCuemAreaTerrenoIgaul(){
        Imovel imovel = criaImovel();
        imovel.setCodigoNaturezaImovel(1L);
        imovel.setCodigoSituacaoIncorporacao(1L);
        imovel.setCodigoTipoImovel(2L);
        imovel.setAreaTerreno(BigDecimal.valueOf(250));
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.CUEM);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, 2L, fundamentoLegalDTO);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarCuemAreaTerrenoMaiorModalidadeUm(){
        Imovel imovel = criaImovel();
        imovel.setCodigoNaturezaImovel(1L);
        imovel.setCodigoSituacaoIncorporacao(1L);
        imovel.setCodigoTipoImovel(2L);
        imovel.setAreaTerreno(BigDecimal.valueOf(250));
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.CUEM);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarCuemAreaTerrenoMaiorModalidadeDois(){
        Imovel imovel = criaImovel();
        imovel.setCodigoNaturezaImovel(1L);
        imovel.setCodigoSituacaoIncorporacao(1L);
        imovel.setCodigoTipoImovel(2L);
        imovel.setAreaTerreno(BigDecimal.valueOf(251));
        validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.CUEM);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRip.validar(imovel, 2L, fundamentoLegalDTO);
    }


    private Imovel criaImovel(){
        Imovel imovel = new Imovel();
        imovel.setEndereco(criaEndereco());
        return imovel;
    }

    private Endereco criaEndereco(){
        Endereco endereco = new Endereco();
        endereco.setUf("DF");
        return endereco;
    }

}