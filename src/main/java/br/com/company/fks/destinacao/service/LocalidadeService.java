package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.LocalizacaoEctDTO;
import br.com.company.fks.destinacao.dominio.dto.MunicipioDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe responsável por realizar busca na Localidade
 * Created by Basis Tecnologia on 16/01/2017.
 */
@Service
public class LocalidadeService {

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    /**
     * Realiza uma busca no endereço por CEP e adiciona o resultado na Localidade
     * @param cep
     * @return LocalizacaoEctDto obtida através da busca do endereço por CEP
     * @throws IntegracaoException
     */
    public LocalizacaoEctDTO findEnderecoByCep(String cep) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlBuscarEnderecoByCep(cep);
        ResponseEntity responseEntity = requestUtils.doGet(url, LocalizacaoEctDTO.class);
        LocalizacaoEctDTO localidade = (LocalizacaoEctDTO) responseEntity.getBody();
        if(localidade == null) {
            throw new IntegracaoException(mensagemNegocio.get("cep.nao.encontrado"));
        }
        return localidade;
    }

    /**
     * Consulta municipios por uf.
     * @param uf
     * @return
     * @throws IntegracaoException
     */
    public List<MunicipioDTO> findMunicipioByUF(String uf) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlGetBuscarMunicipioPorUf(uf);
        ResponseEntity responseEntity = requestUtils.doGet(url, List.class);
        List<MunicipioDTO> municipioDTOs = (List<MunicipioDTO>) responseEntity.getBody();
        if(municipioDTOs == null) {
            throw new IntegracaoException("Municipio não encontrado");
        }
        return municipioDTOs;
    }

}
