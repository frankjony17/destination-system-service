package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.PublicacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.AnaliseTecnicaService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * Created by Basis Tecnologia on 06/10/2016.
 */

/**
 * Classe que conterá a comunicação rest de analise técnica
 */
@RestController
@PreAuthorize("hasAuthority('DESTINACAO_EXEC_ANALISE_TEC_TECNICO') " +
        "OR hasAuthority('DESTINACAO_EXEC_ANALISE_TEC_CHEFIA') " +
        "OR hasAuthority('DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE') " +
        "OR hasAuthority('DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO')")
@RequestMapping(value = "/analise-tecnica")
public class AnaliseTecnicaController {

    private static final Logger LOGGER = Logger.getLogger(AnaliseTecnicaController.class);

    @Autowired
    private AnaliseTecnicaService analiseTecnicaService;

    @Autowired
    private EntityConverter entityConverter;

    /**
     *
     * @param dto de Analise técnica que estará vindo do front-end
     * @return um objeto do tipo resposta contendo o objeto persistido
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<AnaliseTecnica>> salvar(@RequestBody AnaliseTecnicaDTO dto) {
        Resposta<AnaliseTecnica> resposta;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(
                    analiseTecnicaService.salvar(dto)).build();
        } catch (RuntimeException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param dto de Analise técnica que estará vindo do front-end
     * @return um objeto do tipo resposta contendo o objeto persistido
     */
    @RequestMapping(value = "/salvarRascunho", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<AnaliseTecnica>> salvarRascunho(@RequestBody AnaliseTecnicaDTO dto) {
        Resposta<AnaliseTecnica> resposta;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(
                    analiseTecnicaService.salvarRascunho(dto)).build();
        } catch (RuntimeException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param id do objeto de tipo Analise Técnica para ser buscado
     * @return o objeto resposta contendo o respectivo item da procura
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Resposta<AnaliseTecnica>> buscarPorId(@PathVariable Long id){

        Resposta<AnaliseTecnica> resposta;
        try {
            AnaliseTecnicaDTO analiseTecnica = analiseTecnicaService.buscarPorId(id);
            resposta = RespostaBuilder.getBuilder().setResultado(analiseTecnica).build();
        } catch (NegocioException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param requerimentoId recebe o Id do requerimento
     * @return retorna todas as analises atreladas aquele requerimento
     */
    @RequestMapping(value = "/requerimento/{requerimentoId}")
    public ResponseEntity<Resposta<AnaliseTecnica>> buscarAnalisePorRequerimento(@PathVariable Long requerimentoId){

        Resposta<AnaliseTecnica> resposta;
        try {
            AnaliseTecnicaDTO analiseTecnica = analiseTecnicaService.buscarPorIdRequerimento(requerimentoId);
            resposta = RespostaBuilder.getBuilder().setResultado(analiseTecnica).build();
        } catch (NegocioException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param analiseTecnicaDTO recebe um objeto do tipo Analise Tecnica
     * @return uma flag com a sucesso ou erro de publicar no diario oficial
     */
    @RequestMapping(value = "/registrarPublicacaoDiarioUniao", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<PublicacaoDTO>> registrarPublicacaoDiarioUniao(@RequestBody AnaliseTecnicaDTO analiseTecnicaDTO) {
        AnaliseTecnica analiseTecnica = entityConverter.converterStrict(analiseTecnicaDTO, AnaliseTecnica.class);
        analiseTecnica = analiseTecnicaService.registrarPublicacaoDiarioUniao(analiseTecnica.getId(), analiseTecnica.getPublicacao());
        PublicacaoDTO publicacao = entityConverter.converterStrict(analiseTecnica.getPublicacao(), PublicacaoDTO.class);
        Resposta<PublicacaoDTO> resposta = RespostaBuilder.getBuilder().setResultado(publicacao).build();
        return new ResponseEntity(resposta, HttpStatus.OK);
    }



}
