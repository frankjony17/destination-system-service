package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ConsultaDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.ConsultarDadosUtilizacaoAvaliacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.ConsultarUtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.dto.EnderecoDTO;
import br.com.company.fks.destinacao.dominio.dto.FiltroDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.FormaDeIncorporacaoPermitidaDTO;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.dto.ImagemDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoAquisicaoPermitidaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Documento;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.dominio.enums.ClassificacaoImovelEnum;
import br.com.company.fks.destinacao.dominio.enums.EntidadeExtintaEnum;
import br.com.company.fks.destinacao.dominio.enums.FormaDeIncorporacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.StatusDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoAquisicaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.VersaoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.BenfeitoriaDestinadaRepository;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.ParcelaRepository;
import br.com.company.fks.destinacao.service.ArquivoService;
import br.com.company.fks.destinacao.service.AtoAutorizativoService;
import br.com.company.fks.destinacao.service.BenfeitoriaService;
import br.com.company.fks.destinacao.service.ContratoService;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import br.com.company.fks.destinacao.service.DocumentoArquivoService;
import br.com.company.fks.destinacao.service.DocumentoService;
import br.com.company.fks.destinacao.service.DominioService;
import br.com.company.fks.destinacao.service.EncargoService;
import br.com.company.fks.destinacao.service.FinanceiroService;
import br.com.company.fks.destinacao.service.FotoService;
import br.com.company.fks.destinacao.service.ImovelService;
import br.com.company.fks.destinacao.service.LicitacaoService;
import br.com.company.fks.destinacao.service.ParcelaService;
import br.com.company.fks.destinacao.service.UsuarioService;
import br.com.company.fks.destinacao.service.UtilizacaoService;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.service.validadores.ValidadorStrategy;
import br.com.company.fks.destinacao.utils.CastObjectUtil;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.DataUtil;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.apache.log4j.Logger;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

/**
 *
 * @param <T> Recebe o tipo de destinação
 */
@Service
public abstract class DestinacaoService<T> {

    private static final Logger LOGGER = Logger.getLogger(CessaoGratuitaService.class);

    @Autowired
    private DestinacaoRepository destinacaoRepository;

    @Autowired
    private UtilizacaoService utilizacaoService;

    @Autowired
    private EncargoService encargoService;

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    @Autowired
    private ValidadorStrategy validadorStrategy;

    @Autowired
    private FinanceiroService financeiroService;

    @Autowired
    private LicitacaoService licitacaoService;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DominioService dominioService;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private ParcelaService parcelaService;

    @Autowired
    private BenfeitoriaService benfeitoriaService;

    @Autowired
    private DestinacaoImovelService destinacaoImovelService;

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private FotoService fotoService;

    @Autowired
    private DocumentoArquivoService documentoArquivoService;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AtoAutorizativoService autorizativoService;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private BenfeitoriaDestinadaRepository benfeitoriaDestinadaRepository;

    private static final Long ESPELHO_DAGUA = 3L;

    private static final Long CAVIDADE_NATURAL = 5L;

    private static final Long RURAL = 2L;

    private static final Long INCORPORADO = 1L;

    private static final Long EM_PROCESSO_DE_INCORPORACAO = 2L;

    private static final Long POR_AQUISICAO = 2L;

    private static final Long SUCESSAO_POR_ENTIDADES = 9L;



    /**
     *
     * @param destinacao Recebe uma destinação
     * @return a respectiva destinação persistida.
     * @throws NegocioException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public T salvar(T destinacao) throws NegocioException {
        destinacao = (T) salvarDestinacao(destinacao);
        destinacao = salvarDadosEspecificos(destinacao);

        return (T)this.atualizar(destinacao);
    }

    /**
     *
     * @param destinacao Recebe uma destinação para homologar.
     * @return a respectiva destinação persistida.
     * @throws NegocioException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public T homologar(T destinacao) throws NegocioException {
        return salvar(destinacao);
    }

    /**
     * Atualiza outros dados da destinação.
     * @param destinacao
     * @return
     * @throws NegocioException
     */
    public T atualizar(T destinacao) throws NegocioException {
        Destinacao destinacaoCorrente = (Destinacao) destinacao;
        if(destinacaoCorrente.getAtivarCopia()){
            copiaParcela(destinacaoCorrente.getDestinacaoImoveis());
        }else{
            atualizarParcela(((Destinacao) destinacao).getDestinacaoImoveis());
            desmarcarUltimaDestiancao(destinacaoCorrente.getDestinacaoImoveis(), false);
        }



        return (T)destinacaoCorrente;
    }

    /**
     *
     * @param destinacao Recebe a destinacao e salva os dados específicos
     * @return
     */
    public abstract T salvarDadosEspecificos(T destinacao);

    @Transactional(propagation = Propagation.REQUIRED)
    protected Destinacao salvarDestinacao(Object objeto) throws NegocioException {
        Destinacao destinacao = (Destinacao) objeto;
        Boolean destinacaoComAditivo = false;

        Destinacao destinacaoSalvar = null;
        if(destinacao.getDocumentos() != null && !destinacao.getDocumentos().isEmpty()){
            for(Documento documento : destinacao.getDocumentos()){
                if(documento.getTipoDocumento().getId() == 3){
                    destinacaoComAditivo = true;
                }
            }
        }

        if(destinacaoComAditivo){
           destinacaoSalvar = salvaDestinacaoComAditivo(destinacao);

        }else{
            destinacaoSalvar = novaDestinacao(destinacao);
            destinacaoSalvar = destinacaoRepository.save(destinacaoSalvar);
        }
        //Classificar Imovel
        salvarClassificaoImovel(destinacaoSalvar.getDestinacaoImoveis(), destinacaoSalvar.getUtilizacao());

        return destinacaoSalvar;
    }

    private Destinacao salvaDestinacaoComAditivo(Destinacao destinacao){
        Destinacao destinacaoSalvar = null;
        Destinacao destinacaoBanco = destinacaoRepository.findOne(destinacao.getId());
        destinacaoBanco.setAtiva(false);
        destinacaoRepository.save(destinacaoBanco);

        destinacaoSalvar = copiaDestinacao(destinacao, destinacaoBanco);
        copiaBenfeitoria(destinacao.getDestinacaoImoveis().get(0).getImovel());

        return destinacaoSalvar;

    }

