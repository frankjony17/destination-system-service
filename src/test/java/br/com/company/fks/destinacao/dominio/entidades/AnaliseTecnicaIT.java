package br.com.company.fks.destinacao.dominio.entidades;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class AnaliseTecnicaIT {

    private static final String JUSTIFICATIVA = "teste";
    private AnaliseTecnica analiseTecnica;
    private AnaliseTecnicaDespachoID analiseTecnicaDespachoID;

    @Before
    public void setUp() {
        analiseTecnica = new AnaliseTecnica();
        analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setDespacho(new Despacho());
    }

    @Test
    public void getDespachosTecnico() {
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
