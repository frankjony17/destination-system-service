package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.destinacao.DoacaoService;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Classe responsável por realizar operações com Fundamento Legal
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Service
public class FundamentoLegalService {

    @Autowired
    private DoacaoService doacaoService;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    /**
     * Verifica se a destinação está sendo usada em algum fundamento legal
     * @param id
     * @return true ou false
     */
    @Transactional(readOnly = true)
    public Boolean isUsadoDestinacao(Long id) {
        return doacaoService.isUsadoDestinacao(id);
    }

    /**
     * Realiza uma busca Fundamento legal por um id
     * @param tipoDestinacaoEnum
     * @return FundamentoLegalDTO obtido através da busca  por id
     * @throws IntegracaoException
     */
    @Transactional(readOnly = true)
    public List<FundamentoLegalDTO> buscarFundamentoLegalByTipoDestinacaoEnumFuncionalidade(TipoDestinacaoEnum tipoDestinacaoEnum) throws IntegracaoException {
        String url = urlIntegracaoUtils.getBuscarFundamentoLegalIntegradorByTipoDestinacaoEnum(tipoDestinacaoEnum);
        ResponseEntity responseEntity = requestUtils.doGet(url, Object.class);
        Map<String, Object> resposta = (Map<String, Object>) responseEntity.getBody();
        List<FundamentoLegalDTO> fundamentosLegais = (List<FundamentoLegalDTO>) resposta.get("resultado");
        if (fundamentosLegais.isEmpty()) {
            throw new IntegracaoException("Fundamento legal não encontrado");
        }
        return fundamentosLegais;
    }
}
