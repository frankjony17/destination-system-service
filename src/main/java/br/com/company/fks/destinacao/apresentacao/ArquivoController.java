package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.ArquivoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import com.google.common.io.Files;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */

/**
 * Classe que conterá a comunicação rest dos Arquivos
 */
@RestController
@RequestMapping(value = "arquivo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArquivoController {

    private static final Logger LOGGER = Logger.getLogger(ArquivoController.class);
    private static final String ZIP = "zip";
    public static final String FILE = "file";

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private MensagemNegocio mensagemNegocio;


    /**
     * @param file que será persistido
     * @return flag com o sucesso ou o erro da persistencia
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResponseEntity<Resposta<ArquivoDTO>> upload(@RequestParam("file") MultipartFile file) {
        return salvar(file);
    }

    /**
     * Método que realiza o upload de shapefile e outros arquivos
     * @param file
     * @throws NegocioException
     * @return
     */
    @RequestMapping(value = "uploadComShapeFile", method = RequestMethod.POST)
    public ResponseEntity<Resposta<ArquivoDTO>> uploadShapeFile(@RequestParam(FILE) MultipartFile file) throws NegocioException {
        ResponseEntity<Resposta<ArquivoDTO>> respostaResponseEntity  = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(Files.getFileExtension(file.getOriginalFilename()).equalsIgnoreCase(ZIP)){
            if(arquivoService.validarConteudoDoArquivo(file)){
                respostaResponseEntity = salvar(file);
                respostaResponseEntity.getBody().getResultado().setCoordenadas(arquivoService.montarArquivoShapeFile(file));
                return respostaResponseEntity;
            }
            return respostaResponseEntity;
        }
        else{
            respostaResponseEntity = salvar(file);
            return respostaResponseEntity;
        }
    }

    /**
     * @param response
     * @param id       id do arquivo que será feito o download
     */
    @RequestMapping(value = "/downloadArquivoRequerimento/{id}", method = RequestMethod.GET)
    @SneakyThrows
    public void downloadArquivoRequerimento(HttpServletResponse response, @PathVariable("id") Long id) {
        arquivoService.downloadArquivoModuloServicos(id, response);
    }

    /**
     * @param response
     * @param arquivo
     */
    @RequestMapping(value = "/downloadArquivoRequerimentoGestaoPraia/{arquivo}", method = RequestMethod.GET)
    @SneakyThrows
    public void downloadArquivoRequerimentoPraia(HttpServletResponse response, @PathVariable("arquivo") String arquivo) {
        arquivoService.downloadArquivoModuloServicosGestaoPraia(arquivo, response);
    }

    /**
     * @param idRequerimento id do requerimento que será buscado
     * @param idDocumento    id do arquivo contido no requerimento
     * @return uma lista de arquivos contidos em um determinado requerimento
     * @throws IntegracaoException
     */
    @RequestMapping(value = "/listarArquivosRequerimento/{idRequerimento}/{idDocumento}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ArquivoDTO>>> listarArquivosRequerimento(@PathVariable("idRequerimento") Long idRequerimento,
                                                                                 @PathVariable("idDocumento") Long idDocumento) throws IntegracaoException {
        Resposta<List<ArquivoDTO>> resposta;
        try {
            List<ArquivoDTO> arquivoDTOs = arquivoService.listarArquivosRequerimentos(idRequerimento, idDocumento);
            resposta = RespostaBuilder.getBuilder().setResultado(arquivoDTOs).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param id       do arquivo que será buscado
     * @param response
     * @return o arquivo resultante da busca
     */
    @RequestMapping(value = "/baixarArquivo/{id}", method = RequestMethod.GET)
    public ResponseEntity baixarArquivo(@PathVariable("id") Long id, HttpServletResponse response) {
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            arquivoService.baixarArquivo(id, response);
        } catch (RuntimeException | IOException e) {
            LOGGER.error("ERRO BAIXAR ARQUIVO", e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }

    /**
     * @param id do arquivo que será removido
     * @return o status da operação, se deu sucesso ou erro
     */
    @RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remover(@PathVariable("id") Long id) {
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            arquivoService.remover(id);
        } catch (NegocioException e) {
            LOGGER.error(mensagemNegocio.get("erro.remover.arquivo"), e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @RequestMapping(value = "/removerAtoComplementar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removerAtoComplementar(@PathVariable("id") Long id) {
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            arquivoService.removerAtosComplementares(id);
        } catch (NegocioException e) {
            LOGGER.error(mensagemNegocio.get("erro.remover.arquivo"), e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }

    private ResponseEntity<Resposta<ArquivoDTO>> salvar(MultipartFile file) {
        Resposta<ArquivoDTO> resposta;
        try {
            Arquivo arquivo = arquivoService.salvar(file);
            arquivo.setBytes();
            ArquivoDTO arquivoDTO = entityConverter.converterStrict(arquivo, ArquivoDTO.class);
            resposta = RespostaBuilder.getBuilder().setResultado(arquivoDTO).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);

        } catch (RuntimeException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(mensagemNegocio.get("erro.arquivo.salvar")).build();
            return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Salva Fotos/Videos no cadastro de imoveis.
     * @param file
     * @return
     */
    @RequestMapping(value = "uploadFotoVideo/{descricao}/{data}", method = RequestMethod.POST)
    public ResponseEntity<Resposta<ArquivoDTO>> uploadFotoVideo(@RequestParam(FILE) MultipartFile file,
                                                                @PathVariable("descricao") String descricao,
                                                                @PathVariable("data") String data) {

        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ArquivoDTO> resposta;
        try {
            Arquivo arquivo = arquivoService.salvarFotoVideo(file, descricao, data);
            ArquivoDTO arquivoDTO = entityConverter.converterStrict(arquivo, ArquivoDTO.class);
            resposta = RespostaBuilder.getBuilder().setResultado(arquivoDTO).build();
        } catch (ParseException | IOException e) {
            LOGGER.error("ERRO SALVAR IMAGENS", e);
            httpStatus = HttpStatus.BAD_REQUEST;
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    @RequestMapping(value="uploadNovo/{descricao}/{data}", method = RequestMethod.POST)
    public ResponseEntity<Resposta<ArquivoDTO>> uploadNovo(@RequestParam(FILE) MultipartFile file,
                                                           @PathVariable("descricao") String descricao,
                                                           @PathVariable("data") String data) {
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ArquivoDTO> resposta;
        try {
            Arquivo arquivo = arquivoService.salvarNovo(file, descricao, data);
            ArquivoDTO arquivoDTO = entityConverter.converterStrict(arquivo, ArquivoDTO.class);
            resposta = RespostaBuilder.getBuilder().setResultado(arquivoDTO).build();
        }catch (RuntimeException e){
            LOGGER.error("ERRO AO SALVAR ARQUIVO", e);
            httpStatus = HttpStatus.BAD_REQUEST;
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

}
