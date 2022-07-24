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

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 18/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class StatusAnaliseDespachoSecretarioIT extends BaseIntegrationTestCofig {

    private StatusAnaliseDespacho statusAnaliseDespacho;

    @Before
    public void setUp() {
        statusAnaliseDespacho =
                StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(PermissaoAnaliseEnum.EXEC_ANALISE_SECRETARIO);
    }

    @Test
    public void getCodigoStatusPorDespachoSecretarioVazio() {
        AnaliseTecnicaDTO analiseTecnicaDTO = criarAnaliseTecnicaDTO(Collections.emptyList());
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertNull(statusAnaliseTecnica);
    }

    @Test
    public void getCodigoStatusPorDespachoSecretarioPreenchido() {
        AnaliseTecnicaDTO analiseTecnicaDTO = criarAnaliseTecnicaDTO(criarListaDespachoDTO());
        StatusAnaliseTecnica statusAnaliseTecnica = statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(Integer.valueOf(5), statusAnaliseTecnica.getId());
    }

    private AnaliseTecnicaDTO criarAnaliseTecnicaDTO(List<DespachoDTO> despachoDTOs) {
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        analiseTecnicaDTO.setDespachosSecretario(despachoDTOs);
        return analiseTecnicaDTO;
    }

    private List<DespachoDTO> criarListaDespachoDTO() {
        DespachoDTO despachoDTO = new DespachoDTO();
        despachoDTO.setId(11L);
        return asList(despachoDTO);
    }
}
