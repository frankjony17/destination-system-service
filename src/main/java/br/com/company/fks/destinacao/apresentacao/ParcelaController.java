package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ParcelaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.ParcelaService;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Basis Haillanderson on 09/03/2017.
 */

/**
 * Classe que conterá a comunicação rest de parcela
 */
@RestController
@RequestMapping(value = "parcela", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ParcelaController {

    private static final Logger LOGGER = Logger.getLogger(ParcelaController.class);

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private ParcelaService parcelaService;

    /**
     * Cria novas parcelas
     * @param parcelasDTO
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "salvar", method = RequestMethod.POST)
    public ResponseEntity<Resposta<String>> salvar(@RequestBody List<ParcelaDTO> parcelasDTO) {
        parcelaService.criarNovasParcelas(parcelasDTO);
        Resposta<String> resposta = RespostaBuilder.getBuilder().setMensagen(mensagemNegocio.get("parcela.salva.sucesso")).build();
        return new ResponseEntity<>(resposta,HttpStatus.CREATED);

    }

    /**
     * Atualiza a parcela
     * @param parcelaDTO
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "editar", method = RequestMethod.PUT)
    public ResponseEntity<Resposta<String>> editar(@RequestBody ParcelaDTO parcelaDTO) {

        Resposta<String> resposta;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            parcelaService.editar(parcelaDTO);
            resposta = RespostaBuilder.getBuilder().setMensagen("Dados alterados com sucesso").build();
        } catch (NegocioException e) {
            LOGGER.error("ERRO EDITAR PARCELA", e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(resposta, httpStatus);

    }

    /**
     * Realiza a exclusão de uma parcela (adicionando os dados da mesma em uma parcela escolhida e cancelando as duas criando uma nova parcela)
     * @param parcelas
     * @return
     */
    @RequestMapping(value = "excluir", method = {RequestMethod.POST})
    public ResponseEntity<Resposta<String>> excluirParcela(@RequestBody List<ParcelaDTO> parcelas) {
        ParcelaDTO parcelaExcluidaDTO = parcelas.get(Constants.ZERO);
        ParcelaDTO parcelaAtribuidaDTO = parcelas.get(Constants.UM);
        String rip = parcelaExcluidaDTO.getRip();
        Parcela parcelaExcluida = entityConverter.converterStrict(parcelaExcluidaDTO, Parcela.class);
        Parcela parcelaAtribuida = entityConverter.converterStrict(parcelaAtribuidaDTO, Parcela.class);
        parcelaService.excluirParcela(parcelaExcluida, parcelaAtribuida, rip);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "buscarParcelasSemUtilizacao/{rip}/{codigoUtilizacao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ParcelaDTO>>> buscarParcelasSemUtilizacao(@PathVariable("rip") String rip,
                                                                                  @PathVariable("codigoUtilizacao") String codigoUtilizacao) {
        List<ParcelaDTO> parcelaDTOs = parcelaService.buscarParcelasSemUso(rip, codigoUtilizacao);
        Resposta<List<ParcelaDTO>> resposta = RespostaBuilder.getBuilder().setResultado(parcelaDTOs).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @RequestMapping(value = "buscarParcelasInativas/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ParcelaDTO>>> buscarParcelasInativas(@PathVariable("rip") String rip) {
        List<ParcelaDTO> parcelaDTOs = parcelaService.buscarParcelasCanceladas(rip);
        Resposta<List<ParcelaDTO>> resposta = RespostaBuilder.getBuilder().setResultado(parcelaDTOs).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     * Atualiza as parcelas
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "salvarListaParcelas", method = RequestMethod.POST)
    public ResponseEntity<Resposta<String>> salvarListaParcelas(@RequestBody List<ParcelaDTO> parcelas) {

        Resposta<String> resposta;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            parcelaService.salvarListaParcelas(parcelas);
            resposta = RespostaBuilder.getBuilder().setMensagen("Dados alterados com sucesso").build();
        } catch (NegocioException e) {
            LOGGER.error("ERRO EDITAR PARCELAS", e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(resposta, httpStatus);

    }

    @RequestMapping(value = "buscarParcelas/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ParcelaDTO>>> buscarParcelas(@PathVariable("rip") String rip) {
        List<ParcelaDTO> parcelaDTOs = parcelaService.buscarParcelasPorIdImovel(rip);
        Resposta<List<ParcelaDTO>> resposta = RespostaBuilder.getBuilder().setResultado(parcelaDTOs).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }


}
