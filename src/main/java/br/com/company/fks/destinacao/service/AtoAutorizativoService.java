package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.AtoAutorizativo;
import br.com.company.fks.destinacao.repository.AtoAutorizativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by diego on 15/08/17.
 */
@Service
public class AtoAutorizativoService {

    @Autowired
    private AtoAutorizativoRepository atoAutorizativoRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public AtoAutorizativo salvar(AtoAutorizativo atoAutorizativo, Boolean copiaDestinacao) {
        if (atoAutorizativo != null) {
            if(copiaDestinacao){
                atoAutorizativo.setId(null);
            }
            return atoAutorizativoRepository.save(atoAutorizativo);
        }
        return null;
    }
}
