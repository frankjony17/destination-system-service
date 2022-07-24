package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ArquivoRepository;
import br.com.company.fks.destinacao.utils.ArquivoUtils;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.DataUtil;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import com.vividsolutions.jts.geom.Geometry;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Classe reponsável por realizar operações com arquivos
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Service
public class ArquivoService {

    private static final Pattern EXTENSAO_ARQUIVO_PATTERN = Pattern.compile("(.dbf|.shx|.shp|.prj)", Pattern.CASE_INSENSITIVE);
    private static final int NUMERO_ARQUIVO_SHAPEFILE = 4;
    private static final Logger LOGGER = Logger.getLogger(ArquivoService.class);
    private static final String SEPARADOR_PONTO = "\\.";
    private static final String ERRO_SALVAR_ARQUIVO = "ERRO AO SALVAR ARQUIVO";
    private static final String SEPARADOR_BARRA = "\\/";
    private static final int INDICE_ID = 0;
    private static final int INDICE_NOME = 1;

    @Value("${path.arquivo.upload}")
    private String pathArquivo;

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private EntityConverter converter;

    /**
     * Muda o caminho do arquivo configurado.
     * @param path
     */
    public void setPathArquivo(String path) {
        this.pathArquivo = path;
    }

    /**
     * Salva o arquivo por partes
     *
     * @param file
     * @return Arquivo salvo
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @SneakyThrows
    public Arquivo salvar(MultipartFile file) {
        Arquivo arquivo = criarArquivo(file);
        if (ArquivoUtils.salvar(file.getBytes(), arquivo.getDiretorioArquivo(), arquivo.getNome())) {
            return arquivoRepository.save(arquivo);
        } else {
            throw new NegocioException(ERRO_SALVAR_ARQUIVO);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Arquivo> salvarListaArquivo(List<ArquivoDTO> dtos) {
        List<Arquivo> arquivos = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            dtos.forEach(dto -> {
                Arquivo arquivo = converter.converterStrict(dto, Arquivo.class);
                arquivos.add(arquivoRepository.save(arquivo));
            });
        }
        return arquivos;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @SneakyThrows
    public Arquivo salvarNovo(MultipartFile file, String descricao, String data){
        Arquivo arquivo = criarArquivo(file);
        arquivo.setNomeReal(file.getOriginalFilename());
        arquivo.setDescricao(descricao);
        if(!data.equals("null")){
            arquivo.setData(DataUtil.stringToDate(data,"yyyy-MM-dd"));
        }
        if(ArquivoUtils.salvar(file.getBytes(), arquivo.getDiretorioArquivo(), arquivo.getNome())){
            return arquivoRepository.save(arquivo);
        }
        else{
            throw new NegocioException(ERRO_SALVAR_ARQUIVO);
        }
    }

    /**
     * Reconstrói o arquivo por partes
     *
     * @param file
     * @return Arquivo montado
     */
    private Arquivo criarArquivo(MultipartFile file) {
        Arquivo arquivo = new Arquivo();
        arquivo.setData(new Date());
        arquivo.setExtensao(ArquivoUtils.getExtencao(file.getOriginalFilename(), SEPARADOR_PONTO));
        arquivo.setNome(System.currentTimeMillis() + arquivo.getExtensao());
        arquivo.setTamanho(file.getSize());
        arquivo.setContentType(file.getContentType());
        arquivo.setDiretorioArquivo(pathArquivo);
        return arquivo;
    }

    /**
     * Remove o arquivo pelo id
     *
     * @param id
     * @return true ou false
     * @throws NegocioException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean remover(Long id) throws NegocioException {
        Arquivo arquivo = findById(id);
        arquivoRepository.delete(id);
        if (!ArquivoUtils.remover(arquivo.getDiretorioArquivo(), arquivo.getNome())) {
            throw new NegocioException(mensagemNegocio.get("erro.remover.arquivo"));
        }
        return Boolean.TRUE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removerAtosComplementares(Long id) throws NegocioException{
        arquivoRepository.deletarAtosComplementares(id);
        remover(id);
    }

    /**
     * Procura o arquivo por id
     *
     * @param id
     * @return Arquivo encontrado
     */
    @Transactional(readOnly = true)
    public Arquivo findById(Long id) {
        return arquivoRepository.findOne(id);
    }

