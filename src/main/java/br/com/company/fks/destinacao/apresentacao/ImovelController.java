package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ConsultaImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.ImovelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by Basis Tecnologia on 06/10/2016.
 */

/**
 * Classe que conterá a comunicação rest de imovel
 */
@RestController
@RequestMapping(value = "imovel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImovelController {

    private static final Logger LOGGER = Logger.getLogger(ImovelController.class);
    private static final String ERRO_VALIDAR_RIP = "ERRO VALIDAR RIP";
    private static final String RIP = "rip";
    private static final String SEQUENCIA_PARCELA ="sequenciaParcela";
    private static final String CODIGO_UTILIZACAO ="codigoUtilizacao";
    private static final String TIPO_DESTINACAO = "tipoDestinacao";
    public static final int VALOR_IMOVEL = 1000000;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private EntityConverter entityConverter;


    /**
     * Consulta imovel apenas por rip
     * @param consultaImovelDTO
     * @return
     */
    @RequestMapping(value = "consultarPorRip", method = RequestMethod.POST)
    public ResponseEntity<Resposta<ImovelDTO>> consultarPorRip(@RequestBody ConsultaImovelDTO consultaImovelDTO) {
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ImovelDTO> resposta;

        try {

            resposta = RespostaBuilder.getBuilder().setResultado(imovelService.consultarImovelRipValidando(consultaImovelDTO)).build();
        } catch (IntegracaoException | NegocioException e) {
            LOGGER.error(ERRO_VALIDAR_RIP, e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    @RequestMapping(value = "consultarPorRipCUEM/{rip}/{codigoUtilizacao}/{sequenciaParcela}/{tipoDestinacao}/{idModalidade}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<ImovelDTO>> consultarPorRipCUEM(@PathVariable(RIP) String rip,
                                                                   @PathVariable(CODIGO_UTILIZACAO) String codigoUtilizacao,
                                                                   @PathVariable(SEQUENCIA_PARCELA) String sequenciaParcela,
                                                                   @PathVariable(TIPO_DESTINACAO) String tipoDestinacao,
                                                                   @PathVariable("idModalidade") Long idModalidade){
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ImovelDTO> resposta;

        try {
            resposta = RespostaBuilder.getBuilder().setResultado(imovelService.consultarImovel(rip,codigoUtilizacao,sequenciaParcela, tipoDestinacao)).build();
        }catch (NegocioException e){
            LOGGER.error(ERRO_VALIDAR_RIP, e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    @RequestMapping(value = "consultarDadosPosseInformal/{rip}/{codigoUtilizacao}/{parcela}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<ImovelDTO>> buscarDadosPosseInformal(@PathVariable(RIP) String rip,
                                                                        @PathVariable("codigoUtilizacao") String codigoUtilizacao,
                                                                        @PathVariable("parcela") String parcela) {
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ImovelDTO> resposta;
        try{
            ImovelDTO imovelDTO = imovelService.consultarDadosPosseInformal(rip,codigoUtilizacao,parcela);
            resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();
        }catch (IntegracaoException e){
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    /**
     *
     * @param rip
     * @param codigoUtilizacao
     * @param parcela
     * @param tipoDestinacao
     * @return
     */
    @RequestMapping(value = "consultarPorRipEDestinacao/{rip}/{codigoUtilizacao}/{parcela}/{tipoDestinacao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<ImovelDTO>> buscarRipCodigoUtilizacaoParcelaTipoDestinacao(@PathVariable(RIP) String rip,
                                                                                              @PathVariable("codigoUtilizacao") String codigoUtilizacao,
                                                                                              @PathVariable("parcela") String parcela,
                                                                                              @PathVariable("tipoDestinacao") String tipoDestinacao) {
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ImovelDTO> resposta;
        try{
            ImovelDTO imovelDTO = imovelService.consultarImovel(rip, codigoUtilizacao, parcela, tipoDestinacao);
            resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();
        } catch (NegocioException e) {
            LOGGER.error("ERRO CONSULTAR RIP >>> ", e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    @RequestMapping(value = "consultarPorRipEParcela", method = RequestMethod.POST)
    public ResponseEntity<Resposta<ImovelDTO>> buscarRipParcelaTipoDestinacao(@RequestBody ConsultaImovelDTO consultaImovelDTO) throws IntegracaoException {
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ImovelDTO> resposta;
        try{
            ImovelDTO imovelDTO = imovelService.consultarImovelRipParcela(consultaImovelDTO);
            imovelService.buscarListaSuspensoesImovelPorRip(consultaImovelDTO);
            resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();
        } catch (NegocioException e) {
            LOGGER.error("ERRO CONSULTAR RIP >>> ", e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    /**
     *
     * @param rip
     * @param cep
     * @param uf
     * @param municipio
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "consultarDestinacao", method = RequestMethod.GET)
    public ResponseEntity<Resposta<Page<ImovelDTO>>> consultarDestinacao(@RequestParam(value = RIP, required=false) String rip,
                                                                               @RequestParam(value = "cep", required=false) String cep,
                                                                               @RequestParam(value = "uf") String uf,
                                                                               @RequestParam(value = "municipio", required=false) String municipio,
                                                                               @RequestParam(value = "offset") int offset,
                                                                               @RequestParam(value = "limit") int limit){

        Resposta<Page<ImovelDTO>> resposta;
        Page<ImovelDTO> imovelDestinado = imovelService.consultarDestinacao(rip, cep, uf, municipio, offset, limit);
        resposta = RespostaBuilder.getBuilder().setResultado(imovelDestinado).build();

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param rip
     * @return
     */
    @RequestMapping(value = "buscarDadosRipUtilizacao/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<ImovelDTO>> buscarDadosRipUtilizacao(@PathVariable("rip") String rip) {

        Imovel imovelDestinado = imovelService.buscarDadosRipUtilizacao(rip);

        ImovelDTO imovelDTO = entityConverter.converterStrict(imovelDestinado, ImovelDTO.class);

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }


    /**
     *
     * @param rip
     * @return
     */
    @RequestMapping(value = "buscarDadosBenfeitorias/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<ImovelDTO>> buscarDadosBenfeitorias(@PathVariable("rip") String rip) {

        Imovel imovelDestinado = imovelService.buscarDadosBenfeitorias(rip);

        ImovelDTO imovelDTO = entityConverter.converterStrict(imovelDestinado, ImovelDTO.class);

        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }


    /**
     *
     * @param rip rip do imovel para consulta
     * @param tipoDestinacao qual tipo de destinacao está atrelada
     * @return
     */
    @RequestMapping(value = "consultarPorRipEDestinacao/{rip}/{tipoDestinacao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<ImovelDTO>> consultarNumeroDestinacao(@PathVariable(RIP) String rip,
                                                                         @PathVariable(TIPO_DESTINACAO) String tipoDestinacao) {
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ImovelDTO> resposta;
        try{
            resposta = RespostaBuilder.getBuilder().setResultado(imovelService.consultarImovelRipPosseInformal(rip, tipoDestinacao)).build();
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(mensagemNegocio.get("imovel.validadar.rip")).build();
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    /**
     *
     * @param id do imovel que contem o valor da avaliacao
     * @return o omovel com esse valor de avaliacao
     */
    @RequestMapping(value = "buscarValorAvaliacao/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<ImovelDTO>> buscarValorAvaliacao(@PathVariable("id") Long id) {
        //Todo METODO MOCADO EFETUAR CONSULTA CORRETA QUANDO ESTIVAR IMPLENTADO A AVALIAÇÃO.
        ImovelDTO imovelDTO = new ImovelDTO();
        imovelDTO.setId(id);
        imovelDTO.setValorLaudo(new BigDecimal(VALOR_IMOVEL));
        Resposta<ImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(imovelDTO).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @param rip do imovel para ver se tem destinacao
     * @return o imovel com o status se lee possui ou não destinacao
     */
    @RequestMapping(value = "existe-destinacao-imovel/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<Boolean>> verificarImovelDestinado(@PathVariable(RIP) String rip) {
        Boolean imovelDestinado = imovelService.isImovelDestinado(rip);
        Resposta<Boolean> resposta = RespostaBuilder.getBuilder().setResultado(imovelDestinado).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @RequestMapping(value = "buscarPorRip/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<ImovelDTO>> buscarPorRip(@PathVariable(RIP) String rip) throws NegocioException, IntegracaoException{
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<ImovelDTO> resposta;

        try {

            resposta = RespostaBuilder.getBuilder().setResultado(imovelService.consultarImovelRip(rip,"AFETACAO")).build();
        } catch (NegocioException e) {
            LOGGER.error(ERRO_VALIDAR_RIP, e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }catch (IntegracaoException e) {
            LOGGER.error(ERRO_VALIDAR_RIP, e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }
}
