package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoCheckListDTO;
import br.com.company.fks.destinacao.dominio.entidades.ItemVerificacaoCheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by diego on 02/12/16.
 */
@Repository
public interface ItemVerificacaoCheckListRepository extends JpaRepository<ItemVerificacaoCheckList, Long> {

    /**
     *
     * @param idFundamentoLegal Recupera a lista de Item de verificação de um determinado Fundamento Legal
     * @return
     */
    @Query("SELECT new br.com.company.fks.destinacao.dominio.dto.ItemVerificacaoCheckListDTO(i.id, i.descricao) " +
            "FROM ItemVerificacaoCheckList i WHERE i.codFundamentoLegal =:idFundamentoLegal")
    List<ItemVerificacaoCheckListDTO> findByIdFundamentoLegal(@Param("idFundamentoLegal") Long idFundamentoLegal);

}
