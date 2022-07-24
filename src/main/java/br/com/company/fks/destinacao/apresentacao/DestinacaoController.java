package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.CdruDTO;
import br.com.company.fks.destinacao.dominio.dto.CessaoGratuitaDTO;
import br.com.company.fks.destinacao.dominio.dto.CessaoOnerosaDTO;
import br.com.company.fks.destinacao.dominio.dto.ConsultaDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.ConsultarDadosUtilizacaoAvaliacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.ConsultarUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.CuemDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.EncargoDTO;
import br.com.company.fks.destinacao.dominio.dto.DoacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.FiltroDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.PendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.PermissaoUsoImovelFuncionalDTO;
import br.com.company.fks.destinacao.dominio.dto.PosseInformalDTO;
import br.com.company.fks.destinacao.dominio.dto.TermoEntregaDTO;
import br.com.company.fks.destinacao.dominio.dto.TransferenciaTitularidadeDTO;
import br.com.company.fks.destinacao.dominio.dto.UsoProprioDTO;
import br.com.company.fks.destinacao.dominio.dto.VendaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Cdru;
import br.com.company.fks.destinacao.dominio.entidades.CessaoGratuita;
import br.com.company.fks.destinacao.dominio.entidades.CessaoOnerosa;
import br.com.company.fks.destinacao.dominio.entidades.Cuem;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.PermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TermoEntrega;
import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import br.com.company.fks.destinacao.dominio.entidades.UsoProprio;
import br.com.company.fks.destinacao.dominio.entidades.Venda;
import br.com.company.fks.destinacao.dominio.enums.StatusDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import br.com.company.fks.destinacao.service.EncargoService;
import br.com.company.fks.destinacao.service.OcupanteService;
import br.com.company.fks.destinacao.service.destinacao.DestinacaoService;
import br.com.company.fks.destinacao.service.destinacao.DestinacaoStrategy;
import br.com.company.fks.destinacao.service.destinacao.SemUtilizacaoService;
import br.com.company.fks.destinacao.service.destinacao.UsoProprioService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.EnumUtils;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

/**
 * Classe que conterá a comunicação rest de destinação
 */
