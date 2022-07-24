package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSuperintendente;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 30/12/16.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class AnaliseTecnicaDespachoSuperintendenteServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private AnaliseTecnicaDespachoSuperintendenteService analiseTecnicaDespachoSuperintendenteService;

    @Test
    @SneakyThrows
    public void salvar(){
        AnaliseTecnicaDespachoSuperintendente analiseTecnicaDespachoSuperintendente = analiseTecnicaDespachoSuperintendenteService.salvar(criaAnaliseTecnicaDespachoSuperintendente());
        assertNotNull(analiseTecnicaDespachoSuperintendente);
        assertNotNull(analiseTecnicaDespachoSuperintendente.getJustificativa());
        assertNotNull(analiseTecnicaDespachoSuperintendente.getAnaliseTecnicaDespachoID());
    }

    @Test
    @SneakyThrows
    public void salvarListaAnaliseTecnicaDespachoSuperintendente(){
        List<AnaliseTecnicaDespachoSuperintendente> analiseTecnicaDespachoSuperintendenteList = analiseTecnicaDespachoSuperintendenteService.salvar(criarListaDespachoDTO(),criarAnaliseTecnica());
        assertNotNull(analiseTecnicaDespachoSuperintendenteList);
        assertNotNull(analiseTecnicaDespachoSuperintendenteList.get(0).getJustificativa());
        assertNotNull(analiseTecnicaDespachoSuperintendenteList.get(0).getAnaliseTecnicaDespachoID());
    }

    @Test
    @SneakyThrows
    public void salvarListaAnaliseTecnicaDespachoSuperintendenteDespachoNull(){
        List<AnaliseTecnicaDespachoSuperintendente> analiseTecnicaDespachoSuperintendenteList = analiseTecnicaDespachoSuperintendenteService.salvar(null,criarAnaliseTecnica());
        assertTrue(analiseTecnicaDespachoSuperintendenteList.isEmpty());
    }

    private AnaliseTecnicaDespachoSuperintendente criaAnaliseTecnicaDespachoSuperintendente(){
        Despacho despacho = new Despacho();
        despacho.setId(1L);
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setAnaliseTecnica(criarAnaliseTecnica());
        analiseTecnicaDespachoID.setDespacho(despacho);
        AnaliseTecnicaDespachoSuperintendente analiseTecnicaDespachoSuperintendente = new AnaliseTecnicaDespachoSuperintendente();
        analiseTecnicaDespachoSuperintendente.setJustificativa("teste");
        analiseTecnicaDespachoSuperintendente.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
        return analiseTecnicaDespachoSuperintendente;
    }

    private AnaliseTecnica criarAnaliseTecnica(){
        AnaliseTecnica analiseTecnica = new AnaliseTecnica();
        analiseTecnica.setId(125L);
        return analiseTecnica;
    }

    private List<DespachoDTO> criarListaDespachoDTO(){
        DespachoDTO despachoDTO = new DespachoDTO();
        despachoDTO.setId(1L);
        despachoDTO.setJustificativa("Teste");
        List<DespachoDTO> despachoDTOs = new ArrayList<>();
        despachoDTOs.add(despachoDTO);
        return despachoDTOs;
    }
}
