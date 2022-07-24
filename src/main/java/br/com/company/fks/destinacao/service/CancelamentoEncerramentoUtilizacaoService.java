package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.CancelamentoEncerramentoDTO;
import br.com.company.fks.destinacao.dominio.dto.CancelamentoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoPendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.MotivoCancelamentoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.RipSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.SuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.CancelamentoEncerramento;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.Venda;
import br.com.company.fks.destinacao.repository.CancelamentoEncerramentoUtilizacaoRepository;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.DoacaoRepository;
import br.com.company.fks.destinacao.repository.VendaRepository;
import br.com.company.fks.destinacao.service.strategy.ConfirmarCancelamentoAceitarStrategy;
import br.com.company.fks.destinacao.service.strategy.ConfirmarCancelamentoIndefiridoStrategy;
import br.com.company.fks.destinacao.service.strategy.ConfirmarCancelamentoRetornarComplementacaoStrategy;
import br.com.company.fks.destinacao.service.strategy.IConfirmarCancelamentoStrategy;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum.DOACAO;
import static br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum.VENDA;

/**
 * Created by tawan-souza on 19/12/17.
 */
@Service
public class CancelamentoEncerramentoUtilizacaoService {

    public static final long ID_PENDENCIA_CANCELAMENTO = 5L;

    public static final long ID_PENDENCIA_CANCELAMENTO_RETORNADO = 6L;

    @Autowired
    private CancelamentoEncerramentoUtilizacaoRepository repository;

    @Autowired
    private EntityConverter converter;

    @Autowired
    private DestinacaoRepository destinacaoRepository;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private CancelarSuspensaoImovelService cancelarSuspensaoImovelService;



    @Transactional(propagation = Propagation.REQUIRED)
    public void submeterSuperintendente(Long idDestinacao, CancelamentoEncerramentoDTO dto) {
        CancelamentoEncerramento cancelamentoEncerramento = converter.converterStrict(dto, CancelamentoEncerramento.class);
        cancelamentoEncerramento.setArquivos(arquivoService
                .salvarListaArquivo(dto.getArquivos()));

        Destinacao destinacao = destinacaoRepository.findOne(idDestinacao);
        cancelamentoEncerramento.setIsAtivo(true);
        cancelamentoEncerramento.setDestinacao(destinacaoRepository.save(destinacao));

        repository.save(cancelamentoEncerramento);

        destinacaoPendenciaService.removerPendencia(destinacao.getId(), ID_PENDENCIA_CANCELAMENTO_RETORNADO);
        destinacaoPendenciaService.gerarPendencia(destinacao, ID_PENDENCIA_CANCELAMENTO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void confirmarCancelamento(Long idDestinacao, CancelamentoEncerramentoDTO dto) {
        CancelamentoEncerramento cancelamentoEncerramento = converter.converterStrict(dto, CancelamentoEncerramento.class);
        Destinacao destinacao = destinacaoRepository.findOne(idDestinacao);
        cancelamentoEncerramento.setDestinacao(destinacao);

        Integer codigo = cancelamentoEncerramento.getDespacho().getCodigo();
        IConfirmarCancelamentoStrategy strategy;

        if (codigo == 1){
            strategy = new ConfirmarCancelamentoAceitarStrategy(destinacaoRepository, repository, destinacaoPendenciaService,
                destinacao, cancelamentoEncerramento);
            cancelarSuspensao(destinacao);
        } else if (codigo == 2) {
            strategy = new ConfirmarCancelamentoIndefiridoStrategy(repository, destinacaoPendenciaService, destinacao,
                cancelamentoEncerramento);
        } else {
            strategy = new ConfirmarCancelamentoRetornarComplementacaoStrategy(repository, destinacaoPendenciaService,
                destinacao, cancelamentoEncerramento);
        }
        strategy.confirmarCancelamento();
    }

    @Transactional(readOnly = true)
    public CancelamentoEncerramento buscarPorIdDestinacao(Long idDestinacao) {
        CancelamentoEncerramento cancelamentoEncerramento = repository.finByIdDestinacao(idDestinacao);
        if (cancelamentoEncerramento == null){
            return new CancelamentoEncerramento();
        }
        return cancelamentoEncerramento;
    }

    private void cancelarSuspensao(Destinacao destinacao){
        if(destinacao.getTipoDestinacao().getDescricao().equals(DOACAO.getDescricao())){
            Doacao doacao = doacaoRepository.findOne(destinacao.getId());
            if(doacao.getExisteEncargo()){
                cancelarSuspensaoImovelService.cancelarSuspensaoImovel(doacao);
            }
        }else if(destinacao.getTipoDestinacao().getDescricao().equals(VENDA.getDescricao())){
            desfazerCancelamentoImovel(destinacao);
        }
    }

    private void desfazerCancelamentoImovel(Destinacao destinacao){
        destinacao.getDestinacaoImoveis().forEach(destinacaoImovel -> {
           String rip = destinacaoImovel.getImovel().getRip();
           String url = urlIntegracaoUtils.getUrlDesfazerCancelamento(rip);
           requestUtils.doDeleteBetweenModules(url);
        });
    }


}
