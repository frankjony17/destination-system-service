package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.ItemVerificacaoEspecifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by diego on 05/12/16.
 */
@Repository
public interface ItemVerificacaoEspecificaRepository extends JpaRepository<ItemVerificacaoEspecifica, Long> {

    /**
     * Realiza um select na tabela itemVerificacaoEspecifica quando o id da análise técnica for igual ao id passado por parâmetro
     * @param id
     * @return
     */
    @Query("SELECT ive FROM ItemVerificacaoEspecifica ive WHERE ive.analiseTecnica.id = :id")
    List<ItemVerificacaoEspecifica> buscarPorIdAnalise(@Param("id") Long id);

    /**
     * Realiza um select nas tabelas ItemVerificacaoEspecitica, itemVerificacaoCheckList e analiseTecnica quando o id da itemVerificacaoCheckList for igual ao parâmetro recebido
     * @param id
     * @return
     */
    @Query("SELECT i.id from ItemVerificacaoEspecifica i JOIN i.itemVerificacaoCheckList iv " +
            "JOIN i.analiseTecnica a WHERE iv.id =:id")
    List<Long> buscarIdsItemVerificacaoEspecificaIdCheckList(@Param("id") Long id);

}
