package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.dto.EncargoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Encargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Basis Tecnologia on 22/07/2016.
 */
@Repository
public interface EncargoRepository extends JpaRepository<Encargo, Long> {

    @Query(value = " SELECT e.id_encargo, " +
            "e.ds_nome, " +
            "e.ic_cumprimento_encargo, " +
            "e.ic_utilizar_data_contrato, " +
            "e.dt_cumprimento " +
            "from destinacao.tb_encargo e " +
            "INNER JOIN  destinacao.tb_destinacao_encargo de " +
            "ON de.id_encargo = e.id_encargo " +
            "WHERE de.id_destinacao = :idDestinacao ", nativeQuery = true)
    List<Encargo> listaEncargos(@Param("idDestinacao") Long idDestinacao);

}
