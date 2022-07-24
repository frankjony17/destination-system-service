package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Basis Tecnologia on 22/11/2016.
 */
public class TipoMoedaTest {

    private TipoMoeda tipoMoeda;
    private TipoMoeda tipoMoeda1;

    @Before
    public void setUp(){
        tipoMoeda = new TipoMoeda();
        tipoMoeda1 = new TipoMoeda();
    }

    @Test
    public void getId() throws Exception {
        tipoMoeda.setId(1);
        assertEquals(Integer.valueOf(1),tipoMoeda.getId());
    }

    @Test
    public void getDescricao() throws Exception {
        tipoMoeda.setDescricao("teste");
        assertEquals("teste", tipoMoeda.getDescricao());
    }

    @Test
    public void tipoMoedaConstrutor(){
        tipoMoeda = new TipoMoeda(1);
        assertEquals(Integer.valueOf(1), tipoMoeda.getId());
    }
    @Test
    public void equalsInstaciaNaoIgual(){
        tipoMoeda = new TipoMoeda(1);
        assertFalse(tipoMoeda.equals(new Arquivo()));
    }
    @Test
    public void equalsIdNaoIguais(){
        tipoMoeda = criarTipoMoeda(1, "Descrição");
        tipoMoeda1 = criarTipoMoeda(2, "Descrição");
        assertFalse(tipoMoeda.equals(tipoMoeda1));
    }
    @Test
    public void equalsDescricaoDiferente(){
        tipoMoeda = criarTipoMoeda(1, "Descrição");
        tipoMoeda1 = criarTipoMoeda(1, "Descrição Diferente");
        assertFalse(tipoMoeda.equals(tipoMoeda1));
    }
    @Test
    public void hashcode(){
        tipoMoeda = criarTipoMoeda(1, "Descrição");
        tipoMoeda1 = criarTipoMoeda(1, "Descrição");
        assertEquals(tipoMoeda.hashCode(), tipoMoeda1.hashCode());
    }

    private TipoMoeda criarTipoMoeda(Integer id, String descricao){
        TipoMoeda tipoMoeda = new TipoMoeda();
        tipoMoeda.setId(id);
        tipoMoeda.setDescricao(descricao);
        return tipoMoeda;
    }

}