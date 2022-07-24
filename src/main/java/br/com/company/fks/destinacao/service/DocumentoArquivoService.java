package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.DocumentoArquivo;
import br.com.company.fks.destinacao.repository.DocumentoArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haillanderson on 11/07/17.
 */

@Service
public class DocumentoArquivoService {

    @Autowired
    private DocumentoArquivoRepository documentoArquivoRepository;

    @Autowired
    private ArquivoService arquivoService;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<DocumentoArquivo> salvar(List<DocumentoArquivo> documentoArquivos){

        List<DocumentoArquivo> documentoArquivosSalvos = new ArrayList<>();

        if(documentoArquivos !=  null && !documentoArquivos.isEmpty()){

            for (DocumentoArquivo documentoArquivo : documentoArquivos) {
                documentoArquivo.setArquivo(arquivoService.findById(documentoArquivo.getArquivo().getId()));
                documentoArquivo.setId(null);
                documentoArquivosSalvos.add(documentoArquivoRepository.save(documentoArquivo));
            }
        }
        return documentoArquivosSalvos;
    }
}
