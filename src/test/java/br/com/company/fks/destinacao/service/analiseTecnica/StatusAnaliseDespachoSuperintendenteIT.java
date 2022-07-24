package br.com.company.fks.destinacao.service.analiseTecnica;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 18/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class StatusAnaliseDespachoSuperintendenteIT extends BaseIntegrationTestCofig {

    private StatusAnaliseDespacho statusAnaliseDespacho;

    @Before
    public void setUp() {
        statusAnaliseDespacho =
                StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(PermissaoAnaliseEnum.EXEC_ANALISE_SUPERINTENDENTE);
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaVazio() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosSuperintendente(criarListaDespachoDTO(13L));
        analiseTecnicaDTO.setDespachosChefia(Collections.emptyList());
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(6), statusAnaliseTecnica.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoSuperintendenteVazio() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosSuperintendente(Collections.emptyList());
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(1L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertNull(statusAnaliseTecnica);
    }

    @Test
    public void getCodigoStatusPorDespachoSuperintendenteUmaResposta() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosSuperintendente(criarListaDespachoDTO(13L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(1L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(6), statusAnaliseTecnica.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaSuperintendenteAcordo() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosSuperintendente(criarListaDespachoDTO(13L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(6L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(6), statusAnaliseTecnica.getId());
    }


    @Test
    public void getCodigoStatusPorDespachoChefiaAcordoSuperintendenteRetornarChefia() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosSuperintendente(criarListaDespachoDTO(14L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(6L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(4), statusAnaliseTecnica.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoSuperintendeMaisUmaResposta() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosSuperintendente(criarListaDespachoDTO(14L, 4L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(6L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(10), statusAnaliseTecnica.getId());
    }

    @Test
    public void filtrarDespachosSemResultado() {
        DespachoDTO despachoDTO = statusAnaliseDespacho.filtrarDespachos(criarListaDespachoDTO(14L));
        assertNull(despachoDTO);
    }

    @Test
    public void filtrarDespachoComResultado() {
        DespachoDTO despachoDTO = statusAnaliseDespacho.filtrarDespachos(criarListaDespachoDTO(14L, 5L));
        assertEquals(Long.valueOf(5L), despachoDTO.getId());
    }

    @Test
    public void filtrarDespachoComResultadoIgualUm() {
        DespachoDTO despachoDTO = statusAnaliseDespacho.filtrarDespachos(criarListaDespachoDTO(14L, 1L));
        assertEquals(Long.valueOf(1L), despachoDTO.getId());
    }

    private List<DespachoDTO> criarListaDespachoDTO(Long... ids) {
        List<DespachoDTO> despachoDTOs = new ArrayList<>();
        for (Long id : ids) {
            DespachoDTO despachoDTO = new DespachoDTO();
            despachoDTO.setId(id);
            despachoDTOs.add(despachoDTO);
        }
        return despachoDTOs;
    }

}
