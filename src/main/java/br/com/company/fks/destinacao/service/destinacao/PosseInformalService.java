package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.PosseInformalDTO;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.PosseInformalRepository;
import br.com.company.fks.destinacao.service.ImovelService;
import br.com.company.fks.destinacao.service.OcupanteService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por salvar dados da Posse Informal
 * Created by Basis Tecnologia on 25/10/2016.
 */

@Service
public class PosseInformalService extends DestinacaoService<PosseInformal> {

    @Autowired
    private OcupanteService ocupanteService;

    @Autowired
    private PosseInformalRepository posseInformalRepository;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private EntityConverter entityConverter;

    /**
     * Salvar dados Posse Informal
     * @param posseInformal
     * @return PosseInformal gravada no banco de dados
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PosseInformal salvarDadosEspecificos(PosseInformal posseInformal) {

        if(posseInformal.getId() != null ){
            List<Long> ocupantesId = posseInformal.getOcupantes().stream().filter(o -> o.getId() != null).map(o -> o.getId()).collect(Collectors.toList());
            List<Long> idsOcupantes = posseInformalRepository.buscarIdsOcupantesPosseInformal(posseInformal.getId()).stream().filter(o -> o != null && !ocupantesId.contains(o)).collect(Collectors.toList());
            idsOcupantes.forEach(id -> ocupanteService.delete(id));
        }
        Imovel imovel = imovelService.findById(posseInformal.getImovel().getId());
        posseInformal.setImovel(imovel);
        return posseInformalRepository.save(posseInformal);
    }

    /**
     * Método sobrescrito para buscar a destinação específica
     * @param id
     * @return
     * @throws IntegracaoException
     * @throws IOException
     */
    @Override
    public Resposta<PosseInformalDTO> findOne(Long id) throws IntegracaoException, IOException {
        PosseInformalDTO posseInformalDTO = entityConverter.converterStrict(posseInformalRepository.findOne(id), PosseInformalDTO.class);
        montarDadosMapa(posseInformalDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(posseInformalDTO).build();
    }


}

