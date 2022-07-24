package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DocumentoAnaliseDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoTecnico;
import br.com.company.fks.destinacao.dominio.entidades.DocumentoAnalise;
import br.com.company.fks.destinacao.repository.DocumentoAnaliseRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 29/12/16.
 */
@RunWith(PowerMockRunner.class)
public class DocumentoAnaliseServiceTest {

    @InjectMocks
    private DocumentoAnaliseService documentoAnaliseService;

    @Mock
    private DocumentoAnaliseRepository documentoAnaliseRepository;

    @Mock
    private DocumentoAnalise documentoAnalise;

    @Mock
    private AnaliseTecnica analiseTecnica;

    @Mock
    private DocumentoAnaliseDTO documentoAnaliseDTO;

    @Mock
    private EntityConverter entityConverter;

    @Before
    public void setUp() {
        when(documentoAnalise.getId()).thenReturn(1L);
        when(documentoAnaliseDTO.getId()).thenReturn(1L);
        when(documentoAnaliseRepository.save(documentoAnalise)).thenReturn(documentoAnalise);
        when(entityConverter.converterStrict(any(DocumentoAnaliseDTO.class), eq(DocumentoAnalise.class))).thenReturn(documentoAnalise);
        when(documentoAnaliseRepository.buscarPorIdAnalise(anyLong())).thenReturn(criarListaDocuementosAnalise());
        when(entityConverter.converterStrict(any(DocumentoAnalise.class), eq(DocumentoAnaliseDTO.class))).thenReturn(documentoAnaliseDTO);
    }

    private List<DocumentoAnalise> criarListaDocuementosAnalise() {
        DocumentoAnalise documentoAnalise = new DocumentoAnalise();
        documentoAnalise.setId(1L);
        documentoAnalise.setObservacao("teste");
        documentoAnalise.setIdDocumento(1L);
        return asList(documentoAnalise);
    }

    @Test
    public void salvar() {
        DocumentoAnalise documentoAnalise = documentoAnaliseService.salvar(documentoAnaliseDTO, analiseTecnica);
        assertNotNull(documentoAnalise);
    }

    @Test
    public void salvarLista() {
        List<DocumentoAnalise> documentosAnalise = documentoAnaliseService.salvar(asList(documentoAnaliseDTO), analiseTecnica);
        assertNotNull(documentoAnalise);
        assertFalse(documentosAnalise.isEmpty());
    }

    @Test
    public void buscarPorIdAnalise() {
        List<DocumentoAnaliseDTO> documentosAnalise = documentoAnaliseService.buscarPorIdAnalise(1L);
        assertNotNull(documentosAnalise);
        assertFalse(documentosAnalise.isEmpty());
    }

    @Test
    public void filtrarPorTipoComResultado() {
        List<DocumentoAnaliseDTO> documentosFiltrados = documentoAnaliseService.filtrarPorTipo(criarListaDocuementosAnaliseDTO(), "COMPLEMENTAR");
        assertFalse(documentosFiltrados.isEmpty());
        assertEquals(1, documentosFiltrados.size());
    }

    @Test
    public void filtrarPorTipoSemResultado() {
        List<DocumentoAnaliseDTO> documentosFiltrados = documentoAnaliseService.filtrarPorTipo(criarListaDocuementosAnaliseDTO(), "ERRO");
        assertTrue(documentosFiltrados.isEmpty());
    }

    private List<DocumentoAnaliseDTO> criarListaDocuementosAnaliseDTO() {
        DocumentoAnaliseDTO documentoAnalise1 = new DocumentoAnaliseDTO();
        documentoAnalise1.setId(1L);
        documentoAnalise1.setTipoDocumento("OBRIGATORIO");

        DocumentoAnaliseDTO documentoAnalise2 = new DocumentoAnaliseDTO();
        documentoAnalise2.setId(1L);
        documentoAnalise2.setTipoDocumento("OBRIGATORIO");

        DocumentoAnaliseDTO documentoAnalise3 = new DocumentoAnaliseDTO();
        documentoAnalise3.setId(1L);
        documentoAnalise3.setTipoDocumento("COMPLEMENTAR");

        return asList(documentoAnalise1, documentoAnalise2, documentoAnalise3);
    }

}
