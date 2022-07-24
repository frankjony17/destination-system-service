package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.CancelamentoEncerramentoDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.dominio.enums.DespachoCancelarEncerrarEnum;
import br.com.company.fks.destinacao.dominio.enums.DespachoEncerrarDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.repository.CancelamentoEncerramentoUtilizacaoRepository;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.DoacaoRepository;
import br.com.company.fks.destinacao.service.strategy.ConfirmarCancelamentoRetornarComplementacaoStrategy;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import javax.persistence.Converter;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(PowerMockRunner.class)
public class CancelamentoEncerramentoUtilizacaoServiceTest {

    @InjectMocks
    private CancelamentoEncerramentoUtilizacaoService cancelamentoEncerramentoUtilizacaoService;

    @Mock
    private CancelamentoEncerramentoUtilizacaoRepository cancelamentoEncerramentoUtilizacaoRepository;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private DestinacaoRepository destinacaoRepository;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private CancelamentoEncerramentoDTO cancelamentoEncerramentoDTO;

    @Mock
    private CancelamentoEncerramento cancelamentoEncerramento;

    @Mock
    private Destinacao destinacao;

    @Mock
    private EntityConverter converter;

    @Mock
    private DoacaoRepository doacaoRepository;

    @Mock
    private CancelarSuspensaoImovelService cancelarSuspensaoImovelService;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private Imovel imovel;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Before
    public void setUp(){
        when(cancelamentoEncerramento.getId()).thenReturn(1L);
        when(cancelamentoEncerramentoDTO.getId()).thenReturn(1L);
        when(cancelamentoEncerramentoUtilizacaoRepository.save(cancelamentoEncerramento)).thenReturn(cancelamentoEncerramento);
        when(cancelamentoEncerramentoUtilizacaoRepository.finByIdDestinacao(1L)).thenReturn(cancelamentoEncerramento);
        when(converter.converterStrict(cancelamentoEncerramentoDTO, CancelamentoEncerramento.class)).thenReturn(cancelamentoEncerramento);
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacao);
        when(destinacaoRepository.save(destinacao)).thenReturn(destinacao);
    }

    @Test
    public void submeterSuperintendente(){
        cancelamentoEncerramentoUtilizacaoService.submeterSuperintendente(1L, cancelamentoEncerramentoDTO);
    }


    @Test
    public void buscarPorIdDestinacao(){
        assertNotNull(cancelamentoEncerramentoUtilizacaoService.buscarPorIdDestinacao(1L));
    }

    @Test
    public void buscarPorIdDestinacaoNulo(){
        CancelamentoEncerramento cancelamentoEncerramento = cancelamentoEncerramentoUtilizacaoService.buscarPorIdDestinacao(null);
        assertNull(null);
    }

    @Test
    public void confirmarCancelamento2(){
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacao);
        CancelamentoEncerramento cancelamento = new CancelamentoEncerramento();
        cancelamento.setDespacho(DespachoCancelarEncerrarEnum.INDEFERO_CANCELAMENTO_ENCERRAMENTO);
        when(converter.converterStrict(cancelamentoEncerramentoDTO, CancelamentoEncerramento.class)).thenReturn(cancelamento);
        cancelamentoEncerramentoUtilizacaoService.confirmarCancelamento(1L, cancelamentoEncerramentoDTO);
    }

    @Test
    public void confirmarCancelamento1(){

        CancelamentoEncerramento cancelamento = new CancelamentoEncerramento();
        cancelamento.setDespacho(DespachoCancelarEncerrarEnum.DE_ACORDO_COM_CANCELAMENTO_ENCERRAMENTO);
        when(converter.converterStrict(cancelamentoEncerramentoDTO, CancelamentoEncerramento.class)).thenReturn(cancelamento);
        TipoDestinacao tipoDestinacaoMock = new TipoDestinacao();
        tipoDestinacaoMock.setDescricao(TipoDestinacaoEnum.DOACAO.getDescricao());
        Destinacao destinacaoMock = new Destinacao();
        destinacaoMock.setTipoDestinacao(tipoDestinacaoMock);
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacaoMock);
        Doacao doacao = new Doacao();
        doacao.setExisteEncargo(true);
        cancelarSuspensaoImovelService.cancelarSuspensaoImovel(doacao);
        when(doacaoRepository.findOne(destinacaoMock.getId())).thenReturn(doacao);
        cancelamentoEncerramentoUtilizacaoService.confirmarCancelamento(1L, cancelamentoEncerramentoDTO);
    }

    @Test
    public void confirmarCancelamento1False(){

        CancelamentoEncerramento cancelamento = new CancelamentoEncerramento();
        cancelamento.setDespacho(DespachoCancelarEncerrarEnum.DE_ACORDO_COM_CANCELAMENTO_ENCERRAMENTO);
        when(converter.converterStrict(cancelamentoEncerramentoDTO, CancelamentoEncerramento.class)).thenReturn(cancelamento);
        TipoDestinacao tipoDestinacaoMock = new TipoDestinacao();
        tipoDestinacaoMock.setDescricao(TipoDestinacaoEnum.VENDA.getDescricao());
        Destinacao destinacaoMock = new Destinacao();
        destinacaoMock.setTipoDestinacao(tipoDestinacaoMock);
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacaoMock);
        Doacao doacao = new Doacao();
        doacao.setExisteEncargo(false);
        DestinacaoImovel destinacaoImovelMock = new DestinacaoImovel();
        destinacaoImovelMock.setImovel(imovel);
        imovel.setRip("rip");
        cancelarSuspensaoImovelService.cancelarSuspensaoImovel(doacao);
        when(doacaoRepository.findOne(destinacaoMock.getId())).thenReturn(doacao);
        List<DestinacaoImovel> destinacaoImovelList = new ArrayList<>();
        destinacaoImovelList.add(destinacaoImovelMock);
        destinacaoImovelMock.setId(1L);
        destinacaoMock.setDestinacaoImoveis(destinacaoImovelList);
        when(urlIntegracaoUtils.getUrlDesfazerCancelamento(anyString())).thenReturn("rip");
        when(requestUtils.doDeleteBetweenModules(anyString())).thenReturn(responseEntity);
        cancelamentoEncerramentoUtilizacaoService.confirmarCancelamento(1L, cancelamentoEncerramentoDTO);
    }

    @Test
    public void confirmarCancelamento3(){

        CancelamentoEncerramento cancelamento = new CancelamentoEncerramento();
        cancelamento.setDespacho(DespachoCancelarEncerrarEnum.RETORNAR_PARA_COMPLEMENTACAO);
        when(converter.converterStrict(cancelamentoEncerramentoDTO, CancelamentoEncerramento.class)).thenReturn(cancelamento);
        TipoDestinacao tipoDestinacaoMock = new TipoDestinacao();
        tipoDestinacaoMock.setDescricao(TipoDestinacaoEnum.DOACAO.getDescricao());
        Destinacao destinacaoMock = new Destinacao();
        destinacaoMock.setTipoDestinacao(tipoDestinacaoMock);
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacaoMock);
        Doacao doacao = new Doacao();
        doacao.setExisteEncargo(false);
        when(doacaoRepository.findOne(destinacaoMock.getId())).thenReturn(doacao);
        cancelamentoEncerramentoUtilizacaoService.confirmarCancelamento(1L, cancelamentoEncerramentoDTO);
    }

}
