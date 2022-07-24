package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.dominio.entidades.UnidadeAutonoma;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ImovelRepository;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import br.com.company.fks.destinacao.service.validadores.ValidadorRipStrategyFactory;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipGenerico;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by basis on 23/05/17.
 */

@IntegrationTest("server.port:0")
@RunWith(MockitoJUnitRunner.class)
public class ImovelServiceIT{

    @InjectMocks
    private ImovelService imovelService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private ImovelRepository imovelRepository;

    @Mock
    private BenfeitoriaService benfeitoriaService;

    @Mock
    private List<Benfeitoria> benfeitorias;

    @Mock
    private Imovel imovel;

    @Mock
    private List<Parcela> parcelas;

    @Mock
    private ValidadorRip validadorRip;

    @Mock
    private ValidadorRipStrategyFactory validadorRipStrategyFactory;

    @Mock
    private ValidadorRipGenerico validadorRipGenerico;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private Resposta<ImovelDTO> resposta;

    @Mock
    private Endereco endereco;

    @Mock
    private EnderecoService enderecoSerice;

    @Before
    public void setup(){
        when(urlIntegracaoUtils.getUrlImovelCadastroImoveis(anyString())).thenReturn("");
        when(requestUtils.doGet(anyString(),eq(Resposta.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(resposta);
        when(imovel.getParcelas()).thenReturn(parcelas);
        when(benfeitoriaService.extrairBenfeitorias(anyList())).thenReturn(benfeitorias);
        when(validadorRipStrategyFactory.createBean(any(TipoDestinacaoEnum.class))).thenReturn(validadorRipGenerico);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void consultarImovelRIP() {
        List<Imovel> imoveis = new ArrayList<>();
        imoveis.add(imovel);
        when(imovelRepository.buscarPorRipParcelaAtiva(anyString())).thenReturn(imoveis);
        when(entityConverter.converterStrict(any(Imovel.class),eq(ImovelDTO.class))).thenReturn(null);
        doNothing().when(validadorRip).validar(any(Imovel.class), anyLong(), any(FundamentoLegalDTO.class));
        ImovelDTO retorno = imovelService.consultarImovelRip("","VENDA");
        assertNull(retorno);
    }

    @Test
    @SneakyThrows
    public void getImovelCadastroImoveis(){
        when(resposta.getResultado()).thenReturn(null);
        Method metodo = ImovelService.class.getDeclaredMethod("getImovelCadastroImoveis", String.class);
        metodo.setAccessible(true);
        metodo.invoke(imovelService, "");
    }

    @Ignore
    @Test
    @SneakyThrows
    public void getImovelRipValido(){
        Method metodo = ImovelService.class.getDeclaredMethod("getImovelRipValido", String.class, String.class, Long.class);
        metodo.setAccessible(true);
        metodo.invoke(imovelService, "");
    }

    @Test
    @SneakyThrows
    public void setarDadosParcela(){
        List<Parcela> parcelas = new ArrayList<>();
        Parcela parcela = new Parcela();
        parcela.setSequencial("P0");
        parcelas.add(parcela);

        Method metodo = ImovelService.class.getDeclaredMethod("setarDadosParcela", ImovelDTO.class, List.class, String.class);
        metodo.setAccessible(true);
        metodo.invoke(imovelService, null, parcelas, "P1");
    }

    @Test
    public void buscarPorRip(){
        List<Imovel> imoveis = new ArrayList<>();
        imoveis.add(imovel);
        when(imovelRepository.buscarPorRip(anyString())).thenReturn(imoveis);
        Imovel retorno = imovelService.buscarPorRip("");
        assertNotNull(retorno);
    }

    @Test
    public void buscarPorRipVazio(){
        when(imovelRepository.buscarPorRip(anyString())).thenReturn(java.util.Collections.emptyList());
        Imovel retorno = imovelService.buscarPorRip("");
        assertNull(retorno);
    }

    @Test
    public void isImovelDestinadoVV(){
        when(imovelRepository.quantidadesDestinacaoImovel(anyString())).thenReturn(Constants.UM);
        Boolean retorno = imovelService.isImovelDestinado("");
        assertTrue(retorno);
    }

    @Test
    public void isImovelDestinadoVF(){
        when(imovelRepository.quantidadesDestinacaoImovel(anyString())).thenReturn(Constants.ZERO);
        Boolean retorno = imovelService.isImovelDestinado("");
        assertFalse(retorno);
    }

    @Test
    public void isImovelDestinadoFF(){
        when(imovelRepository.quantidadesDestinacaoImovel(anyString())).thenReturn(null);
        Boolean retorno = imovelService.isImovelDestinado("");
        assertFalse(retorno);
    }

    @Test
    public void isImovelDestinacaoAtivaVV(){
        when(imovelRepository.quantidadesDestinacaoAtivaImovel(anyString())).thenReturn(Constants.UM);
        Boolean retorno = imovelService.isImovelDestinacaoAtiva("");
        assertTrue(retorno);
    }

    @Test
    public void isImovelDestinacaoAtivaVF(){
        when(imovelRepository.quantidadesDestinacaoAtivaImovel(anyString())).thenReturn(Constants.ZERO);
        Boolean retorno = imovelService.isImovelDestinacaoAtiva("");
        assertFalse(retorno);
    }

    @Test
    public void isImovelDestinacaoAtivaFF(){
        when(imovelRepository.quantidadesDestinacaoAtivaImovel(anyString())).thenReturn(null);
        Boolean retorno = imovelService.isImovelDestinacaoAtiva("");
        assertFalse(retorno);
    }

    @Test
    public void findByIdCadastroImovel(){
        when(imovelRepository.findByIdCadastroImovel(anyLong())).thenReturn(imovel);
        Imovel retorno = imovelService.findByIdCadastroImovel(1L);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void consultarDadosPosseInformalDestinacaoImovelNula(){

    }

    @Ignore
    @Test
    @SneakyThrows
    public void atualizar(){
        Imovel imovelSalvo = new Imovel();
        imovelSalvo.setAreaTerreno(BigDecimal.valueOf(100));
        imovelSalvo.setEndereco(endereco);
        Imovel imovel = new Imovel();
        imovel.setAreaTerreno(BigDecimal.valueOf(100));
        imovel.setCodigoNaturezaImovel(1L);
        imovel.setCodigoSituacaoIncorporacao(1L);
        imovel.setCodigoTipoImovel(1L);
        imovel.setCoditoTipoProprietario(1L);
        imovel.setProprietario("");
        imovel.setEndereco(endereco);
        when(imovelRepository.findByRip(anyString())).thenReturn(imovelSalvo);
        when(enderecoSerice.atualizar(any(Endereco.class), any(Endereco.class))).thenReturn(endereco);
        when(imovelRepository.save(any(Imovel.class))).thenReturn(imovelSalvo);

        Imovel retorno = imovelService.atualizar(imovel, null);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void atualizarCriarNovaUnidadeAutonoma(){
        Imovel imovelSalvo = new Imovel();
        Imovel imovel = new Imovel();
        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma();
        imovelSalvo.setUnidadeAutonoma(null);
        unidadeAutonoma.setArea(BigDecimal.valueOf(100));
        unidadeAutonoma.setIdUnidadeAutonomaCadImovel(1L);
        imovel.setUnidadeAutonoma(unidadeAutonoma);

        Method metodo = ImovelService.class.getDeclaredMethod("atualizarCriarNovaUnidadeAutonoma", Imovel.class, Imovel.class, Map.class);
        metodo.setAccessible(true);
        metodo.invoke(imovelService, imovelSalvo, imovel, null);
        assertNotNull(imovelSalvo.getUnidadeAutonoma());
    }

    @Test
    @SneakyThrows
    public void atualizarCriarNovaUnidadeAutonomaUnidadeAutonomaDiferentes(){
        Imovel imovelSalvo = new Imovel();
        Imovel imovel = new Imovel();
        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma();
        unidadeAutonoma.setArea(BigDecimal.valueOf(100));
        unidadeAutonoma.setIdUnidadeAutonomaCadImovel(1L);
        imovelSalvo.setUnidadeAutonoma(unidadeAutonoma);
        unidadeAutonoma.setIdUnidadeAutonomaCadImovel(2L);
        unidadeAutonoma.setArea(BigDecimal.valueOf(99));
        imovel.setUnidadeAutonoma(unidadeAutonoma);

        Method metodo = ImovelService.class.getDeclaredMethod("atualizarCriarNovaUnidadeAutonoma", Imovel.class, Imovel.class, Map.class);
        metodo.setAccessible(true);
        metodo.invoke(imovelService, imovelSalvo, imovel, null);
        assertNotNull(imovelSalvo.getUnidadeAutonoma());
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void extrairImovelParcela(){
        imovelService.extrairImovelParcela(Collections.emptyList());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void getImovelRipValidoFV(){
        List<Imovel> imoveis = new ArrayList<>();
        imoveis.add(imovel);
        when(imovelRepository.buscarPorRipParcelaAtiva(anyString())).thenReturn(imoveis);
        doNothing().when(validadorRip).validar(any(Imovel.class), anyLong(), any(FundamentoLegalDTO.class));
        when(imovelRepository.quantidadesDestinacaoAtivaImovel(anyString())).thenReturn(Constants.UM);
        when(imovelRepository.quantidadesDestinacaoAtivaImovelCessaoEntrega(anyString())).thenReturn(Constants.UM);

        Method metodo = ImovelService.class.getDeclaredMethod("getImovelRipValido", String.class);
        metodo.setAccessible(true);
        Imovel retorno = (Imovel) metodo.invoke(imovelService,"");
        assertNotNull(retorno);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void getImovelRipValidoVV(){
        List<Imovel> imoveis = new ArrayList<>();
        imoveis.add(imovel);
        when(imovelRepository.buscarPorRipParcelaAtiva(anyString())).thenReturn(imoveis);
        doNothing().when(validadorRip).validar(any(Imovel.class), anyLong(), any(FundamentoLegalDTO.class));
        when(imovelRepository.quantidadesDestinacaoAtivaImovel(anyString())).thenReturn(Constants.ZERO);
        when(imovelRepository.quantidadesDestinacaoAtivaImovelCessaoEntrega(anyString())).thenReturn(Constants.UM);

        Method metodo = ImovelService.class.getDeclaredMethod("getImovelRipValido", String.class);
        metodo.setAccessible(true);
        Imovel retorno = (Imovel) metodo.invoke(imovelService,"");
        assertNotNull(retorno);
    }

}
