package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Afetacao;
import br.com.company.fks.destinacao.repository.AfetacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AfetacaoService {

    @Autowired
    private AfetacaoRepository afetacaoRepository;

    public Afetacao salvar(Afetacao afetacao) {
       return afetacaoRepository.save(afetacao);
    }
}