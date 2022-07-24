package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.ItemVerificacao;
import br.com.company.fks.destinacao.repository.ItemVerificacaoRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações nos itens de verificação
 * Created by raphael on 06/12/16.
 */
@Service
public class ItemVerificacaoService {

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private ItemVerificacaoRepository itemVerificacaoRepository;

    /**
     * Insere a análise técnica recebida por parâmetro no item de veriricação e grava o mesmo no banco de dados
     * @param dto
     * @param analiseTecnica
     * @return ItemVerificacao gravado no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ItemVerificacao salvar(ItemVerificacaoDTO dto, AnaliseTecnica analiseTecnica) {
        ItemVerificacao itemVerificacao = entityConverter.converterStrict(dto, ItemVerificacao.class);
        itemVerificacao.setAnaliseTecnica(analiseTecnica);
        return itemVerificacaoRepository.save(itemVerificacao);
    }

    /**
     * Insere a análise técnica nos itens de verificação presentes em uma lista e grava a lista no banco de dados
     * @param dtos
     * @param analiseTecnica
     * @return List<ItemVerificacao> lista com os itens de verificação gravados no banco de dados
     */
    public List<ItemVerificacao> salvar(List<ItemVerificacaoDTO> dtos, AnaliseTecnica analiseTecnica){
        List<ItemVerificacao> result = new ArrayList<>();
        for (ItemVerificacaoDTO dto : dtos) {
            result.add(salvar(dto, analiseTecnica));
        }

        return result;
    }

    /**
     * Realiza uma busca nos itens de verificação por id da análise técnica
     * @param id
     * @return List<ItemVerificacaoDTO> lista dos itens de verificação encontrados
     */
    public List<ItemVerificacaoDTO> buscarPorIdAnalise(Long id) {
        List<ItemVerificacao> itens = itemVerificacaoRepository.buscarPorIdAnalise(id);

        List<ItemVerificacaoDTO> result = new ArrayList<>();
        for (ItemVerificacao item : itens) {
            result.add(entityConverter.converterStrict(item, ItemVerificacaoDTO.class));
        }

        return result;
    }
}
