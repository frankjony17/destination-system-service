package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ArquivoRepository;
import br.com.company.fks.destinacao.service.ArquivoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.apache.velocity.runtime.directive.Parse;
import org.apache.xpath.operations.Neg;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by haillanderson on 01/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ArquivoControllerTest {

    @InjectMocks
    private ArquivoController arquivoController;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private Arquivo arquivo;

    @Mock
    private ArquivoDTO arquivoDTO;


    @Mock
    private Resposta resposta;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private ArquivoRepository arquivoRepository;

    @Mock
    private MultipartFile file;



    @Before
    @SneakyThrows
    public void setUp(){
        when(arquivoService.salvar(any(MultipartFile.class))).thenReturn(arquivo);
        when(entityConverter.converterStrict(any(Arquivo.class), eq(ArquivoDTO.class))).thenReturn(arquivoDTO);
        when(mensagemNegocio.get(anyString())).thenReturn("msg");
        when(arquivoService.listarArquivosRequerimentos(anyLong(),anyLong())).thenReturn(Arrays.asList(arquivoDTO));
    }

    @Test
    public void upload() throws Exception {
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity= arquivoController.upload(file);
        assertNotNull(respostaResponseEntity);
    }

    @Test
    public void uploadDisparandoException(){
        when(entityConverter.converterStrict(any(Arquivo.class), eq(ArquivoDTO.class))).thenThrow(RuntimeException.class);

        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity= arquivoController.upload(file);
        assertTrue(respostaResponseEntity.getStatusCode()== HttpStatus.BAD_REQUEST);
    }

    @Test
    @SneakyThrows
    public void downloadArquivoRequerimento(){
        arquivoController.downloadArquivoRequerimento(httpServletResponse, 1l);
    }

    @Test
    @SneakyThrows
    public void downloadArquivoRequerimentoPraia(){
        arquivoController.downloadArquivoRequerimentoPraia(httpServletResponse,"");
    }

    @Test
    @SneakyThrows
    public void listarArquivosRequerimento(){
        ResponseEntity<Resposta<List<ArquivoDTO>>> respostaResponseEntity= arquivoController.listarArquivosRequerimento(1l,1l);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void listarArquivosRequerimentoDisparandoException(){
        when(arquivoService.listarArquivosRequerimentos(anyLong(), anyLong())).thenThrow(IntegracaoException.class);
        ResponseEntity<Resposta<List<ArquivoDTO>>> respostaResponseEntity= arquivoController.listarArquivosRequerimento(1l,1l);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void baixarArquivo(){
        ResponseEntity responseEntity= arquivoController.baixarArquivo(1l, httpServletResponse);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void baixarArquivoDisparandoException(){
        doThrow(IOException.class).when(arquivoService).baixarArquivo(anyLong(),any(HttpServletResponse.class));
        ResponseEntity responseEntity= arquivoController.baixarArquivo(1l, httpServletResponse);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void remover(){
        ResponseEntity responseEntity= arquivoController.remover(1l);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void removerAtoComplementar(){
        ResponseEntity responseEntity = arquivoController.removerAtoComplementar(1L);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void removerAtoComplementarException() throws NegocioException {
        doThrow(NegocioException.class).when(arquivoService).removerAtosComplementares(anyLong());
        ResponseEntity responseEntity = arquivoController.removerAtoComplementar(1L);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @SneakyThrows
    public void removerDisparandoException(){
        when(arquivoService.remover(anyLong())).thenThrow(NegocioException.class);
        ResponseEntity responseEntity= arquivoController.remover(1l);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @SneakyThrows
    public void uploadShapeFileComShapeFileValido(){
        when(multipartFile.getOriginalFilename()).thenReturn("teste.zip");
        when(arquivoService.validarConteudoDoArquivo(any(MultipartFile.class))).thenReturn(Boolean.TRUE);
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity = arquivoController.uploadShapeFile(multipartFile);
        assertNotNull(respostaResponseEntity.getBody().getResultado());
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void uploadShapeFileComShapeFileInalido(){
        when(multipartFile.getOriginalFilename()).thenReturn("teste.zip");
        when(arquivoService.validarConteudoDoArquivo(any(MultipartFile.class))).thenReturn(Boolean.FALSE);
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity = arquivoController.uploadShapeFile(multipartFile);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    @SneakyThrows
    public void uploadShapeFileComShapeFileSemZip(){
        when(multipartFile.getOriginalFilename()).thenReturn("teste.jpg");
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity = arquivoController.uploadShapeFile(multipartFile);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void uploadFotoVideo(){
        when(arquivoService.salvarFotoVideo(any(MultipartFile.class),anyString(),anyString())).thenReturn(arquivo);
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity = arquivoController.uploadFotoVideo(multipartFile,"","");
        assertNotNull(respostaResponseEntity.getBody().getResultado());
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void uploadFotoVideoDisparandoException(){
        when(arquivoService.salvarFotoVideo(any(MultipartFile.class),anyString(),anyString())).thenThrow(ParseException.class, IOException.class);
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity = arquivoController.uploadFotoVideo(multipartFile,"","");
        assertNull(respostaResponseEntity.getBody().getResultado());
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    @SneakyThrows
    public void upLoadNovo(){
        when(arquivoService.salvarNovo(any(MultipartFile.class),anyString(),anyString())).thenReturn(arquivo);
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity = arquivoController.uploadNovo(multipartFile,"","");
        assertNotNull(respostaResponseEntity.getBody().getResultado());
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void uploadNovoDisparandoException(){
        when(arquivoService.salvarNovo(any(MultipartFile.class),anyString(),anyString())).thenThrow(RuntimeException.class);
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity = arquivoController.uploadNovo(multipartFile,"","");
        assertNull(respostaResponseEntity.getBody().getResultado());
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

}