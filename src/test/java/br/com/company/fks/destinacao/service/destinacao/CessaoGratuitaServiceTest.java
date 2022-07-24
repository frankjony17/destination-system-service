package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.dto.CessaoGratuitaDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.ImagemDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.CessaoGratuitaRepository;
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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.annotation.ElementType.METHOD;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 23/01/17.
 */
@RunWith(PowerMockRunner.class)
public class CessaoGratuitaServiceTest {

    @InjectMocks
    private CessaoGratuitaService cessaoGratuitaService;

    @Mock
    private CessaoGratuitaRepository cessaoGratuitaRepository;

    @Mock
    private ContratoService contratoService;

    @Mock
    private Contrato contrato;

    @Mock
    private CessaoGratuita cessaoGratuita;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private DestinacaoImovel novaDestinacaoImovel;

    @Mock
    private ParcelaService parcelaService;

    @Mock
    private BenfeitoriaService benfeitoriaService;

    @Mock
    private List<Parcela> parcelas;

    @Mock
    private DestinacaoImovelService destinacaoImovelService;

    @Mock
    private Imovel imovel;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private CessaoGratuitaDTO cessaoGratuitaDTO;

    @Mock
    private ImovelService imovelService;

    @Mock
    private ImovelDTO imovelDTO;

    @Mock
    private ImagemDTO imagemDTO;

    @Mock
    private DestinacaoImovelDTO destinacaoImovelDTO;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private ArquivoDTO arquivoDTO;

    @Mock
    private ResponsavelService responsavelService;

    @Mock
    private Responsavel responsavel;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private Resposta<CessaoGratuitaDTO> resposta;

    @Before
    public void setUp() {
        when(cessaoGratuita.getContrato()).thenReturn(contrato);
        when(cessaoGratuita.getId()).thenReturn(1L);
        when(cessaoGratuitaRepository.save(any(CessaoGratuita.class))).thenReturn(cessaoGratuita);
        given(cessaoGratuitaRepository.save(any(CessaoGratuita.class))).willReturn(cessaoGratuita);
    }

    @Test
   @SneakyThrows
   public void salvarDAdosEspecificos(){
        CessaoGratuita test = cessaoGratuitaService.salvarDadosEspecificos(cessaoGratuita);
        assertNotNull(test);
   }

    @Test
    @SneakyThrows
    public void atualizarParcela(){
        List<DestinacaoImovel> destinacaoImovels = new ArrayList<>();
        destinacaoImovels.add(destinacaoImovel);
        when(parcelaService.atualizar(anyList(), any(DestinacaoImovel.class))).thenReturn(parcelas);
        cessaoGratuitaService.atualizarParcela(destinacaoImovels);
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

        cessaoGratuitaService.desmarcarUltimaDestiancao(destinacaoImovels, Boolean.TRUE);
    }

    @Test
    @SneakyThrows
    public void findOne(){
        when(cessaoGratuitaRepository.findOne(1L)).thenReturn(cessaoGratuita);
        when(entityConverter.converterStrict(cessaoGratuita, CessaoGratuitaDTO.class)).thenReturn(cessaoGratuitaDTO);
        resposta = cessaoGratuitaService.findOne(1L);
        assertNotNull(resposta);
    }

    private void mocarImovelDTO() {
        when(imovelDTO.getRip()).thenReturn("123");
        when(imovelDTO.getLatitude()).thenReturn(Double.valueOf(1));
        when(imovelDTO.getLongitude()).thenReturn(Double.valueOf(1));
        when(imagemDTO.getImagem()).thenReturn("String imabem");
        when(imovelDTO.getImagens()).thenReturn(Arrays.asList(imagemDTO));
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