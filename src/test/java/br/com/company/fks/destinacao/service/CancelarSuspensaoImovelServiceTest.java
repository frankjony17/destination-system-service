package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.CancelamentoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.MotivoCancelamentoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.RipSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.SuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ResponseEntity.class)
public class CancelarSuspensaoImovelServiceTest {

    @InjectMocks
    private CancelarSuspensaoImovelService cancelarSuspensaoImovelService;

    @Mock
    private CancelamentoSuspensaoImovelDTO cancelamentoSuspensaoImovelDTO;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private Doacao doacao;

    @Mock
    private MotivoCancelamentoSuspensaoImovelDTO motivoCancelamentoSuspensaoImovelDTO;

    @Mock
    private Date date;

    @Mock
    private TipoDestinacao tipoDestinacaoMock;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private Imovel imovel;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private Object object;

    @Mock
    private SuspensaoImovelDTO suspensaoImovelDTO;

    @Mock
    private List<RipSuspensaoImovelDTO> ripSuspensaoImovelDTOList;

    @Test
    public void cancelarSuspensaoImovel(){
            List<DestinacaoImovel> destinacaoImovelList = new ArrayList<>();
            destinacaoImovelList.add(destinacaoImovel);
            when(doacao.getDestinacaoImoveis()).thenReturn(destinacaoImovelList);
            when(doacao.getNumeroProcesso()).thenReturn("0");
            when(destinacaoImovel.getImovel()).thenReturn(imovel);
            when(imovel.getRip()).thenReturn("teste");
            when(destinacaoImovel.getCodigoUtilizacao()).thenReturn("1");
            when(urlIntegracaoUtils.getUrlImovelSuspensoMotivo(anyString(),anyLong())).thenReturn("teste");
            when(requestUtils.doGet("teste",Object.class)).thenReturn(responseEntity);
            when(responseEntity.getBody()).thenReturn(object);
            when(entityConverter.converterStrict(object, SuspensaoImovelDTO.class)).thenReturn(suspensaoImovelDTO);
            when(destinacaoImovel.getImovel()).thenReturn(imovel);
            when(imovel.getRip()).thenReturn("rip");
            when(suspensaoImovelDTO.getRips()).thenReturn(ripSuspensaoImovelDTOList);
            when(suspensaoImovelDTO.getId()).thenReturn(2L);
            when(urlIntegracaoUtils.getUrlCancelarSuspensaoImovel()).thenReturn("test");
            when(requestUtils.doPostBetweenModules(anyString(),any(Object.class))).thenReturn(responseEntity);
            cancelarSuspensaoImovelService.cancelarSuspensaoImovel(doacao);
    }
}
