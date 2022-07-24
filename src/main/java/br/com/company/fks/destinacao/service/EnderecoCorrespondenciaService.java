package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.EnderecoCorrespondencia;
import br.com.company.fks.destinacao.repository.EnderecoCorrespondenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoCorrespondenciaService {

    @Autowired
    private EnderecoCorrespondenciaRepository enderecoCorrespondenciaRepository;

    @Autowired
    private EnderecoService enderecoService;


    @Transactional(propagation = Propagation.REQUIRED)
    public EnderecoCorrespondencia salvar(EnderecoCorrespondencia enderecoCorrespondencia) {
        if(enderecoCorrespondencia != null){
            enderecoCorrespondencia.setEndereco(enderecoService.salvar(enderecoCorrespondencia.getEndereco()));
            return enderecoCorrespondenciaRepository.save(enderecoCorrespondencia);
        }
        return null;
    }
}
