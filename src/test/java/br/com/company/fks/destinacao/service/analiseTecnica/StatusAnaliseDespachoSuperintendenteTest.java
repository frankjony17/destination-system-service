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
public class StatusAnaliseDespachoSuperintendenteTest {

    @InjectMocks
    private StatusAnaliseDespachoSuperintendente statusAnaliseDespachoSuperintendente;

    @Mock
    private DespachoDTO despachoDTOSuperintendente;

    @Mock
    private DespachoDTO despachoDTOAlterarAnalise;

    @Mock
    private DespachoDTO despachoDTOChefia;

    @Mock
    private DominioService dominioService;

    @Mock
    private StatusAnaliseTecnica statusAnaliseTecnica;

    @Mock
    private AnaliseTecnicaDTO analiseTecnicaDTO;

    @Before
    public void setUp() {
        when(analiseTecnicaDTO.getDespachosSuperintendente()).thenReturn(asList(despachoDTOSuperintendente));
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefia));
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaDeAcordoSuperintendenteDeAcordoAnalise() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TEC_CHEFIA.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaDeAcordoSuperintendenteRetornarAnalise() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.RETORNAR_AVALIACAO_TEC_CHEFIA.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaDeAcordoSuperintendenteAlterarAnaliseDeAcordoAvaliacao() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
        when(despachoDTOAlterarAnalise.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(analiseTecnicaDTO.getDespachosSuperintendente()).thenReturn(asList(despachoDTOSuperintendente, despachoDTOAlterarAnalise));
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TEC_CHEFIA.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);


        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaDeAcordoSuperintendenteAlterarAnaliseNaoAtendeRequisitos() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.INDEFERIDO.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
        when(despachoDTOAlterarAnalise.getId()).thenReturn(DespachoEnum.NAO_ATENDE_AOS_REQUISITOS.getCodigo());
        when(analiseTecnicaDTO.getDespachosSuperintendente()).thenReturn(asList(despachoDTOSuperintendente, despachoDTOAlterarAnalise));
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaDeAcordoSuperintendenteAlterarAnaliseRetornarPendenciaRequerente() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_REQUERENTE.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
        when(despachoDTOAlterarAnalise.getId()).thenReturn(DespachoEnum.RETORNAR_PENDENCIA_REQUERENTE.getCodigo());
        when(analiseTecnicaDTO.getDespachosSuperintendente()).thenReturn(asList(despachoDTOSuperintendente, despachoDTOAlterarAnalise));
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaDeAcordoSuperintendenteAlterarAnaliseCancelado() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.CANCELADO.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
        when(despachoDTOAlterarAnalise.getId()).thenReturn(DespachoEnum.CANCELAR_ERRO_DUPLICIDADE.getCodigo());
        when(analiseTecnicaDTO.getDespachosSuperintendente()).thenReturn(asList(despachoDTOSuperintendente, despachoDTOAlterarAnalise));
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaSemRespostaSuperintendenteAtendeRequisitos() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(new ArrayList<>());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaSemRespostaSuperintendenteNaoAtendeRequisitos() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.NAO_ATENDE_AOS_REQUISITOS.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(new ArrayList<>());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaSemRespostaSuperintendenteRetornarPendencia() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.RETORNAR_PENDENCIA_REQUERENTE.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(new ArrayList<>());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaSemRespostaSuperintendenteCancelar() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo();
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.CANCELAR_ERRO_DUPLICIDADE.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(new ArrayList<>());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaRespostaErradaSuperintendenteCancelar() {
        when(despachoDTOSuperintendente.getId()).thenReturn(DespachoEnum.CANCELAR_ERRO_DUPLICIDADE.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.APROVO_ADESAO.getCodigo());
        when(analiseTecnicaDTO.getDespachosSuperintendente()).thenReturn(asList(despachoDTOSuperintendente));
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefia));

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertNull(statusAnaliseAtual);
    }

    @Test
    public void getCodigoStatusPorDespachoChefiaAtendeRequisitosaSuperintendenteSemResposta() {
        when(analiseTecnicaDTO.getDespachosSuperintendente()).thenReturn(new ArrayList<>());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.RETORNAR_PARA_ANALISE_TECNICA.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefia));

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertNull(statusAnaliseAtual);
    }
}
