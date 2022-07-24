package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DocumentoAnaliseDTO;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.DocumentoComplementar;
import br.com.company.fks.destinacao.dominio.entidades.DocumentoComplementarEspecifico;
import br.com.company.fks.destinacao.dominio.entidades.Publicacao;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaRepository;
import br.com.company.fks.destinacao.service.analiseTecnica.StatusAnaliseDespacho;
import br.com.company.fks.destinacao.service.analiseTecnica.StatusAnaliseTecnicaStrategy;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.EnviadorEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Classe genérica responsável por realizar o perações com Análises Técnicas
 * Created by raphael on 05/12/16.
 */
@Service
public class AnaliseTecnicaService {

    private static final Logger LOGGER = Logger.getLogger(AnaliseTecnicaService.class);

    private static final String EMAIL_RESPONSAVEL = "email";
    private static final String NUMERO_ATENDIMENTO = "numeroAtendimento";
    private static final String NOME_RESPONSAVEL = "nomeResponsavel";
    private static final String OBRIGATORIO = "OBRIGATORIO";
    private static final String COMPLEMENTAR = "COMPLEMENTAR";
    private static final String DEFERIDO = "DEFERIDO";

    private static final Map<Integer, StatusAnaliseTecnicaEnum> mapaStatusAtualizaStatusReqStatusAnalise = new HashMap<>();

    public static final int NOVE = 9;

    public static final int DEZ = 10;

    public static final int ONZE = 11;
    public static final long SEIS = 6L;

    static {
            mapaStatusAtualizaStatusReqStatusAnalise.put(NOVE, StatusAnaliseTecnicaEnum.AGUARDANDO_REQUERENTE);
            mapaStatusAtualizaStatusReqStatusAnalise.put(DEZ, StatusAnaliseTecnicaEnum.CANCELADO);
            mapaStatusAtualizaStatusReqStatusAnalise.put(ONZE, StatusAnaliseTecnicaEnum.INDEFERIDO);
        }

    @Autowired
    private AnaliseTecnicaRepository analiseTecnicaRepository;

    @Autowired
    private ItemVerificacaoService itemVerificacaoService;
    @Autowired
    private ItemVerificacaoEspecificaService itemVerificacaoEspecificaService;
    @Autowired
    private EntityConverter entityConverter;
    @Autowired
    private HistoricoAnaliseTecnicaService historicoAnaliseTecnicaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DominioService dominioService;
    @Autowired
    private DespachoService despachoService;
    @Autowired
    private EnviadorEmail enviadorEmail;
    @Autowired
    private RequerimentoService requerimentoService;
    @Autowired
    private PublicacaoService publicacaoService;
    @Autowired
    private AnaliseTecnicaDespachoTecnicoService analiseTecnicaDespachoTecnicoService;
    @Autowired
    private AnaliseTecnicaDespachoChefiaService analiseTecnicaDespachoChefiaService;
    @Autowired
    private AnaliseTecnicaDespachoSuperintendenteService analiseTecnicaDespachoSuperintendenteService;
    @Autowired
    private AnaliseTecnicaDespachoSecretarioService analiseTecnicaDespachoSecretarioService;
    @Autowired
    private DocumentoAnaliseService documentoAnaliseService;

