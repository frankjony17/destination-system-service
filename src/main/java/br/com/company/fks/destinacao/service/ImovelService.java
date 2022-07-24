package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.BenfeitoriaDTO;
import br.com.company.fks.destinacao.dominio.dto.ConsultaImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.MotivacaoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.ParcelaDTO;
import br.com.company.fks.destinacao.dominio.dto.SuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.UtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.dominio.entidades.UnidadeAutonoma;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ImovelRepository;
import br.com.company.fks.destinacao.service.destinacao.PosseInformalService;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import br.com.company.fks.destinacao.service.validadores.ValidadorRipStrategyFactory;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.ObjetoUtils;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar operações com o Imóvel
 * Created by Basis Tecnologia on 06/10/2016.
 */
@Service
public class ImovelService {

    private static final Logger LOGGER = Logger.getLogger(ImovelService.class);
    private static final String P0 = "P0";
    private static final String UTILIZACAO = "0001";
    private static final String CONSULTAR_IMOVEL_CADASTRO = "CONSULTAR IMOVEL CADASTRO:";
    private static final String POSSE_INFORMAL = "POSSE_INFORMAL";

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private PosseInformalService posseInformalService;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private BenfeitoriaService benfeitoriaService;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private ParcelaService parcelaService;

    @Autowired
    private DestinacaoImovelService destinacaoImovelService;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private ValidadorRipStrategyFactory validadorRipStrategyFactory;

    @Autowired
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Autowired
    private OcupanteService ocupanteService;



    /**
     * Busca Imóveis por RIP e valida o RIP
     * @param rip
     * @param tipoDestinacao
     * @return Resposta<ImovelDTO> lista dos imóveis encontrados pelo RIP que possuem RIP válido
     * @throws IntegracaoException
     */

    public ImovelDTO consultarImovelRip(String rip, String tipoDestinacao) throws IntegracaoException, NegocioException {
        try {
            ImovelDTO imovelDTO =  getImovelRipValido(rip);
            ImovelDTO imovelDTOCadastro = getImovelCadastroImoveis(rip);
            if (imovelDTO != null ) {
                mesclarDadosImovel(imovelDTO, imovelDTOCadastro, tipoDestinacao);
                imovelDTO.setId(getIdImovelByIdCadastroImovel(imovelDTO.getIdCadastroImovel()));
            }
            if (imovelDTOCadastro != null && imovelDTOCadastro.getSituacaoImovel() != null && imovelDTOCadastro.getSituacaoImovel().getId().equals(3L)){
                throw new NegocioException("RIP INVÁLIDO");
            }
            return imovelDTO;
        } catch (NegocioException e) {
            LOGGER.error("VALIDAÇÃO DE RIP:", e);
            throw e;
        } catch (IntegracaoException e) {
            LOGGER.error(CONSULTAR_IMOVEL_CADASTRO, e);
            throw e;
        }
    }

    /**
     * Busca Imóveis por RIP e valida o RIP
     * @return Resposta<ImovelDTO> lista dos imóveis encontrados pelo RIP que possuem RIP válido
     * @throws IntegracaoException
     */

    public void buscarListaSuspensoesImovelPorRip(ConsultaImovelDTO consultaImovelDTO) throws IntegracaoException, NegocioException {
        List<SuspensaoImovelDTO> listaSuspensaoDTO = getListaDadosSuspensaoPorRip(consultaImovelDTO);
        List<MotivacaoSuspensaoImovelDTO> motivosSuspensao = new ArrayList<>();
        for(int i = 0; i < listaSuspensaoDTO.size(); i++){
            if(listaSuspensaoDTO.get(i) != null) {
                motivosSuspensao.add(listaSuspensaoDTO.get(i).getMotivacao());
            }
        }
        if(!motivosSuspensao.isEmpty()){
            throw new NegocioException("O imóvel encontra-se suspenso pelo motivo: " +concatenaVirgula(motivosSuspensao));
        }
    }

    protected String concatenaVirgula(List<MotivacaoSuspensaoImovelDTO> listaMotivosSuspensao) {
        String virgulaSuspensoes = "";
        int cont = 1;
        for (MotivacaoSuspensaoImovelDTO motivosSuspensao : listaMotivosSuspensao) {
            if (cont < listaMotivosSuspensao.size()){
                virgulaSuspensoes = virgulaSuspensoes.concat(motivosSuspensao.getDescricao() + ", ");
                cont++;
            } else {
                virgulaSuspensoes = virgulaSuspensoes.concat(motivosSuspensao.getDescricao());
            }
        }
        return virgulaSuspensoes;
    }

