package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.dto.OcupanteDTO;
import br.com.company.fks.destinacao.dominio.entidades.Ocupante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by haillanderson on 11/07/17.
 */

@Repository
public interface OcupanteRepository extends JpaRepository<Ocupante, Long> {

    /**
     * Recupera os ocupantes pelo id da posse informal
     * @param idPosseInformal
     * @return
     */
    @Query("SELECT DISTINCT o " +
            "FROM Ocupante o " +
            "WHERE o.posseInformal.id =:idPosseInformal ")
    List<OcupanteDTO> findByIdPosseInformal(@Param("idPosseInformal") Long idPosseInformal);
}
