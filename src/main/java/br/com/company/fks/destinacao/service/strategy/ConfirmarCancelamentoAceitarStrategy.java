package br.com.company.fks.destinacao.service.strategy;

import br.com.company.fks.destinacao.dominio.entidades.CancelamentoEncerramento;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.repository.CancelamentoEncerramentoUtilizacaoRepository;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import lombok.AllArgsConstructor;

import static br.com.company.fks.destinacao.service.CancelamentoEncerramentoUtilizacaoService.ID_PENDENCIA_CANCELAMENTO;

@AllArgsConstructor
public class ConfirmarCancelamentoAceitarStrategy implements IConfirmarCancelamentoStrategy {

    private DestinacaoRepository destinacaoRepository;

    private CancelamentoEncerramentoUtilizacaoRepository repository;

    private DestinacaoPendenciaService destinacaoPendenciaService;

    private Destinacao destinacao;

    private CancelamentoEncerramento cancelamentoEncerramento;

    @Override
    public void confirmarCancelamento() {
        StatusDestinacao statusDestinacao = new StatusDestinacao();


        statusDestinacao.setId(2);
        destinacao.setStatusDestinacao(statusDestinacao);
        cancelamentoEncerramento.setDestinacao(destinacaoRepository.save(destinacao));

        repository.save(cancelamentoEncerramento);
        destinacaoPendenciaService.removerPendencia(destinacao.getId(), ID_PENDENCIA_CANCELAMENTO);
    }
}
