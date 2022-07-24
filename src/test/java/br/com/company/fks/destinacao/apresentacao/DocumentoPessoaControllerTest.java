package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DocumentoIntegradorPessoasDTO;
import br.com.company.fks.destinacao.service.DocumentoPessoaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class DocumentoPessoaControllerTest {

    @Mock
    private DocumentoPessoaService documentoPessoaService;

    @InjectMocks
    private DocumentoPessoaController controller;

    @Mock
    private DocumentoIntegradorPessoasDTO documentoIntegradorPessoasDTO;


    @Before
    public void setUp() throws Exception {
        when(documentoPessoaService.buscarDocumentoPessoa()).thenReturn(Collections.singletonList(documentoIntegradorPessoasDTO));
    }

    @Test
    public void buscarDocumentoBasePessoa() throws Exception {
        ResponseEntity<Resposta<List<DocumentoIntegradorPessoasDTO>>> entity = controller.buscarDocumentoBasePessoa();
        assertNotNull(entity.getBody());
        assertFalse(entity.getBody().getResultado().isEmpty());
    }

}