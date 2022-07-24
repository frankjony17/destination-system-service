package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.dominio.entidades.SemUtilizacao;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.UnidadeAutonoma;
import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoOperacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.SemUtilizacaoRepository;
import br.com.company.fks.destinacao.service.BenfeitoriaService;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import br.com.company.fks.destinacao.service.DominioService;
import br.com.company.fks.destinacao.service.ImovelService;
import br.com.company.fks.destinacao.service.ParcelaService;
import br.com.company.fks.destinacao.service.TipoUtilizacaoService;
import br.com.company.fks.destinacao.service.UtilizacaoService;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.service.validadores.ValidadorStrategy;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by diego on 31/01/17.
 */

/**
 * Classe para o terreno que vem sem utilização lá do Cadastro de Imoveis
 */
@Service
public class SemUtilizacaoService extends DestinacaoService<SemUtilizacao> {

    @Autowired
    private DestinacaoImovelService destinacaoImovelService;

    @Autowired
    private SemUtilizacaoRepository semUtilizacaoRepository;

    @Autowired
    private TipoUtilizacaoService tipoUtilizacaoService;

    @Autowired
    private ValidadorStrategy validadorStrategy;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private DominioService dominioService;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private BenfeitoriaService benfeitoriaService;

    @Autowired
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Autowired
    private ParcelaService parcelaService;

    @Autowired
    private UtilizacaoService utilizacaoService;

