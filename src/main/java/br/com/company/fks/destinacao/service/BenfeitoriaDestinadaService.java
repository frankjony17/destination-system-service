package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.BenfeitoriaDestinada;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.repository.BenfeitoriaDestinadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável por realizar operações com benfeitorias destinadas
 * Created by diego on 19/01/17.
 */
@Service
public class BenfeitoriaDestinadaService {

    @Autowired
    private BenfeitoriaDestinadaRepository benfeitoriaDestinadaRepository;

    /**
     * Sava a benfeitoria destinada
     * @param benfeitoriaDestinada
     * @return BenfeitoriaDestinada salva
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public BenfeitoriaDestinada salvar(BenfeitoriaDestinada benfeitoriaDestinada) {
        return benfeitoriaDestinadaRepository.save(benfeitoriaDestinada);
    }

    /**
     * Salva uma lista de benfeitorias destinadas
     * @param benfeitoriasDestinadas
     * @param destinacaoImovel
     * @return List<BenfeitoriaDestinada> lista com as benfeitorias destinadas salvas
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<BenfeitoriaDestinada> salvar(List<BenfeitoriaDestinada> benfeitoriasDestinadas, DestinacaoImovel destinacaoImovel, Boolean copiaDestinacao) {
        if (benfeitoriasDestinadas == null) {
            return new ArrayList<>();
        }
        List<BenfeitoriaDestinada> benfeitoriasDestinadasSalvas = new ArrayList<>();

        if(copiaDestinacao){
            for(BenfeitoriaDestinada benfeitoriaDestinada : destinacaoImovel.getBenfeitoriasDestinadas()){
                benfeitoriaDestinada.setAtiva(false);
                benfeitoriaDestinadaRepository.save(benfeitoriaDestinada);
            }
        }

        benfeitoriasDestinadas.forEach(benfeitoriaDestinada -> {
            benfeitoriaDestinada.setDestinacaoImovel(destinacaoImovel);
            benfeitoriasDestinadasSalvas.add(salvar(benfeitoriaDestinada));
        });
        return benfeitoriasDestinadasSalvas;
    }

    /**
     * Busca benfeitorias destinadas por RIP do imóvel
     * @param rip
     * @return List<BenfeitoriaDestinada> lista com as benfeitorias encontradas por RIP do imóvel
     */
    @Transactional(readOnly = true)
    public List<BenfeitoriaDestinada> buscarBenfeitoriasRipImovel(String rip) {
        return benfeitoriaDestinadaRepository.buscarBenfeitoriasIdImovel(rip);
    }

    /**
     * Busca benfeitorias destinadas por RIP do imóvel
     * @param idImovel
     * @return List<BenfeitoriaDestinada> lista com as benfeitorias encontradas por RIP do imóvel
     */
    @Transactional(readOnly = true)
    public BenfeitoriaDestinada buscarBenfeitoriasPorIdRipImovel(Long idImovel, Long id) {
        return benfeitoriaDestinadaRepository.buscarBenfeitoriasIdImovelIdBenfeitoria(idImovel, id);
    }

    /**
     * Busca benfeitorias destinadas por RIP do imóvel
     * @param id
     * @return List<BenfeitoriaDestinada> lista com as benfeitorias encontradas por RIP do imóvel
     */
    @Transactional(readOnly = true)
    public BenfeitoriaDestinada buscarBenfeitoriaPorId(Long id) {
        BenfeitoriaDestinada benfeitoriaDestinada = benfeitoriaDestinadaRepository.buscarBenfeitoriaPorId(id);
        if(benfeitoriaDestinada != null) {
            return benfeitoriaDestinada;
        }else{
            return null;
        }
    }

    /**
     * Busca benfeitorias destinadas por RIP do imóvel e popula um Map com as mesmas
     * @param rip
     * @return Map<Long,BigDecimal> mapa com as benfeitorias destinadas encontradas por RIP
     */
    @Transactional(readOnly = true)
    public Map<Long, BigDecimal> buscarMapaBenfeitoriasIdImovel(String rip) {
        List<BenfeitoriaDestinada> benfeitoriasDestinadas = buscarBenfeitoriasRipImovel(rip);
        Map<Long, BigDecimal> mapaBenfeitorias = new HashMap<>();
        benfeitoriasDestinadas.forEach(benfeitoria -> {
            if (!mapaBenfeitorias.containsKey(benfeitoria.getIdBenfeitoria())) {
                mapaBenfeitorias.put(benfeitoria.getIdBenfeitoria(), benfeitoria.getAreaUtilizar());
            } else {
                BigDecimal total = benfeitoria.getAreaUtilizar().add(mapaBenfeitorias.get(benfeitoria.getIdBenfeitoria()));
                mapaBenfeitorias.put(benfeitoria.getIdBenfeitoria(), total);
            }
        });
        return mapaBenfeitorias;
    }
}
