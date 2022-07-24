package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoChefia;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSecretario;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoSecretarioRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 30/12/16.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class AnaliseTecnicaDespachoSecretarioServiceIT extends BaseIntegrationTestCofig{
    @Autowired
    private AnaliseTecnicaDespachoSecretarioService analiseTecnicaDespachoSecretarioService;

    @Test
    @SneakyThrows
    public void salvar(){
        AnaliseTecnicaDespachoSecretario analiseTecnicaDespachoSecretario = analiseTecnicaDespachoSecretarioService.salvar(criaAnaliseTecnicaDespachoSecretario());
        assertNotNull(analiseTecnicaDespachoSecretario);
        assertNotNull(analiseTecnicaDespachoSecretario.getJustificativa());
        assertNotNull(analiseTecnicaDespachoSecretario.getAnaliseTecnicaDespachoID());
    }

    @Test
    @SneakyThrows
    public void salvarListaAnaliseTecnicaDespachoSecretario(){
        List<AnaliseTecnicaDespachoSecretario> analiseTecnicaDespachoSecretarios = analiseTecnicaDespachoSecretarioService.salvar(criarListaDespachoDTO(),criarAnaliseTecnica());
        assertNotNull(analiseTecnicaDespachoSecretarios);
        assertNotNull(analiseTecnicaDespachoSecretarios.get(0).getJustificativa());
        assertNotNull(analiseTecnicaDespachoSecretarios.get(0).getAnaliseTecnicaDespachoID());
    }

    @Test
    @SneakyThrows
    public void salvarListaAnaliseTecnicaDespachoSecretarioDespachoNull(){
        List<AnaliseTecnicaDespachoSecretario> analiseTecnicaDespachoSecretarios = analiseTecnicaDespachoSecretarioService.salvar(null,criarAnaliseTecnica());
        assertTrue(analiseTecnicaDespachoSecretarios.isEmpty());
    }

    private AnaliseTecnicaDespachoSecretario criaAnaliseTecnicaDespachoSecretario(){
        Despacho despacho = new Despacho();
        despacho.setId(1L);
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setAnaliseTecnica(criarAnaliseTecnica());
        analiseTecnicaDespachoID.setDespacho(despacho);
        AnaliseTecnicaDespachoSecretario analiseTecnicaDespachoSecretario = new AnaliseTecnicaDespachoSecretario();
        analiseTecnicaDespachoSecretario.setJustificativa("teste");
        analiseTecnicaDespachoSecretario.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
        return analiseTecnicaDespachoSecretario;
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
