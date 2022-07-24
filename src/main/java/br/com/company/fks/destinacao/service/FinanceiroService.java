package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Financeiro;
import br.com.company.fks.destinacao.repository.FinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe responsável por salvar o Financeiro
 * Created by Basis Tecnologia on 21/10/2016.
 */
@Service
public class FinanceiroService {

    @Autowired
    private FinanceiroRepository financeiroRepository;

    /**
     * Salva dados inseridos referentes ao financeiro no banco
     * @param financeiro
     * @return Financeiro gravado no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Financeiro salvar (Financeiro financeiro, Boolean copiaDestinacao) {
        Financeiro financeiroSalvo = null;
        if (financeiro != null) {

            if(copiaDestinacao) {
                financeiro.setId(null);
            }

            financeiroSalvo = financeiroRepository.save(financeiro);
        }
        return financeiroSalvo;
    }
}
