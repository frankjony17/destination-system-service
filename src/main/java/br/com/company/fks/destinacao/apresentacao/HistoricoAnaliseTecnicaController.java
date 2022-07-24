package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.HistoricoAnaliseTecnicaDTO;
import br.com.company.fks.destinacao.service.HistoricoAnaliseTecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by diego on 29/11/16.
 */

/**
 * Classe que conterá a comunicação rest dos históricos de analises tecnicas.
 */
@RestController
@PreAuthorize("hasAuthority('DESTINACAO_EXEC_ANALISE_TEC_TECNICO') " +
        "OR hasAuthority('DESTINACAO_EXEC_ANALISE_TEC_CHEFIA') " +
        "OR hasAuthority('DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE') " +
        "OR hasAuthority('DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO')")
@RequestMapping(value = "historicoAnaliseTecnica", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HistoricoAnaliseTecnicaController {

    @Autowired
    private HistoricoAnaliseTecnicaService historicoAnaliseTecnicaService;

    /**
     *
     * @param idAnaliseTecnica id da analise tecnica
     * @param offset
     * @param limit
     * @return a lista com o historico da analise tecnica
     */
    @RequestMapping(value = "{idAnaliseTecnica}/{offset}/{limit}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<Page<HistoricoAnaliseTecnicaDTO>>> getHistorico(@PathVariable("idAnaliseTecnica") Long idAnaliseTecnica,
                                                                                   @PathVariable(value = "offset") int offset,
                                                                                   @PathVariable(value = "limit") int limit) {
        Page<HistoricoAnaliseTecnicaDTO> historicosDtos =
                historicoAnaliseTecnicaService.findByAnaliseTecnicaId(idAnaliseTecnica, offset, limit);
        Resposta<Page<HistoricoAnaliseTecnicaDTO>> resposta = RespostaBuilder.getBuilder().setResultado(historicosDtos).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);

    }
}
