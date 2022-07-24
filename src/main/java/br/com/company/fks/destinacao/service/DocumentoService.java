package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.Documento;
import br.com.company.fks.destinacao.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdias on 3/30/2017.
 */

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Documento salvar(Documento documento, Destinacao destinacao){
        if (documento != null) {
            documento.setId(null);
            documento.setDestinacao(destinacao);
            return documentoRepository.save(documento);
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Documento> salvar(List<Documento> documentos, Destinacao destinacao){
        List<Documento> documentosSalvos = new ArrayList<>();
        if (documentos != null) {
            documentos.forEach(documento -> documentosSalvos.add(salvar(documento, destinacao)));
        }
        return documentosSalvos;
    }

}
