package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.CadastroSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.DoacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.MotivacaoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.RestricaoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.SuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.repository.DoacaoRepository;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 23/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DoacaoServiceTest {

    @InjectMocks
    private DoacaoService doacaoService;

    @Mock
    private DoacaoRepository doacaoRepository;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private DoacaoDTO doacaoDTO;

    @Mock
    private Doacao doacao;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private Imovel imovel;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private Object object;

    @Mock
    private RestricaoSuspensaoImovelDTO restricaoSuspensaoImovelDTO;

    @Mock
    private SuspensaoImovelDTO suspensaoImovelDTO;

    @Mock
    private MotivacaoSuspensaoImovelDTO motivacaoSuspensaoImovelDTO;

    @Mock
    private CadastroSuspensaoImovelDTO cadastroSuspensaoImovelDTO;

    @Mock
    private List<String> stringList;

    @Mock
    private List<DestinacaoImovel> destinacaoImovelList;

    @Before
    public void setup() {
        given(doacaoRepository.save(any(Doacao.class))).willReturn(doacao);
        given(entityConverter.converterStrict(any(Doacao.class), eq(DoacaoDTO.class))).willReturn(doacaoDTO);
        given(doacaoRepository.findOne(anyLong())).willReturn(doacao);

    }

    @Test
    public void salvarDadosEspecificos() {
//        List<DestinacaoImovel> destinacaoImovelList = new ArrayList<>();
//        destinacaoImovelList.add(destinacaoImovel);
//        destinacaoImovel.setImovel(imovel);
        when(doacao.getExisteEncargo()).thenReturn(true);
        when(doacao.getDestinacaoImoveis()).thenReturn(asList(destinacaoImovel));
        List<RestricaoSuspensaoImovelDTO> restricaoSuspensaoImovelDTOList = new ArrayList<>();
        restricaoSuspensaoImovelDTOList.add(restricaoSuspensaoImovelDTO);
        restricaoSuspensaoImovelDTO.setId(1L);
        when(urlIntegracaoUtils.getUrlBuscarRestricoes()).thenReturn("buscar");
        when(requestUtils.doGet("url", List.class)).thenReturn(responseEntity);
        when(requestUtils.doGet("buscar", List.class)).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(stringList);
        when(entityConverter.converterStrict(object, RestricaoSuspensaoImovelDTO.class)).thenReturn(restricaoSuspensaoImovelDTO);
        when(suspensaoImovelDTO.getVigencia()).thenReturn(new Date());
        when(suspensaoImovelDTO.getNumeroProcesso()).thenReturn("1");
        when(suspensaoImovelDTO.getDescricao()).thenReturn("teste");
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getRip()).thenReturn("rip");
        when(destinacaoImovel.getCodigoUtilizacao()).thenReturn("1");
        when(suspensaoImovelDTO.getMotivacao()).thenReturn(motivacaoSuspensaoImovelDTO);
        when(suspensaoImovelDTO.getRestricoes()).thenReturn(restricaoSuspensaoImovelDTOList);
        when(cadastroSuspensaoImovelDTO.getSuspensaoImovel()).thenReturn(suspensaoImovelDTO);
        when(cadastroSuspensaoImovelDTO.getRips()).thenReturn(stringList);
        when(urlIntegracaoUtils.getUrlSuspenderImovel()).thenReturn("suspenso");
        when(requestUtils.doPostBetweenModules("url", object)).thenReturn(responseEntity);
        Doacao test = doacaoService.salvarDadosEspecificos(doacao);
        assertNotNull(test);
    }
    @Test
    public void salvarDadosEspecificosFalse() {
        when(doacao.getExisteEncargo()).thenReturn(Boolean.FALSE);
        Doacao test = doacaoService.salvarDadosEspecificos(doacao);
        assertNotNull(test);
    }

    @Test
    public void findOne() throws Exception{
        Resposta<DoacaoDTO> test = doacaoService.findOne(1l);
        assertNotNull(test);
    }


}
