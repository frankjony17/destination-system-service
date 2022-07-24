package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class FinanceiroIT {
    private Financeiro financeiro;

    @Before
    public void setUp(){
        financeiro = new Financeiro();
    }

    @Test
    public void getId() throws Exception {
        financeiro.setId(1l);
        assertEquals(Long.valueOf(1), financeiro.getId());
    }

    @Test
    public void getNuFCC() throws Exception {
        financeiro.setNuFCC("01");
        assertEquals("01", financeiro.getNuFCC());
    }

    @Test
    public void getValor() throws Exception {
        financeiro.setValor(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, financeiro.getValor());
    }

    @Test
    public void getTipoPeriocidade() throws Exception {
        TipoPeriocidade tipoPeriocidade = new TipoPeriocidade();
        financeiro.setTipoPeriocidade(tipoPeriocidade);
        assertEquals(tipoPeriocidade, financeiro.getTipoPeriocidade());
    }

    @Test
    public void getDataInicioCobranca() throws Exception {
        Date date = new Date();
        financeiro.setDataInicioCobranca(date);
        assertEquals(date, financeiro.getDataInicioCobranca());
    }

    @Test
    public void getDataInicioCobrancaNull(){
        financeiro.setDataInicioCobranca(null);
        assertEquals(null, financeiro.getDataInicioCobranca());
    }

    @Test
    public void getTipoIndiceReajusteAnual() throws Exception {
        TipoReajuste tipoReajuste = new TipoReajuste();
        financeiro.setTipoIndiceReajusteAnual(tipoReajuste);
        assertEquals(tipoReajuste, financeiro.getTipoIndiceReajusteAnual());
    }

    @Test
    public void getCarenciaMeses() throws Exception {
        financeiro.setCarenciaMeses(1l);
        assertEquals(Long.valueOf(1), financeiro.getCarenciaMeses());
    }

    @Test
    public void getMesAnoReajuste() throws Exception {
        financeiro.setMesAnoReajuste("01");
        assertEquals("01", financeiro.getMesAnoReajuste());
    }

    @Test
    public void getDiaVencimento() throws Exception {
        financeiro.setDiaVencimento("01");
        assertEquals("01", financeiro.getDiaVencimento());
    }

    @Test
    public void getTipoJurosMensal() throws Exception {
        TipoJuro tipoJuro = new TipoJuro();
        financeiro.setTipoJurosMensal(tipoJuro);
        assertEquals(tipoJuro, financeiro.getTipoJurosMensal());
    }

    @Test
    public void getJurosMensal() throws Exception {
        financeiro.setJurosMensal(Double.valueOf(1));
        assertEquals(Double.valueOf(1), financeiro.getJurosMensal());
    }

    @Test
    public void getTipoIndiceJurosMensal() throws Exception {
        TipoReajuste tipoReajuste = new TipoReajuste();
        financeiro.setTipoIndiceJurosMensal(tipoReajuste);
        assertEquals(tipoReajuste, financeiro.getTipoIndiceJurosMensal());
    }

    @Test
    public void getMultaInadimplacia() throws Exception {
        financeiro.setMultaInadimplacia(Double.valueOf(1));
        assertEquals(Double.valueOf(1), financeiro.getMultaInadimplacia());
    }

    @Test
    public void getNumeroParcelas() throws Exception {
        financeiro.setNumeroParcelas(1);
        assertEquals(Integer.valueOf(1), financeiro.getNumeroParcelas());
    }

    @Test
    public void getTipoPagamento() throws Exception {
        TipoPagamento tipoPagamento = new TipoPagamento();
        financeiro.setTipoPagamento(tipoPagamento);
        assertEquals(tipoPagamento, financeiro.getTipoPagamento());
    }

    @Test
    public void getTipoMoeda() throws Exception {
        TipoMoeda tipoMoeda = new TipoMoeda();
        financeiro.setTipoMoeda(tipoMoeda);
        assertEquals(tipoMoeda, financeiro.getTipoMoeda());
    }

    @Test
    public void getValorEntrada() throws Exception {
        financeiro.setValorEntrada(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, financeiro.getValorEntrada());
    }

    @Test
    public void getValorFinancidado() throws Exception {
        financeiro.setValorFinancidado(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, financeiro.getValorFinancidado());
    }
}
