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
public class StatusAnaliseDespachoChefiaIT extends BaseIntegrationTestCofig {

    private StatusAnaliseDespacho statusAnaliseDespacho;

    @Before
    public void setUp() {
        statusAnaliseDespacho =
                StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA);
    }


    @Test
    public void getCodigoStatusPorDespachoTecnicoVazio() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosTecnico(Collections.emptyList());
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(1L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(5), statusAnaliseTecnica.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaVazio() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosTecnico(criarListaDespachoDTO(1L));
        analiseTecnicaDTO.setDespachosChefia(Collections.emptyList());
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertNull(statusAnaliseTecnica);
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaNaoConcordaTecnicoAcordo() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosTecnico(criarListaDespachoDTO(1L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(2L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(5), statusAnaliseTecnica.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaConcordaTecnicoAcordo() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosTecnico(criarListaDespachoDTO(1L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(6L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(5), statusAnaliseTecnica.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaConcordaTecnicoNaoAcordo() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosTecnico(criarListaDespachoDTO(2L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(6L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(5), statusAnaliseTecnica.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaNaoConcordaTecnicoNaoAcordo() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosTecnico(criarListaDespachoDTO(2L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(2L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(5), statusAnaliseTecnica.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaQtdRespostaMaiorUm() {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosTecnico(criarListaDespachoDTO(1L));
        analiseTecnicaDTO.setDespachosChefia(criarListaDespachoDTO(2L, 7L));
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(5), statusAnaliseTecnica.getId());
    }

    @Test
    public void filtrarDespachoAtendeCondicaoIgualSeis() {
        DespachoDTO despachoDTO = statusAnaliseDespacho.filtrarDespachos(criarListaDespachoDTO(14L, 6L));
        assertEquals(Long.valueOf(6L), despachoDTO.getId());
    }

    @Test
    public void filtrarDespachoAtendeCondicaoIgualUm() {
        DespachoDTO despachoDTO = statusAnaliseDespacho.filtrarDespachos(criarListaDespachoDTO(14L, 1L));
        assertEquals(Long.valueOf(1L), despachoDTO.getId());
    }

    @Test
    public void filtrarDespachoAtendeCondicaoMenorSeis() {
        DespachoDTO despachoDTO = statusAnaliseDespacho.filtrarDespachos(criarListaDespachoDTO(14L, 5L));
        assertEquals(Long.valueOf(5L), despachoDTO.getId());
    }

    @Test
    public void filtrarDespachoAtendeCondicaoMaiorUm() {
        DespachoDTO despachoDTO = statusAnaliseDespacho.filtrarDespachos(criarListaDespachoDTO(14L, 3L));
        assertEquals(Long.valueOf(3L), despachoDTO.getId());
    }

    @Test
    public void filtrarDespachoNaoAtendeCondicaoMaiorUm() {
        DespachoDTO despachoDTO = statusAnaliseDespacho.filtrarDespachos(criarListaDespachoDTO(14L, 0L));
        assertNull(despachoDTO);
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
