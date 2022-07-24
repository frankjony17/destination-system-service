package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.RequerimentoConsultaDTO;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.DataUtil;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe responsável por realizar operações no Requerimento
 * Created by diego on 05/12/16.
 */

@Service
public class RequerimentoService {

    private static final String RESPOSTA = "resposta";
    private static final String DD_MM_YYYY = "dd/MM/yyyy";

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    /**
     * Realiza busca por id do requerimento
     * @param id
     * @return Object requerimento encontrado pelo id
     * @throws IntegracaoException
     */
    public Object buscarRequerimento(Long id) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlGetRequerimentoByID(id);
        ResponseEntity responseEntity = requestUtils.doGet(url, Object.class);
        LinkedHashMap body = (LinkedHashMap) responseEntity.getBody();
        Object requerimento = body.get(RESPOSTA);

        if (requerimento != null && ((ArrayList) requerimento).size() > Constants.ZERO) {
            return ((ArrayList) requerimento).get(0);
        }
        throw new IntegracaoException("ERRO BUSCAR REQUERIMENTO");
    }

    /**
     * Busca no Integração dados do Requerimento e Análise Técnica
     * @param requerimentoConsultaDTO
     * @return Object requerimento encontrado
     */
    public Object consultaRequerimentoEAnaliseTecnica(RequerimentoConsultaDTO requerimentoConsultaDTO) {
        String url = urlIntegracaoUtils.getBuscarRequerimentoAnaliseTecnica();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("idServico", requerimentoConsultaDTO.getIdServico())
                .queryParam("nuAtendimento", requerimentoConsultaDTO.getNuAtendimento())
                .queryParam("nomeRequerente", requerimentoConsultaDTO.getNomeRequerente())
                .queryParam("cpfCnpj", requerimentoConsultaDTO.getCpfCnpj())
                .queryParam("uf", requerimentoConsultaDTO.getUf())
                .queryParam("situacao", requerimentoConsultaDTO.getSituacao())
                .queryParam("nomeResponsavel", requerimentoConsultaDTO.getNomeResponsavel())
                .queryParam("dataSolicitacaoInicio", DataUtil.formataDataHora(requerimentoConsultaDTO.getDataSolicitacaoInicio(), DD_MM_YYYY))
                .queryParam("dataSolicitacaoFinal", DataUtil.formataDataHora(requerimentoConsultaDTO.getDataSolicitacaoFinal(), DD_MM_YYYY))
                .queryParam("dataEnvioAnaliseInicio", DataUtil.formataDataHora(requerimentoConsultaDTO.getDataEnvioAnaliseInicio(), DD_MM_YYYY))
                .queryParam("dataEnvioAnaliseFinal", DataUtil.formataDataHora(requerimentoConsultaDTO.getDataEnvioAnaliseFinal(), DD_MM_YYYY))
                .queryParam("offset", requerimentoConsultaDTO.getPage() - 1)
                .queryParam("limit", requerimentoConsultaDTO.getLimit());

        ResponseEntity responseEntity = requestUtils.getForEntity(builder.build().encode().toUri(), Object.class);
        LinkedHashMap body = (LinkedHashMap) responseEntity.getBody();

        return body.get(RESPOSTA);

    }

    /**
     * Busca análise técnica requerimento por id no integração e serviços
     * @param id
     * @return Object análise técnica requerimento
     * @throws IntegracaoException
     */
    public Object buscarAnaliseTecnicaRequerimento(Long id) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlGetRequerimentoAnaliseTecnicaByID(id);
        ResponseEntity responseEntity = requestUtils.doGet(url, Object.class);
        LinkedHashMap body = (LinkedHashMap) responseEntity.getBody();

        if (body != null) {
            return (ArrayList) body.get(RESPOSTA);
        }
        throw new IntegracaoException("ERRO BUSCAR ANALISE TECNICA REQUERIMENTO");
    }

    /**
     * Busca dados do atendimento pelo id
     * @param id
     * @return Map<Strin, String> map com os dados do atendimento encontrado pelo id
     * @throws IntegracaoException
     */
    public Map<String, String> getDadosAtendimento(Long id) throws IntegracaoException {
        Map<String, String> dadosAtendimento = new HashMap<>();
        Object requerimento = buscarRequerimento(id);
        LinkedHashMap atendimento = (LinkedHashMap) ((LinkedHashMap) requerimento).get("atendimento");
        LinkedHashMap requerente = (LinkedHashMap) ((LinkedHashMap) requerimento).get("requerente");
        String numeroAtendimento = (String) atendimento.get("numero");
        String nomeResponsavel = (String) requerente.get("nome");
        String email = (String) requerente.get("email");
        dadosAtendimento.put("numeroAtendimento", numeroAtendimento);
        dadosAtendimento.put("nomeResponsavel", nomeResponsavel);
        dadosAtendimento.put("email", email);
        return dadosAtendimento;
    }

    /**
     * Identifica o requerimento e altera o status do requerimento
     * @param idRequerimento
     * @param statusRequerimento
     */
    public void alterarStatusRequerimento(Long idRequerimento, String statusRequerimento) {
        String url = urlIntegracaoUtils.getUrlGetAlterarStatus(statusRequerimento, idRequerimento);
        requestUtils.doPut(url, Object.class);
    }

    /**
     * Identifica o requerimento e insere o status da análise técnica Enum no status do requerimento
     * @param idRequerimento
     * @param statusAnaliseTecnicaEnum
     */
    public void alterarStatusRequerimento(Long idRequerimento, StatusAnaliseTecnicaEnum statusAnaliseTecnicaEnum) {
        String url = urlIntegracaoUtils.getUrlGetAlterarStatus(statusAnaliseTecnicaEnum.name(), idRequerimento);
        requestUtils.doPut(url, Object.class);
    }

    /**
     * Identifica requerimento e análise técnica e altera o status da análise técnica
     * @param idRequerimento
     * @param statusAnaliseTecnicaEnum
     */
    public void alterarStatusAnaliseTecnica(Long idRequerimento, StatusAnaliseTecnicaEnum statusAnaliseTecnicaEnum) {
        String url = urlIntegracaoUtils.getUrlAlterarStatusAnaliseTecnica(idRequerimento, statusAnaliseTecnicaEnum);
        requestUtils.doPut(url, Object.class);
    }

    /**
     * Busrca pendência requerimento pelo id do requerimento
     * @param idRequerimento
     * @param offset
     * @param limit
     * @return Object com os dados encontrados pelo id do requerimento
     * @throws IntegracaoException
     */
    public Object buscarPendenciaRequerimento(Long idRequerimento, Integer offset, Integer limit) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlGetPendenciasRequerimento(idRequerimento, offset, limit);
        ResponseEntity responseEntity = requestUtils.doGet(url, Object.class);
        LinkedHashMap body = (LinkedHashMap) responseEntity.getBody();
        Object requerimento = body.get(RESPOSTA);
        if (requerimento != null && ((ArrayList) requerimento).size() > Constants.ZERO) {
            return ((ArrayList) requerimento).get(Constants.ZERO);
        }
        throw new IntegracaoException("ERRO BUSCAR PENDENCIA REQUERIMENTO");
    }

    /**
     * Busca todos os título serviços
     * @return Object com a lista de todos dados encontrados
     * @throws IntegracaoException
     */
    public Object listarTitulosServico() throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlListaTituloServico();
        ResponseEntity responseEntity = requestUtils.doGet(url, Object.class);
        LinkedHashMap body = (LinkedHashMap) responseEntity.getBody();
        Object requerimento = body.get(RESPOSTA);

        if (requerimento != null) {
            return requerimento;
        }
        throw new IntegracaoException("ERRO LISTAR TITULOS SERVIÇOS");
    }

}
