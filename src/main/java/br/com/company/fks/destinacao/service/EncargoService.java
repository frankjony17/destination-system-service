package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.EncargoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Encargo;
import br.com.company.fks.destinacao.repository.EncargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por salvar encargos
 * Created by Basis Tecnologia on 22/07/2016.
 */
@Service
public class EncargoService {

    @Autowired
    private EncargoRepository encargoRepository;

    /**
     * Salva encargo
     * @param encargo
     * @return Encargo salvo
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Encargo salvar(Encargo encargo) {
        return encargoRepository.save(encargo);
    }

    /**
     * Salva encargo na lista de encargos salvos
     * @param encargos
     * @return List<Encargo> lista com os encargos salvos
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Encargo> salvar(List<Encargo> encargos) {
        List<Encargo> encargosSalvos = new ArrayList<>();

        if (encargos != null) {
            for(Encargo encargo: encargos){
                encargo.setId(null);
                encargosSalvos.add(salvar(encargo));
            }
        }

        return encargosSalvos;
    }

    public List<Encargo> listaEncargos(Long idDestinacao){
        return encargoRepository.listaEncargos(idDestinacao);
    }

}