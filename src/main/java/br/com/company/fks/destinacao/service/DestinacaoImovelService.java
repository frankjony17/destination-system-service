package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.UtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import br.com.company.fks.destinacao.repository.DestinacaoImovelRepository;
import br.com.company.fks.destinacao.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Classe resonsável por realizar operações com Destinações Imóvel
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Service
public class DestinacaoImovelService {

    private static final Integer SEQUENCIAL_INICIAL = 1;
    private static final Integer QTD_ZEROS_ESQUERDA = 4;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private DestinacaoImovelRepository destinacaoImovelRepository;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private BenfeitoriaDestinadaService benfeitoriaDestinadaService;

    @Autowired
    private GeradorCodigoUtilizacaoImovel geradorCodigoUtilizacaoImovel;

    /**
     * Salva destinação imóvel
     * @param destinacaoImovel
     * @return DestinacaoImovel salva
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public DestinacaoImovel salvar(DestinacaoImovel destinacaoImovel, Boolean copiaDestinacao) {
        destinacaoImovel.setImovel(imovelService.findById(destinacaoImovel.getImovel().getId()));
        destinacaoImovel.setDocumentos(arquivoService.findListaArquivoById(destinacaoImovel.getDocumentos()));
        destinacaoImovel.setFotoVideo(arquivoService.findListaArquivoById(destinacaoImovel.getFotoVideo()));
        destinacaoImovel.setBenfeitoriasDestinadas(
                benfeitoriaDestinadaService.salvar(destinacaoImovel.getBenfeitoriasDestinadas(), destinacaoImovel, copiaDestinacao));
        if (destinacaoImovel.getCodigoUtilizacao() == null) {
            String sequencial = StringUtils.leftPad(SEQUENCIAL_INICIAL.toString(), QTD_ZEROS_ESQUERDA, '0');
            String codigoUtilizacao = destinacaoImovel.getImovel().getRip() + "-" + sequencial;
            destinacaoImovel.setCodigoUtilizacao(codigoUtilizacao);
        }
        return destinacaoImovelRepository.save(destinacaoImovel);
    }

    /**
     * Salva destinação imóvel dentro da lista de destinações salvas
     * @param destinacoesImoveis
     * @param destinacao
     * @return List<DestinacaoImovel> lista final das destinações salvas
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<DestinacaoImovel> salvar(List<DestinacaoImovel> destinacoesImoveis, Destinacao destinacao, Boolean copiaDestinacao) {
        List<DestinacaoImovel> destinacaoImoveisSalvos = new ArrayList<>();
        geradorCodigoUtilizacaoImovel.gerarCodigoUtilizacao(destinacoesImoveis);

        for(DestinacaoImovel destinacaoImovel : destinacoesImoveis){
            if(copiaDestinacao){
                destinacaoImovel.setId(null);
            }

            destinacaoImovel.setDestinacao(destinacao);
            destinacaoImovel.setUltimaDestinacao(true);

            destinacaoImoveisSalvos.add(salvar(destinacaoImovel, false));
        }

        return destinacaoImoveisSalvos;
    }

    /**
     *Grava no banco a lista de destinações salvas
     * @param destinacoesImoveis
     * @return List<DestinacaoImovel> lista com as destinações gravadas no banco
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<DestinacaoImovel> salvar(List<DestinacaoImovel> destinacoesImoveis) {
        List<DestinacaoImovel> destinacaoImoveisSalvos = new ArrayList<>();

        destinacoesImoveis.forEach(destinacaoImovel -> {

            if (destinacaoImovel.getBenfeitoriasDestinadas() != null && destinacaoImovel.getBenfeitoriasDestinadas().isEmpty()) {
                destinacaoImovel.setBenfeitoriasDestinadas(benfeitoriaDestinadaService.salvar(destinacaoImovel.getBenfeitoriasDestinadas(), destinacaoImovel, false));
            }
            DestinacaoImovel destinacaoImovelSalva = destinacaoImovelRepository.save(destinacaoImovel);
            destinacaoImovelSalva.setImovel(imovelService.salvar(destinacaoImovel.getImovel(), destinacaoImovelSalva));
            Parcela parcelaSalva = getParcelaInicialSalva(destinacaoImovel.getImovel());
            addParcela(destinacaoImovelSalva, parcelaSalva);
            destinacaoImoveisSalvos.add(destinacaoImovelSalva);

        });

        return destinacaoImoveisSalvos;
    }

    private void addParcela(DestinacaoImovel destinacaoImovel, Parcela parcela) {
        if (destinacaoImovel.getParcelas() == null) {
            destinacaoImovel.setParcelas(new ArrayList<>());
        }
        destinacaoImovel.getParcelas().add(parcela);
    }

    private Parcela getParcelaInicialSalva(Imovel imovel) {
        return imovel.getParcelas().stream().findFirst().get();
    }

    /**
     * Verifica se a cidade da destinação é no exterior
     * @param destinacaoImoveis
     * @return true ou false
     */
    public Boolean verificarImovelExterior (List<DestinacaoImovel> destinacaoImoveis) {
        Boolean imovelExterior = Boolean.FALSE;

        for (DestinacaoImovel destinacaoImovel : destinacaoImoveis) {
            Endereco endereco = destinacaoImovel.getImovel().getEndereco();
            if (endereco.getCidadeExterior() != null) {
                imovelExterior = Boolean.TRUE;
                break;
            }
        }

        return imovelExterior;
    }

