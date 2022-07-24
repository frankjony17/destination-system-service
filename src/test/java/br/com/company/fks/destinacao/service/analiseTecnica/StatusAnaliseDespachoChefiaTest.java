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
 * Created by diego on 30/12/16.
 */
@RunWith(PowerMockRunner.class)
public class StatusAnaliseDespachoChefiaTest {

    @InjectMocks
    private StatusAnaliseDespachoChefia statusAnaliseDespachoChefia;

    @Mock
    private AnaliseTecnicaDTO analiseTecnicaDTO;

    @Mock
    private DespachoDTO despachoDTOTecnico;

    @Mock
    private DespachoDTO despachoDTOChefia;

    @Mock
    private DespachoDTO despachoDTOChefiaAlterarAvaliacao;

    @Mock
    private DominioService dominioService;

    @Mock
    private StatusAnaliseTecnica statusAnaliseTecnica;

    @Before
    public void setUp() {
        when(analiseTecnicaDTO.getDespachosTecnico()).thenReturn(asList(despachoDTOTecnico));
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefia));
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoDeAcordoChefiaDeAcordoAnalise() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
        when(despachoDTOTecnico.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoDeAcordoChefiaRetornarAnalise() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA.getCodigo();
        when(despachoDTOTecnico.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.RETORNAR_PARA_ANALISE_TECNICA.getCodigo());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoDeAcordoChefiaAlterarAnaliseAtendeRequisitos() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
        when(despachoDTOTecnico.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(despachoDTOChefiaAlterarAvaliacao.getId()).thenReturn(DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefiaAlterarAvaliacao, despachoDTOChefia));
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);


        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoDeAcordoChefiaAlterarAnaliseNaoAtendeRequisitos() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
        when(despachoDTOTecnico.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.NAO_ATENDE_AOS_REQUISITOS.getCodigo());
        when(despachoDTOChefiaAlterarAvaliacao.getId()).thenReturn(DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefiaAlterarAvaliacao, despachoDTOChefia));
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);


        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoDeAcordoChefiaAlterarAnaliseRetornarPendenciaRequerente() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_REQUERENTE.getCodigo();
        when(despachoDTOTecnico.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.RETORNAR_PENDENCIA_REQUERENTE.getCodigo());
        when(despachoDTOChefiaAlterarAvaliacao.getId()).thenReturn(DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefiaAlterarAvaliacao, despachoDTOChefia));
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);


        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoDeAcordoChefiaAlterarAnaliseCancelar() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.CANCELADO.getCodigo();
        when(despachoDTOTecnico.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.CANCELAR_ERRO_DUPLICIDADE.getCodigo());
        when(despachoDTOChefiaAlterarAvaliacao.getId()).thenReturn(DespachoEnum.ALTERAR_AVALIACAO.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefiaAlterarAvaliacao, despachoDTOChefia));
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);


        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoDeAcordoChefiaSemDespacho() {
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(new ArrayList<>());

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertNull(statusAnaliseAtual);
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoSemRespostaChefiaAtendeRequisitos() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefia));
        when(analiseTecnicaDTO.getDespachosTecnico()).thenReturn(new ArrayList<>());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoSemRespostaChefiaNaoAtendeRequisitos() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.NAO_ATENDE_AOS_REQUISITOS.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefia));
        when(analiseTecnicaDTO.getDespachosTecnico()).thenReturn(new ArrayList<>());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoSemRespostaChefiaRetornarRequerente() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.AGUARDANDO_REQUERENTE.getCodigo();
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.RETORNAR_PENDENCIA_REQUERENTE.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefia));
        when(analiseTecnicaDTO.getDespachosTecnico()).thenReturn(new ArrayList<>());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }

    @Test
    public void getCodigoStatusPorDespachoTecnicoSemRespostaChefiaCancelado() {
        Integer idStatusAnalise = StatusAnaliseTecnicaEnum.CANCELADO.getCodigo();
        when(despachoDTOChefia.getId()).thenReturn(DespachoEnum.CANCELAR_ERRO_DUPLICIDADE.getCodigo());
        when(analiseTecnicaDTO.getDespachosChefia()).thenReturn(asList(despachoDTOChefia));
        when(analiseTecnicaDTO.getDespachosTecnico()).thenReturn(new ArrayList<>());
        when(dominioService.findById(idStatusAnalise.intValue(), StatusAnaliseTecnica.class)).thenReturn(statusAnaliseTecnica);
        when(statusAnaliseTecnica.getId()).thenReturn(idStatusAnalise);

        StatusAnaliseTecnica statusAnaliseAtual = statusAnaliseDespachoChefia.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        assertEquals(idStatusAnalise, statusAnaliseAtual.getId());
    }




}
