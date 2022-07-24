package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class EnderecoIT {
    private Endereco endereco;

    @Before
    public void setUp(){
        endereco = new Endereco();
    }

    @Test
    public void getId() throws Exception {
        endereco.setId(1l);
        assertEquals(Long.valueOf(1), endereco.getId());
    }

    @Test
    public void getCep() throws Exception {
        endereco.setCep("70650156");
        assertEquals("70650156", endereco.getCep());
    }

    @Test
    public void getTipoLogradouro() throws Exception {
        endereco.setTipoLogradouro("bairo");
        assertEquals("bairo", endereco.getTipoLogradouro());
    }

    @Test
    public void getLogradouro() throws Exception {
        endereco.setLogradouro("bairro");
        assertEquals("bairro", endereco.getLogradouro());
    }

    @Test
    public void getNumero() throws Exception {
        endereco.setNumero("1");
        assertEquals("1", endereco.getNumero());
    }

    @Test
    public void getComplemento() throws Exception {
        endereco.setComplemento("teste");
        assertEquals("teste", endereco.getComplemento());
    }

    @Test
    public void getMunicipio() throws Exception {
        endereco.setMunicipio("DF");
        assertEquals("DF", endereco.getMunicipio());
    }

    @Test
    public void getBairro() throws Exception {
        endereco.setBairro("Cruzeiro");
        assertEquals("Cruzeiro", endereco.getBairro());
    }

    @Test
    public void getUf() throws Exception {
        endereco.setUf("DF");
        assertEquals("DF", endereco.getUf());
    }

    @Test
    public void getPais() throws Exception {
        endereco.setPais("Brasil");
        assertEquals("Brasil", endereco.getPais());
    }

    @Test
    public void getCidadeExterior() throws Exception {
        endereco.setCidadeExterior("EUA");
        assertEquals("EUA", endereco.getCidadeExterior());
    }

    @Test
    public void getCodigoPostal() throws Exception {
        endereco.setCodigoPostal("teste");
        assertEquals("teste", endereco.getCodigoPostal());
    }
}
