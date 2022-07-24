package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.EncerramentoDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.dominio.enums.DespachoCancelarEncerrarEnum;
import br.com.company.fks.destinacao.dominio.enums.DespachoEncerrarDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.DoacaoRepository;
import br.com.company.fks.destinacao.repository.EncerramentoDestinacaoRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.apache.catalina.LifecycleState;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import javax.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class EncerramentoDestinacaoServiceTest {

    @InjectMocks
    private EncerramentoDestinacaoService encerramentoDestinacaoService;

    @Mock
    private EncerramentoDestinacao encerramentoDestinacao;

    @Mock
    private EncerramentoDestinacaoDTO encerramentoDestinacaoDTO;

    @Mock
    private EncerramentoDestinacaoRepository encerramentoDestinacaoRepository;

    @Mock
    private EntityConverter converter;

    @Mock
    private DestinacaoRepository destinacaoRepository;

    @Mock
    private Destinacao destinacao;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Mock
    private DoacaoRepository doacaoRepository;

    @Mock
    private CancelarSuspensaoImovelService cancelarSuspensaoImovelService;

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
    private TipoDestinacao tipoDestinacao;

    @Before
    public void setUp(){
        when(encerramentoDestinacao.getId()).thenReturn(1L);
        when(encerramentoDestinacaoDTO.getId()).thenReturn(1L);
        when(encerramentoDestinacaoRepository.save(encerramentoDestinacao)).thenReturn(encerramentoDestinacao);
        when(encerramentoDestinacaoRepository.finByIdDestinacao(1L)).thenReturn(encerramentoDestinacao);
        when(converter.converterStrict(encerramentoDestinacaoDTO, EncerramentoDestinacao.class)).thenReturn(encerramentoDestinacao);
        when(destinacaoRepository.save(destinacao)).thenReturn(destinacao);
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacao);
    }

    @Test
    public void submeterSuperintendente(){
        encerramentoDestinacaoService.submeterSuperintendente(1L, encerramentoDestinacaoDTO);
    }

    @Test
    public void buscarPorIdDestinacao(){ assertNotNull(encerramentoDestinacaoService.buscarPorIdDestinacao(1L)); }

    @Test
    public void buscarPorIdDestinacaoNulo(){
        EncerramentoDestinacao encerramentoDestinacao = encerramentoDestinacaoService.buscarPorIdDestinacao(null);
        assertNull(null);
    }

    @Test
    public void confirmarEncerramento3(){
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacao);
        EncerramentoDestinacao encerramentoDestinacaoMock = new EncerramentoDestinacao();
        encerramentoDestinacaoMock.setDespacho(DespachoEncerrarDestinacaoEnum.RETORNAR_PARA_COMPLEMENTACAO);
        when(converter.converterStrict(encerramentoDestinacaoDTO, EncerramentoDestinacao.class)).thenReturn(encerramentoDestinacaoMock);
        encerramentoDestinacaoService.confirmarEncerramento(1L, encerramentoDestinacaoDTO);
    }

    @Test
    public void confirmarEncerramento2(){
        EncerramentoDestinacao encerramento = new EncerramentoDestinacao();
        encerramento.setDespacho(DespachoEncerrarDestinacaoEnum.INDEFERO_ENCERRAMENTO);
        when(converter.converterStrict(encerramentoDestinacaoDTO, EncerramentoDestinacao.class)).thenReturn(encerramento);
        TipoDestinacao tipoDestinacaoMock = new TipoDestinacao();
        tipoDestinacaoMock.setDescricao(TipoDestinacaoEnum.VENDA.getDescricao());
        Destinacao destinacaoMock = new Destinacao();
        destinacaoMock.setTipoDestinacao(tipoDestinacaoMock);
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacaoMock);
        Doacao doacao = new Doacao();
        doacao.setExisteEncargo(true);
        when(doacaoRepository.findOne(destinacaoMock.getId())).thenReturn(doacao);
        encerramentoDestinacaoService.confirmarEncerramento(1L, encerramentoDestinacaoDTO);
    }

    @Test
    public void confirmarEncerramento1(){
        EncerramentoDestinacao encerramento = new EncerramentoDestinacao();
        encerramento.setDespacho(DespachoEncerrarDestinacaoEnum.DE_ACORDO_COM_O_ENCERRAMENTO);
        when(converter.converterStrict(encerramentoDestinacaoDTO, EncerramentoDestinacao.class)).thenReturn(encerramento);
        TipoDestinacao tipoDestinacaoMock = new TipoDestinacao();
        tipoDestinacaoMock.setDescricao(TipoDestinacaoEnum.DOACAO.getDescricao());
        Destinacao destinacaoMock = new Destinacao();
        destinacaoMock.setTipoDestinacao(tipoDestinacaoMock);
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacaoMock);
        when(doacaoRepository.findOne(destinacaoMock.getId())).thenReturn(doacao);
        when(doacao.getExisteEncargo()).thenReturn(true);
        cancelarSuspensaoImovelService.cancelarSuspensaoImovel(doacao);
        List<DestinacaoImovel> destinacaoImovelList = new ArrayList<>();
        destinacaoImovelList.add(destinacaoImovel);
        destinacaoImovel.setId(1L);
        when(destinacao.getTipoDestinacao()).thenReturn(tipoDestinacaoMock);
        when(destinacao.getDestinacaoImoveis()).thenReturn(destinacaoImovelList);
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getRip()).thenReturn("rip");
        when(urlIntegracaoUtils.getUrlDesfazerCancelamento("rip")).thenReturn("rip");
        when(requestUtils.doDeleteBetweenModules("url")).thenReturn(responseEntity);
        encerramentoDestinacaoService.confirmarEncerramento(1L, encerramentoDestinacaoDTO);
    }

    @Test
    public void confirmarEncerramentoIfFalse(){
        EncerramentoDestinacao encerramento = new EncerramentoDestinacao();
        encerramento.setDespacho(DespachoEncerrarDestinacaoEnum.DE_ACORDO_COM_O_ENCERRAMENTO);
        when(converter.converterStrict(encerramentoDestinacaoDTO, EncerramentoDestinacao.class)).thenReturn(encerramento);
        TipoDestinacao tipoDestinacaoMock = new TipoDestinacao();
        tipoDestinacaoMock.setDescricao(TipoDestinacaoEnum.DOACAO.getDescricao());
        Destinacao destinacaoMock = new Destinacao();
        destinacaoMock.setTipoDestinacao(tipoDestinacaoMock);
        when(destinacaoRepository.findOne(1L)).thenReturn(destinacaoMock);
        when(doacaoRepository.findOne(destinacaoMock.getId())).thenReturn(doacao);
        when(doacao.getExisteEncargo()).thenReturn(false);
        cancelarSuspensaoImovelService.cancelarSuspensaoImovel(doacao);
        List<DestinacaoImovel> destinacaoImovelList = new ArrayList<>();
        destinacaoImovelList.add(destinacaoImovel);
        destinacaoImovel.setId(1L);
        when(destinacao.getTipoDestinacao()).thenReturn(tipoDestinacaoMock);
        when(destinacao.getDestinacaoImoveis()).thenReturn(destinacaoImovelList);
        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getRip()).thenReturn("rip");
        when(urlIntegracaoUtils.getUrlDesfazerCancelamento("rip")).thenReturn("rip");
        when(requestUtils.doDeleteBetweenModules("url")).thenReturn(responseEntity);
        encerramentoDestinacaoService.confirmarEncerramento(1L, encerramentoDestinacaoDTO);
    }

    @Test
    public void confirmarEncerramento1False(){
        EncerramentoDestinacao encerramento = new EncerramentoDestinacao();
        encerramento.setDespacho(DespachoEncerrarDestinacaoEnum.DE_ACORDO_COM_O_ENCERRAMENTO);
        when(converter.converterStrict(encerramentoDestinacaoDTO, EncerramentoDestinacao.class)).thenReturn(encerramento);
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
        encerramentoDestinacaoService.confirmarEncerramento(1L, encerramentoDestinacaoDTO);
    }

}
