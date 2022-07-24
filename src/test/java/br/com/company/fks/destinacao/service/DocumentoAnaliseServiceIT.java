package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.DocumentoAnaliseDTO;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 29/12/16.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DocumentoAnaliseServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private DocumentoAnaliseService documentoAnaliseService;

    @Test
    @SneakyThrows
    public void buscarPorIdAnalise(){
        List<DocumentoAnaliseDTO> documentoAnaliseDTOList = documentoAnaliseService.buscarPorIdAnalise(125L);
        assertTrue(!documentoAnaliseDTOList.isEmpty());
        assertNotNull(documentoAnaliseDTOList);
    }

    @Test
    @SneakyThrows
    public void buscarPorIdAnaliseResultadoVazio(){
        List<DocumentoAnaliseDTO> documentoAnaliseDTOList = documentoAnaliseService.buscarPorIdAnalise(1L);
        assertTrue(documentoAnaliseDTOList.isEmpty());
    }
}
