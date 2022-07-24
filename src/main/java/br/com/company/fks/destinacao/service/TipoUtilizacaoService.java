package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import br.com.company.fks.destinacao.repository.TipoUtilizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haillanderson on 07/04/17.
 */

@Service
public class TipoUtilizacaoService {

    @Autowired
    private TipoUtilizacaoRepository tipoUtilizacaoRepository;

    public List<TipoUtilizacao> buscarTodosTiposUtilizacaoAtivos(){
        return tipoUtilizacaoRepository.findAllAtivos();
    }

}
