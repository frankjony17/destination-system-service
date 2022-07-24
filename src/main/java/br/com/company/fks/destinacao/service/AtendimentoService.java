package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.RequerimentoDestinacaoDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por realizar buscas por requerimento e verificar número processo
 * Created by Basis Tecnologia on 06/10/2016.
 */
@Service
public class AtendimentoService {

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    /**
     * Busca requerimento por número de atendimento
     * @param numeroAtendimento
     * @return RequerimentoDestinacaoDTO encontrado através do número do atendimento
     * @throws IntegracaoException
     */
    public RequerimentoDestinacaoDTO findRequerimentoByNumeroAtendimento(String numeroAtendimento) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlGetRequerimentoByNumeroAtendimento(numeroAtendimento);
        RequerimentoDestinacaoDTO requerimento = (RequerimentoDestinacaoDTO) requestUtils.doGet(url, RequerimentoDestinacaoDTO.class).getBody();
        if(requerimento == null) {
            throw new IntegracaoException(mensagemNegocio.get("atendimento.nao.encontrado"));
        }
        return requerimento;
    }

    /**
     * Busca requerimento por número de processo
     * @param numeroProcesso
     * @return RequerimentoDestinacaoDTO encontrado através do número do processo
     * @throws IntegracaoException
     */
    public RequerimentoDestinacaoDTO findRequerimentoByNumeroProcesso(String numeroProcesso) throws IntegracaoException{
        String url = urlIntegracaoUtils.getUrlGetRequerimentoByNumeroProcesso(numeroProcesso);
        RequerimentoDestinacaoDTO requerimento = (RequerimentoDestinacaoDTO) requestUtils.doGet(url, RequerimentoDestinacaoDTO.class).getBody();
        if(requerimento == null){
            throw new IntegracaoException(mensagemNegocio.get("processo.nao.encontrado"));
        }
        return requerimento;
    }

    /**
     * Verifica número do processo
     * @param idRequerimento
     * @param numeroProcesso
     * @return true ou false
     * @throws IntegracaoException
     */
    public Boolean verificarNumeroProcedimentoSei(Long idRequerimento, String numeroProcesso) throws IntegracaoException{
        String url = urlIntegracaoUtils.getVerificarNumeroProcesso(idRequerimento, numeroProcesso);
        Boolean existeProcesso = (Boolean) requestUtils.doGet(url, Boolean.class).getBody();
        if(!existeProcesso) {
            throw new IntegracaoException(mensagemNegocio.get("processo.nao.encontrado"));
        }
        return existeProcesso;
    }
}
