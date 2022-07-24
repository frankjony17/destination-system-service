package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DocumentoAnaliseDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.DocumentoAnalise;
import br.com.company.fks.destinacao.repository.DocumentoAnaliseRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe responsável por realizar operações com o Documento Análise
 * Created by basis on 19/12/16.
 */
@Service
public class DocumentoAnaliseService {

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DocumentoAnaliseRepository documentoAnaliseRepository;

    /**
     * Salva documento análise
     * @param dto
     * @param analiseTecnica
     * @return DocumentoAnalise documento análise salvo
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public DocumentoAnalise salvar(DocumentoAnaliseDTO dto, AnaliseTecnica analiseTecnica){
        DocumentoAnalise documentoAnalise = entityConverter.converterStrict(dto, DocumentoAnalise.class);
        documentoAnalise.setAnaliseTecnica(analiseTecnica);
        return documentoAnaliseRepository.save(documentoAnalise);
    }

    /**
     * Salva documento análise na lista de documentos salvos
     * @param dtos
     * @param analiseTecnica
     * @return List<DocumentoAnalise> lista dos documentos de análise salvos
     */
    public List<DocumentoAnalise> salvar(List<DocumentoAnaliseDTO> dtos, AnaliseTecnica analiseTecnica){
        List<DocumentoAnalise> result = new ArrayList<>();
        for (DocumentoAnaliseDTO dto : dtos) {
            result.add(salvar(dto, analiseTecnica));
        }

        return result;
    }

    /**
     * Busca Documento da análise por id da análise técnica
     * @param id
     * @return List<DocumentoAnaliseDTO> lista dos documento da análise encontrados pelo id da análise técnica
     */
    public List<DocumentoAnaliseDTO> buscarPorIdAnalise(Long id) {
        List<DocumentoAnalise> itens = documentoAnaliseRepository.buscarPorIdAnalise(id);

        List<DocumentoAnaliseDTO> result = new ArrayList<>();
        for (DocumentoAnalise item : itens) {
            result.add(entityConverter.converterStrict(item, DocumentoAnaliseDTO.class));
        }

        return result;
    }

    /**
     * Filtra os documentos da análise por tipo de documento
     * @param documentosAnalise
     * @param tipoDocumento
     * @return List<DocumentoAnaliseDTO> lista com os documentos da análise encontrados por tipo de documento
     */
    public List<DocumentoAnaliseDTO> filtrarPorTipo(List<DocumentoAnaliseDTO> documentosAnalise, String tipoDocumento) {
        Stream<DocumentoAnaliseDTO> docuementosFiltrados  =
                documentosAnalise.stream().filter(documentoAnaliseDTO -> documentoAnaliseDTO.getTipoDocumento().equals(tipoDocumento));
        return docuementosFiltrados.collect(Collectors.toList());
    }
}
