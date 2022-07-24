package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.OcupanteDTO;
import br.com.company.fks.destinacao.dominio.entidades.Ocupante;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.repository.OcupanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haillanderson on 11/07/17.
 */

@Service
public class OcupanteService {

    @Autowired
    private OcupanteRepository ocupanteRepository;


    /**
     * Salva no banco de dados os dados referentes ao ocupante
     * @param ocupante
     * @return ocupante gravado no banco
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Ocupante salvar (Ocupante ocupante) {
        return ocupanteRepository.save(ocupante);
    }

    /**
     * Salva os dados de possse informal na lista dos ocupantes
     * @param ocupantes
     * @param posseInformal
     * @return List<Ocupante> lista dos ocupantes após salvar os dados da possse informal
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ocupante> salvar(List<Ocupante> ocupantes, PosseInformal posseInformal) {
        List<Ocupante> ocupantesSalvos = new ArrayList<>();
        ocupantes.forEach(ocupante -> {
            ocupante.setPosseInformal(posseInformal);
            ocupantesSalvos.add(salvar(ocupante));
        });
        return ocupantesSalvos;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long idOcupante){
        ocupanteRepository.delete(idOcupante);
    }
    /**
     * Retorna todos os ocupantes pelo id da posse informal
     * @param idPosseInformal
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<OcupanteDTO> findByIdPosseInformal(Long idPosseInformal){
        return ocupanteRepository.findByIdPosseInformal(idPosseInformal);
    }
}
