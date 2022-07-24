package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoChefia;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoChefiaRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Responsável por salvar Análise Técnica chefia e Despachos Chefia
 * Created by diego on 19/12/16.
 */
@Service
public class AnaliseTecnicaDespachoChefiaService {

    @Autowired
    private AnaliseTecnicaDespachoChefiaRepository analiseTecnicaDespachoChefiaRepository;

    @Autowired
    private EntityConverter entityConverter;

    /**
     *Método reponsável por salvar Análise Técnica Chefia
     * @param analise
     * @return AnaliseTecnicaDespachoChefia salva no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AnaliseTecnicaDespachoChefia salvar(AnaliseTecnicaDespachoChefia analise) {
        return analiseTecnicaDespachoChefiaRepository.save(analise);
    }

    /**
     * Método reponsável por salvar despachos chefia
     * @param despachos
     * @param analiseTecnica
     * @return List<AnaliseTecnicaDespachoChefia> lista das análises técnicas salvas no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<AnaliseTecnicaDespachoChefia> salvar(List<DespachoDTO> despachos, AnaliseTecnica analiseTecnica) {
        List<AnaliseTecnicaDespachoChefia> analises = new ArrayList<>();
        analiseTecnicaDespachoChefiaRepository.deleteByIdAnalise(analiseTecnica.getId());
        if (despachos != null) {
            despachos.forEach(despachoDTO -> {
                Despacho despacho = entityConverter.converterStrict(despachoDTO, Despacho.class);
                AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID(analiseTecnica, despacho);
                AnaliseTecnicaDespachoChefia analise = new AnaliseTecnicaDespachoChefia();
                analise.setJustificativa(despacho.getJustificativa());
                analise.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
                analises.add(analiseTecnicaDespachoChefiaRepository.save(analise));
            });
        }
        return analises;
    }
}
