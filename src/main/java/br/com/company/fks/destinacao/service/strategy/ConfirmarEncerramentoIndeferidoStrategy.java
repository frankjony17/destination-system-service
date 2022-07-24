package br.com.company.fks.destinacao.service.strategy;

import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.EncerramentoDestinacao;
import br.com.company.fks.destinacao.repository.EncerramentoDestinacaoRepository;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import lombok.AllArgsConstructor;

import static br.com.company.fks.destinacao.service.EncerramentoDestinacaoService.ID_PENDENCIA_CANCELAMENTO;
@AllArgsConstructor
public class ConfirmarEncerramentoIndeferidoStrategy implements IConfirmarEncerramentoStrategy {

    private DestinacaoPendenciaService destinacaoPendenciaService;

    private Destinacao destinacao;

    private EncerramentoDestinacao encerramentoDestinacao;

    private EncerramentoDestinacaoRepository encerramentoDestinacaoRepository;

    @Override
    public void confirmarEncerramento() {
        encerramentoDestinacao.setIsAtivo(false);
        encerramentoDestinacaoRepository.save(encerramentoDestinacao);
        destinacaoPendenciaService.removerPendencia(destinacao.getId(), ID_PENDENCIA_CANCELAMENTO);
    }
}