@RestController
@RequestMapping(value = "destinacao", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DestinacaoController {

    private static final Logger LOGGER = Logger.getLogger(DestinacaoController.class);
    private static final String ERRO_SALVAR_DESTINACAO = "ERRO SALVAR DESTINACAO";
    private static final String SALVAR = "SALVAR";
    private static final String HOMOLOGAR_USO_PROPRIO = "HOMOLOGAR_USO_PROPRIO";
    private static final String RECUSAR = "RECUSAR";

    @Autowired
    private DestinacaoStrategy destinacaoStrategy;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private UsoProprioService usoProprioService;

    @Autowired
    private SemUtilizacaoService semUtilizacaoService;

    @Autowired
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Autowired
    private EncargoService encargoService;

    @Autowired
    private OcupanteService ocupanteService;

    private static final String ERRO_CONSULTAR_DESTINACAO = "ERRO CONSULTAR DESTINACAO";


    /**
     *
     * @param doacaoDTO recebe uma destinação do tipo doacao
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "doacao", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarDoacao(@RequestBody DoacaoDTO doacaoDTO) {
        Doacao doacao = entityConverter.converterStrict(doacaoDTO, Doacao.class);
        return salvarDadosDestinacao(doacao);

    }

    /**
     *
     * @param vendaDTO recebe uma destinação do tipo venda
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "venda", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarVenda(@RequestBody VendaDTO vendaDTO) {
        Venda venda = entityConverter.converterStrict(vendaDTO, Venda.class);
        return salvarDadosDestinacao(venda);
    }

    /**
     *
     * @param posseInformalDTO recebe uma destinação do tipo posse informal
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "posse-informal", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarPosseInformal(@RequestBody PosseInformalDTO posseInformalDTO) {
        PosseInformal posseInformal = entityConverter.converterStrict(posseInformalDTO, PosseInformal.class);
        posseInformal.setDestinacoes(null);
        return salvarDadosDestinacao(posseInformal);
    }

    /**
     *
     * @param cuemDTO recebe uma destinação do tipo cuem
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "cuem", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarCuem(@RequestBody CuemDTO cuemDTO) {
        Cuem cuem = entityConverter.converterStrict(cuemDTO, Cuem.class);
        return salvarDadosDestinacao(cuem);
    }

    /**
     *
     * @param cdruDTO recebe uma destinação do tipo cdru
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "cdru", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarCdru(@RequestBody CdruDTO cdruDTO) {
        Cdru cdru = entityConverter.converterStrict(cdruDTO, Cdru.class);
        return salvarDadosDestinacao(cdru);

    }

    /**
     *
     * @param termoEntregaDTO recebe uma destinação do tipo termo entrega
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "termo-entrega", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarTermoEntrega(@RequestBody TermoEntregaDTO termoEntregaDTO) {
        TermoEntrega termoEntrega = entityConverter.converterStrict(termoEntregaDTO, TermoEntrega.class);
        return salvarDadosDestinacao(termoEntrega);
    }

    /**
     *
     * @return todos os tipos de destinações existentes no banco
     */
    @RequestMapping(value = "buscar-tipo-destinacao", method = RequestMethod.GET)
    public ResponseEntity<Resposta<TipoDestinacaoEnum>> recuperarTiposDestincacao() {
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(EnumUtils.buscarEnumCodigoDescricao(TipoDestinacaoEnum.class)).build(), HttpStatus.OK);
    }

    /**
     *
     * @param cessaoGratuitaDTO recebe uma destinação do tipo cessão gratuida
     * @return retorna o doacao persistida
     */
    @RequestMapping(value = "cessaoGratuita", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarCessaoGratuita(@RequestBody CessaoGratuitaDTO cessaoGratuitaDTO) {
        CessaoGratuita cessaoGratuita = entityConverter.converterStrict(cessaoGratuitaDTO, CessaoGratuita.class);
        return salvarDadosDestinacao(cessaoGratuita);
    }

    /**
     * Método responsável por preparar o salvamento destinção do tipo uso proprio.
     * @param usoProprioDTO
     * @return Uma resposta com a destinação salva.
     */
    @RequestMapping(value = "usoProprio", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarUsoProprio(@RequestBody UsoProprioDTO usoProprioDTO){
        UsoProprio usoProprio = entityConverter.converterStrict(usoProprioDTO, UsoProprio.class);
        return salvarDadosDestinacao(usoProprio);
    }

    /**
     * Método responsável por preparar o salvamento destinacao do tipo Permissao de uso de imovel Funcional.
     * @param permissaoUsoImovelFuncionalDTO
     * @return Uma resposta com a destinação salva
     */
    @RequestMapping(value = "permissaoUsoImovelFuncional", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarPermissaoUsoImovelInformal(@RequestBody PermissaoUsoImovelFuncionalDTO permissaoUsoImovelFuncionalDTO){
        PermissaoUsoImovelFuncional permissaoUsoImovelFuncional = entityConverter.converterStrict(permissaoUsoImovelFuncionalDTO, PermissaoUsoImovelFuncional.class);
        return salvarDadosDestinacao(permissaoUsoImovelFuncional);
    }

    @RequestMapping(value = "transferencia", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarTransferencia(@RequestBody TransferenciaTitularidadeDTO transferenciaTitularidadeDTO){
        TransferenciaTitularidade transferenciaTitularidade = entityConverter.converterStrict(transferenciaTitularidadeDTO, TransferenciaTitularidade.class);
        return salvarDadosDestinacao(transferenciaTitularidade);
    }

    /**
     * Método responsavel por preparar o salvamento
     * @param cessaoOnerosaDTO
     * @return uma resposta de destinação salva
     */
    @RequestMapping(value = "cessaoOnerosa", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> salvarCessaoOnerosa(@RequestBody CessaoOnerosaDTO cessaoOnerosaDTO){
        CessaoOnerosa cessaoOnerosa = entityConverter.converterStrict(cessaoOnerosaDTO, CessaoOnerosa.class);
        return salvarDadosDestinacao(cessaoOnerosa);
    }

    /**
     * Método responsável por preparar o salvamento da homologação de uso próprio.
     * @param usoProprioDTO
     * @return Uma resposta com a destinação de uso próprio homologada.
     */
    @RequestMapping(value = "homologarUsoProprio", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> homologarUsoProprio(@RequestBody UsoProprioDTO usoProprioDTO){
        UsoProprio usoProprio = entityConverter.converterStrict(usoProprioDTO, UsoProprio.class);

        usoProprio.setStatusDestinacao(this.obterStatusDestinacao(StatusDestinacaoEnum.ATIVO));
        usoProprio.setHomologado(Boolean.TRUE);
        this.removerPendenciaDeHomologacao(usoProprio);
        return this.salvarHomologarUsoProprio(usoProprio);

    }

    /**
     * Método responsável por preparar o salvamento da recusa de uso prórprio.
     * @param usoProprioDTO
     * @return Uma resposta com a destinação de uso próprio recusada.
     */
    @RequestMapping(value = "recusarUsoProprio", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Destinacao>> recusarUsoProprio(@RequestBody UsoProprioDTO usoProprioDTO){
        UsoProprio usoProprio = entityConverter.converterStrict(usoProprioDTO, UsoProprio.class);

        usoProprio.setStatusDestinacao(this.obterStatusDestinacao(StatusDestinacaoEnum.CANCELADO));
        this.removerPendenciaDeHomologacao(usoProprio);
        return salvarRecusarUsoProprio(usoProprio);
    }

    /**
     * Método responsável por solicitar o salvamento da destinação.
     * @param destinacao
     * @return A resposta com a destinação salva.
     */
    private ResponseEntity<Resposta<Destinacao>> salvarDadosDestinacao(Destinacao destinacao){
        return this.salvarDados(destinacao, mensagemNegocio.get("destinacao.salvar"), SALVAR);
    }

    /**
     * Método responsável por solicitar o salvamento da homologação de uso próprio.
     * @param destinacao
     * @return A resposta com a destinação de uso próprio homologada.
     */
    private ResponseEntity<Resposta<Destinacao>> salvarHomologarUsoProprio(Destinacao destinacao){
        return this.salvarDados(destinacao, mensagemNegocio.get("uso.proprio.homologado"), HOMOLOGAR_USO_PROPRIO);
    }

    /**
     * Método responsável por solicitar o salvamento da recusa de uso próprio.
     * @param destinacao
     * @return A resposta com a destinação de uso próprio recusada.
     */
    private ResponseEntity<Resposta<Destinacao>> salvarRecusarUsoProprio(Destinacao destinacao){
        return this.salvarDados(destinacao, mensagemNegocio.get("uso.proprio.recusado"), RECUSAR);
    }

    /**
     * Método responsável por salvar dados de destinação.
     * @param destinacao
     * @param msgSucesso
     * @return A resposta com a destinação salva.
     */
    private ResponseEntity<Resposta<Destinacao>> salvarDados(Destinacao destinacao, String msgSucesso, String acao){
        DestinacaoService destinacaoService = destinacaoStrategy.createBean(destinacao.getTipoDestinacaoEnum());
        Resposta<Destinacao> resposta;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Object retorno;
            if(acao.equals(HOMOLOGAR_USO_PROPRIO)){
                retorno = destinacaoService.homologar(destinacao);
            } else {
                retorno = destinacaoService.salvar(destinacao);
            }
            resposta = RespostaBuilder.getBuilder().setResultado(retorno).setMensagen(msgSucesso).build();
        } catch (NegocioException e) {
            LOGGER.error(ERRO_SALVAR_DESTINACAO, e);
            httpStatus = HttpStatus.BAD_REQUEST;
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        } catch (RuntimeException e) {
            LOGGER.error(ERRO_SALVAR_DESTINACAO, e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            resposta = RespostaBuilder.getBuilder().setErro(e.toString()).build();
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    /**
     * Método responsável por salvar destinação sem utilização.
     * @param imovelDTO recebe o dto do imovel
     * @return a destinacao sem utilização
     */
    @RequestMapping(value = "sem-utilizacao", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<String>> salvarSemUtilizacao(@RequestBody ImovelDTO imovelDTO) {
        Imovel imovel = entityConverter.converterStrict(imovelDTO, Imovel.class);
        Resposta<String> resposta = null;
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            semUtilizacaoService.salvar(imovel);
            resposta = RespostaBuilder.getBuilder().setMensagen(mensagemNegocio.get("operacao.realizaca.sucesso")).build();
        } catch (NegocioException e) {
            LOGGER.error(e.getCause(), e);
            httpStatus = HttpStatus.BAD_REQUEST;
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity(resposta, httpStatus);
    }

    /**
     * Método responsável por retorna as pendencias da Destinação.
     * @return ResponseEntity<Resposta<PendenciaDTO>>
     */
    @RequestMapping(value = "pendencias", method = RequestMethod.GET)
    public ResponseEntity<Resposta<PendenciaDTO>> buscaPendenciasDeDestinacao() {
        Resposta<PendenciaDTO> resposta = RespostaBuilder.getBuilder().setResultado(destinacaoPendenciaService.buscarPendenciasUsuario()).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     * Cancela uma destinação apartir do RIP do imovel.
     * @param rip
     * @return
     */
    @RequestMapping(value = "/cancelar/{rip}", method = RequestMethod.DELETE)
    public ResponseEntity<Resposta<String>> cancelarDestinacaoSemUtilizacao(@PathVariable("rip") String rip) {
        Resposta<String> resposta = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            semUtilizacaoService.cancelar(rip);
            resposta = RespostaBuilder.getBuilder()
                    .setMensagen(mensagemNegocio.get("operacao.realizaca.sucesso"))
                    .build();
        } catch (NegocioException e) {
            LOGGER.error("ERRO CANCELAR DESTINACAO", e);
            httpStatus = HttpStatus.BAD_REQUEST;
            resposta = RespostaBuilder.getBuilder().setMensagen(e.getMessage()).build();
        }

        return new ResponseEntity<>(resposta, httpStatus);
    }

    /**
     * Consulta destinações.
     * @param filtroDestinacaoDTO
     * @return uma resposta com a lista de destinações.
     */
    @RequestMapping(value = "consultar", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ConsultaDestinacaoDTO>>> consultarDestinacao(FiltroDestinacaoDTO filtroDestinacaoDTO) {
        try {
            return new ResponseEntity<>(semUtilizacaoService.consultar(filtroDestinacaoDTO), HttpStatus.OK);
        } catch (IntegracaoException e) {
            LOGGER.error(ERRO_CONSULTAR_DESTINACAO, e);
            Resposta<List<ConsultaDestinacaoDTO>> resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * buscar historico.
     * @return uma resposta com a lista de historico.
     */
    @RequestMapping(value = "buscarHistorico/{id}/{idDestinacao}/{idVersao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ConsultaDestinacaoDTO>>> buscarHistorico(@PathVariable("id") Long id, @PathVariable("idDestinacao") Long idDestinacao,
                                                                                 @PathVariable("idVersao") Long idVersao)  throws IntegracaoException  {

        DestinacaoService destinacaoService = destinacaoStrategy.createBean(TipoDestinacaoEnum.getPorCodigo(id.intValue()));
        try {
            return new ResponseEntity<>(destinacaoService.buscarHistoricoDestinacao(idDestinacao, idVersao), HttpStatus.OK);
        } catch (IntegracaoException e) {
            LOGGER.error(ERRO_CONSULTAR_DESTINACAO, e);
            Resposta<List<ConsultaDestinacaoDTO>> resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * buscar lista Versões.
     * @return uma resposta com a lista de versões.
     */
    @RequestMapping(value = "buscarListaVersaoDestinacao/{id}/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<DestinacaoDTO>>> buscarListaVersaoDestinacao(@PathVariable("id") Long id, @PathVariable("rip") String rip)  throws IntegracaoException  {

        DestinacaoService destinacaoService = destinacaoStrategy.createBean(TipoDestinacaoEnum.getPorCodigo(id.intValue()));
        List<DestinacaoDTO> dados = destinacaoService.buscarListaVersoesDestinacoesService(rip);
        Resposta<List<DestinacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(dados).build();

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }





    /**
     * Consulta as utilizacoes disponiveis
     * @param filtroDestinacaoDTO
     * @return uma resposta com a lista de utilizacoes disponiveis.
     */
    @RequestMapping(value = "consultarUtilizacao", method = RequestMethod.POST)
    public ResponseEntity<Resposta<List<ConsultarUtilizacaoDTO>>> consultarUtilizacoes(@RequestBody FiltroDestinacaoDTO filtroDestinacaoDTO) {
        try {
            return new ResponseEntity<>(semUtilizacaoService.consultarUtilizacao(filtroDestinacaoDTO, filtroDestinacaoDTO.getFundamentoLegal()), HttpStatus.OK);
        } catch (IntegracaoException e) {
            LOGGER.error(ERRO_CONSULTAR_DESTINACAO, e);
            Resposta<List<ConsultarUtilizacaoDTO>> resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "buscarEncargos/{idDestinacao}")
    public ResponseEntity<Resposta<EncargoDTO>> buscarEncargos(@PathVariable("idDestinacao") Long idDestinacao){

        Resposta<EncargoDTO> resposta = RespostaBuilder.getBuilder().setResultado(encargoService.listaEncargos(idDestinacao)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);

    }

    @RequestMapping(value = "consultar-pendencia", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ConsultaDestinacaoDTO>>> consultarDestinacaoPendencia(String pendencia) {
        try {
            return new ResponseEntity<>(semUtilizacaoService.consultarDestinacaoPendencia(pendencia), HttpStatus.OK);
        } catch (IntegracaoException e){
            LOGGER.error("ERRO CONSULTAR DESTINACAO COM PENDENCIA", e);
            Resposta<List<ConsultaDestinacaoDTO>> resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Consulta destinação pelo id.
     * @param id da destinação
     * @return Uma destinação filtrada pelo id.
     */
    @RequestMapping(value = "{id}/{tipoDestinacao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta> consultarDestinacaoPorId(@PathVariable("id") Long id,
                                                             @PathVariable("tipoDestinacao") String tipoDestinacao){

        Resposta resposta = null;
        try{
            DestinacaoService destinacaoService = destinacaoStrategy.createBean(TipoDestinacaoEnum.valueOf(tipoDestinacao));
            resposta = destinacaoService.findOne(id);
        }catch (IntegracaoException | IOException | RuntimeException e){
            LOGGER.error("ERRO INTEGRACAO COM O INCORPORACAO",e);
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     * Método responsável por consultar cidades.
     * @param pais
     * @param dadosUtilizacao
     * @return A resposta com a lista de nomes de cidades.
     */
    @RequestMapping(value = "consultarCidades/{pais}/{dadosUtilizacao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<String>>> consultarCidades(@PathVariable("pais") String pais,
                                                                   @PathVariable("dadosUtilizacao") String dadosUtilizacao){
        return new ResponseEntity<>(semUtilizacaoService.consultarCidades(pais,dadosUtilizacao), HttpStatus.OK);
    }

    /**
     * Método responsável por obter o status da destinação.
     * @param statusDestinacaoEnum
     * @return Status da destinação.
     */
    private StatusDestinacao obterStatusDestinacao(StatusDestinacaoEnum statusDestinacaoEnum){
        StatusDestinacao statusDestinacao = new StatusDestinacao();
        statusDestinacao.setId(statusDestinacaoEnum.getCodigo());
        statusDestinacao.setDescricao(statusDestinacao.getDescricao());
        return statusDestinacao;
    }

    /**
     * Método responsável por remover pendencias de homologação.
     * @param usoProprio
     */
    private void removerPendenciaDeHomologacao(UsoProprio usoProprio){
        usoProprioService.removerPendenciaDeHomologacao(usoProprio);

    }

    @RequestMapping(value = "/consultarDadosDestinacaoAvaliacao/{rip}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<ConsultarDadosUtilizacaoAvaliacaoDTO>>> consultarDadosDestinacaoAvaliacao(@PathVariable("rip") String rip){
        List<ConsultarDadosUtilizacaoAvaliacaoDTO> dados = semUtilizacaoService.buscarDadosDestinacaoAvaliacao(rip);
        Resposta<List<ConsultarDadosUtilizacaoAvaliacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(dados).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }


}
