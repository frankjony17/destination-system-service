package br.com.company.fks.destinacao.service.analiseTecnica;

import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.DespachoEnum;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.service.DominioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 02/01/17.
 */
@RunWith(PowerMockRunner.class)
public class StatusAnaliseDespachoSecretarioTest {

    @InjectMocks
    private StatusAnaliseDespachoSecretario statusAnaliseDespachoSecretario;

    @Mock
    private DespachoDTO despachoDTO;

    @Mock
    private DominioService dominioService;

    @Mock
    private StatusAnaliseTecnica statusAnaliseTecnica;

    @Mock
    private AnaliseTecnicaDTO analiseTecnicaDTO;

    @Before
    public void setUp() {
        when(analiseTecnicaDTO.getDespachosSecretario()).thenReturn(asList(despachoDTO));
    }

    @Test
    public void getCodigoStatusPorDespachoSecretarioAprovaAdesao() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO.getCodigo();
        when(despachoDTO.getId()).thenReturn(DespachoEnum.APROVO_ADESAO.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSecretario.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoSecretarioAprovaIndeferimento() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.INDEFERIDO.getCodigo();
        when(despachoDTO.getId()).thenReturn(DespachoEnum.APROVO_INDEFERIMENTO.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSecretario.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoSecretarioRetornarSuperintendente() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
        when(despachoDTO.getId()).thenReturn(DespachoEnum.RETORNAR_SUPERINTENDENTE.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSecretario.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoSecretarioVazio() {
        when(analiseTecnicaDTO.getDespachosSecretario()).thenReturn(new ArrayList<>());
        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSecretario.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertNull(statusAnaliseAtual);
    }
}
