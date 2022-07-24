package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 06/04/17.
 */
public class PendenciaDTOTest {

    private PendenciaDTO pendenciaDTO;
    private PendenciaDTO pendenciaDTO1;
    private Date data;

    @Before
    public void setUp() {
        pendenciaDTO = new PendenciaDTO();
        pendenciaDTO1 = new PendenciaDTO();
        data = new Date();
    }

    @Test
    public void testaConstrutorDois(){
        Long id = 1L;
        Long idDestinacao = 2L;
        String descricao = "descricao";
        String modulo = "modulo";
        Date dataGerada = new Date();

        PendenciaDTO pendenciaDTO = new PendenciaDTO(id, idDestinacao, descricao, modulo, dataGerada);
        assertEquals(id, pendenciaDTO.getId());
        assertEquals(idDestinacao, pendenciaDTO.getIdDestinacao());
        assertEquals(descricao, pendenciaDTO.getDescricao());
        assertEquals(modulo, pendenciaDTO.getModulo());
        assertEquals(dataGerada, pendenciaDTO.getDataGerada());
    }

    @Test
    public void testaConstrutor() {

        Long idDestinacao = 1L;
        String descricao = "teste";
        Date dataGerada = new Date();

        PendenciaDTO pendenciaDTO = new PendenciaDTO(idDestinacao, descricao, dataGerada);
        assertEquals(idDestinacao, pendenciaDTO.getIdDestinacao());
        assertEquals(descricao, pendenciaDTO.getDescricao());
        assertEquals(dataGerada, pendenciaDTO.getDataGerada());
    }


    @Test
    public void equalsInstanciaIguais() {
        pendenciaDTO = criarPendenciaDTO(1L, "descricao", "www.url.com.br");
        assertTrue(pendenciaDTO.equals(pendenciaDTO));
    }

    @Test
    public void equalsInstanciaDiferentes() {
        pendenciaDTO = criarPendenciaDTO(1l, "descricao", "www.url.com.br");
        assertFalse(pendenciaDTO.equals(new ImovelDTO()));
    }


    @Test
    public void equalsDescricaoDiferentes() {
        pendenciaDTO = criarPendenciaDTO(1l, "descricao", "www.url.com.br");
        pendenciaDTO1 = criarPendenciaDTO(1l, "descricao1", "www.url.com.br");
        assertFalse(pendenciaDTO.equals(pendenciaDTO1));
    }

    @Test
    public void equalsUrlDiferentes() {
        pendenciaDTO = criarPendenciaDTO(1l, "descricao", "www.url.com.br");
        pendenciaDTO1 = criarPendenciaDTO(1l, "descricao", "www.lru.com.br");
        assertFalse(pendenciaDTO.equals(pendenciaDTO1));
    }


    @Test
    public void hashcode() {
        pendenciaDTO = criarPendenciaDTO(1L, "Modulo", "www.url.com.br");
        pendenciaDTO1 = criarPendenciaDTO(1L, "Modulo", "www.url.com.br");
        //assertEquals(pendenciaDTO.hashCode(), pendenciaDTO1.hashCode());
    }

    private PendenciaDTO criarPendenciaDTO(Long id, String descricao, String url) {
        PendenciaDTO pendenciaDTO = new PendenciaDTO();
        pendenciaDTO.setId(id);
        pendenciaDTO.setDescricao(descricao);
        pendenciaDTO.setUrl(url);
        return pendenciaDTO;
    }

    @Test
    public void getDataGeradaNula() {
        pendenciaDTO.setDataGerada(null);
        assertNull(pendenciaDTO.getDataGerada());
    }

    @Test
    public void getDataGeradaNaoNula() {
        pendenciaDTO.setDataGerada(data);
        assertEquals(data, pendenciaDTO.getDataGerada());
    }

}