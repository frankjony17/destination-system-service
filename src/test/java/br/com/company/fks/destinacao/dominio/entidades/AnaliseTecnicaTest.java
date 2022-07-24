package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by diego on 04/01/17.
 */
@RunWith(PowerMockRunner.class)
public class AnaliseTecnicaTest {

    private static final String JUSTIFICATIVA = "teste";
    private AnaliseTecnica analiseTecnica;

    @Before
    public void setUp() {
        analiseTecnica = new AnaliseTecnica();
    }

    @Test
    public void getDespachosTecnico() {
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setDespacho(new Despacho());
        AnaliseTecnicaDespachoTecnico analiseTecnicaDespachoTecnico = new AnaliseTecnicaDespachoTecnico();
        analiseTecnicaDespachoTecnico.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
        analiseTecnicaDespachoTecnico.setJustificativa(JUSTIFICATIVA);
        analiseTecnica.setDespachosAnaliseTecnico(asList(analiseTecnicaDespachoTecnico));
        assertFalse(analiseTecnica.getDespachosTecnico().isEmpty());
        Despacho despacho = analiseTecnica.getDespachosTecnico().get(0);
        assertEquals(JUSTIFICATIVA, despacho.getJustificativa());
    }

    @Test
    public void getDespachosChefia() {
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setDespacho(new Despacho());
        AnaliseTecnicaDespachoChefia analiseTecnicaDespachoChefia = new AnaliseTecnicaDespachoChefia();
        analiseTecnicaDespachoChefia.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
        analiseTecnicaDespachoChefia.setJustificativa(JUSTIFICATIVA);
        analiseTecnica.setDespachosAnaliseChefia(asList(analiseTecnicaDespachoChefia));
        assertFalse(analiseTecnica.getDespachosChefia().isEmpty());
        Despacho despacho = analiseTecnica.getDespachosChefia().get(0);
        assertEquals(JUSTIFICATIVA, despacho.getJustificativa());
    }

    @Test
    public void getDespachosSuperintendente() {
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setDespacho(new Despacho());
        AnaliseTecnicaDespachoSuperintendente analiseTecnicaDespachoSuperintendente = new AnaliseTecnicaDespachoSuperintendente();
        analiseTecnicaDespachoSuperintendente.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
        analiseTecnicaDespachoSuperintendente.setJustificativa(JUSTIFICATIVA);
        analiseTecnica.setDespachosAnaliseSuperintendente(asList(analiseTecnicaDespachoSuperintendente));
        assertFalse(analiseTecnica.getDespachosSuperintendente().isEmpty());
        Despacho despacho = analiseTecnica.getDespachosSuperintendente().get(0);
        assertEquals(JUSTIFICATIVA, despacho.getJustificativa());
    }

    @Test
    public void getDespachosSecretario() {
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setDespacho(new Despacho());
        AnaliseTecnicaDespachoSecretario analiseTecnicaDespachoSecretario = new AnaliseTecnicaDespachoSecretario();
        analiseTecnicaDespachoSecretario.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
        analiseTecnicaDespachoSecretario.setJustificativa(JUSTIFICATIVA);
        analiseTecnica.setDespachosAnaliseSecretario(asList(analiseTecnicaDespachoSecretario));
        assertFalse(analiseTecnica.getDespachosSecretario().isEmpty());
        Despacho despacho = analiseTecnica.getDespachosSecretario().get(0);
        assertEquals(JUSTIFICATIVA, despacho.getJustificativa());
    }

    @Test
    public void getDataEnvioPublicacao(){
        Date date = new Date();
        analiseTecnica.setDataEnvioPublicacao(date);
        assertEquals(date, analiseTecnica.getDataEnvioPublicacao());
    }

    @Test
    public void getDataEnvioPublicacaoNull(){
        analiseTecnica.setDataEnvioPublicacao(null);
        assertEquals(null, analiseTecnica.getDataEnvioPublicacao());
    }
}
