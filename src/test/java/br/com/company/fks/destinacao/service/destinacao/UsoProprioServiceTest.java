package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.PendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.UsoProprioDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.repository.UsoProprioRepository;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import br.com.company.fks.destinacao.service.ResponsavelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by basis on 01/08/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UsoProprioServiceTest {
    public static final Long ID_PENDENCIA_USO_PROPRIO = 4L;
    public static final Long ID_LONG_1 = 1L;

    @InjectMocks
    private UsoProprioService usoProprioService;

    @Mock
    private UsoProprio usoProprio;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private DestinacaoPendencia destinacaoPendencia;

    @Mock
    private UsoProprioRepository usoProprioRepository;

    @Mock
    private DestinacaoService destinacaoService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private UsoProprioDTO usoProprioDTO;

    @Mock
    private Resposta<UsoProprioDTO> resposta;

    @Mock
    private List<DestinacaoImovel> destinacoesImoveis;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private Imovel imovel;

    @Mock
    Stream<DestinacaoImovel> destinacaoImovelStream;

    @Mock
    DestinacaoImovelService destinacaoImovelService;

    @Mock
    private ResponsavelService responsavelService;

    @Mock
    private Responsavel responsavel;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private List<DestinacaoImovel> destinacaoImovelList;

    @Mock
    private PendenciaDTO pendenciaDTO;

    @Mock
    private DestinacaoPendenciaID destinacaoPendenciaID;

    @Mock
    private Pendencia pendencia;

    @Mock
    private Destinacao destinacao;

    @Mock
    private Set<DestinacaoPendencia> destinacaoPendenciaSet;

    @Mock
    private Set<PendenciaDTO> pendenciaDTOSet;

    @Before
    public void setUp() {
        usoProprio.setId(ID_LONG_1);
        usoProprioDTO.setId(ID_LONG_1);

        when(usoProprio.getId()).thenReturn(ID_LONG_1);
        when(usoProprioRepository.save(usoProprio)).thenReturn(usoProprio);
        given(usoProprioRepository.save(any(UsoProprio.class))).willReturn(usoProprio);
    }

    @Test
    @SneakyThrows
    public void findOne(){
        when(usoProprioRepository.findOne(ID_LONG_1)).thenReturn(usoProprio);
        when(entityConverter.converterStrict(usoProprio, UsoProprioDTO.class)).thenReturn(usoProprioDTO);

        resposta = usoProprioService.findOne(ID_LONG_1);

        assertNotNull(resposta);
        assertEquals(ID_LONG_1, usoProprio.getId());

    }

    @Test
    @SneakyThrows
    public void salvarDadosEspecificos(){
        UsoProprio test = usoProprioService.salvarDadosEspecificos(usoProprio);
        assertNotNull(test);
    }

    @Test
    @SneakyThrows
    public void atualizarParcelaExecutaAtualizarParcelaEspecifica() {

        usoProprioService.atualizarParcela(destinacoesImoveis);
        doNothing().when(destinacaoService).atualizarParcelaEspecifica(destinacoesImoveis);

    }

    @Test
    @SneakyThrows
    public void desmarcarUltimaDestiancao() throws Exception {
        List<DestinacaoImovel> destinacaoImovels = mocarListaDestinacaoImoveis();

        doAnswer(invocationOnMock -> {
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            Assert.assertEquals(ids, invocationOnMock.getArguments()[0]);
            return true;
        }).when(destinacaoImovelService).desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas(any());

        usoProprioService.desmarcarUltimaDestiancao(destinacaoImovels, Boolean.TRUE);
    }

    @Test
    @SneakyThrows
    public void removerPendenciaDeHomologacao() {
        usoProprio = new UsoProprio();
        usoProprio.setId(1L);
        Set<PendenciaDTO> pendenciaDTOSet = new HashSet<>();
        PendenciaDTO pendenciaDTO = new PendenciaDTO();
        pendenciaDTO.setDataGerada(new Date());
        pendenciaDTOSet.add(pendenciaDTO);
        when(destinacaoPendenciaService.buscarPendenciasPorIdDestinacao(usoProprio.getId())).thenReturn(pendenciaDTOSet);
        when(entityConverter.converterStrict(pendenciaDTO, Pendencia.class)).thenReturn(pendencia);
        Set<DestinacaoPendencia> destinacoesPendencias = new HashSet<>();
        Pendencia pendencia = new Pendencia();
        pendencia.setId(1L);
        destinacoesPendencias.add(destinacaoPendencia);
        usoProprio.setPendencias(destinacoesPendencias);
        usoProprioService.removerPendenciaDeHomologacao(usoProprio);
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


