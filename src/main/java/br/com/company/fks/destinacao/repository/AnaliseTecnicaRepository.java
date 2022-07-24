package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by raphael on 05/12/16.
 */
@Repository
public interface AnaliseTecnicaRepository extends JpaRepository<AnaliseTecnica, Long> {

    /**
     *
     * @param requerimentoId Buscar a Analise Tecnica pelo ID do Requerimento
     * @return
     */
    @Query("SELECT a FROM AnaliseTecnica a WHERE a.idRequerimento = :requerimentoId")
    AnaliseTecnica buscarPorIdRequerimento(@Param("requerimentoId") Long requerimentoId);

}
