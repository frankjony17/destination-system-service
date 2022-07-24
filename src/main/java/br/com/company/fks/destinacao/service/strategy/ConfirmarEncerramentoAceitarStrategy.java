package br.com.company.fks.destinacao.service.strategy;

import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.EncerramentoDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.EncerramentoDestinacaoRepository;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import lombok.AllArgsConstructor;


import static br.com.company.fks.destinacao.service.EncerramentoDestinacaoService.ID_PENDENCIA_CANCELAMENTO;
@AllArgsConstructor
public class ConfirmarEncerramentoAceitarStrategy implements IConfirmarEncerramentoStrategy {

    private DestinacaoRepository destinacaoRepository;

    private EncerramentoDestinacaoRepository repository;

    private DestinacaoPendenciaService destinacaoPendenciaService;

    private Destinacao destinacao;

    private EncerramentoDestinacao encerramentoDestinacao;

    @Override
    public void confirmarEncerramento() {
        StatusDestinacao statusDestinacao = new StatusDestinacao();

        statusDestinacao.setId(5);
        destinacao.setStatusDestinacao(statusDestinacao);
        encerramentoDestinacao.setDestinacao(destinacaoRepository.save(destinacao));
        repository.save(encerramentoDestinacao);
        destinacaoPendenciaService.removerPendencia(destinacao.getId(), ID_PENDENCIA_CANCELAMENTO);

    }
}
