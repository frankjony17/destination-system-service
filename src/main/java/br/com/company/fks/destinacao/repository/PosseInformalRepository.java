package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Basis Tecnologia on 25/10/2016.
 */

@Repository
public interface PosseInformalRepository extends JpaRepository<PosseInformal, Long> {

    @Query(" SELECT o.id FROM PosseInformal p " +
            " JOIN p.ocupantes o " +
            " WHERE p.id = :id ")
    List<Long> buscarIdsOcupantesPosseInformal(@Param("id") Long id);

}
