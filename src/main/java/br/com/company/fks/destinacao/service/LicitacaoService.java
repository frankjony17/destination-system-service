package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por salvar a Licitação
 * Created by Basis Tecnologia on 21/10/2016.
 */
@Service
public class LicitacaoService {

    @Autowired
    private LicitacaoRepository licitacaoRepository;

    @Autowired
    private ArquivoService arquivoService;

    /**
     * Salva a licitação no banco de dados
     * @param licitacao
     * @return Licitacao gravada no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Licitacao salvar (Licitacao licitacao, Boolean copiaDestinacao) {
        Licitacao licitacaoSalva = null;
        if (licitacao != null) {
            licitacao.setArquivos(recuperarArquivoLicitacao(licitacao.getArquivos()));

            if(copiaDestinacao){
                licitacao.setId(null);
            }
            licitacaoSalva = licitacaoRepository.save(licitacao);

        }
        return licitacaoSalva;
    }

    private List<Arquivo> recuperarArquivoLicitacao(List<Arquivo> arquivos){
        List<Arquivo> arquivosLicitacao = new ArrayList<>();
        if(arquivos != null){
            for (Arquivo arquivo : arquivos) {
                Arquivo parcelaArquivo = arquivoService.findById(arquivo.getId());
                arquivosLicitacao.add(parcelaArquivo);
            }
        }
        return arquivosLicitacao;
    }

}
