package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ImovelUsoDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Classe responsável por realizar uma busca nos Imóveis em Uso
 * Created by Basis Tecnologia on 10/10/2016.
 */
@Service
public class UsoImovelService {

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    /**
     * Realiza busca em todos os imóveis em uso
     * @return List<ImovelUsoDTO> lista com os imóveis usados nas destinações
     * @throws IntegracaoException
     */
    public List<ImovelUsoDTO> getUsoImoveis() throws IntegracaoException {
        String url = urlIntegracaoUtils.getUsosImoveis();
        LinkedHashMap<String, Object> resposta = (LinkedHashMap<String, Object>) requestUtils.doGet(url, Object.class).getBody();
        List<ImovelUsoDTO> usoRegimeDTOs = (List<ImovelUsoDTO>) resposta.get("resposta");
        if(usoRegimeDTOs == null) {
            throw new IntegracaoException(mensagemNegocio.get("imovel.uso"));
        }
        return usoRegimeDTOs;
    }




}
