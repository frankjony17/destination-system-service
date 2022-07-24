package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe responsável por salvar Contrato
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    /**
     * Salva contrato
     * @param contrato
     * @return Contrato salvo
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Contrato salvar (Contrato contrato, Boolean copiaDestinacao) {
        if (contrato != null) {
            if(copiaDestinacao){
                contrato.setId(null);
            }
            return contratoRepository.save(contrato);
        }
        return null;
    }

}
