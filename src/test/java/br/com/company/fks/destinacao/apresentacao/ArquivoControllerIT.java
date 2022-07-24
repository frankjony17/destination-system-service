package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.apresentacao.mockserver.MockServerUtils;
import br.com.company.fks.destinacao.service.ArquivoService;
import br.com.company.fks.destinacao.utils.ArquivoUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 05/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ArquivoControllerIT extends BaseIntegrationTestCofig {

    private static final String URL_BASE = "/arquivo/";
    private static final String PATH_ARQUIVO_TESTE = "/arquivos/teste.jpeg";
    private static final String REMOVER_JPEG = "remover.jpeg";
    private static final String TESTE_JPEG = "teste.jpeg";
    private static final String ARQUIVOS_TESTE_JPEG = "/arquivos/teste.jpeg";

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    private MockServerUtils mockServerUtils;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private Environment environment;

    @Test
    @SneakyThrows
    public void upload() {
        MockMultipartFile file = new MockMultipartFile("file","original_filename.pdf", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "upload")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void uploadSemExtensao() {
        MockMultipartFile file = new MockMultipartFile("file","original_filename", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "upload")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void uploadGerandoErro() {
        byte [] arquivo = new byte[0];
        arquivoService.setPathArquivo("/%20f/tmp/");
        MockMultipartFile file = new MockMultipartFile("file", "teste.pdf", MediaType.APPLICATION_OCTET_STREAM_VALUE, arquivo);
        mockMvc.perform(fileUpload(URL_BASE + "upload")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.erros[0]").value("Erro ao carregar o arquivo"));
        arquivoService.setPathArquivo("/tmp/");
    }

    @Test
    @SneakyThrows
    public void downloadArquivoRequerimento() {

        String url = urlIntegracaoUtils.getUrlDownlodaArquivoServico(1L);

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getByteArrayfile(), MediaType.APPLICATION_OCTET_STREAM);

        mockMvc.perform(get(URL_BASE + "/downloadArquivoRequerimento/1")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "image/jpeg"));
    }

    @Test
    @SneakyThrows
    public void downloadArquivoRequerimentoPraia() {
        String url = urlIntegracaoUtils.getUrlGetDonwloadGestaoPraia("PRAIA");

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getByteArrayfile(), MediaType.APPLICATION_OCTET_STREAM);

        mockMvc.perform(get(URL_BASE + "/downloadArquivoRequerimentoGestaoPraia/PRAIA")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "image/jpeg"));
    }

    @Test
    @SneakyThrows
    public void listarArquivosRequerimento() {
        String url = urlIntegracaoUtils.getUrlListarArquivosServicos(1L, 1L);

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getListaArquivosJson(), MediaType.APPLICATION_JSON);

        mockMvc.perform(get(URL_BASE + "/listarArquivosRequerimento/1/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void listarArquivosRequerimentoVazio() {
        String url = urlIntegracaoUtils.getUrlListarArquivosServicos(1L, 1L);

        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resposta", Collections.emptyList());

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, toJson(resposta), MediaType.APPLICATION_JSON);

        mockMvc.perform(get(URL_BASE + "/listarArquivosRequerimento/1/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    @SneakyThrows
    public void listarArquivosRequerimentoLancaErros() {

        String url = urlIntegracaoUtils.getUrlListarArquivosServicos(1L, 1L);

        mockServerUtils = new MockServerUtils()
                .iniciarRestTemplate(requestUtils.getRestTemplate())
                .mockServe(url, HttpMethod.GET, getListaArquivosVazioJson(), MediaType.APPLICATION_JSON);

        mockMvc.perform(get(URL_BASE + "/listarArquivosRequerimento/1/1")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.erros").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void baixarArquivoSucesso() {

        String pathfile = getClass().getResource(ARQUIVOS_TESTE_JPEG).getFile();
        String newPathFile = pathfile.replace(TESTE_JPEG, "");

        copyFile();

        mockMvc.perform(get(URL_BASE + "/baixarArquivo/7")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());

        ArquivoUtils.remover(newPathFile, REMOVER_JPEG);

    }

    @Test
    @SneakyThrows
    public void baixarArquivoErro() {
        mockMvc.perform(get(URL_BASE + "/baixarArquivo/2")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    @SneakyThrows
    public void remover() {
        copyFile();
        mockMvc.perform(delete(URL_BASE + "remover/" + "7")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void removerGerandoErro() {
        mockMvc.perform(delete(URL_BASE + "remover/" + "9")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @SneakyThrows
    private void copyFile() {

        String pathfile = getClass().getResource("/arquivos/teste.jpeg").getFile().replace("%20", " ");

        Path source = Paths.get(pathfile);
        String newPathFile = pathfile.replace("teste.jpeg", "");
        String newNameFile = "remover.jpeg";

        atualizaPathArquivoH2(newPathFile, newNameFile);

        Path nwdir = Paths.get(newPathFile + newNameFile);

        try {
            Files.copy(source, nwdir);
        } catch (IOException e){
            System.out.println("Unsucessful. What a surprise!");
        }
    }

    @SneakyThrows
    private void atualizaPathArquivoH2(String path, String name) {
        Class.forName(environment.getProperty("spring.datasource.driverClassName"));
        String url = environment.getProperty("spring.datasource.url");
        String user = environment.getProperty("spring.datasource.username");
        String pwd = environment.getProperty("spring.datasource.password");
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String query = "UPDATE destinacao.tb_arquivo as a SET a.dir_arquivo = '" + path + "', a.ds_nome = '" + name + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);

        statement.close();
        connection.close();
    }

    private byte[] getByteArrayfile() {
        try {
            InputStream inputStream = getClass().getResourceAsStream(PATH_ARQUIVO_TESTE);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            return new byte[1024];
        }
    }

    @SneakyThrows
    private String getListaArquivosJson() {
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        List objects = Arrays.asList(1, "teste.pdf");
        List arquivos = Arrays.asList(objects);
        resposta.put("resposta", arquivos);
        return toJson(resposta);
    }

    @SneakyThrows
    private String getListaArquivosVazioJson() {
        LinkedHashMap<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("resposta", Collections.emptyList());
        return toJson(resposta);
    }

    @Test
    @SneakyThrows
    public void uploadShapeFileComShapeFileValido(){
        InputStream inputStream = getClass().getResourceAsStream("/arquivos/ShapeFile.zip");
        MockMultipartFile file = new MockMultipartFile("file","original_filename.zip", MediaType.APPLICATION_OCTET_STREAM_VALUE, IOUtils.toByteArray(inputStream));
        mockMvc.perform(fileUpload(URL_BASE + "uploadComShapeFile")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void uploadShapeFileComShapeFileInvalido(){
        MockMultipartFile file = new MockMultipartFile("file","original_filename.zip", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadComShapeFile")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    public void uploadShapeFileComPdf(){
        MockMultipartFile file = new MockMultipartFile("file","original_filename.pdf", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadComShapeFile")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void uploadVideo() {
        MockMultipartFile file = new MockMultipartFile("file","teste.mp4", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadFotoVideo/teste/2017-05-01")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void uploadVideoGerandoErro() {
        arquivoService.setPathArquivo("/%20f/tmp/");
        MockMultipartFile file = new MockMultipartFile("file","teste.mp4", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadFotoVideo/teste/2017-05-01")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        arquivoService.setPathArquivo("/tmp/");
    }

    @Test
    @SneakyThrows
    public void uploadFoto() {
        MockMultipartFile file = new MockMultipartFile("file","teste.png", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadFotoVideo/teste/2017-05-01")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void uploadFotoVideoGerandoErro() {
        MockMultipartFile file = new MockMultipartFile("file","teste.mp4", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadFotoVideo/teste/null")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @SneakyThrows
    public void uploadNovoGerandoErro() {
        MockMultipartFile file = new MockMultipartFile("file","teste.pdf", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadNovo/teste.pdf")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @SneakyThrows
    public void uploadNovoPDF() {
        MockMultipartFile file = new MockMultipartFile("file","teste.pdf", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadNovo/teste/2017-05-01")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void uploadNovoPDFSemData() {
        MockMultipartFile file = new MockMultipartFile("file","teste.pdf", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadNovo/teste/null")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void uploadNovoSemBytes() {
        arquivoService.setPathArquivo("/%20f/tmp/");
        MockMultipartFile file = new MockMultipartFile("file","teste.pdf", MediaType.APPLICATION_OCTET_STREAM_VALUE, "meu_arquivo".getBytes());
        mockMvc.perform(fileUpload(URL_BASE + "uploadNovo/teste/2017-05-01")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        arquivoService.setPathArquivo("/tmp/");
    }
}
