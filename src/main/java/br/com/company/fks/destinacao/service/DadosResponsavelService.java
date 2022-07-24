package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Interveniente;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.repository.DadosResponsavelRepository;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tawan-souza on 28/11/17.
 */
@Service
public class DadosResponsavelService {

    @Autowired
    private DadosResponsavelRepository dadosResponsavelRepository;

    @Autowired
    private EnderecoCorrespondenciaService enderecoService;

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private IntervenienteService intervenienteService;

    @Transactional(propagation = Propagation.REQUIRED)
    public DadosResponsavel salvar(DadosResponsavel dadosResponsavel, Boolean copiaDestiancao) {
        if(dadosResponsavel != null){
            if(dadosResponsavel.getTipoPosseOcupacao().getDescricao().equals("Coletivo") ){
                dadosResponsavel.setEnderecoCorrespondencia(enderecoService
                        .salvar(dadosResponsavel.getEnderecoCorrespondencia()));
            }

            List<Responsavel> responsavels = dadosResponsavel.getResponsaveis();
            dadosResponsavel.setResponsaveis(null);

            Interveniente intervenientes = dadosResponsavel.getInterveniente();
            dadosResponsavel.setInterveniente(null);

            DadosResponsavel dadosResponsavel1Salvo = dadosResponsavelRepository.save(dadosResponsavel);
            dadosResponsavel1Salvo.setResponsaveis(responsavelService.salvar(responsavels, dadosResponsavel1Salvo));
            dadosResponsavel1Salvo.setInterveniente(intervenienteService.salvar(intervenientes, dadosResponsavel1Salvo, copiaDestiancao ));
            return dadosResponsavel1Salvo;
        }else {
            return null;
        }
    }
}
