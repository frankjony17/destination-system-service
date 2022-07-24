package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.BenfeitoriaDestinada;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.BenfeitoriaRepository;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import br.com.company.fks.destinacao.utils.ObjetoUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar operações com benfeitorias
 * Created by diego on 31/01/17.
 */
@Service
public class BenfeitoriaService {

    @Autowired
    private BenfeitoriaRepository benfeitoriaRepository;

    @Autowired
    private BenfeitoriaDestinadaService benfeitoriaDestinadaService;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private ParcelaService parcelaService;

    /**
     * Salva benfeitoria
     * @param benfeitoria
     * @return Benfeitoria salva
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Benfeitoria salvar(Benfeitoria benfeitoria) {
        return benfeitoriaRepository.save(benfeitoria);
    }

    /**
     * Sala uma lista de benfeitorias
     * @param benfeitorias
     * @param parcela
     * @return List<Benfeitoria> lista com as benfeitorias salvas
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Benfeitoria> salvar(List<Benfeitoria> benfeitorias, Parcela parcela, Imovel imovel) {
        List<Benfeitoria> benfeitoriasSalva = new ArrayList<>();
            for(Benfeitoria benfeitoriaCorrente: benfeitorias) {
                Benfeitoria benfeitoria = criarObjeto(parcela, imovel, benfeitoriaCorrente);
                if(benfeitoriaCorrente.getAreaDisponivel() != null){
                    benfeitoria.setAreaDisponivel(imovel.getAreaTerreno().subtract(benfeitoriaCorrente.getAreaDisponivel()));
                }else{
                    benfeitoria.setAreaDisponivel(imovel.getAreaTerreno());
                }
                benfeitoria.setNome(benfeitoriaCorrente.getNome());
                benfeitoria.setEspecializacao(benfeitoriaCorrente.getEspecializacao());


                benfeitoriasSalva.add(salvar(benfeitoria));
            }

        return benfeitoriasSalva;
    }

    /**
     *
     * @param parcela
     * @param imovel
     * @param benfeitoriaCorrente
     * @return
     */
    private Benfeitoria criarObjeto(Parcela parcela, Imovel imovel, Benfeitoria benfeitoriaCorrente){
        Benfeitoria benfeitoria = new Benfeitoria();
        benfeitoria.setParcela(parcela);
        benfeitoria.setImovel(imovel);
        benfeitoria.setIdBenfeitoriaCadImovel(benfeitoriaCorrente.getIdBenfeitoriaCadImovel());
        benfeitoria.setCodigo(benfeitoriaCorrente.getCodigo());
        benfeitoria.setAtiva(true);
        benfeitoria.setAreaConstruida(benfeitoriaCorrente.getAreaConstruida());
        return benfeitoria;
    }

