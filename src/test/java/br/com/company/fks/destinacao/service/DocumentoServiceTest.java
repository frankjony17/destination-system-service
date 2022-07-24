package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.Documento;
import br.com.company.fks.destinacao.repository.DocumentoRepository;
import br.com.company.fks.destinacao.utils.Constants;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 18/04/17.
 */

@RunWith(PowerMockRunner.class)
public class DocumentoServiceTest {

    @InjectMocks
    private DocumentoService documentoService;

    @Mock
    private Documento documento;

    @Mock
    private List<Documento> documentos;

    @Mock
    private Destinacao destinacao;

    @Mock
    private DocumentoRepository documentoRepository;


    @Test
    @SneakyThrows
    public void salvar(){
        when(documentoRepository.save(any(Documento.class))).thenReturn(documento);
        Documento retorno = documentoService.salvar(documento,destinacao);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void salvarDocumentoNull(){
        Documento doc = null;
        when(documentoRepository.save(any(Documento.class))).thenReturn(documento);
        Documento retorno = documentoService.salvar(doc,destinacao);
        assertNull(retorno);
    }

    @Test
    @SneakyThrows
    public void salvarListaDocumentos(){
        List<Documento> lista = documentoService.salvar(documentos, destinacao);
        assertNotNull(lista);
    }

    @Test
    @SneakyThrows
    public void salvarListaDocumentosNull(){
        List<Documento> docs = null;
        List<Documento> lista = documentoService.salvar(docs, destinacao);
        assertTrue(lista.size() == Constants.ZERO);
    }

}