    /**
     * Consulta dados suspensao
     * @return
     * @throws IntegracaoException
     */

    protected List<SuspensaoImovelDTO> getListaDadosSuspensaoPorRip(ConsultaImovelDTO consultaImovelDTO) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveisSuspensao(consultaImovelDTO.getRip());
        List resposta = (List) requestUtils.doGet(url, List.class).getBody();
        List<SuspensaoImovelDTO> listaSuspensaoImovelDTO = new ArrayList<>();
        for(int i = 0; i < resposta.size(); i++) {
            listaSuspensaoImovelDTO.add(entityConverter.converterStrict(resposta.get(i), SuspensaoImovelDTO.class));
        }
        return listaSuspensaoImovelDTO;
    }

    public ImovelDTO consultarImovelRipValidando(ConsultaImovelDTO consultaImovelDTO) throws IntegracaoException, NegocioException{
        try{
            ImovelDTO imovelDTO = consultarImovelRip(consultaImovelDTO.getRip(), consultaImovelDTO.getTipoDestinacao());
            imovelDTO.setUtilizacao(buscarTodasDestinacoesPorRip(consultaImovelDTO.getRip()));
            ValidadorRip validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.getPorNome(consultaImovelDTO.getTipoDestinacao()));
            Imovel imovel = entityConverter.converter(imovelDTO, Imovel.class);
            validadorRip.validar(imovel, consultaImovelDTO.getIdModalidade(), consultaImovelDTO.getFundamentoLegal());
            consultaImovelDTO.setSequencialParcela(P0);

            DestinacaoImovel destinacaoImovel =
                    destinacaoImovelService.findByIdImovelSequencial(imovelDTO.getId());

            verificarExisteDestinacaoImovel(destinacaoImovel);

            Parcela parcela = parcelaService.buscarParcelaPorIdImovelSequencial(imovelDTO.getId(), consultaImovelDTO.getSequencialParcela());

            setarDados(imovelDTO, parcela, destinacaoImovel, consultaImovelDTO);

            return imovelDTO;
        }catch (NegocioException e){
            LOGGER.error("VALIDAÇÃO DE RIP:", e);
            throw e;
        }
    }

    protected void mesclarDadosImovel(ImovelDTO imovelDTO, ImovelDTO imovelDTOCadastro, String tipoDestinacao) {
        imovelDTO.setLatitude(imovelDTOCadastro.getLatitude());
        imovelDTO.setLongitude(imovelDTOCadastro.getLongitude());
        imovelDTO.setImagens(imovelDTOCadastro.getImagens());
        imovelDTO.setMemorialDescritivo(imovelDTOCadastro.getMemorialDescritivo());
        if(POSSE_INFORMAL.equals(tipoDestinacao)){
            imovelDTO.setNumeroProcesso(imovelDTOCadastro.getNumeroProcesso());
        }
    }

    /**
     * Consulta imovel no Incorporação
     * @param rip
     * @return
     * @throws IntegracaoException
     */
    public ImovelDTO getImovelCadastroImoveis(String rip) throws IntegracaoException {
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis(rip);
        ImovelDTO imovelDTO = null;
        try {
            Resposta<ImovelDTO> resposta = (Resposta<ImovelDTO>) requestUtils.doGet(url, Resposta.class).getBody();
            if (resposta.getResultado() != null) {
                Type targetType = new TypeToken<ImovelDTO>() {
                }.getType();
                imovelDTO = entityConverter.converterStrict(resposta.getResultado(), targetType);
            }
        } catch (RuntimeException e) {
            LOGGER.error("ERRO INTEGRACAO CADASTRO IMOVEL", e);
            throw new IntegracaoException(e.getMessage(), e);
        }

        return imovelDTO;
    }

    /**
     * Busca imóvel pelo rip, cep e uf
     * @param rip
     * @param cep
     * @param uf
     * @param municipio
     * @param offset
     * @param limit
     * @return
     */
    public Page<ImovelDTO> consultarDestinacao(String rip, String cep, String uf,
                                               String municipio, int offset, int limit){

        Pageable pageable = new PageRequest(offset, limit, Sort.Direction.ASC, "id");
        Optional<String> optionalMunicipio = Optional.ofNullable(municipio);
        Optional<String> optionalRip = Optional.ofNullable(rip);

        String paramMunicipio = optionalMunicipio.map(map -> "%" + StringUtils.upperCase(map) + "%").orElse(null);
        String paramRip = optionalRip.map(map -> "%" + map+ "%").orElse(null);

        Page<ImovelDTO> page = imovelRepository.buscarDestinacao(paramRip, cep, uf, paramMunicipio, pageable);
        for (ImovelDTO dto : page.getContent()) {
            dto.setAreaConstruida(benfeitoriaService.somaAreaConstruida(dto.getRip()));
            dto.setQuantidade(parcelaService.buscarParcelasPorIdImovel(dto.getRip()).size());
        }
        return page;
    }


    /**
     * Busca imóvel pelo rip
     * @param rip
     * @return
     */
    public Imovel buscarDadosRipUtilizacao(String rip){
        Imovel imovel = imovelRepository.buscarDadosRipUtilizacao(rip);

        imovel.setParcelas(imovel.getParcelas().stream().filter(Parcela::getAtiva).collect(Collectors.toList()));
        imovel.setBenfeitorias(imovel.getBenfeitorias().stream().filter(Benfeitoria::getAtiva).collect(Collectors.toList()));

        List<Parcela> parcelas = imovel.getParcelas().stream().filter(Parcela::getAtiva).collect(Collectors.toList());

        int quantidadePendencia = 0;
        int parcelaDestinacao = 0;

        for(DestinacaoImovel destinacaoImovel : imovel.getDestinacoes()){
            quantidadePendencia += destinacaoPendenciaService.buscarPendenciasPorIdDestinacao(destinacaoImovel.getDestinacao().getId()).size();
            parcelaDestinacao += destinacaoImovelService.findByDestinacaoImovelById(destinacaoImovel.getId()).size();
        }

        if(quantidadePendencia < 1 && parcelaDestinacao > 0){
            tratarParcelas(imovel);
        }else{
            tratarParcelasComPendencia(imovel, quantidadePendencia);
        }

        Set<Long> idsParcelasUtilizadas = parcelaService.buscarIdsParcelasUtilizadasPorImovelId(imovel.getId());
        parcelas.forEach(parcela -> {
            setarParcela(parcela, idsParcelasUtilizadas);
        });

        imovel.setUltimaParcelaCriada(parcelaService.getUltimaParcela(parcelas));

        return imovel;
    }

    protected void setarParcela(Parcela parcela, Set<Long> idsParcelasUtilizadas){
        parcela.setUtilizada(parcelaService.isUtilizada(parcela, idsParcelasUtilizadas));
        parcela.setIdDestinacaoImoveis(parcela.getDestinacaoImoveis().stream().map(map -> map.getId()).collect(Collectors.toSet()));
        parcela.setDestinacaoImoveis(parcela.getDestinacaoImoveis().stream().filter(DestinacaoImovel::getUltimaDestinacao).collect(Collectors.toList()));
        parcela.setBenfeitorias(parcela.getBenfeitorias().stream().filter(Benfeitoria::getAtiva).collect(Collectors.toList()));

    }

    protected void removerBenfeitoriaLista(List<Benfeitoria> listaBenfeitorias, Benfeitoria benfeitoria){

        if (benfeitoria.getParcela().getDestinacaoImoveis() != null && !benfeitoria.getParcela().getDestinacaoImoveis().isEmpty()) {
            for (DestinacaoImovel destinacaoImovel : benfeitoria.getParcela().getDestinacaoImoveis()) {
                if (destinacaoImovel.getUltimaDestinacao() && destinacaoImovel.getCodigoUtilizacao().equals(UTILIZACAO)) {
                    listaBenfeitorias.remove(benfeitoria);
                    break;
                }
            }
        }
    }

    protected void tratarParcelas(Imovel imovel) {
        List<Benfeitoria> listaBenfeitorias = new ArrayList<>() ;
        listaBenfeitorias.addAll(imovel.getBenfeitorias());
        int quatidadesBenfeitorias = listaBenfeitorias.size();
        for (Parcela parcela : imovel.getParcelas()) {
            if (parcela.getAtiva()) {
                for (Benfeitoria benfeitoria : parcela.getBenfeitorias()) {
                    if (benfeitoria.getAtiva() && !listaBenfeitorias.isEmpty()) {
                        removerBenfeitoriaLista(listaBenfeitorias, benfeitoria);
                    }
                }
            }
        }
        montarParcelas(imovel.getParcelas(), listaBenfeitorias);
        adicionarBenfeitoriaParcela(imovel, quatidadesBenfeitorias, listaBenfeitorias);

    }


    protected void adicionarBenfeitoriaParcela(Imovel imovel, int quatidadesBenfeitorias, List<Benfeitoria> listaBenfeitorias ){

        for (Parcela parcela : imovel.getParcelas()) {
            if(quatidadesBenfeitorias != listaBenfeitorias.size()) {
                for (Benfeitoria benfeitoria : parcela.getBenfeitorias()) {
                    if (benfeitoria.getAtiva() && !parcela.getListaBenfeitorias().contains(benfeitoria)) {
                        parcela.getListaBenfeitorias().add(benfeitoria);
                    }
                }
            }else{
                parcela.setListaBenfeitorias(imovel.getBenfeitorias());
            }
        }
    }

    protected void tratarParcelasComPendencia(Imovel imovel, int qtdPendencia){
        if(qtdPendencia > 0){
            imovel.setPossuiPendencia(true);
        }
        for(Parcela parcela : imovel.getParcelas()){
            parcela.setListaBenfeitorias(new ArrayList<>());
            if(parcela.getAtiva()){
                parcela.getListaBenfeitorias().addAll(imovel.getBenfeitorias());
            }
        }
    }

    protected void montarParcelas(List<Parcela> parcelas, List<Benfeitoria> benfeitorias){

        for(Parcela parcela : parcelas){
            parcela.setListaBenfeitorias(new ArrayList<>());
            parcela.getListaBenfeitorias().addAll(benfeitorias);
        }
    }

    /**
     * Busca imóvel pelo rip
     * @param rip
     * @return
     */
    public Imovel buscarDadosBenfeitorias(String rip){
        Imovel imovel = imovelRepository.buscarDadosBenfeitorias(rip);

        List<Parcela> parcelas = imovel.getParcelas();

        imovel.setBenfeitorias(extrairBenfeitoriasParcela(parcelas));

        Set<Long> idsParcelasUtilizadas = parcelaService.buscarIdsParcelasUtilizadasPorImovelId(imovel.getId());
        parcelas.forEach(parcela -> {
            parcela.setUtilizada(parcelaService.isUtilizada(parcela, idsParcelasUtilizadas));
            parcela.setIdDestinacaoImoveis(parcela.getDestinacaoImoveis().stream().map(map -> map.getId()).collect(Collectors.toSet()));

        });

        imovel.setParcelas(parcelas.stream().filter(Parcela::getAtiva).collect(Collectors.toList()));
        imovel.setUltimaParcelaCriada(parcelaService.getUltimaParcela(parcelas));

        return imovel;
    }

    protected List<Benfeitoria> extrairBenfeitoriasParcela(List<Parcela> parcelas) {
        List<Benfeitoria> benfeitorias = new ArrayList<>();
        Parcela parcela = parcelas.stream().filter(p -> p.getSequencial().equalsIgnoreCase(P0)).findFirst().orElse(new Parcela());
        benfeitorias.addAll(parcela.getBenfeitorias());


        return benfeitorias;
    }

    protected ImovelDTO getImovelRipValido(String rip) throws NegocioException {

        return buscarImovel(rip);
    }

    protected ImovelDTO buscarImovel(String rip) throws NegocioException {
        List<Imovel> imoveis = imovelRepository.buscarPorRipParcelaAtiva(rip);

        if (imoveis.isEmpty()) {
            throw new NegocioException("RIP INVÁLIDO");
        }

        Imovel imovel = imoveis.get(Constants.ZERO);
        imovel.setBenfeitorias(benfeitoriaService.extrairBenfeitorias(imovel.getParcelas()));
        ImovelDTO imovelDTO = entityConverter.converter(imovel, ImovelDTO.class);

        return imovelDTO;
    }


    /**
     * Salva o imóvel
     * @param imovel
     * @return Imovel gravado no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Imovel salvar (Imovel imovel, DestinacaoImovel destinacaoImovel) {
        Imovel imovelSalvo = imovelRepository.save(imovel);
        Parcela parcelaSalva = parcelaService.salvarParcelaInicial(imovelSalvo, destinacaoImovel);
        imovelSalvo.setParcelas(Arrays.asList(parcelaSalva));
        imovelSalvo.setBenfeitorias(benfeitoriaService.salvar(imovel.getBenfeitorias(), parcelaSalva, imovelSalvo));
        return imovelSalvo;
    }

    @Transactional(readOnly = true)
    public ImovelDTO consultarDadosPosseInformal (String rip, String codigoUtilizacao, String sequencialParcela) throws IntegracaoException{
        try{
            ImovelDTO imovelDTO = consultarImovelPosseInformal (rip);
            DestinacaoImovel destinacaoImovel =
                    destinacaoImovelService.findByIdImovelSequencialParcelaCodigoUtilizacao(imovelDTO.getId(), sequencialParcela, codigoUtilizacao);

            if(destinacaoImovel != null){
                imovelDTO.setOcupantes(ocupanteService.findByIdPosseInformal(destinacaoImovel.getDestinacao().getId()));
                imovelDTO.setParcelas(montarListaParcelasDTO(destinacaoImovel));
                imovelDTO.setCodigoUtilizacao(destinacaoImovel.getCodigoUtilizacao());
            }
            else{
                throw new IntegracaoException("Código de Utilização Inválido");
            }
            return imovelDTO;
        }catch (IntegracaoException e){
            LOGGER.error(CONSULTAR_IMOVEL_CADASTRO, e);
            throw e;
        }
    }

    private List<ParcelaDTO> montarListaParcelasDTO(DestinacaoImovel destinacaoImovel){
        List<ParcelaDTO> parcelaDTOs = new ArrayList<>();
        for (Parcela parcela: destinacaoImovel.getParcelas()) {
            parcelaDTOs.add(entityConverter.converterStrict(parcela, ParcelaDTO.class));

        }
        return parcelaDTOs;
    }


    /**
     * Realiza busca do imóvel por RIP quando o tipo de destinação for Posse Informal
     * @param rip
     * @param
     * @return Resposta<ImovelDTO> lista com os imóveis encontrados
     * @throws IntegracaoException
     */
    @Transactional(readOnly = true)
    public ImovelDTO consultarImovelRipPosseInformal (String rip, String tipoDestinacao) throws IntegracaoException {
        try {
            ImovelDTO imovelDTO = consultarImovelRip(rip, tipoDestinacao);
            imovelDTO.setDestinacoes(posseInformalService.consultarNumeroDestinacao(rip));
            return imovelDTO;
        } catch (IntegracaoException | NegocioException e) {
            LOGGER.error("ERRO CONSULTAR RIP POSSE INFORMAL", e);
            throw new IntegracaoException(mensagemNegocio.get("erro.consultar.rip"), e);
        }
    }

    private ImovelDTO consultarImovelPosseInformal (String rip) throws IntegracaoException{
        try{
            ImovelDTO imovelDTO = imovelRepository.buscarDadosPosseInformal(rip);
            ImovelDTO imovelDTOCadastro = getImovelCadastroImoveis(rip);
            if (imovelDTO != null) {
                mesclarDadosImovel(imovelDTO, imovelDTOCadastro, TipoDestinacaoEnum.POSSE_INFORMAL.toString());
            }
            return imovelDTO;
        }catch (IntegracaoException e){
            LOGGER.error(CONSULTAR_IMOVEL_CADASTRO, e);
            throw e;
        }
    }


    @Transactional(readOnly = true)
    public ImovelDTO consultarImovel(String rip, String codigoUtizacao,  String sequencialParcela, String tipoDestinacao) throws NegocioException {
        try {
            ImovelDTO imovelDTO = consultarImovelRip(rip, tipoDestinacao);
            imovelDTO.setUtilizacao(buscarTodasDestinacoesPorRip(rip));
            DestinacaoImovel destinacaoImovel =
                    destinacaoImovelService.findByIdImovelSequencialParcelaCodigoUtilizacao(imovelDTO.getId(), sequencialParcela, codigoUtizacao);

            verificarExisteDestinacaoImovel(destinacaoImovel);

            imovelDTO.setCodigoUtilizacao(destinacaoImovel.getCodigoUtilizacao());
            setarDadosParcela(imovelDTO, destinacaoImovel.getParcelas(), sequencialParcela);
            imovelDTO.setBenfeitorias(imovelDTO.getParcela().getBenfeitorias());

            return imovelDTO;
        } catch (NegocioException | IntegracaoException e) {
            LOGGER.error("ERRO CONSULTAR RIP >>> ", e);
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public ImovelDTO consultarImovelRipParcela(ConsultaImovelDTO consultaImovelDTO) throws NegocioException {
        try {
            ImovelDTO imovelDTO = consultarImovelRip(consultaImovelDTO.getRip(), consultaImovelDTO.getTipoDestinacao());
            imovelDTO.setUtilizacao(buscarTodasDestinacoesPorRip(consultaImovelDTO.getRip()));

            ValidadorRip validadorRip = validadorRipStrategyFactory.createBean(TipoDestinacaoEnum.getPorNome(consultaImovelDTO.getTipoDestinacao()));
            Imovel imovel = entityConverter.converter(imovelDTO, Imovel.class);
            validadorRip.validar(imovel, consultaImovelDTO.getIdModalidade(), consultaImovelDTO.getFundamentoLegal());

            DestinacaoImovel destinacaoImovel =
                    destinacaoImovelService.findByIdImovelSequencial(imovelDTO.getId());

            verificarExisteDestinacaoImovel(destinacaoImovel);

            Parcela parcela = parcelaService.buscarParcelaPorIdImovelSequencial(imovelDTO.getId(), consultaImovelDTO.getSequencialParcela());

            setarDados(imovelDTO, parcela, destinacaoImovel, consultaImovelDTO);

            return imovelDTO;
        } catch (NegocioException | IntegracaoException e) {
            LOGGER.error("ERRO CONSULTAR RIP >>> ", e);
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private void setarDados(ImovelDTO imovelDTO, Parcela parcela, DestinacaoImovel destinacaoImovel, ConsultaImovelDTO consultaImovelDTO){
        imovelDTO.setParcela(entityConverter.converterStrict(parcela, ParcelaDTO.class));
        imovelDTO.setCodigoUtilizacao(destinacaoImovel.getCodigoUtilizacao());
        setarDadosParcela(imovelDTO, destinacaoImovel.getParcelas(), consultaImovelDTO.getSequencialParcela());

        imovelDTO.setBenfeitorias(imovelDTO.getParcela().getBenfeitorias().stream().filter(BenfeitoriaDTO::getAtiva).collect(Collectors.toList()));
    }

    private void setarDadosParcela(ImovelDTO imovelDTO, List<Parcela> parcelas, String sequencial) {
        Optional<Parcela> optional = parcelas.stream().filter(p -> p.getSequencial().equals(sequencial)).findFirst();
        if (optional.isPresent()) {
            imovelDTO.setParcela(entityConverter.converterStrict(optional.get(), ParcelaDTO.class));
        }
    }

    public List<UtilizacaoDTO> buscarTodasDestinacoesPorRip(String rip) throws NegocioException {
        return destinacaoImovelService.buscarTodasUtilizacoesPorRip(rip);
    }

    private void verificarExisteDestinacaoImovel(DestinacaoImovel destinacaoImovel) throws NegocioException {
        if (destinacaoImovel == null) {
            throw new NegocioException("Código de Utilização Inválido");
        }
    }

    /**
     * Busca imóvel por rip
     * @param rip
     * @return Imovel encontrado através do RIP informado
     */
    @Transactional(readOnly = true)
    public Imovel buscarPorRip(String rip) {
        List<Imovel> imoveis = imovelRepository.buscarPorRip(rip);
        return !imoveis.isEmpty() ? imoveis.get(0) : null;
    }

    /**
     * Verifica se imovel possui destinações
     * @param rip
     * @return true ou false
     */
    @Transactional(readOnly = true)
    public Boolean isImovelDestinado(String rip) {
        Integer quantidadeImovelDestinacoes = imovelRepository.quantidadesDestinacaoImovel(rip);
        return quantidadeImovelDestinacoes != null && quantidadeImovelDestinacoes > 0;
    }

    /**
     * Verifica se o imóvel possui destinação ativa
     * @param rip
     * @return true ou false
     */
    @Transactional(readOnly = true)
    public Boolean isImovelDestinacaoAtiva(String rip) {
        Integer quantidadeDestinacoes = imovelRepository.quantidadesDestinacaoAtivaImovel(rip);
        return quantidadeDestinacoes != null && quantidadeDestinacoes > 0;
    }

    /**
     * Busca imóvel por id do Cadastro de Imóvel
     * @param idCadastro
     * @return Imóvel encontrado pelo id do cadastro do imóvel
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Imovel findByIdCadastroImovel(Long idCadastro) {
        return imovelRepository.findByIdCadastroImovel(idCadastro);
    }

    /**
     * Busca imóvel pelo id
     * @param id
     * @return Imóvel encontrado pelo id
     */
    @Transactional(readOnly = true)
    public Imovel findById(Long id) {
        return imovelRepository.findOne(id);
    }

    /**
     * Busca o id do imóvel pelo id do Cadastro do Imóvel
     * @param idCadastroImovel
     * @return Id do imóvel encontrado
     */
    @Transactional(readOnly = true)
    public Long getIdImovelByIdCadastroImovel(Long idCadastroImovel) {
        return imovelRepository.getIdImovelByIdCadastroImovel(idCadastroImovel);
    }

    /**
     * Atualiza os dados do imovel
     * @param imovel
     * @return Imovel
     * @throws NegocioException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Imovel atualizar(Imovel imovel, Map<Long, Boolean> pendenciasGeradas) throws NegocioException {
        Imovel imovelSalvo = imovelRepository.findByRip(imovel.getRip());

        BigDecimal areaTerreno = imovelSalvo.getAreaTerreno();

        if(areaTerreno != imovel.getAreaTerreno()){
            imovelSalvo.setAreaTerrenoAntigo(areaTerreno);
        }

        if (imovelSalvo.getAreaTerreno().compareTo(imovel.getAreaTerreno()) != Constants.ZERO) {
            imovelSalvo.setAreaTerreno(imovel.getAreaTerreno());
            gerarPendenciaParcela(imovelSalvo, pendenciasGeradas);
            gerarPendenciaParcela(imovelSalvo, pendenciasGeradas);
        }

        setarDadosImovelSalvo(imovelSalvo, imovel);

        atualizarUnidadeAutonoma(imovelSalvo, imovel, pendenciasGeradas);
        double vlrAreaConstruida = 0;

        vlrAreaConstruida =   calcularAreaConstruida(imovelSalvo.getParcelas(), vlrAreaConstruida);
        imovelSalvo.setAreaConstruidaAntigo(BigDecimal.valueOf(vlrAreaConstruida));
        return imovelRepository.save(imovelSalvo);
    }

    private double calcularAreaConstruida(List<Parcela> listaParcelas, double vlrAreaConstruida){
        for(Parcela parcela : listaParcelas){
            if(parcela.getAtiva()) {
                for (Benfeitoria benfeitoria : parcela.getBenfeitorias()) {
                    if(benfeitoria.getAtiva()){
                        vlrAreaConstruida += benfeitoria.getAreaConstruida().doubleValue();
                    }
                }
            }
        }
        return vlrAreaConstruida;
    }


    /**
     * Atualiza os dados do imovel
     * @param imovel
     * @return Imovel
     * @throws NegocioException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Imovel atualizarImovel(Imovel imovel) throws NegocioException {
        Imovel imovelSalvo = imovelRepository.findByRip(imovel.getRip());

        if(imovelSalvo != null){
            imovelSalvo.setCodigoClassificacaoImovel(imovel.getCodigoClassificacaoImovel());

        }
        return imovelRepository.save(imovelSalvo);

    }

    private void setarDadosImovelSalvo(Imovel imovelSalvo, Imovel imovel){
        imovelSalvo.setCodigoNaturezaImovel(imovel.getCodigoNaturezaImovel());
        imovelSalvo.setCodigoSituacaoIncorporacao(imovel.getCodigoSituacaoIncorporacao());
        imovelSalvo.setCodigoTipoImovel(imovel.getCodigoTipoImovel());
        imovelSalvo.setCoditoTipoProprietario(imovel.getCoditoTipoProprietario());
        imovelSalvo.setProprietario(imovel.getProprietario());
        imovelSalvo.setEndereco(enderecoService.atualizar(imovelSalvo.getEndereco(), imovel.getEndereco()));
    }

    private void gerarPendenciaParcela(Imovel imovel, Map<Long, Boolean> pendenciasGeradas) {
        if (isMaisUmaParcela(imovel)) {
            pendenciasGeradas.put(Constants.ID_PENDENCIA_AJUSTE_PARCELA_IMOVEL, Boolean.TRUE);
        }
    }

    private Boolean isMaisUmaParcela(Imovel imovel) {
        List<Parcela> parcelas = imovel.getParcelas()
                .stream()
                .filter(Parcela::getAtiva)
                .collect(Collectors.toList());
        return parcelas.size() > Constants.UM;
    }

    private void atualizarUnidadeAutonoma(Imovel imovelSalvo, Imovel imovel, Map<Long, Boolean> pendenciasGeradas) throws NegocioException {

        if (imovel.getUnidadeAutonoma() == null) {
            imovelSalvo.setUnidadeAutonoma(null);
        } else {
            atualizarCriarNovaUnidadeAutonoma(imovelSalvo, imovel, pendenciasGeradas);
        }

    }

    private void atualizarCriarNovaUnidadeAutonoma(Imovel imovelSalvo, Imovel imovel, Map<Long, Boolean> pendenciasGeradas) throws NegocioException {
        if (imovelSalvo.getUnidadeAutonoma() != null) {
            tratarEdicaoUnidadeAutonoma(imovelSalvo, imovel, pendenciasGeradas);
        } else {
            salvarNovaUnidadeAutonoma(imovelSalvo, imovel);
        }
    }

    private void salvarNovaUnidadeAutonoma(Imovel imovelSalvo, Imovel imovel) {
        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma();
        unidadeAutonoma.setAreaDisponivel(imovel.getUnidadeAutonoma().getArea());
        unidadeAutonoma.setArea(imovel.getUnidadeAutonoma().getArea());
        unidadeAutonoma.setIdUnidadeAutonomaCadImovel(imovel.getUnidadeAutonoma().getIdUnidadeAutonomaCadImovel());
        imovelSalvo.setUnidadeAutonoma(unidadeAutonoma);
    }

    private void tratarEdicaoUnidadeAutonoma(Imovel imovelSalvo, Imovel imovel, Map<Long, Boolean> pendenciasGeradas) throws NegocioException {
        Set<String> camposValidar = ObjetoUtils.criarCamposValidar("area", "idUnidadeAutonomaCadImovel");
        UnidadeAutonoma unidadeAutonomaSalva = imovelSalvo.getUnidadeAutonoma();
        UnidadeAutonoma novaUnidadeAutonoma = imovel.getUnidadeAutonoma();
        if (ObjetoUtils.compararObjetosDiferentes(unidadeAutonomaSalva, novaUnidadeAutonoma, camposValidar)) {
            unidadeAutonomaSalva.setAreaDisponivel(novaUnidadeAutonoma.getArea());
            unidadeAutonomaSalva.setArea(novaUnidadeAutonoma.getArea());
            unidadeAutonomaSalva.setIdUnidadeAutonomaCadImovel(novaUnidadeAutonoma.getIdUnidadeAutonomaCadImovel());
            imovelSalvo.setUnidadeAutonoma(unidadeAutonomaSalva);
            gerarPendenciaParcela(imovelSalvo, pendenciasGeradas);
        }
    }

    /**
     * Inativa o imovel e suas parcelas
     * @param imovel
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void inativar(Imovel imovel) {
        imovel.setAtivo(Boolean.FALSE);
        parcelaService.inativar(imovel.getParcelas());
        imovelRepository.save(imovel);
    }

    @Transactional(readOnly = true)
    public List<Imovel> buscarImoveisPorRip(String rip) {
        return imovelRepository.buscarPorRip(rip);
    }

    /**
     * Extrai o imóvel da parcela.
     * @param parcelas
     * @return
     * @throws NegocioException
     */
    public Imovel extrairImovelParcela(List<Parcela> parcelas) throws NegocioException {
        if (!parcelas.isEmpty()) {
            return parcelas.get(Constants.ZERO).getImovel();
        }
        throw new NegocioException("A parcela não possui um imóvel");
    }

}
