package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSecretario;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoSecretarioRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Responsável por salvar Análise Técnica Secretário e Despachos Secretário
 * Created by diego on 19/12/16.
 */
@Service
public class AnaliseTecnicaDespachoSecretarioService {

    @Autowired
    private AnaliseTecnicaDespachoSecretarioRepository analiseTecnicaDespachoSecretarioRepository;

    @Autowired
    private EntityConverter entityConverter;

    /**
     * Método reponsável por salvar Análise Técnica Secretário
     * @param analise
     * @return AnaliseTecnicaDespachoSecretario salva no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AnaliseTecnicaDespachoSecretario salvar(AnaliseTecnicaDespachoSecretario analise) {
        return analiseTecnicaDespachoSecretarioRepository.save(analise);
    }

    /**
     *Método reponsável por salvar Despachos Secretário
     * @param despachos
     * @param analiseTecnica
     * @return List<AnaliseTecnicaDespachoSecretario> lista com as anáslise técnicas salvas no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<AnaliseTecnicaDespachoSecretario> salvar(List<DespachoDTO> despachos, AnaliseTecnica analiseTecnica) {
        List<AnaliseTecnicaDespachoSecretario> analises = new ArrayList<>();
        analiseTecnicaDespachoSecretarioRepository.deleteByIdAnalise(analiseTecnica.getId());
        if (despachos != null) {
            despachos.forEach(despachoDTO -> {
                Despacho despacho = entityConverter.converterStrict(despachoDTO, Despacho.class);
                AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID(analiseTecnica, despacho);
                AnaliseTecnicaDespachoSecretario analise = new AnaliseTecnicaDespachoSecretario();
                analise.setJustificativa(despacho.getJustificativa());
                analise.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
                analises.add(analiseTecnicaDespachoSecretarioRepository.save(analise));
            });
        }
        return analises;
    }
}
