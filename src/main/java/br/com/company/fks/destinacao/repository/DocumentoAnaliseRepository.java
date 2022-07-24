package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.DocumentoAnalise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raphael on 06/12/16.
 */
@Repository
public interface DocumentoAnaliseRepository extends JpaRepository<DocumentoAnalise, Long> {

    /**
     *
     * @param id Traz uma lista de analise de documentos pelo ID da analise
     * @return
     */
    @Query("SELECT d FROM DocumentoAnalise d WHERE d.analiseTecnica.id = :id")
    List<DocumentoAnalise> buscarPorIdAnalise(@Param("id") Long id);

}