    /**
     * Atualiza os dados das Benfeitorias
     * @param parcela
     * @param benfeitorias
     * @param pendenciasGeradas
     * @param benfeitoriasSalvas
     * @param imovel
     * @return List<Benfeitoria>
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Benfeitoria> atualizar(Parcela parcela,
                                       List<Benfeitoria> benfeitorias,
                                       Map<Long, Boolean> pendenciasGeradas,
                                       List<Benfeitoria> benfeitoriasSalvas,
                                       Imovel imovel) throws NegocioException {
        Map<Long, Benfeitoria> mapaBenfeitoriasSalvas = criarMapaBenfeitoria(benfeitoriasSalvas);
        Map<Long, Benfeitoria> mapaNovasBenfeitorias = criarMapaBenfeitoria(benfeitorias);

        mapaBenfeitoriasSalvas = removerListaBenfeitoriasSalvas(mapaBenfeitoriasSalvas, mapaNovasBenfeitorias, pendenciasGeradas);

        return atualizar(mapaBenfeitoriasSalvas, mapaNovasBenfeitorias, parcela, pendenciasGeradas, imovel);

    }

    private List<Benfeitoria> atualizar(Map<Long, Benfeitoria> mapaBenfeitoriasSalvas,
                                        Map<Long, Benfeitoria> mapaNovasBenfeitorias,
                                        Parcela parcela,
                                        Map<Long, Boolean> pendenciasGeradas,
                                        Imovel imovel) throws NegocioException {

        Set<String> camposSeremValidados = ObjetoUtils.criarCamposValidar("areaConstruida");
        List<Benfeitoria> benfeitoriasAtualizadas = new ArrayList<>();

        for (Map.Entry<Long, Benfeitoria> entry : mapaNovasBenfeitorias.entrySet()) {

            if (mapaBenfeitoriasSalvas.containsKey(entry.getKey())) {
                Benfeitoria benfeitoriaSalva = mapaBenfeitoriasSalvas.get(entry.getKey());
                Benfeitoria benfeitoriaNova = mapaNovasBenfeitorias.get(entry.getKey());

                tratarAtualizacaoBenfeitoriasJaExistentes(camposSeremValidados,
                                                          benfeitoriasAtualizadas,
                                                          benfeitoriaSalva,
                                                          benfeitoriaNova, pendenciasGeradas);

            } else {
                Benfeitoria benfeitoria = salvar(mapaNovasBenfeitorias, entry.getKey(), parcela, imovel);
                benfeitoriasAtualizadas.add(benfeitoria);
            }
        }

        return benfeitoriasAtualizadas;

    }

    private Benfeitoria salvar(Map<Long, Benfeitoria> mapaNovasBenfeitorias,
                               Long key,
                               Parcela parcela,
                               Imovel imovel) {
        Benfeitoria benfeitoria = mapaNovasBenfeitorias.get(key);
        benfeitoria.setParcela(parcela);
        benfeitoria.setImovel(imovel);
        benfeitoria.setAreaDisponivel(benfeitoria.getAreaDisponivel());
        return benfeitoriaRepository.save(benfeitoria);
    }

    private void tratarAtualizacaoBenfeitoriasJaExistentes(Set<String> camposSeremValidados,
                                                           List<Benfeitoria> benfeitoriasAtualizadas,
                                                           Benfeitoria benfeitoriaSalva, Benfeitoria benfeitoriaNova,
                                                           Map<Long, Boolean> pendenciasGeradas) throws NegocioException {
        if (ObjetoUtils.compararObjetosDiferentes(benfeitoriaSalva, benfeitoriaNova, camposSeremValidados)) {
            benfeitoriaSalva.setAreaConstruida(benfeitoriaNova.getAreaConstruida());
            benfeitoriaSalva.setAtiva(benfeitoriaNova.getAtiva());
            benfeitoriaSalva.setCodigo(benfeitoriaNova.getCodigo());
            benfeitoriaSalva.setAreaDisponivel(benfeitoriaNova.getAreaConstruida());
            benfeitoriaSalva.setIdBenfeitoriaCadImovel(benfeitoriaNova.getIdBenfeitoriaCadImovel());
            benfeitoriasAtualizadas.add(benfeitoriaRepository.save(benfeitoriaSalva));
            gerarPendenciaBenfitoriaParcelaJaDestinada(benfeitoriaSalva, pendenciasGeradas);
        } else {
            benfeitoriasAtualizadas.add(benfeitoriaSalva);
        }
    }

    private void gerarPendenciaBenfitoriaParcelaJaDestinada(Benfeitoria benfeitoria, Map<Long, Boolean> pendenciasGeradas) {

        if (parcelaService.isParcelaDestinada(benfeitoria.getParcela())) {
            pendenciasGeradas.put(Constants.ID_PENDENCIA_AJUSTE_DESTINACAO_IMOVEL, Boolean.TRUE);
        }

    }

    private Map<Long, Benfeitoria> removerListaBenfeitoriasSalvas(Map<Long, Benfeitoria> mapaBenfeitoriasSalvas,
                                                                  Map<Long, Benfeitoria> mapaNovasBenfeitorias,
                                                                  Map<Long, Boolean> pendenciasGeradas) {
        ConcurrentHashMap<Long, Benfeitoria> mapaSincronizado = new ConcurrentHashMap<>(mapaBenfeitoriasSalvas);

        mapaSincronizado.keySet().forEach(key -> {
            if (!mapaNovasBenfeitorias.containsKey(key)) {
                Benfeitoria benfeitoria = mapaSincronizado.get(key);
                gerarPendenciaBenfitoriaParcelaJaDestinada(benfeitoria, pendenciasGeradas);
                deletarBenfeitoria(benfeitoria);
                mapaSincronizado.remove(key);
            }
        });

        return new HashedMap(mapaSincronizado);
    }

    private void deletarBenfeitoria(Benfeitoria benfeitoria) {
        if (benfeitoria.getParcela() == null) {
            benfeitoriaRepository.deletarBenfeitoriaSemParcela(benfeitoria.getId());
        } else  {
            deletarBenfeitoriaParcelaAtiva(benfeitoria);
        }
    }

    private void deletarBenfeitoriaParcelaAtiva(Benfeitoria benfeitoria) {
        if (benfeitoria.getAtiva()) {
            benfeitoriaRepository.delete(benfeitoria.getId());
        }
    }

    private Map<Long, Benfeitoria> criarMapaBenfeitoria(List<Benfeitoria> benfeitorias) {
        Map<Long, Benfeitoria> mapa = new HashedMap();

        if (benfeitorias != null) {
            benfeitorias.forEach(benfeitoria -> mapa.put(benfeitoria.getIdBenfeitoriaCadImovel(), benfeitoria));
        }

        return mapa;
    }

    /**
     * Filtra as benfeitorias do tipo Edificação.
     * @param benfeitorias
     * @return
     */
    public List<Benfeitoria> filtrarBenfeitoriasEdificacao(List<Benfeitoria> benfeitorias) {

        if (benfeitorias != null) {
            return benfeitorias.stream()
                                  .filter(benfeitoria -> benfeitoria.getCodigo().startsWith("E"))
                                  .collect(Collectors.toList());
        }

        return new ArrayList<>();

    }

