package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.apresentacao.builder.StatusDestinacaoStatusBuilder;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static java.util.Arrays.asList;

/**
 * Created by samuel on 19/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ValidadorVendaIT extends BaseIntegrationTestCofig {

    @Autowired
    private ValidadorVenda validadorVenda;

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void verificarMoedaPreenchidaNull() throws NegocioException {
        Financeiro financeiro = new Financeiro();
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setId(1);
        financeiro.setTipoPagamento(tipoPagamento);
        Venda venda = new Venda();
        venda.setStatusDestinacao(criarStatusDetinacao(1));
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Licitacao licitacao = new Licitacao();
        venda.setLicitacao(licitacao);
        TipoMoeda tipoMoeda = new TipoMoeda();
        tipoMoeda.setId(null);
        financeiro.setTipoMoeda(tipoMoeda);
        venda.setFinanceiro(financeiro);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void verificarMoedaPreenchida() throws NegocioException {
        TipoPagamento tipoPagamento = new TipoPagamento();
        Financeiro financeiro = new Financeiro();
        tipoPagamento.setId(1);
        financeiro.setTipoPagamento(tipoPagamento);
        Venda venda = new Venda();
        venda.setStatusDestinacao(criarStatusDetinacao(1));
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Licitacao licitacao = new Licitacao();
        venda.setLicitacao(licitacao);
        TipoMoeda tipoMoeda = new TipoMoeda();
        tipoMoeda.setId(1);
        financeiro.setTipoMoeda(tipoMoeda);
        venda.setFinanceiro(financeiro);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void verificaJurosPreenchidos() throws NegocioException{
        TipoPagamento tipoPagamento = new TipoPagamento();
        Financeiro financeiro = new Financeiro();
        Contrato contrato = new Contrato();
        contrato.setDataFinal(new Date());
        contrato.setDataInicio(new Date());
        tipoPagamento.setId(2);
        financeiro.setTipoPagamento(tipoPagamento);
        financeiro.setDataInicioCobranca(new Date());
        financeiro.setValor(new BigDecimal(2.0));
        financeiro.setValorFinancidado(new BigDecimal(1.0));
        financeiro.setMesAnoReajuste("022017");
        Venda venda = new Venda();
        venda.setStatusDestinacao(criarStatusDetinacao(1));
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Licitacao licitacao = new Licitacao();
        financeiro.setJurosMensal(2.0);
        TipoJuro tipoJuro = new TipoJuro();
        tipoJuro.setId(1);
        financeiro.setTipoJurosMensal(tipoJuro);
        venda.setLicitacao(licitacao);
        venda.setFinanceiro(financeiro);
        venda.setContrato(contrato);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarValorTotalImovel() throws NegocioException{
        TipoPagamento tipoPagamento = new TipoPagamento();
        Financeiro financeiro = new Financeiro();
        Venda venda = new Venda();
        Imovel imovel = new Imovel();
        Contrato contrato = new Contrato();
        Licitacao licitacao = new Licitacao();
        financeiro.setValorFinancidado(new BigDecimal(-1.0));
        financeiro.setValorEntrada(new BigDecimal(-2.0));
        venda.setLicitacao(licitacao);
        venda.setFinanceiro(financeiro);
        venda.setContrato(contrato);
        venda.setStatusDestinacao(StatusDestinacaoStatusBuilder.getBuilder().setId(4).build());
        venda.setDestinacaoImoveis(asList(new DestinacaoImovel()));
        validadorVenda.validadorEspecifico(venda);
    }


    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validaValorTotalImovel() {
        BigDecimal valorEntrada = new BigDecimal(2.0);
        BigDecimal valorFinanciado = new BigDecimal(3.0);
        Financeiro financeiro = new Financeiro();
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setId(1);
        financeiro.setTipoPagamento(tipoPagamento);
        financeiro.setValorEntrada(valorEntrada);
        financeiro.setValorFinancidado(valorFinanciado);
        Venda venda = new Venda();
        venda.setStatusDestinacao(criarStatusDetinacao(1));
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        venda.setFinanceiro(financeiro);
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Licitacao licitacao = new Licitacao();
        venda.setLicitacao(licitacao);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validaValorTotalImovelMaior() {
        Venda venda = new Venda();
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,3, 1);
        Financeiro financeiro =  criarFinanceiro("042018");
        financeiro.setValor(new BigDecimal(50));
        venda.setFinanceiro(financeiro);
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Contrato contrato = new Contrato();
        contrato.setDataInicio(calendar.getTime());
        venda.setContrato(contrato);
        venda.setLicitacao(criarLicitacao());
        venda.setStatusDestinacao(StatusDestinacaoStatusBuilder.getBuilder().setId(4).build());
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarValorTotalImovelMaiorQueZero() {
        Venda venda = new Venda();
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,3, 1);
        venda.setFinanceiro(criarFinanceiro("022017"));
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Contrato contrato = new Contrato();
        contrato.setDataInicio(calendar.getTime());
        venda.setContrato(contrato);
        venda.setLicitacao(criarLicitacao());
        venda.setStatusDestinacao(StatusDestinacaoStatusBuilder.getBuilder().setId(4).build());
        validadorVenda.validadorEspecifico(venda);
    }


    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarMesAnoReajusteContratualAnoCobrancaoMaiorAnoContrato() {
        Venda venda = new Venda();
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,3, 1);

        venda.setFinanceiro(criarFinanceiro("022018"));
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Contrato contrato = new Contrato();
        contrato.setDataInicio(calendar.getTime());
        venda.setContrato(contrato);
        venda.setLicitacao(criarLicitacao());
        venda.setStatusDestinacao(criarStatusDetinacao(1));
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarMesAnoReajusteContratualMesCobrancaoMaiorMesContrato() {
        Venda venda = new Venda();
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,3, 1);

        venda.setFinanceiro(criarFinanceiro("042017"));
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Contrato contrato = new Contrato();
        contrato.setDataInicio(calendar.getTime());
        venda.setContrato(contrato);
        venda.setLicitacao(criarLicitacao());
        venda.setStatusDestinacao(criarStatusDetinacao(1));
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarMesAnoReajusteContratualMesAnoCobrancaoMaiorAnoMesContrato() {
        Venda venda = new Venda();
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setCidadeExterior("");
        imovel.setEndereco(endereco);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,3, 1);

        venda.setFinanceiro(criarFinanceiro("042018"));
        venda.setDestinacaoImoveis(asList(destinacaoImovel));
        Contrato contrato = new Contrato();
        contrato.setDataInicio(calendar.getTime());
        venda.setContrato(contrato);
        venda.setLicitacao(criarLicitacao());
        venda.setStatusDestinacao(criarStatusDetinacao(4));
        validadorVenda.validadorEspecifico(venda);
    }

    private Financeiro criarFinanceiro(String mesAnoReajuste) {
        Financeiro financeiro = new Financeiro();
        financeiro.setTipoPagamento(criarTipoPagamento());
        financeiro.setValorEntrada(new BigDecimal(5.0));
        financeiro.setValorFinancidado(new BigDecimal(3.0));
        financeiro.setValor(new BigDecimal(1));
        financeiro.setMesAnoReajuste(mesAnoReajuste);
        financeiro.setDataInicioCobranca(new Date());
        financeiro.setTipoJurosMensal(criarTipoJuro());
        return financeiro;
    }

    private TipoPagamento criarTipoPagamento() {
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setId(2);
        return tipoPagamento;
    }

    private TipoJuro criarTipoJuro() {
        TipoJuro tipoJuro = new TipoJuro();
        tipoJuro.setId(1);
        return tipoJuro;
    }

    private TipoLicitacao criarTipoLicitacao() {
        TipoLicitacao tipoLicitacao = new TipoLicitacao();
        tipoLicitacao.setId(1);
        return tipoLicitacao;
    }

    private Licitacao criarLicitacao() {
        Licitacao licitacao = new Licitacao();
        licitacao.setTipoLicitacao(criarTipoLicitacao());
        return licitacao;
    }
    
    private StatusDestinacao criarStatusDetinacao(Integer id) {
        StatusDestinacao statusDestinacao = new StatusDestinacao();
        statusDestinacao.setId(id);
        return statusDestinacao;
    }

}
