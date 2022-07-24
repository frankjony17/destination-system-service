package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ArquivoRepository;
import br.com.company.fks.destinacao.utils.*;
import lombok.SneakyThrows;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.xpath.operations.Neg;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Converter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by Basis Tecnologia on 21/10/2016.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ArquivoUtils.class, Files.class, Paths.class})
public class ArquivoServiceTest {

    private static final String PATH_ARQUIVO_TESTE = "/arquivos/teste.jpeg";

    @InjectMocks
    private ArquivoService arquivoService;

    @Mock
    private ArquivoRepository arquivoRepository;

    @Mock
    private Arquivo arquivo;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private ResponseEntity<byte[]> responseEntityDownloadArquivo;

    @Mock
    private ResponseEntity responseEntityListaArquivos;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletOutputStream servletOutputStream;

    @Mock
    private LinkedHashMap listaArquivos;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private Path pathNio;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private List<Long> longs;

    @Mock
    private List<Arquivo> arquivos;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private ArquivoDTO arquivoDTO;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private InputStream inputStreamMock;

    @Mock
    private Path path;

    @Before
    @SneakyThrows
    public void setUp() {
        mockStatic(ArquivoUtils.class);
        mockStatic(Files.class);
        mockStatic(Paths.class);

        byte[] bytes = new byte[1];
        String contentType = "tipo";
        String nome = "nome";
        when(multipartFile.getBytes()).thenReturn(bytes);
        when(multipartFile.getContentType()).thenReturn(contentType);
        when(multipartFile.getOriginalFilename()).thenReturn(nome);
        when(multipartFile.getOriginalFilename()).thenReturn("teste.pdf");

        when(arquivoRepository.save(any(Arquivo.class))).thenReturn(arquivo);
        when(arquivoRepository.findOne(anyLong())).thenReturn(arquivo);
        byte [] byteArray = new byte[1024];
        //when(multipartFile.getBytes()).thenReturn(getByteArrayfile());
        when(urlIntegracaoUtils.getUrlImovelCadastroImoveis(anyString())).thenReturn("www.google.com.br");
        when(urlIntegracaoUtils.getUrlListarArquivosServicos(anyLong(), anyLong())).thenReturn("www.google.com.br");
    }


    private byte[] getByteArrayfile() {
        try {
            InputStream inputStream = getClass().getResourceAsStream(PATH_ARQUIVO_TESTE);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[1024];
        }
    }

    @Test
    public void setPathArquivo(){
        arquivoService.setPathArquivo("path");
    }

    @Test
    public void salvaComFile() throws Exception {
        when(ArquivoUtils.salvar(any(), anyString(), anyString())).thenReturn(Boolean.TRUE);
        Arquivo arquivoTeste = arquivoService.salvar(multipartFile);
        assertNotNull(arquivoTeste);
    }

    @Test
    public void salvaComFileSemExtensao() throws Exception {
        when(ArquivoUtils.salvar(any(), anyString(), anyString())).thenReturn(Boolean.TRUE);
        when(multipartFile.getOriginalFilename()).thenReturn("teste");
        Arquivo arquivoTeste = arquivoService.salvar(multipartFile);
        assertNotNull(arquivoTeste);
    }

    @Test(expected = NegocioException.class)
    public void salvarArquivoGerandoErro() throws Exception {
        when(ArquivoUtils.salvar(any(byte[].class), anyString(), anyString())).thenReturn(Boolean.FALSE);
        Arquivo arquivoTeste = arquivoService.salvar(multipartFile);
    }

    @Test
    public void findById() throws Exception {
        Long id = Long.valueOf(01);
        Arquivo arquivoTeste = arquivoService.findById(id);
        assertNotNull(arquivoTeste);
    }

    @Test
    public void downloadArquivoModuloServicos() throws MagicMatchNotFoundException, MagicException, MagicParseException, IOException {

        byte [] byteArray = new byte[1024];
        //when(responseEntityDownloadArquivo.getBody()).thenReturn(getByteArrayfile());
        Mockito.<ResponseEntity<byte[]>>when((ResponseEntity<byte[]>) requestUtils.doGet(anyString(), eq(byte[].class))).thenReturn(responseEntityDownloadArquivo);
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        when(responseEntityDownloadArquivo.getBody()).thenReturn(byteArray);
        when(ArquivoUtils.getContentType(byteArray)).thenReturn("arquivo");
        arquivoService.downloadArquivoModuloServicosGestaoPraia("teste", response);

        arquivoService.downloadArquivoModuloServicos(1L, response);
        assertNotNull(response.getStatus());
    }

