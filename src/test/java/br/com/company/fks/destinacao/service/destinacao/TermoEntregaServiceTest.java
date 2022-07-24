package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.TermoEntregaDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.repository.TermoEntregaRepository;
import br.com.company.fks.destinacao.service.*;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 23/01/17.
 */
@RunWith(PowerMockRunner.class)
public class TermoEntregaServiceTest {

    @InjectMocks
    private TermoEntregaService termoEntregaService;

    @Mock
    private ContratoService contratoService;

    @Mock
    private AtoAutorizativoService atoAutorizativoService;

    @Mock
    private TermoEntregaRepository termoEntregaRepository;

    @Mock
    private TermoEntrega termoEntrega;

    @Mock
    private DestinacaoService destinacaoService;

    @Mock
    private Contrato contrato;

    @Mock
    private AtoAutorizativo atoAutorizativo;

    @Mock
    private List<DestinacaoImovel> destinacaoImovelList;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private DestinacaoImovel novaDestinacaoImovel;

    @Mock
    private Imovel imovel;

    @Mock
    private DestinacaoImovel destinacaoImoveis;

    @Mock
    private DestinacaoImovelService destinacaoImovelService;

    @Mock
    private ResponsavelService responsavelService;

    @Mock
    private Responsavel responsavel;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private TermoEntregaDTO termoEntregaDTO;

    @Mock
    private Resposta<TermoEntregaDTO> resposta;

    @Mock
    private List<DestinacaoImovel> destinacoesImoveis;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Before
    public void setUp() {
        when(contrato.getId()).thenReturn(1L);
        when(atoAutorizativo.getId()).thenReturn(1L);
//        when(contratoService.salvar(any(Contrato.class), Boolean.FALSE)).thenReturn(contrato);
//        when(atoAutorizativoService.salvar(any(AtoAutorizativo.class), Boolean.FALSE)).thenReturn(atoAutorizativo);
        when(termoEntrega.getContrato()).thenReturn(contrato);
        when(termoEntrega.getId()).thenReturn(1L);
        when(termoEntregaRepository.save(any(TermoEntrega.class))).thenReturn(termoEntrega);
//        given(dadosResponsavelService.salvar(any(DadosResponsavel.class), Boolean.FALSE)).willReturn(dadosResponsavel);
        given(termoEntregaRepository.save(any(TermoEntrega.class))).willReturn(termoEntrega);
    }

    @Test
    public void salvarDadosEspecificos() {
        TermoEntrega test = termoEntregaService.salvarDadosEspecificos(termoEntrega);
        assertNotNull(test);
    }

    @Test
    @SneakyThrows
    public void atualizarParcela(){
        termoEntregaService.atualizarParcela(destinacaoImovelList);
    }

    @Test
    @SneakyThrows
    public void findOne(){
        when(termoEntregaRepository.findOne(1L)).thenReturn(termoEntrega);
        when(entityConverter.converterStrict(termoEntrega, TermoEntregaDTO.class)).thenReturn(termoEntregaDTO);
        resposta = termoEntregaService.findOne(1L);
        assertNotNull(resposta);
    }

    @Test
    public void desmarcarUltimaDestiancao() throws Exception {
        List<DestinacaoImovel> destinacaoImovels = mocarListaDestinacaoImoveis();

        doAnswer(invocationOnMock -> {
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            Assert.assertEquals(ids, invocationOnMock.getArguments()[0]);
            return true;
        }).when(destinacaoImovelService).desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas(any());

        termoEntregaService.desmarcarUltimaDestiancao(destinacaoImovels, Boolean.TRUE);
    }

    private List<DestinacaoImovel> mocarListaDestinacaoImoveis() {
        List<DestinacaoImovel> destinacaoImovels = new ArrayList<>();
        DestinacaoImovel destImovel = new DestinacaoImovel();
        Imovel imovel = new Imovel();
        imovel.setId(1L);
        destImovel.setImovel(imovel);
        destinacaoImovels.add(destImovel);

        return destinacaoImovels;
    }
}