package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.CancelamentoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.MotivoCancelamentoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.SuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CancelarSuspensaoImovelService {

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private EntityConverter entityConverter;

    private static final String numero_folha = "00000000";

    private static final String mensagem_observacao = "Suspensão cancelada por cancelamento ou encerramento da Doação do Imóvel";



    public void cancelarSuspensaoImovel(Doacao doacao){
        doacao.getDestinacaoImoveis().forEach(destinacaoImovel -> {
            CancelamentoSuspensaoImovelDTO cancelamentoSuspensaoImovelDTO = criarObjeto(doacao, destinacaoImovel);
            String url = urlIntegracaoUtils.getUrlCancelarSuspensaoImovel();

            requestUtils.doPostBetweenModules(url, cancelamentoSuspensaoImovelDTO);
        });
    }

    private CancelamentoSuspensaoImovelDTO criarObjeto(Doacao doacao, DestinacaoImovel destinacaoImovel){
        CancelamentoSuspensaoImovelDTO cancelamentoSuspensaoImovelDTO = new CancelamentoSuspensaoImovelDTO();
        cancelamentoSuspensaoImovelDTO.setNumeroProcesso(doacao.getNumeroProcesso());
        cancelamentoSuspensaoImovelDTO.setMotivo(new MotivoCancelamentoSuspensaoImovelDTO(2L,"Decisão Administrativa/Judicial",1L));
        cancelamentoSuspensaoImovelDTO.setNumeroFolha(numero_folha);
        cancelamentoSuspensaoImovelDTO.setDataFimVigencia(new Date());
        cancelamentoSuspensaoImovelDTO.setObservacao(mensagem_observacao + "(" + destinacaoImovel.getImovel().getRip() + "/" + destinacaoImovel.getCodigoUtilizacao() + ")");
        SuspensaoImovelDTO suspensaoImovelDTO = getDadosSuspensaoPorRip(destinacaoImovel.getImovel().getRip());
        cancelamentoSuspensaoImovelDTO.setRips(suspensaoImovelDTO.getRips());
        cancelamentoSuspensaoImovelDTO.setIdSuspensao(suspensaoImovelDTO.getId());
        return cancelamentoSuspensaoImovelDTO;
    }

    private SuspensaoImovelDTO getDadosSuspensaoPorRip(String rip){
        String url = urlIntegracaoUtils.getUrlImovelSuspensoMotivo(rip, 6L);
        Object resposta =  requestUtils.doGet(url, Object.class).getBody();
        SuspensaoImovelDTO suspensaoImovelDTO = entityConverter.converterStrict(resposta, SuspensaoImovelDTO.class);
        return suspensaoImovelDTO;
    }


}
