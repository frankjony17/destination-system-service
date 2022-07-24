package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.entidades.Dominio;
import br.com.company.fks.destinacao.repository.custom.DominioCustomRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações com Domínios
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Service
public class DominioService {

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DominioCustomRepository dominioCustomRepository;

    /**
     * Realiza uma busca de todos os domínios
     * @param clazz
     * @return List<DominioDTO> lista com todos os domínios
     */
    public List<DominioDTO> buscarTodos(Class clazz){

        List<Dominio> dominios = dominioCustomRepository.buscarTodos(clazz);

        return converter(dominios);
    }

    private List<DominioDTO> converter(List<Dominio> dominios) {
        List<DominioDTO> dominiosDTOs = new ArrayList<>();

        if (!dominios.isEmpty()) {
            Type targetListType = new TypeToken<List<DominioDTO>>() {}.getType();
            dominiosDTOs = entityConverter.converterListaStrictLazyLoading(dominios, targetListType);
        }

        return dominiosDTOs;
    }

    /**
     * Procura domínio por id
     * @param id
     * @param clazz
     * @return Dominio domínio encontrado por id do domínio
     */
    public Dominio findById(Integer id, Class clazz) {
        return dominioCustomRepository.buscarPorId(id, clazz);
    }


}
