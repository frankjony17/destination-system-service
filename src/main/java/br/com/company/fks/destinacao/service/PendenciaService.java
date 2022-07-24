package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Pendencia;
import br.com.company.fks.destinacao.repository.PendenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar operações na Pendencia
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Service
public class PendenciaService {

    @Autowired
    private PendenciaRepository pendenciaRepository;

    /**
     * Recupera uma pendencia pelo ID.
     * @param id
     * @return Pendencia
     */
    @Transactional(readOnly = true)
    public Pendencia findById (Long id) {
        return pendenciaRepository.findOne(id);
    }

    /**
     * Cria mapa de pencencias.
     * @return
     */
    @Transactional(readOnly = true)
    public Map<String, Pendencia> getMapPendencias() {
        List<Pendencia> pendencias = pendenciaRepository.findAll();
        return pendencias
                .stream()
                .collect(Collectors.toMap(map -> map.getDescricao(), map -> map));
    }

}
