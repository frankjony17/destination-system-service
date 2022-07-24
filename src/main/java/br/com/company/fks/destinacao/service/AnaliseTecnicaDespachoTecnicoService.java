package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoTecnico;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoTecnicoRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *Classe Responsável por salvar Análise Técnica Técnico e Despachos Técnico
 * Created by diego on 19/12/16.
 */
@Service
public class AnaliseTecnicaDespachoTecnicoService {

    @Autowired
    private AnaliseTecnicaDespachoTecnicoRepository analiseTecnicaDespachoTecnicoRepository;

    @Autowired
    private EntityConverter entityConverter;

    /**
     * Método reponsável por salvar Análise Técnica Técnico
     * @param analises
     * @return AnaliseTecnicaDespachoTecnico salva no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AnaliseTecnicaDespachoTecnico salvar(AnaliseTecnicaDespachoTecnico analises) {
        return analiseTecnicaDespachoTecnicoRepository.save(analises);
    }

    /**
     * Método reponsável por salvar Despachos Técnico
     * @param despachos
     * @param analiseTecnica
     * @return List<AnaliseTecnicaDespachoTecnico> lista com as análises salvas no banco
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<AnaliseTecnicaDespachoTecnico> salvar(List<DespachoDTO> despachos, AnaliseTecnica analiseTecnica) {
        List<AnaliseTecnicaDespachoTecnico> analises = new ArrayList<>();
        analiseTecnicaDespachoTecnicoRepository.deleteByIdAnalise(analiseTecnica.getId());
        if (despachos != null) {
            despachos.forEach(despachoDTO -> {
                Despacho despacho = entityConverter.converterStrict(despachoDTO, Despacho.class);
                AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID(analiseTecnica, despacho);
                AnaliseTecnicaDespachoTecnico analise = new AnaliseTecnicaDespachoTecnico();
                analise.setJustificativa(despacho.getJustificativa());
                analise.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
                analises.add(analiseTecnicaDespachoTecnicoRepository.save(analise));
            });
        }
        return analises;
    }
}
