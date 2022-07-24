package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.repository.UtilizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe responsável por salvar a utilização no banco de dados
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Service
public class UtilizacaoService {

    @Autowired
    private UtilizacaoRepository utilizacaoRepository;

    /**
     * Salva a utilização no banco de dados
     * @param utilizacao
     * @return Utilizacao gravada no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Utilizacao salvar(Utilizacao utilizacao, Boolean copiaDestinacao) {
        Utilizacao utilizacaoSalva = null;
        if (utilizacao != null) {

            if(copiaDestinacao) {
                utilizacao.setId(null);
            }

            utilizacaoSalva = utilizacaoRepository.save(utilizacao);
        }
        return utilizacaoSalva;

    }

    public List<String> buscarEspecificacoes (Integer idTipoUtilizacao) {
        return utilizacaoRepository.buscarEspecificacoes(idTipoUtilizacao);
    }

    /**
     * Seleciona utilização inicial
     * @return
     */
    public Utilizacao selecionaSemUsoVago(){
        return utilizacaoRepository.selecionaSemUsoVago();
    }
}