    public Destinacao copiaDestinacao(Object object , Destinacao destinacaoBanco){

        Destinacao destinacao = (Destinacao) object;

        Destinacao destiBanco = destinacaoRepository.findOne(destinacao.getId());
        destiBanco.setAtiva(false);
        destinacaoRepository.save(destiBanco);
        destinacao.setId(null);
        destinacao.setVersaoDestinacao((destinacaoBanco.getVersaoDestinacao().longValue() + 1l));
        setarDadosDestinacao(destinacao, true);

        Destinacao destinacaoSalvo = destinacaoRepository.save(destinacao);
        destinacao.setDestinacaoImoveis(destinacaoImovelService.salvar(destinacao.getDestinacaoImoveis(), destinacao, true));
        destinacaoSalvo.setAtivarCopia(true);

        destinacao.setDataDestinacaoHistorico(new Date());

        return destinacaoSalvo;

    }

    private void setarDadosDestinacao(Destinacao destinacao, Boolean copiaDestinacao){

        destinacao.setUtilizacao(utilizacaoService.salvar(destinacao.getUtilizacao(), copiaDestinacao));

        destinacao.setFinanceiro(financeiroService.salvar(destinacao.getFinanceiro(), copiaDestinacao));
        destinacao.setLicitacao(licitacaoService.salvar(destinacao.getLicitacao(), copiaDestinacao));
        destinacao.setEncargos(encargoService.salvar(destinacao.getEncargos()));
        destinacao.setContrato(contratoService.salvar(destinacao.getContrato(), copiaDestinacao));
        destinacao.setFotos(fotoService.salvar(destinacao.getFotos()));
        destinacao.setDocumentosArquivo(documentoArquivoService.salvar(destinacao.getDocumentosArquivo()));
        destinacao.setDocumentos(documentoService.salvar(destinacao.getDocumentos(), destinacao));
        destinacao.setDadosResponsavel(dadosResponsavelService.salvar(destinacao.getDadosResponsavel(), copiaDestinacao));
        destinacao.setAtoAutorizativo(autorizativoService.salvar(destinacao.getAtoAutorizativo(), copiaDestinacao));
        destinacao.setAtiva(true);
    }


    public Destinacao novaDestinacao(Object object) throws  NegocioException{

        Destinacao destinacao = (Destinacao) object;

        setarStatusDestinacao(destinacao);
        ValidadorDestinacao validadorDestinacao = validadorStrategy.createBean(destinacao.getTipoDestinacaoEnum());
        validadorDestinacao.validar(destinacao);
        setarTipoDestinacao(destinacao);

        setarDadosDestinacao(destinacao, false);
        destinacao.setVersaoDestinacao(VersaoDestinacaoEnum.VERSAO1.getCodigo().longValue());

        destinacao.setDataDestinacaoHistorico(new Date());
        destinacao.setAtiva(true);
        destinacao.setDestinacaoImoveis(destinacaoImovelService.salvar(destinacao.getDestinacaoImoveis(), destinacao, false));
        destinacao.setDataDestinacaoHistorico(new Date());

        Destinacao destinacaoSalvo = destinacaoRepository.save(destinacao);
        destinacaoSalvo.setAtivarCopia(false);

        return destinacaoSalvo;


    }

    public void copiaBenfeitoria(Imovel imovel){
        for(Benfeitoria benfeitoriaAtual: imovel.getBenfeitorias()){

            benfeitoriaAtual.setAtiva(false);
            benfeitoriaService.salvar(benfeitoriaAtual);

            Benfeitoria benfeitoria = new Benfeitoria();
            benfeitoria.setAtiva(true);
            benfeitoria.setAreaDisponivel(benfeitoriaAtual.getAreaDisponivel());
            benfeitoria.setAreaConstruida(benfeitoriaAtual.getAreaConstruida());
            benfeitoria.setNome(benfeitoriaAtual.getNome());
            benfeitoria.setCodigo(benfeitoriaAtual.getCodigo());
            benfeitoria.setEspecializacao(benfeitoriaAtual.getEspecializacao());
            benfeitoria.setIdBenfeitoriaCadImovel(benfeitoriaAtual.getIdBenfeitoriaCadImovel());
            benfeitoria.setImovel(imovel);
            benfeitoria.setParcela(benfeitoriaAtual.getParcela());

            benfeitoriaService.salvar(benfeitoria);

        }
    }

    protected void salvarClassificaoImovel(List<DestinacaoImovel> destinacaoImovels, Utilizacao utilizacao)  throws NegocioException  {
        if(destinacaoImovels != null && !destinacaoImovels.isEmpty() && utilizacao != null){
            DestinacaoImovel destinacaoImovel = destinacaoImovels.get(0);
            TipoUtilizacao tipoUtilizacao =  utilizacao.getTipoUtilizacao();

            if(utilizacao.getSubTipoUtilizacao() != null){
                tipoUtilizacao = utilizacao.getSubTipoUtilizacao().getTipoUtilizacao();
            }else{
                utilizacao.getTipoUtilizacao().setNumeroTipoUtilizacao(utilizacao.getTipoUtilizacao().getId() -1l);
            }

            classificarImovel(tipoUtilizacao, destinacaoImovel);
            destinacaoImovelService.salvar(destinacaoImovel, false);


            imovelService.atualizarImovel(destinacaoImovel.getImovel());

        }
    }

    protected void classificarImovel(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){
        classificarImovelZeroDois(tipoUtilizacao, destinacaoImovel);
        classificarImovelTresCinco(tipoUtilizacao, destinacaoImovel);
        classificarImovelSeis(tipoUtilizacao, destinacaoImovel);
        classificarImovelSeteOito(tipoUtilizacao, destinacaoImovel);
        classificarImovelNoveOnze(tipoUtilizacao,destinacaoImovel);
        classificarImovelDose(tipoUtilizacao, destinacaoImovel);
        classificarImovelTreze(tipoUtilizacao, destinacaoImovel);
        classificarImovelQuatorze(tipoUtilizacao, destinacaoImovel);
        classificarImovelQuize(tipoUtilizacao, destinacaoImovel);
        classificarImovelDezesseDezoito(tipoUtilizacao, destinacaoImovel);
        classificarImovelVinte(tipoUtilizacao, destinacaoImovel);
    };

