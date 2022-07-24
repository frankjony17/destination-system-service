package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.MotivoCancelamento;
import br.com.company.fks.destinacao.repository.MotivoCancelamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author haillanderson
 */

@Service
public class MotivoCancelamentoService {
    
    @Autowired
    private MotivoCancelamentoRepository motivoCancelamentoRepository;
    
    
    /**
     * Método que busca todos os motivos de cancelamento de uma destinação
     * @return lista com todos os motivos de cancelamento
     */
    public List<MotivoCancelamento> findAll(){
        return motivoCancelamentoRepository.findAll();
    }
    
}
