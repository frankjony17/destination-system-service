package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DocumentoIntegradorPessoasDTO;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoPessoaService {

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    public List<DocumentoIntegradorPessoasDTO> buscarDocumentoPessoa(){
        ResponseEntity<Resposta<List<DocumentoIntegradorPessoasDTO>>> responseEntity =
                (ResponseEntity<Resposta<List<DocumentoIntegradorPessoasDTO>>>) requestUtils.doGet(urlIntegracaoUtils.getUrlGetDocumentosPessoaFisica(), Resposta.class);
        return responseEntity.getBody().getResultado();
    }
}
