package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoTecnico;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoTecnicoRepository;
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
public class AnaliseTecnicaDespachoTecnicoServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private AnaliseTecnicaDespachoTecnicoService analiseTecnicaDespachoTecnicoService;

    @Test
    @SneakyThrows
    public void salvar(){
        AnaliseTecnicaDespachoTecnico analiseTecnicaDespachoTecnico = analiseTecnicaDespachoTecnicoService.salvar(criaAnaliseTecnicaDespachoTecnico());
        assertNotNull(analiseTecnicaDespachoTecnico.getAnaliseTecnicaDespachoID());
    }

    @Test
    @SneakyThrows
    public void salvarLista(){
        List<DespachoDTO> despachos = null;
        AnaliseTecnica analiseTecnica = new AnaliseTecnica();
        analiseTecnica.setId(125L);
        List<AnaliseTecnicaDespachoTecnico> analiseTecnicaDespachoTecnicos = analiseTecnicaDespachoTecnicoService.salvar(despachos, analiseTecnica);
        assertTrue(analiseTecnicaDespachoTecnicos.isEmpty());
    }

    private AnaliseTecnicaDespachoTecnico criaAnaliseTecnicaDespachoTecnico(){
        Despacho despacho = new Despacho();
        despacho.setId(1L);
        AnaliseTecnica analiseTecnica = new AnaliseTecnica();
        analiseTecnica.setId(125L);
        AnaliseTecnicaDespachoID analiseTecnicaDespachoID = new AnaliseTecnicaDespachoID();
        analiseTecnicaDespachoID.setAnaliseTecnica(analiseTecnica);
        analiseTecnicaDespachoID.setDespacho(despacho);
        AnaliseTecnicaDespachoTecnico analiseTecnicaDespachoTecnico = new AnaliseTecnicaDespachoTecnico();
        analiseTecnicaDespachoTecnico.setJustificativa("teste");
        analiseTecnicaDespachoTecnico.setAnaliseTecnicaDespachoID(analiseTecnicaDespachoID);
        return analiseTecnicaDespachoTecnico;
    }
}
