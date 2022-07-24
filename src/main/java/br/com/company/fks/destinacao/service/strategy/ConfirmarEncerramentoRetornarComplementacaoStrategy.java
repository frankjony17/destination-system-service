package br.com.company.fks.destinacao.service.strategy;

import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.EncerramentoDestinacao;
import br.com.company.fks.destinacao.repository.EncerramentoDestinacaoRepository;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import lombok.AllArgsConstructor;

import static br.com.company.fks.destinacao.service.EncerramentoDestinacaoService.ID_PENDENCIA_CANCELAMENTO;
import static br.com.company.fks.destinacao.service.EncerramentoDestinacaoService.ID_PENDENCIA_RETORNADO;
@AllArgsConstructor
public class ConfirmarEncerramentoRetornarComplementacaoStrategy implements IConfirmarEncerramentoStrategy {

    private EncerramentoDestinacao encerramentoDestinacao;

    private EncerramentoDestinacaoRepository encerramentoDestinacaoRepository;

    private DestinacaoPendenciaService destinacaoPendenciaService;

    private Destinacao destinacao;

    @Override
    public void confirmarEncerramento() {
        encerramentoDestinacaoRepository.save(encerramentoDestinacao);
        destinacaoPendenciaService.removerPendencia(destinacao.getId(), ID_PENDENCIA_CANCELAMENTO );
        destinacaoPendenciaService.gerarPendencia(destinacao, ID_PENDENCIA_RETORNADO);
    }

}
