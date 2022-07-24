package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.enums.EmailEnum;
import lombok.SneakyThrows;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.byteThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by diego on 02/01/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Files.class, Magic.class})
public class ArquivoUtilsTest {

    @Mock
    private List<Byte> aByteList;

    @Before
    public void setUp() {
        mockStatic(Files.class);
    }

    @Test
    @SneakyThrows
    public void getContentType() {
        byte [] arquivo = getByteArrayfile();
        String contetType = ArquivoUtils.getContentType(arquivo);
        assertEquals("image/jpeg", contetType);
    }

    @Test(expected = MagicParseException.class)
    @SneakyThrows
    public void getContentTypeGerandoMagicParseException() {
        mockStatic(Magic.class);
        when(Magic.getMagicMatch(any(byte[].class))).thenThrow(MagicParseException.class);
        byte [] arquivo = getByteArrayfile();
        ArquivoUtils.getContentType(arquivo);
    }

    @Test(expected = MagicException.class)
    @SneakyThrows
    public void getContentTypeGerandoMagicMagicException() {
        mockStatic(Magic.class);
        when(Magic.getMagicMatch(any(byte[].class))).thenThrow(MagicException.class);
        byte [] arquivo = getByteArrayfile();
        ArquivoUtils.getContentType(arquivo);
    }

    @Test(expected = MagicMatchNotFoundException.class)
    @SneakyThrows
    public void getContentTypeGerandoMagicMatchNotFoundException() {
        mockStatic(Magic.class);
        when(Magic.getMagicMatch(any(byte[].class))).thenThrow(MagicMatchNotFoundException.class);
        byte [] arquivo = getByteArrayfile();
        ArquivoUtils.getContentType(arquivo);
    }

    @Test
    public void getExtencao() {
        String extensao = ArquivoUtils.getExtencao("application/pdf", "/");
        assertEquals(".pdf", extensao);
    }

    @Test
    public void getExtencaoRespostaVazia() {
        String extensao = ArquivoUtils.getExtencao("application", "/");
        assertEquals(StringUtils.EMPTY, extensao);
    }

    @Test
    public void lerArquivo() {
        String arquivo = ArquivoUtils.lerArquivo(EmailEnum.ANALISE_AGUARDANDO_REQUERENTE.getPath());
        assertEquals("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n" +
                "    <meta charset=\"UTF-8\">\n    <title></title>\n" +
                "</head>\n<body>\n<p>Caro Requerente,</p>\n<br />\n" +
                "\n<p>Foram identificadas pendências no seu requerimento.</p>\n" +
                "<p>Acesse <a href=\"http://e-spu.planejamento.gov.br\">e-spu.planejamento.gov.br</a> para execução dos ajustes necessários.</p>\n" +
                "\n<br />\n\n<p><b>{{nome_requerimento}}</b></p>\n" +
                "<p><b>Número do Atendimento:</b> {{numero_atendimento}}</p>\n" +
                "<p><b>Requerente:</b> {{nome_requerente}}</p>\n" +
                "<br>\n\n<p><b>IMPORTANTE!</b></p>\n" +
                "<p>1- A Secretaria do Patrimônio da União poderá cancelar os requerimentos cujas pendências não forem atendidas no prazo de 60 (sessenta) dias.</p>\n" +
                "<p>2- É possível acompanhar o andamento dos serviços no <a href=\"http://e-spu.planejamento.gov.br\">e-spu.planejamento.gov.br</a> informando o seu CPF/CNPJ e o <b>Número do Atendimento</b>.</p>\n" +
                "<br>\n\n" +
                "<p><b>Este e-mail está sendo enviado de forma automática. Favor não responder.</b></p>\n" +
                "</body>\n</html>", arquivo);
    }

    @Test
    public void lerArquivoGerandoException() {
        String arquivo = ArquivoUtils.lerArquivo("teste/teste.jpg");
        assertNull(arquivo);
    }

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testeInstanciaClasse() {
        Constructor<ArquivoUtils> constructor = ArquivoUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void salvarSucesso() throws IOException {
        byte [] bytes = getByteArrayfile();
        Boolean arquivoSalvo = ArquivoUtils.salvar(bytes, "/tmp/", System.currentTimeMillis() + ".jpeg");
        assertEquals(Boolean.TRUE, arquivoSalvo);
    }

    @Test
    public void removerErro() {
        Boolean delete = ArquivoUtils.remover("/tmp/", ".jpeg");
        assertEquals(Boolean.FALSE, delete);
    }

    @Test
    public void video(){
        Set<String> formatoVideos = new HashSet<>();
        formatoVideos.add("extensao");
        ArquivoUtils.isVideo("extensao");
    }

    @Test
    public void lerArquivoBytes()  {
        byte [] bytes = null;
        ArquivoUtils.lerArquivoBytes("path");
        assertNull(bytes);
    }

    @Test
    public void converteInputStreamToByte(){
    }

    private byte[] getByteArrayfile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/arquivos/teste.jpeg");
        return IOUtils.toByteArray(inputStream);
    }

}