    public void inativar(List<Benfeitoria> lista) {
        lista.forEach(benf -> {
            benf.setAtiva(false);
            benfeitoriaRepository.save(benf);
        });
    }

    /**
     * Extrai as benfeitorias do tipo de edificação da parcela.
     * @param parcelas
     * @return
     */
    public List<Benfeitoria> extrairBenfeitorias(List<Parcela> parcelas) {

        List<Benfeitoria> benfeitorias = new ArrayList<>();
        List<Parcela> parcelasAtivas =
                parcelas.stream().filter(Parcela::getAtiva).collect(Collectors.toList());

        parcelasAtivas.forEach(parcela -> {
            List<Benfeitoria> benfeitoriasEdificacao =
                    filtrarBenfeitoriasEdificacao(parcela.getBenfeitorias());
            if (!benfeitoriasEdificacao.isEmpty()) {
                benfeitorias.addAll(benfeitoriasEdificacao);
            }
        });

        return benfeitorias;

    }

    /**
     * Atualiza a area disponivel das benfeitorias
     * @param benfeitoriaDestinadas
     */
    public void atualizarAreaDisponivel(List<BenfeitoriaDestinada> benfeitoriaDestinadas) {
        benfeitoriaDestinadas.forEach(benfeitoriaDestinada -> {
            Benfeitoria benfeitoria = benfeitoriaRepository.findOne(benfeitoriaDestinada.getIdBenfeitoria());
            BigDecimal areaDisponivel = benfeitoria.getAreaConstruida().subtract(benfeitoriaDestinada.getAreaUtilizar());
            benfeitoria.setAreaDisponivel(areaDisponivel);
            benfeitoriaRepository.save(benfeitoria);
        });
    }

    /**
     * Atualiza area disponivel para zero das benfeitorias disponiveis
     * @param benfeitorias
     */
    public void atualizarAreaDisponivelZero(List<Benfeitoria> benfeitorias) {
        if (benfeitorias != null) {
            benfeitorias.forEach(benfeitoria -> {
                benfeitoria.setAreaDisponivel(BigDecimal.ZERO);
                benfeitoriaRepository.save(benfeitoria);
            });
        }
    }

    /**
     * Atualiza area disponivel para zero das benfeitorias disponiveis
     * @param imovel
     *
     */

    public void atualizarAreaDisponivelBenfeitoria(Imovel imovel, Boolean copiaDestinacao) {
        List<Benfeitoria> benfeitorias = imovel.getBenfeitorias();
            for(Benfeitoria benfeitoria : benfeitorias){

                BenfeitoriaDestinada benfeitoriaDestinada = benfeitoriaDestinadaService.buscarBenfeitoriasPorIdRipImovel(imovel.getId(), benfeitoria.getId());
                if(benfeitoriaDestinada != null){
                    benfeitoria.setAreaDisponivel(benfeitoria.getAreaConstruida().subtract(benfeitoriaDestinada.getAreaUtilizar()));
                }else{
                    benfeitoria.setAreaDisponivel(benfeitoria.getAreaConstruida());
                }

                if(copiaDestinacao) {
                    benfeitoria.setId(null);
                }
            }

            benfeitoriaRepository.save(benfeitorias);
        }


    /**
     * Busca as benfeitorias de acordo com o id do cadastro imovel informado.
     * @param idCadastroImovel
     * @return
     */
    @Transactional(readOnly = true)
    public List<Benfeitoria> buscarBenfeitoriasPorImovelIdCadastro(Long idCadastroImovel) {
        return benfeitoriaRepository.buscarBenfeitoriasPorImovelIdCadastro(idCadastroImovel);
    }
    /**
     * Busca as benfeitorias de acordo com o id do cadastro imovel informado.
     * @param idCadastroImovel
     * @return
     */
    @Transactional(readOnly = true)
    public Benfeitoria buscarBenfeitoriaPorId(Long idCadastroImovel) {
        return benfeitoriaRepository.buscarBenfeitoriaPorId(idCadastroImovel);
    }


    /**
     * Extrai benfeitorias das parcelas salvas
     * @param parcelas
     * @return
     */
    public List<Benfeitoria> extrairBenfeitoriasParcelasSalvas(List<Parcela> parcelas) {
        List<Benfeitoria> benfeitoriasSalvas = new ArrayList<>();
        parcelas.forEach(parcela -> {
            if (parcela.getBenfeitorias() != null && !parcela.getBenfeitorias().isEmpty()) {
                benfeitoriasSalvas.addAll(parcela.getBenfeitorias());
            }
        });
        return benfeitoriasSalvas;
    }

    /**
     * * Busca as benfeitorias que não tem parcela do imovel
     * @return
     */
    @Transactional(readOnly = true)
    public List<Benfeitoria> buscarBenfeitoriasSemParcelaIdImovel(Long id) {
        return benfeitoriaRepository.buscarBenfeitoriasSemParcelaIdImovel(id);
    }


    public BigDecimal somaAreaConstruida(String rip) {
        return benfeitoriaRepository.sumAreaConstruida(rip);
    }
}
