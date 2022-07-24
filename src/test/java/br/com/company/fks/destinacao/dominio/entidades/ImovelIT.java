package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;


import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ImovelIT {
    private Imovel imovel;
    private Imovel imovel1;

    @Before
    public void setUp(){
        imovel = new Imovel();
        imovel1 = new Imovel();
    }

    @Test
    public void getId() throws Exception {
        imovel.setId(1l);
        assertEquals(Long.valueOf(1), imovel.getId());
    }

    @Test
    public void getRip() throws Exception {
        imovel.setRip("01");
        assertEquals("01", imovel.getRip());
    }

    @Test
    public void getAreaTerreno() throws Exception {
        imovel.setAreaTerreno(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, imovel.getAreaTerreno());
    }

    @Test
    public void getEndereco() throws Exception {
        Endereco endereco = new Endereco();
        imovel.setEndereco(endereco);
        assertEquals(endereco, imovel.getEndereco());
    }

    @Test
    public void getIdCadastroImovel() throws Exception{
        imovel.setIdCadastroImovel(1l);
        assertEquals(Long.valueOf(1), imovel.getIdCadastroImovel());
    }
    @Test
    public void equalsInstanciaIguais(){
        imovel = criarImovel(1L,1L, "0000006");
        assertTrue(imovel.equals(imovel));
    }

    @Test
    public void equalsInstanciaNaoIguais(){
        imovel = criarImovel(1L, 1L, "000000005");
        assertFalse(imovel.equals(new Benfeitoria()));
    }

    @Test
    public void equalsIdDiferente(){
        imovel = criarImovel(1L, 1L, "000000005");
        imovel1 = criarImovel(2L, 1L, "0000000005");
        assertFalse(imovel.equals(imovel1));
    }

    @Test
    public void equalsRipDiferente(){
        imovel = criarImovel(1L, 1L, "00000006");
        imovel1 = criarImovel(1L, 1L, "00000007");
        assertFalse(imovel.equals(imovel1));
    }

    @Test
    public void equalsIdCadastroImovelDiferente(){
        imovel = criarImovel(1L, 2L, "00000005");
        imovel1 = criarImovel(1L, 1L, "00000005");
        assertFalse(imovel.equals(imovel1));
    }

    @Test
    public void hashcode(){
        imovel = criarImovel(1L, 1L, "00000001");
        imovel1 = criarImovel(1L, 1L, "00000001");
        assertEquals(imovel.hashCode(), imovel1.hashCode());
    }

    private Imovel criarImovel(Long id,Long idCadastro, String rip ){
        Imovel imovel = new Imovel();
        imovel.setId(id);
        imovel.setIdCadastroImovel(idCadastro);
        imovel.setRip(rip);
        return imovel;
    }
}

