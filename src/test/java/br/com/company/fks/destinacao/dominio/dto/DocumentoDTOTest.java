package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 25/05/17.
 */
public class DocumentoDTOTest {

    private DocumentoDTO documentoDTO;
    private Date data;

    @Before
    public void setUp() {
        documentoDTO = new DocumentoDTO();
        data = new Date();
    }

    @Test
    public void dataPublicacaoNula() {
        documentoDTO.setDataPublicacao(null);
        assertNull(documentoDTO.getDataPublicacao());
    }

    @Test
    public void dataPublicacaoNaoNula() {
        documentoDTO.setDataPublicacao(data);
        assertEquals(data, documentoDTO.getDataPublicacao());
    }

    @Test
    public void getDataInicialVigenciaNula() {
        documentoDTO.setDataInicialVigencia(null);
        assertNull(documentoDTO.getDataInicialVigencia());
    }

    @Test
    public void getDataIncialNaoNula() {
        documentoDTO.setDataInicialVigencia(data);
        assertEquals(data, documentoDTO.getDataInicialVigencia());
    }

    @Test
    public void DataFinalVigenciaNula() {
      documentoDTO.setDataFinalVigencia(null);
      assertNull(documentoDTO.getDataFinalVigencia());
    }

    @Test
    public void getDataFinalVigenciaNaoNula() {
        documentoDTO.setDataFinalVigencia(data);
        assertEquals(data, documentoDTO.getDataFinalVigencia());
    }

}