    /**
     * Procura uma lista de arquivos por id dos arquivos
     *
     * @param arquivos
     * @return List<arquivo> (lista dos arquivos encontrados)
     */
    @Transactional(readOnly = true)
    public List<Arquivo> findListaArquivoById(List<Arquivo> arquivos) {
        if (arquivos != null) {
            List<Arquivo> arquivosRecuperados = new ArrayList<>();
            arquivos.forEach(arquivo -> arquivosRecuperados.add(findById(arquivo.getId())));
            return arquivosRecuperados;
        }
        return Collections.emptyList();
    }

    /**
     * Realiza o download do arquivo do módulo serviços
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @SneakyThrows
    public void downloadArquivoModuloServicos(Long id, HttpServletResponse response) {
        String url = urlIntegracaoUtils.getUrlDownlodaArquivoServico(id);
        ResponseEntity<byte[]> resposta = (ResponseEntity<byte[]>) requestUtils.doGet(url, byte[].class);
        byte[] arquivo = resposta.getBody();
        downloadArquivo(response, arquivo);
    }

    /**
     * Realiza o download do arquivo do módulo serviços gestão praia
     *
     * @param nomeArquivo
     * @param response
     * @throws IOException
     */
    @SneakyThrows
    public void downloadArquivoModuloServicosGestaoPraia(String nomeArquivo, HttpServletResponse response) {
        String url = urlIntegracaoUtils.getUrlGetDonwloadGestaoPraia(nomeArquivo);
        ResponseEntity<byte[]> resposta = (ResponseEntity<byte[]>) requestUtils.doGet(url, byte[].class);
        byte[] arquivo = resposta.getBody();
        downloadArquivo(response, arquivo);
    }

