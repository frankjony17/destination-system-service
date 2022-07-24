package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class PosseInformalIT {
    private PosseInformal posseInformal;
    private PosseInformal posseInformal2;
    private Destinacao destinacao = new Destinacao();
    private Ocupante ocupante = new Ocupante();

    @Before
    public void setUp(){
        posseInformal = new PosseInformal();
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
        listaDestinacao.add(destinacao);
        posseInformal.setDestinacoes(listaDestinacao);
        assertEquals(listaDestinacao, posseInformal.getDestinacoes());
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
    public void equalsIdIgual(){
        posseInformal = criarPosseInformal(1l);
        posseInformal2 = criarPosseInformal(1L);
        assertTrue(posseInformal.equals(posseInformal2));
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
