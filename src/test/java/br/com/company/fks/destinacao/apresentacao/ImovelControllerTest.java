package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ConsultaImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.ImovelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Created by guilherme on 02/03/17.
 */

@RunWith(PowerMockRunner.class)
public class ImovelControllerTest {

    @InjectMocks
    private ImovelController imovelController;

    @Mock
    private ImovelService imovelService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private Imovel imovel;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private Page<ImovelDTO> imovelDestinado;

    @Mock
    private ImovelDTO imovelDTO;

    @Mock
    private Resposta<ImovelDTO> resposta;

    @Mock
    private ConsultaImovelDTO consultaImovelDTO;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void buscarValorAvaliacao() throws Exception {
        ResponseEntity<Resposta<ImovelDTO>> resposta =
                imovelController.buscarValorAvaliacao(anyLong());

        assertNotNull(resposta);
    }

    @Test
    public void verificarImovelDestinado() throws Exception {
        when(imovelService.isImovelDestinado(anyString())).thenReturn(true);
        ResponseEntity<Resposta<Boolean>> resposta = imovelController.verificarImovelDestinado("1");
        assertNotNull(resposta);
    }

    @Test
    public void consultarDestinacao (){
        when(imovelService.consultarDestinacao(anyString(),anyString(),anyString(),anyString(),anyInt(),anyInt())).thenReturn(imovelDestinado);
        ResponseEntity<Resposta<Page<ImovelDTO>>> resposta = imovelController.consultarDestinacao("","","MG","",1,1);
        assertNotNull(resposta);
        assertTrue(resposta.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void buscarDadosRipUtilizacao(){
        when(imovelService.buscarDadosRipUtilizacao(anyString())).thenReturn(imovel);
        when(entityConverter.converter(imovel, ImovelDTO.class)).thenReturn(imovelDTO);
        ResponseEntity<Resposta<ImovelDTO>> resposta = imovelController.buscarDadosRipUtilizacao("00000007");
        assertNotNull(resposta);
        assertTrue(resposta.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void buscarDadosBenfeitorias(){
        when(imovelService.buscarDadosBenfeitorias(anyString())).thenReturn(imovel);
        when(entityConverter.converter(imovel, ImovelDTO.class)).thenReturn(imovelDTO);
        ResponseEntity<Resposta<ImovelDTO>> resposta = imovelController.buscarDadosBenfeitorias("rip");
        assertNotNull(resposta);
        assertTrue(resposta.getStatusCode() == HttpStatus.OK);
    }


    @SneakyThrows
    @Test
    public void consultarNumeroDestinacao(){
        when(imovelService.consultarImovelRipPosseInformal(anyString(),anyString())).thenReturn(imovelDTO);
        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.consultarNumeroDestinacao(anyString(),anyString());
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void consultarNumeroDestinacaoException(){
        when(mensagemNegocio.get(anyString())).thenReturn(String.valueOf(String.class));
        when(imovelService.consultarImovelRipPosseInformal(anyString(),anyString())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.consultarNumeroDestinacao(anyString(),anyString());
        assertNotNull(respostaResponseEntity);
    }

    @Test
    public void buscarValorAvaliacaoTest(){
        Resposta<ImovelDTO> resposta  = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();
        ResponseEntity<Resposta<ImovelDTO>> respostaBusca = imovelController.buscarValorAvaliacao(1L);
        assertNotNull(resposta);
        assertNotNull(respostaBusca);
        assertTrue(respostaBusca.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarRipCodigoUtilizacaoParcelaTipoDestinacao(){

        when(imovelService.consultarImovel(anyString(),anyString(),anyString(),anyString())).thenReturn(imovelDTO);
        ResponseEntity resposta = imovelController.buscarRipCodigoUtilizacaoParcelaTipoDestinacao("00000","0000","p0","VENDA");
        assertEquals(HttpStatus.OK,resposta.getStatusCode());

    }

    @Test
    @SneakyThrows
    public void buscarRipCodigoUtilizacaoParcelaTipoDestinacaoException(){

        when(imovelService.consultarImovel(anyString(),anyString(),anyString(),anyString())).thenThrow(NegocioException.class);
        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.buscarRipCodigoUtilizacaoParcelaTipoDestinacao("00000","0000","p0","VENDA");
        assertNotNull(respostaResponseEntity);
    }

    @Test
    @SneakyThrows
    public void buscarDadosPosseInformal(){
        when(imovelService.consultarDadosPosseInformal(anyString(), anyString(), anyString())).thenReturn(imovelDTO);
        ResponseEntity resposta = imovelController.buscarDadosPosseInformal("00000", "0000", "p0");
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void buscarDadosPosseInformalException(){
        when(imovelService.consultarDadosPosseInformal(anyString(), anyString(), anyObject())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.buscarDadosPosseInformal("rip", "codigoUtilizacao", "parcela");
        assertNotNull(respostaResponseEntity);
    }

    @Test
    @SneakyThrows
    public void buscarPorRip(){
        when(imovelService.consultarImovel(anyString(),anyString(),anyString(),anyString())).thenReturn(imovelDTO);
        ResponseEntity resposta = imovelController.buscarPorRip("0000");
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void buscarPorRipNegocioException(){
        when(imovelService.consultarImovelRip(anyString(), anyString())).thenThrow(NegocioException.class);
        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.buscarPorRip("rip");
        assertNotNull(respostaResponseEntity);
    }

    @Test
    @SneakyThrows
    public void buscarPorRipIntegracaoException(){
        when(imovelService.consultarImovelRip(anyString(), anyString())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.buscarPorRip("rip");
        assertNotNull(respostaResponseEntity);
    }

    @Test
    @SneakyThrows
    public void buscarRipParcelaTipoDestinacao(){
        when(imovelService.consultarImovelRipParcela(consultaImovelDTO)).thenThrow(NegocioException.class);
        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.buscarRipParcelaTipoDestinacao(consultaImovelDTO);
        assertNotNull(respostaResponseEntity);
    }

    @Test
    @SneakyThrows
    public void buscarRipParcelaTipoDestinacaoException(){
        when(imovelService.consultarImovelRipParcela(consultaImovelDTO)).thenReturn(imovelDTO);
        ResponseEntity resposta  = imovelController.buscarRipParcelaTipoDestinacao(consultaImovelDTO);
        assertNotNull(resposta);
    }


    @Test
    public void consultarPorRipComSucesso() throws IntegracaoException, NegocioException{
        when(imovelService.consultarImovelRipValidando(any(ConsultaImovelDTO.class))).thenReturn(imovelDTO);
        ResponseEntity<Resposta<ImovelDTO>> resposta = imovelController.consultarPorRip(consultaImovelDTO);
        assertNotNull(resposta);
    }

    @Test
    @SneakyThrows
    public void consultarPorRipException() throws IntegracaoException, NegocioException {
        when(imovelService.consultarImovelRipValidando(any(ConsultaImovelDTO.class))).thenThrow(IntegracaoException.class, NegocioException.class);

        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.consultarPorRip(consultaImovelDTO);
        assertNotNull(respostaResponseEntity);
    }

    @Test
    @SneakyThrows
    public void consultarPorRipCuem(){
        when(imovelService.consultarImovel(anyString(), anyString(), anyString(), anyString())).thenReturn(imovelDTO);
        ResponseEntity resposta = imovelController.consultarPorRipCUEM("0000", "000", "p0", "t0", 1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void consultarPorRipCuemException(){
        when(imovelService.consultarImovel(anyString(), anyString(), anyString(), anyString())).thenThrow(NegocioException.class);
        ResponseEntity<Resposta<ImovelDTO>> respostaResponseEntity = imovelController.consultarPorRipCUEM("00000", "000", "p0", "00", 1L);
        assertNotNull(respostaResponseEntity);
    }

}