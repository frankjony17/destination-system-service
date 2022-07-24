package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DestinacaoResponsavelDTO;
import br.com.company.fks.destinacao.dominio.dto.ResponsavelDTO;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Residente;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ResponsavelRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Classe responsável por salvar o responsável na destinação e no banco de dados
 * Created by Basis Tecnologia on 17/10/2016.
 */
@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private FamiliaBeneficiadaService familiaBeneficiadaService;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private ResidenteService residenteService;

    @Autowired
    private EnderecoCorrespondenciaService enderecoService;

    @Autowired
    private TelefoneService telefoneService;

    private static final Logger LOGGER = Logger.getLogger(ResponsavelService.class);

    /**
     * Salva os dados referentes ao responsável no banco de dados
     * @param responsavel
     * @return Reponsavel gravado no banco de daddos
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Responsavel salvar (Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    /**
     * Adiciona os responsáveis na destinação
     * @param responsaveis
     * @param dadosResponsavel
     * @return List<responsavel> lista dos responsáveis adicionados na destinação
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Responsavel> salvar(List<Responsavel> responsaveis, DadosResponsavel dadosResponsavel){

        List<Responsavel> responsaveisSalvos = new ArrayList<>();

        if(responsaveis != null){
            responsaveis.forEach(getSalvaResponsavelConsumer(responsaveisSalvos, dadosResponsavel));
        }

        return responsaveisSalvos;
    }

    /**
     * Método que faz a integração com o cadastro de imóveis para buscar o responsável do imóvel pelo id do imóvel
     * @param idImovel
     * @return Responsável do imóvel
     * @throws IntegracaoException
     */
    public ResponsavelDTO buscarResponsavelImovel(Long idImovel) throws IntegracaoException {
        String urlCadastro = urlIntegracaoUtils.getUrlBuscarResponsavelImovel(idImovel);
        ResponsavelDTO responsavelDTO;
        try {
            responsavelDTO = (ResponsavelDTO) requestUtils.doGet(urlCadastro, ResponsavelDTO.class).getBody();
        } catch (RuntimeException e){
            LOGGER.error("ERRO INTEGRACAO CADASTRO IMOVEL", e);
            throw new IntegracaoException(e.getMessage(), e);
        }
        return responsavelDTO;
    }


    public List<DestinacaoResponsavelDTO> buscarResponsavelDestinacao(String cpf){
        return DestinacaoResponsavelDTO.toDto(responsavelRepository.buscarDestinacaoesResponsavel(cpf));
    }

    private Consumer<Responsavel> getSalvaResponsavelConsumer(List<Responsavel> responsaveisSalvos, DadosResponsavel dadosResponsavel) {
        return responsavel -> {
            responsavel.setDadosResponsavel(dadosResponsavel);
            List<Residente> residentes = responsavel.getResidentes();
            responsavel.setResidentes(null);
            responsavel.setTelefones(telefoneService.salvar(responsavel.getTelefones(), responsavel));
            responsavel.setEnderecoCorrespondencia(enderecoService.salvar(responsavel.getEnderecoCorrespondencia()));
            Responsavel responsavelSalvo = responsavelRepository.save(responsavel);
            responsavel.setResidentes(residenteService.salvar(residentes, responsavelSalvo));
            if (responsavel.getFamiliasBeneficiadas() == null || responsavel.getFamiliasBeneficiadas().isEmpty()) {
                responsaveisSalvos.add(responsavelRepository.save(responsavelSalvo));
            } else {
                responsavel.setFamiliasBeneficiadas(familiaBeneficiadaService.salvar(responsavel.getFamiliasBeneficiadas(), responsavel));
                responsaveisSalvos.add(responsavelRepository.save(responsavelSalvo));
            }
        };
    }
}
