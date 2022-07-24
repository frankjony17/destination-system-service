package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.CancelamentoImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class CancelarImovelServiceTest {

    @InjectMocks
    private CancelarImovelService cancelarImovelService;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private CancelamentoImovelDTO cancelamentoImovelDTO;

    @Mock
    private Destinacao destinacaoMock;

    @Mock
    private TipoDestinacao tipoDestinacaoMock;

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTOMock;

    @Test
    public void cancelarImovel(){
        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTOMock);
        when(usuarioService.getUsuarioLogado().getNome()).thenReturn("teste");
        when(usuarioService.getUsuarioLogado().getCpf()).thenReturn("cpf");
        Destinacao destinacao = new Destinacao();
        destinacao.setNumeroProcesso("1");
        tipoDestinacaoMock.setDescricao("tsafadasfa");
        destinacao.setTipoDestinacao(tipoDestinacaoMock);
        Imovel imovel = new Imovel();
        imovel.setRip("teste1");
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        destinacaoImovel.setCodigoUtilizacao("teste");
        List<DestinacaoImovel> destinacaoImovelList = new ArrayList<>();
        destinacaoImovelList.add(destinacaoImovel);
        destinacao.setDestinacaoImoveis(destinacaoImovelList);
        Endereco endereco = new Endereco();
        endereco.setUf("1");
        imovel.setEndereco(endereco);
        when(urlIntegracaoUtils.getUrlCancelarImovel()).thenReturn("url");

        cancelarImovelService.cancelarImovel(destinacao);
    }
}
