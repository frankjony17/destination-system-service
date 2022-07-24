package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Publicacao;
import br.com.company.fks.destinacao.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe responsável por salvar uma Publicação
 * Created by diego on 16/12/16.
 */
@Service
public class PublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    /**
     * Salva uma publicação no banco de dados
     * @param publicacao
     * @return Publicacao gravada no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Publicacao salvar(Publicacao publicacao) {
        return publicacaoRepository.save(publicacao);
    }

}