    protected void setarTipoDestinacao(Destinacao destinacao) {
        TipoDestinacao tipoDestinacao = new TipoDestinacao();
        tipoDestinacao.setId(destinacao.getTipoDestinacaoEnum().codigo());
        destinacao.setTipoDestinacao(tipoDestinacao);
    }

    protected void setarStatusDestinacao(Destinacao destinacao) {
        if(destinacao.getStatusDestinacao() == null){
            destinacao.setStatusDestinacao((StatusDestinacao) dominioService.findById(StatusDestinacaoEnum.ATIVO.getCodigo(), StatusDestinacao.class));
        }
    }

    protected void classificarImovelZeroDois(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){
        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {
            case 0:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());

                break;
            case 1:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.SEMINFORMACAO.getCodigo().longValue());
                break;

            case 2:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                break;


            default:
                break;

        }

    }

    protected void classificarImovelTresCinco(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){

        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {
            case 3:
                if (destinacaoImovel.getDestinacao().getDadosResponsavel().getResponsaveis()!= null && destinacaoImovel.getImovel().getCodigoNaturezaImovel() != null) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                } else {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }
                break;

            case 4:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                break;
            case 5:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());

                break;


            default:
                break;

        }
    }

    protected void classificarImovelSeis(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){

        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {
            case 6:
                if (destinacaoImovel.getDestinacao().getDadosResponsavel().getCnpj() != null) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                } else {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }

                break;


            default:
                break;
        }

    }

    protected void classificarImovelSeteOito(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){
        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()){
            case 7:
                if (destinacaoImovel.getDestinacao().getDadosResponsavel().getCnpj() != null) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                } else {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }
                break;
            case 8:
                if (tipoUtilizacao.getDescricao().equalsIgnoreCase("Praça/Parque Urbano") || tipoUtilizacao.getDescricao().equalsIgnoreCase("Via pública (rua, avenida etc)")) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOCOMUMDOPOVO.getCodigo().longValue());
                } else {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }

                break;
            default:
                break;
        }
    }

    protected void classificarImovelNoveOnze(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){
        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {
            case 9:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());

                break;

            case 10:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());

                break;

            case 11:
                if (tipoUtilizacao.getDescricao().equalsIgnoreCase("Aerodromo privativo")) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                } else {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }

                break;
            default:
                break;
        }
    }

    protected void classificarImovelDose(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){

        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {
            case 12:
                if (tipoUtilizacao.getDescricao().equalsIgnoreCase("Faixa de Domínio") || tipoUtilizacao.getDescricao().equalsIgnoreCase("Ponte/Passarela/Viaduto")) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOCOMUMDOPOVO.getCodigo().longValue());
                } else {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }
                break;

            default:
                break;
        }

    }

    protected void classificarImovelTreze(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){
        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()){
            case 13:
                if (tipoUtilizacao.getDescricao().equalsIgnoreCase("Faixa de Domínio")) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOCOMUMDOPOVO.getCodigo().longValue());
                } else  if(tipoUtilizacao.getDescricao().equalsIgnoreCase("Sem informação")) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.SEMINFORMACAO.getCodigo().longValue());
                }else{
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }
                break;
            default:
                break;
        }
    }

    protected void classificarImovelQuatorze(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){

        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {
            case 14:
                if (tipoUtilizacao.getDescricao().equalsIgnoreCase("Estruturas naúticas autorizadas pela ANTAQ (TUP, ETC, IPT, IP4)") ||
                        tipoUtilizacao.getDescricao().equalsIgnoreCase("Marina/Pier") || tipoUtilizacao.getDescricao().equalsIgnoreCase("Outro (especificar)")
                        && destinacaoImovel.getDestinacao().getDadosResponsavel().getCnpj() != null)  {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }else{
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                }

                break;

            default:
                break;
        }

    }

    protected void classificarImovelQuize(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){

        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {
            case 15:
                if (destinacaoImovel.getDestinacao().getDadosResponsavel().getCnpj() != null) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }else{
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                }

                break;

            case 16:
                if (destinacaoImovel.getDestinacao().getDadosResponsavel().getCnpj() != null) {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }else{
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                }

                break;

            default:
                break;
        }

    }

    protected void classificarImovelDezesseDezoito(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){
        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {

            case 17:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                break;

            case 18:
                if (tipoUtilizacao.getDescricao().equalsIgnoreCase("Funcional Militar (PNR)")
                        || tipoUtilizacao.getDescricao().equalsIgnoreCase("Residência Obrigatória/Residência no Interesse do Serviço")) {

                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                }else{
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                }
                break;

            default:
                break;
        }

    }

    protected void classificarImovelVinte(TipoUtilizacao tipoUtilizacao, DestinacaoImovel destinacaoImovel){

        switch (tipoUtilizacao.getNumeroTipoUtilizacao().intValue()) {

            case 19:
                if (tipoUtilizacao.getDescricao().equalsIgnoreCase("Usufruto indígena (Terra/Comunidade indígena)")){
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                } else {
                    destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                }
                break;
            case 20:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.DOMINIAL.getCodigo().longValue());
                break;
            case 21:
                destinacaoImovel.getImovel().setCodigoClassificacaoImovel(ClassificacaoImovelEnum.USOESPECIAL.getCodigo().longValue());
                break;
            default:
                break;
        }

    }

    /**
     *
     * @param rip recebe um RIP e retorna todas as destinações atrelado aquele RIP
     * @return
     */
    public List<DestinacaoDTO> consultarNumeroDestinacao(String rip){

        List<Destinacao> destinacoes = destinacaoRepository.buscarDestinacaoPorRip(rip);

        return converterListaDestinacaoToListDestinacaoDTO(destinacoes);
    }

    private List<DestinacaoDTO> converterListaDestinacaoToListDestinacaoDTO(List<Destinacao> destinacoes) {
        Optional<List<Destinacao>> optional = Optional.ofNullable(destinacoes);
        Boolean existeDestinacao = optional.map(lista -> !lista.isEmpty()).orElse(false);
        if (existeDestinacao) {
            List<DestinacaoDTO> destinacoesDTOs = new ArrayList<>();
            destinacoes.forEach(destinacao -> {
                destinacao.setDestinacaoImoveis(null);
                DestinacaoDTO destinacaoDTO = entityConverter.converterStrict(destinacao, DestinacaoDTO.class);
                destinacoesDTOs.add(destinacaoDTO);
            });
            return destinacoesDTOs;
        }

        return new ArrayList<>();
    }


    /**
     *
     * @param rip Recebe um RIP do imovel
     * @return retorna todas as destinações que possuem um imovel com aquele rip
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Destinacao> consultarDestinacaoNumeroRip(String rip) {
        return destinacaoRepository.buscarDestinacaoPorRip(rip);
    }

    /**
     *
     * @param destinacaoImoveis Recebe um objeto de destinação de imovel e atualiza a area disponivel para zero
     *                          do terreno destinada para benfeitorias que não utilizam parcelas do imovel.
     * @throws NegocioException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizarParcela(List<DestinacaoImovel> destinacaoImoveis) throws NegocioException {
        for (DestinacaoImovel destinacaoImovel : destinacaoImoveis) {
            Imovel imovel = destinacaoImovel.getImovel();
            Parcela parcela = imovel.getParcelas()
                    .stream()
                    .filter(p -> p.getSequencial().equals("P0"))
                    .findFirst()
                    .orElse(null);
            parcelaService.atualizar(destinacaoImovel.getParcelas(), destinacaoImovel);
            parcelaService.atualizarAreaDisponivel(parcela.getId(), BigDecimal.ZERO);
            //removido 22/05/2018 jonatas.sousa
            benfeitoriaService.atualizarAreaDisponivelBenfeitoria(imovel, false);
        }
    }
    /**
     *
     * @param destinacaoImoveis Recebe um objeto de destinação de imovel e atualiza a area disponivel para zero
     *                          do terreno destinada para benfeitorias que não utilizam parcelas do imovel.
     * @throws NegocioException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void copiaParcela(List<DestinacaoImovel> destinacaoImoveis) throws NegocioException {
        for (DestinacaoImovel destinacaoImovel : destinacaoImoveis) {
            Imovel imovel = destinacaoImovel.getImovel();
            List<Parcela> parcelas = new ArrayList<>();
            for(Parcela parcela : destinacaoImovel.getParcelas()){
                Parcela parcelaBanco = parcelaService.buscarParcelaPorId(parcela.getId());
                parcelaBanco.setAtiva(false);
                parcelaRepository.save(parcela);
                Parcela parcelaNova = new Parcela();
                parcelaNova = criarParcelaNova(parcelaNova , parcela, destinacaoImovel, imovel, parcelaBanco);


                if(destinacaoImovel.getFracaoIdeal() != null){
                    BigDecimal areaDisponivel = parcela.getAreaTerreno().subtract(destinacaoImovel.getFracaoIdeal());
                    parcelaNova.setAreaDisponivel(areaDisponivel);
                }else{
                    parcelaNova.setAreaTerreno(parcela.getAreaDisponivel());
                }
                Parcela parcela1 = parcelaRepository.save(parcelaNova);

                parcelas.add(parcela1);
            }



            destinacaoImovel.setParcelas(parcelas);

            destinacaoImovelService.salvar(destinacaoImovel, false);

        }
    }

    private Parcela criarParcelaNova(Parcela parcelaNova,Parcela parcela, DestinacaoImovel destinacaoImovel, Imovel imovel, Parcela parcelaBanco){
        parcelaNova.setAreaDisponivel(parcela.getAreaDisponivel());
        parcelaNova.setListaBenfeitorias(parcela.getListaBenfeitorias());
        parcelaNova.setAtiva(true);
        parcelaNova.setParcelacomPendenciaDestinacao(parcela.getParcelacomPendenciaDestinacao());
        parcelaNova.setAreaTerreno(destinacaoImovel.getImovel().getAreaTerreno());

        if(!parcela.getArquivos().isEmpty()){
            parcelaNova.setArquivos(parcela.getArquivos());
        }
        parcelaNova.setImovel(parcela.getImovel());
        parcelaNova.setMemorialDescritivo(parcela.getMemorialDescritivo());
        parcelaNova.setIdDestinacaoImoveis(parcela.getIdDestinacaoImoveis());
        parcelaNova.setSequencial(parcela.getSequencial());
        parcelaNova.setUtilizada(parcela.getUtilizada());
        parcelaNova.setBenfeitorias(benfeitoriaService.salvar(imovel.getBenfeitorias(), parcelaNova, parcelaBanco.getImovel()));
        parcelaNova.setUtilizada(true);

        parcelaNova.setDestinacaoImoveis(new ArrayList<DestinacaoImovel>());
        parcelaNova.getDestinacaoImoveis().add(destinacaoImovel);

        return parcelaNova;
    }

    protected void atualizarParcelaEspecifica(List<DestinacaoImovel> destinacaoImoveis) {
        List<DestinacaoImovel> destinacoesImoveis = Collections.synchronizedList(destinacaoImoveis);
        destinacoesImoveis.forEach(destinacaoImovel -> {
            parcelaService.atualizar(destinacaoImovel.getParcelas(), destinacaoImovel);
            benfeitoriaService.atualizarAreaDisponivel(destinacaoImovel.getBenfeitoriasDestinadas());
        });
    }


    /**
     *  Desmarca a destinação como a ultima destinada
     * @param destinacaoImoveis
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void desmarcarUltimaDestiancao(List<DestinacaoImovel> destinacaoImoveis, Boolean copiaDestinacao) {
        List<Long> idsImoveis = destinacaoImoveis.stream()
                .map(destinacaoImovel -> destinacaoImovel.getImovel().getId())
                .distinct()
                .collect(Collectors.toList());
        destinacaoImovelService.desmarcarUltimaDestiancaoPorImovel(idsImoveis, copiaDestinacao);
    }

    /**
     *  Desmarca a destinação como a ultima destinada
     * @param destinacaoImoveis
     */
    @Transactional(propagation = Propagation.REQUIRED)
    protected void desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas(List<DestinacaoImovel> destinacaoImoveis) {
        List<Long> idsImoveis = destinacaoImoveis.stream()
                .map(destinacaoImovel -> destinacaoImovel.getImovel().getId())
                .distinct()
                .collect(Collectors.toList());
        destinacaoImovelService.desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas(idsImoveis);
    }

    /**
     * Cancela uma destinacao
     * @param t
     */
    @Transactional(propagation = Propagation.REQUIRED)
    protected void cancelar(T t) {
        Destinacao destinacao = (Destinacao) t;
        destinacao.setStatusDestinacao((StatusDestinacao) dominioService.findById(StatusDestinacaoEnum.CANCELADO.getCodigo(), StatusDestinacao.class));
        destinacaoRepository.save(destinacao);
    }


    /**
     * Verifica se a destinação está sendo usada em algum fundamento legal
     * @param id
     * @return true ou false
     */
    @Transactional(readOnly = true)
    public Boolean isUsadoDestinacao(Long id) {
        List<Long> idsDestinacao = destinacaoRepository.buscarIdsDestinacaoPorFundamentoLegal(id);
        return !idsDestinacao.isEmpty();
    }

    /**
     * Consulta as destinações cadastradas
     * @param filtroDestinacaoDTO
     * @return Resposta<ConsultaDestinacaoDTO>
     */
    @Transactional(readOnly = true)
    public Resposta<List<ConsultaDestinacaoDTO>> consultar(FiltroDestinacaoDTO filtroDestinacaoDTO) throws IntegracaoException {

        Pageable pageable = new PageRequest(filtroDestinacaoDTO.getOffset(),  filtroDestinacaoDTO.getLimit(), Sort.Direction.ASC, "id");

        Page<Object> destinacoesPage = destinacaoRepository.consultar(filtroDestinacaoDTO.getRip(),
                filtroDestinacaoDTO.getCodigoUtilizacao(),
                filtroDestinacaoDTO.getCodigoParcela(),
                filtroDestinacaoDTO.getIdTipoUtilizacao(),
                filtroDestinacaoDTO.getIdSubTipoUtilizacao(),
                filtroDestinacaoDTO.getPais(),
                filtroDestinacaoDTO.getCep(),
                filtroDestinacaoDTO.getUf(),
                filtroDestinacaoDTO.getMunicipio(),
                filtroDestinacaoDTO.getCidadeExterior(),
                filtroDestinacaoDTO.getTiposDestinacao(),
                filtroDestinacaoDTO.getNomeResponsavel(),
                filtroDestinacaoDTO.getCpfCnpjResponsavel(),
                filtroDestinacaoDTO.getFracaoIdealInicial(),
                filtroDestinacaoDTO.getFracaoIdealFinal(),
                filtroDestinacaoDTO.getAreaConstruidaInicial(),
                filtroDestinacaoDTO.getAreaConstruidaFinal(),
                filtroDestinacaoDTO.getCodigoContrato(),
                filtroDestinacaoDTO.getClassificacao(),

                pageable);


        Resposta<List<ConsultaDestinacaoDTO>> resposta = RespostaBuilder
                .getBuilder()
                .setResultado(criarListaRespostaBuscarDadosIncorporacao(destinacoesPage.getContent()))
                .setTotalPaginas(destinacoesPage.getTotalPages())
                .setTotalElementos(destinacoesPage.getTotalElements())
                .build();

        return resposta;
    }

    /**
     * buscar historico
     * @return Resposta<ConsultaDestinacaoDTO>
     */
    @Transactional(readOnly = true)
    public Resposta<List<ConsultaDestinacaoDTO>> buscarHistoricoDestinacao(Long idDestinacao, Long idVersao) throws IntegracaoException {

        List<Object> destinacoesPage = destinacaoRepository.buscarHistoricoDestinacoes(idDestinacao, idVersao);

        Resposta<List<ConsultaDestinacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(criarListaRespostaBuscarDadosIncorporacao(destinacoesPage)).build();

        return resposta;
    }

    /**
     * buscar versoes
     * @return Resposta<ConsultaDestinacaoDTO>
     */
    @Transactional(readOnly = true)
    public List<Destinacao> buscarListaVersoesDestinacoesService(String rip) throws IntegracaoException {
        List<Destinacao> lista = destinacaoRepository.buscarListaVersoesDestinacoes(rip);

        for(Destinacao destinacao : lista){
           String data  = DataUtil.formataDataHora(destinacao.getDataDestinacaoHistorico(), DataUtil.DD_MM_YY_HH_MM);
            destinacao.setDataHistoricoFormatada(data);
        }
        return lista;
    }


    @Transactional(readOnly = true)
    public Resposta<List<ConsultarUtilizacaoDTO>> consultarUtilizacao(FiltroDestinacaoDTO filtroDestinacaoDTO, FundamentoLegalDTO fundamentoLegalDTO) throws IntegracaoException {

        Pageable pageable = new PageRequest(filtroDestinacaoDTO.getOffset(),  filtroDestinacaoDTO.getLimit(), Sort.Direction.ASC, "id");

        Page<Object> destinacoesPage = destinacaoRepository.consultarUtilizacao(filtroDestinacaoDTO.getRip(),
                filtroDestinacaoDTO.getCodigoUtilizacao(),
                filtroDestinacaoDTO.getCodigoParcela(),
                filtroDestinacaoDTO.getPais(),
                filtroDestinacaoDTO.getCep(),
                filtroDestinacaoDTO.getUf(),
                filtroDestinacaoDTO.getMunicipio(),
                filtroDestinacaoDTO.getCidadeExterior(),
                filtroDestinacaoDTO.getTiposDestinacao(),
                pageable);


        Resposta<List<ConsultarUtilizacaoDTO>> resposta = RespostaBuilder
                .getBuilder()
                .setResultado(criarListaRespostaBuscarDadosIncorporacaoUtilizacao(destinacoesPage.getContent(), fundamentoLegalDTO))
                .setTotalPaginas(destinacoesPage.getTotalPages())
                .setTotalElementos(destinacoesPage.getTotalElements())
                .build();

        return resposta;
    }

    @Transactional(readOnly = true)
    public Resposta<List<ConsultaDestinacaoDTO>> consultarDestinacaoPendencia(String pendencia) throws IntegracaoException {
        List<Object> resultado = destinacaoRepository.buscarDestinacaoPorPendencia(pendencia);
        Resposta<List<ConsultaDestinacaoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(criarListaRespostaBuscarDadosIncorporacao(resultado)).build();

        return resposta;
    }


    @Transactional(readOnly = true)
    public Resposta<List<String>> consultarCidades(String pais, String dadosUtilizacao){
        String rip = dadosUtilizacao.substring(Constants.ZERO,Constants.OITO);
        String codigoUtilizacao = dadosUtilizacao.substring(Constants.OITO,Constants.DOZE);
        Integer numParcela = Integer.valueOf(dadosUtilizacao.substring(Constants.TREZE,dadosUtilizacao.length()));
        String codigoParcela = dadosUtilizacao.substring(Constants.DOZE,Constants.TREZE)+numParcela;
        List<String> resultado = destinacaoRepository.consultarCidades(pais, rip, codigoUtilizacao, codigoParcela);
        Resposta<List<String>> resposta;
        if(resultado.isEmpty()){
            resposta = RespostaBuilder.getBuilder().setResultado(new ArrayList<>()).build();
        }
        else{
            resposta = RespostaBuilder.getBuilder().setResultado(resultado).build();
        }
        return resposta;
    }

    /**
     * Metodo que busca os dados das Destinações para o Módulo de Avaliação
     * @param rip
     * @return
     */
    @Transactional(readOnly = true)
    public List<ConsultarDadosUtilizacaoAvaliacaoDTO> buscarDadosDestinacaoAvaliacao(String rip) {
        List<DestinacaoImovel> dados = destinacaoRepository.buscarDadosDestinacaoAvaliacao(rip, TipoDestinacaoEnum.SEM_UTILIZACAO.getDescricao());

        Type targetListType = new TypeToken<List<ConsultarDadosUtilizacaoAvaliacaoDTO>>() {}.getType();
        return entityConverter.converterListaStrictLazyLoading(dados, targetListType);
    }

    private List<ConsultaDestinacaoDTO> criarListaRespostaBuscarDadosIncorporacao(List<Object> destinacoes) throws IntegracaoException {
        List<ConsultaDestinacaoDTO> retorno = new ArrayList<>();
        for (Object arrayParametros : destinacoes) {
            ConsultaDestinacaoDTO consultaDestinacaoDTO = new ConsultaDestinacaoDTO();
            Object [] objeto = (Object[]) arrayParametros;
            consultaDestinacaoDTO.setId(CastObjectUtil.cast(objeto[Constants.ZERO], Long.class));
            consultaDestinacaoDTO.setRip(CastObjectUtil.cast(objeto[Constants.UM], String.class));
            consultaDestinacaoDTO.setCodigoUtilizacao(CastObjectUtil.cast(objeto[Constants.DOIS], String.class));
            consultaDestinacaoDTO.setSequencialParcela(CastObjectUtil.cast(objeto[Constants.TRES], String.class));
            consultaDestinacaoDTO.setTipoDestinacao(criarObjetosTipo(objeto[Constants.QUATRO], objeto[Constants.CINCO]));
            consultaDestinacaoDTO.setTipoUtilizacao(criarObjetosTipo(objeto[Constants.SEIS], objeto[Constants.SETE]));
            consultaDestinacaoDTO.setSubTipoUtilizacao(criarObjetosTipo(objeto[Constants.OITO], objeto[Constants.NOVE]));
            consultaDestinacaoDTO.setFracaoIdeal(CastObjectUtil.cast(objeto[Constants.DEZ], BigDecimal.class));
            consultaDestinacaoDTO.setAreaConstruida(CastObjectUtil.cast(objeto[Constants.ONZE], BigDecimal.class));
            consultaDestinacaoDTO.setNomeResponsavel(CastObjectUtil.cast(objeto[Constants.DOZE], String.class));
            consultaDestinacaoDTO.setCpfCnpj(CastObjectUtil.cast(objeto[Constants.TREZE], String.class));
            consultaDestinacaoDTO.setEndereco(criarEnderecoDTO(objeto));
            consultaDestinacaoDTO.setAreaTerreno(CastObjectUtil.cast(objeto[Constants.VINTE_QUATRO], BigDecimal.class));
            Date dataInico = CastObjectUtil.cast(objeto[Constants.VINTE_CINCO], Date.class);
            Date dataFim = CastObjectUtil.cast(objeto[Constants.VINTE_SEIS], Date.class);
            consultaDestinacaoDTO.setDataInicioContrato(DataUtil.formataDataHora(dataInico, DataUtil.DD_MM_YYYY));
            consultaDestinacaoDTO.setDataFimContrato(DataUtil.formataDataHora(dataFim, DataUtil.DD_MM_YYYY));
            consultaDestinacaoDTO.setStatusDestinacao(criarObjetosTipo(objeto[Constants.VINTE_SETE], objeto[Constants.VINTE_OITO]));
            consultaDestinacaoDTO.setCodigoClassificacaoImovel(CastObjectUtil.cast(objeto[Constants.VINTE_NOVE], Long.class));
            consultaDestinacaoDTO.setAtiva(CastObjectUtil.cast(objeto[Constants.TRINTA], Boolean.class));
            Date dataHistorico =  CastObjectUtil.cast(objeto[Constants.TRINTA_UM], Date.class);
            consultaDestinacaoDTO.setDataDestinacaoHistorico(DataUtil.formataDataHora(dataHistorico, DataUtil.DD_MM_YY_HH_MM));
            consultaDestinacaoDTO.setVersaoDestinacao(CastObjectUtil.cast(objeto[Constants.TRINTA_DOIS], Long.class));
            consultaDestinacaoDTO.setDataInicioFundamento(CastObjectUtil.cast(objeto[Constants.TRINTA_TRES], Date.class));
            consultaDestinacaoDTO.setDataFimFundamento(CastObjectUtil.cast(objeto[Constants.TRINTA_QUATRO], Date.class));

            consultarImovelIncorporacao(consultaDestinacaoDTO);
            retorno.add(consultaDestinacaoDTO);
        }

        retorno = retorno
                .stream()
                .filter(c -> usuarioService.getUsuarioLogado().getJurisdicoes().contains(c.getEndereco().getUf()))
                .collect(Collectors.toList());

        return retorno;
    }

    private List<ConsultarUtilizacaoDTO> criarListaRespostaBuscarDadosIncorporacaoUtilizacao(List<Object> destinacoes, FundamentoLegalDTO fundamentoLegalDTO) throws IntegracaoException {
        List<ConsultarUtilizacaoDTO> retorno = new ArrayList<>();
        for (Object arrayParametros : destinacoes) {
            ConsultarUtilizacaoDTO consultarUtilzacaoDTO= new ConsultarUtilizacaoDTO();
            Object [] objeto = (Object[]) arrayParametros;
            consultarUtilzacaoDTO.setId(CastObjectUtil.cast(objeto[Constants.ZERO], Long.class));
            consultarUtilzacaoDTO.setImovel(CastObjectUtil.cast(objeto[Constants.UM], Imovel.class));
            consultarUtilzacaoDTO.setCodigoUtilizacao(CastObjectUtil.cast(objeto[Constants.DOIS], String.class));
            consultarUtilzacaoDTO.setSequencialParcela(CastObjectUtil.cast(objeto[Constants.TRES], String.class));
            consultarUtilzacaoDTO.setTipoDestinacao(criarObjetosTipo(objeto[Constants.QUATRO], objeto[Constants.CINCO]));
            consultarUtilzacaoDTO.setTipoUtilizacao(criarObjetosTipo(objeto[Constants.SEIS], objeto[Constants.SETE]));
            consultarUtilzacaoDTO.setSubTipoUtilizacao(criarObjetosTipo(objeto[Constants.OITO], objeto[Constants.NOVE]));
            consultarUtilzacaoDTO.setFracaoIdeal(CastObjectUtil.cast(objeto[Constants.DEZ], BigDecimal.class));
            consultarUtilzacaoDTO.setAreaConstruida(CastObjectUtil.cast(objeto[Constants.ONZE], BigDecimal.class));
            consultarUtilzacaoDTO.setNomeResponsavel(CastObjectUtil.cast(objeto[Constants.DOZE], String.class));
            consultarUtilzacaoDTO.setCpfCnpj(CastObjectUtil.cast(objeto[Constants.TREZE], String.class));
            consultarUtilzacaoDTO.setEndereco(criarEnderecoDTO(objeto));
            consultarUtilzacaoDTO.setAreaTerreno(CastObjectUtil.cast(objeto[Constants.VINTE_QUATRO], BigDecimal.class));
            Date dataInico = CastObjectUtil.cast(objeto[Constants.VINTE_CINCO], Date.class);
            Date dataFim = CastObjectUtil.cast(objeto[Constants.VINTE_SEIS], Date.class);
            consultarUtilzacaoDTO.setDataInicioContrato(DataUtil.formataDataHora(dataInico, DataUtil.DD_MM_YYYY));
            consultarUtilzacaoDTO.setDataFimContrato(DataUtil.formataDataHora(dataFim, DataUtil.DD_MM_YYYY));
            consultarUtilzacaoDTO.setStatusDestinacao(criarObjetosTipo(objeto[Constants.VINTE_SETE], objeto[Constants.VINTE_OITO]));
            consultarUtilzacaoDTO.setCodigoClassificacaoImovel(CastObjectUtil.cast(objeto[Constants.VINTE_NOVE], Long.class));
            if(imovelValido(consultarUtilzacaoDTO.getImovel(), fundamentoLegalDTO)){
                retorno.add(consultarUtilzacaoDTO);
            }

        }

        retorno = retorno
                .stream()
                .filter(c -> usuarioService.getUsuarioLogado().getJurisdicoes().contains(c.getEndereco().getUf()))
                .collect(Collectors.toList());

        return retorno;
    }

    private Boolean imovelValido(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO){
        return validarNatureza(imovel, fundamentoLegalDTO) && validarTipoImovel(imovel, fundamentoLegalDTO) && validarSituacaoIncorporacao(imovel, fundamentoLegalDTO)
                && validarFormaDeIncorporacao(imovel, fundamentoLegalDTO) && validarTipoAquisicaoFundamento(imovel, fundamentoLegalDTO) && validarEntidadeExtinta(imovel, fundamentoLegalDTO);

    }

    private Boolean validarNatureza(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO){
        if(imovel.getCodigoNaturezaImovel().equals(RURAL)){
            if(fundamentoLegalDTO.getIsNaturezaRural() == null || !fundamentoLegalDTO.getIsNaturezaRural()) {
                return false;
            }
        }else {
            if (fundamentoLegalDTO.getIsNaturezaUrbana() == null || !fundamentoLegalDTO.getIsNaturezaUrbana()){
                return false;
            }
        }
        return true;
    }

    private Boolean validarTipoImovel(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO){
        if(imovel.getCodigoTipoImovel().equals(ESPELHO_DAGUA)){
            if(fundamentoLegalDTO.getIsPermitirImoveisEspelhoDAgua() == null || !fundamentoLegalDTO.getIsPermitirImoveisEspelhoDAgua() ){
                return false;
            }
        } else{
            if(imovel.getCodigoTipoImovel().equals(CAVIDADE_NATURAL) && isNaoPermitido(fundamentoLegalDTO)){
                return false;
            }
        }
        return true;
    }

    private static Boolean isNaoPermitido(FundamentoLegalDTO fundamentoLegalDTO){
        return fundamentoLegalDTO.getIsPermitirImoveisCavidadeNatural() == null || !fundamentoLegalDTO.getIsPermitirImoveisCavidadeNatural();
    }

    private Boolean validarSituacaoIncorporacao(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO){
        if (imovel.getCodigoSituacaoIncorporacao().equals(INCORPORADO)){
            if(fundamentoLegalDTO.getIsincorporado() == null || !fundamentoLegalDTO.getIsincorporado()){
                return false;
            }
        } else if(imovel.getCodigoSituacaoIncorporacao().equals(EM_PROCESSO_DE_INCORPORACAO)){
            if(fundamentoLegalDTO.getIsEmProcessoIncorporacao() == null || !fundamentoLegalDTO.getIsEmProcessoIncorporacao()){
                return false;
            }
        } else {
            if(fundamentoLegalDTO.getIsNaoSeAplica() == null || !fundamentoLegalDTO.getIsNaoSeAplica()){
                return false;
            }
        }
        return true;
    }

    private Boolean validarFormaDeIncorporacao(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO){
        FormaDeIncorporacaoEnum formaDeIncorporacaoEnum = FormaDeIncorporacaoEnum.getByCodigo(imovel.getCodigoFormaIncorporacao());
        if(formaDeIncorporacaoEnum != null){
            List<FormaDeIncorporacaoPermitidaDTO> formaDeIncorporacaoPermitida = fundamentoLegalDTO.getFormaDeIncorporacaoPermitidas().stream().filter(f -> f.getDescricao().equals(formaDeIncorporacaoEnum.getDescricao())).collect(Collectors.toList());
            return formaDeIncorporacaoPermitida.isEmpty();

        }
        return false;
    }

    private Boolean validarTipoAquisicaoFundamento(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO){
        if(imovel.getCodigoFormaIncorporacao().equals(POR_AQUISICAO)){
            TipoAquisicaoEnum tipoAquisicaoEnum = TipoAquisicaoEnum.getByCodigo(imovel.getCodigoTipoAquisicao());
            List<TipoAquisicaoPermitidaDTO> tipoAquisicaoPermitida = fundamentoLegalDTO.getTipoAquisicaoPermitidas().stream().filter(f -> f.getDescricao().equals(tipoAquisicaoEnum.getDescricao())).collect(Collectors.toList());
            if(tipoAquisicaoPermitida.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private Boolean validarEntidadeExtinta(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO){
        if (imovel.getCodigoTipoAquisicao() != null && imovel.getCodigoTipoAquisicao().equals(SUCESSAO_POR_ENTIDADES)){
            EntidadeExtintaEnum entidadeExtintaEnum = EntidadeExtintaEnum.getByCodigo(imovel.getCodigoEntidadeExtinta());
            List<TipoAquisicaoPermitidaDTO> tipoAquisicaoPermitida = fundamentoLegalDTO.getTipoAquisicaoPermitidas().stream().filter(f -> f.getDescricao().equals(entidadeExtintaEnum.getDescricao())).collect(Collectors.toList());
            if(tipoAquisicaoPermitida.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private void consultarImovelIncorporacao(ConsultaDestinacaoDTO consultaDestinacaoDTO) throws IntegracaoException {
        ImovelDTO imovelDTO = imovelService.getImovelCadastroImoveis(consultaDestinacaoDTO.getRip());
        consultaDestinacaoDTO.setLongitude(imovelDTO.getLongitude());
        consultaDestinacaoDTO.setLatitude(imovelDTO.getLatitude());
        Optional<List<ImagemDTO>> optional = Optional.ofNullable(imovelDTO.getImagens());
        Boolean listaPreenchida = optional.map(lista -> !lista.isEmpty()).orElse(false);
        if (listaPreenchida) {
            ImagemDTO imagemDTO = imovelDTO.getImagens().get(Constants.ZERO);
            consultaDestinacaoDTO.setImagem(imagemDTO.getImagem());
        }
    }

    private DominioDTO criarObjetosTipo(Object id, Object descricao) {
        DominioDTO dominiioDTO = new DominioDTO();
        dominiioDTO.setId(CastObjectUtil.cast(id, Integer.class));
        dominiioDTO.setDescricao(CastObjectUtil.cast(descricao, String.class));
        return dominiioDTO;
    }

    public EnderecoDTO criarEnderecoDTO(Object... objetos) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep(CastObjectUtil.cast(objetos[Constants.QUATORZE], String.class));
        enderecoDTO.setTipoLogradouro(CastObjectUtil.cast(objetos[Constants.QUINZE], String.class));
        enderecoDTO.setLogradouro(CastObjectUtil.cast(objetos[Constants.DEZESSEIS], String.class));
        enderecoDTO.setNumero(CastObjectUtil.cast(objetos[Constants.DEZESETE], String.class));
        enderecoDTO.setComplemento(CastObjectUtil.cast(objetos[Constants.DEZOITO], String.class));
        enderecoDTO.setMunicipio(CastObjectUtil.cast(objetos[Constants.DEZENOVE], String.class));
        enderecoDTO.setBairro(CastObjectUtil.cast(objetos[Constants.VINTE], String.class));
        enderecoDTO.setUf(CastObjectUtil.cast(objetos[Constants.VINTE_UM], String.class));
        enderecoDTO.setPais(CastObjectUtil.cast(objetos[Constants.VINTE_DOIS], String.class));
        enderecoDTO.setCidadeExterior(CastObjectUtil.cast(objetos[Constants.VINTE_TRES], String.class));
        return enderecoDTO;
    }

    public Resposta findOne(Long id) throws IntegracaoException, IOException{
        DestinacaoDTO destinacaoDTO = entityConverter.converterStrict(destinacaoRepository.findOne(id), DestinacaoDTO.class);
        montarDadosMapa(destinacaoDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(destinacaoDTO).build();
    }

    protected void montarDadosMapa(List<DestinacaoImovelDTO> destinacaoImovelDTOs) throws IntegracaoException, IOException{
        for (DestinacaoImovelDTO destinacaoImovelDTO : destinacaoImovelDTOs) {
            try {
                ImovelDTO imovelDTOIncorporacao = imovelService.getImovelCadastroImoveis(destinacaoImovelDTO.getImovel().getRip());
                destinacaoImovelDTO.getImovel().setLongitude(imovelDTOIncorporacao.getLongitude());
                destinacaoImovelDTO.getImovel().setLatitude(imovelDTOIncorporacao.getLatitude());
                destinacaoImovelDTO.getImovel().setImagens(imovelDTOIncorporacao.getImagens());
                destinacaoImovelDTO.getImovel().setMemorialDescritivo(imovelDTOIncorporacao.getMemorialDescritivo());
                destinacaoImovelDTO.setFotoVideo(arquivoService.prepararListaFotoVideo(destinacaoImovelDTO.getFotoVideo()));
            } catch (IntegracaoException e) {
                LOGGER.error("ERRO CONSULTAR IMOVEL NO INCORPORACAO", e);
                throw new IntegracaoException(e.getMessage(), e);
            }
        }
    }

}
