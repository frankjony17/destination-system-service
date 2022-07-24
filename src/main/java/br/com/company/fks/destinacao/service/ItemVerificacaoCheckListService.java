package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoCheckListDTO;
import br.com.company.fks.destinacao.repository.ItemVerificacaoCheckListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe responsável por realizar busca nos Itens de Verificação check list
 * Created by diego on 02/12/16.
 */
@Service
public class ItemVerificacaoCheckListService {

    @Autowired
    private ItemVerificacaoCheckListRepository itemVerificacaoCheckListRepository;

    /**
     * Realiza uma busca nos intens de verificação check list por id do fundamento legal
     * @param idFundamentoLegal
     * @return List<ItemVerificacaoCheckListDTO> lista dos intens de verificação check list encontrados
     */
    @Transactional(readOnly = true)
    public List<ItemVerificacaoCheckListDTO>findByIdFundamentoLegal(Long idFundamentoLegal) {
        return itemVerificacaoCheckListRepository.findByIdFundamentoLegal(idFundamentoLegal);
    }
}
