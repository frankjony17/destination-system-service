package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DocumentoIntegradorPessoasDTO;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

@RunWith(PowerMockRunner.class)
public class DocumentoPessoaServiceTest {

    @InjectMocks
    private DocumentoPessoaService documentoPessoaService;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private Resposta<List<DocumentoIntegradorPessoasDTO>> resposta;

    @Before
    public void setup() {
        given(requestUtils.doGet(anyString(), eq(Resposta.class))).willReturn(responseEntity);
        given(urlIntegracaoUtils.getUrlGetDocumentosPessoaFisica()).willReturn("ts");
        given(responseEntity.getBody()).willReturn(resposta);
        given(resposta.getResultado()).willReturn(Arrays.asList());

    }

    @Test
    public void buscarDocumentoPessoa() {
        List<DocumentoIntegradorPessoasDTO> test = documentoPessoaService.buscarDocumentoPessoa();
        assertNotNull(test);
    }

}
