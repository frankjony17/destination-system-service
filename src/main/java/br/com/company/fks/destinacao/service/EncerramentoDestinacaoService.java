package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.EncerramentoDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import br.com.company.fks.destinacao.dominio.entidades.EncerramentoDestinacao;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.DoacaoRepository;
import br.com.company.fks.destinacao.repository.EncerramentoDestinacaoRepository;
import br.com.company.fks.destinacao.service.strategy.ConfirmarEncerramentoAceitarStrategy;
import br.com.company.fks.destinacao.service.strategy.ConfirmarEncerramentoIndeferidoStrategy;
import br.com.company.fks.destinacao.service.strategy.ConfirmarEncerramentoRetornarComplementacaoStrategy;
import br.com.company.fks.destinacao.service.strategy.IConfirmarEncerramentoStrategy;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum.DOACAO;
import static br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum.VENDA;

/**
 *
 * @author haillanderson
 */

@Service
public class EncerramentoDestinacaoService {

    
    public static final long ID_PENDENCIA_CANCELAMENTO = 7L;

    public static final long ID_PENDENCIA_RETORNADO = 8L;

    @Autowired
    private EncerramentoDestinacaoRepository encerramentoDestinacaoRepository;

    @Autowired
    private EntityConverter converter;

    @Autowired
    private DestinacaoRepository destinacaoRepository;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private CancelarSuspensaoImovelService cancelarSuspensaoImovelService;

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Transactional(propagation = Propagation.REQUIRED)
    public void submeterSuperintendente(Long idDestinacao, EncerramentoDestinacaoDTO dto) {
        EncerramentoDestinacao encerramentoDestinacao = converter.converterStrict(dto, EncerramentoDestinacao.class);
        encerramentoDestinacao.setArquivos(arquivoService.salvarListaArquivo(dto.getArquivos()));
        Destinacao destinacao = destinacaoRepository.findOne(idDestinacao);
        encerramentoDestinacao.setIsAtivo(true);
        encerramentoDestinacao.setDestinacao(destinacaoRepository.save(destinacao));
        encerramentoDestinacaoRepository.save(encerramentoDestinacao);
        destinacaoPendenciaService.removerPendencia(destinacao.getId(), ID_PENDENCIA_RETORNADO);
        destinacaoPendenciaService.gerarPendencia(destinacao, ID_PENDENCIA_CANCELAMENTO);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void confirmarEncerramento(Long idDestinacao, EncerramentoDestinacaoDTO dto) {
        EncerramentoDestinacao encerramentoDestinacao = converter.converterStrict(dto, EncerramentoDestinacao.class);
        Destinacao destinacao = destinacaoRepository.findOne(idDestinacao);
        encerramentoDestinacao.setDestinacao(destinacao);

        Integer codigo = encerramentoDestinacao.getDespacho().getCodigo();
        IConfirmarEncerramentoStrategy strategy;

        if(codigo == 1) {
            strategy = new ConfirmarEncerramentoAceitarStrategy(destinacaoRepository, encerramentoDestinacaoRepository, destinacaoPendenciaService, destinacao, encerramentoDestinacao);
            cancelarSuspensaoImovel(destinacao);
        }
        else if (codigo == 2) {
            strategy = new ConfirmarEncerramentoIndeferidoStrategy(destinacaoPendenciaService, destinacao, encerramentoDestinacao, encerramentoDestinacaoRepository);
        }
        else {
            strategy = new ConfirmarEncerramentoRetornarComplementacaoStrategy(encerramentoDestinacao, encerramentoDestinacaoRepository, destinacaoPendenciaService, destinacao);
        }

        strategy.confirmarEncerramento();
    }

    @Transactional(readOnly = true)
    public EncerramentoDestinacao buscarPorIdDestinacao(Long idDestinacao) {
        EncerramentoDestinacao encerramentoDestinacao = encerramentoDestinacaoRepository.finByIdDestinacao(idDestinacao);
        if (encerramentoDestinacao == null){
            return new EncerramentoDestinacao();
        }
        return encerramentoDestinacao;
    }

    private void cancelarSuspensaoImovel(Destinacao destinacao){
         if(destinacao.getTipoDestinacao().getDescricao().equals(VENDA.getDescricao())){
            desfazerCancelamentoImovel(destinacao);
         } else if(destinacao.getTipoDestinacao().getDescricao().equals(DOACAO.getDescricao())){
             Doacao doacao = doacaoRepository.findOne(destinacao.getId());
             if(doacao.getExisteEncargo()){
                 cancelarSuspensaoImovelService.cancelarSuspensaoImovel(doacao);
             }
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
