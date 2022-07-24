package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.PublicacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.Publicacao;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.AnaliseTecnicaService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by guilherme on 01/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnaliseTecnicaControllerTest {

    @InjectMocks
    private AnaliseTecnicaController analiseTecnicaController;

    @Mock
    private AnaliseTecnicaService analiseTecnicaService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private AnaliseTecnicaDTO analiseTecnicaDTO;


    @Before
    public void setUp(){
        when(analiseTecnicaService.salvar(any(AnaliseTecnicaDTO.class))).thenReturn(new AnaliseTecnicaDTO());
        when(analiseTecnicaService.salvarRascunho(any(AnaliseTecnicaDTO.class))).thenReturn(new AnaliseTecnicaDTO());
    }


    @Test
    public void salvarComSucesso() throws Exception {
        ResponseEntity<Resposta<AnaliseTecnica>> analiseTecnicasalvaDTO =
                analiseTecnicaController.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicasalvaDTO);
    }

    @Test
    public void salvarComErro() throws RuntimeException{
        when(analiseTecnicaService.salvar(any(AnaliseTecnicaDTO.class))).thenThrow(RuntimeException.class);
        ResponseEntity<Resposta<AnaliseTecnica>> analiseTecnicasalvaDTO =
                analiseTecnicaController.salvar(analiseTecnicaDTO);
        assertNotNull(analiseTecnicasalvaDTO);
    }

    @Test
    public void salvarRascunhoComSucesso() throws Exception {
        ResponseEntity<Resposta<AnaliseTecnica>> analiseTecnicasalvaDTO =
                analiseTecnicaController.salvarRascunho(analiseTecnicaDTO);
        assertNotNull(analiseTecnicasalvaDTO);
    }

    @Test
    public void salvarRascunhoComErro() throws RuntimeException{
        when(analiseTecnicaService.salvarRascunho(any(AnaliseTecnicaDTO.class))).thenThrow(RuntimeException.class);
        ResponseEntity<Resposta<AnaliseTecnica>> analiseTecnicasalvaDTO =
                analiseTecnicaController.salvarRascunho(analiseTecnicaDTO);
        assertNotNull(analiseTecnicasalvaDTO);
    }

    @Test
    public void buscarPorId()throws NegocioException {
        when(analiseTecnicaService.buscarPorId(anyLong())).thenReturn( analiseTecnicaDTO);

        ResponseEntity<Resposta<AnaliseTecnica>> analiseTecnicaRetornoDTO =
                analiseTecnicaController.buscarPorId(1l);
        assertNotNull(analiseTecnicaRetornoDTO);
    }

    @Test
    public void buscarPorIdComErro() throws Exception{
        when(analiseTecnicaService.buscarPorId(anyLong())).thenThrow(NegocioException.class);
        ResponseEntity<Resposta<AnaliseTecnica>> analiseTecnicaRetornoDTO =
                analiseTecnicaController.buscarPorId(1l);
        assertNotNull(analiseTecnicaRetornoDTO);

    }

    @Test
    public void buscarAnalisePorRequerimentoComSucesso() throws Exception {
        when(analiseTecnicaService.buscarPorIdRequerimento(anyLong())).thenReturn(new AnaliseTecnicaDTO());
        ResponseEntity<Resposta<AnaliseTecnica>> analiseTecnicaRetornoDTO =
                analiseTecnicaController.buscarAnalisePorRequerimento(1l);
        assertNotNull(analiseTecnicaRetornoDTO);
    }

    @Test
    public void buscarAnalisePorRequerimentoComErro() throws Exception{
        when(analiseTecnicaService.buscarPorIdRequerimento(anyLong())).thenThrow(NegocioException.class);
        ResponseEntity<Resposta<AnaliseTecnica>> analiseTecnicaRetornoDTO =
                analiseTecnicaController.buscarAnalisePorRequerimento(1l);
        assertNotNull(analiseTecnicaRetornoDTO);
    }

    @Test
    public void registrarPublicacaoDiarioUniaoComSucesso() {
        when(entityConverter.converterStrict(any(AnaliseTecnicaDTO.class), eq(AnaliseTecnica.class))).thenReturn(new AnaliseTecnica());
        when(entityConverter.converterStrict(any(Publicacao.class), eq(Publicacao.class))).thenReturn(new Publicacao());
        when(analiseTecnicaService.registrarPublicacaoDiarioUniao(anyLong(), any(Publicacao.class))).thenReturn(new AnaliseTecnica());

        ResponseEntity<Resposta<PublicacaoDTO>> publicaoSalva =
                analiseTecnicaController.registrarPublicacaoDiarioUniao(analiseTecnicaDTO);

        assertNotNull(publicaoSalva);
    }

}