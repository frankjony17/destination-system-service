package br.com.company.fks.destinacao.service.validadores.impl;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Encargo;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Financeiro;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoJuro;
import br.com.company.fks.destinacao.dominio.entidades.TipoLicitacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import br.com.company.fks.destinacao.dominio.entidades.TipoPagamento;
import br.com.company.fks.destinacao.dominio.entidades.TipoReajuste;
import br.com.company.fks.destinacao.dominio.entidades.Venda;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import br.com.company.fks.destinacao.service.UsuarioService;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 21/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidadorVendaTest {

    private static final String CNPJ = "00000000000191";
    private static final String CPF = "00000000191";
    private static final int ID_TIPO_LICITACAO = 1;

    @InjectMocks
    private ValidadorVenda validadorVenda;

    @Mock
    private Venda venda;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private Contrato contrato;

    @Mock
    private Encargo encargo;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private Imovel imovel;

    @Mock
    private Endereco endereco;

    @Mock
    private DadosResponsavel responsavel;

    @Mock
    private Licitacao licitacao;

    @Mock
    private TipoLicitacao tipoLicitacao;

    @Mock
    private Financeiro financeiro;

    @Mock
    private TipoPagamento tipoPagamento;

    @Mock
    private DestinacaoImovelService destinacaoImovelService;

    @Mock
    private TipoMoeda tipoMoeda;

    @Mock
    private TipoJuro tipoJuro;

    @Mock
    private TipoReajuste tipoReajuste;
    
    @Mock
    private StatusDestinacao statusDestinacao;    

    @Before
    public void setUp() {
        Calendar calendarDataContratro = Calendar.getInstance();
        calendarDataContratro.set(2016, Calendar.NOVEMBER, 16);
        Calendar calendarDataEncargo = Calendar.getInstance();
        calendarDataEncargo.set(2016, Calendar.NOVEMBER, 15);

        when(contrato.getDataInicio()).thenReturn(calendarDataContratro.getTime());
        when(encargo.getDataCumprimento()).thenReturn(calendarDataEncargo.getTime());
        when(statusDestinacao.getId()).thenReturn(1);
        
        mockVenda();

        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        when(usuarioLogadoDTO.getJurisdicoes()).thenReturn(new HashSet<>(asList("MG", "DF", "SP")));

        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getEndereco()).thenReturn(endereco);
        when(endereco.getUf()).thenReturn("DF");
        when(responsavel.getCnpj()).thenReturn(CPF);

        mockFinanceiro();

        mockLicitacao();

        when(destinacaoImovelService.verificarImovelExterior(anyListOf(DestinacaoImovel.class))).thenReturn(Boolean.FALSE);
    }

    private void mockVenda() {
        when(venda.getContrato()).thenReturn(contrato);
        when(venda.getDestinacaoImoveis()).thenReturn(asList(destinacaoImovel));
        when(venda.getDadosResponsavel()).thenReturn(responsavel);
        when(venda.getCodFundamentoLegal()).thenReturn(1L);
        when(venda.getLicitacao()).thenReturn(licitacao);
        when(venda.getFinanceiro()).thenReturn(financeiro);
        when(venda.getStatusDestinacao()).thenReturn(statusDestinacao);
    }

    private void mockFinanceiro() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 16);

        when(tipoPagamento.getId()).thenReturn(1);
        when(tipoMoeda.getId()).thenReturn(1);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getValor()).thenReturn(new BigDecimal(100));
        when(financeiro.getTipoMoeda()).thenReturn(tipoMoeda);
        when(financeiro.getMesAnoReajuste()).thenReturn("112016");
        when(financeiro.getDataInicioCobranca()).thenReturn(calendar.getTime());
        when(financeiro.getTipoJurosMensal()).thenReturn(tipoJuro);
        when(financeiro.getValorEntrada()).thenReturn(new BigDecimal(50));
        when(financeiro.getValorFinancidado()).thenReturn(new BigDecimal(50));
        when(tipoJuro.getId()).thenReturn(1);
    }

    private void mockLicitacao() {
        when(tipoLicitacao.getId()).thenReturn(ID_TIPO_LICITACAO);
        when(licitacao.getTipoLicitacao()).thenReturn(tipoLicitacao);
        when(licitacao.getNumeroProcesso()).thenReturn("212");
    }

    @Test
    public void validadorEspecificoSucesso() {
        try {
            validadorVenda.validadorEspecifico(venda);
            assertTrue(Boolean.TRUE);
        } catch (NegocioException e) {
            fail();
        }
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoTipoLicitacaoNulo() throws NegocioException {
        when(licitacao.getTipoLicitacao()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoIdTipoLicitacaoNulo() throws NegocioException {
        when(tipoLicitacao.getId()).thenReturn(null);
        when(licitacao.getTipoLicitacao()).thenReturn(tipoLicitacao);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoNumeroProcessoNulo() throws NegocioException {
        when(licitacao.getNumeroProcesso()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoTipoPagamentoNulo() throws NegocioException {
        when(financeiro.getTipoPagamento()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoTipoPagamentoIdNulo() throws NegocioException {
        when(tipoPagamento.getId()).thenReturn(null);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoTipoMoedaNulo() throws NegocioException {
        when(destinacaoImovelService.verificarImovelExterior(anyListOf(DestinacaoImovel.class))).thenReturn(Boolean.TRUE);
        when(financeiro.getTipoMoeda()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoTipoMoedaIdNulo() throws NegocioException {
        when(destinacaoImovelService.verificarImovelExterior(anyListOf(DestinacaoImovel.class))).thenReturn(Boolean.TRUE);
        when(tipoMoeda.getId()).thenReturn(null);
        when(financeiro.getTipoMoeda()).thenReturn(tipoMoeda);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoValorTotalMenorQueValorEntradaEValorFinanciado() throws NegocioException {
        when(tipoPagamento.getId()).thenReturn(2);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getValorEntrada()).thenReturn(new BigDecimal(20));
        when(financeiro.getValorFinancidado()).thenReturn(new BigDecimal(40));
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorDataCobrancaMenorDataInicioContrato() throws NegocioException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 10);
        when(tipoPagamento.getId()).thenReturn(2);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getDataInicioCobranca()).thenReturn(calendar.getTime());
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoMesAnoReajusteMenor() throws NegocioException {
        when(tipoPagamento.getId()).thenReturn(2);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getMesAnoReajuste()).thenReturn("092016");
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoJuroMensalNulo() throws NegocioException {
        when(tipoPagamento.getId()).thenReturn(2);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getJurosMensal()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoIndiceMensalNulo() throws NegocioException {
        when(tipoPagamento.getId()).thenReturn(2);
        when(tipoJuro.getId()).thenReturn(2);
        when(financeiro.getTipoJurosMensal()).thenReturn(tipoJuro);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getTipoIndiceJurosMensal()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoIndiceMensalIdNulo() throws NegocioException {
        when(tipoPagamento.getId()).thenReturn(2);
        when(tipoReajuste.getId()).thenReturn(null);
        when(tipoJuro.getId()).thenReturn(2);
        when(financeiro.getTipoJurosMensal()).thenReturn(tipoJuro);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getTipoIndiceJurosMensal()).thenReturn(tipoReajuste);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoValorEntradaNulo() throws NegocioException {
        when(tipoPagamento.getId()).thenReturn(2);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getValorEntrada()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoValorFinanciadoNulo() throws NegocioException {
        when(tipoPagamento.getId()).thenReturn(2);
        when(financeiro.getTipoPagamento()).thenReturn(tipoPagamento);
        when(financeiro.getValorFinancidado()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoDataInicioContratoNulo() throws NegocioException {
        when(contrato.getDataInicio()).thenReturn(null);
        when(venda.getContrato()).thenReturn(contrato);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoContratoNulo() throws NegocioException {
        when(venda.getContrato()).thenReturn(null);
        validadorVenda.validadorEspecifico(venda);
    }

    @Test(expected = NegocioException.class)
    public void validadorValorNulo() throws NegocioException {
        when(financeiro.getValor()).thenReturn(null);
        when(financeiro.getTipoPagamento()).thenReturn(null);
        when(venda.getFinanceiro()).thenReturn(financeiro);
        validadorVenda.validadorEspecifico(venda);
    }
}
