package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe responsável por manipular arquivos
 * Created by diego on 06/12/16.
 */
public final class ArquivoUtils {

    private static final Logger LOGGER = Logger.getLogger(ArquivoUtils.class);

    private static final Set<String> formatoVideos;

    static{
        formatoVideos = new HashSet<>();
        formatoVideos.add(".mp4");
    }

    private ArquivoUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Busca ContentType do arquivo
     * @param arquivo
     * @return String com o resultado da busca
     */
    @SneakyThrows
    public static String getContentType(byte[] arquivo) {
        MagicMatch match = Magic.getMagicMatch(arquivo);
        return match.getMimeType();
    }

    /**
     * Identifica a extenção do arquivo
     * @param contentType
     * @param separador
     * @return String com a extenção do arquivo
     */
    public static String getExtencao(String contentType, String separador) {
        String [] extensao = contentType.split(separador);
        if (extensao.length > 1) {
            return "." + extensao[extensao.length - 1];
        }
        return StringUtils.EMPTY;
    }

    /**
     * Ler o arquivo
     * @param path
     * @return String com conteúdo do arquivo
     */
    public static String lerArquivo(String path){
        String content = null;

        try  {
            String pathArquivo = Paths.get(path).toString();
            InputStream in = new ClassPathResource(pathArquivo).getInputStream();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            content =  buffer.lines().collect(Collectors.joining("\n"));

            buffer.close();
            in.close();
        } catch (IOException e) {
            LOGGER.error("ERRO LER ARQUIVO", e);
        }

        return content;
    }

    /**
     * Salvar o arquivo em determinado diretório
     * @param bytes
     * @param diretorioArquivo
     * @param nome
     * @return true ou false
     */
    public static Boolean salvar(byte[] bytes, String diretorioArquivo, String nome) {
        try {
            criaDiretorioSeNaoExiste(diretorioArquivo);
            Path pathArquivo = Paths.get(diretorioArquivo, nome);
            Files.write(pathArquivo, bytes);
            return Boolean.TRUE;
        } catch (IOException e) {
            LOGGER.error("ERRO SALVAR ARQUIVO", e);
            return Boolean.FALSE;
        }
    }

    /**
     * Verifica a existencia de um diretorio, e cria caso nao exista
     * @param path
     */
    private static Boolean criaDiretorioSeNaoExiste(String path) {
        File diretorio = new File(path);
        if (!diretorio.exists()) {
            return diretorio.mkdirs();
        }
        return false;
    }

    /**
     * Remover determinado arquivo de determinado diretório
     * @param diretorio
     * @param nome
     * @return true ou false
     */
    public static Boolean remover(String diretorio, String nome) {
        Path path = Paths.get(diretorio + nome);
        try {
            Files.delete(path);
            return Boolean.TRUE;
        } catch (IOException e) {
            LOGGER.error("ERRO REMOVER ARQUIVO", e);
            return Boolean.FALSE;
        }
    }

    /**
     * Verifica a extensao do arquivo é um video
     * @param extensao
     * @return boolean
     */
    public static boolean isVideo(String extensao){
        return formatoVideos.contains(extensao);
    }

    /**
     *
     * @return
     * @throws IOException
     */
       public static byte[] converteInputStreamToByte(InputStream inputStream) throws IOException {
        return IOUtils.toByteArray(inputStream);
    }

    public static byte[] lerArquivoBytes(String path) {
        byte[] bytes = null;
        Path caminho = Paths.get(path);
        try {
            bytes = Files.readAllBytes(caminho);
        } catch (IOException e) {
            LOGGER.error("ERRO LER ARQUIVO", e);
        }
        return bytes;
    }
}