    @SneakyThrows
    private void downloadArquivo(HttpServletResponse response, byte[] arquivo, String contentType) {
        ByteArrayInputStream bis = new ByteArrayInputStream(arquivo);
        String extensao = ArquivoUtils.getExtencao(contentType, SEPARADOR_BARRA);
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + extensao + ";");
        IOUtils.copy(bis, response.getOutputStream());
        response.flushBuffer();
    }

    @SneakyThrows
    private void downloadArquivo(HttpServletResponse response, byte[] arquivo) {
        String contentType = ArquivoUtils.getContentType(arquivo);
        downloadArquivo(response, arquivo, contentType);
    }

    /**
     * Realiza uma busca dos arquivos por id do requerimento
     *
     * @param idRequerimento
     * @param idDocumento
     * @return lista de arquivos do requerimento
     * @throws IntegracaoException
     */
    public List<ArquivoDTO> listarArquivosRequerimentos(Long idRequerimento, Long idDocumento) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlListarArquivosServicos(idRequerimento, idDocumento);
        LinkedHashMap body = (LinkedHashMap) requestUtils.doGet(url, Object.class).getBody();
        List arquivos = (List) body.get("resposta");
        Optional<List> optional = Optional.ofNullable(arquivos);
        Boolean existeArquivos = optional.map(lista -> !lista.isEmpty()).orElse(false);
        if (existeArquivos) {
            return montarArquivoDTO(arquivos);
        }
        throw new IntegracaoException("ERRO LISTAR ARQUIVOS REQUERIMENTO");
    }

    private List<ArquivoDTO> montarArquivoDTO(List arquivos) {
        List<ArquivoDTO> arquivoDTOs = new ArrayList<>();

        arquivos.forEach(listaArquivos -> {
            List arqs = (List) listaArquivos;
            Integer id = (Integer) arqs.get(INDICE_ID);
            String nome = (String) arqs.get(INDICE_NOME);
            ArquivoDTO arquivoDTO = new ArquivoDTO(id.longValue(), nome);
            arquivoDTOs.add(arquivoDTO);
        });

        return arquivoDTOs;
    }

    /**
     * Baixa o arquivo
     *
     * @param id
     * @param response
     * @throws IOException
     */
    public void baixarArquivo(Long id, HttpServletResponse response) throws IOException {
        Arquivo arquivo = arquivoRepository.findOne(id);
        Path path = Paths.get(arquivo.getDiretorioArquivo(), "/" + arquivo.getNome());
        byte[] bytes = Files.readAllBytes(path);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        response.setContentType(arquivo.getContentType());
        response.setHeader("Content-Disposition", "attachment;filename=" + arquivo.getNome() + ";");
        IOUtils.copy(bis, response.getOutputStream());
        response.flushBuffer();
    }


    /**
     * Método que recebe o arquivo ShapeFile e verifica se o zip contém os arquivos com as seguintes extenções: .dbf .shx .shp .prj
     * @param arquivo
     * @return
     */
    public boolean validarConteudoDoArquivo(MultipartFile arquivo) {
        List<String> nomesArquivos = recuperarNomeArquivosConteudoZip(arquivo);
        if (nomesArquivos.stream()
                .mapToInt(value -> EXTENSAO_ARQUIVO_PATTERN.matcher(value).find() ? Constants.UM : Constants.ZERO)
                .sum() < NUMERO_ARQUIVO_SHAPEFILE) {
            return false;
        }
        return true;
    }


    private List<String> recuperarNomeArquivosConteudoZip(MultipartFile zip) {
        List<String> nomes = new ArrayList<>();
        try {
            if (!zip.isEmpty()) {
                ZipInputStream zis = new ZipInputStream(new BufferedInputStream(zip.getInputStream()));
                ZipEntry entry;

                while((entry = zis.getNextEntry()) != null) {
                    nomes.add(entry.getName());
                }
                zis.close();
            }
        } catch(IOException e) {
            LOGGER.error("ERRO AO DESCOMPACTAR O ARQUIVO COM OS SHAPES", e);
        }
        return nomes;
    }


    private List<File> gravarArquivosTemporarios(MultipartFile zip) {
        List<File> urls = new ArrayList<>();
        File tempDir = com.google.common.io.Files.createTempDir();

        try {

            if (!zip.isEmpty()) {
                ZipInputStream zis = new ZipInputStream(new BufferedInputStream(zip.getInputStream()));

                lerShapefileZip(urls, tempDir, zis);

                zis.close();
            }
        } catch(IOException e) {
            LOGGER.error("ERRO GRAVAR ARQUIVOS TEMPORARIOS", e);
        }

        return urls;
    }

    private void lerShapefileZip(List<File> urls, File tempDir, ZipInputStream zis) throws IOException {
        ZipEntry entry;
        BufferedOutputStream dest;
        while ((entry = zis.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                continue;
            }

            int count;
            byte [] data = new byte[Constants.DOIS_MIL_E_QUARENTA_E_OITO];

            File arquivoTemporario = new File(tempDir, entry.getName());
            FileOutputStream fos = new FileOutputStream(arquivoTemporario);
            dest = new BufferedOutputStream(fos, Constants.DOIS_MIL_E_QUARENTA_E_OITO);

            while ((count = zis.read(data, Constants.ZERO, Constants.DOIS_MIL_E_QUARENTA_E_OITO)) != Constants.MENOS_UM) {
                dest.write(data,Constants.ZERO, count);
            }

            urls.add(arquivoTemporario);
            dest.flush();
            fos.close();
            dest.close();
        }
    }


    private Geometry validarArquivoContemGeometriaPoligono(File file) throws NegocioException {
        Geometry geometry = null;

        try {
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();
            geometry = verificarGeometriaValida(featureSource);
        } catch (IOException e) {
            LOGGER.error("ERRO VALIDAR GEOMETRIA", e);
        }

        return geometry;
    }

    private Geometry verificarGeometriaValida(SimpleFeatureSource featureSource) throws IOException, NegocioException {
        Geometry geometry = null;
        SimpleFeatureIterator iterator = null;
        try  {
            iterator = featureSource.getFeatures().features();
            if (iterator != null) {
                while (iterator.hasNext()) {
                    SimpleFeature feature = iterator.next();
                    geometry = (Geometry) feature.getAttribute(Constants.ZERO);
                    String geometryType = geometry.getGeometryType();
                    validarTipoGeometria(geometry, geometryType);
                }
            }
        } finally {
            closeFeatureIteratorIterator(iterator);
        }
        return geometry;
    }

    private void closeFeatureIteratorIterator(SimpleFeatureIterator iterator) {
        if (iterator != null) {
            iterator.close();
        }
    }

    private void validarTipoGeometria(Geometry geometry, String geometryType) throws NegocioException {
        if (!geometry.isValid() && !validarPolygon(geometryType)) {
            throw new NegocioException("Erro na Validação da Geometria.");
        }
    }

    private boolean validarPolygon(String geometryType) {
        return "MultiPolygon".equalsIgnoreCase(geometryType)
                || "Polygon".equalsIgnoreCase(geometryType);
    }

    private String geraMemorialDescritivoCartorial(Geometry geometry) {
        String memorial;
        final HashMap<String, Object> model = new HashMap<>();
        model.put("geometry", geometry);
        memorial = VelocityEngineUtils
                .mergeTemplateIntoString(velocityEngine, "/templates/memorial-cadastral.vm", "UTF-8", model);
        return memorial;
    }

    /**
     * Método que ler o arquivo ShapeFile e retorna as coordenadas para que seja preenchido o memorial descritivo
     * @param file
     * @throws NegocioException
     * @return
     */
    public String montarArquivoShapeFile(MultipartFile file) throws NegocioException {
        List<File> files = gravarArquivosTemporarios(file);
        Optional<File> urlShp = files.stream().filter(u -> u.getName().contains(".shp")).findFirst();
        Geometry geometry = validarArquivoContemGeometriaPoligono(urlShp.get());
        return geraMemorialDescritivoCartorial(geometry);
    }

    /**
     * Efetua upload de arquivos para o cadastro de imoveis
     * @param multipartFile
     * @param descricao
     * @param data
     * @return
     * @throws IOException
     */
    public Arquivo salvarFotoVideo(MultipartFile multipartFile, String descricao, String data) throws IOException, ParseException {

        Arquivo arquivo = criarArquivo(multipartFile);
        arquivo.setDescricao(descricao);
        arquivo.setNomeReal(multipartFile.getOriginalFilename());
        arquivo.setData(DataUtil.stringToDate(data,"yyyy-MM-dd"));
        if (ArquivoUtils.salvar(multipartFile.getBytes(), arquivo.getDiretorioArquivo(), arquivo.getNome())) {
            byte[] imagens = getByteFileUpload(multipartFile, arquivo);
            Arquivo arquivoSaved = arquivoRepository.save(arquivo);
            arquivoSaved.setImagem(imagens);
            return arquivoSaved;
        } else {
            throw new IOException(ERRO_SALVAR_ARQUIVO);
        }
    }

    private byte[] getByteFileUpload(MultipartFile multipartFile, Arquivo arquivo) throws IOException {
        byte[] imagens;
        if(ArquivoUtils.isVideo(arquivo.getExtensao())){
            imagens = ArquivoUtils.converteInputStreamToByte(getClass().getResourceAsStream("/imagens/play.png"));
        }else{
            imagens = multipartFile.getBytes();
        }
        return imagens;
    }

    /**
     * Busca arquivos pelos ids.
     * @param ids
     * @return
     */
    @Transactional(readOnly = true)
    public List<Arquivo> buscarArquivos(List<Long> ids) {
        return arquivoRepository.buscarArquivos(ids);
    }

    /**
     * Apaga os arquivos que nao estão em uso.
     * @param arquivoDTOs
     * @throws NegocioException
     */
    public void remover(List<ArquivoDTO> arquivoDTOs) throws NegocioException {
        for (ArquivoDTO arquivoDTO : arquivoDTOs) {
            remover(arquivoDTO.getId());
        }
    }

    /**
     * Prepara os arquivos de video e foto para exibição na tela.
     * @param arquivoDTOs
     * @return
     * @throws IOException
     */
    public List<ArquivoDTO> prepararListaFotoVideo(List<ArquivoDTO> arquivoDTOs) throws IOException {
        List<ArquivoDTO> arquivosPreparados = new ArrayList<>();
        for (ArquivoDTO arquivoDTO : arquivoDTOs) {
            if (ArquivoUtils.isVideo(arquivoDTO.getExtensao())) {
                arquivoDTO.setImagem(ArquivoUtils.converteInputStreamToByte(getClass().getResourceAsStream("/imagens/play.png")));
            } else {
                String arquivoDiretorio = arquivoDTO.getDiretorioArquivo() + arquivoDTO.getNome();
                InputStream inputStream = new FileInputStream(arquivoDiretorio);
                arquivoDTO.setImagem(ArquivoUtils.converteInputStreamToByte(inputStream));
            }
            arquivosPreparados.add(arquivoDTO);
        }
        return arquivosPreparados;
    }


}
