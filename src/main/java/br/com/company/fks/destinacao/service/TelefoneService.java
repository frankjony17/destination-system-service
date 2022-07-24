package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Interveniente;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.Telefone;
import br.com.company.fks.destinacao.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelefoneService {
    @Autowired
    private TelefoneRepository telefoneRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Telefone salvar (Telefone telefone) {
        return telefoneRepository.save(telefone);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Telefone> salvar (List<Telefone> telefones, Responsavel responsavel){

        List<Telefone> telefonesSalvos = new ArrayList<>();

        if(telefones != null){
            telefones.forEach(telefone -> {
                telefone.setResponsavel(responsavel);
                telefonesSalvos.add(telefoneRepository.save(telefone));
            });

        }
        return telefonesSalvos;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Telefone> salvarTelefoneInterveniente (List<Telefone> telefones, Interveniente interveniente){

        List<Telefone> telefonesSalvos = new ArrayList<>();

        if(telefones != null){
            telefones.forEach(telefone -> {
                telefone.setInterveniente(interveniente);
                telefonesSalvos.add(telefoneRepository.save(telefone));
            });

        }
        return telefonesSalvos;
    }
}