    /**
     *
     * @param semUtilizacao Salva localmmente esse objeto que ainda não foi destinada alguma destinação
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public SemUtilizacao salvar(SemUtilizacao semUtilizacao) {
        SemUtilizacao semUtilizacaoSalva = semUtilizacaoRepository.save(semUtilizacao);
        semUtilizacaoSalva.getDestinacaoImoveis()
                .forEach(destinacaoImovel -> {
                    destinacaoImovel.setDestinacao(semUtilizacaoSalva);
                    destinacaoImovel.setUltimaDestinacao(true);
                    Imovel imovel = destinacaoImovel.getImovel();
                    imovel.setAtivo(Boolean.TRUE);
                    if (imovel.getUnidadeAutonoma() != null) {
                        UnidadeAutonoma unidadeAutonoma = destinacaoImovel.getImovel().getUnidadeAutonoma();
                        unidadeAutonoma.setAreaDisponivel(unidadeAutonoma.getArea());
                    }

                });
        semUtilizacaoSalva.setDestinacaoImoveis(destinacaoImovelService.salvar(semUtilizacaoSalva.getDestinacaoImoveis()));
        return semUtilizacaoSalva;
    }

    @Override
    public SemUtilizacao salvarDadosEspecificos(SemUtilizacao destinacao) {
        throw new NotImplementedException();
    }

    /**
     * Salva a destinação sem utlização com a area total do imovel
     * @param imovel
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Destinacao salvar(Imovel imovel) throws NegocioException {

        if (imovel.getIndicadorOperacao() == TipoOperacaoEnum.CADASTRO) {
            return salvarNovaDestinacao(imovel);
        } else {
            return atualizar(imovel);
        }

    }

    private Destinacao atualizar(Imovel imovel) throws NegocioException {

        Map<Long, Boolean> pendenciasGeradas = criarMapControleGerarPendencia();

        Destinacao destinacao = semUtilizacaoRepository.buscarDestinacaoRipImovel(imovel.getRip());


        long qtdPendencia = 0;
        for (DestinacaoImovel destinacaoImovel : destinacao.getDestinacaoImoveis()) {
            imovelService.atualizar(imovel, pendenciasGeradas);
            atualizarBenfeitorias(destinacaoImovel.getParcelas(), imovel, pendenciasGeradas);

            qtdPendencia += destinacaoPendenciaService.buscarPendenciasPorIdDestinacao(destinacaoImovel.getDestinacao().getId()).size();

            if(qtdPendencia < 1) {
                gerarPendencia(destinacao, pendenciasGeradas);
            }
        }


        return destinacao;
    }

    private void atualizarBenfeitorias(List<Parcela> parcelas, Imovel imovel, Map<Long, Boolean> pendenciasGeradas) throws NegocioException {
        if (parcelas.size() == Constants.UM) {
            Parcela parcela = parcelas.get(Constants.ZERO);
            benfeitoriaService.atualizar(parcela, imovel.getBenfeitorias(), pendenciasGeradas, parcela.getBenfeitorias(), parcela.getImovel());
        } else {
            List<Parcela> parcelasAtivas = parcelaService.extrairParcelasAtivas(parcelas);
            Imovel imovelSalvo = imovelService.extrairImovelParcela(parcelasAtivas);
            List<Benfeitoria> benfeitoriasSalvas = benfeitoriaService.extrairBenfeitoriasParcelasSalvas(parcelasAtivas);
            benfeitoriasSalvas.addAll(benfeitoriaService.buscarBenfeitoriasSemParcelaIdImovel(imovelSalvo.getId()));
            benfeitoriaService.atualizar(null, imovel.getBenfeitorias(), pendenciasGeradas, benfeitoriasSalvas, imovelSalvo);
        }
    }

    private Map<Long, Boolean> criarMapControleGerarPendencia() {
        Map<Long, Boolean> pendenciasGeradas = new HashedMap();
        pendenciasGeradas.put(Constants.ID_PENDENCIA_AJUSTE_DESTINACAO_IMOVEL, Boolean.FALSE);
        pendenciasGeradas.put(Constants.ID_PENDENCIA_AJUSTE_PARCELA_IMOVEL, Boolean.FALSE);
        return pendenciasGeradas;
    }

    private void gerarPendencia(Destinacao destinacao, Map<Long, Boolean> pendenciasGeradas) {
        if (pendenciasGeradas.get(Constants.ID_PENDENCIA_AJUSTE_DESTINACAO_IMOVEL)) {
            destinacaoPendenciaService.gerarPendencia(destinacao, Constants.ID_PENDENCIA_AJUSTE_DESTINACAO_IMOVEL);
        }

        if (pendenciasGeradas.get(Constants.ID_PENDENCIA_AJUSTE_PARCELA_IMOVEL)) {
            destinacaoPendenciaService.gerarPendencia(destinacao, Constants.ID_PENDENCIA_AJUSTE_PARCELA_IMOVEL);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void cancelar(String rip) throws NegocioException {

        Destinacao destinacao = buscarDestinacaoRipImovel(rip);
        Imovel imovel = getImovel(destinacao.getDestinacaoImoveis(), rip);

        if (isDestinacaoUtilizandoImovel(imovel)) {
            throw new NegocioException(mensagemNegocio.get("imovel.destinado"));
        }

        cancelar((SemUtilizacao) destinacao);
        imovelService.inativar(imovel);
    }

    private Destinacao buscarDestinacaoRipImovel(String rip) throws NegocioException {
        Destinacao destinacao = semUtilizacaoRepository.buscarDestinacaoRipImovel(rip);
        if (destinacao == null) {
            throw new NegocioException(mensagemNegocio.get("destinacao.nao.existe"));
        }
        return destinacao;
    }

    private Imovel getImovel(List<DestinacaoImovel> destinacaoImoveis, String rip) throws NegocioException {
        return destinacaoImoveis.stream().filter(
                destinacaoImovel ->
                    destinacaoImovel.getImovel().getRip().equals(rip))
                    .map(DestinacaoImovel::getImovel)
                    .findFirst()
                    .orElseThrow(() -> new NegocioException(mensagemNegocio.get("imovel.nao.encontrado")));
    }

    private Destinacao salvarNovaDestinacao(Imovel imovel) throws NegocioException {

        List<DestinacaoImovel> destinacaoImoveis = new ArrayList<>();
        destinacaoImoveis.add(criarDestinacaoImovel(imovel));

        SemUtilizacao semUtilizacao = criarDestinacao(destinacaoImoveis);
        ValidadorDestinacao validadorDestinacao = validadorStrategy.createBean(TipoDestinacaoEnum.SEM_UTILIZACAO);
        validadorDestinacao.validadorEspecifico(semUtilizacao);

        return salvar(semUtilizacao);
    }

    private DestinacaoImovel criarDestinacaoImovel(Imovel imovel) {
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        destinacaoImovel.setCodigoUtilizacao("0000");
        destinacaoImovel.setParcelas(imovel.getParcelas());
        return destinacaoImovel;
    }

    private TipoDestinacao criarTipoDestinacaoSemUtilizacao() {
        TipoDestinacao tipoDestinacao = new TipoDestinacao();
        tipoDestinacao.setId(TipoDestinacaoEnum.SEM_UTILIZACAO.getCodigo());
        return tipoDestinacao;
    }

    private SemUtilizacao criarDestinacao(List<DestinacaoImovel> destinacaoImoveis) {
        SemUtilizacao semUtilizacao = new SemUtilizacao();
        semUtilizacao.setDestinacaoImoveis(destinacaoImoveis);
        semUtilizacao.setTipoDestinacaoEnum(TipoDestinacaoEnum.SEM_UTILIZACAO);
        semUtilizacao.setStatusDestinacao((StatusDestinacao) dominioService.findById(Constants.UM, StatusDestinacao.class));
        semUtilizacao.setTipoDestinacao(criarTipoDestinacaoSemUtilizacao());
        semUtilizacao.setUtilizacao(criarCodigoUtilizacao());
        semUtilizacao.setVersaoDestinacao(0l);
        semUtilizacao.setAtiva(true);
        semUtilizacao.setDataDestinacaoHistorico(new Date());
        return semUtilizacao;
    }

    private Boolean isDestinacaoUtilizandoImovel(Imovel imovel) {
        Long quantidade = semUtilizacaoRepository.getQuantidadeDestinacoesImovel(imovel.getRip());
        return quantidade > Constants.ZERO;
    }

    private Utilizacao criarCodigoUtilizacao(){
        return utilizacaoService.selecionaSemUsoVago();
    }

}