    /**
     * Método reponsável por salvar Análise Técnica DTO
     * @param analiseTecnicaDTO
     * @return AnaliseTecnicaDTO salva no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AnaliseTecnicaDTO salvar(AnaliseTecnicaDTO analiseTecnicaDTO) {
        UsuarioLogadoDTO usuarioLogadoDTO = usuarioService.getUsuarioLogado();
        AnaliseTecnica analiseTecnica = entityConverter.converterStrict(analiseTecnicaDTO, AnaliseTecnica.class);
        analiseTecnica.setIdUsuario(usuarioLogadoDTO.getId());
        AnaliseTecnica analiseTecnicaSalva = salvar(analiseTecnica, analiseTecnicaDTO, usuarioLogadoDTO);
        return entityConverter.converterStrict(analiseTecnicaSalva, AnaliseTecnicaDTO.class);
    }

    /**
     * Salva análise técnica
     * @param analiseTecnica
     * @param analiseTecnicaDTO
     * @param usuarioLogadoDTO
     * @return AnaliseTecnica salva no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AnaliseTecnica salvar(AnaliseTecnica analiseTecnica, AnaliseTecnicaDTO analiseTecnicaDTO, UsuarioLogadoDTO usuarioLogadoDTO) {

        StatusAnaliseTecnica statusAnaliseTecnicaAnterior = getStatusAnaliseTecnicaAtual(analiseTecnica.getId());

        //VERIFICACAO DO STATUS
        StatusAnaliseTecnica statusAnaliseTecnicaAtual = definirStatusPorPermissao(usuarioLogadoDTO, analiseTecnicaDTO);

        analiseTecnica.setStatusAnaliseTecnica(statusAnaliseTecnicaAtual);
        setarEnvioPublicacao(analiseTecnica);

        AnaliseTecnica analiseTecnicaSalva = analiseTecnicaRepository.save(analiseTecnica);

        itemVerificacaoService.salvar(analiseTecnicaDTO.getItensVerificacao(), analiseTecnicaSalva);
        itemVerificacaoEspecificaService.salvar(analiseTecnicaDTO.getItensVerificacaoEspecifica(), analiseTecnicaSalva);

        salvarDespachosAnalise(analiseTecnicaSalva, analiseTecnicaDTO);

        documentoAnaliseService.salvar(analiseTecnicaDTO.getDocumentosAnaliseObrigatorio(), analiseTecnicaSalva);
        documentoAnaliseService.salvar(analiseTecnicaDTO.getDocumentosAnaliseComplementar(), analiseTecnicaSalva);

        setarAnaliseNosDocumentos(analiseTecnica);

        historicoAnaliseTecnicaService.salvar(statusAnaliseTecnicaAnterior, statusAnaliseTecnicaAtual, analiseTecnicaSalva, usuarioLogadoDTO);
        enviarEmail(statusAnaliseTecnicaAtual, analiseTecnicaDTO.getIdRequerimento());
        alterarStatusAnaliseModuloServico(analiseTecnicaSalva);
        return analiseTecnicaSalva;
    }

    private void setarEnvioPublicacao(AnaliseTecnica analiseTecnica) {
        StatusAnaliseTecnica statusAnaliseTecnica = analiseTecnica.getStatusAnaliseTecnica();

        if (statusAnaliseTecnica != null && statusAnaliseTecnica.getId().equals(StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO.getCodigo())) {
            analiseTecnica.setDataEnvioPublicacao(new Date());
        }

    }

    private void alterarStatusAnaliseModuloServico(AnaliseTecnica analiseTecnica) {
        StatusAnaliseTecnica statusAnaliseTecnica = analiseTecnica.getStatusAnaliseTecnica();
        StatusAnaliseTecnicaEnum statusAnaliseTecnicaEnum = StatusAnaliseTecnicaEnum.getPorCodigo(statusAnaliseTecnica.getId());
        if (mapaStatusAtualizaStatusReqStatusAnalise.containsKey(statusAnaliseTecnica.getId())) {
            requerimentoService.alterarStatusRequerimento(analiseTecnica.getIdRequerimento(), statusAnaliseTecnicaEnum);
        }
        requerimentoService.alterarStatusAnaliseTecnica(analiseTecnica.getIdRequerimento(), statusAnaliseTecnicaEnum);
    }

    /**
     * Salva despachos análise dto
     * @param analiseTecnica
     * @param analiseTecnicaDTO
     */
    public void salvarDespachosAnalise(AnaliseTecnica analiseTecnica,
                                       AnaliseTecnicaDTO analiseTecnicaDTO) {
        analiseTecnica.setDespachosAnaliseTecnico(analiseTecnicaDespachoTecnicoService.salvar(analiseTecnicaDTO.getDespachosTecnico(), analiseTecnica));
        analiseTecnica.setDespachosAnaliseChefia(analiseTecnicaDespachoChefiaService.salvar(analiseTecnicaDTO.getDespachosChefia(), analiseTecnica));
        analiseTecnica.setDespachosAnaliseSuperintendente(analiseTecnicaDespachoSuperintendenteService.salvar(analiseTecnicaDTO.getDespachosSuperintendente(), analiseTecnica));
        analiseTecnica.setDespachosAnaliseSecretario(analiseTecnicaDespachoSecretarioService.salvar(analiseTecnicaDTO.getDespachosSecretario(), analiseTecnica));
    }

