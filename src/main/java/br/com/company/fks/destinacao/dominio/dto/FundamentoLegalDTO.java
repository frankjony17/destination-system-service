package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class FundamentoLegalDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private TipoDestinacaoDTO tipoDestinacao;
    @Getter
    @Setter
    private Boolean isExigirAtoAutorizativo;
    @Getter
    @Setter
    private Boolean isSuperintendente;
    @Getter
    @Setter
    private Boolean isSecretario;
    @Getter
    @Setter
    private Boolean isMinistro;
    @Getter
    @Setter
    private Boolean isDiferencaRegiao;
    @Getter
    @Setter
    private Boolean isDiferencaNatureza;
    @Getter
    @Setter
    private Boolean isIncluirAreaFracaoResponsavel;
    @Getter
    @Setter
    private Boolean isPermitirImoveisEspelhoDAgua;
    @Getter
    @Setter
    private Boolean isPermitirImoveisCavidadeNatural;
    @Getter
    @Setter
    private Boolean isNaturezaUrbana;
    @Getter
    @Setter
    private Boolean isNaturezaRural;
    @Getter
    @Setter
    private Boolean isEmProcessoIncorporacao;
    @Getter
    @Setter
    private Boolean isincorporado;
    @Getter
    @Setter
    private Boolean isNaoSeAplica;
    @Getter
    @Setter
    private Boolean isNecessarioAvaliacaoImovel;
    @Getter
    @Setter
    private Boolean isAvaliacaoPvgSpu;
    @Getter
    @Setter
    private Boolean isAvaliacaoReferenciaValorVenal;
    @Getter
    @Setter
    private Boolean isAvaliacaoReferenciaIncra;
    @Getter
    @Setter
    private Boolean isPermitirImovelParcelado;
    @Getter
    @Setter
    private List<UtilizacaoFundamentoDTO> utilizacoes;
    @Getter
    @Setter
    private List<NaturezaJuridicaPermitidaDTO> naturezaJuridicaPermitidas;
    @Getter
    @Setter
    private List<FormaDeIncorporacaoPermitidaDTO> formaDeIncorporacaoPermitidas;
    @Getter
    @Setter
    private List<TipoAquisicaoPermitidaDTO> tipoAquisicaoPermitidas;
    @Getter
    @Setter
    private List<EntidadeExtintaDTO> entidadeExtintas;

    public FundamentoLegalDTO () {
    }

    public FundamentoLegalDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}
