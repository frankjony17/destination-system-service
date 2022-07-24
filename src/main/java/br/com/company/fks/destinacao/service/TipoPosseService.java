package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import br.com.company.fks.destinacao.repository.TipoPosseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haillanderson on 07/07/17.
 */

@Service
public class TipoPosseService {

    @Autowired
    private TipoPosseRepository tipoPosseRepository;

    public List<TipoPosse> findAll(){
        return tipoPosseRepository.findAll();
    }
}