    /**
     * Busca a última destinação por id do imóvel
     * @param idImovel
     * @return nome da última destinação por id do imóvel
     */
    public String getUltimaUtilizadaoImovel(Long idImovel) {
        return destinacaoImovelRepository.getUltimaUtilizacaoImovel(idImovel);
    }

    /**
     * Busca código de utilização pelo rip e idParcela
     * @param rip
     * @return
     */
    @Transactional(readOnly = true)
    public DestinacaoImovelDTO buscarCodigoUtilizacao(String rip){
        return destinacaoImovelRepository.buscarCodigoUtilizacao(rip);
    }

    /**
     * Recupera uma destinação imovel pelo id.
     * @param id
     * @return DestinacaoImovel
     */
    @Transactional(readOnly = true)
    public DestinacaoImovel findById(Long id) {
        return destinacaoImovelRepository.findOne(id);
    }

    /**
     * Recupera uma lista de destinação imovel pelo id.
     * @param ids
     * @return List<DestinacaoImovel>
     */
    @Transactional(readOnly = true)
    public List<DestinacaoImovel> findByIds(Set<Long> ids) {
        return destinacaoImovelRepository.findByIds(ids);
    }

    /**
     * Recupera uma lista de destinação imovel pelo id.
     * @param id
     * @return List<DestinacaoImovel>
     */
    @Transactional(readOnly = true)
    public List<DestinacaoImovel> findByDestinacaoImovelById(Long id) {
        return destinacaoImovelRepository.findByDestinacaoImovelById(id);
    }

    /**
     * Busca Destinação Imóvel pelo id do imóvel e pelo sequencial da parcela.
     * @param idImovel
     * @param sequencial
     * @return
     */
    @Transactional(readOnly = true)
    public DestinacaoImovel findByIdImovelSequencialParcelaCodigoUtilizacao(Long idImovel, String sequencial, String codigoUtilizacao) {
        return destinacaoImovelRepository.findByIdImovelSequencialParcelaCodigoUtilizacao(idImovel, sequencial, codigoUtilizacao);
    }

    @Transactional(readOnly = true)
    public DestinacaoImovel findByIdImovelSequencial(Long idImovel) {
        return destinacaoImovelRepository.findByIdImovelSequencial(idImovel);
    }

    @Transactional(readOnly = true)
    public List<UtilizacaoDTO> buscarTodasUtilizacoesPorRip(String rip){
        return destinacaoImovelRepository.buscarTodasUtilizacoesPorRip(rip);
    }

    /**
     * Desmarca a destinação como a ultima destinada
     * @param idsImovel
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void desmarcarUltimaDestiancaoPorImovel(List<Long> idsImovel, Boolean copiaImovel) {

        if(copiaImovel){
            for(int i =0; i < idsImovel.size(); i++){
                List<DestinacaoImovel> destinacaoImoveis = destinacaoImovelRepository.buscarDestinacaoImovelPorId(idsImovel.get(i));

                String ultimoCodigoUlizacao = getUltimoCodigoUtilizacao(destinacaoImoveis);
                salvarDestinacaoImovel(destinacaoImoveis, ultimoCodigoUlizacao);
            }

        }else{
            idsImovel.forEach(id -> {
                List<DestinacaoImovel> destinacaoImoveis = destinacaoImovelRepository.buscarDestinacaoImovelPorId(id);
                String ultimoCodigoUlizacao = getUltimoCodigoUtilizacao(destinacaoImoveis);
               salvarDestinacaoImovel(destinacaoImoveis, ultimoCodigoUlizacao);
            });
        }
    }

    private void salvarDestinacaoImovel(List<DestinacaoImovel> destinacaoImoveis, String ultimoCodigoUlizacao){
        destinacaoImoveis.forEach(destinacaoImovel -> {
            if (!destinacaoImovel.getCodigoUtilizacao().equals(ultimoCodigoUlizacao)) {
                destinacaoImovel.setUltimaDestinacao(false);
            }
        });
        destinacaoImovelRepository.save(destinacaoImoveis);
    }

    private String getUltimoCodigoUtilizacao(List<DestinacaoImovel> destinacaoImoveis) {
        return destinacaoImoveis.get(destinacaoImoveis.size() - Constants.UM).getCodigoUtilizacao();
    }

    /**
     * Desmarca a destinação como a ultima destinada para destiações Cessao Gratuita, Termo Entrega e
     * demais que utilizam parcelamento parcial
     * @param idsImovel
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas(List<Long> idsImovel) {
        idsImovel.forEach(id -> {
            List<DestinacaoImovel> destinacaoImoveis = destinacaoImovelRepository.buscarDestinacaoImovelPorId(id);
            destinacaoImoveis.forEach(destinacaoImovel -> {
                if (destinacaoImovel.getCodigoUtilizacao().equals("0000")) {
                    destinacaoImovel.setUltimaDestinacao(false);
                }
            });
            destinacaoImovelRepository.save(destinacaoImoveis);
        });
    }

}
