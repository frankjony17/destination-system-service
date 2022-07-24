package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.CancelamentoEncerramento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tawan-souza on 19/12/17.
 */
@Repository
public interface CancelamentoEncerramentoUtilizacaoRepository extends JpaRepository<CancelamentoEncerramento, Long> {

    @Query(" SELECT DISTINCT ce FROM CancelamentoEncerramento ce " +
            " WHERE ce.destinacao.id = :idDestinacao " +
            " AND ce.isAtivo = true ")
    CancelamentoEncerramento finByIdDestinacao(@Param("idDestinacao") Long idDestinacao);
}
