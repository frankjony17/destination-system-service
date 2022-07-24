package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.RequerimentoConsultaDTO;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.RequerimentoService;
import br.com.company.fks.destinacao.utils.EnumUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by raphael on 01/12/16.
 */

/**
 * Classe que conterá a comunicação rest de requerimentos
 */
@Controller
@RequestMapping(value = "/requerimento")
public class RequerimentoController {

    private static final Logger LOGGER = Logger.getLogger(RequerimentoController.class);

    @Autowired
    private RequerimentoService requerimentoService;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    /**
     *
     * @param id do requerimento que será buscado
     * @return o requerimento com atrelado aquele id
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Resposta> buscarRequerimento(@PathVariable(value = "id") Long id) {
        Resposta resposta;

        try {
            Object requerimento = requerimentoService.buscarRequerimento(id);
            resposta = RespostaBuilder.getBuilder().setResultado(requerimento).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } catch (IntegracaoException e){
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }
    }

    /**
     *
     * @param requerimentoConsultaDTO os dados para consultar o requerimento
     * @return o resultado que casam com os parametros de pesquisa
     */
    @RequestMapping(value = "/consultar/analise-tecnica", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resposta> consultaRequerimentoEAnaliseTecnica(RequerimentoConsultaDTO requerimentoConsultaDTO){

        Object pesquisar = requerimentoService.consultaRequerimentoEAnaliseTecnica(requerimentoConsultaDTO);
        Resposta resposta = RespostaBuilder.getBuilder().setResultado(pesquisar).build();


        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param id da analise tenica
     * @return retorna o requerimento atrelado ao id da analise tecnicafv
     */
    @RequestMapping(value = "requerimento-analise-tecnica/{id}")
    public ResponseEntity<Resposta> buscarAnaliseTecnicaRequerimento(@PathVariable(value = "id") Long id) {
        Resposta resposta;
        try {
            Object requerimento = requerimentoService.buscarAnaliseTecnicaRequerimento(id);
            resposta = RespostaBuilder.getBuilder().setResultado(requerimento).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } catch (IntegracaoException e){
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }
    }


    /**
     *
     * @param idRequerimento id do requerimento a ser buscado
     * @param offset
     * @param limit
     * @return retorna o requerimento atrelado ao id
     */
    @RequestMapping(value = "requerimento-pendencia/{idRequerimento}")
    public ResponseEntity<Resposta> buscarPendenciaRequerimento(@PathVariable(value = "idRequerimento") Long idRequerimento,
                                                                @RequestParam(value = "offset") int offset,
                                                                @RequestParam(value = "limit") int limit) {
        Resposta resposta;
        try {
            Object requerimento = requerimentoService.buscarPendenciaRequerimento(idRequerimento, offset, limit);
            resposta = RespostaBuilder.getBuilder().setResultado(requerimento).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }
    }

    /**
     *
     * @param status recebe o novo status do requerimento
     * @param idRequerimento o id do requerimento que tera o status alterado
     * @return status da alteraçaõ do status
     */
    @RequestMapping(value = "alterar-status/{status}/{idRequerimento}", method = RequestMethod.PUT)
    public ResponseEntity alterarStatusRequerimento(@PathVariable(value = "status") String status,
                                          @PathVariable(value = "idRequerimento") Long idRequerimento) {
        requerimentoService.alterarStatusRequerimento(idRequerimento, status);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     *
     * @return lista todos os titulos de servicos no banco
     */
    @RequestMapping(value = "/listar-titulo-servicos")
    public ResponseEntity<Resposta> listarTitulosServico() {
        Resposta resposta;
        try {
            Object lista = requerimentoService.listarTitulosServico();
            resposta = RespostaBuilder.getBuilder().setResultado(lista).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } catch (IntegracaoException e){
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }
    }

    /**
     * Busca todos status AnaliseTecnica
     * @return lista de todos os status análise técnica
     */
    @RequestMapping(value = "/buscar-tipo-status-analise-tecnica")
    public ResponseEntity<Resposta> buscarTodosStatusAnaliseTecnica(){
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(EnumUtils.buscarEnumChaveValor(StatusAnaliseTecnicaEnum.class)).build(), HttpStatus.OK);
    }

}
