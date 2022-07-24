package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Financeiro;
import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import br.com.company.fks.destinacao.dominio.entidades.TipoPagamento;
import br.com.company.fks.destinacao.dominio.entidades.TipoReajuste;
import br.com.company.fks.destinacao.dominio.entidades.Venda;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */


/**
 * Classe para fazer as validações específicas de Venda
 */
@Service
public class ValidadorVenda extends ValidadorDestinacao<Venda> {

    private static final Long TIPO_PAGAMENTO_PARCELADO = 2L;
    private static final Long TIPO_JURO_MANUAL = 1L;
    private static final Long TIPO_JURO_INDICE = 2L;

    @Autowired
    private DestinacaoImovelService destinacaoImovelService;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    /**
     *
     * @param venda Recebe o objeto de venda para verifcar os dados específicos
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(Venda venda) throws NegocioException {
        List<String> erros = new ArrayList<>();

        Financeiro financeiro = venda.getFinanceiro();
        Contrato contrato = venda.getContrato();

        validarCamposObrigatoriosLicitacao(venda.getLicitacao(), erros);
        validarCamposObritatoriosFinanceiro(financeiro, venda.getDestinacaoImoveis(), erros);
        validarCamposFinancieroTipoPagamentoParcelado(financeiro, contrato, erros);
        validarCamposObrigatorioContrato(venda, erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }
    }

    private void validarCamposFinancieroTipoPagamentoParcelado(Financeiro financeiro, Contrato contrato, List<String> erros) {
        Optional<TipoPagamento> optional = Optional.ofNullable(financeiro.getTipoPagamento());
        Boolean tipoPagamentoNulo = optional.map(map -> map.getId() == null).orElse(true);
        if (tipoPagamentoNulo) {
            erros.add(mensagemNegocio.get("financeiro.forma.pagamento.vazio"));
        } else if (isTipoPagamentoParcelado(optional.get())) {
            validarValorTotalImovel(financeiro, erros);
            validarMesAnoReajusteContratual(financeiro.getMesAnoReajuste(), contrato.getDataInicio(), erros);
            validarDataCobranca(financeiro.getDataInicioCobranca(), contrato.getDataInicio(), erros);
            validarCamposFinanceiroObrigatoriosTipoParcelado(financeiro, erros);
        }
    }

    private Boolean isTipoPagamentoParcelado(TipoPagamento tipoPagamento) {
        long codigo = tipoPagamento.getId().longValue();
        return codigo == TIPO_PAGAMENTO_PARCELADO;
    }

    private void validarCamposObritatoriosFinanceiro(Financeiro financeiro, List<DestinacaoImovel> destinacaoImoveis, List<String> erros) {
        if (financeiro.getValor() == null) {
            erros.add(mensagemNegocio.get("financeiro.valor.vazio"));
        }

        if (isTipoPagamentoNaoNulo(financeiro.getTipoPagamento())) {
            validarCampoMoeda(financeiro, destinacaoImoveis, erros);
        }
    }

    private Boolean isTipoPagamentoNaoNulo(TipoPagamento tipoPagamento) {
        Optional<TipoPagamento> optional = Optional.ofNullable(tipoPagamento);
        return optional.map(map -> map.getId() != null).orElse(false);
    }

    private void validarCamposFinanceiroObrigatoriosTipoParcelado(Financeiro financeiro, List<String> erros) {
        validarCampoJurosMensal(financeiro, erros);
        validarCampoIndiceMensal(financeiro, erros);
        validarValorEntrada(financeiro, erros);
        validarValorFinanciado(financeiro, erros);
    }

    private void validarCampoMoeda(Financeiro financeiro, List<DestinacaoImovel> destinacaoImoveis, List<String> erros) {
        Boolean imovelExterior = destinacaoImovelService.verificarImovelExterior(destinacaoImoveis);
        if (financeiro.getTipoPagamento().getId().longValue() != TIPO_PAGAMENTO_PARCELADO && imovelExterior) {
            verificarMoedaPreenchida(financeiro, erros);
        }
    }

    private void verificarMoedaPreenchida(Financeiro financeiro, List<String> erros) {
        Optional<TipoMoeda> optional = Optional.ofNullable(financeiro.getTipoMoeda());
        Boolean tipoMoedaPreenchida = optional.map(map -> map.getId() != null).orElse(false);
        if (!tipoMoedaPreenchida) {
            erros.add(mensagemNegocio.get("financeiro.moeda"));
        }
    }

    private void validarCampoJurosMensal(Financeiro financeiro, List<String> erros) {
        if (financeiro.getTipoJurosMensal().getId().intValue() == TIPO_JURO_MANUAL) {
            verificarJurosPreenchidos(financeiro, erros);
        }
    }

    private void verificarJurosPreenchidos(Financeiro financeiro, List<String> erros) {
        if (financeiro.getJurosMensal() == null) {
            erros.add(mensagemNegocio.get("financeiro.juro.mensal"));
        }
    }

    private void validarCampoIndiceMensal(Financeiro financeiro, List<String> erros) {
        if (financeiro.getTipoJurosMensal().getId().intValue() == TIPO_JURO_INDICE) {
            verificarTipoIndiceJurosMensalPreenchido(financeiro, erros);
        }
    }

    private void verificarTipoIndiceJurosMensalPreenchido(Financeiro financeiro, List<String> erros) {
        Optional<TipoReajuste> optional = Optional.ofNullable(financeiro.getTipoIndiceJurosMensal());
        Boolean tipoReajustePreenchido = optional.map(map -> map.getId() != null).orElse(false);
        if (!tipoReajustePreenchido) {
            erros.add(mensagemNegocio.get("financeiro.indice.juro.mensal"));
        }
    }

    private void validarValorEntrada(Financeiro financeiro, List<String> erros) {
        if (financeiro.getValorEntrada() == null) {
            erros.add(mensagemNegocio.get("financeiro.valor.entrada"));
        }
    }

    private void validarValorFinanciado(Financeiro financeiro, List<String> erros) {
        if (financeiro.getValorFinancidado() == null) {
            erros.add(mensagemNegocio.get("financeiro.valor.financiado"));
        }
    }

    private void validarValorTotalImovel(Financeiro financeiro, List<String> erros) {

        if (financeiro.getValorEntrada() != null && financeiro.getValorFinancidado() != null) {
            BigDecimal total = financeiro.getValorEntrada().plus().add(financeiro.getValorFinancidado());

            if (total.compareTo(financeiro.getValor()) < Constants.ZERO) {
                erros.add(mensagemNegocio.get("financeiro.valor.total.diferente"));
            }
        }

    }

    private void validarDataCobranca(Date dataInicioCobranca, Date dataAssinaturaContrato, List<String> erros) {
        if (dataInicioCobranca.compareTo(dataAssinaturaContrato) < Constants.ZERO) {
            erros.add(mensagemNegocio.get("financeiro.data.cobranca"));
        }
    }

    private void validarMesAnoReajusteContratual(String reajusteContratual, Date dataAssinaturaContrato, List<String> erros) {
        String mesCobranca = reajusteContratual.substring(Constants.ZERO, Constants.DOIS);
        String anoCobranca = reajusteContratual.substring(Constants.DOIS, Constants.SEIS);
        Integer mesInicioCobranca = Integer.valueOf(mesCobranca) - Constants.UM;
        Integer anoInicioCobranca = Integer.valueOf(anoCobranca);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataAssinaturaContrato);
        if (mesInicioCobranca < calendar.get(Calendar.MONTH) && anoInicioCobranca <= calendar.get(Calendar.YEAR)) {
            erros.add(mensagemNegocio.get("financeiro.mes.ano"));
        }
    }

}
