package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoChefia;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import lombok.SneakyThrows;
import org.junit.Ignore;
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
public class AnaliseTecnicaDespachoChefiaServiceIT extends BaseIntegrationTestCofig{
    @Autowired
    private AnaliseTecnicaDespachoChefiaService analiseTecnicaDespachoChefiaService;

    @Test
    @SneakyThrows
    public void salvar(){
        AnaliseTecnicaDespachoChefia analiseTecnicaDespachoChefia = analiseTecnicaDespachoChefiaService.salvar(criaAnaliseTecnicaDespachoChefia());
        assertNotNull(analiseTecnicaDespachoChefia);
        assertNotNull(analiseTecnicaDespachoChefia.getJustificativa());
        assertNotNull(analiseTecnicaDespachoChefia.getAnaliseTecnicaDespachoID());
    }

    @Test
    @SneakyThrows
    public void salvarListaAnaliseTecnicaDespachoChefia(){
        List<AnaliseTecnicaDespachoChefia> analiseTecnicaDespachoChefia = analiseTecnicaDespachoChefiaService.salvar(criarListaDespachoDTO(),criarAnaliseTecnica());
        assertNotNull(analiseTecnicaDespachoChefia);
        assertNotNull(analiseTecnicaDespachoChefia.get(0).getJustificativa());
        assertNotNull(analiseTecnicaDespachoChefia.get(0).getAnaliseTecnicaDespachoID());
    }

    @Test
    @SneakyThrows
    public void salvarListaAnaliseTecnicaDespachoChefiaDespachoNull(){
        List<AnaliseTecnicaDespachoChefia> analiseTecnicaDespachoChefia = analiseTecnicaDespachoChefiaService.salvar(null,criarAnaliseTecnica());
        assertTrue(analiseTecnicaDespachoChefia.isEmpty());
    }

    private AnaliseTecnicaDespachoChefia criaAnaliseTecnicaDespachoChefia(){
        Despacho despacho = new Despacho();
        despacho.setId(1L);
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setAnaliseTecnica(criarAnaliseTecnica());
        analiseTecnicaDespachoID.setDespacho(despacho);
        AnaliseTecnicaDespachoChefia analiseTecnicaDespachoChefia = new AnaliseTecnicaDespachoChefia();
        analiseTecnicaDespachoChefia.setJustificativa("teste");
        analiseTecnicaDespachoChefia.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
        return analiseTecnicaDespachoChefia;
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
