package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoEspecificoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.ItemVerificacaoEspecifica;
import br.com.company.fks.destinacao.repository.ItemVerificacaoEspecificaRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações com o Item de Verificação Específica
 * Created by diego on 05/12/16.
 */
@Service
public class ItemVerificacaoEspecificaService {

    @Autowired
    private ItemVerificacaoEspecificaRepository itemVerificacaoEspecificaRepository;

    @Autowired
    private EntityConverter entityConverter;

    /**
     * Insere a análise técnica recebida por parâmetro no intem de verificação espefícica e grava a mesma no banco de dados
     * @param dto
     * @param analiseTecnica
     * @return ItemVerificacaoEspecifica gravada no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ItemVerificacaoEspecifica salvar(ItemVerificacaoEspecificoDTO dto, AnaliseTecnica analiseTecnica) {
        ItemVerificacaoEspecifica itemVerificacaoEspecifica = entityConverter.converterStrict(dto, ItemVerificacaoEspecifica.class);
        itemVerificacaoEspecifica.setAnaliseTecnica(analiseTecnica);
        return itemVerificacaoEspecificaRepository.save(itemVerificacaoEspecifica);
    }

    /**
     * Insere a análise técnica recebida por parâmetro nos itens de verificação específicas da lista e grava a lista no banco de dados
     * @param dtos
     * @param analiseTecnica
     * @return List<ItemVerificacaoEspecifica> lista gravada no banco de dados
     */
    public List<ItemVerificacaoEspecifica> salvar(List<ItemVerificacaoEspecificoDTO> dtos, AnaliseTecnica analiseTecnica){
        List<ItemVerificacaoEspecifica> result = new ArrayList<>();
        for (ItemVerificacaoEspecificoDTO dto : dtos) {
            result.add(salvar(dto, analiseTecnica));
        }

        return result;
    }

    /**
     * Realiza uma busca nos itens de verificação específico por id da análise técnica
     * @param id
     * @return List<ItemVerificacaoEspecificoDTO> lista dos itens de verificação específica encontrados
     */
    public List<ItemVerificacaoEspecificoDTO> buscarPorIdAnalise(Long id) {
        List<ItemVerificacaoEspecifica> itens = itemVerificacaoEspecificaRepository.buscarPorIdAnalise(id);

        List<ItemVerificacaoEspecificoDTO> result = new ArrayList<>();
        for (ItemVerificacaoEspecifica item : itens) {
            result.add(entityConverter.converterStrict(item, ItemVerificacaoEspecificoDTO.class));
        }

        return result;
    }

    /**
     * Verifica se a análise técnica está sendo utilizado em algum item de verificação específica
     * @param id
     * @return true ou false
     */
    @Transactional(readOnly = true)
    public Boolean isUsadoAnaliseTecnica(Long id) {
        List<Long> idsItensVerificacao = itemVerificacaoEspecificaRepository.buscarIdsItemVerificacaoEspecificaIdCheckList(id);
        return !idsItensVerificacao.isEmpty();
    }
}
