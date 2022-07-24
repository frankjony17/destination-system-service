package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.ItemVerificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raphael on 06/12/16.
 */
@Repository
public interface ItemVerificacaoRepository extends JpaRepository<ItemVerificacao, Long> {

    /**
     * Realiza um select na tabela ItemVerificacao quando o id for igual ao id recebido por parâmetro
     * @param id
     * @return
     */
    @Query("SELECT iv FROM ItemVerificacao iv WHERE iv.analiseTecnica.id = :id")
    List<ItemVerificacao> buscarPorIdAnalise(@Param("id") Long id);

}
