package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.dominio.entidades.DocumentoArquivo;
import br.com.company.fks.destinacao.dominio.entidades.Foto;
import br.com.company.fks.destinacao.repository.DocumentoArquivoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
public class DocumentoArquivoServiceTest {

    @InjectMocks
    private DocumentoArquivoService documentoArquivoService;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private DocumentoArquivoRepository documentoArquivoRepository;

    @Mock
    private Arquivo arquivo;

    @Mock
    private DocumentoArquivo documentoArquivo;

    @Before
    public void setUp(){
        when(arquivoService.findById(anyLong())).thenReturn(arquivo);
        when(documentoArquivoRepository.save(any(DocumentoArquivo.class))).thenReturn(documentoArquivo);
        when(arquivo.getId()).thenReturn(1L);
        when(documentoArquivo.getArquivo()).thenReturn(arquivo);
    }

    @Test
    public void salvarNulo(){
        List<DocumentoArquivo> documentoArquivoList = new ArrayList<>();
        List<DocumentoArquivo> documentoArquivos = documentoArquivoService.salvar(null);
        assertNotNull(documentoArquivos);
    }

    @Test
    public void salvar(){
        List<DocumentoArquivo> documentoArquivoList = new ArrayList<>();
        documentoArquivoList.add(documentoArquivo);
        List<DocumentoArquivo> documentoArquivos = documentoArquivoService.salvar(documentoArquivoList);
        assertNotNull(documentoArquivos);
    }

    @Test
    public void salvarListaFalsa(){
        List<DocumentoArquivo> documentoArquivoList = new ArrayList<>();
        List<DocumentoArquivo> documentoArquivos = documentoArquivoService.salvar(documentoArquivoList);
        assertNotNull(documentoArquivos);
    }


}
