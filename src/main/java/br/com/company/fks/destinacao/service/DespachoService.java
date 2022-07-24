package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.dominio.enums.PerfilEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import br.com.company.fks.destinacao.repository.DespachoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe responsável por realizar operações com Despachos
 * Created by raphael on 01/12/16.
 */
@Service
public class DespachoService {

    private static final int ID_DESPACHO_RETORNAR_CHEFIA = 12;

    @Autowired
    private DespachoRepository despachoRepository;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Busca Despachos por tipo de despacho
     * @param tipoDespacho
     * @return List<Despacho> lista com os despachos encontrados pelo tipo de despacho
     */
    public List<Despacho> buscarPorTipoDespacho(TipoDespachoEnum tipoDespacho){
        List<Despacho> despachos = despachoRepository.buscarPorTipoDespacho(tipoDespacho);
        PerfilEnum perfilEnum = usuarioService.getPerfil();
        if (perfilEnum == PerfilEnum.CHEFIA) {
            return removerDespachoPerfilChefia(despachos);
        }
        return despachos;
    }

    private List<Despacho> removerDespachoPerfilChefia(List<Despacho> despachos) {

        Stream<Despacho> despachosFiltrados = despachos.stream().filter(despacho ->
                despacho.getId() != ID_DESPACHO_RETORNAR_CHEFIA
        );

        return despachosFiltrados.collect(Collectors.toList());

    }

    /**
     * Filtra despachos dto por tipo
     * @param tipoDespachoEnum
     * @param despachos
     * @return List<DespachoDTO> lista com os despachos dto encontrados por tipo de despacho
     */
    public List<DespachoDTO> filtrarDespachos(TipoDespachoEnum tipoDespachoEnum, List<DespachoDTO> despachos) {
        Stream<DespachoDTO> despachosFiltrados  = despachos.stream().filter(despacho -> despacho.getTipoDespacho() == tipoDespachoEnum);
        return despachosFiltrados.collect(Collectors.toList());
    }

    /**
     * Oredena os despachos dto
     * @param despachos
     */
    public void sort(List<DespachoDTO> despachos) {
        Collections.sort(despachos, (a, b) -> b.getId().compareTo(a.getId()));
    }

}
