package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.DocumentoIntegradorPessoasDTO;
import br.com.company.fks.destinacao.service.DocumentoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/documento-pessoa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DocumentoPessoaController {

    @Autowired
    private DocumentoPessoaService documentoPessoaService;

    @RequestMapping(value = "/fisica", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<DocumentoIntegradorPessoasDTO>>> buscarDocumentoBasePessoa(){
        List<DocumentoIntegradorPessoasDTO> documento = documentoPessoaService.buscarDocumentoPessoa();
        return new ResponseEntity<Resposta<List<DocumentoIntegradorPessoasDTO>>>(RespostaBuilder.getBuilder().setResultado(documento).build(), HttpStatus.OK);
    }

}