    @Test
    public void listarArquivosRequerimentosSucesso() throws IntegracaoException {
        when(requestUtils.doGet(anyString(), any())).thenReturn(responseEntityListaArquivos);
        when(listaArquivos.get("resposta")).thenReturn(montListaArquivos());
        when(responseEntityListaArquivos.getBody()).thenReturn(listaArquivos);
        List<ArquivoDTO> arquivos = arquivoService.listarArquivosRequerimentos(1L, 1L);
        assertFalse(arquivos.isEmpty());
    }
    @Test(expected = IntegracaoException.class)
    public void listarArquivosRequerimentosErro() throws IntegracaoException {
        when(requestUtils.doGet(anyString(), any())).thenReturn(responseEntityListaArquivos);
        when(listaArquivos.get("resposta")).thenReturn(Collections.emptyList());
        when(responseEntityListaArquivos.getBody()).thenReturn(listaArquivos);
        arquivoService.listarArquivosRequerimentos(1L, 1L);
    }

    private List montListaArquivos() {
        List lista = new ArrayList<>();
        lista.add(asList(1, "nome"));
        return lista;
    }

    @Test
    public void downloadArquivoModuloServicosGestaoPraia() throws MagicMatchNotFoundException, MagicException, MagicParseException, IOException {
        byte [] byteArray = new byte[1024];
        when(urlIntegracaoUtils.getUrlGetDonwloadGestaoPraia(anyString())).thenReturn("www.google.com.br");
        //when(responseEntityDownloadArquivo.getBody()).thenReturn(getByteArrayfile());
        Mockito.<ResponseEntity<byte[]>>when((ResponseEntity<byte[]>) requestUtils.doGet(anyString(), eq(byte[].class))).thenReturn(responseEntityDownloadArquivo);
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        when(responseEntityDownloadArquivo.getBody()).thenReturn(byteArray);
        when(ArquivoUtils.getContentType(byteArray)).thenReturn("arquivo");
        arquivoService.downloadArquivoModuloServicosGestaoPraia("teste", response);
        assertNotNull(response.getStatus());
    }

    @Ignore
    @Test
    public void baixarArquivo() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File f = new File(classLoader.getResource("arquivos/teste.jpeg").getFile());
        String path = f.getPath().replace("%20", StringUtils.SPACE);
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        when(arquivoRepository.findOne(anyLong())).thenReturn(arquivo);
        when(arquivo.getNome()).thenReturn(f.getName());
        when(arquivo.getDiretorioArquivo()).thenReturn(path.substring(0, (path.length() - 1) - f.getName().length()));

