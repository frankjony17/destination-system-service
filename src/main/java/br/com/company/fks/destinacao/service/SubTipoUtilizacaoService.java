package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.TipoUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.SubTipoUtilizacao;
import br.com.company.fks.destinacao.repository.SubTipoUtilizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haillanderson on 07/04/17.
 */

@Service
public class SubTipoUtilizacaoService {

    @Autowired
    private SubTipoUtilizacaoRepository subTipoUtilizacaoRepository;

    public List<SubTipoUtilizacao> buscarTodosSubTiposUtilizacaoAtivos(){
        return subTipoUtilizacaoRepository.findAllAtivos();
    }

    public List<SubTipoUtilizacao> buscarFiltrando(Integer tipoUtilizacaoDTO){
        return subTipoUtilizacaoRepository.findAllByTipoUtilizacao(tipoUtilizacaoDTO);
    }

}
