package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.dto.HistoricoAnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.entidades.HistoricoAnaliseTecnica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by diego on 29/11/16.
 */
@Repository
public interface HistoricoAnaliseTecnicaRepository extends JpaRepository<HistoricoAnaliseTecnica, Long> {

    /**
     *
     * @param idAnaliseTecnica Recupera o historico de analise tecnica pelo ID da analise tecnica
     * @param pageable
     * @return
     */
    @Query(value = "SELECT new br.com.company.fks.destinacao.dominio.dto.HistoricoAnaliseTecnicaDTO(h.id, h.dataAlteracao, " +
            "statusAnterior, statusAtual, h.justificativa, h.nomeUsuario) " +
            " FROM HistoricoAnaliseTecnica h " +
            " LEFT JOIN h.statusAnaliseTecnicaAnterior statusAnterior " +
            " JOIN h.statusAnaliseTecnicaAtual statusAtual " +
            " JOIN h.analiseTecnica a WHERE a.id = :idAnaliseTecnica")
    Page<HistoricoAnaliseTecnicaDTO> findByAnaliseTecnicaId(@Param("idAnaliseTecnica") Long idAnaliseTecnica, Pageable pageable);

}
