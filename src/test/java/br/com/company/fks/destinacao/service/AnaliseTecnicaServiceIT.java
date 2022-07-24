package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaRepository;
import br.com.company.fks.destinacao.service.analiseTecnica.StatusAnaliseDespachoSuperintendente;
import br.com.company.fks.destinacao.service.analiseTecnica.StatusAnaliseTecnicaStrategy;
import br.com.company.fks.destinacao.utils.EnviadorEmail;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;

import javax.mail.MessagingException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by diego on 02/01/17.
 */

@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
@PrepareForTest({StatusAnaliseTecnicaStrategy.class})
public class AnaliseTecnicaServiceIT{

    @InjectMocks
    private AnaliseTecnicaService analiseTecnicaService;

    @Mock
    private RequerimentoService requerimentoService;

    @Mock
    private EnviadorEmail enviadorEmail;

    @Mock
    private StatusAnaliseTecnica statusAnaliseTecnica;

    @Mock
    private DominioService dominioService;

    @Mock
    private StatusAnaliseTecnicaStrategy statusAnaliseTecnicaStrategy;

    @Mock
    private StatusAnaliseDespachoSuperintendente statusAnaliseDespachoSuperintendente;

    @Mock
    private AnaliseTecnicaRepository analiseTecnicaRepository;

    @Mock
    private AnaliseTecnica analiseTecnica;

    private MockServerUtils mockServerUtils;


    @Before
    @SneakyThrows
    public void setUp() {
        mockStatic(StatusAnaliseTecnicaStrategy.class);
    }

    @Test
    @SneakyThrows
    public void setarEnvioPublicacaoStatusVF(){
        AnaliseTecnica analiseTecnica = new AnaliseTecnica();
        StatusAnaliseTecnica statusAnaliseTecnica = new StatusAnaliseTecnica();
        statusAnaliseTecnica.setId(6);
        analiseTecnica.setStatusAnaliseTecnica(statusAnaliseTecnica);
        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("setarEnvioPublicacao",AnaliseTecnica.class);
        metodo.setAccessible(true);
        metodo.invoke(analiseTecnicaService, analiseTecnica);
    }

    @Test
    @SneakyThrows
    public void setarEnvioPublicacaoStatusFF(){
        AnaliseTecnica analiseTecnica = new AnaliseTecnica();
        analiseTecnica.setStatusAnaliseTecnica(null);
        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("setarEnvioPublicacao",AnaliseTecnica.class);
        metodo.setAccessible(true);
        metodo.invoke(analiseTecnicaService, analiseTecnica);
    }

    @Test
    @SneakyThrows
    public void setarEnvioPublicacaoStatusVV(){
        AnaliseTecnica analiseTecnica = new AnaliseTecnica();
        StatusAnaliseTecnica statusAnaliseTecnica = new StatusAnaliseTecnica();
        statusAnaliseTecnica.setId(7);
        analiseTecnica.setStatusAnaliseTecnica(statusAnaliseTecnica);
        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("setarEnvioPublicacao",AnaliseTecnica.class);
        metodo.setAccessible(true);
        metodo.invoke(analiseTecnicaService, analiseTecnica);
        assertNotNull(analiseTecnica.getDataEnvioPublicacao());
    }

    @Test
    @SneakyThrows
    public void alterarStatusAnaliseModuloServico(){
        doNothing().when(requerimentoService).alterarStatusRequerimento(anyLong(), any(StatusAnaliseTecnicaEnum.class));

        AnaliseTecnica analiseTecnica = new AnaliseTecnica();
        StatusAnaliseTecnica statusAnaliseTecnica = new StatusAnaliseTecnica();
        statusAnaliseTecnica.setId(9);
        analiseTecnica.setStatusAnaliseTecnica(statusAnaliseTecnica);
        analiseTecnica.setIdRequerimento(1L);
        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("alterarStatusAnaliseModuloServico",AnaliseTecnica.class);
        metodo.setAccessible(true);
        metodo.invoke(analiseTecnicaService, analiseTecnica);
    }

