package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Basis Tecnologia on 23/11/2016.
 */
public class PosseInformalTest {
    private PosseInformal posseInformal;
    private PosseInformal posseInformal2;

    @Before
    public void setUp(){
        posseInformal = new PosseInformal();
        posseInformal2 = new PosseInformal();
    }


    @Test
    public void getImovel() throws Exception {
        Imovel imovel = new Imovel();
        posseInformal.setImovel(imovel);
        assertEquals(imovel, posseInformal.getImovel());
    }

    @Test
    public void getDestinacoes() throws Exception {
        List<Destinacao> listaDestinacao = new ArrayList<>();
        Destinacao destinacao = new Destinacao();
        listaDestinacao.add(destinacao);
        posseInformal.setDestinacoes(listaDestinacao);
        assertEquals(listaDestinacao, posseInformal.getDestinacoes());
    }

    @Test
    public void getInteressados() throws Exception {
        List<Ocupante> listaOcupantes = new ArrayList<>();
        Ocupante ocupante = new Ocupante();
        listaOcupantes.add(ocupante);
        posseInformal.setOcupantes(listaOcupantes);
        assertEquals(listaOcupantes, posseInformal.getOcupantes());
    }

    @Test
    public void getTipoPosse() throws Exception {
        DadosResponsavel dadosResponsavel = new DadosResponsavel();
        dadosResponsavel.setTipoPosseOcupacao(TipoPosseOcupacaoEnum.INDIVIDUAL);
        assertEquals(TipoPosseOcupacaoEnum.INDIVIDUAL, dadosResponsavel.getTipoPosseOcupacao());
    }

    @Test
    public void getNomeEntidade() throws Exception {
        posseInformal.setNomeEntidade("teste");
        assertEquals("teste", posseInformal.getNomeEntidade());
    }

    @Test
    public void getCnpj() throws Exception {
        posseInformal.setCnpj("03247831169");
        assertEquals("03247831169", posseInformal.getCnpj());
    }
    @Test
    public void equalsInstanciaIguais(){
        posseInformal = criarPosseInformal(1L);
        assertTrue(posseInformal.equals(posseInformal));
    }
    @Test
    public void equalsInstanciaNaoIguais(){
        posseInformal = criarPosseInformal(1L);
        assertFalse(posseInformal.equals(new Benfeitoria()));
    }

    @Test
    public void equalsIdDiferentes(){
        posseInformal = criarPosseInformal(1L);
        posseInformal2 = criarPosseInformal(2L);
        assertFalse(posseInformal.equals(posseInformal2));
    }

    @Test
    public void hashcode(){
        posseInformal = criarPosseInformal(1L);
        posseInformal2 = criarPosseInformal(1L);
        assertEquals(posseInformal.hashCode(), posseInformal.hashCode());
    }
    private PosseInformal criarPosseInformal(Long id){
        PosseInformal posseInformal = new PosseInformal();
        posseInformal.setId(id);
        return posseInformal;
    }
}