package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSuperintendente;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoSuperintendenteRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *Classe Responsável por salvar Análise Técnica Superintendente e Despachos Superintendente
 * Created by diego on 19/12/16.
 */
@Service
public class AnaliseTecnicaDespachoSuperintendenteService {

    @Autowired
    private AnaliseTecnicaDespachoSuperintendenteRepository analiseTecnicaDespachoSuperintendenteRepository;

    @Autowired
    private EntityConverter entityConverter;

    /**
     * Método reponsável por salvar Análise Técnica Superintendente
     * @param analises
     * @return AnaliseTecnicaDespachoSuperintendente salva no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AnaliseTecnicaDespachoSuperintendente salvar(AnaliseTecnicaDespachoSuperintendente analises) {
        return analiseTecnicaDespachoSuperintendenteRepository.save(analises);
    }

    /**
     * Método reponsável por salvar Despachos Superintendente
     * @param despachos
     * @param analiseTecnica
     * @return List<AnaliseTecnicaDespachoSuperintendente> lista com as análises salvas no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<AnaliseTecnicaDespachoSuperintendente> salvar(List<DespachoDTO> despachos, AnaliseTecnica analiseTecnica) {
        List<AnaliseTecnicaDespachoSuperintendente> analises = new ArrayList<>();
        analiseTecnicaDespachoSuperintendenteRepository.deleteByIdAnalise(analiseTecnica.getId());
        if (despachos != null) {
            despachos.forEach(despachoDTO -> {
                Despacho despacho = entityConverter.converterStrict(despachoDTO, Despacho.class);
                AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID(analiseTecnica, despacho);
                AnaliseTecnicaDespachoSuperintendente analise = new AnaliseTecnicaDespachoSuperintendente();
                analise.setJustificativa(despacho.getJustificativa());
                analise.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
                analises.add(analiseTecnicaDespachoSuperintendenteRepository.save(analise));
            });
        }
        return analises;
    }
}
