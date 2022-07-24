package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.dto.BenfeitoriaDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoPendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.ParcelaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ParcelaRepository;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by diego on 01/03/17.
 */
@Service
public class ParcelaService {

    private static final String SEQ_INICIAL = "P0";

    private static final String CODIGO_UTILIZACAO = "0001";

    @Autowired
    private BenfeitoriaService benfeitoriaService;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private DestinacaoImovelService destinacaoImovelService;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DestinacaoPendenciaService destinacaoPendenciaService;



    @Transactional(propagation = Propagation.REQUIRED)
    public List<Parcela> criarNovasParcelas(List<ParcelaDTO> parcelasDTO) {
        List<Parcela> parcelas = new ArrayList<>();
        inativarParcela(parcelasDTO);
        parcelasDTO.forEach(parcelaDTO -> {
            Imovel imovel = imovelService.findById(parcelaDTO.getIdImovel());
            List<DestinacaoImovel> destinacaoImoveis = destinacaoImovelService.findByIds(parcelaDTO.getIdDestinacaoImoveis());
            Parcela parcelaSalva = salvar(parcelaDTO, imovel, destinacaoImoveis);
            parcelas.add(parcelaSalva);
        });
        return parcelas;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<ParcelaDTO> buscarParcelasPorIdImovel (String rip) {
        List<Parcela> parcelas = parcelaRepository.buscarParcelasPorId(rip);
        return converter(parcelas);
    }

    private Parcela salvar(ParcelaDTO parcelaDTO, Imovel imovel, List<DestinacaoImovel> destinacaoImoveis) {
        Parcela parcela = entityConverter.converterStrict(parcelaDTO, Parcela.class);
        parcela.setImovel(imovel);
        parcela.setAtiva(true);
        parcela.setArquivos(recuperarArquivoParcela(parcela.getArquivos()));
        parcela.setAreaDisponivel(parcela.getAreaTerreno());
        parcela.setDestinacaoImoveis(destinacaoImoveis);
        salvarBenfeitorias(parcela);
        Parcela parcelaSalva = parcelaRepository.save(parcela);
        return parcelaSalva;
    }

    private void inativarParcela(List<ParcelaDTO> parcelasDTO) {

        Set<Long> idsParcelasInativar = parcelasDTO.stream().map(ParcelaDTO::getIdParcelaInativar).collect(Collectors.toSet());

        idsParcelasInativar.forEach(id -> {
            Parcela parcela = parcelaRepository.findOne(id);
            parcela.setAtiva(false);
            parcelaRepository.save(parcela);
            benfeitoriaService.inativar(parcela.getBenfeitorias());
        });

    }

    private void salvarBenfeitorias(Parcela parcela) {
        if (parcela.getBenfeitorias() != null) {
            benfeitoriaService.salvar(parcela.getBenfeitorias(), parcela, parcela.getImovel());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Parcela buscarParcelaPorId(Long idParcela) {
        return  parcelaRepository.buscarParcelaPorId(idParcela);
    }


    private Integer buscarUltimaSequenciaParcela(Parcela parcelaCriada){
        Integer maior = Constants.ZERO;
        Integer atual;
        List<Imovel> imoveis = imovelService.buscarImoveisPorRip(parcelaCriada.getImovel().getRip());
        for (Imovel imovel : imoveis) {
            for (Parcela parcela : imovel.getParcelas()) {
                atual = Integer.valueOf(parcela.getSequencial().substring(Constants.UM));
                if(maior < atual){
                    maior = atual;
                }
            }
        }
        return maior;
    }



    /**
     * Inativa uma lista de parcelas.
     * @param parcelas
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void inativar(List<Parcela> parcelas) {
        parcelas.forEach(parcela -> {
            parcela.setAtiva(Boolean.FALSE);
            parcelaRepository.save(parcela);
        });
    }

    /**
     * Cria uma nova parcela.
     * @param imovel
     * @return Retorna uma parcela salva.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Parcela salvarParcelaInicial(Imovel imovel, DestinacaoImovel destinacaoImovel) {
        Parcela parcela = new Parcela();
        parcela.setAreaTerreno(imovel.getAreaTerreno());
        parcela.setAtiva(Boolean.TRUE);
        parcela.setSequencial(SEQ_INICIAL);
        parcela.setAreaDisponivel(imovel.getAreaTerreno());
        parcela.setImovel(imovel);
        parcela.setDestinacaoImoveis(Arrays.asList(destinacaoImovel));
        return parcelaRepository.save(parcela);
    }

    /**
     * Atualiza a area disponivel da parcela selecionada.
     * @param id
     * @param areaDisponivel
     * @return Retorna uma parcela atualizada.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Parcela atualizarAreaDisponivel(Long id, BigDecimal areaDisponivel) {
        Parcela parcela = parcelaRepository.findOne(id);
        parcela.setAreaDisponivel(areaDisponivel);
        return parcelaRepository.save(parcela);
    }

    /**
     * Atualiza a area disponivel da parcela selecionada.
     * @return Retorna uma parcela atualizada.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Parcela salvarParcela(Parcela parcela) {
        return parcelaRepository.save(parcela);
    }

    private List<Arquivo> recuperarArquivoParcela(List<Arquivo> arquivos){
        List<Arquivo> arquivosParcela = new ArrayList<>();
        if(arquivos != null){
            for (Arquivo arquivo : arquivos) {
                Arquivo parcelaArquivo = arquivoService.findById(arquivo.getId());
                arquivosParcela.add(parcelaArquivo);
            }
        }
        return arquivosParcela;
    }

    /**
     * Exclui a parcela de forma logica e cria uma nova apartir dela
     * @param parcelaExcluida
     * @param parcelaAtribuida
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void excluirParcela(Parcela parcelaExcluida, Parcela parcelaAtribuida, String rip) {

        Integer numeroParcelas = parcelaRepository.buscarNumeroParcelas(rip);

        parcelaRepository.setarParcelaInativa(parcelaExcluida.getId());

        parcelaRepository.setarParcelaInativa(parcelaAtribuida.getId());

        Imovel imovel = imovelService.buscarPorRip(rip);

        if (numeroParcelas == Constants.DOIS) {
            Parcela parcela = parcelaRepository.findByIdDestinacaoImovelParcelaInicial(parcelaExcluida.getIdDestinacaoImoveis());
            parcela.setAtiva(true);
            parcelaRepository.save(parcela);
        } else {
            parcelaAtribuida.setImovel(imovel);
            parcelaExcluida.setImovel(imovel);
            Parcela parcelaCriada = criarNovaParcelaExclusao(parcelaExcluida, parcelaAtribuida);
            Integer sequencial = buscarUltimaSequenciaParcela(parcelaExcluida);
            String sequencialParcela = "P" + sequencial + Constants.UM;
            parcelaCriada.setSequencial(sequencialParcela);
            parcelaRepository.save(parcelaCriada);
            salvarBenfeitorias(parcelaCriada);
        }

    }

    private Parcela criarNovaParcelaExclusao(Parcela parcelaExcluida, Parcela parcelaAtribuida){
        Parcela parcelaCriada = new Parcela();
        List<DestinacaoImovel> destinacaoImoveis = destinacaoImovelService.findByIds(parcelaExcluida.getIdDestinacaoImoveis());
        parcelaCriada.setDestinacaoImoveis(destinacaoImoveis);
        List<Benfeitoria> listaTodasBenfeitorias = new ArrayList<>();

        parcelaCriada.setAtiva(Boolean.TRUE);
        parcelaCriada.setImovel(parcelaExcluida.getImovel());

        BigDecimal areaTerreno = parcelaExcluida.getAreaTerreno().add(parcelaAtribuida.getAreaTerreno());
        parcelaCriada.setAreaTerreno(areaTerreno);
        parcelaCriada.setAreaDisponivel(areaTerreno);

        setarListaBenfeitorias(parcelaExcluida.getBenfeitorias(), listaTodasBenfeitorias);
        setarListaBenfeitorias(parcelaAtribuida.getBenfeitorias(), listaTodasBenfeitorias);

        parcelaCriada.setBenfeitorias(listaTodasBenfeitorias);

        return parcelaCriada;
    }

    private void setarListaBenfeitorias(List<Benfeitoria> benfeitorias, List<Benfeitoria> listaTodasBenfeitorias) {
        if (benfeitorias != null) {
            benfeitorias.forEach(benfeitoria -> {
                benfeitoria.setId(null);
                listaTodasBenfeitorias.add(benfeitoria);
            });
        }
    }

    /**
     * Atualiza parcelas com nova destinação
     * @param parcelas
     * @param destinacaoImovel
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Parcela> atualizar(List<Parcela> parcelas, DestinacaoImovel destinacaoImovel) {
        List<Parcela> parcelasAtualizadas = new ArrayList<>();
        if (parcelas != null) {
            parcelas.forEach(parcela -> {
                Parcela parcelaAnterior = parcelaRepository.findOne(parcela.getId());
                if(destinacaoImovel.getFracaoIdeal() != null){
                    BigDecimal areaDisponivel = parcelaAnterior.getAreaTerreno().subtract(destinacaoImovel.getFracaoIdeal());
                    parcelaAnterior.setAreaDisponivel(areaDisponivel);
                }
                if(!parcelaAnterior.getDestinacaoImoveis().contains(destinacaoImovel)){
                    parcelaAnterior.getDestinacaoImoveis().add(destinacaoImovel);
                }
                parcelasAtualizadas.add(parcelaAnterior);
            });
            return parcelasAtualizadas;
        }
        return Collections.emptyList();
    }

    /**
     * Extrai o sequencial da ultima parcela criada.
     * @param parcelas
     * @return
     */
    public String getUltimaParcela(List<Parcela> parcelas) {
        Long maxIdParcela = parcelas.stream().collect(Collectors.summarizingLong(Parcela::getId)).getMax();
        Optional<Parcela> optional = parcelas.stream().filter(parcela -> parcela.getId().equals(maxIdParcela)).findFirst();

        return optional.get().getSequencial();
    }

    /**
     * Busca as parcelas sem utilização.
     * @param rip
     * @param codigoUtilizacao
     * @return
     */
    @Transactional(readOnly = true)
    public List<ParcelaDTO> buscarParcelasSemUso(String rip, String codigoUtilizacao) {
        List<Parcela> parcelas = parcelaRepository.buscarParcelasSemUso(rip, codigoUtilizacao);
        return converter(parcelas);
    }

    private List<ParcelaDTO> converter(List<Parcela> parcelas) {
        Type targetListType = new TypeToken<List<ParcelaDTO>>() {}.getType();
        return entityConverter.converterListaStrictLazyLoading(parcelas, targetListType);
    }

    /**
     * Recupera as parcelas sem utilizações do imóvel.
     * @param rip
     * @return
     */
    public List<ParcelaDTO> buscarParcelasCanceladas(String rip) {
        List<Parcela> parcelas = parcelaRepository.buscarParcelasCanceladas(rip);
        return converter(parcelas);
    }

    /**
     * Recupera os ids das parcelas utilizadas.
     * @param id
     * @return
     */
    public Set<Long> buscarIdsParcelasUtilizadasPorImovelId(Long id) {
        Set<Long> parcelasUtilizadas = parcelaRepository.buscarIdsParcelasUtilizadasPorImovelId(id);
        Optional<Set<Long>> optional = Optional.ofNullable(parcelasUtilizadas);
        Boolean idsPreenchidos = optional.map(lista -> !lista.isEmpty()).orElse(false);
        if (!idsPreenchidos) {
            return Collections.emptySet();
        }
        return parcelasUtilizadas;
    }

    /**
     *
     * @param parcela
     * @param idsParcelasUtilizadas
     * @return
     */
    public Boolean isUtilizada(Parcela parcela, Set<Long> idsParcelasUtilizadas) {
        return idsParcelasUtilizadas.contains(parcela.getId());
    }

    /**
     * Verifica se a parcela já foi destinada.
     * @param parcela
     * @return
     */
    @Transactional(readOnly = true)
    public Boolean isParcelaDestinada(Parcela parcela) {
        if (parcela != null) {
            Integer qtdParcelasJaDestinada = parcelaRepository.contarQuantidadeParcelaDestinacao(parcela.getId());
            return qtdParcelasJaDestinada > Constants.ZERO;
        }
        return Boolean.FALSE;
    }

    /**
     * Edita uma parcela
     * @param parcelaDTO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ParcelaDTO editar(ParcelaDTO parcelaDTO) throws NegocioException {
        Parcela parcela = parcelaRepository.findOne(parcelaDTO.getId());
        parcela.setMemorialDescritivo(parcelaDTO.getMemorialDescritivo());
        parcela.setArquivos(getArquivosSalvos(parcelaDTO.getArquivos()));
        parcela = parcelaRepository.save(parcela);
        arquivoService.remover(parcelaDTO.getArquivosExcluir());
        return entityConverter.converterStrict(parcela, ParcelaDTO.class);
    }

    private List<Arquivo> getArquivosSalvos(List<ArquivoDTO> arquivos) {
        if (arquivos != null) {
            List<Long> ids = arquivos.stream().map(map -> map.getId()).collect(Collectors.toList());
            return arquivoService.buscarArquivos(ids);
        }
        return Collections.emptyList();
    }


    /**
     * Extrai as parcelas ativas
     * @param parcelas
     * @return
     */
    public List<Parcela> extrairParcelasAtivas(List<Parcela> parcelas) {
        return parcelas.stream().filter(Parcela::getAtiva).collect(Collectors.toList());
    }

    /**
     * Edita uma lista de parcelas
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<ParcelaDTO> salvarListaParcelas(List<ParcelaDTO> parcelaDTOs) throws NegocioException {
        List<ParcelaDTO> parcelas = new ArrayList<>();
        for(ParcelaDTO parcelaDTO : parcelaDTOs) {
            Parcela parcela = parcelaRepository.findOne(parcelaDTO.getId());
            Destinacao destinacao = null;

            if(parcela.getAtiva()) {
                for (Benfeitoria benfeitoria : parcela.getBenfeitorias()) {
                    if(benfeitoria.getParcela() != null && benfeitoria.getParcela().getDestinacaoImoveis() != null && !benfeitoria.getParcela().getDestinacaoImoveis().isEmpty()) {
                        gerarPenenciaParcela(benfeitoria.getParcela().getDestinacaoImoveis(), parcelaDTO, parcela);
                        destinacao = benfeitoria.getParcela().getDestinacaoImoveis().get(0).getDestinacao();
                    }
                }
            }

            salvarParcelas(parcela, parcelaDTO);
            if(destinacao != null) {
                removerPendencias(destinacao.getId());
            }
        }
        return parcelas;
    }


    private void salvarParcelas(Parcela parcela, ParcelaDTO parcelaDTO){
        parcela.setAreaTerreno(parcelaDTO.getAreaTerreno());
        inativarBenfeitorias(parcela.getBenfeitorias());
        gerenciaBenfeitorias(parcelaDTO, parcela);

        parcelaRepository.save(parcela);
    }
    private void gerarPenenciaParcela(List<DestinacaoImovel> destinacaoImoveis, ParcelaDTO parcelaDTO, Parcela parcela){

        for (DestinacaoImovel destinacaoImovel : destinacaoImoveis) {
            if (destinacaoImovel.getUltimaDestinacao() && destinacaoImovel.getCodigoUtilizacao().equals(CODIGO_UTILIZACAO) && parcelaDTO.getAreaTerreno() != parcela.getAreaTerreno()) {
                destinacaoPendenciaService.gerarPendencia(destinacaoImovel.getDestinacao(), Constants.ID_PENDENCIA_AJUSTE_PARCELA_IMOVEL);
                parcela.setParcelacomPendenciaDestinacao(true);
            }

        }
    }

    private void removerPendencias(Long idDestinacao){
        DestinacaoPendenciaDTO pendenciaAjusteImovel;
        DestinacaoPendenciaDTO pendenciaJusteParcela;

        pendenciaAjusteImovel= destinacaoPendenciaService.buscarDestinacaoPendenciaPorId(idDestinacao, Constants.ID_PENDENCIA_AJUSTE_DESTINACAO_IMOVEL );
        pendenciaJusteParcela= destinacaoPendenciaService.buscarDestinacaoPendenciaPorId(idDestinacao, Constants.ID_PENDENCIA_AJUSTE_PARCELA_IMOVEL);

        if(pendenciaAjusteImovel != null){
            destinacaoPendenciaService.removerPendencia(idDestinacao, Constants.ID_PENDENCIA_AJUSTE_DESTINACAO_IMOVEL);
        }

        if(pendenciaJusteParcela != null){
            destinacaoPendenciaService.removerPendencia(idDestinacao, Constants.ID_PENDENCIA_AJUSTE_PARCELA_IMOVEL);
        }

    }

    private void inativarBenfeitorias(List<Benfeitoria> benfeitorias){
        for(Benfeitoria ben : benfeitorias) {
            ben.setAtiva(false);
        }
    }

    private void gerenciaBenfeitorias(ParcelaDTO parcelaDTO, Parcela parcela){

        // verificar se a benfeitoria da parcela existe,
        // se existir, ativar
        // se não existir, inserir
        for (BenfeitoriaDTO benfeitoriaDTO : parcelaDTO.getBenfeitorias()) {
            Benfeitoria ben = this.contem(parcela.getBenfeitorias(), benfeitoriaDTO);
            if (ben != null) {
                ben.setAtiva(true);
            } else {
                Benfeitoria benfeitoria = new Benfeitoria();
                setarBenfeitoria(benfeitoria, benfeitoriaDTO, parcela);
                parcela.getBenfeitorias().add(benfeitoria);
            }
        }

    }

    private void setarBenfeitoria(Benfeitoria benfeitoria, BenfeitoriaDTO benfeitoriaDTO, Parcela parcela){
        benfeitoria.setIdBenfeitoriaCadImovel(benfeitoriaDTO.getIdBenfeitoriaCadImovel());
        benfeitoria.setParcela(parcela);
        benfeitoria.setImovel(parcela.getImovel());
        benfeitoria.setCodigo(benfeitoriaDTO.getCodigo());
        benfeitoria.setAtiva(true);
        benfeitoria.setAreaConstruida(benfeitoriaDTO.getAreaConstruida());
        benfeitoria.setAreaDisponivel(benfeitoriaDTO.getAreaDisponivel());
        benfeitoria.setNome(benfeitoriaDTO.getNome());
        benfeitoria.setEspecializacao(benfeitoriaDTO.getEspecializacao());
    }

    private Benfeitoria contem(List<Benfeitoria> benfeitorias, BenfeitoriaDTO benDTO) {
        for (Benfeitoria ben : benfeitorias) {
            if (ben.getIdBenfeitoriaCadImovel().equals(benDTO.getIdBenfeitoriaCadImovel())) {
                return ben;
            }
        }
        return null;
    }

    public Parcela buscarParcelaPorIdImovelSequencial(Long idImovel, String sequencial){
        return parcelaRepository.buscarParcelaPorIdImovelSequencial(idImovel, sequencial);
    }


}