    private void enviarEmail(StatusAnaliseTecnica statusAnaliseTecnica, Long idRequerimento) {
        try {
            Map<String, String> dadosAtendimento = requerimentoService.getDadosAtendimento(idRequerimento);

            enviadorEmail.enviar(statusAnaliseTecnica,
                                 dadosAtendimento.get(EMAIL_RESPONSAVEL),
                                 dadosAtendimento.get(NUMERO_ATENDIMENTO),
                                 dadosAtendimento.get(NOME_RESPONSAVEL));

        } catch (MessagingException | IntegracaoException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Salva rascunho análise técnica dto
     * @param analiseTecnicaDTO
     * @return AnaliseTecnicaDTO em que o rascunho foi salvo
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AnaliseTecnicaDTO salvarRascunho(AnaliseTecnicaDTO analiseTecnicaDTO) {

        UsuarioLogadoDTO usuarioLogadoDTO = usuarioService.getUsuarioLogado();
        AnaliseTecnica analiseTecnica = entityConverter.converterStrict(analiseTecnicaDTO, AnaliseTecnica.class);
        analiseTecnica.setIdUsuario(usuarioLogadoDTO.getId());

        analiseTecnica.setStatusAnaliseTecnica(getStatusRascunho());

        AnaliseTecnica analiseTecnicaSalva = analiseTecnicaRepository.save(analiseTecnica);

        itemVerificacaoService.salvar(analiseTecnicaDTO.getItensVerificacao(), analiseTecnicaSalva);
        itemVerificacaoEspecificaService.salvar(analiseTecnicaDTO.getItensVerificacaoEspecifica(), analiseTecnicaSalva);

        salvarDespachosAnalise(analiseTecnicaSalva, analiseTecnicaDTO);

        documentoAnaliseService.salvar(analiseTecnicaDTO.getDocumentosAnaliseObrigatorio(), analiseTecnicaSalva);
        documentoAnaliseService.salvar(analiseTecnicaDTO.getDocumentosAnaliseComplementar(), analiseTecnicaSalva);

        setarAnaliseNosDocumentos(analiseTecnica);

        return entityConverter.converterStrict(analiseTecnicaSalva, AnaliseTecnicaDTO.class);
    }

    private void setarAnaliseNosDocumentos(AnaliseTecnica analiseTecnica){

        for (DocumentoComplementar documentoComplementar : analiseTecnica.getDocumentosComplementares()) {
            documentoComplementar.setAnaliseTecnica(analiseTecnica);
        }

        for (DocumentoComplementarEspecifico documentoComplementarEspecifico : analiseTecnica.getDocumentosComplementaresEspecificos()) {
            documentoComplementarEspecifico.setAnaliseTecnica(analiseTecnica);
        }
    }

    private StatusAnaliseTecnica definirStatusPorPermissao(UsuarioLogadoDTO usuarioLogadoDTO, AnaliseTecnicaDTO analiseTecnicaDTO) {
        PermissaoAnaliseEnum permissaoAnalise = PermissaoAnaliseEnum.getPermissaoAnaliseDescricao(usuarioLogadoDTO.getPermissoes());

        //Se não existir no banco
        if(analiseTecnicaDTO.getId() == null || analiseTecnicaStatusRascunho(analiseTecnicaDTO.getStatusAnaliseTecnica())) {
            return getStatusNovaAnaliseTecnica(permissaoAnalise);
        } else {
            return getStatusAnaliseTecnica(analiseTecnicaDTO, permissaoAnalise);
        }

    }

    private StatusAnaliseTecnica getStatusAnaliseTecnica(AnaliseTecnicaDTO analiseTecnicaDTO,
                                                         PermissaoAnaliseEnum permissaoAnalise) {
        StatusAnaliseDespacho statusAnaliseDespacho =
                StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(permissaoAnalise);
        if (statusAnaliseDespacho == null) {
            return getStatusNovaAnaliseTecnica(permissaoAnalise);
        } else {
            return statusAnaliseDespacho.getCodigoStatusPorDespacho(analiseTecnicaDTO);
        }
    }

    private StatusAnaliseTecnica getStatusNovaAnaliseTecnica(PermissaoAnaliseEnum permissaoAnalise) {
        Integer codigo = null;
        switch (permissaoAnalise){
            case EXEC_ANALISE_CHEFIA:
                codigo = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo();
                break;
            case EXEC_ANALISE_SUPERINTENDENTE:
                codigo = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo();
                break;
            default:
                codigo = StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA.getCodigo();
                break;
        }
        return (StatusAnaliseTecnica) dominioService.findById(codigo, StatusAnaliseTecnica.class);
    }

    private Boolean analiseTecnicaStatusRascunho(DominioDTO statusAnaliseTecnica) {
        return statusAnaliseTecnica.getId().intValue() == StatusAnaliseTecnicaEnum.RASCUNHO.getCodigo();
    }

    private StatusAnaliseTecnica getStatusAnaliseTecnicaAtual(Long id) {
        if (id != null) {
            AnaliseTecnica analiseTecnica = analiseTecnicaRepository.findOne(id);
            if (analiseTecnica != null) {
                return analiseTecnica.getStatusAnaliseTecnica();
            }
        }
        return null;
    }

    private StatusAnaliseTecnica getStatusRascunho() {
        return(StatusAnaliseTecnica) dominioService.findById(StatusAnaliseTecnicaEnum.RASCUNHO.getCodigo(), StatusAnaliseTecnica.class);
    }

    /**
     * Realiza uma busca por id da análise técnica dto
     * @param id
     * @return AnaliseTecnicaDTO encontrada por id da análise
     * @throws NegocioException
     */
    @Transactional(readOnly = true)
    public AnaliseTecnicaDTO buscarPorId(Long id) throws NegocioException {
        AnaliseTecnica analise = analiseTecnicaRepository.findOne(id);
        return converterAnaliseDTO(analise);
    }

    /**
     * Realiza uma busca da análise técnica dto pelo id do requerimento
     * @param requerimentoId
     * @return AnaliseTecnicaDTO encontrada por id do requerimento
     * @throws NegocioException
     */
    @Transactional(readOnly = true)
    public AnaliseTecnicaDTO buscarPorIdRequerimento(Long requerimentoId) throws NegocioException {
        AnaliseTecnica analise = analiseTecnicaRepository.buscarPorIdRequerimento(requerimentoId);
        return converterAnaliseDTO(analise);
    }

    private AnaliseTecnicaDTO converterAnaliseDTO(AnaliseTecnica analise) throws NegocioException {
        if(analise == null){
            throw new NegocioException("Nenhuma analise encontrada");
        }

        AnaliseTecnicaDTO analiseTecnicaDTO = entityConverter.converterStrict(analise, AnaliseTecnicaDTO.class);

        despachoService.sort(analiseTecnicaDTO.getDespachosChefia());
        despachoService.sort(analiseTecnicaDTO.getDespachosSuperintendente());

        analiseTecnicaDTO.setItensVerificacao(itemVerificacaoService.buscarPorIdAnalise(analise.getId()));
        analiseTecnicaDTO.setItensVerificacaoEspecifica(itemVerificacaoEspecificaService.buscarPorIdAnalise(analise.getId()));
        List<DocumentoAnaliseDTO> documentosAnalises = documentoAnaliseService.buscarPorIdAnalise(analise.getId());
        analiseTecnicaDTO.setDocumentosAnaliseObrigatorio(documentoAnaliseService.filtrarPorTipo(documentosAnalises, OBRIGATORIO));
        analiseTecnicaDTO.setDocumentosAnaliseComplementar(documentoAnaliseService.filtrarPorTipo(documentosAnalises, COMPLEMENTAR));

        //TODO: Deverá vim do banco....
        TipoDestinacaoDTO tipoDestinacaoDTO = new TipoDestinacaoDTO();
        tipoDestinacaoDTO.setId(SEIS);
        tipoDestinacaoDTO.setDescricao("Gestão Praia");
        analiseTecnicaDTO.setTipoDestinacao(tipoDestinacaoDTO);

        return analiseTecnicaDTO;
    }

    /**
     * Registra publicação diário da união na análise técnica
     * @param idAnalise
     * @param publicacao
     * @return AnaliseTecnica em que foi publicada o diário da união
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AnaliseTecnica registrarPublicacaoDiarioUniao(Long idAnalise, Publicacao publicacao) {

        Integer codigoStatusPublicado = StatusAnaliseTecnicaEnum.PUBLICADO.getCodigo();
        StatusAnaliseTecnica statusAnaliseTecnica =
                (StatusAnaliseTecnica) dominioService.findById(codigoStatusPublicado, StatusAnaliseTecnica.class);

        Publicacao publicacaoSalva = publicacaoService.salvar(publicacao);

        AnaliseTecnica analiseTecnica = analiseTecnicaRepository.findOne(idAnalise);
        analiseTecnica.setPublicacao(publicacaoSalva);
        analiseTecnica.setStatusAnaliseTecnica(statusAnaliseTecnica);
        AnaliseTecnica analiseTecnicaSalva = analiseTecnicaRepository.save(analiseTecnica);

        requerimentoService.alterarStatusRequerimento(analiseTecnicaSalva.getIdRequerimento(), DEFERIDO);
        requerimentoService.alterarStatusAnaliseTecnica(analiseTecnicaSalva.getIdRequerimento(), StatusAnaliseTecnicaEnum.PUBLICADO);

        return analiseTecnicaSalva;
    }

}