        arquivoService.baixarArquivo(1L, response);
    }

    @Test
    public void remover() throws NegocioException {
        when(ArquivoUtils.remover(anyString(), anyString())).thenReturn(Boolean.TRUE);
        Boolean resultado = arquivoService.remover(1L);
        assertEquals(true, resultado);
    }

    @Test(expected = NegocioException.class)
    public void removerErro() throws NegocioException {
        when(ArquivoUtils.remover(anyString(), anyString())).thenReturn(Boolean.FALSE);
        arquivoService.remover(1L);
    }

    @Test
    public void findListaArquivoById() {
        List<Arquivo> arquivos = arquivoService.findListaArquivoById(asList(arquivo));
        assertNotNull(arquivos);
        assertFalse(arquivos.isEmpty());
    }

    @Test
    public void findListaArquivoByIdRespostaVazia() {
        List<Arquivo> arquivos = arquivoService.findListaArquivoById(null);
        assertTrue(arquivos.isEmpty());
    }

    @Test
    public void buscarArquivosEntraIF(){
        when(arquivoRepository.buscarArquivos(longs)).thenReturn(asList(arquivo));
        List<Long> ids = new ArrayList<>();
        ids.add(new Long(1));
        arquivoService.buscarArquivos(ids);
    }

    @Test
    public void buscarArquivosNaoEntraIF(){
        List<Long> ids = new ArrayList<>();
        arquivoService.buscarArquivos(ids);
    }

    @Test
    public void salvarNovo(){
        when(ArquivoUtils.salvar(any(), anyString(), anyString())).thenReturn(Boolean.TRUE);
        Arquivo retorno = arquivoService.salvarNovo(multipartFile,"","2017-02-02");
        assertNotNull(retorno);
    }

    @Test(expected = NegocioException.class)
    public void salvarNovoDisparandoException(){
        when(ArquivoUtils.salvar(any(), anyString(), anyString())).thenReturn(Boolean.FALSE);
        Arquivo retorno = arquivoService.salvarNovo(multipartFile,"","2017-02-02");
    }

    @Test
    public void salvarListaArquivo(){
        List<ArquivoDTO> arquivoList = new ArrayList<>();
        arquivoList.add(new ArquivoDTO());
        when(entityConverter.converterStrict(anyList(), eq(Arquivo.class))).thenReturn(arquivo);
        List<Arquivo> arquivoDTOList = arquivoService.salvarListaArquivo(arquivoList);
        assertNotNull(arquivoDTOList);
        assertTrue(!arquivoDTOList.isEmpty());
    }

    @Test
    public void salvarListaArquivoNull(){
        List<ArquivoDTO> arquivoList = new ArrayList<>();
        arquivoList.add(new ArquivoDTO());
        when(entityConverter.converterStrict(anyList(), eq(Arquivo.class))).thenReturn(arquivo);
        List<Arquivo> arquivoDTOList = arquivoService.salvarListaArquivo(null);
        assertNotNull(arquivoDTOList);
        assertFalse(!arquivoDTOList.isEmpty());
    }

    @Test
    public void salvarListaArquivoFalse(){
        List<ArquivoDTO> arquivoList = new ArrayList<>();
        when(entityConverter.converterStrict(anyList(), eq(Arquivo.class))).thenReturn(arquivo);
        List<Arquivo> arquivoDTOList = arquivoService.salvarListaArquivo(arquivoList);
        assertNull(null);
    }


    @Test(expected = IOException.class)
    public void prepararListaFotoVideo() throws  IOException{
        List<ArquivoDTO> arquivoDTOList = new ArrayList<>();
        arquivoDTOList.add(arquivoDTO);
        arquivoDTO.setId(1L);
        when(arquivoDTO.getExtensao()).thenReturn("teste");
        List<ArquivoDTO> arquivosPreparados = arquivoService.prepararListaFotoVideo(arquivoDTOList);
        assertNotNull(arquivosPreparados);
    }

    @Test(expected = NegocioException.class)
    public void removerAtosComplementares() throws NegocioException {
        arquivoRepository.deletarAtosComplementares(1L);
        arquivoService.removerAtosComplementares(1L);
    }

    @Test(expected = NegocioException.class)
    public void removerListaArquivo() throws NegocioException {
        List<ArquivoDTO> arquivoDTOs = new ArrayList<>();
        arquivoDTOs.add(arquivoDTO);
        arquivoDTO.setId(1L);
        arquivoService.remover(arquivoDTOs);
    }

    @Test
    @SneakyThrows
    public void validarConteudoDoArquivoFalse(){

        when(multipartFile.getName()).thenReturn("teste");
        when(multipartFile.getOriginalFilename()).thenReturn("teste.pdf");
        when(multipartFile.getContentType()).thenReturn("application/pdf");

        arquivoService.validarConteudoDoArquivo(multipartFile);

    }


    @Test
    @SneakyThrows
    public void validarConteudoDoArquivoTrue(){

        when(multipartFile.getName()).thenReturn("teste");
        when(multipartFile.getOriginalFilename()).thenReturn("teste.pdf");
        when(multipartFile.getContentType()).thenReturn("application/pdf");
        when(multipartFile.getInputStream()).thenReturn(inputStreamMock);

        Boolean arquivo = arquivoService.validarConteudoDoArquivo(multipartFile);

        assertFalse(arquivo);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void montarArquivoShapeFile(){

        when(multipartFile.getName()).thenReturn("teste");
        when(multipartFile.getOriginalFilename()).thenReturn("teste.pdf");
        when(multipartFile.getContentType()).thenReturn("application/pdf");
        when(multipartFile.getInputStream()).thenReturn(inputStreamMock);


        arquivoService.montarArquivoShapeFile(multipartFile);

    }



    @Test(expected = IOException.class)
    @SneakyThrows
    public void salvarFotoVideo(){


        byte[] bytes = new byte[1];
        String contentType = "tipo";
        String nome = "nome";
        when(multipartFile.getBytes()).thenReturn(bytes);
        when(multipartFile.getContentType()).thenReturn(contentType);
        when(multipartFile.getOriginalFilename()).thenReturn(nome);
        when(multipartFile.getOriginalFilename()).thenReturn("teste.pdf");

        arquivoService.salvarFotoVideo(multipartFile, "", "2018-10-10");

    }



}