    @Test
    @SneakyThrows
    public void enviaremailIntegrationException(){
        when(requerimentoService.getDadosAtendimento(anyLong())).thenThrow(IntegracaoException.class);
        StatusAnaliseTecnica statusAnaliseTecnica = new StatusAnaliseTecnica();
        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("enviarEmail",StatusAnaliseTecnica.class, Long.class);
        metodo.setAccessible(true);
        metodo.invoke(analiseTecnicaService,statusAnaliseTecnica, 1L);
    }

    @Test
    @SneakyThrows
    public void enviaremailMessaginException(){
        when(requerimentoService.getDadosAtendimento(anyLong())).thenThrow(MessagingException.class);
        StatusAnaliseTecnica statusAnaliseTecnica = new StatusAnaliseTecnica();
        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("enviarEmail",StatusAnaliseTecnica.class, Long.class);
        metodo.setAccessible(true);
        metodo.invoke(analiseTecnicaService,statusAnaliseTecnica, 1L);
    }

    @Test
    @SneakyThrows
    public void definirStatusPorPermissaoVFAnaliseChefia(){

        when(dominioService.findById(anyInt(),eq(StatusAnaliseTecnica.class))).thenReturn(statusAnaliseTecnica);
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        DominioDTO statusAnaliseTecnica = new DominioDTO();
        statusAnaliseTecnica.setId(3);
        analiseTecnicaDTO.setStatusAnaliseTecnica(statusAnaliseTecnica);
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_CHEFIA");
        usuarioLogadoDTO.setPermissoes(permissoes);

        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("definirStatusPorPermissao",UsuarioLogadoDTO.class, AnaliseTecnicaDTO.class);
        metodo.setAccessible(true);
        StatusAnaliseTecnica retorno =(StatusAnaliseTecnica)metodo.invoke(analiseTecnicaService, usuarioLogadoDTO,analiseTecnicaDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void definirStatusPorPermissaoVFAnaliseSuperintendente(){

        when(dominioService.findById(anyInt(),eq(StatusAnaliseTecnica.class))).thenReturn(statusAnaliseTecnica);
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        DominioDTO statusAnaliseTecnica = new DominioDTO();
        statusAnaliseTecnica.setId(3);
        analiseTecnicaDTO.setStatusAnaliseTecnica(statusAnaliseTecnica);
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE");
        usuarioLogadoDTO.setPermissoes(permissoes);

        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("definirStatusPorPermissao",UsuarioLogadoDTO.class, AnaliseTecnicaDTO.class);
        metodo.setAccessible(true);
        StatusAnaliseTecnica retorno =(StatusAnaliseTecnica)metodo.invoke(analiseTecnicaService, usuarioLogadoDTO,analiseTecnicaDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void definirStatusPorPermissaoFVAnaliseSuperintendente(){

        when(dominioService.findById(anyInt(),eq(StatusAnaliseTecnica.class))).thenReturn(statusAnaliseTecnica);
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        DominioDTO statusAnaliseTecnica = new DominioDTO();
        statusAnaliseTecnica.setId(2);
        analiseTecnicaDTO.setStatusAnaliseTecnica(statusAnaliseTecnica);
        analiseTecnicaDTO.setId(1L);
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE");
        usuarioLogadoDTO.setPermissoes(permissoes);

        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("definirStatusPorPermissao",UsuarioLogadoDTO.class, AnaliseTecnicaDTO.class);
        metodo.setAccessible(true);
        StatusAnaliseTecnica retorno =(StatusAnaliseTecnica)metodo.invoke(analiseTecnicaService, usuarioLogadoDTO,analiseTecnicaDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void definirStatusPorPermissaoVVAnaliseSuperintendente(){

        when(dominioService.findById(anyInt(),eq(StatusAnaliseTecnica.class))).thenReturn(statusAnaliseTecnica);
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        DominioDTO statusAnaliseTecnica = new DominioDTO();
        statusAnaliseTecnica.setId(2);
        analiseTecnicaDTO.setStatusAnaliseTecnica(statusAnaliseTecnica);
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE");
        usuarioLogadoDTO.setPermissoes(permissoes);

        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("definirStatusPorPermissao",UsuarioLogadoDTO.class, AnaliseTecnicaDTO.class);
        metodo.setAccessible(true);
        StatusAnaliseTecnica retorno =(StatusAnaliseTecnica)metodo.invoke(analiseTecnicaService, usuarioLogadoDTO,analiseTecnicaDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void definirStatusPorPermissaoFFAnaliseSuperintendente(){
        when(statusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(any(PermissaoAnaliseEnum.class))).thenReturn(statusAnaliseDespachoSuperintendente);
        when(statusAnaliseDespachoSuperintendente.getCodigoStatusPorDespacho(any(AnaliseTecnicaDTO.class))).thenReturn(statusAnaliseTecnica);
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        DominioDTO statusAnaliseTecnica = new DominioDTO();
        statusAnaliseTecnica.setId(3);
        analiseTecnicaDTO.setStatusAnaliseTecnica(statusAnaliseTecnica);
        analiseTecnicaDTO.setId(1L);
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE");
        usuarioLogadoDTO.setPermissoes(permissoes);

        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("definirStatusPorPermissao",UsuarioLogadoDTO.class, AnaliseTecnicaDTO.class);
        metodo.setAccessible(true);
        StatusAnaliseTecnica retorno =(StatusAnaliseTecnica)metodo.invoke(analiseTecnicaService, usuarioLogadoDTO,analiseTecnicaDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void definirStatusPorPermissaoFFAnaliseSuperintendenteStatusDespachoNull(){
        when(statusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(any(PermissaoAnaliseEnum.class))).thenReturn(null);
        when(dominioService.findById(anyInt(),eq(StatusAnaliseTecnica.class))).thenReturn(statusAnaliseTecnica);
        AnaliseTecnicaDTO analiseTecnicaDTO = new AnaliseTecnicaDTO();
        DominioDTO statusAnaliseTecnica = new DominioDTO();
        statusAnaliseTecnica.setId(3);
        analiseTecnicaDTO.setStatusAnaliseTecnica(statusAnaliseTecnica);
        analiseTecnicaDTO.setId(1L);
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE");
        usuarioLogadoDTO.setPermissoes(permissoes);

        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("definirStatusPorPermissao",UsuarioLogadoDTO.class, AnaliseTecnicaDTO.class);
        metodo.setAccessible(true);
        StatusAnaliseTecnica retorno =(StatusAnaliseTecnica)metodo.invoke(analiseTecnicaService, usuarioLogadoDTO,analiseTecnicaDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void getStatusAnaliseTecnicaAtual(){
        when(analiseTecnicaRepository.findOne(anyLong())).thenReturn(analiseTecnica);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnica);

        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("getStatusAnaliseTecnicaAtual",Long.class);
        metodo.setAccessible(true);
        StatusAnaliseTecnica retorno =(StatusAnaliseTecnica) metodo.invoke(analiseTecnicaService,1L);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void getStatusAnaliseTecnicaAtualNull(){
        when(analiseTecnicaRepository.findOne(anyLong())).thenReturn(null);
        when(analiseTecnica.getStatusAnaliseTecnica()).thenReturn(statusAnaliseTecnica);

        Method metodo = AnaliseTecnicaService.class.getDeclaredMethod("getStatusAnaliseTecnicaAtual",Long.class);
        metodo.setAccessible(true);
        StatusAnaliseTecnica retorno =(StatusAnaliseTecnica) metodo.invoke(analiseTecnicaService,1L);
        assertNull(retorno);
    }
}
