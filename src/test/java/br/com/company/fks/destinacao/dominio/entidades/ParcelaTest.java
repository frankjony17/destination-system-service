package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Test;

import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by samuel on 15/05/17.
 */
public class ParcelaTest {
    private Parcela parcela;
    private Parcela parcela2;

    @Test
    public void equalsInstanciaIguais(){
        parcela = criaParcela(1L);
        assertTrue(parcela.equals(parcela));
    }

    @Test
    public void equalsInstanciaNaoIguais(){
        parcela = criaParcela(1L);
        assertFalse(parcela.equals(new Benfeitoria()));
    }
    @Test
    public void equalsSequencialNaoIgual(){
        parcela = criarParcela(1L, "Exemplo");
        parcela2 = criarParcela(1L, "Exemplo");
        assertTrue(parcela.equals(parcela2));
    }
    @Test
    public void equalsIdDiferentes(){
        parcela = criaParcela(1L);
        parcela2 = criaParcela(2L);
        assertFalse(parcela.equals(parcela2));
    }

    @Test
    public void hashcode() {
        parcela = criarParcela(1L, "exemplo");
        parcela2 = criarParcela(1L, "exemplo");
        assertEquals(parcela.hashCode(), parcela2.hashCode());
    }

    public Parcela criarParcela(Long id, String ex){
       Parcela parcela = new Parcela();
       parcela.setId(id);
       parcela.setSequencial(ex);
       return parcela;
    }

    public Parcela criaParcela(Long id){
        Parcela parcela = new Parcela();
        parcela.setId(id);
        return parcela;
    }

    @Test
    public void getDestinacaoImoveisNull(){
        Parcela parcela = new Parcela();
        assertNull(parcela.getDestinacaoImoveis());
    }

    @Test
    public void getDestinacaoImoveisIsEmpty(){
        Parcela parcela = new Parcela();
        parcela.setDestinacaoImoveis(new ArrayList<>());
        assertTrue(parcela.getDestinacaoImoveis().isEmpty());
    }

    @Test
    public void getDescParcela(){

        Imovel imovel = new Imovel();
        imovel.setRip("00000001");
        Parcela parcela = new Parcela();
        parcela.setId(1L);
        parcela.setImovel(imovel);
        assertNull(parcela.getDestinacaoImoveis());

        parcela.setDestinacaoImoveis(new ArrayList<>());

        assertTrue(parcela.getDestinacaoImoveis().isEmpty());


        assertNotNull(parcela.getDescParcela());

    }

    @Test
    public void getDescParcelaFalse(){

        Imovel imovel = new Imovel();
        imovel.setRip("00000001");
        Parcela parcela = new Parcela();
        parcela.setId(1L);
        parcela.setImovel(imovel);

        parcela.setDestinacaoImoveis(new ArrayList<>());


        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();



        destinacaoImovel.setId(1l);
        destinacaoImovel.setUltimaDestinacao(true);

        parcela.getDestinacaoImoveis().add(destinacaoImovel);

        assertNotNull(parcela.getDestinacaoImoveis());

        assertNotNull(parcela.getDescParcela());
    }

    @Test
    public void getUtilizacao(){

        Parcela parcela = new Parcela();
        parcela.setId(1L);
        assertNull(parcela.getDestinacaoImoveis());

        parcela.setDestinacaoImoveis(new ArrayList<>());

        assertTrue(parcela.getDestinacaoImoveis().isEmpty());

        assertNotNull(parcela.getUtilizacao(), parcela.getDestinacaoImoveis());
    }

    @Test
    public void getUtilizacaoElse(){
        Imovel imovel = new Imovel();
        imovel.setRip("00000001");
        Parcela parcela = new Parcela();
        parcela.setId(1L);
        parcela.setImovel(imovel);
        assertNull(parcela.getDestinacaoImoveis());

        parcela.setDestinacaoImoveis(new ArrayList<>());

        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();

        destinacaoImovel.setId(1l);
        destinacaoImovel.setUltimaDestinacao(true);

        Destinacao destinacao = new Destinacao();
        destinacao.setId(1l);


        Utilizacao utilizacao = new Utilizacao();
        utilizacao.setId(1l);
        utilizacao.setNumeroProcesso("1");


        TipoUtilizacao tipoUtilizacao = new TipoUtilizacao();
        tipoUtilizacao.setId(1);
        tipoUtilizacao.setDescricao("destricao tipo de utilizacao");
        utilizacao.setTipoUtilizacao(tipoUtilizacao);


        destinacao.setUtilizacao(utilizacao);
        destinacaoImovel.setDestinacao(destinacao);
        parcela.getDestinacaoImoveis().add(destinacaoImovel);
        assertTrue(destinacaoImovel.getUltimaDestinacao());
        assertTrue(destinacaoImovel.getDestinacao().getUtilizacao().getTipoUtilizacao().getDescricao() != null);

        assertFalse(!destinacaoImovel.getUltimaDestinacao());
        assertFalse(!(destinacaoImovel.getDestinacao().getUtilizacao().getTipoUtilizacao().getDescricao() != null));

        assertNotNull(parcela.getDestinacaoImoveis());

        assertNotNull(parcela.getUtilizacao());
    }

    @Test
    public void getUtilizacaoNull(){
        Imovel imovel = new Imovel();
        imovel.setRip("00000001");
        Parcela parcela = new Parcela();
        parcela.setId(1L);
        parcela.setImovel(imovel);
        assertNull(parcela.getDestinacaoImoveis());

        parcela.setDestinacaoImoveis(new ArrayList<>());

        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();

        destinacaoImovel.setId(1l);
        destinacaoImovel.setUltimaDestinacao(false);

        Destinacao destinacao = new Destinacao();
        destinacao.setId(1l);


        Utilizacao utilizacao = new Utilizacao();
        utilizacao.setId(1l);
        utilizacao.setNumeroProcesso("1");


        TipoUtilizacao tipoUtilizacao = new TipoUtilizacao();
        tipoUtilizacao.setId(1);
        utilizacao.setTipoUtilizacao(tipoUtilizacao);


        destinacao.setUtilizacao(utilizacao);
        destinacaoImovel.setDestinacao(destinacao);
        parcela.getDestinacaoImoveis().add(destinacaoImovel);
        for (DestinacaoImovel destinacaoImovel1: parcela.getDestinacaoImoveis()) {
            assertFalse(destinacaoImovel.getUltimaDestinacao());
            assertFalse(destinacaoImovel.getDestinacao().getUtilizacao().getTipoUtilizacao().getDescricao() != null);
        }
        assertNotNull(parcela.getDestinacaoImoveis());

        assertNotNull(parcela.getUtilizacao());
    }
}
