package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.EncerramentoDestinacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EncerramentoDestinacaoRepository extends JpaRepository<EncerramentoDestinacao, Long> {

    @Query(" SELECT DISTINCT ce FROM EncerramentoDestinacao ce " +
            " WHERE ce.destinacao.id = :idDestinacao " +
            " AND ce.isAtivo = true ")
    EncerramentoDestinacao finByIdDestinacao(@Param("idDestinacao") Long idDestinacao);
}
